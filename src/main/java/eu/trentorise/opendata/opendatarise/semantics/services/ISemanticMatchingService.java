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
package eu.trentorise.opendata.opendatarise.semantics.services;

import eu.trentorise.opendata.opendatarise.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.opendatarise.semantics.services.model.ICorrespondence;
import java.util.List;

/**
 * A service that performs semantic matching between two schemas
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@trentorise.eu>
 * @date Feb 03, 2014
 */
public interface ISemanticMatchingService {

    /**
     * Given a list of disambiguated column header names guesses a target
     * entitytype and returns a matching between the source headers and the
     * attributes of the target entity type. Todo for now types are expressed as
     * String, maybe we can do an enum.
     *
     * @param sourceConcepts the names of the resource columns
     * @param sourceTypes a list of source types to aid the matcher
     * @return - the correspondence 
     */
    ICorrespondence matchSchemas(List<IConcept> sourceConcepts, List<String> sourceTypes);
}
