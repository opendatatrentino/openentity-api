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

import java.util.List;

/**
 * The unique indexes are a set of attribute definitions to be used in entity
 * matching
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date June 13, 2014
 */
public interface IUniqueIndex {

    /**
     * Gets the URL of the unique index
     * @deprecated use getURL instead
     * @return ID of the URL of the unique index
     */
    Long getLocalID();

    /**
     * Gets the URL of the unique index
     *
     * @return a string that holds the URL of the unique index
     */
    String getURL();        
    
    /**
     * Gets the attribute definitions for the unique index
     * @deprecated Use {@link #getAttributeDefURLs()} instead.
     * @return the attribute definitions for the unique indexes
     */
    List<IAttributeDef> getAttributeDefs();

    /**
     * Gets the attribute definitions for the unique index
     *
     * @return the attribute definitions for the unique index
     */
    List<String> getAttributeDefURLs();

    /**
     * Adds an attribute definition to the unique index
     * @deprecated
     * @param attrDef the attribute definition to be added
     */
    void addAttributeDef(IAttributeDef attrDef);

    /**
     * Removes an attribute definition from the unique indexes
     * @deprecated we don't need methods to change objects
     * @param attrDefID the local ID of the attribute definition to be removed
     */    
    void removeAttributeDef(long attrDefID);  
    
    /**
     * Removes an attribute definition from the unique index
     * @deprecated
     * @param attrURL the URL of the attribute definition to be removed
     */
    void removeAttributeDef(String attrURL);
}
