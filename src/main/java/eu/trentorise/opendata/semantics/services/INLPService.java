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
import eu.trentorise.opendata.semantics.model.knowledge.IResourceContext;
import eu.trentorise.opendata.semantics.model.knowledge.ISemanticText;
import eu.trentorise.opendata.semantics.model.knowledge.ITableResource;
import java.util.List;

/**
 * The NLP interface provides natural language processing services such as named
 * entity recognition and word sense disambiguation
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date Feb 25, 2014
 */
public interface INLPService {

    /**
     * Disambiguates the column header names of a resource that typically comes
     * from a catalog.
     *
     * @param table the content of the resource
     * @param context the metadata of the resource, typically found in the
     * catalog
     * @return the column names enriched with the meaning, ranked by confidence
     */
    List<ISemanticText> disambiguateColumns(ITableResource table, IResourceContext context);

    /**
     * Guesses the datatype common to a list of strings passed as input. Todo
     * for now types are expressed as String, maybe we can do an enum
     *
     * @return the guessed type
     * @param cellList a list of strings
     */
    String guessType(List<String> cellList);

    /**
     * Takes a natural language string and returns concepts and entities
     * disambiguated
     *
     * @param text an input natural language string
     * @return the natural language text object with all the entities and
     * concepts found and disambiguated
     */
    ISemanticText runNLP(String text);

    /**
     * Takes natural language strings and returns concepts and entities
     * disambiguated
     *
     * @param texts an input natural language string
     * @return the texts enriched with all the entities and concepts found and
     * disambiguated when there is sufficient confidence
     */
    List<ISemanticText> runNLP(List<String> texts);

    /**
     * Takes natural language strings and assigns to each string candidate
     * concepts meanings. All the candidate concepts will descend from the
     * provided parent concept.
     *
     * @param texts an input natural language string
     * @param parentConcept the concept from which all the resulting concepts
     * will descend from.
     * @return the list of enriched strings, one for each of the provided texts.
     * Each enriched string will be a semantic text of only one sentence and one
     * word. All the candidate meanings will be concepts descending from
     * parentConcept.
     */
    List<ISemanticText> runNLP(List<String> texts, IConcept parentConcept);

}
