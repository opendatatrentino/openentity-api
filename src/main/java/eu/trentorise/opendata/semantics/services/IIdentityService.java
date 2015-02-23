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
 * @date 15 Oct, 2014
 *
 */
public interface IIdentityService {

    /**
     * Reconciliates the given list of entities against existing ones.
     *
     * @param entities a list of entities to reconciliate. They will not be
     * modified by the function.
     * @param numCandidates maximum number of candidates to return for each
     * entity to match
     * @return a list of reconciliation results, one for each input entity.
     */
    List<? extends IIDResult> assignURL(List<? extends IEntity> entities, int numCandidates);
}
