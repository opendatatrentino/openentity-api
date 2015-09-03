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
package eu.trentorise.opendata.semantics.services;

import eu.trentorise.opendata.semantics.model.entity.IAttribute;
import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.entity.IValue;

import java.io.Writer;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nullable;

/**
 * Entity services allow CRUD on entities and attributes, and also exporting in
 * various formats.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @author Ivan Tankoyeu <tankoyeu@disi.unitn.it>
 *
 *
 */
public interface IEntityService {

    /**
     * Creates an entity
     *
     * @param entity the entity to be created. Will not be changed by the
     * method.
     * @return the GUID of the newly created entity
     * @deprecated use
     * {@link #createEntityURL(eu.trentorise.opendata.semantics.model.entity.IEntity)}
     * instead
     */
    Long createEntity(IEntity entity);

    /**
     * Creates an entity
     *
     * @param entity the entity to be created. Will not be changed by the
     * method. URL of the provided entity will be ignored. If an equal entity is
     * already present in the server, a duplicate with different URL will be
     * created. All linked entities must exist on the server.
     * @return the URL of the newly created entity
     */
    String createEntityURL(IEntity entity);

    /**
     * Updates an entity. For values of type IDict, translations provided in
     * this entity are going to be merged with translations already present in
     * the Ekb
     *
     * @param entity the entity to be updated
     * @throws eu.trentorise.opendata.semantics.NotFoundException if the entity
     * doesn't exists
     */
    void updateEntity(IEntity entity);

    /**
     * Deletes an entity
     *
     * @param entityID the local ID of the entity to be deleted
     * @deprecated use deleteEntity(String) instead
     */
    void deleteEntity(long entityID);

    /**
     * Deletes an entity
     *
     * @param URL the URL of the entity to delete. If the entity is not present,
     * the method silently exits.
     */
    void deleteEntity(String URL);

    /**
     * Reads an entity given its URL
     *
     * @param URL the URL of the entity to read
     * @return the entity identified by this URL, or null if not found
     */
    @Nullable
    IEntity readEntity(String URL);

    /**
     * Reads an entities given their URLs
     *
     * @param entityURLs the URLs of the entity to read
     * @return the list of entities identified by the URL. Not found entities
     * will be null.
     */
    List<IEntity> readEntities(List<String> entityURLs);

    /**
     * Creates an attribute of the specified attribute definition kind and fills
     * it with the provided value.
     *
     * @param attrDef the attribute definition of the attribute to create
     * @param value the value to set in the attribute. It must belong to
     * supported types in {@link
     *                eu.trentorise.opendata.semantics.services.model.DataTypes} or be a
     * Collection of supported values
     *
     * @return the attribute with the provided values
     */
    IAttribute createAttribute(IAttributeDef attrDef, Object value);


    /**
     * Updates an attribute value in an attribute in an entity
     *
     * @param entity the entity which owns the attribute
     * @param attribute the attribute that owns the value
     * @param newValue the value to be updated
     * @throws eu.trentorise.opendata.semantics.NotFoundException if the
     * provided entity or attribute don't exist
     */
    void updateAttributeValue(IEntity entity, IAttribute attribute,
            IValue newValue);

    /**
     * Writes the given entities in rdf format into the provided writer.
     *
     * @param entityURLs the URLs of the entities to export. If list is empty, an IllegalArgumentException is thrown.
     * @param writer A writer to store the generated rdf
     */
    void exportToRdf(List<String> entityURLs, Writer writer);

    /**
     * Writes the given entities in rdf format into the provided writer.
     *
     * @param entityURLs the URLs of the entities to export. If list is empty, an IllegalArgumentException is thrown.
     * @param writer A writer to store the generated jsonld
     */
    void exportToJsonLd(List<String> entityURLs, Writer writer);

    /**
     * Writes the given entities in rdf format into the provided writer.
     *
     * @param entityURLs the URLs of the entities to export. If list is empty,
     * an IllegalArgumentException is thrown.
     * @param writer A writer to store the generated csv
     */
    void exportToCsv(List<String> entityURLs, Writer writer);

    /**
     * Returns a list of possible entities with name similar to provided partial
     * name. Returned entities will belong to provided etype, if any.
     *
     * @param partialName a partial entity name. It is assumed to be in one of
     * the default locales of the ekb.
     * @param etypeURL The url of the entity type. Enities returned will be
     * instances of that etype (or its descendants).
     * @return a list of candidate entities, ordered by probability. The first
     * one is the most probable.
     */
    List<SearchResult> searchEntities(String partialName, @Nullable String eTypeURL, Locale locale);

    /**
     * Returns whether or not the URL was generated during calls to assign URL
     * for deduplication purposes.
     */
    boolean isTemporaryURL(String temporaryEntityURL);
}
