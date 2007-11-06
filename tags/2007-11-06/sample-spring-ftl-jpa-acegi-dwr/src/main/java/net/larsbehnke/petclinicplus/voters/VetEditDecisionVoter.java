package net.larsbehnke.petclinicplus.voters;

import net.larsbehnke.petclinicplus.Vet;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.vote.AccessDecisionVoter;

public class VetEditDecisionVoter implements AccessDecisionVoter {

	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return Vet.class.isAssignableFrom(clazz);
	}

	public int vote(Authentication authentication, Object object,
			ConfigAttributeDefinition config) {
		return 0;
	}

}
