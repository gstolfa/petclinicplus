package net.larsbehnke.petclinicplus.jpa.populator;

import java.util.Collection;
import java.util.Set;

import net.larsbehnke.petclinicplus.Role;
import net.larsbehnke.petclinicplus.User;
import net.larsbehnke.petclinicplus.security.CRUDUserDetailsService;
import net.larsbehnke.petclinicplus.util.SecurityUtils;
import net.larsbehnke.petclinicplus.util.namedvocabulary.NamedVocabularyManager;

import org.acegisecurity.GrantedAuthority;

public abstract class AbstractDbPopulator implements DbPopulator {

    public static final String MODE_CLEAN_INSERT = "CLEAN_INSERT";
    public static final String MODE_INSERT_IF_EMPTY = "INSERT_IF_EMPTY";

    private String mode;
    private String adminUser;
    private String adminPassword;
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

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Set<String> getAdminAuthorities() {
        return adminAuthorities;
    }

    public void setAdminAuthorities(Set<String> adminAuthorities) {
        this.adminAuthorities = adminAuthorities;
    }

    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
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
        boolean asAdmin = getAdminUser() != null;
        if (asAdmin)
            SecurityUtils.createSecureContext(getAdminUser(), getAdminPassword(), authArray);
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
