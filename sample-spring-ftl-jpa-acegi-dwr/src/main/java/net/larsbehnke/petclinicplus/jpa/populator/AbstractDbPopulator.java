package net.larsbehnke.petclinicplus.jpa.populator;

import java.util.Collection;
import java.util.Set;

import net.larsbehnke.petclinicplus.model.Role;
import net.larsbehnke.petclinicplus.model.User;
import net.larsbehnke.petclinicplus.security.CRUDUserDetailsService;
import net.larsbehnke.petclinicplus.util.SecurityUtils;
import net.larsbehnke.petclinicplus.util.namedvocabulary.NamedVocabularyManager;

import org.acegisecurity.GrantedAuthority;

public abstract class AbstractDbPopulator implements DbPopulator {

    public static final String MODE_CLEAN_INSERT = "CLEAN_INSERT";
    public static final String MODE_INSERT_IF_EMPTY = "INSERT_IF_EMPTY";

    private String mode;
    private Set<String> adminAuthorities;
    private UserDataReader userDataReader;

    private CRUDUserDetailsService userDetailsService;

    private NamedVocabularyManager namedVocabularyManager;

    public NamedVocabularyManager getNamedVocabularyManager() {
        return namedVocabularyManager;
    }

    public void setNamedVocabularyManager(NamedVocabularyManager namedVocabularyManager) {
        this.namedVocabularyManager = namedVocabularyManager;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    public Set<String> getAdminAuthorities() {
        return adminAuthorities;
    }

    public void setAdminAuthorities(Set<String> adminAuthorities) {
        this.adminAuthorities = adminAuthorities;
    }

    public CRUDUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(CRUDUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserDataReader getUserDataReader() {
        return userDataReader;
    }

    public void setUserDataReader(UserDataReader userDataReader) {
        this.userDataReader = userDataReader;
    }

    public void populate() {

        String[] authArray = getAdminAuthorities().toArray(new String[] {});
        boolean asAdmin = authArray.length > 0;
        if (asAdmin)
            SecurityUtils.createSecureContext("populator", "populator", authArray);
        try {
            if (MODE_CLEAN_INSERT.equalsIgnoreCase(getMode())) {
                if (getUserDataReader() != null) {
                    getUserDetailsService().clearUsers();
                }
                doClear();
                populateUsers();
                doInsert();
            } else if (MODE_INSERT_IF_EMPTY.equalsIgnoreCase(getMode())) {
                if (getUserDataReader() != null && getUserDetailsService().countUsers() == 0) {
                    populateUsers();
                }
                doInsert();
            } else {
                throw new RuntimeException("Invalid database population mode: " + getMode());
            }
        } finally {
            if (asAdmin)
                SecurityUtils.clearSecurityContext();
        }

    }

    private void populateUsers() {
       
        Collection<User> list = getUserDataReader().read();
        for (User user : list) {
            user = getUserDetailsService().storeUser(user);
            updateRoleIds(list, user.getAuthorities());
        }
    }

    private void updateRoleIds(Collection<User> list, GrantedAuthority[] authorities) {
        for (int i = 0; i < authorities.length; i++) {
            Role role = (Role)authorities[i];
            for (User user : list) {
                for (GrantedAuthority userAuth : user.getAuthorities()) {
                    Role userRole = (Role)userAuth;
                    if (userRole.getName().equals(role.getName())) {
                        userRole.setId(role.getId());
                    }
                }
            }
        }
        
    }

    /**
     * Template method to be implemented by subclass.
     */
    protected abstract void doInsert();

    /**
     * Template method to be implemented by subclass.
     */
    abstract protected void doClear();

}
