package net.larsbehnke.petclinicplus.jpa.populator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;

import net.larsbehnke.petclinicplus.User;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.util.WebUtils;

/**
 * Reads user data from a xml file.
 * @author Lars Behnke
 */
public class XmlUserDataReader implements UserDataReader, ServletContextAware {

    private String initialUsersFile;

    private ServletContext servletContext;

    @SuppressWarnings("unchecked")
    public Collection<User> read() {
        Document doc = createDoc();
        Collection<User> users = new ArrayList<User>();
        List<Element> list = doc.selectNodes("users/user");
        for (Element element : list) {
            User user = new User();
            user.setUsername(getString(element, "loginname"));
            user.setPassword(getString(element, "password"));
            user.setFirstName(getString(element, "firstname"));
            user.setLastName(getString(element, "lastname"));
            user.setAccountNonExpired(getBoolean(element, "accountNonExpired", true));
            user.setAccountNonLocked(getBoolean(element, "accountNonLocked", true));
            user.setCredentialsNonExpired(getBoolean(element, "credentialsNonExpired", true));
            user.setEnabled(getBoolean(element, "enabled", true));
            List<Element> roleElems = element.selectNodes("roles/role");
            for (Element roleElem : roleElems) {
                user.addRole(roleElem.getTextTrim());
            }
            users.add(user);
        }
        return users;
    }

    private boolean getBoolean(Element element, String tag, boolean defaultValue) {
        String s = getString(element, tag, new Boolean(defaultValue).toString());
        Boolean b = new Boolean(s);
        return b.booleanValue();
    }

    private String getString(Element element, String tag) {
        return getString(element, tag, null);
    }

    private String getString(Element element, String tag, String defaultVal) {
        String s = element.elementText(tag);
        if (s == null || s.trim().length() == 0) {
            s = defaultVal;
        }
        return s;
    }

    public Document createDoc() {
        Document doc;
        SAXReader reader = new SAXReader();
        File file = null;
        try {
            file = resolveFilePath();
            doc = reader.read(file);
        } catch (Exception e) {
           throw new IllegalArgumentException("Could not create DOM from file " + file);
        } 
        return doc;
    }

    private File resolveFilePath() throws FileNotFoundException {
        String loc = getInitialUsersFile();
        if (!ResourceUtils.isUrl(loc)) {
            loc = SystemPropertyUtils.resolvePlaceholders(loc);
            loc = WebUtils.getRealPath(servletContext, loc);
        }
        servletContext.log("Resolving file path to " + getInitialUsersFile() + ": " + loc );
        return new File(loc);
    }

    public String getInitialUsersFile() {
        return initialUsersFile;
    }

    public void setInitialUsersFile(String initialUsersFile) {
        this.initialUsersFile = initialUsersFile;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;

    }

}
