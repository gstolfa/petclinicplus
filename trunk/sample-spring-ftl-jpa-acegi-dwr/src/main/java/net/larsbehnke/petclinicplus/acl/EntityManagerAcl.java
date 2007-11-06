package net.larsbehnke.petclinicplus.acl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.acegisecurity.acl.basic.AclObjectIdentity;
import org.acegisecurity.acl.basic.BasicAclDao;
import org.acegisecurity.acl.basic.BasicAclEntry;
import org.acegisecurity.acl.basic.NamedEntityObjectIdentity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA implementation of the BasicAclDao interface using EntityManager.
 * 
 * <p>
 * The mappings are defined in "orm-acl.xml" located in the META-INF dir.
 * 
 * @author Lars Behnke
 */
@Repository
@Transactional
@NamedQueries( {
        @NamedQuery(name = "selectObjectIdentities", query = "SELECT oi FROM AcegiAclObjectIdentity oi WHERE oi.objectIdentity = :oi"),
        @NamedQuery(name = "selectPermissions", query = "SELECT p FROM AcegiAclPermission p WHERE p.aclObjectIdentity.id = :oiId") })
public class EntityManagerAcl implements BasicAclDao {

    @PersistenceContext
    private EntityManager em;

    public static final String RECIPIENT_USED_FOR_INHERITENCE_MARKER = "___INHERITENCE_MARKER_ONLY___";

    @SuppressWarnings("unchecked")
    public BasicAclEntry[] getAcls(AclObjectIdentity aclObjectIdentity) {

        String oiName = convertAclObjectIdentityToString(aclObjectIdentity);
        Query query = em.createNamedQuery("selectObjectIdentities");
        query.setParameter("oi", oiName);
        List<AcegiAclObjectIdentity> oiResult = query.getResultList();
        if (oiResult.size() == 0) {
            return null;
        }
        AcegiAclObjectIdentity oi = oiResult.get(0);
        query = em.createQuery("selectPermissions");
        query.setParameter("oiId", oi.getId());
        List<AcegiAclPermission> aclList = query.getResultList();
        if (aclList.size() == 0) {
            return new BasicAclEntry[] { createBasicAclEntry(oi, null) };
        } else {
            List toReturnAcls = new ArrayList();
            for (AcegiAclPermission acl : aclList) {
                toReturnAcls.add(createBasicAclEntry(oi, acl));
            }
            return (BasicAclEntry[]) toReturnAcls.toArray(new BasicAclEntry[] {});
        }

    }

    /**
     * Constructs an individual <code>BasicAclEntry</code> from the passed
     * <code>AclDetailsHolder</code>s.
     * <P>
     * Guarantees to never return <code>null</code> (exceptions are thrown in
     * the event of any issues).
     * </p>
     * 
     * @param propertiesInformation
     *            mandatory information about which instance to create, the
     *            object identity, and the parent object identity (<code>null</code>
     *            or empty <code>String</code>s prohibited for
     *            <code>aclClass</code> and <code>aclObjectIdentity</code>
     * @param aclInformation
     *            optional information about the individual ACL record (if
     *            <code>null</code> only an "inheritance marker" instance is
     *            returned which will include a recipient of {@link
     *            #RECIPIENT_USED_FOR_INHERITENCE_MARKER} ; if not
     *            <code>null</code>, it is prohibited to present
     *            <code>null</code> or an empty <code>String</code> for
     *            <code>recipient</code>)
     * 
     * @return a fully populated instance suitable for use by external objects
     * 
     * @throws IllegalArgumentException
     *             if the indicated ACL class could not be created
     */
    private BasicAclEntry createBasicAclEntry(AcegiAclObjectIdentity propertiesInfo,
            AcegiAclPermission aclInfo) {
        BasicAclEntry entry;

        try {
            entry = (BasicAclEntry) Class.forName(propertiesInfo.getAclClass()).newInstance();
        } catch (InstantiationException ie) {
            throw new IllegalArgumentException(ie.getMessage());
        } catch (IllegalAccessException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        } catch (ClassNotFoundException cnfe) {
            throw new IllegalArgumentException(cnfe.getMessage());
        }

        AclObjectIdentity aoi = buildIdentity(propertiesInfo.getObjectIdentity());
        AclObjectIdentity paoi = buildIdentity(propertiesInfo.getParentObject().getObjectIdentity());

        entry.setAclObjectIdentity(aoi);
        entry.setAclObjectParentIdentity(paoi);

        if (aclInfo == null) {
            entry.setMask(0);
            entry.setRecipient(RECIPIENT_USED_FOR_INHERITENCE_MARKER);
        } else {
            entry.setMask(aclInfo.getMask());
            entry.setRecipient(aclInfo.getRecipient());
        }

        return entry;
    }

    private AclObjectIdentity buildIdentity(String identity) {
        if (identity == null) {
            return null;
        }

        int delim = identity.lastIndexOf(":");
        String classname = identity.substring(0, delim);
        String id = identity.substring(delim + 1);

        return new NamedEntityObjectIdentity(classname, id);
    }

    /**
     * Responsible for covering a <code>AclObjectIdentity</code> to a
     * <code>String</code> that can be located in the RDBMS.
     * 
     * @param aclObjectIdentity
     *            to locate
     * 
     * @return the object identity as a <code>String</code>
     */
    protected String convertAclObjectIdentityToString(AclObjectIdentity aclObjectIdentity) {
        NamedEntityObjectIdentity neoi = (NamedEntityObjectIdentity) aclObjectIdentity;
        return neoi.getClassname() + ":" + neoi.getId();
    }

}
