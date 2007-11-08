package net.larsbehnke.petclinicplus.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Simple JavaBean domain object adds a name property to <code>BaseEntity</code>.
 * Used as a base class for objects needing these properties.
 * <p>
 * Corresponding mapping:
 * </p>
 * 
 * <pre>
 *  &lt;mapped-superclass class=&quot;NamedEntity&quot;&gt;
 *       &lt;attributes&gt;
 *           &lt;basic name=&quot;name&quot;&gt;
 *               &lt;column name=&quot;NAME&quot; /&gt;
 *           &lt;/basic&gt;
 *       &lt;/attributes&gt;
 *   &lt;/mapped-superclass&gt;
 * </pre>
 * 
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Lars Behnke
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity {

    private String name;

    @Column(name = "NAME")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
