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
import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import java.util.List;

/**
 * Interface for entity search services
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@trentorise.eu>
 * @date Mar 27, 2014
 *
 */
public interface ISearch {

    /**
     * Performs a search for entities using EQL syntax
     *
     * EQL syntax is an extended version from HQL
     * (http://docs.jboss.org/hibernate/core/3.3/reference/en/html/queryhql.html).
     *
     * Examples:
     *
     * 1. EQL Query : "from location location1" <br/>
     * Description: returns all entities, where the entity type is Location
     *
     * 2. EQL Query : "from location[description:administrative division]
     * location1" <br/>
     * Description : returns all entities, where the entity type is location and
     * the description attribute contains "administrative division".
     *
     * @param eqlQuery A string query using EQL syntax to be executed
     * @return a string table which contains the search results. Each column in
     * the table corresponds to an (ordered) item in the 'select' clause in the
     * query; and each row in the table corresponds to a set of values for these
     * columns.
     */
    String[][] searchEQL(String eqlQuery);

  
    /**
     * Performs a concept search for entities
     *
     * @param conceptSearchQuery A string query using concept search syntax to
     * be executed
     * @return the list of entities that match the query
     */
    List<IEntity> conceptSearch(String conceptSearchQuery);
}
