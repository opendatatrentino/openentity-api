/* 
 * Copyright 2013-2015   Trento Rise   trentorise.eu
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
package eu.trentorise.opendata.semantics.services.model;

import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.entity.IStructure;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semantics.model.knowledge.ISemanticText;
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
 * RDF Semantics, XSD datatypes section</a>. Complex types such as <i>oe:structure</i>
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
 * CONCEPT : {@link eu.trentorise.opendata.semantics.model.knowledge.IConcept} <br/>
 * SEMANTIC_TEXT : {@link eu.trentorise.opendata.semantics.model.knowledge.ISemanticText} <br/> 
 * ENTITY : {@link eu.trentorise.opendata.semantics.model.entity.IEntity} A convenient implementation for entities which are values of other entities (like in part-of relations) may be found in the {@link eu.trentorise.opendata.semantics.impl.model.entity.MinimalEntity} class <br/> 
 * STRUCTURE : {@link eu.trentorise.opendata.semantics.model.entity.IStructure} <br/>
 *
 * @author Ivan Tankoyeu <tankoyeu@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date Sept 17, 2014
 *
 */
public final class DataTypes {

    public static final String OPEN_ENTITY_PREFIX = "oe";
    public static final String OPEN_ENTITY_EXPANDED_PREFIX = "https://github.com/opendatatrentino/openentity-api/1.0/";

    public static final String STRING = "xsd:string";
    public static final String BOOLEAN = "xsd:boolean";
    public static final String DATE = "xsd:dateTime";
    public static final String INTEGER = "xsd:int";
    public static final String FLOAT = "xsd:float";
    public static final String LONG = "xsd:long";
    public static final String CONCEPT = "oe:concept";
    public static final String SEMANTIC_TEXT = "oe:semantic-text";    
    public static final String STRUCTURE = "oe:structure";
    public static final String ENTITY = "oe:entity";
    
    public static final String ATTRDEF = "oe:attrdef";
    public static final String ETYPE = "oe:etype";

    private static final Map<String, String> DATATYPE_PRETTY_NAMES_IT = new HashMap<String, String>();
    private static final Map<String, String> DATATYPE_PRETTY_NAMES_EN = new HashMap<String, String>();
    private static final Map<Locale, Map<String, String>> DATATYPE_PRETTY_NAMES_MAP = new HashMap<Locale, Map<String, String>>();
    private static final Map JAVA_DATATYPES = new HashMap<String, Class>();

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
        JAVA_DATATYPES.put(SEMANTIC_TEXT, ISemanticText.class);        
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

    private DataTypes(){
        
    }
}
