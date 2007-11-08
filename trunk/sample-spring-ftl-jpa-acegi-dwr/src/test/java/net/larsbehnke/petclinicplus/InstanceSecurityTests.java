package net.larsbehnke.petclinicplus;

import net.larsbehnke.petclinicplus.model.Clinic;
import net.larsbehnke.petclinicplus.model.Vet;
import net.larsbehnke.petclinicplus.util.SecurityUtils;

import org.junit.Test;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class InstanceSecurityTests extends
		AbstractDependencyInjectionSpringContextTests {

	private Clinic clinic;

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	@Test
	public void testEditOthersVetData() throws Exception {
		SecurityUtils.createSecureContext("user", "user",
				new String[] { "ROLE_USER" });
		Vet vet = getClinic().loadVetByLoginName("dalton");
		try {
			getClinic().storeVet(vet);
			fail();
		} catch (Exception e) {
		}

	}

	@Test
	public void testEditOwnVetData() throws Exception {
		SecurityUtils.createSecureContext("dalton", "dalton",
				new String[] { "ROLE_USER" });
		Vet vet = getClinic().loadVetByLoginName("dalton");
		getClinic().storeVet(vet);
	}

	/**
	 * Specifies the Spring configuration to load for this test fixture,
	 */
	protected String[] getConfigLocations() {
		return new String[] { "file:src/main/webapp/WEB-INF/app-ctx-acegi.xml",
				"file:src/main/webapp/WEB-INF/app-ctx-jpa.xml" };
	}

}
