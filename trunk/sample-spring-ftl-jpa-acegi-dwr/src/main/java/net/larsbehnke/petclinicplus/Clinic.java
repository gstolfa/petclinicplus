package net.larsbehnke.petclinicplus;

import java.util.Collection;

import org.acegisecurity.annotation.Secured;
import org.springframework.dao.DataAccessException;

/**
 * The high-level PetClinic business interface.
 *
 * <p>This is basically a data access object.
 * PetClinic doesn't have a dedicated business facade.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Lars Behnke
 */

public interface Clinic {

	/**
	 * Retrieve all <code>Vet</code>s from the datastore.
	 * @return a <code>Collection</code> of <code>Vet</code>s
	 */
    @Secured({"ROLE_USER"})
	Collection<Vet> getVets() throws DataAccessException;

	/**
	 * Retrieve all <code>PetType</code>s from the datastore.
	 * @return a <code>Collection</code> of <code>PetType</code>s
	 */
    @Secured({"ROLE_USER"})
	Collection<PetType> getPetTypes() throws DataAccessException;

	/**
	 * Retrieve all <code>Specialties</code>s from the datastore.
	 * @return a <code>Collection</code> of <code>Specialty</code>s
	 */
    @Secured({"ROLE_USER"})
	Collection<Specialty> getSpecialties() throws DataAccessException;
	
	/**
	 * Retrieve <code>Owner</code>s from the datastore by last name,
	 * returning all owners whose last name <i>starts</i> with the given name.
	 * @param lastName Value to search for
	 * @return a <code>Collection</code> of matching <code>Owner</code>s
	 * (or an empty <code>Collection</code> if none found)
	 */
    @Secured({"ROLE_USER"})
	Collection<Owner> findOwners(String lastName) throws DataAccessException;

	/**
	 * Retrieve an <code>Owner</code> from the datastore by id.
	 * @param id the id to search for
	 * @return the <code>Owner</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */
	Owner loadOwner(int id) throws DataAccessException;

	/**
	 * Retrieve a <code>Pet</code> from the datastore by id.
	 * @param id the id to search for
	 * @return the <code>Pet</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */
	Pet loadPet(int id) throws DataAccessException;

	/**
	 * Retrieve a <code>Vet</code> from the datastore by id.
	 * @param id the id to search for
	 * @return the <code>Pet</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */
	Vet loadVet(int id) throws DataAccessException;

	/**
	 * Retrieve a <code>Vet</code> from the datastore by its login name.
	 * @param loginName the login name to search for
	 * @return the <code>Pet</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */
	Vet loadVetByLoginName(String loginName) throws DataAccessException;

	
	/**
	 * Save an <code>Owner</code> to the datastore,
	 * either inserting or updating it.
	 * @param owner the <code>Owner</code> to save
	 * @see BaseEntity#isNew
	 */
	Owner storeOwner(Owner owner) throws DataAccessException;

	/**
	 * Save a <code>Pet</code> to the datastore,
	 * either inserting or updating it.
	 * @param pet the <code>Pet</code> to save
	 * @see BaseEntity#isNew
	 */
	Pet storePet(Pet pet) throws DataAccessException;

	/**
	 * Save a <code>Visit</code> to the datastore,
	 * either inserting or updating it.
	 * @param visit the <code>Visit</code> to save
	 * @see BaseEntity#isNew
	 */
	Visit storeVisit(Visit visit) throws DataAccessException;

	/**
	 * Save a <code>Vet</code> to the datastore,
	 * either inserting or updating it.
	 * @param vet the <code>Vet</code> to save
	 * @see BaseEntity#isNew
	 */
	@Secured({"ACL_USER_WRITE", "ROLE_SUPERVISOR"})
	Vet storeVet(Vet vet) throws DataAccessException;


	/**
	 * Stores a pet type. Called on populating the database.
	 * @param petType The pet type to store
	 * @throws DataAccessException
	 */
	PetType storePetType(PetType petType) throws DataAccessException ;
	
	/**
	 * Stores a specialty. Called on populating the database.
	 * @param specialtiy The vet specialty to store.
	 * @throws DataAccessException
	 */
	Specialty storeSpecialty(Specialty specialtiy) throws DataAccessException ;
	
	/**
	 * Clears all vet specialties.
	 * @throws DataAccessException
	 */
	@Secured({"ROLE_SUPERVISOR" })
	void clearSpecialties() throws DataAccessException;
	
	/**
	 * Clears all pet types.
	 * @throws DataAccessException
	 */
	@Secured({"ROLE_SUPERVISOR" })
	void clearPetTypes() throws DataAccessException;

	/**
	 * Clears  all vets.
	 * @throws DataAccessException
	 */
	@Secured({"ROLE_SUPERVISOR" })
	void clearVets() throws DataAccessException;



}