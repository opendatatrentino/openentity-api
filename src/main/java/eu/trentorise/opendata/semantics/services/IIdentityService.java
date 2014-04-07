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

import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.services.model.IIDResult;
import java.util.List;




/**
 * Identity management services that handles the creation of IDs for entities
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date Mar 27, 2014

 */
public interface IIdentityService {

    /**     
     * Reconciliates the given list of entities against existing ones. 
     * 
     * @param entities a list of entities to reconciliate
     * @param numCandidates maximum number of candidates to return for each entity to match
     * @return a list of reconciliation results, one for each input entity.
     */
     List<IIDResult> assignGUID(List<IEntity> entities, int numCandidates);
}
