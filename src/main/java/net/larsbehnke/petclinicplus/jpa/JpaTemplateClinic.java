package net.larsbehnke.petclinicplus.jpa;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import net.larsbehnke.petclinicplus.Clinic;
import net.larsbehnke.petclinicplus.Owner;
import net.larsbehnke.petclinicplus.Pet;
import net.larsbehnke.petclinicplus.PetType;
import net.larsbehnke.petclinicplus.Specialty;
import net.larsbehnke.petclinicplus.Visit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * JPA implementation of the Clinic interface.
 * 
 * <p>
 * The mappings are defined in "orm.xml" located in the META-INF dir.
 * 
 * @author Mike Keith
 * @since 22.04.2006
 */
public class JpaTemplateClinic extends JpaDaoSupport implements Clinic {
	
	private static Log log = LogFactory.getLog(JpaTemplateClinic.class);

	public Collection getVets() throws DataAccessException {
		return getJpaTemplate().find(
				"SELECT vet FROM Vet vet ORDER BY vet.lastName, vet.firstName");
	}

	public Collection getPetTypes() throws DataAccessException {
		return getJpaTemplate().find(
				"SELECT ptype FROM PetType ptype ORDER BY ptype.name");
	}
	
	public Collection getSpecialties() throws DataAccessException {
		return getJpaTemplate().find(
				"SELECT s FROM Specialty s");
	}

	public Collection findOwners(String lastName) throws DataAccessException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("lastName", lastName + "%");
		return getJpaTemplate()
				.findByNamedParams(
						"SELECT owner FROM Owner owner WHERE owner.lastName LIKE :lastName",
						map);
	}

	public Owner loadOwner(int id) throws DataAccessException {
		return getJpaTemplate().find(Owner.class, id);
	}

	public Pet loadPet(int id) throws DataAccessException {
		return getJpaTemplate().find(Pet.class, id);
	}

	public void storeOwner(Owner owner) throws DataAccessException {
		// consider using merge with returning the persistent object here
		getJpaTemplate().persist(owner);
	}

	public void storePet(Pet pet) throws DataAccessException {
		// consider using merge with returning the persistent object here
		getJpaTemplate().persist(pet);
	}

	public void storeVisit(Visit visit) throws DataAccessException {
		// consider using merge with returning the persistent object here
		getJpaTemplate().persist(visit);
	}

	public void storePetType(PetType petType) throws DataAccessException {
		getJpaTemplate().persist(petType);

	}

	public void storeSpecialty(Specialty specialtiy) throws DataAccessException {
		getJpaTemplate().persist(specialtiy);


	}

	public void clearSpecialties() throws DataAccessException {
		getJpaTemplate().execute(new JpaCallback() {

			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery("DELETE s FROM Specialty s");
				int count = query.executeUpdate();
				log.debug("Deleted " + count + " specialties.");
				return null;
			}
		});
	}

	public void clearPetTypes() throws DataAccessException {
		getJpaTemplate().execute(new JpaCallback() {

			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createQuery("DELETE t FROM PetType t");
				int count = query.executeUpdate();
				log.debug("Deleted " + count + " vet types.");
				return null;
			}
		});
	}
}
