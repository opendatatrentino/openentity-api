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
package eu.trentorise.opendata.semantics.model.entity;

import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import java.util.List;
import javax.annotation.Nullable;

/**
 * The entity type defines the attributes that the entity can have
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date June 13, 2014
 */
public interface IEntityType {

    /**
     * Gets the name of the entity type
     *
     * @return the name of the entity type in the default languages if available. Returned dict can be empty.
     */
    IDict getName();

    /**
     * Gets the concept of the entity type
     * @deprecated Use {@link #getConceptURL()}} or {@link eu.trentorise.opendata.semantics.services.IKnowledgeService#getConcept(String)} instead.
     * @return the concept of the entity type
     */
    IConcept getConcept();

    /**
     * Gets the concept URL of the entity type
     * @return the concept URL of the entity type
     */
    String getConceptURL();


    /**
     * Sets the concept of the entity type
     *
     * @deprecated we don't need methods to change objects
     * @param concept the concept of the entity type
     */
    void setConcept(IConcept concept);

    /**
     * Gets the attribute definitions for the entity type
     *
     * @return the attribute definitions for the entity type
     */
    List<IAttributeDef> getAttributeDefs();

    /**
     * Adds an attribute definition to the entity type
     * @deprecated we don't need methods to change objects
     * @param attrDef the attribute definition to be added
     */
    void addAttributeDef(IAttributeDef attrDef);

    /**
     * Removes an attribute definition from the entity type
     *
     * @deprecated use removeAttributeDef by url
     * @param attrDefID the local ID of the attribute definition to be removed
     */
    void removeAttributeDef(long attrDefID);

    /**
     * Removes an attribute definition from the entity type
     * @deprecated we don't need methods to change objects
     * @param attrDefURL the URL of the attribute definition to be removed
     */
    void removeAttributeDef(String attrDefURL);

    /**
     * Gets the unique indexes
     *
     * @return the unique indexes
     */
    List<IUniqueIndex> getUniqueIndexes();

    /**
     * Removes Unique Indexes
     *
     *      * @deprecated we don't need methods to change objects
     * @param uniqueIndexID the local ID of the unique indexes to be removed
     */
    void removeUniqueIndex(long uniqueIndexID);

    /**
     * Removes Unique Indexes
     * @deprecated we don't need methods to change objects
     * @param uniqueIndexURL the URL of the unique indexes to be removed
     */
    void removeUniqueIndex(String uniqueIndexURL);

    /**
     * Adds Unique Indexes
     * @deprecated we don't need methods to change objects
     * @param uniqueIndex the unique indexes to be added
     */
    void addUniqueIndex(IUniqueIndex uniqueIndex);

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
     * entity. If it represents a structure, returns null.
     */
    @Nullable
    IAttributeDef getNameAttrDef();

    /**
     * Returns the attribute def used for description.
     *
     * @return the attribute def used for description if the entity type represents an
     * entity. If it represents a structure, returns null.
     */
    @Nullable
    IAttributeDef getDescriptionAttrDef();


    /**
     * Returns the attribute def indicated by the provided URL.
     *
     * @return Returns the attribute def indicated by the provided URL if it exists, null otherwise.
     */
    @Nullable
    IAttributeDef getAttrDef(String URL);
}
