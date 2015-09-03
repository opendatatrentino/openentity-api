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

import eu.trentorise.opendata.semantics.model.knowledge.IConcept;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Locale;

/**
 * Knowledge services are used to read words from the a knowledge base.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 *
 */
public interface IKnowledgeService {

    /**
     * Returns the concepts with the given URLs
     *
     * @param URLs a list of URLs for concepts
     * @return the list of concepts. If a given concept is not found, null is
     * returned at the corresponding position in the list.
     */
    List<IConcept> readConcepts(List<String> URLs);

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
     * @return the parent of all concepts.
     * @throws UnsupportedOperationException if concepts are not supported by
     * the Ekb.
     */
    IConcept readRootConcept();

    /**
     * Returns a list of possible concepts with name similar to provided partial
     * name.
     *
     * @param partialName a partial concept name. It is assumed to be in the
     * provided locale.
     * @return a list of candidate entities, ordered by probability. The first
     * one is the most probable.
     */
    List<SearchResult> searchConcepts(String partialName, Locale locale);

    /**
     * Returns the distance between two concepts, in the range of [0.0, 1.0].
     * Two concepts are considered as equal if their absolute distance is less
     * than {@link OdtUtils#TOLERANCE}.
     *
     * @param sourceUrl source concept url
     * @param targetUrl target concept url
     * @return the distance between two provided concepts
     */
    double getConceptsDistance(String sourceUrl, String targetUrl);
}
