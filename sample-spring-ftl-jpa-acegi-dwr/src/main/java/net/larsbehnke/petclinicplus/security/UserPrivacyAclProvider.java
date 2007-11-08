package net.larsbehnke.petclinicplus.security;

import java.lang.reflect.InvocationTargetException;

import net.larsbehnke.petclinicplus.model.UserDataHolder;
import net.larsbehnke.petclinicplus.util.SecurityUtils;

import org.acegisecurity.Authentication;
import org.acegisecurity.acl.AclEntry;
import org.acegisecurity.acl.AclProvider;
import org.acegisecurity.acl.basic.AclObjectIdentity;
import org.acegisecurity.acl.basic.BasicAclEntry;
import org.acegisecurity.acl.basic.NamedEntityObjectIdentity;
import org.acegisecurity.acl.basic.SimpleAclEntry;

/**
 * This ACL provider returns permission data for an user object. The following
 * rule is applied: <i>Users are only allowed to modify their own data.
 * Authenticated user have only read permissions for other user's data.</i>
 * This ACL provider is not backed by any data store. The ACL is calculated
 * on-the-fly by comparing the principal name with the user object's login name.
 * 
 * @author Lars Behnke
 * 
 */
public class UserPrivacyAclProvider implements AclProvider {

    /**
     * {@inheritDoc}
     */
    public AclEntry[] getAcls(Object domainInstance) {
        return getAcls(domainInstance, SecurityUtils.getCurrentUser());
    }

    /**
     * {@inheritDoc}
     */
    public AclEntry[] getAcls(Object domainInstance, Authentication authentication) {

        UserDataHolder user = (UserDataHolder) domainInstance;
        AclObjectIdentity aclOi;
        try {
            aclOi = new NamedEntityObjectIdentity(user);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
        int mask;
        if (user.getUserData() == null || user.getUserData().getUsername() == null || authentication == null
                || authentication.getName() == null) {
            mask = SimpleAclEntry.NOTHING;
        } else if (user.getUserData().getUsername().equals(authentication.getName())) {
            mask = SimpleAclEntry.READ_WRITE_CREATE_DELETE;
        } else {
            mask = SimpleAclEntry.READ;
        }
        BasicAclEntry acl = new SimpleAclEntry(authentication.getName(), aclOi, null, mask);

        return new BasicAclEntry[] { acl };
    }

    /**
     * {@inheritDoc}
     */
    public boolean supports(Object domainInstance) {
        return UserDataHolder.class.isAssignableFrom(domainInstance.getClass());
    }

}
