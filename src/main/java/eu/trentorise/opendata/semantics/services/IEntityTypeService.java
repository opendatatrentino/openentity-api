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
package eu.trentorise.opendata.semantics.services;

import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.entity.IUniqueIndex;
import eu.trentorise.opendata.semantics.services.model.IEtypeSearchResult;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Entity type services allow reading and modifying entity types
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date June 08, 2014
 */
public interface IEntityTypeService {

    /**
     * Reads all entity types available in the system
     *
     * @return list of all entity types in the system
     */
    List<IEntityType> getAllEntityTypes();

    /**
     * Returns the entity types with the given URLs
     *
     * @param URLs a list of URLs for entity types
     * @return the entity types. For entity types that were not found, the corresponding item in the list will contain
     * null.
     */

    List<IEntityType> getEntityTypes(List<String> URLs);

    /**
     * Returns a list of possible entity types with name similar to provided partial name.
     *
     * @param partialName a partial entity type name. It is assumed to be in one of the default locales of the ekb.
     * @return a list of candidate entity types, ordered by probability. The first one is the most probable.
     */
    List<IEtypeSearchResult> searchEntityTypes(String partialName);

    /**
     * Adds an attribute definition to an entity type
     *
     * @param entityType the entity type that will own the attribute definition
     * @param attrDef    the attribute definition to be added
     */
    void addAttributeDefToEtype(IEntityType entityType, IAttributeDef attrDef);

    /**
     * Adds a unique index to an entity type
     *
     * @param entityType  the entity type that will own the matching set
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
    IEntityType getEntityType(String URL);

    /**
     * Return the entity type by the given ID
     *
     * @param id of the entity type
     * @return entity type
     * @deprecated use getEntityType by URL instead
     */
    IEntityType getEntityType(long id);

    /**
     * Returns the parent of all structures
     *
     * @return the parent of all structures
     * @see #getRootEtype()
     */
    IEntityType getRootStructure();

    /**
     * Returns the parent of all etypes.
     *
     * @return the parent of all etypes. Must inherit from value returned by {@link #getRootStructure()}
     * @see #getRootStructure()
     */
    IEntityType getRootEtype();

}
