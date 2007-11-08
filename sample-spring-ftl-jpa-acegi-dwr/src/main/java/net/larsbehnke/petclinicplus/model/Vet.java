package net.larsbehnke.petclinicplus.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

/**
 * Simple JavaBean domain object representing a veterinarian.
 * <p>
 * Corresponding mapping:
 * </p>
 * 
 * <pre>
 *  &lt;entity class=&quot;Vet&quot;&gt;
 *        &lt;table name=&quot;VETS&quot; /&gt;
 *        &lt;attributes&gt;
 *            &lt;basic name=&quot;favoriteAnimal&quot;&gt;
 *                &lt;column name=&quot;FAV_ANIMAL&quot; /&gt;
 *            &lt;/basic&gt;
 * 
 *            &lt;many-to-many name=&quot;specialtiesInternal&quot; target-entity=&quot;Specialty&quot; fetch=&quot;EAGER&quot;&gt;
 *                &lt;join-table name=&quot;VET_SPECIALTIES&quot;&gt;
 *                    &lt;join-column name=&quot;VET_ID&quot; /&gt;
 *                    &lt;inverse-join-column name=&quot;SPECIALTY_ID&quot; /&gt;
 *                &lt;/join-table&gt;
 *                &lt;cascade&gt;
 *                    &lt;cascade-all /&gt;
 *                &lt;/cascade&gt;
 *            &lt;/many-to-many&gt;
 *            &lt;transient name=&quot;specialtiesReadOnly&quot; /&gt;
 *            &lt;transient name=&quot;nrOfSpecialties&quot; /&gt;
 *        &lt;/attributes&gt;
 *    &lt;/entity&gt;
 * </pre>
 * 
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Lars Behnke
 */
@Entity
@Table(name = "VETS")
public class Vet extends UserDataHolder implements Serializable {

    private static final long serialVersionUID = -2034818079578897612L;

    private Set<Specialty> specialties;

    private String favoriteAnimal;

    public void addSpecialty(Specialty specialty) {
        getSpecialtiesInternal().add(specialty);
    }

    @Column(name = "FAV_ANIMAL")
    public String getFavoriteAnimal() {
        return favoriteAnimal;
    }

    @Transient
    public int getNrOfSpecialties() {
        return getSpecialtiesInternal().size();
    }

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Specialty.class, fetch = FetchType.EAGER)
    @JoinTable(name = "VET_SPECIALTIES", joinColumns = @JoinColumn(name = "VET_ID"), inverseJoinColumns = @JoinColumn(name = "SPECIALTY_ID"))
    public Set<Specialty> getSpecialtiesInternal() {
        if (this.specialties == null) {
            this.specialties = new HashSet<Specialty>();
        }
        return this.specialties;
    }

    @Transient
    public List<Specialty> getSpecialtiesReadOnly() {
        List<Specialty> sortedSpecs = new ArrayList<Specialty>(getSpecialtiesInternal());
        PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecs);
    }

    public void setFavoriteAnimal(String favoriteAnimal) {
        this.favoriteAnimal = favoriteAnimal;
    }

    public void setSpecialtiesInternal(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

}
