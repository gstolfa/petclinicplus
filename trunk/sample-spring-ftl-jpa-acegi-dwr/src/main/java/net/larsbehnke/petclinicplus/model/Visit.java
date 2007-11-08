package net.larsbehnke.petclinicplus.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Simple JavaBean domain object representing a visit.
 * <p>
 * Corresponding mapping:
 * </p>
 * 
 * <pre>
 * 
 *  &lt;entity class=&quot;Visit&quot;&gt;
 *        &lt;table name=&quot;VISITS&quot; /&gt;
 *        &lt;attributes&gt;
 *            &lt;basic name=&quot;date&quot;&gt;
 *                &lt;column name=&quot;VISIT_DATE&quot; /&gt;
 *                &lt;temporal&gt;DATE&lt;/temporal&gt;
 *            &lt;/basic&gt;
 *            &lt;basic name=&quot;description&quot;&gt;
 *                &lt;column name=&quot;DESCRIPTION&quot; /&gt;
 *            &lt;/basic&gt;
 *            &lt;many-to-one name=&quot;pet&quot; fetch=&quot;EAGER&quot;&gt;
 *                &lt;join-column name=&quot;PET_ID&quot; /&gt;
 *                &lt;cascade&gt;
 *                    &lt;cascade-all /&gt;
 *                &lt;/cascade&gt;
 *            &lt;/many-to-one&gt;
 *            &lt;many-to-one name=&quot;vet&quot; fetch=&quot;EAGER&quot;&gt;
 *                &lt;join-column name=&quot;VET_ID&quot; /&gt;
 *                &lt;cascade&gt;
 *                    &lt;cascade-all /&gt;
 *                &lt;/cascade&gt;
 *            &lt;/many-to-one&gt;
 *        &lt;/attributes&gt;
 *    &lt;/entity&gt;
 * </pre>
 * 
 * @author Ken Krebs
 * @author Lars Behnke
 */
@Entity
@Table(name = "VISITS")
public class Visit extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8355837672830445948L;

    /** Holds value of property date. */
    private Date date;

    /** Holds value of property description. */
    private String description;

    /** Holds value of property pet. */
    private Pet pet;

    /** Holds value of property vet. */
    private Vet vet;

    /** Creates a new instance of Visit for the current date */
    public Visit() {
        this.date = new Date();
    }

    /**
     * Getter for property date.
     * @return Value of property date.
     */
    @Column(name = "VISIT_DATE")
    @Temporal(value = TemporalType.DATE)
    public Date getDate() {
        return this.date;
    }

    /**
     * Getter for property description.
     * @return Value of property description.
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for property pet.
     * @return Value of property pet.
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "PET_ID")
    public Pet getPet() {
        return this.pet;
    }

    /**
     * Getter for property vet.
     * @return Value of property vet.
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "VET_ID")
    public Vet getVet() {
        return vet;
    }

    /**
     * Setter for property date.
     * @param date New value of property date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Setter for property description.
     * @param description New value of property description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter for property pet.
     * @param pet New value of property pet.
     */
    protected void setPet(Pet pet) {
        this.pet = pet;
    }

    /**
     * Setter for property vet.
     * @param pet New value of property vet.
     */
    public void setVet(Vet vet) {
        this.vet = vet;
    }

}
