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

import eu.trentorise.opendata.semantics.model.entity.IAttribute;
import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.entity.IValue;
import java.io.Writer;
import java.util.List;

/**
 * Entity services allow CRUD on entities and attributes, and also exporting in
 * various formats.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @author Ivan Tankoyeu <tankoyeu@disi.unitn.it>* 
 * @date Apr 8, 2014
 */
public interface IEntityService {

    /**
     * Creates an entity
     *
     * @param entity the entity to be created
     * @return the URL of the newly created entity
     */
    String createEntity(IEntity entity);

    /**
     * Updates an entity. For values of type INLString, translations provided in
     * this entity are going to be merged with translations already present in
     * the Ekb
     *
     * @param entity the entity to be updated
     */
    void updateEntity(IEntity entity);

    /**
     * Deletes an entity
     *
     * @param URL the URL of the entity to delete
     */
    void deleteEntity(String URL);

    /**
     * Reads an entity given its URL
     *
     * @param entityURL the URL of the entity to read
     * @return the entity identified by this URL, or null if not found
     */
    IEntity readEntity(String entityURL);

    /**
     * Creates an attribute of the specified attribute definition kind and fills
     * it with provided values.
     *
     * @param attrDef the attribute definition of the attribute to create
     * @param values the values to set in the attribute. They must belong to
     * supported types in
     * {@link eu.trentorise.opendata.semantics.services.model.DataTypes}
     * @return the attribute with the provided values
     */
    IAttribute createAttribute(IAttributeDef attrDef, List<Object> values);

    /**
     * Creates an attribute of the specified attribute definition kind and fills
     * it with the provided value.
     *
     * @param attrDef the attribute definition of the attribute to create
     * @param value the value to set in the attribute. It must belong to
     * supported types in
     * {@link eu.trentorise.opendata.semantics.services.model.DataTypes}
     * @return the attribute with the provided values
     */
    IAttribute createAttribute(IAttributeDef attrDef, Object value);

    /**
     * Adds an attribute to an entity
     *
     * @param entity the entity that
     * @param attribute
     */
    void addAttribute(IEntity entity, IAttribute attribute);

    /**
     * Adds an attribute value to an attribute in an entity
     *
     * @param entity the entity which owns the attribute
     * @param attribute the attribute that owns the value
     * @param value the value to be added
     */
    void addAttributeValue(IEntity entity, IAttribute attribute,
            IValue value);

    /**
     * Updates an attribute value in an attribute in an entity
     *
     * @param entity the entity which owns the attribute
     * @param attribute the attribute that owns the value
     * @param newValue the value to be updated
     */
    void updateAttributeValue(IEntity entity, IAttribute attribute,
            IValue newValue);

    /**
     * Writes the given entities in rdf format into the provided writer.
     *
     * @param entityIds the ids of the entities to export
     * @param writer A writer to store the generated rdf
     */
    void exportToRdf(List<Long> entityIds, Writer writer);

    /**
     * Writes the given entities in rdf format into the provided writer.
     *
     * @param entityIds the ids of the entities to export
     * @param writer A writer to store the generated jsonld
     */
    void exportToJsonLd(List<Long> entityIds, Writer writer);

    /**
     * Writes the given entities in rdf format into the provided writer.
     *
     * @param entityIds the ids of the entities to export
     * @param writer A writer to store the generated csv
     *
     */
    void exportToCsv(List<Long> entityIds, Writer writer);

}
