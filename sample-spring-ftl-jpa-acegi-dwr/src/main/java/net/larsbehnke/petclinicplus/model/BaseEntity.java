package net.larsbehnke.petclinicplus.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Simple JavaBean domain object with an id property. Used as a base class for
 * objects needing this property.
 * <p>
 * Corresponding mapping:
 * </p>
 * 
 * <pre>
 *  &lt;mapped-superclass class=&quot;BaseEntity&quot;&gt;
 *       &lt;attributes&gt;
 *           &lt;id name=&quot;id&quot;&gt;
 *               &lt;column name=&quot;ID&quot; /&gt;
 *               &lt;generated-value strategy=&quot;IDENTITY&quot; /&gt;
 *           &lt;/id&gt;
 *           &lt;transient name=&quot;new&quot; /&gt;
 *       &lt;/attributes&gt;
 *   &lt;/mapped-superclass&gt;
 * </pre>
 * 
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Lars Behnke
 */
@MappedSuperclass
public abstract class BaseEntity {

    private Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    @Transient
    public boolean isNew() {
        return this.id == null;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
