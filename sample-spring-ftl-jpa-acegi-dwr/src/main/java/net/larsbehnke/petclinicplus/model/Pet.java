package net.larsbehnke.petclinicplus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

/**
 * Simple JavaBean business object representing a pet.
 * <p>
 * Corresponding mapping:
 * </p>
 * 
 * <pre>
 *  &lt;entity class=&quot;Pet&quot;&gt;
 *        &lt;table name=&quot;PETS&quot; /&gt;
 *        &lt;attributes&gt;
 *            &lt;basic name=&quot;birthDate&quot;&gt;
 *                &lt;column name=&quot;BIRTH_DATE&quot; /&gt;
 *                &lt;temporal&gt;DATE&lt;/temporal&gt;
 *            &lt;/basic&gt;
 *            &lt;many-to-one name=&quot;owner&quot; fetch=&quot;EAGER&quot;&gt;
 *                &lt;join-column name=&quot;OWNER_ID&quot; /&gt;
 *                &lt;cascade&gt;
 *                    &lt;cascade-all /&gt;
 *                &lt;/cascade&gt;
 *            &lt;/many-to-one&gt;
 *            &lt;many-to-one name=&quot;type&quot; fetch=&quot;EAGER&quot;&gt;
 *                &lt;join-column name=&quot;TYPE_ID&quot; /&gt;
 *                &lt;cascade&gt;
 *                    &lt;cascade-all /&gt;
 *                &lt;/cascade&gt;
 *            &lt;/many-to-one&gt;
 *            &lt;one-to-many name=&quot;visitsInternal&quot; target-entity=&quot;Visit&quot; mapped-by=&quot;pet&quot;
 *                fetch=&quot;EAGER&quot;&gt;
 *                &lt;cascade&gt;
 *                    &lt;cascade-all /&gt;
 *                &lt;/cascade&gt;
 *            &lt;/one-to-many&gt;
 *            &lt;transient name=&quot;visits&quot; /&gt;
 *        &lt;/attributes&gt;
 *    &lt;/entity&gt;
 * </pre>
 * 
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Lars Behnke
 */
@Entity
@Table(name = "PETS")
public class Pet extends NamedEntity implements Serializable {

    private static final long serialVersionUID = 4923609485226116823L;

    private Date birthDate;

    private PetType type;

    private Owner owner;

    private Set<Visit> visits;

    public void addVisit(Visit visit) {
        getVisitsInternal().add(visit);
        visit.setPet(this);
    }

    @Column(name = "BIRTH_DATE")
    public Date getBirthDate() {
        return this.birthDate;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Owner.class)
    @JoinColumn(name = "OWNER_ID")
    public Owner getOwner() {
        return owner;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = PetType.class)
    @JoinColumn(name = "TYPE_ID")
    public PetType getType() {
        return type;
    }

    @Transient
    public List<Visit> getVisits() {
        List<Visit> sortedVisits = new ArrayList<Visit>(getVisitsInternal());
        PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedVisits);
    }

    // TODO mappedBy owner superfuous here?
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Visit.class, mappedBy = "pet")
    protected Set<Visit> getVisitsInternal() {
        if (this.visits == null) {
            this.visits = new HashSet<Visit>();
        }
        return this.visits;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    protected void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    protected void setVisitsInternal(Set<Visit> visits) {
        this.visits = visits;
    }

}
