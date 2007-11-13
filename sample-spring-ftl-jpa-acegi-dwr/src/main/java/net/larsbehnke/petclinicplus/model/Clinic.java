package net.larsbehnke.petclinicplus.model;

import java.util.List;

import org.acegisecurity.annotation.Secured;
import org.springframework.dao.DataAccessException;

/**
 * The high-level PetClinic business interface.
 * <p>
 * This is basically a data access object. PetClinic doesn't have a dedicated
 * business facade.
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Lars Behnke
 */
public interface Clinic {

    /**
     * Clears all pet types.
     * @throws DataAccessException
     */
    @Secured( { "ROLE_SUPERVISOR" })
    void clearPetTypes() throws DataAccessException;

    /**
     * Clears all vet specialties.
     * @throws DataAccessException
     */
    @Secured( { "ROLE_SUPERVISOR" })
    void clearSpecialties() throws DataAccessException;

    /**
     * Clears all vets.
     * @throws DataAccessException
     */
    @Secured( { "ROLE_SUPERVISOR" })
    void clearVets() throws DataAccessException;

    /**
     * Retrieve <code>Owner</code>s from the datastore by last name,
     * returning all owners whose last name <i>starts</i> with the given name.
     * @param lastName Value to search for
     * @return a <code>Collection</code> of matching <code>Owner</code>s
     *         (or an empty <code>Collection</code> if none found)
     */
    @Secured( { "ROLE_USER" })
    List<Owner> findOwners(String lastName) throws DataAccessException;

    /**
     * Count <code>Owner</code>s in the datastore by last name,
     * returning the numer of all owners whose last name <i>starts</i> with the given name.
     * @param lastName Value to search for
     * @return a <code>Collection</code> of matching <code>Owner</code>s
     *         (or an empty <code>Collection</code> if none found)
     */
    @Secured( { "ROLE_USER" })
    Long countOwners(String lastName) throws DataAccessException;

    
    /**
     * Retrieve all <code>PetType</code>s from the datastore.
     * @return a <code>Collection</code> of <code>PetType</code>s
     */
    @Secured( { "ROLE_USER" })
    List<PetType> getPetTypes() throws DataAccessException;

    /**
     * Retrieve all <code>Specialties</code>s from the datastore.
     * @return a <code>Collection</code> of <code>Specialty</code>s
     */
    @Secured( { "ROLE_USER" })
    List<Specialty> getSpecialties() throws DataAccessException;

    /**
     * Retrieve all <code>Vet</code>s from the datastore.
     * @return a <code>Collection</code> of <code>Vet</code>s
     */
    @Secured( { "ROLE_USER" })
    List<Vet> getVets() throws DataAccessException;

    /**
     * Retrieve an <code>Owner</code> from the datastore by id.
     * @param id the id to search for
     * @return the <code>Owner</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not
     *         found
     */
    @Secured( { "ROLE_USER" })
    Owner loadOwner(int id) throws DataAccessException;

    /**
     * Retrieve a <code>Pet</code> from the datastore by id.
     * @param id the id to search for
     * @return the <code>Pet</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not
     *         found
     */
    @Secured( { "ROLE_USER" })
    Pet loadPet(int id) throws DataAccessException;

    /**
     * Retrieve a <code>Vet</code> from the datastore by id.
     * @param id the id to search for
     * @return the <code>Pet</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not
     *         found
     */
    @Secured( { "ROLE_USER" })
    Vet loadVet(int id) throws DataAccessException;

    /**
     * Retrieve a <code>Vet</code> from the datastore by its login name.
     * @param loginName the login name to search for
     * @return the <code>Pet</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not
     *         found
     */
    @Secured( { "ROLE_USER" })
    Vet loadVetByLoginName(String loginName) throws DataAccessException;

    /**
     * Save an <code>Owner</code> to the datastore, either inserting or
     * updating it.
     * @param owner the <code>Owner</code> to save
     * @see BaseEntity#isNew
     */
    @Secured( { "ROLE_USER" })
    Owner storeOwner(Owner owner) throws DataAccessException;

    /**
     * Save a <code>Pet</code> to the datastore, either inserting or updating
     * it.
     * @param pet the <code>Pet</code> to save
     * @see BaseEntity#isNew
     */
    @Secured( { "ROLE_USER" })
    Pet storePet(Pet pet) throws DataAccessException;

    /**
     * Stores a pet type. Called on populating the database.
     * @param petType The pet type to store
     * @throws DataAccessException
     */
    @Secured( { "ROLE_SUPERVISOR" })
    PetType storePetType(PetType petType) throws DataAccessException;

    /**
     * Stores a specialty. Called on populating the database.
     * @param specialtiy The vet specialty to store.
     * @throws DataAccessException
     */
    @Secured( { "ROLE_SUPERVISOR" })
    Specialty storeSpecialty(Specialty specialtiy) throws DataAccessException;

    /**
     * Save a <code>Vet</code> to the datastore, either inserting or updating
     * it.
     * @param vet the <code>Vet</code> to save
     * @see BaseEntity#isNew
     */
    @Secured( { "ACL_USER_WRITE", "ROLE_USER" })
    Vet storeVet(Vet vet) throws DataAccessException;

    /**
     * Save a <code>Visit</code> to the datastore, either inserting or
     * updating it.
     * @param visit the <code>Visit</code> to save
     * @see BaseEntity#isNew
     */
    @Secured( { "ROLE_USER" })
    Visit storeVisit(Visit visit) throws DataAccessException;

}
