package net.larsbehnke.petclinicplus.model;

import java.util.Collection;
import java.util.List;

import net.larsbehnke.petclinicplus.model.Clinic;
import net.larsbehnke.petclinicplus.model.Owner;
import net.larsbehnke.petclinicplus.model.PetType;
import net.larsbehnke.petclinicplus.model.Specialty;
import net.larsbehnke.petclinicplus.model.User;
import net.larsbehnke.petclinicplus.model.Vet;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Base class for Clinic tests. Allows subclasses to specify context locations.
 * <p>
 * This class extends {@link AbstractTransactionalDataSourceSpringContextTests},
 * one of the valuable test superclasses provided in the
 * org.springframework.test package. This represents best practice for
 * integration tests with Spring.
 * <p>
 * The AbstractTransactionalDataSourceSpringContextTests superclass provides the
 * following services:
 * <li>Injects test dependencies, meaning that we don't need to perform
 * application context lookups. See the setClinic() method. Injection uses
 * autowiring by type.
 * <li>Executes each test method in its own transaction, which is automatically
 * rolled back by default. This means that even if tests insert or otherwise
 * change database state, there is no need for a teardown or cleanup script.
 * <li>Provides useful inherited protected fields, such as a JdbcTemplate that
 * can be used to verify database state after test operations, or verify the
 * results of queries performed by application code. An ApplicationContext is
 * also inherited, and can be used for explicit lookup if necessary.
 * <p>
 * {@link AbstractTransactionalDataSourceSpringContextTests} and related classes
 * are shipped in <code>spring-mock.jar</code>.
 * @author Ken Krebs
 * @author Rod Johnson
 * @author Juergen Hoeller
 */
public class ClinicServiceTests extends AbstractTransactionalDataSourceSpringContextTests {

    protected Clinic clinic;

    /**
     * This method is provided to set the Clinic instance being tested by the
     * Dependency Injection injection behaviour of the superclass from the
     * <code>org.springframework.test</code> package.
     * @param clinic the Clinic implementation to test
     */
    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    @Test
    public void testGetVets() {
        Collection<Vet> vets = this.clinic.getVets();

        // Use the inherited JdbcTemplate (from
        // AbstractTransactionalDataSourceSpringContextTests)
        // to verify the results of the query
        assertEquals("JDBC query must show the same number of vets", jdbcTemplate
                .queryForInt("SELECT COUNT(0) FROM VETS"), vets.size());

        Vet vetCreated = createVet(1);
        if (clinic.loadVetByLoginName(vetCreated.getUserData().getUsername()) != null) {
            fail("Inconsistent database");
        }
        vetCreated = clinic.storeVet(vetCreated);
        Vet vetLoaded = clinic.loadVetByLoginName(vetCreated.getUserData().getUsername());
        assertEquals(vetCreated.getFavoriteAnimal(), vetLoaded.getFavoriteAnimal());
        assertEquals(vetCreated.getUserData().getFirstName(), vetLoaded.getUserData()
                .getFirstName());
        assertEquals(vetCreated.getUserData().getLastName(), vetLoaded.getUserData().getLastName());

        List<Specialty> specs = clinic.getSpecialties();
        assertTrue(specs.size() >= 2);
        vetCreated.addSpecialty(specs.get(0));
        vetCreated.addSpecialty(specs.get(1));
        clinic.storeVet(vetCreated);
        vetLoaded = clinic.loadVetByLoginName(vetCreated.getUserData().getUsername());
        assertEquals(vetCreated.getSpecialtiesReadOnly().size(), vetLoaded.getSpecialtiesReadOnly()
                .size());
        assertEquals(vetCreated.getSpecialtiesReadOnly().get(0).getName(), vetLoaded
                .getSpecialtiesReadOnly().get(0).getName());
        assertEquals(vetCreated.getSpecialtiesReadOnly().get(1).getName(), vetLoaded
                .getSpecialtiesReadOnly().get(1).getName());

        clinic.clearVets();
        vetLoaded = clinic.loadVetByLoginName(vetCreated.getUserData().getUsername());
        assertNull(vetLoaded);
    }

    private Vet createVet(int suffix) {
        Vet vet = new Vet();
        User ud = new User();
        ud.setUsername("username" + suffix);
        ud.setLastName("lastname" + suffix);
        ud.setFirstName("firstname" + suffix);

        vet.setUserData(ud);
        vet.setFavoriteAnimal("Monkey" + suffix);
        return vet;
    }

    @Test
    public void testGetPetTypes() {
        List<PetType> petTypes = this.clinic.getPetTypes();
        assertEquals("JDBC query must show the same number of pet typess", jdbcTemplate
                .queryForInt("SELECT COUNT(0) FROM TYPES"), petTypes.size());
    }

    @Test
    public void testFindOwners() {
        List<Owner> owners = this.clinic.findOwners("Scott");
        assertEquals(1, owners.size());
        assertTrue(owners.get(0).getUserData().getLastName().startsWith("Scott"));
        owners = this.clinic.findOwners("Scotty");
        assertEquals(0, owners.size());
    }

    @Test
    public void testLoadOwner() {
        List<Owner> owners = this.clinic.findOwners("Scott");
        assertEquals(1, owners.size());
        Owner o1 = owners.get(0);
        Owner o2 = this.clinic.loadOwner(o1.getId());
        assertEquals(o1.getAddress(), o2.getAddress());
        assertEquals(o1.getCity(), o2.getCity());
        assertEquals(o1.getUserData().getFirstName(), o2.getUserData().getFirstName());

        // Check lazy loading, by ending the transaction
        endTransaction();
        // Now Owners are "disconnected" from the data store.
        // We might need to touch this collection if we switched to lazy loading
        // in mapping files, but this test would pick this up.
        o1.getPets();
    }

    @Test
    public void testInsertOwner() {
        List<Owner> owners = this.clinic.findOwners("Schultz");
        int found = owners.size();
        Owner owner = new Owner();
        owner.getUserData().setLastName("Schultz");
        this.clinic.storeOwner(owner);
        // assertTrue(!owner.isNew());
        owners = this.clinic.findOwners("Schultz");
        assertEquals(found + 1, owners.size());
    }

    @Test
    public void testUpdateOwner() throws Exception {
        Owner owner = new Owner();
        owner.getUserData().setUsername("testuser1234");
        owner.getUserData().setLastName("Before");
        owner = clinic.storeOwner(owner);
        owner.getUserData().setLastName("After");
        clinic.storeOwner(owner);
        Owner ownerLoaded = clinic.loadOwner(owner.getId());
        assertEquals("After", ownerLoaded.getUserData().getLastName());
    }

    @Test
    public void testLoadPet() {
//        Collection<PetType> types = this.clinic.getPetTypes();
//        Pet p7 = this.clinic.loadPet(7);
//        assertTrue(p7.getName().startsWith("Samantha"));
//        assertEquals(EntityUtils.getById(types, PetType.class, 1).getId(), p7.getType().getId());
//        assertEquals("Jean", p7.getOwner().getUserData().getFirstName());
//        Pet p6 = this.clinic.loadPet(6);
//        assertEquals("George", p6.getName());
//        assertEquals(EntityUtils.getById(types, PetType.class, 4).getId(), p6.getType().getId());
//        assertEquals("Peter", p6.getOwner().getUserData().getFirstName());
    }

    @Test
    public void testInsertPet() {
//        Owner o6 = this.clinic.loadOwner("");
//        int found = o6.getPets().size();
//        Pet pet = new Pet();
//        pet.setName("bowser");
//        Collection<PetType> types = this.clinic.getPetTypes();
//        pet.setType((PetType) EntityUtils.getById(types, PetType.class, 2));
//        pet.setBirthDate(new Date());
//        o6.addPet(pet);
//        assertEquals(found + 1, o6.getPets().size());
//        // both storePet and storeOwner are necessary to cover all ORM tools
//        this.clinic.storePet(pet);
//        this.clinic.storeOwner(o6);
//        // assertTrue(!pet.isNew()); -- NOT TRUE FOR TOPLINK (before commit)
//        o6 = this.clinic.loadOwner(6);
//        assertEquals(found + 1, o6.getPets().size());
    }

    @Test
    public void testUpdatePet() throws Exception {
//        Pet p7 = this.clinic.loadPet(7);
//        String old = p7.getName();
//        p7.setName(old + "X");
//        this.clinic.storePet(p7);
//        p7 = this.clinic.loadPet(7);
//        assertEquals(old + "X", p7.getName());
    }

    @Test
    public void testInsertVisit() {
//        Pet p7 = this.clinic.loadPet(7);
//        int found = p7.getVisits().size();
//        Visit visit = new Visit();
//        p7.addVisit(visit);
//        visit.setDescription("test");
//        // both storeVisit and storePet are necessary to cover all ORM tools
//        this.clinic.storeVisit(visit);
//        this.clinic.storePet(p7);
//        // assertTrue(!visit.isNew()); -- NOT TRUE FOR TOPLINK (before commit)
//        p7 = this.clinic.loadPet(7);
//        assertEquals(found + 1, p7.getVisits().size());
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "classpath:/app-ctx-jpa-test.xml" };
    }
}
