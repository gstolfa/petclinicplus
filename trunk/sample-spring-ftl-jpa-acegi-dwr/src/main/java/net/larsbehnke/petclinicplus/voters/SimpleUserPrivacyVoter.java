package net.larsbehnke.petclinicplus.voters;

import net.larsbehnke.petclinicplus.User;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthorizationServiceException;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.vote.AccessDecisionVoter;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

public class SimpleUserPrivacyVoter implements AccessDecisionVoter {

    public static String AUTHORITY_TOKEN = "USER_PRIVACY_ZONE";

    public boolean supports(ConfigAttribute attribute) {
        return AUTHORITY_TOKEN.equalsIgnoreCase(attribute.getAttribute());
    }

    @SuppressWarnings("unchecked")
    public boolean supports(Class clazz) {
        if (MethodInvocation.class.isAssignableFrom(clazz)) {
            return true;
        } else if (JoinPoint.class.isAssignableFrom(clazz)) {
            return true;
        } else if (User.class.isAssignableFrom(clazz)) {
            return true;
        } else {
            return false;
        }
    }


    public int vote(Authentication authentication, Object object, ConfigAttributeDefinition config) {
        if (authentication == null) {
            return ACCESS_DENIED;
        } 
        if (object == null || config == null) {
            return ACCESS_ABSTAIN;
        }

        User userObject = (User) getDomainObjectInstance(object);
        if (authentication.getName() == null || userObject.getLoginName() == null) {
            return ACCESS_DENIED;
        } else if (authentication.getName().equals(userObject.getLoginName())) {
            return ACCESS_GRANTED;
        } else {
            return ACCESS_DENIED;
        }

    }

    protected Object getDomainObjectInstance(Object secureObject) {
        Object[] args;
        Class<?>[] params;

        if (secureObject instanceof MethodInvocation) {
            MethodInvocation invocation = (MethodInvocation) secureObject;
            params = invocation.getMethod().getParameterTypes();
            args = invocation.getArguments();
        } else {
            JoinPoint jp = (JoinPoint) secureObject;
            params = ((CodeSignature) jp.getStaticPart().getSignature()).getParameterTypes();
            args = jp.getArgs();
        }

        for (int i = 0; i < params.length; i++) {
            if (User.class.isAssignableFrom(params[i])) {
                return args[i];
            }
        }

        throw new AuthorizationServiceException("Secure object: " + secureObject
            + " did not provide any argument of type: " + User.class);
    }
}
