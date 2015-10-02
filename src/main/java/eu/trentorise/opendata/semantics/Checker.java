/* 
 * Copyright 2015 TrentoRISE   (trentorise.eu).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.trentorise.opendata.semantics;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import eu.trentorise.opendata.commons.validation.Preconditions;
import static eu.trentorise.opendata.commons.validation.Preconditions.checkNotDirtyUrl;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import eu.trentorise.opendata.semantics.model.entity.AStruct;
import eu.trentorise.opendata.semantics.model.entity.Attr;
import eu.trentorise.opendata.semantics.model.entity.AttrDef;
import eu.trentorise.opendata.semantics.model.entity.AttrType;
import eu.trentorise.opendata.semantics.model.entity.Entity;
import eu.trentorise.opendata.semantics.model.entity.Etype;
import eu.trentorise.opendata.semantics.model.entity.Struct;
import eu.trentorise.opendata.semantics.model.entity.Val;
import eu.trentorise.opendata.semantics.services.IEkb;
import eu.trentorise.opendata.semantics.services.IEntityService;
import eu.trentorise.opendata.semantics.services.IEtypeService;
import eu.trentorise.opendata.semantics.services.SchemaMapping;
import eu.trentorise.opendata.semantics.services.AssignmentResult;
import eu.trentorise.opendata.semantics.services.AttrMapping;
import eu.trentorise.opendata.semantics.services.IdResult;
import eu.trentorise.opendata.semantics.services.Mappings;
import eu.trentorise.opendata.traceprov.types.Concept;

/**
 * Class to collect checkers for implementations of Open Entity API interfaces.
 *
 * @author David Leoni
 */
public final class Checker {

    private IEkb ekb;

    private Checker(IEkb ekb) {
	checkNotNull(ekb);
	this.ekb = ekb;
    }

    public static Checker of(IEkb ekb) {
	return new Checker(ekb);
    }

    private void checkScore(Float score, String prependedErrorMessage) {
	checkNotNull(score, "%s Found null score!");
	Preconditions.checkScore(score, prependedErrorMessage);
    }

    /**
     * Checks if provided schema correspondence complies with open entity specs.
     *
     * @param schemaMapping
     *            to check
     * @throws IllegalArgumentException
     *             if provided correspondence is not conformant to OpenEntity
     *             specs.
     */
    public void checkSchemaMapping(SchemaMapping schemaMapping) {
	if (schemaMapping == null) {
	    throw new IllegalArgumentException("Schema mapping is null!");
	}

	try {
	    checkEtype(schemaMapping.getTargetEtype());
	} catch (Exception ex) {
	    throw new IllegalArgumentException("Invalid etype in schema mapping!", ex);
	}

	for (AttrMapping attrMapping : schemaMapping.getMappings()) {
	    checkAttrMapping(attrMapping);
	}

    }

    /**
     *
     * Checks if provided path is valid
     *
     * @param prependedErrorMessage
     *            the exception message to use if the check fails; will be
     *            converted to a string using String.valueOf(Object) and
     *            prepended to more specific error messages.
     *
     * @throws IllegalArgumentException
     *             if provided path fails validation
     *
     * @return a valid immutable path
     */
    public static ImmutableList<String> checkSourcePath(Iterable<String> path, @Nullable Object prependedErrorMessage) {
	Iterator<String> iter = path.iterator();
	if (iter.hasNext()) {
	    String selector = iter.next();
	    switch (selector) {

	    case Mappings.SCHEMA_SOURCE: {
		return Mappings.schemaSourcePath(Iterables.skip(path, 1));
	    }

	    case Mappings.DCAT_METADATA_SOURCE: {
		return Mappings.dcatPath(Iterables.skip(path, 1));
	    }

	    case Mappings.CUSTOM_SOURCE: {
		return Mappings.customPath(Iterables.skip(path, 1));
	    }
	    default: {
		throw new IllegalArgumentException(String.valueOf(prependedErrorMessage)
			+ " -- Reason: Input path should have first element starting with one of "
			+ Mappings.ALLOWED_SOURCES);
	    }
	    }

	} else {
	    return ImmutableList.of();
	}

    }

    private void checkAttrMapping(AttrMapping attrMapping) {

	checkSourcePath(attrMapping.getSourcePath(), "Invalid attribute mapping!");
	// target path can be empty
    }

    /**
     * Checks if provided entity type complies with open entity specs.
     *
     * @param etype
     *            the entity type to check
     * @throws IllegalArgumentException
     *             if provided etype is not conformant to OpenEntity specs.
     */
    public void checkEtype(Etype etype) {
	if (etype == null) {
	    throw new IllegalArgumentException("Found null etype!");
	}
	checkNotDirtyUrl(etype.getId(), "Invalid etype URL!");

	checkNotNull(etype.getName(), "Invalid etype name!");

	checkNotDirtyUrl(etype.getConceptId(), "Found invalid concept for etype " + etype.getId());

	/**
	 * not supported for now if (etype.getUniqueIndexes() == null){ throw
	 * new IllegalArgumentException("Found null unique indexes for etype " +
	 * etype.getURL()); }
	 */
	for (AttrDef attrDef : etype.getAttrDefs().values()) {
	    try {
		checkAttrDef(attrDef);
	    } catch (Exception ex) {
		throw new IllegalArgumentException("Found invalid attr def in etype " + etype.getId(), ex);
	    }
	}

	if (!etype.isStruct()) {
	    try {
		etype.nameAttrDef();
	    } catch (Exception ex) {
		throw new IllegalArgumentException(
			"Found problem in getNameAttrDef() for etype with URL " + etype.getId(), ex);
	    }

	    try {
		etype.descrAttrDef();
	    } catch (Exception ex) {
		throw new IllegalArgumentException(
			"Found problem in getNameAttrDef() for etype with URL " + etype.getId(), ex);
	    }
	}

    }

    /**
     * Checks if provided attribute def complies with open entity specs.
     *
     * @param attrDef
     *            the attribute definition to check
     * @throws IllegalArgumentException
     *             if provided attrDef is not conformant to OpenEntity specs.
     */
    public void checkAttrDef(AttrDef attrDef) {
	if (attrDef == null) {
	    throw new IllegalArgumentException("Found null attribute def!");
	}
	if (attrDef.getId() == null) {
	    throw new IllegalArgumentException("Found null URL for attribute def " + attrDef.getName());
	}
	if (attrDef.getId().length() == 0) {
	    throw new IllegalArgumentException("Found empty URL for attribute def " + attrDef.getName());
	}

	if (attrDef.getName() == null) {
	    throw new IllegalArgumentException("Found null name dict for attribute def " + attrDef.getId());
	}

	if (attrDef.getType().getDatatype() == null) {
	    throw new IllegalArgumentException("Found null datatype for attribute def " + attrDef.getId());
	}
	if ((attrDef.getType().getDatatype().equals(DataTypes.STRUCTURE)
		|| attrDef.getType().getDatatype().equals(DataTypes.ENTITY))) {

	    checkNotDirtyUrl(attrDef.getType().etypeId(), "Attr def " + attrDef.getId() + " is of datatype "
		    + attrDef.getType().getDatatype() + ", but is has invalid range Etype URL()");

	}

	try {
	    checkNotNull(attrDef.getConceptId());
	} catch (Exception ex) {
	    throw new IllegalArgumentException("Found invalid concept for attr def " + attrDef.getId(), ex);
	}

    }

    /**
     * Checks if provided ekb complies with open entity specs.
     *
     * @param ekb
     *            the entity knowledge base to check
     * @throws IllegalArgumentException
     *             if provided ekb is not conformant to OpenEntity specs.
     */
    public void checkEkbQuick(IEkb ekb) {

	if (ekb == null) {
	    throw new IllegalArgumentException("Found null ekb!");
	}

	if (ekb.getDefaultLocales() == null) {
	    throw new IllegalArgumentException("Found null locales list in ekb " + ekb.getClass().getCanonicalName());
	}

	if (ekb.getDefaultLocales().get(0) == null) {
	    throw new IllegalArgumentException("Found null first locale in ekb " + ekb.getClass().getCanonicalName());
	}
    }

    public void checkIDResult(IdResult idResult) {

	if (idResult == null) {
	    throw new IllegalArgumentException("Found null idResult!");
	}


	for (Entity entity : idResult.getEntities()) {
	    try {		
		if (ekb.getEntityService().isTemporaryURL(entity.getId())){
		    checkEntity(entity, true);    
		} else {
		    checkEntity(entity, false);
		}
		
	    } catch (Exception ex) {
		throw new IllegalArgumentException(
			"Failed integrity check on entity " + entity + " in idResult " + idResult, ex);
	    }
	}

	if (AssignmentResult.REUSE.equals(idResult.getAssignmentResult())) {

	    checkEntity(idResult.getResultEntity());

	    if (idResult.getEntities().isEmpty()) {
		throw new IllegalArgumentException(
			"Found empty entities in idResult with "+idResult.getAssignmentResult()+". idResult is " + idResult);
	    }
	}

	if (AssignmentResult.NEW.equals(idResult.getAssignmentResult())) {

	    checkEntity(idResult.getResultEntity(), true);

	    if (idResult.getEntities().isEmpty()) {
		throw new IllegalArgumentException(
			"Found empty entities in idResult with "+idResult.getAssignmentResult()+". idResult is " + idResult);
	    }	    
	    
	}

    }

    /**
     * Checks if provided entity complies with open entity specs.
     *
     * @param entity
     *            to check
     * @throws IllegalArgumentException
     *             if provided entity is not conformant to OpenEntity specs.
     */
    public void checkEntity(Entity entity) {

	checkEntity(entity, false);
    }

    /**
     * Checks if provided entity complies with open entity specs. For synthetic
     * entities some checks will be skipped.
     *
     * @param entity
     *            to check
     * @param synthetic
     *            if true URL and local ids of attributes and values will not be
     *            checked
     * @throws IllegalArgumentException
     *             if provided entity is not conformant to OpenEntity specs.
     */
    public void checkEntity(Entity entity, boolean synthetic) {

	if (entity == null) {
	    throw new IllegalArgumentException("Found null entity!");
	}

	Etype etype = ekb.getEtypeService().readEtype(entity.getEtypeId());

	if (etype == null) {
	    throw new IllegalArgumentException("Found null etype!");
	}

	try {
	    checkStruct(entity, synthetic);
	} catch (Exception ex) {
	    throw new IllegalArgumentException("Found invalid structural properties of entity " + entity.getId(), ex);
	}

	if (!synthetic) {
	    checkNotDirtyUrl(entity.getId(), "Found invalid URL in entity " + entity);
	}

	try {
	    checkNotNull(entity.getName());
	} catch (Exception ex) {
	    throw new IllegalArgumentException("Found invalid name in entity " + entity.getId(), ex);
	}

	try {
	    checkNotNull(entity.getDescription());
	} catch (Exception ex) {
	    throw new IllegalArgumentException("Found invalid description in entity " + entity.getId(), ex);
	}

    }

    /**
     * Checks if provided struct complies with open entity specs.
     *
     * @param struct
     *            to check
     * @throws IllegalArgumentException
     *             if provided struct is not conformant to OpenEntity specs.
     */
    public void checkStruct(Struct struct) {

	checkStruct(struct, false);

    }

    /**
     * Checks if provided struct complies with open entity specs. For synthetic
     * structs some checks will be skipped.
     *
     * @param struct
     *            the struct to check
     * @param synthetic
     *            if true URLs and local ids of attributes and values will not
     *            be checked. The URL of the struct is not checked anyway.
     */
    public void checkStruct(AStruct struct, boolean synthetic) {
	if (struct == null) {
	    throw new IllegalArgumentException("Found null struct!");
	}

	Etype etype = ekb.getEtypeService().readEtype(struct.getEtypeId());

	if (etype == null) {
	    throw new IllegalArgumentException("Found null etype!");
	}

	checkNotDirtyUrl(struct.getEtypeId(), "Found invalid entity type URL in struct " + struct.getId());

	checkArgument(etype.getId().equals(struct.getEtypeId()), "Provided etype " + etype.getId()
		+ " is not the one referenced by the struct, which is " + struct.getEtypeId());

	if (struct.getAttrs() == null) {
	    throw new IllegalArgumentException("Found null attributes in struct " + struct.getId());
	}

	for (String attrDefId : struct.getAttrs().keySet()) {
	    try {
		checkAttr(struct.attr(attrDefId), synthetic, etype.attrDefById(attrDefId));
	    } catch (Exception ex) {
		throw new IllegalArgumentException("Found invalid attribute in struct " + struct.getId(), ex);
	    }
	}
    }

    /**
     * Checks if provided attribute complies with open entity specs.
     *
     * @param attr
     *            to check
     * @param synthetic
     *            if true URL and local ids of attributes and values will not be
     *            checked
     * @throws IllegalArgumentException
     *             if provided attribute is not conformant to OpenEntity specs.
     */
    public void checkAttr(Attr attr, boolean synthetic, AttrDef attrDef) {
	checkNotNull(attr);
	checkNotNull(attrDef);
	checkArgument(attr.getAttrDefId().equals(attrDef.getId()),
		"Provided attribute def id %s is not the same as attrDefId %s in provided attr %s", attrDef.getId(),
		attr.getAttrDefId(), attr);

	if (!synthetic && attr.getLocalId() < 0) {
	    throw new IllegalArgumentException("Found negative local ID in attribute " + attr);
	}

	for (Val val : attr.getValues()) {
	    try {
		checkValue(val, attrDef, synthetic);
	    } catch (Exception ex) {
		throw new IllegalArgumentException("Found invalid value in attribute " + attr.getLocalId(), ex);
	    }
	}

    }

    /**
     * Checks if provided attribute complies with open entity specs.
     *
     * @param attribute
     *            to check
     * @throws IllegalArgumentException
     *             if provided attribute is not conformant to OpenEntity specs.
     */
    public void checkAttr(Attr attribute, AttrDef attrDef) {
	checkAttr(attribute, false, attrDef);

    }

    /**
     * Checks if provided value complies with open entity specs.
     *
     * @param value
     *            to check
     * @throws IllegalArgumentException
     *             if provided value is not conformant to OpenEntity specs.
     */
    public void checkValue(Val value, AttrDef attrDef) {
	checkValue(value, attrDef, false);
    }

    /**
     * 
     * @param obj
     *            an object that can be stored inside a value. NOTE: in case it
     *            is a structure or entity, subvalues are *NOT* checked.
     */
    public static void checkObj(Object obj, AttrType type, boolean synthetic) {

	if (obj == null) {
	    throw new IllegalArgumentException("Found null object!");
	}
	if (type == null) {
	    throw new IllegalArgumentException("Found null type!");
	}

	if (DataTypes.getDataTypes().get(type.getDatatype()) == null) {
	    throw new IllegalArgumentException("Found unsupported datatype " + type.getDatatype() + " in value " + obj
		    + ". Its class is " + obj.getClass().getName());
	}

	if (!(DataTypes.getDataTypes().get(type.getDatatype()).isInstance(obj))) {

	    if ((type.getDatatype().equals(DataTypes.STRUCTURE) || type.getDatatype().equals(DataTypes.ENTITY))
		    && obj instanceof String) {
		checkNotDirtyUrl((String) obj,
			"Found invalid " + type.getDatatype() + " URL for referenced structure!!");
	    } else {
		throw new IllegalArgumentException("Found value not corresponding to its datatype " + type.getDatatype()
			+ ". Value is " + obj + ". Its class is " + obj.getClass().getName());
	    }
	}

    }

    /**
     * Checks if provided value complies with open entity specs.
     *
     * @param value
     *            to check
     * @throws IllegalArgumentException
     *             if provided value is not conformant to OpenEntity specs.
     */
    public void checkValue(Val value, AttrDef attrDef, boolean synthetic) {

	checkNotNull(attrDef);

	String datatype = attrDef.getType().getDatatype();

	if (value == null) {
	    throw new IllegalArgumentException("Found null value!");
	}

	if (!synthetic && value.getLocalId() < 0) {
	    throw new IllegalArgumentException("Found negative local ID in value " + value);
	}

	AttrType type;
	if (attrDef.getType().isList()) {
	    type = AttrType.builder().from(attrDef.getType()).setMandatory(false).setList(false).build();
	} else {
	    type = attrDef.getType();
	}
	Object obj = value.getObj();
	checkObj(obj, attrDef.getType(), synthetic);

	if (!(obj instanceof String)) {
	    if (DataTypes.STRUCTURE.equals(datatype)) {

		Struct s = (Struct) obj;
		/*
		 * todo we can't verify inheritance of etypes! if
		 * (!attrDef.getType().getEtypeId().equals(s.getEtypeId())) {
		 * throw new IllegalArgumentException(
		 * "Found struct value with value ID " + value.getLocalId() +
		 * " having etype URL different from its attribute rangeEtypeURL! "
		 * + "\nStruct etype URL: " + s.getEtypeId() +
		 * "\nAttr rangeEtypeURL: " + attrDef.getType().getEtypeId()); }
		 */
		checkStruct(s, synthetic);

	    }

	    if (DataTypes.ENTITY.equals(datatype)) {
		Entity entity = (Entity) obj;
		/*
		 * todo we can't verify inheritance of etypes! if
		 * (!attrDef.getType().getEtypeId().equals(entity.getEtypeId()))
		 * { throw new IllegalArgumentException(
		 * "Found entity value with value ID " + value.getLocalId() +
		 * " having etype URL different from its attribute rangeEtypeURL! "
		 * + "\nStruct etype URL: " + entity.getEtypeId() +
		 * "\nAttr rangeEtypeURL: " + attrDef.getType().getEtypeId()); }
		 */
		checkEntity(entity, synthetic);
	    }

	}

    }

    /**
     * Checks if provided concept complies with open entity specs.
     *
     * @param concept
     *            to check
     * @throws IllegalArgumentException
     *             if provided concept is not conformant to OpenEntity specs.
     */
    public void checkConcept(Concept concept) {
	if (concept == null) {
	    throw new IllegalArgumentException("Found null concept!");
	}

	checkNotDirtyUrl(concept.getId(), "Found invalid URL in concept " + concept);

	try {
	    checkNotNull(concept.getDescription());
	} catch (Exception ex) {
	    throw new IllegalArgumentException("Found invalid description in concept " + concept.getId(), ex);
	}

	try {
	    checkNotNull(concept.getName());
	} catch (Exception ex) {
	    throw new IllegalArgumentException("Found invalid name in concept " + concept.getId(), ex);
	}

    }

}
