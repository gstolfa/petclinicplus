package net.larsbehnke.petclinicplus;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

/**
 * Persistent user capable of collaborating with Acegi security.
 * @author Lars Behnke
 */
public class User extends BaseEntity implements UserDetails, Serializable {

    private static final long serialVersionUID = -1306581235758909330L;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private Set<Role> roles;

    private boolean credentialsNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean accountNonExpired = true;

    private boolean enabled = true;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GrantedAuthority[] getAuthorities() {
        return getRolesInternal().toArray(new GrantedAuthority[] {});
    }

    protected Set<Role> getRolesInternal() {
        if (roles == null) {
            roles = new HashSet<Role>();
        }
        return roles;
    }

    protected void setRolesInternal(Set<Role> roles) {
        this.roles = roles;
    }
    
    public void addRole(String role) {
        getRolesInternal().add(new Role(role));
    }

}
