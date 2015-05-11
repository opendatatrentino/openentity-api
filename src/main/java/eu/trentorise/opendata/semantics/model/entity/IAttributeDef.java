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
package eu.trentorise.opendata.semantics.model.entity;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;

/**
 * The attribute definition stores information about the attributes, such as the
 * Name, the Concept and if the attribute is set or not.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * 
 */
public interface IAttributeDef {

    /**
     * Gets the Globally Unique Identifier (GUID) for the attribute definition
     * @deprecated use getURL instead
     * @return the Globally Unique Identifier (GUID) represented as Long
     */
    Long getGUID();
 
    /**
     * Gets the URL of the attribute definition
     *
     * @return a string that holds the URL of the attribute definition
     */
    String getURL();


    /**
     * Gets the attribute name in the given language
     *
     * @return the attribute name in the default languages if available
     */
    Dict getName();

    /**
     * Gets the data type of the attribute definition. Possible values for the
     * datatypes are listed in
     * {@link eu.trentorise.opendata.semantics.services.model.DataTypes} class.
     *
     * @return the data type as string
     */
    String getDataType();

    /**
     * Return the entity type for the range, when the datatype is oe:structure or oe:entity
     * @deprecated we only provide the URL with {@link #getRangeEtypeURL()}
     * @return the entity type if the data type is oe:structure or oe:entity, null otherwise
     */
    IEntityType getRangeEType();

    /**
     * Gets the concept of the attribute definition
     * @deprecated Use {@link #getConceptURL()}} or {@link eu.trentorise.opendata.semantics.services.IKnowledgeService#getConcept(String)} instead.
     * @return the concept of attribute definition
     */
    IConcept getConcept();

    /**
     * Gets the concept URL of the entity type
     * @return the concept URL of the entity type
     */
    String getConceptURL();

    /**
     * Gets the IsSet flag that tells if the attribute can hold a set of values
     * or it can hold only one value.
     *
     * @deprecated use {@link #isList()} instead
     * @return true if the attribute can hold a set or false if it can hold only
     * one value.
     */
    boolean isSet();

    /**
     * Tells if the attribute can hold a set of values.
     *
     * @return true if the attribute can hold a list of values
     */
    boolean isList();



    /**
     * Gets the regular expression that all the attribute values should follow
     * @deprecated Doesn't make much sense
     * 
     * @return the regular expression as string
     */
    String getRegularExpression();

    /**
     * Set the regular expression that all the attribute values should follow
     * @deprecated Doesn't make much sense
     *
     * @param regularExpression the regular expression as string
     */
    void setRegularExpression(String regularExpression);

    /**
     * Gets the IsMandatory flag that tells if the attribute is mandatory or not
     *
     * @return true if the attribute is mandatory or false if it is not
     * mandatory
     */
    boolean isMandatory();

       
    /**
     * Returns the entity type for the attribute definition 
     * @deprecated use getETypeURL instead
     * @return entity type
     */
     Long getEType();
    
    /**
     * Returns the owner entity type for this attribute definition 
     * 
     * @return entity type URL
     */
     String getEtypeURL();  	

    /**
     * Return the entity type for the range, when the datatype is oe:structure or oe:entity     
     * @return the entity type URL if the data type is oe:structure or oe:entity, null otherwise
     */     
     String getRangeEtypeURL();
    	
}
