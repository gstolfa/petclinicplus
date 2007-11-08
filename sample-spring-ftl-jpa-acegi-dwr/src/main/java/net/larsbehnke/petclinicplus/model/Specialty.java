package net.larsbehnke.petclinicplus.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Models a {@link Vet Vet's} specialty (for example, dentistry).
 * <p>
 * Corresponding mapping:
 * </p>
 * 
 * <pre>
 *    &lt;entity class=&quot;Specialty&quot;&gt;
 *        &lt;table name=&quot;SPECIALTIES&quot; /&gt;
 *    &lt;/entity&gt;
 * </pre>
 * 
 * @author Juergen Hoeller
 * @author Lars Behnke
 */
@Entity
@Table(name = "SPECIALTIES")
public class Specialty extends NamedEntity implements Serializable {

    private static final long serialVersionUID = 3894940748476692057L;

}
