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

import eu.trentorise.opendata.semantics.model.provenance.Evidence;
import eu.trentorise.opendata.semantics.model.provenance.Provenance;

/**
 * Interface for service related to provenance and evidence
 *
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date June 8, 2014
 */
public interface IProvenanceService {

    /**
     * TODO Why IDs are 'Long' and not 'long'?
     * Returns the provenance of the given attribute
     *
     * @param attributeID the ID of the attribute which has the value
     * @param attributeValueID the ID of the attribute value
     *
     * @return the provenance of the attribute value
     */
    Provenance readProvenance(Long attributeID, Long attributeValueID);

    /**
     * TODO What happens if entity is not existing?
     *      What if we have no evidence for a given entity?
     * Returns the evidence of the given entity
     *
     * @param entityURL the URL of the entity
     * @return the evidence of the given entity
     */
    Evidence readEvidence(String entityURL);
}
