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
package eu.trentorise.opendata.semantics.model.entity;

import javax.annotation.Nullable;
import java.util.List;

/**
 *
 * A complex structure that is represented by the set of attribute.
 *
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date June 13, 2014
 */
public interface IStructure {

     /**
     * Gets the local identifier for the entity
     * @deprecated use getURL() instead
     * @return the local identifier as Long
     */
    Long getLocalID();    
    
    /**
     * Gets the URL of the object
     *
     * @return a string that holds the URL of the object
     */
    String getURL();

    /**
     * Sets the URL of the object
     * @deprecated we don't need methods to change objects
     * @param url a string that holds the URL of the object
     */
    void setURL(String url);

    /**
     * Gets the external identifier of the structure
     *
     * @return a string representing the external identifier of the structure
     */
    List<IAttribute> getStructureAttributes();

    /**
     * Sets the list of attributes in this structure
     *
     * @param attributes the list of attributes to be set in the structure
     * @deprecated Don't want setters in interfaces
     */
    void setStructureAttributes(List<IAttribute> attributes);

    /**
     * Gets the structure type
     * @deprecated Use {@link #getEtypeURL()} or  {@link eu.trentorise.opendata.semantics.services.IEntityTypeService#getEntityType(String)} instead.
     * @return the structure type
     */
    IEntityType getEtype();


    /**
     * Gets the structure type
     * @return the structure type URL
     *
    */
    String getEtypeURL();

    /**
     * Sets the structure type
     * @deprecated we don't need methods to change objects
     * @param type the structure type
     */
    void setEtype(IEntityType type);

    /**
     * Gets an attribute from the structure.
     *
     * @param attrDefURL the URL of the attribute definition corresponding to the desired attribute.
     * @return the attribute corresponding to the given attribute def, if present. Returns null otherwise.
     */
    @Nullable
    IAttribute getAttribute(String attrDefURL);
}
