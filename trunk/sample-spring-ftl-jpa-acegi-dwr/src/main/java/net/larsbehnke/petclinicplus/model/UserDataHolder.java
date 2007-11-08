package net.larsbehnke.petclinicplus.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * Parent class of various user categories, for instance vets and owner.
 * <p>
 * Corresponding mapping:
 * </p>
 * 
 * <pre>
 *  &lt;mapped-superclass class=&quot;UserDataHolder&quot;&gt;
 *        &lt;attributes&gt;
 *            &lt;one-to-one name=&quot;userData&quot; target-entity=&quot;User&quot;&gt;
 *                &lt;join-column name=&quot;USER_ID&quot; /&gt;
 *                &lt;cascade&gt;
 *                    &lt;cascade-all /&gt;
 *                &lt;/cascade&gt;
 *            &lt;/one-to-one&gt;
 *        &lt;/attributes&gt;
 *    &lt;/mapped-superclass&gt;
 * </pre>
 * 
 * @author Lars Behnke
 */
@MappedSuperclass
public abstract class UserDataHolder extends BaseEntity {

    private User userData;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "USER_ID")
    public User getUserData() {
        if (userData == null) {
            userData = new User();
        }
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

}
