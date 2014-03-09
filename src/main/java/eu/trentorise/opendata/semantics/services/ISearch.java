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
 * @date Mar 09, 2014
 *
 */
public interface ISearch {

    /**
     * Performs a search for entities using EQL syntax
     *
     * @param eqlQuery A string query using EQL syntax to be executed
     * @return the list of entities that match the query
     */
    List<IEntity> searchEQL(String eqlQuery);

    /**
     * Performs a search for entities belonging to <i>entityType</>. For each
     * entity to search, a list of some of its attributes is passed to match
     * against. The list of attributes is only an indication, so the match needs
     * not to be exact. For each passed list of attributes, a list of maximum
     * numCandidates candidate entities is returned.
     *
     * Example call:  <br/>
     * <br/>
     * <pre>
     * search(City, 3,  
     *        [
     *          [name: "Trento city", state: "IT"], 
     *          [name: "Bolzano", position: (23.2323,54.23333))]
     *        ])
     * </pre>
     * 
     * Example result:
     * 
     *<pre>
     * [
     *  [
     *      (name: "Trento",    state: "IT-TN",     position: (24.33655,56.43666)),
     *      (name: "Trient",    state: "Autria",    position: (14.33253,96.43466))
     *      (name: "Trint",   state: "Germany",   position: (14.33253,96.43466)),
     
     *  ],
     *  [
     *      (name: "Bolzano",   state: "Italy",         position: (22.2321,55.23313)),
     *      (name: "Balzan",    state: "Switzerland",   position: (12.3722,16.23414)),
     *      (name: "Bozan",     state: "Hungary",       position: (15.3738,13.24529))
     *  ]
     * ]
     * </pre>
     * 
     * @param entityType the entitytype to which the entities belong to.
     * @param numCandidates max number of candidates to return for each searched
     * entity
     * @param attributes 
     * @return A list of lists of candidate entities. Each list of candidate
     * entities is sorted by relevance. The entity with the highest score is to
     * be found at the head of this list.                
     */
    List<List<IEntity>> search(IEntityType entityType, int numCandidates, List<List<IAttribute>> attributes);

    /**
     * Performs a concept search for entities
     *
     * @param conceptSearchQuery A string query using concept search syntax to
     * be executed
     * @return the list of entities that match the query
     */
    List<IEntity> conceptSearch(String conceptSearchQuery);
}
