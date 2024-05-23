package com.codejstudio.lin.persistence.neo4j.entityFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PropertyContainer;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.pojo.AbstractRelationableInformationElement;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lin.common.exception.LINException;
import com.codejstudio.lin.persistence.neo4j.entityFactory.i.IRelationshipFactory;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lin4j_v1.0.0
 */
public class BaseRelationFactory extends OwnableInformationElementFactory implements IRelationshipFactory {

	/* constants */

	public static final String PROPERTY_KEY_PRIMARY_ELEMENT = "primaryElement";
	public static final String PROPERTY_KEY_SECONDARY_ELEMENT = "secondaryElement";

	public static final RelationshipType DEFAULT_RELATION_TYPE 
			= RelationType.getRelationType(BaseElement.generateTypeFromClass(BaseRelation.class));


	/* variables */

	private static BaseRelationFactory FACTORY;


	/* constructors */

	protected BaseRelationFactory() {}


	/* initializers */

	/**
	 * only for com.codejstudio.lim.common.util.InitializationUtil#autoInit() usage
	 */
	static void autoInit() {}

	static {
		try {
			registerFactoryForInit(BaseElement.getSubPojoClassCollection(BaseRelation.class), 
					BaseRelationFactory.class);
		} catch (LIMException e) {
			throw new RuntimeException(e);
		}
	}


	public static BaseRelationFactory getInstance() {
		if (FACTORY == null) {
			FACTORY = new BaseRelationFactory();
		}
		return FACTORY;
	}


	/* overridden methods */

	@Override
	public Relationship createRelationship(final Map<String, Node> nodeMap, final BaseElement element) {
		if (!(element instanceof BaseRelation)) {
			return null;
		}
		BaseRelation relation = (BaseRelation) element;

		AbstractRelationableInformationElement primaryElement = relation.getPrimaryElement();
		AbstractRelationableInformationElement secondaryElement = relation.getSecondaryElement();

		Node primary = nodeMap.get(primaryElement.getId());
		Node secondary = nodeMap.get(secondaryElement.getId());

		String type = relation.getType();
		Relationship relationship = primary.createRelationshipTo(secondary, 
				(type!=null) ? RelationType.getRelationType(type) : DEFAULT_RELATION_TYPE);
		this.setProperty(relationship, relation, true);
		return relationship;
	}

	public void setProperty(PropertyContainer propertyContainer, final BaseRelation element, 
			final boolean superPropertyFlag) {
		if (superPropertyFlag) {
			super.setProperty(propertyContainer, element, superPropertyFlag);
		}

		BaseElement bpe = element.getBasePrimaryElement();
		if (bpe != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_PRIMARY_ELEMENT, BaseElementFactory.convertToPlainText(bpe));
		}

		BaseElement bse = element.getBaseSecondaryElement();
		if (bse != null) {
			propertyContainer.setProperty(
					PROPERTY_KEY_SECONDARY_ELEMENT, BaseElementFactory.convertToPlainText(bse));
		}
	}


	@Override
	public BaseRelation loadRelationship(final Relationship relationship) throws LIMException {
		return loadRelationship(relationship, 
				!(relationship != null 
				&& relationship.getType().name().equals(DEFAULT_RELATION_TYPE.name())));
	}

	@Override
	public BaseRelation loadRelationship(final Relationship relationship, final boolean initTypeFlag) 
			throws LIMException {
		try {
			BaseRelation relation = (BaseRelation) RelationType
					.getRelationType(relationship.getType()).relationClazz.newInstance();
			this.loadProperty(relationship, relation, initTypeFlag, true);
			return relation;
		} catch (InstantiationException | IllegalAccessException e) {
			throw LINException.getLINException(e);
		}
	}

	public void loadProperty(final PropertyContainer propertyContainer, BaseRelation element, 
			final boolean initTypeFlag, final boolean superPropertyFlag) throws LIMException {
		if (superPropertyFlag) {
			super.loadProperty(propertyContainer, element, initTypeFlag, superPropertyFlag);
		}

		Set<String> apks = propertyContainer.getAllProperties().keySet();

		if (apks.contains(PROPERTY_KEY_PRIMARY_ELEMENT)) {
			Object pe = propertyContainer.getProperty(PROPERTY_KEY_PRIMARY_ELEMENT);
			if (pe != null) {
				element.setBasePrimaryElement(BaseElementFactory.convertToElement((String) pe));
			}
		}

		if (apks.contains(PROPERTY_KEY_SECONDARY_ELEMENT)) {
			Object se = propertyContainer.getProperty(PROPERTY_KEY_SECONDARY_ELEMENT);
			if (se != null) {
				element.setBaseSecondaryElement(BaseElementFactory.convertToElement((String) se));
			}
		}
	}



	/* inner classes */

	@SuppressWarnings("unchecked")
	static class RelationType implements RelationshipType {

		/* variables */

		private Class<? extends BaseRelation> relationClazz;


		/* variables: arrays, collections, maps, groups */

		private static final Map<String, RelationType> ELEMENT_TYPE_NAME_MAP;
		private static final Map<String, RelationType> RELATION_TYPE_NAME_MAP;


		/* constructors */

		private RelationType(Class<? extends BaseRelation> relationClazz) {
			this.relationClazz = relationClazz;
		}


		/* initializers */

		static {
			try {
				ELEMENT_TYPE_NAME_MAP = CollectionUtil.generateNewMap();
				RELATION_TYPE_NAME_MAP = CollectionUtil.generateNewMap();

				Collection<Class<? extends BaseElement>> subRelationClazzCollection 
						= BaseElement.getSubPojoClassCollection(BaseRelation.class);
				for (Class<? extends BaseElement> src : subRelationClazzCollection) {
					if (!BaseRelation.class.isAssignableFrom(src)) {
						continue;
					}
					RelationType rt = new RelationType((Class<? extends BaseRelation>) src);
					ELEMENT_TYPE_NAME_MAP.put(BaseElement.generateTypeFromClass(src), rt);
					RELATION_TYPE_NAME_MAP.put(rt.name(), rt);
					FactoryConstant.registerRelationFactoryForInit(rt, BaseRelationFactory.class);
				}
			} catch (LIMException e) {
				throw new RuntimeException(e);
			}
		}


		/* overridden methods */

		@Override
		public String name() {
			return BaseElement.generateConstantFromClass(this.relationClazz);
		}


		/* static methods */

		static final RelationType getRelationType(final String elementType) {
			return ELEMENT_TYPE_NAME_MAP.get(elementType);
		}

		static final RelationType getRelationType(final RelationshipType type) {
			return RELATION_TYPE_NAME_MAP.get(type.name());
		}

	}

}
