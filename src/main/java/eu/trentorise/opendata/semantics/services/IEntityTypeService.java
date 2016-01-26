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
package eu.trentorise.opendata.semantics.services;

import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.entity.IUniqueIndex;
import eu.trentorise.opendata.semantics.services.model.ISearchResult;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Locale;

/**
 * Entity type services allow reading and modifying entity types
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 */
public interface IEntityTypeService {

    /**
     * Refreshes the cached etypes and attr defs by updating old versions with
     * new ones from the server. 
     *
     * @since 0.26.3
     */
    void refreshEtypes();

    /**
     * Returns true if given etype is cached
     */
    boolean isEtypeCached(String etypeUrl);

    /**
     * Returns an etype if it is cached, otherwise throws exception
     *
     * @param etypeUrl
     * @throw eu.trentorise.opendata.semantics.NotFoundException if not cached
     * @see #readEntityType(java.lang.String)
     */
    IEntityType getEtype(String etypeUrl);


    /**
     * Reads all entity types available in the system
     *
     * @return list of all entity types in the system
     */
    List<IEntityType> readAllEntityTypes();


    /**
     * Returns the entity types with the given URLs
     *
     * @param URLs a list of URLs for entity types
     * @return the entity types. For entity types that were not found, the
     * corresponding item in the list will contain null.
     */
    List<IEntityType> readEntityTypes(List<String> URLs);

    /**
     * Returns a list of possible entity types with name similar to provided
     * partial name.
     *
     * @param partialName a partial entity type name. It is assumed to be in one
     * of the default locales of the ekb.
     * @return a list of candidate entity types, ordered by probability. The
     * first one is the most probable.
     */
    List<ISearchResult> searchEntityTypes(String partialName, Locale locale);

    /**
     * Adds an attribute definition to an entity type
     *
     * @param entityType the entity type that will own the attribute definition
     * @param attrDef the attribute definition to be added
     */
    void addAttributeDefToEtype(IEntityType entityType, IAttributeDef attrDef);

    /**
     * Adds a unique index to an entity type
     *
     * @param entityType the entity type that will own the matching set
     * @param uniqueIndex the unique index to be added
     */
    void addUniqueIndexToEtype(IEntityType entityType, IUniqueIndex uniqueIndex);

    /**
     * Return the entity type by the given URL
     *
     * @param URL The URL of the entity type
     * @return the entity type, null if not found.
     */
    @Nullable
    IEntityType readEntityType(String URL);



    /**
     * Return the entity type by the given ID
     *
     * @param id of the entity type
     * @return entity type
     * @deprecated use {@link #readEntityType(java.lang.String)} by URL instead
     */
    IEntityType getEntityType(long id);


    /**
     * Returns the parent of all structures
     *
     * @return the parent of all structures
     * @see #getRootEtype()
     */
    IEntityType readRootStructure();



    /**
     * Returns the parent of all etypes.
     *
     * @return the parent of all etypes. Must inherit from value returned by
     * {@link #getRootStructure()}
     * @see #getRootStructure()
     */
    IEntityType readRootEtype();

}
