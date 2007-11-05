package net.larsbehnke.petclinicplus.voters;

import net.larsbehnke.petclinicplus.Vet;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.vote.AccessDecisionVoter;

public class VetAccessDecisionVoter implements AccessDecisionVoter {

	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean supports(Class clazz) {
		return true;
	}

	public int vote(Authentication authentication, Object object,
			ConfigAttributeDefinition config) {
		// TODO Auto-generated method stub
		return 0;
	}

}
