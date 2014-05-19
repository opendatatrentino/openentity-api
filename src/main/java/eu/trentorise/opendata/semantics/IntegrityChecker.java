/**
 * *****************************************************************************
 * Copyright 2012-2013 Trento Rise (www.trentorise.eu/)
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the GNU Lesser General Public License (LGPL)
 * version 2.1 which accompanies this distribution, and is available at
 *
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************
 */
package eu.trentorise.opendata.semantics;

import eu.trentorise.opendata.semantics.model.entity.IAttribute;
import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.entity.IStructure;
import eu.trentorise.opendata.semantics.model.entity.IValue;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.services.IEkb;
import eu.trentorise.opendata.semantics.services.model.AssignmentResult;
import eu.trentorise.opendata.semantics.services.model.DataTypes;
import eu.trentorise.opendata.semantics.services.model.IAttributeCorrespondence;
import eu.trentorise.opendata.semantics.services.model.IIDResult;
import eu.trentorise.opendata.semantics.services.model.ISchemaCorrespondence;
import java.util.List;
import java.util.Locale;

/**
 * Class to collect checkers for implementations of Open Entity API interfaces.
 *
 * @author David Leoni
 */
public class IntegrityChecker {

    private static void checkScore(Float score) {
        if (score == null) {
            throw new IntegrityException("Found null score!");
        }
        float prec = 0.01f;
        if (score < -prec || score > 1.0f + prec) {
            throw new IntegrityException("Score " + score + " exceeds bounds [-1.01, 1.01].");
        }
    }

    /**
     * Checks if provided schema correspondence complies with open entity specs.
     *
     * @param schemaCor to check
     * @throws IntegrityException if provided correspondence is not conformant
     * to OpenEntity specs.
     */
    public static void checkSchemaCorrespondence(ISchemaCorrespondence schemaCor) {
        if (schemaCor == null) {
            throw new IntegrityException("Schema correspondence is null!");
        }

        try {
            checkEntityType(schemaCor.getEtype());
        } catch (Exception ex) {
            throw new IntegrityException("Invalid etype in schema correspondence!", ex);
        }

        if (schemaCor.getAttributeCorrespondences() == null) {
            throw new IntegrityException("Schema Correrespondence has null correspondences!");
        }

        for (IAttributeCorrespondence attrCor : schemaCor.getAttributeCorrespondences()) {
            checkURL(attrCor.getHeaderConceptURL());
            try {

                checkScore(attrCor.getScore());
            } catch (Exception ex) {

                throw new IntegrityException("Invalid score for attribute correspondence " + attrCor.getScore() + " Found score: " + attrCor.getScore(), ex);
            }

            if (attrCor.getColumnIndex() < 0) {
                throw new IntegrityException("Attribute correspondence column index is lesss than zero. Found index: " + attrCor.getColumnIndex());
            }

            checkAttributeDef(attrCor.getAttrDef());

            if (attrCor.getAttrMap() == null) {
                throw new IntegrityException("Found null attr map in IAttributeCorrespondence for etype " + schemaCor.getEtype().getURL());
            }

            for (IAttributeDef ad : attrCor.getAttrMap().keySet()) {
                try {
                    checkScore(attrCor.getAttrMap().get(ad));
                } catch (Exception ex) {
                    throw new IntegrityException("Found invalid score in attribute correspondence map for attribute " + ad.getURL(), ex);
                }
            }

        }
    }

    /**
     * Checks if provided dictionary complies with open entity specs.
     *
     * @param dict the dictionary to check
     * @throws IntegrityException if provided dict is not conformant to
     * OpenEntity specs.
     */
    public static void checkDict(IDict dict) {

        if (dict == null) {
            throw new IntegrityException("Dict is null!");
        }

        if (dict.getLocales() == null) {
            throw new IntegrityException("Dict has null locales!");
        }

        for (Locale loc : dict.getLocales()) {
            List<String> tr = dict.getStrings(loc);
            if (tr == null) {
                throw new IntegrityException("Dict has null translations for locale " + loc);
            }
            for (String t : tr) {
                if (t == null) {
                    throw new IntegrityException("Found null translation fot locale " + loc);
                }
            }
        }

    }

    /**
     * Checks if provided URL complies with open entity specs.
     *
     * @param URL the URL to check
     * @throws IntegrityException if provided URL is not conformant to
     * OpenEntity specs.
     */
    public static void checkURL(String URL) {
        if (URL == null) {
            throw new IntegrityException("Found null URL!");
        }
        if (URL.length() == 0) {
            throw new IntegrityException("Found empty URL!");
        }

    }

    /**
     * Checks if provided entity type complies with open entity specs.
     *
     * @param etype the entity type to check
     * @throws IntegrityException if provided etype is not conformant to
     * OpenEntity specs.
     */
    public static void checkEntityType(IEntityType etype) {
        if (etype == null) {
            throw new IntegrityException("Found null etype!");
        }

        try {
            checkURL(etype.getURL());
        } catch (IntegrityException ex) {
            throw new IntegrityException("Invalid etype URL!", ex);
        }

        try {
            checkDict(etype.getName());
        } catch (Exception ex) {
            throw new IntegrityException("Invalid etype name!", ex);
        }

        if (etype.getConcept() == null) {
            throw new IntegrityException("Found null concept for etype " + etype.getURL());
        }

        /**
         * not supported for now if (etype.getUniqueIndexes() == null){ throw
         * new IntegrityException("Found null unique indexes for etype " +
         * etype.getURL()); }
         */
        for (IAttributeDef attrDef : etype.getAttributeDefs()) {
            try {
                checkAttributeDef(attrDef);
            } catch (Exception ex) {
                throw new IntegrityException("Found invalid attr def in etype " + etype.getURL(), ex);
            }
        }

    }

    /**
     * Checks if provided attribute def complies with open entity specs.
     *
     * @param attrDef the attribute definition to check
     * @throws IntegrityException if provided attrDef is not conformant to
     * OpenEntity specs.
     */
    public static void checkAttributeDef(IAttributeDef attrDef) {
        if (attrDef == null) {
            throw new IntegrityException("Found null attribute def!");
        }
        if (attrDef.getURL() == null) {
            throw new IntegrityException("Found null URL for attribute def " + attrDef.getName());
        }
        if (attrDef.getURL().length() == 0) {
            throw new IntegrityException("Found empty URL for attribute def " + attrDef.getName());
        }

        if (attrDef.getName() == null) {
            throw new IntegrityException("Found null name dict for attribute def " + attrDef.getURL());
        }

        try {
            checkURL(attrDef.getEtypeURL());
        } catch (Exception ex) {
            throw new IntegrityException("Found invalid etype URL for attribute def " + attrDef.getURL(), ex);
        }

        if (attrDef.getDataType() == null) {
            throw new IntegrityException("Found null datatype for attribute def " + attrDef.getURL());
        }
        if ((attrDef.getDataType().equals(DataTypes.STRUCTURE) || attrDef.getDataType().equals(DataTypes.ENTITY))) {
            try {
                checkURL(attrDef.getRangeEtypeURL());
            } catch (Exception ex) {
                throw new IntegrityException("Attribute def " + attrDef.getURL() + " with parent etype " + attrDef.getEtypeURL() + " is of datatype " + attrDef.getDataType() + ", but is has invalid getRangeEtypeURL()", ex);

            }
        }
        if (attrDef.getConcept() == null) {
            throw new IntegrityException("Found null concept for attribute def " + attrDef.getURL());
        }

    }

    /**
     * Checks if provided ekb complies with open entity specs.
     *
     * @param ekb the entity knowledge base to check
     * @throws IntegrityException if provided ekb is not conformant to
     * OpenEntity specs.
     */
    public static void checkEkbQuick(IEkb ekb) {

        if (ekb == null) {
            throw new IntegrityException("Found null ekb!");
        }

        if (ekb.getDefaultLocales() == null) {
            throw new IntegrityException("Found null locales list in ekb " + ekb.getClass().getCanonicalName());
        }

        if (ekb.getDefaultLocales().get(0) == null) {
            throw new IntegrityException("Found null first locale in ekb " + ekb.getClass().getCanonicalName());
        }
    }

    public static void checkIDResult(IIDResult idResult) {

        if (idResult == null) {
            throw new IntegrityException("Found null idResult!");
        }

        if (idResult.getAssignmentResult() == null) {
            throw new IntegrityException("Found null assignment result in idResult: " + idResult);
        }

        if (AssignmentResult.REUSE.equals(idResult.getAssignmentResult())
                || AssignmentResult.NEW.equals(idResult.getAssignmentResult())) {

            try {
                checkURL(idResult.getURL());
            } catch (IntegrityException ex) {
                throw new IntegrityException("Found invalid URL in idResult with REUSE! idResult is " + idResult, ex);
            }

            if (idResult.getResultEntity() == null) {
                throw new IntegrityException("Found null result entity in idResult with REUSE! idResult is " + idResult);
            }

            IEntity resEntity = idResult.getResultEntity();
            try {
                checkEntity(resEntity);
            } catch (IntegrityException ex) {
                throw new IntegrityException("Failed integrity check on entity " + resEntity + " in idResult " + idResult, ex);
            }
        }

        if (AssignmentResult.REUSE.equals(idResult.getAssignmentResult())) {
            if (idResult.getEntities() == null) {
                throw new IntegrityException("Found null result entities in idResult with REUSE!  idResult is " + idResult);
            }

            if (idResult.getEntities().isEmpty()) {
                throw new IntegrityException("Found empty entities in idResult with REUSE. idResult is " + idResult);
            }

            for (IEntity entity : idResult.getEntities()) {
                try {
                    checkEntity(entity);
                } catch (Exception ex) {
                    throw new IntegrityException("Failed integrity check on entity " + entity + " in idResult " + idResult, ex);
                }
            }

        }

    }

    /**
     * Checks if provided entity complies with open entity specs.
     *
     * @param entity to check
     * @throws IntegrityException if provided entity is not conformant to
     * OpenEntity specs.
     */
    public static void checkEntity(IEntity entity) {

        if (entity == null) {
            throw new IntegrityException("Found null entity!");
        }

        try {
            checkStructure(entity);
        } catch (Exception ex) {
            throw new IntegrityException("Found invalid structural properties of entity " + entity.getURL(), ex);
        }

        try {
            checkDict(entity.getName());
        } catch (Exception ex) {
            throw new IntegrityException("Found invalid name in entity " + entity.getURL(), ex);
        }

        try {
            checkDict(entity.getDescription());
        } catch (Exception ex) {
            throw new IntegrityException("Found invalid description in entity " + entity.getURL(), ex);
        }

    }

    /**
     * Checks if provided structure complies with open entity specs.
     *
     * @param structure to check
     * @throws IntegrityException if provided structure is not conformant to
     * OpenEntity specs.
     */
    public static void checkStructure(IStructure structure) {

        if (structure == null) {
            throw new IntegrityException("Found null structure!");
        }

        try {
            checkURL(structure.getURL());
        } catch (Exception ex) {
            throw new IntegrityException("Found invalid URL in structure " + structure, ex);
        }

        try {
            checkEntityType(structure.getEtype());
        } catch (Exception ex) {
            throw new IntegrityException("Found invalid entity type in structure " + structure.getURL(), ex);
        }

        if (structure.getStructureAttributes() == null) {
            throw new IntegrityException("Found null attributes in structure " + structure.getURL());
        }

        for (IAttribute attr : structure.getStructureAttributes()) {
            try {
                checkAttribute(attr);
            } catch (Exception ex) {
                throw new IntegrityException("Found invalid attribute in structure " + structure.getURL(), ex);
            }
        }

    }

    /**
     * Checks if provided attribute complies with open entity specs.
     *
     * @param attribute to check
     * @throws IntegrityException if provided attribute is not conformant to
     * OpenEntity specs.
     */
    public static void checkAttribute(IAttribute attribute) {

        if (attribute == null) {
            throw new IntegrityException("Found null attribute!");
        }

        if (attribute.getLocalID() == null) {
            throw new IntegrityException("Found null local ID in attribute " + attribute);
        }

        if (attribute.getAttributeDefinition() == null) {
            throw new IntegrityException("Found null attribute definition in attribute " + attribute.getLocalID());
        }

        if (attribute.getValues() == null) {
            throw new IntegrityException("Found null values list in attribute " + attribute.getLocalID());
        }

        if (attribute.getValuesCount() != attribute.getValues().size()) {
            throw new IntegrityException("Found inconsistent values count in attribute " + attribute.getLocalID() + ". ValuesCount = " + attribute.getValuesCount() + ". attr.getValues.().size() = " + attribute.getValues().size());
        }

        for (IValue val : attribute.getValues()) {
            try {
                checkValue(val);
            } catch (Exception ex) {
                throw new IntegrityException("Found invalid value in attribute " + attribute.getLocalID(), ex);
            }
        }

    }

    /**
     * Checks if provided value complies with open entity specs.
     *
     * @param value to check
     * @throws IntegrityException if provided value is not conformant to
     * OpenEntity specs.
     */
    public static void checkValue(IValue value) {
        if (value == null) {
            throw new IntegrityException("Found null value!");
        }

        if (value.getLocalID() == null) {
            throw new IntegrityException("Found null local ID in value " + value);
        }

        if (value.getValue() == null) {
            throw new IntegrityException("Found null object in value " + value.getLocalID());
        }

    }

    /**
     * Checks if provided concept complies with open entity specs.
     *
     * @param concept to check
     * @throws IntegrityException if provided concept is not conformant to
     * OpenEntity specs.
     */    
    public static void checkConcept(IConcept concept) {
        if (concept == null) {
            throw new IntegrityException("Found null concept!");
        }       
        
        try {
            checkURL(concept.getURL());
        } catch(Exception ex){
            throw new IntegrityException("Found invalid URL in concept " + concept, ex);
        }
        
        try {
            checkDict(concept.getDescription());
        } catch (Exception ex){
            throw new IntegrityException("Found invalid description in concept " + concept.getURL());
        }
        
        try {
            checkDict(concept.getName());
        } catch (Exception ex){
            throw new IntegrityException("Found invalid name in concept " + concept.getURL(), ex);
        }                
        
        
    }
}