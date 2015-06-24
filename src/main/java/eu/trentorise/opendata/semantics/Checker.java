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

import static com.google.common.base.Preconditions.checkNotNull;
import static eu.trentorise.opendata.commons.OdtUtils.checkNotDirtyUrl;
import eu.trentorise.opendata.semantics.model.entity.IAttribute;
import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.entity.IStructure;
import eu.trentorise.opendata.semantics.model.entity.IValue;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semtext.SemTexts;
import eu.trentorise.opendata.semantics.services.IEkb;
import eu.trentorise.opendata.semantics.services.model.AssignmentResult;
import eu.trentorise.opendata.semantics.services.model.IAttributeCorrespondence;
import eu.trentorise.opendata.semantics.services.model.IIDResult;
import eu.trentorise.opendata.semantics.services.model.ISchemaCorrespondence;

/**
 * Class to collect checkers for implementations of Open Entity API interfaces.
 *
 * @author David Leoni
 */
public final class Checker {

    private Checker() {
    }

    private static void checkScore(Float score, String prependedErrorMessage) {
        checkNotNull(score, "Found null score!");
        SemTexts.checkScore(score.doubleValue(), prependedErrorMessage);

    }

    /**
     * Checks if provided schema correspondence complies with open entity specs.
     *
     * @param schemaCor to check
     * @throws IllegalArgumentException if provided correspondence is not
     * conformant to OpenEntity specs.
     */
    public static void checkSchemaCorrespondence(ISchemaCorrespondence schemaCor) {
        if (schemaCor == null) {
            throw new IllegalArgumentException("Schema correspondence is null!");
        }

        try {
            checkEntityType(schemaCor.getEtype());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Invalid etype in schema correspondence!", ex);
        }

        if (schemaCor.getAttributeCorrespondences() == null) {
            throw new IllegalArgumentException("Schema Correrespondence has null correspondences!");
        }

        for (IAttributeCorrespondence attrCor : schemaCor.getAttributeCorrespondences()) {
            checkNotDirtyUrl(attrCor.getHeaderConceptURL(), "header concept URL is invalid!");
            try {

                checkScore(attrCor.getScore(), "attribute correspondence score is invalid!");
            }
            catch (Exception ex) {

                throw new IllegalArgumentException("Invalid score for attribute correspondence " + attrCor.getScore() + " Found score: " + attrCor.getScore(), ex);
            }

            if (attrCor.getColumnIndex() < 0) {
                throw new IllegalArgumentException("Attribute correspondence column index is lesss than zero. Found index: " + attrCor.getColumnIndex());
            }

            checkAttributeDef(attrCor.getAttrDef());

            if (attrCor.getAttrMap() == null) {
                throw new IllegalArgumentException("Found null attr map in IAttributeCorrespondence for etype " + schemaCor.getEtype().getURL());
            }

            for (IAttributeDef ad : attrCor.getAttrMap().keySet()) {
                try {
                    checkScore(attrCor.getAttrMap().get(ad), "Found invalid score in attribute correspondance map!");
                }
                catch (Exception ex) {
                    throw new IllegalArgumentException("Found invalid score in attribute correspondence map for attribute " + ad.getURL(), ex);
                }
            }

        }
    }

    /**
     * Checks if provided entity type complies with open entity specs.
     *
     * @param etype the entity type to check
     * @throws IllegalArgumentException if provided etype is not conformant to
     * OpenEntity specs.
     */
    public static void checkEntityType(IEntityType etype) {
        if (etype == null) {
            throw new IllegalArgumentException("Found null etype!");
        }
        checkNotDirtyUrl(etype.getURL(), "Invalid etype URL!");

        checkNotNull(etype.getName(), "Invalid etype name!");

        checkNotDirtyUrl(etype.getConceptURL(), "Found invalid concept for etype " + etype.getURL());

        /**
         * not supported for now if (etype.getUniqueIndexes() == null){ throw
         * new IllegalArgumentException("Found null unique indexes for etype " +
         * etype.getURL()); }
         */
        for (IAttributeDef attrDef : etype.getAttributeDefs()) {
            try {
                checkAttributeDef(attrDef);
            }
            catch (Exception ex) {
                throw new IllegalArgumentException("Found invalid attr def in etype " + etype.getURL(), ex);
            }
        }

        try {
            etype.getNameAttrDef();
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found problem in getNameAttrDef() for etype with URL " + etype.getURL(), ex);
        }

        try {
            etype.getDescriptionAttrDef();
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found problem in getNameAttrDef() for etype with URL " + etype.getURL(), ex);
        }

    }

    /**
     * Checks if provided attribute def complies with open entity specs.
     *
     * @param attrDef the attribute definition to check
     * @throws IllegalArgumentException if provided attrDef is not conformant to
     * OpenEntity specs.
     */
    public static void checkAttributeDef(IAttributeDef attrDef) {
        if (attrDef == null) {
            throw new IllegalArgumentException("Found null attribute def!");
        }
        if (attrDef.getURL() == null) {
            throw new IllegalArgumentException("Found null URL for attribute def " + attrDef.getName());
        }
        if (attrDef.getURL().length() == 0) {
            throw new IllegalArgumentException("Found empty URL for attribute def " + attrDef.getName());
        }

        if (attrDef.getName() == null) {
            throw new IllegalArgumentException("Found null name dict for attribute def " + attrDef.getURL());
        }

        checkNotDirtyUrl(attrDef.getEtypeURL(), "Found invalid etype URL for attribute def " + attrDef.getURL());

        if (attrDef.getDataType() == null) {
            throw new IllegalArgumentException("Found null datatype for attribute def " + attrDef.getURL());
        }
        if ((attrDef.getDataType().equals(DataTypes.STRUCTURE) || attrDef.getDataType().equals(DataTypes.ENTITY))) {

            checkNotDirtyUrl(attrDef.getRangeEtypeURL(), "Attribute def " + attrDef.getURL() + " with parent etype " + attrDef.getEtypeURL() + " is of datatype " + attrDef.getDataType() + ", but is has invalid getRangeEtypeURL()");

        }

        try {
            checkConcept(attrDef.getConcept());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found invalid concept for attr def " + attrDef.getURL(), ex);
        }

    }

    /**
     * Checks if provided ekb complies with open entity specs.
     *
     * @param ekb the entity knowledge base to check
     * @throws IllegalArgumentException if provided ekb is not conformant to
     * OpenEntity specs.
     */
    public static void checkEkbQuick(IEkb ekb) {

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

    public static void checkIDResult(IIDResult idResult) {

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

            checkNotDirtyUrl(idResult.getURL(), "Found invalid URL in idResult! AssignmentResult is " + idResult.getAssignmentResult() + " in idResult " + idResult);

            for (IEntity entity : idResult.getEntities()) {
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
    public static void checkEntity(IEntity entity) {

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
    public static void checkEntity(IEntity entity, boolean synthetic) {

        if (entity == null) {
            throw new IllegalArgumentException("Found null entity!");
        }

        try {
            checkStructure(entity, synthetic);
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found invalid structural properties of entity " + entity.getURL(), ex);
        }

        if (!synthetic) {
            checkNotDirtyUrl(entity.getURL(), "Found invalid URL in entity " + entity);
        }

        try {
            checkNotNull(entity.getName());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found invalid name in entity " + entity.getURL(), ex);
        }

        try {
            checkNotNull(entity.getDescription());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found invalid description in entity " + entity.getURL(), ex);
        }

    }

    /**
     * Checks if provided structure complies with open entity specs.
     *
     * @param structure to check
     * @throws IllegalArgumentException if provided structure is not conformant
     * to OpenEntity specs.
     */
    public static void checkStructure(IStructure structure) {

        checkStructure(structure, false);

    }

    /**
     * Checks if provided structure complies with open entity specs. For
     * synthetic structures some checks will be skipped.
     *
     * @param structure the structure to check
     * @param synthetic if true URLs and local ids of attributes and values will
     * not be checked. The URL of the structure is not checked anyway.
     */
    public static void checkStructure(IStructure structure, boolean synthetic) {
        if (structure == null) {
            throw new IllegalArgumentException("Found null structure!");
        }

        checkNotDirtyUrl(structure.getEtypeURL(), "Found invalid entity type URL in structure " + structure.getURL());

        if (structure.getStructureAttributes() == null) {
            throw new IllegalArgumentException("Found null attributes in structure " + structure.getURL());
        }

        for (IAttribute attr : structure.getStructureAttributes()) {
            try {
                checkAttribute(attr, synthetic);
            }
            catch (Exception ex) {
                throw new IllegalArgumentException("Found invalid attribute in structure " + structure.getURL(), ex);
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
    public static void checkAttribute(IAttribute attribute, boolean synthetic) {
        if (attribute == null) {
            throw new IllegalArgumentException("Found null attribute!");
        }

        IAttributeDef attrDef = attribute.getAttrDef();

        if (!synthetic && attribute.getLocalID() == null) {
            throw new IllegalArgumentException("Found null local ID in attribute " + attribute);
        }

        if (attrDef == null) {
            throw new IllegalArgumentException("Found null attribute definition in attribute " + attribute.getLocalID());
        }

        if (attribute.getValues() == null) {
            throw new IllegalArgumentException("Found null values list in attribute " + attribute.getLocalID());
        }

        if (attribute.getValuesCount() != attribute.getValues().size()) {
            throw new IllegalArgumentException("Found inconsistent values count in attribute " + attribute.getLocalID() + ". ValuesCount = " + attribute.getValuesCount() + ". attr.getValues.().size() = " + attribute.getValues().size());
        }

        for (IValue val : attribute.getValues()) {
            try {
                checkValue(val, attrDef, synthetic);
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
    public static void checkAttribute(IAttribute attribute) {
        checkAttribute(attribute, false);

    }

    /**
     * Checks if provided value complies with open entity specs.
     *
     * @param value to check
     * @throws IllegalArgumentException if provided value is not conformant to
     * OpenEntity specs.
     */
    public static void checkValue(IValue value, IAttributeDef attrDef) {
        checkValue(value, attrDef, false);
    }

    /**
     * Checks if provided value complies with open entity specs.
     *
     * @param value to check
     * @throws IllegalArgumentException if provided value is not conformant to
     * OpenEntity specs.
     */
    public static void checkValue(IValue value, IAttributeDef attrDef, boolean synthetic) {

        String datatype = attrDef.getDataType();

        if (value == null) {
            throw new IllegalArgumentException("Found null value!");
        }

        if (!synthetic && value.getLocalID() == null) {
            throw new IllegalArgumentException("Found null local ID in value " + value);
        }

        if (value.getValue() == null) {
            throw new IllegalArgumentException("Found null object in value " + value.getLocalID());
        }
//here
        if (DataTypes.getDataTypes().get(datatype) == null) {
            throw new IllegalArgumentException("Found unsupported datatype " + datatype + " in value " + value.getValue() + ". Its class is " + value.getValue().getClass().getName());
        }

        if (!(DataTypes.getDataTypes().get(datatype).isInstance(value.getValue()))) {
            throw new IllegalArgumentException("Found value not corresponding to its datatype " + datatype + ". Value is " + value.getValue() + ". Its class is " + value.getValue().getClass().getName());
        }

        if (DataTypes.STRUCTURE.equals(datatype)) { // for structures we do deep check
            IStructure s = (IStructure) value.getValue();
            if (!attrDef.getRangeEtypeURL().equals(s.getEtypeURL())) {
                throw new IllegalArgumentException("Found structure value with value ID " + value.getLocalID() + " having etype URL different from its attribute rangeEtypeURL! "
                        + "\nStructure etype URL: " + s.getEtypeURL() + "\nAttribute rangeEtypeURL: " + attrDef.getRangeEtypeURL());
            }
            checkStructure(s, synthetic);
        }

        if (DataTypes.ENTITY.equals(datatype)) { // for entities we only check URL and name            
            IEntity entity = (IEntity) value.getValue();
            if (!synthetic) {

                checkNotDirtyUrl(entity.getURL(), "Found invalid URL in entity inside value with local ID: " + value.getLocalID());

            }

            checkNotNull(entity.getName(), "Found invalid name in entity with URL " + entity.getURL() + " inside value with local ID: " + value.getLocalID());

        }

    }

    /**
     * Checks if provided concept complies with open entity specs.
     *
     * @param concept to check
     * @throws IllegalArgumentException if provided concept is not conformant to
     * OpenEntity specs.
     */
    public static void checkConcept(IConcept concept) {
        if (concept == null) {
            throw new IllegalArgumentException("Found null concept!");
        }

        checkNotDirtyUrl(concept.getURL(), "Found invalid URL in concept " + concept);

        try {
            checkNotNull(concept.getDescription());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found invalid description in concept " + concept.getURL(), ex);
        }

        try {
            checkNotNull(concept.getName());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Found invalid name in concept " + concept.getURL(), ex);
        }

    }

    

}
