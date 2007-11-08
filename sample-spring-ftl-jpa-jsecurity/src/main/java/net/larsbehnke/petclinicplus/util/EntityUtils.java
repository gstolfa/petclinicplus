package net.larsbehnke.petclinicplus.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.larsbehnke.petclinicplus.model.BaseEntity;
import net.larsbehnke.petclinicplus.model.NamedEntity;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * Utility methods for handling entities. Separate from the BaseEntity class
 * mainly because of dependency on the ORM-associated
 * ObjectRetrievalFailureException.
 * 
 * @author Juergen Hoeller
 * @author Lars Behnke
 * @since 29.10.2003
 * @see net.larsbehnke.petclinicplus.model.BaseEntity
 */
public abstract class EntityUtils {

	/**
	 * Look up the entity of the given class with the given id in the given
	 * collection.
	 * 
	 * @param entities
	 *            the collection to search
	 * @param entityClass
	 *            the entity class to look up
	 * @param entityId
	 *            the entity id to look up
	 * @return the found entity
	 * @throws ObjectRetrievalFailureException
	 *             if the entity was not found
	 */
	public static BaseEntity getById(Collection<? extends BaseEntity> entities, Class<? extends BaseEntity> entityClass,
			int entityId) throws ObjectRetrievalFailureException {
		for (BaseEntity entity : entities) {
			if (entity.getId().intValue() == entityId
					&& entityClass.isInstance(entity)) {
				return entity;
			}
		}
		throw new ObjectRetrievalFailureException(entityClass, new Integer(
				entityId));
	}

	/**
	 * Converts a list of named entities into a hash map. The entity id becomes
	 * the map key, The entity name becomes the corresponding map value.
	 * 
	 * @param list
	 *            The list of named entities.
	 * @return The map.
	 */
	public static Map<String, String> createMap(Collection<? extends NamedEntity> list) {
		Map<String, String> map = new HashMap<String, String>();
		for (NamedEntity entry : list) {
			map.put(entry.getId() + "", entry.getName());
		}
		return map;
	}
}
