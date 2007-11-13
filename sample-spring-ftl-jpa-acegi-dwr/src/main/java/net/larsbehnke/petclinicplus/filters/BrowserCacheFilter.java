package net.larsbehnke.petclinicplus.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.AntPathMatcher;

public class BrowserCacheFilter implements Filter {

    private Log log = LogFactory.getLog(BrowserCacheFilter.class);
    private FilterConfig config;
    private List<String> includePatterns;
    private List<String> excludePatterns;
    private List<String> excludeHosts;
    private int seconds;
    private AntPathMatcher pathMatcher;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String clientAddr = req.getRemoteAddr();
        if (log.isDebugEnabled()) {
           log.debug("Client IP: " + clientAddr);
        }
        
        if (!hostMatches (clientAddr, excludeHosts)) {
            String uri = req.getRequestURI();
            boolean included = requestMatches(includePatterns, uri);
            boolean excluded = requestMatches(excludePatterns, uri);
            if (included && !excluded) {
                res = new CacheHttpResponseWrapper(res, seconds);
            }
        }
        chain.doFilter(req, res);
    }

    private boolean hostMatches(String clientAddr, List<String> excludeHosts) {
        if (clientAddr == null || excludeHosts == null) {
            throw new IllegalArgumentException();
        }
        for (String string : excludeHosts) {
            if (clientAddr.equals(string)) {
                if (log.isDebugEnabled()) {
                    log.debug("Host " + clientAddr + " excluded from " + this.getClass());
                }
                return true;
            }
        }
        return false;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        this.pathMatcher = new AntPathMatcher();
        this.pathMatcher.setPathSeparator("/");
        processFilterParams(filterConfig);

    }

    private boolean requestMatches(List<String> patterns, String requestStr) {
        for (String pattern : patterns) {
            if (pathMatcher.match(pattern, requestStr)) {
                return true;
            }
        }
        return false;
    }

    private void processFilterParams(FilterConfig cnf) {
        includePatterns = createList("includeUrls", true);
        excludePatterns = createList("excludeUrls", true);
        excludeHosts = createList("excludeHosts", false);
        try {
            seconds = Integer.parseInt(cnf.getInitParameter("seconds"));
        } catch (Exception e) {
            seconds = 10;
        }
    }

    private List<String> createList(String initParam, boolean validateAntPattern) {
        List<String> result = new ArrayList<String>();
        String includePatterns = config.getInitParameter(initParam);
        if (includePatterns != null) {
            String[] parts = includePatterns.split(",");
            for (int i = 0; i < parts.length; i++) {
                String entry = parts[i].trim();
                if (validateAntPattern) {
                    if (pathMatcher.isPattern(entry)) {
                        result.add(entry);
                    } else {
                        log.error("Invalid ANT style pattern: " + entry + ". Ignored!");
                    }
                } else {
                    result.add(entry);
                }
            }
        }
        return result;
    }
    
    private static class CacheHttpResponseWrapper extends HttpServletResponseWrapper {

        private static final int DEFAULT_LIFETIME = 3600;

        public CacheHttpResponseWrapper(HttpServletResponse res, int seconds) {

            super(res);
            if (seconds == 0) {

                /*
                 * Some Browsers may fail when both "Content-Disposition" and
                 * "Cache-Control" are set.
                 */
                if (!res.containsHeader("Content-Disposition")) {

                    /* HTTP 1.1 */
                    res.setHeader("Cache-Control", "no-cache, post-check=0, pre-check=0");
                }

                /* HTTP 1.0 */
                res.setHeader("Pragma", "no-cache");

            } else {
                if (seconds < 0) {
                    seconds = DEFAULT_LIFETIME;
                }
                Date currDate = new Date();
                long timeStamp = currDate.getTime();
                res.setHeader("Pragma", "");
                res.setHeader("Cache-Control", "");
                res.setDateHeader("Date", timeStamp);
                long endOfLifeStamp = timeStamp + seconds * 1000;
                res.setDateHeader("Expires", endOfLifeStamp);
            }

        }

        public void setHeader(String key, String val) {
            if (checkKey(key)) {
                super.setHeader(key, val);
            }
        }

        public void setDateHeader(String key, long val) {
            if (checkKey(key)) {
                super.setDateHeader(key, val);
            }
        }

        public void addDateHeader(String key, long val) {
            if (checkKey(key)) {
                super.addDateHeader(key, val);
            }
        }

        private boolean checkKey(String key) {
            if ("Pragma".equalsIgnoreCase(key) || "Cache-Control".equalsIgnoreCase(key)
                    || "Expires".equalsIgnoreCase(key)) {
                return false;

            } else {
                return true;
            }
        }

        public void addHeadder(String key, String val) {
            if (checkKey(key)) {
                super.addHeader(key, val);
            }
        }

    }

}
