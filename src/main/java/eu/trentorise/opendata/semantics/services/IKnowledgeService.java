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

import javax.annotation.Nullable;
import java.util.List;

/**
 * Knowledge services are used to read words from the a knowledge base.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date June 8, 2014
 */
public interface IKnowledgeService {

    /*
     * Reading words by lemma
     * @param wordLemma the word's lemma
     * @return the list of words that have this lemma
     *
    List<IWord> readByWordLemma(String wordLemma);

    /**
     * Reading a word by a prefix
     * @param prefix the prefix of the words to be found
     * @return the list of words that starts with the given prefix
     *
    List<IWord> readByWordPrefix(String prefix); */

    /**
     * Returns the concepts with the given URLs
     *
     * @param URLs a list of URLs for concepts
     * @return the list of concepts. If a given concept is not found, null is returned at the corresponding position in
     * the list.
     */
    List<IConcept> getConcepts(List<String> URLs);

    /**
     * Returns the concepts with the given URLs
     *
     * @param URL the URL of a concept
     * @return The concept. If not found, null is returned instead,
     */
    @Nullable
    IConcept getConcept(String URL);

    /**
     * Returns the parent of all concepts
     *
     * @return the parent of all concepts
     */
    IConcept getRootConcept();

}
