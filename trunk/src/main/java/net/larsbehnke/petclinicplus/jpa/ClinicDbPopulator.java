package net.larsbehnke.petclinicplus.jpa;

import java.util.Collection;
import java.util.List;

import net.larsbehnke.petclinicplus.Clinic;
import net.larsbehnke.petclinicplus.PetType;
import net.larsbehnke.petclinicplus.Specialty;
import net.larsbehnke.petclinicplus.util.namedvocabulary.NVEntry;

/**
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

	@Override
	protected void doInsertClean() {
		clinic.clearPetTypes();
		populatePetTypes();
		clinic.clearSpecialties();
		populateSpecialties();
		
	}
	

	@Override
	protected void doInsertIfEmpty() {
		Collection list = clinic.getPetTypes();
		if (list.size() ==0) {
			populatePetTypes();
		}
		list = clinic.getSpecialties();
		if (list.size() ==0) {
			populateSpecialties();
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

}
