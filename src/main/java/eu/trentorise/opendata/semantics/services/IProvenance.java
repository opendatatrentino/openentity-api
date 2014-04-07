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

import eu.trentorise.opendata.semantics.model.provenance.Evidence;
import eu.trentorise.opendata.semantics.model.provenance.Provenance;

/**
 * Interface for service related to provenance and evidence
 *
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date Apr 7, 2014
 */
public interface IProvenance {

    /**
     * Returns the provenance of the given attribute
     *
     * @param attributeID the ID of the attribute which has the value
     * @param attributeValueID the ID of the attribute value
     *
     * @return the provenance of the attribute value
     */
    Provenance readProvenance(Long attributeID, Long attributeValueID);

    /**
     * Returns the evidence of the given entity
     *
     * @param entityURL the URL of the entity
     * @return the evidence of the given entity
     */
    Evidence readEvidence(String entityURL);
}
