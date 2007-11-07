package net.larsbehnke.petclinicplus.jpa;

import java.util.Collection;
import java.util.List;

import net.larsbehnke.petclinicplus.Clinic;
import net.larsbehnke.petclinicplus.PetType;
import net.larsbehnke.petclinicplus.Specialty;
import net.larsbehnke.petclinicplus.Vet;
import net.larsbehnke.petclinicplus.util.namedvocabulary.NVEntry;

/**
 * Helper class for populating database with initial data.
 * @author Lars Behnke
 * 
 */
public class ClinicDbPopulator extends AbstractDbPopulator {

	private Clinic clinic;

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	/*
	 * Note that repopulating the database may fail, if there are references to
	 * the database table already.
	 */
	@Override
	protected void doInsertClean() {
		clinic.clearVets();
		clinic.clearPetTypes();
		clinic.clearSpecialties();
		populateSpecialties();
		populatePetTypes();
		populateVets();

	}

	@Override
	protected void doInsertIfEmpty() {
		Collection<?> list;
		list = clinic.getPetTypes();
		if (list.size() == 0) {
			populatePetTypes();
		}
		list = clinic.getSpecialties();
		if (list.size() == 0) {
			populateSpecialties();
		}
		list = clinic.getVets();
		if (list.size() == 0) {
			populateVets();
		}

	}

	private void populateSpecialties() {
		List<NVEntry> list = getNamedVocabularyManager().getList("specialties");
		for (NVEntry entry : list) {
			Specialty specialty = new Specialty();
			specialty.setName(entry.getValue());
			clinic.storeSpecialty(specialty);
		}
	}

	private void populatePetTypes() {
		List<NVEntry> list = getNamedVocabularyManager().getList("pettypes");
		for (NVEntry entry : list) {
			PetType petType = new PetType();
			petType.setName(entry.getValue());
			clinic.storePetType(petType);
		}
	}
	
	private void populateVets() {
		Specialty[] specs = clinic.getSpecialties().toArray(new Specialty[0]);
		Vet vet;
		vet = new Vet();
		vet.setFirstName("David");
		vet.setLastName("Dalton");
		vet.setLoginName("dalton");
		vet.setPassword("dalton");
		vet.addSpecialty(specs[0]);
		vet = clinic.storeVet(vet);
		
		vet = new Vet();
		vet.setFirstName("Carry");
		vet.setLastName("Cameron");
		vet.setLoginName("cameron");
		vet.setPassword("cameron");
		vet.addSpecialty(specs[1]);
		vet.addSpecialty(specs[2]);
		clinic.storeVet(vet);
		
		

	}

}
