package net.larsbehnke.petclinicplus.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The pet type (for instance cat, dog etc.).
 * <p>
 * Corresponding mapping:
 * </p>
 * 
 * <pre>
 *    &lt;entity class=&quot;PetType&quot;&gt;
 *        &lt;table name=&quot;TYPES&quot; /&gt;
 *    &lt;/entity&gt;
 * </pre>
 * 
 * @author Juergen Hoeller
 * @author Lars Behnke
 */
@Entity
@Table(name = "TYPES")
public class PetType extends NamedEntity implements Serializable {

    private static final long serialVersionUID = 4175258915657385718L;

}
