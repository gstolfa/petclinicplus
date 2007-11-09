package net.larsbehnke.petclinicplus.dwr;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.larsbehnke.petclinicplus.model.Clinic;
import net.larsbehnke.petclinicplus.model.Owner;
import net.larsbehnke.petclinicplus.util.SecurityUtils;

/**
 * Entry point for DWR calls into the business layer.
 * @author Lars Behnke
 */
public class AjaxService implements Serializable {

    private static final long serialVersionUID = -6933943165065147957L;
    private static final Comparator<String> comparator = new MyComparator();
    private Clinic clinic;

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    /**
     * Simple method for testing DWR.
     * @return The current user.
     */
    public String getUserName() {
        if (SecurityUtils.getCurrentUser() == null) {
            return "no user";
        } else {
            return SecurityUtils.getCurrentUser().getName();
        }
    }

    /**
     * The method is called from the FindOwners dialog. See findOwners.ftl,
     * main.dec and custom.js for implementation details.
     * @param name The search string.
     * @return The list of matching owner names.
     */
    public String[] findOwnerNames(String name) {
        if (name == null || name.length() < 1) {
            return new String[0];
        }
        List<String> list = new ArrayList<String>();
        Collection<Owner> owners = getClinic().findOwners(name);
        for (Owner owner : owners) {
            list.add(owner.getUserData().getLastName());
        }
        Collections.sort(list, comparator);
        return list.toArray(new String[] {});
    }

    private static class MyComparator implements Comparator<String> {
        private Collator collator = Collator.getInstance();

        public int compare(String o1, String o2) {
            return collator.compare(o1, o2);
        }

    }
}
