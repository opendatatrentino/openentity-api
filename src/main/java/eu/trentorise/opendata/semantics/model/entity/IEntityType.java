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
import java.util.List;
import javax.annotation.Nullable;

/**
 * The entity type defines the attributes that the entity can have
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 *
 */
public interface IEntityType {

    /**
     * Gets the name of the entity type
     *
     * @return the name of the entity type in the default languages if
     * available. Returned dict can be empty.
     */
    Dict getName();
 

    /**
     * Gets the concept URL of the entity type
     *
     * @return the concept URL of the entity type
     */
    String getConceptUrl();

    /**
     * Gets the attribute definitions for the entity type
     *
     * @return the attribute definitions for the entity type
     */
    List<IAttributeDef> getAttributeDefs();

    /**
     * Gets the unique indexes
     *
     * @return the unique indexes
     */
    List<IUniqueIndex> getUniqueIndexes();

    /**
     * Gets the URL of the entity type
     *
     * @return the URL of the entity type
     */
    String getURL();

    /**
     * Returns the attribute def used for name.
     *
     * @return the attribute def used for name if the entity type represents an
     * entity. If it represents a structure, throws exception.     
     */  
    @Nullable
    IAttributeDef getNameAttrDef();

    /**
     * Returns the attribute def used for description.
     *
     * @return the attribute def used for description if the entity type
     * represents an entity.  If it represents a structure, throws exception.
     * @throws eu.trentorise.opendata.semantics.exceptions.OpenEntityNotFoundException 
     */    
    @Nullable
    IAttributeDef getDescriptionAttrDef();

    /**
     * Returns the attribute def indicated by the provided URL.
     *
     * @return Returns the attribute def indicated by the provided URL if it
     * exists, throws exceprion otherwise.
     * 
     * @throws eu.trentorise.opendata.semantics.exceptions.OpenEntityNotFoundException if attribute is not found.
     */
    IAttributeDef getAttrDef(String URL);
}
