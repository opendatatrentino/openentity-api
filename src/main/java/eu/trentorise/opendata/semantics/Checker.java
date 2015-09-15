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
import static eu.trentorise.opendata.commons.OdtUtils.checkNotDirtyUrl;
import eu.trentorise.opendata.semantics.model.entity.AStruct;
import eu.trentorise.opendata.semantics.model.entity.Attr;
import eu.trentorise.opendata.semantics.model.entity.AttrDef;
import eu.trentorise.opendata.semantics.model.entity.Entity;
import eu.trentorise.opendata.semantics.model.entity.Etype;
import eu.trentorise.opendata.semantics.model.entity.Struct;
import eu.trentorise.opendata.semantics.model.entity.Val;
import eu.trentorise.opendata.semantics.services.IEkb;
import eu.trentorise.opendata.semantics.services.SchemaMapping;
import eu.trentorise.opendata.semantics.services.AssignmentResult;
import eu.trentorise.opendata.semantics.services.IdResult;
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

    public static Checker of(IEkb ekb){
        return new Checker(ekb);
    }
    
    private void checkScore(Float score, String prependedErrorMessage) {
        checkNotNull(score, "%s Found null score!");
        Preconditions.checkScore(score, prependedErrorMessage);
    }

    /**
     * Checks if provided schema correspondence complies with open entity specs.
     *
     * @param schemaMapping to check
     * @throws IllegalArgumentException if provided correspondence is not
     * conformant to OpenEntity specs.
     */
    public void checkSchemaMapping(SchemaMapping schemaMapping) {
        if (schemaMapping == null) {
            throw new IllegalArgumentException("Schema mapping is null!");
        }

        try {
            checkEtype(schemaMapping.getTargetEtype());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Invalid etype in schema mapping!", ex);
        }
    }

    /**
     * Checks if provided entity type complies with open entity specs.
     *
     * @param etype the entity type to check
     * @throws IllegalArgumentException if provided etype is not conformant to
     * OpenEntity specs.
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
            }
            catch (Exception ex) {
                throw new IllegalArgumentException("Found invalid attr def in etype " + etype.getId(), ex);
            }
        }

        try {
            etype.nameAttrDef();
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found problem in getNameAttrDef() for etype with URL " + etype.getId(), ex);
        }

        try {
            etype.descrAttrDef();
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found problem in getNameAttrDef() for etype with URL " + etype.getId(), ex);
        }

    }

    /**
     * Checks if provided attribute def complies with open entity specs.
     *
     * @param attrDef the attribute definition to check
     * @throws IllegalArgumentException if provided attrDef is not conformant to
     * OpenEntity specs.
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
        if ((attrDef.getType().getDatatype().equals(DataTypes.STRUCTURE) || attrDef.getType().getDatatype().equals(DataTypes.ENTITY))) {

            checkNotDirtyUrl(attrDef.getType().etypeId(), "Attr def " + attrDef.getId() + " is of datatype " + attrDef.getType().getDatatype() + ", but is has invalid range Etype URL()");

        }

        try {
            checkNotNull(attrDef.getConceptId());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found invalid concept for attr def " + attrDef.getId(), ex);
        }

    }

    /**
     * Checks if provided ekb complies with open entity specs.
     *
     * @param ekb the entity knowledge base to check
     * @throws IllegalArgumentException if provided ekb is not conformant to
     * OpenEntity specs.
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

        if (idResult.getAssignmentResult() == null) {
            throw new IllegalArgumentException("Found null assignment result in idResult: " + idResult);
        }

        if (idResult.getEntities() == null) {
            throw new IllegalArgumentException("Found null result entities!  idResult is " + idResult);
        }

        if (AssignmentResult.REUSE.equals(idResult.getAssignmentResult())
                || AssignmentResult.NEW.equals(idResult.getAssignmentResult())) {

            checkNotDirtyUrl(idResult.getId(), "Found invalid URL in idResult! AssignmentResult is " + idResult.getAssignmentResult() + " in idResult " + idResult);

            for (Entity entity : idResult.getEntities()) {
                try {                    
                    checkEntity(entity);
                }
                catch (Exception ex) {
                    throw new IllegalArgumentException("Failed integrity check on entity " + entity + " in idResult " + idResult, ex);
                }
            }

        }

        if (AssignmentResult.REUSE.equals(idResult.getAssignmentResult())) {

            checkEntity(idResult.getResultEntity());

            if (idResult.getEntities().isEmpty()) {
                throw new IllegalArgumentException("Found empty entities in idResult with REUSE. idResult is " + idResult);
            }
        }

        if (AssignmentResult.NEW.equals(idResult.getAssignmentResult())) {

            checkEntity(idResult.getResultEntity(), true);

            if (!idResult.getEntities().isEmpty()) {
                throw new IllegalArgumentException("Found non-empty entities in idResult with NEW. idResult is " + idResult);
            }
        }

    }

    /**
     * Checks if provided entity complies with open entity specs.
     *
     * @param entity to check
     * @throws IllegalArgumentException if provided entity is not conformant to
     * OpenEntity specs.
     */
    public void checkEntity(Entity entity) {

        checkEntity(entity, false);
    }

    /**
     * Checks if provided entity complies with open entity specs. For synthetic
     * entities some checks will be skipped.
     *
     * @param entity to check
     * @param synthetic if true URL and local ids of attributes and values will
     * not be checked
     * @throws IllegalArgumentException if provided entity is not conformant to
     * OpenEntity specs.
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
            checkStruct(entity,  synthetic);
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found invalid structural properties of entity " + entity.getId(), ex);
        }

        if (!synthetic) {
            checkNotDirtyUrl(entity.getId(), "Found invalid URL in entity " + entity);
        }

        try {
            checkNotNull(entity.getName());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found invalid name in entity " + entity.getId(), ex);
        }

        try {
            checkNotNull(entity.getDescription());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found invalid description in entity " + entity.getId(), ex);
        }

    }

    /**
     * Checks if provided struct complies with open entity specs.
     *
     * @param struct to check
     * @throws IllegalArgumentException if provided struct is not conformant
     * to OpenEntity specs.
     */
    public void checkStruct(Struct struct) {

        checkStruct(struct,  false);

    }

    /**
     * Checks if provided struct complies with open entity specs. For
     * synthetic structs some checks will be skipped.
     *
     * @param struct the struct to check
     * @param synthetic if true URLs and local ids of attributes and values will
     * not be checked. The URL of the struct is not checked anyway.
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

        checkArgument(etype.getId().equals(struct.getEtypeId()), "Provided etype " + etype.getId() + " is not the one referenced by the struct, which is " + struct.getEtypeId());

        if (struct.getAttrs() == null) {
            throw new IllegalArgumentException("Found null attributes in struct " + struct.getId());
        }

        for (Attr attr : struct.getAttrs().values()) {
            try {
                checkAttr(attr,  synthetic);
            }
            catch (Exception ex) {
                throw new IllegalArgumentException("Found invalid attribute in struct " + struct.getId(), ex);
            }
        }
    }

    /**
     * Checks if provided attribute complies with open entity specs.
     *
     * @param attribute to check
     * @param synthetic if true URL and local ids of attributes and values will
     * not be checked
     * @throws IllegalArgumentException if provided attribute is not conformant
     * to OpenEntity specs.
     */
    public void checkAttr(Attr attribute, boolean synthetic) {
        if (attribute == null) {
            throw new IllegalArgumentException("Found null attribute!");
        }

        AttrDef attrDef = ekb.getEtypeService().readAttrDef(attribute.getAttrDefId());

        if (attrDef == null) {
            throw new IllegalArgumentException("Found null attr def!");
        }

        if (!synthetic && attribute.getLocalID() < 0) {
            throw new IllegalArgumentException("Found negative local ID in attribute " + attribute);
        }

        if (attribute.getAttrDefId() == null) {
            throw new IllegalArgumentException("Found null attribute definition in attribute " + attribute.getLocalID());
        }

        if (attribute.getValues() == null) {
            throw new IllegalArgumentException("Found null values list in attribute " + attribute.getLocalID());
        }


        for (Val val : attribute.getValues()) {
            try {
                checkValue(val, attrDef,  synthetic);
            }
            catch (Exception ex) {
                throw new IllegalArgumentException("Found invalid value in attribute " + attribute.getLocalID(), ex);
            }
        }

    }

    /**
     * Checks if provided attribute complies with open entity specs.
     *
     * @param attribute to check
     * @throws IllegalArgumentException if provided attribute is not conformant
     * to OpenEntity specs.
     */
    public void checkAttr(Attr attribute) {
        checkAttr(attribute, false);

    }

    /**
     * Checks if provided value complies with open entity specs.
     *
     * @param value to check
     * @throws IllegalArgumentException if provided value is not conformant to
     * OpenEntity specs.
     */
    public void checkValue(Val value, AttrDef attrDef) {
        checkValue(value, attrDef, false);
    }

    /**
     * Checks if provided value complies with open entity specs.
     *
     * @param value to check
     * @throws IllegalArgumentException if provided value is not conformant to
     * OpenEntity specs.
     */
    public void checkValue(Val value, AttrDef attrDef, boolean synthetic) {

        checkNotNull(attrDef);
        
        String datatype = attrDef.getType().getDatatype();

        if (value == null) {
            throw new IllegalArgumentException("Found null value!");
        }

        if (!synthetic && value.getLocalID() < 0 ) {
            throw new IllegalArgumentException("Found negative local ID in value " + value);
        }

        if (value.getObj() == null) {
            throw new IllegalArgumentException("Found null object in value " + value.getLocalID());
        }
//here
        if (DataTypes.getDataTypes().get(datatype) == null) {
            throw new IllegalArgumentException("Found unsupported datatype " + datatype + " in value " + value.getObj() + ". Its class is " + value.getObj().getClass().getName());
        }

        if (!(DataTypes.getDataTypes().get(datatype).isInstance(value.getObj()))) {
            throw new IllegalArgumentException("Found value not corresponding to its datatype " + datatype + ". Value is " + value.getObj() + ". Its class is " + value.getObj().getClass().getName());
        }

        if (DataTypes.STRUCTURE.equals(datatype)) { // for structs we do deep check
            Struct s = (Struct) value.getObj();
            if (!attrDef.getType().getEtypeId().equals(s.getEtypeId())) {
                throw new IllegalArgumentException("Found struct value with value ID " + value.getLocalID() + " having etype URL different from its attribute rangeEtypeURL! "
                        + "\nStruct etype URL: " + s.getEtypeId() + "\nAttr rangeEtypeURL: " + attrDef.getType().getEtypeId());
            }
            checkStruct(s, synthetic);
        }

        if (DataTypes.ENTITY.equals(datatype)) { // for entities we only check URL and name            
            Entity entity = (Entity) value.getObj();
            if (!synthetic) {

                checkNotDirtyUrl(entity.getId(), "Found invalid URL in entity inside value with local ID: " + value.getLocalID());

            }

            checkNotNull(entity.getName(), "Found invalid name in entity with URL " + entity.getId() + " inside value with local ID: " + value.getLocalID());

        }

    }

    /**
     * Checks if provided concept complies with open entity specs.
     *
     * @param concept to check
     * @throws IllegalArgumentException if provided concept is not conformant to
     * OpenEntity specs.
     */
    public void checkConcept(Concept concept) {
        if (concept == null) {
            throw new IllegalArgumentException("Found null concept!");
        }

        checkNotDirtyUrl(concept.getId(), "Found invalid URL in concept " + concept);

        try {
            checkNotNull(concept.getDescription());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found invalid description in concept " + concept.getId(), ex);
        }

        try {
            checkNotNull(concept.getName());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found invalid name in concept " + concept.getId(), ex);
        }

    }

}
