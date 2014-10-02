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

import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semantics.services.model.ISearchResult;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Locale;

/**
 * Knowledge services are used to read words from the a knowledge base.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date Sept 24, 2014
 */
public interface IKnowledgeService {

    /**
     * Returns the concepts with the given URLs
     *
     * @deprecated Use {@link #readConcepts(java.util.List) } instead
     * @param URLs a list of URLs for concepts
     * @return the list of concepts. If a given concept is not found, null is returned at the corresponding position in
     * the list.
     */
    List<IConcept> getConcepts(List<String> URLs);

    /**
     * Returns the concepts with the given URLs
     *     
     * @param URLs a list of URLs for concepts
     * @return the list of concepts. If a given concept is not found, null is returned at the corresponding position in
     * the list.
     */
    List<IConcept> readConcepts(List<String> URLs);    
    
    /**
     * Returns the concepts with the given URLs
     *
     *  @deprecated Use {@link #readConcept(java.lang.String)} instead
     * @param URL the URL of a concept
     * @return The concept. If not found, null is returned instead,
     */
    @Nullable
    IConcept getConcept(String URL);

    /**
     * Returns the concepts with the given URLs
     *
     * @param URL the URL of a concept
     * @return The concept. Returns null if the concept is not found.
     */
    @Nullable
    IConcept readConcept(String URL);    
    
    /**
     * Returns the parent of all concepts
     *
     * @deprecated Use {@link #readConcept(java.lang.String) } instead
     * @return the parent of all concepts
     */
    IConcept getRootConcept();
    
    /**
     * Returns the parent of all concepts
     *
     * @return the parent of all concepts. 
     * @throws UnsupportedOperationException if concepts are not supported by the Ekb.
     */    
    IConcept readRootConcept();
    
    /**
     * Returns a list of possible concepts with name similar to provided partial name.
     *
     * @param partialName a partial concept name. It is assumed to be in one of the default locales of the ekb.
     * @return a list of candidate entities, ordered by probability. The first one is the most probable.
     */
    List<ISearchResult> searchConcepts(String partialName, Locale locale);    
}
