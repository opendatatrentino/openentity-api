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

import eu.trentorise.opendata.semantics.model.entity.AttrType;
import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.entity.IStructure;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semtext.MeaningKind;
import eu.trentorise.opendata.semtext.SemText;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Class to model data types. <br/>
 * <br/>
 * Primitive datatypes are a subset of <a
 * target="_blank" href="http://www.w3.org/TR/2004/REC-rdf-mt-20040210/">
 * RDF Semantics, XSD datatypes section</a>. Complex types such as
 * <i>oe:structure</i>
 * and <i>oe:entity</i> are newly defined. <br/>
 * <br/>
 * Datatypes are mapped to Java objects according to the following scheme: <br/>
 * <br/>
 * STRING : {@link java.lang.String} <br/>
 * BOOLEAN : {@link java.lang.Boolean} <br/>
 * DATE : {@link java.util.Date} <br/>
 * INTEGER : {@link java.lang.Integer} <br/>
 * FLOAT : {@link java.lang.Float} <br/>
 * LONG : {@link java.lang.Long} <br/>
 * CONCEPT : {@link eu.trentorise.opendata.semantics.model.knowledge.IConcept}
 * <br/>
 * SEMANTIC_TEXT : {@link eu.trentorise.opendata.semtext.SemText} <br/>
 * ENTITY : {@link eu.trentorise.opendata.semantics.model.entity.IEntity} A
 * convenient implementation for entities which are values of other entities
 * (like in part-of relations) may be found in the
 * {@link eu.trentorise.opendata.semantics.impl.model.entity.MinimalEntity}
 * class <br/>
 * STRUCTURE : {@link eu.trentorise.opendata.semantics.model.entity.IStructure}
 * <br/>
 *
 * @author Ivan Tankoyeu <tankoyeu@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 *
 *
 */
public final class DataTypes {

    public static final String OPEN_ENTITY_PREFIX = "oe:";
    public static final String OPEN_ENTITY_URL = "https://github.com/opendatatrentino/openentity-api/1.0/";

    
 /**
     * every value inhibits this one, null included.
     */
    public static final String ANY_TYPE = OPEN_ENTITY_PREFIX + "any-type";
    public static final AttrType TS_ANY_TYPE = AttrType.of(ANY_TYPE);
    
    public static final String STRING = "xsd:string";
    public static final String BOOLEAN = "xsd:boolean";
    public static final String DATE = "xsd:dateTime";
    public static final String INTEGER = "xsd:int";
    public static final String FLOAT = "xsd:float";
    public static final String LONG = "xsd:long";
    public static final String CONCEPT = OPEN_ENTITY_PREFIX + "concept";
    public static final String SEMANTIC_TEXT = OPEN_ENTITY_PREFIX + "semantic-text";
    public static final String STRUCTURE = OPEN_ENTITY_PREFIX + "structure";
    public static final String ENTITY = OPEN_ENTITY_PREFIX + "entity";

    public static final String ATTRDEF = "oe:attrdef";
    public static final String ETYPE = "oe:etype";
    
    
    public static final AttrType AT_STRING = AttrType.of(DataTypes.STRING);
    public static final AttrType AT_BOOLEAN = AttrType.of(DataTypes.BOOLEAN);
    public static final AttrType AT_DATE = AttrType.of(DataTypes.DATE);
    public static final AttrType AT_INTEGER = AttrType.of(DataTypes.INTEGER);
    public static final AttrType AT_FLOAT = AttrType.of(DataTypes.FLOAT);
    public static final AttrType AT_LONG = AttrType.of(DataTypes.LONG);
    public static final AttrType AT_CONCEPT = AttrType.of(DataTypes.CONCEPT);
    public static final AttrType AT_SEMANTIC_TEXT = AttrType.of(DataTypes.SEMANTIC_TEXT);
    // gives error on init public static final AttrType AT_STRUCTURE = AttrType.of(DataTypes.STRUCTURE);
    // gives error on init public static final AttrType AT_ENTITY = AttrType.of(DataTypes.ENTITY);
    

    private static final Map<String, String> DATATYPE_PRETTY_NAMES_IT = new HashMap();
    private static final Map<String, String> DATATYPE_PRETTY_NAMES_EN = new HashMap();
    private static final Map<Locale, Map<String, String>> DATATYPE_PRETTY_NAMES_MAP = new HashMap();
    private static final Map JAVA_DATATYPES = new HashMap();

    static {

        DATATYPE_PRETTY_NAMES_EN.put(STRING, "String");
        DATATYPE_PRETTY_NAMES_EN.put(BOOLEAN, "Boolean");
        DATATYPE_PRETTY_NAMES_EN.put(DATE, "Date");
        DATATYPE_PRETTY_NAMES_EN.put(INTEGER, "Integer");
        DATATYPE_PRETTY_NAMES_EN.put(FLOAT, "Single precision number");
        DATATYPE_PRETTY_NAMES_EN.put(LONG, "Long integer");
        DATATYPE_PRETTY_NAMES_EN.put(CONCEPT, "Concept");
        DATATYPE_PRETTY_NAMES_EN.put(SEMANTIC_TEXT, "Semantic text");
        DATATYPE_PRETTY_NAMES_EN.put(STRUCTURE, "Structure");
        DATATYPE_PRETTY_NAMES_EN.put(ENTITY, "Entity");
        DATATYPE_PRETTY_NAMES_MAP.put(Locale.ENGLISH, DATATYPE_PRETTY_NAMES_EN);

        DATATYPE_PRETTY_NAMES_IT.put(STRING, "Stringa");
        DATATYPE_PRETTY_NAMES_IT.put(BOOLEAN, "Booleano");
        DATATYPE_PRETTY_NAMES_IT.put(DATE, "Data");
        DATATYPE_PRETTY_NAMES_IT.put(INTEGER, "Intero");
        DATATYPE_PRETTY_NAMES_IT.put(FLOAT, "Numero a precisione singola");
        DATATYPE_PRETTY_NAMES_IT.put(LONG, "Intero grande");
        DATATYPE_PRETTY_NAMES_IT.put(CONCEPT, "Concetto");
        DATATYPE_PRETTY_NAMES_IT.put(SEMANTIC_TEXT, "Testo semantico");
        DATATYPE_PRETTY_NAMES_IT.put(STRUCTURE, "Struttura");
        DATATYPE_PRETTY_NAMES_IT.put(ENTITY, "Entità");
        DATATYPE_PRETTY_NAMES_MAP.put(Locale.ITALIAN, DATATYPE_PRETTY_NAMES_IT);

        JAVA_DATATYPES.put(STRING, String.class);
        JAVA_DATATYPES.put(BOOLEAN, Boolean.class);
        JAVA_DATATYPES.put(DATE, Date.class);
        JAVA_DATATYPES.put(INTEGER, Integer.class);
        JAVA_DATATYPES.put(FLOAT, Float.class);
        JAVA_DATATYPES.put(LONG, Long.class);
        JAVA_DATATYPES.put(CONCEPT, IConcept.class);
        JAVA_DATATYPES.put(SEMANTIC_TEXT, SemText.class);
        JAVA_DATATYPES.put(ENTITY, IEntity.class);
        JAVA_DATATYPES.put(STRUCTURE, IStructure.class);

    }

    /**
     * Provides a map of the supported datatypes. Each datatype name is mapped
     * to the java class or interface that represents it.
     *
     * @return an unmodifiable map of the supported data types
     */
    public static Map<String, Class> getDataTypes() {
        return Collections.unmodifiableMap(JAVA_DATATYPES);
    }

    /**
     * Returns human-readable name of a datatype in the provided locale
     *
     * @param datatype the given datatype
     * @param locale the language of the desired translation
     * @return the datatype in a human-readable form in the provided locale if
     * the translation is present and the datatype is supported, returns null
     * otherwise.
     */
    public static String prettyDataType(String datatype, Locale locale) {

        Map map = DATATYPE_PRETTY_NAMES_MAP.get(locale);
        if (map == null) {
            return null;
        } else {
            return DATATYPE_PRETTY_NAMES_MAP.get(locale).get(datatype);
        }

    }

    /**
     *
     * @return a set of the supported locales
     */
    public static Set<Locale> supportedLocales() {
        return Collections.unmodifiableSet(DATATYPE_PRETTY_NAMES_MAP.keySet());
    }

    private DataTypes() {

    }

    /**
     * Returns the MeaningKind corresponding to the given datatype url.
     *
     * @throws IllegalArgumentException if url doesn't correspond to entity or
     * concept datatypes.
     */
    public static MeaningKind datatypeUrlToMeaningKind(String datatypeUrl) {
        if (datatypeUrl.contains(ENTITY)) {
            return MeaningKind.ENTITY;
        }
        if (datatypeUrl.contains(CONCEPT)) {
            return MeaningKind.CONCEPT;
        }
        throw new IllegalArgumentException("Tried to get a MeaningKind from an unsupported datatype url! Found url: " + datatypeUrl);
    }
    
    /**
     * todo here we don't consider structures to be enricheable, even if
     * they _might_ contain relations and names in subfields.
     */
    public static boolean isNlpProcessable(String dataType) {
        return DataTypes.ENTITY.equals(dataType)
                || DataTypes.CONCEPT.equals(dataType)
                || DataTypes.SEMANTIC_TEXT.equals(dataType);
    }

    /**
     * Only
     * {@link eu.trentorise.opendata.semantics.services.model.DataTypes.ENTITY ENTITY}
     * are considered relational. todo 0.3 here we don't consider structures
     * to be relational, even if they _might_ contain relations in subfields.
     */
    public static boolean isRelationalDatatype(String dataType) {
        return DataTypes.ENTITY.equals(dataType);
    }

    /**
     * @see #isRelationalDatatype(java.lang.String)
     */
    public boolean isRelationalDataType(AttrType AttrType) {
        return AttrType.isRelational();
    }    
}
