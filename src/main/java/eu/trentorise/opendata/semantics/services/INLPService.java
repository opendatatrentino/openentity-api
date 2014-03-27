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
import eu.trentorise.opendata.semantics.model.knowledge.ITableResource;
import it.unitn.disi.sweb.core.nlp.model.NLText;
import java.util.List;

/**
 * The NLP interface provides natural language processing services such as named
 * entity recognition and word sense disambiguation
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@trentorise.eu>
 * @date Feb 25, 2014
 */
public interface INLPService {

    /**
     * Takes a natural language text and finds the concepts and entities in the
     * text.
     *
     * @param nlText the input natural language string. After calling the
     * function it will have also the concept and entities found in the text.
     * They will be added to the object with their locations.
     */
    void namedEntityRecognition(NLText nlText);

    /**
     * Takes a natural language name of an entity as a string and finds the
     * disambiguated entity.
     *
     * @param nlText the natural language name of an entity which has been
     * processed before by Named Entity Recognition. After calling the function
     * it will have also the disambiguated Entity added to the object with their
     * locations.
     */
    void namedEntityDisambiguate(NLText nlText);

    /**
     * Takes a natural language text that has been processed by NER and
     * disambiguates the concepts
     *
     * @param nlText the input natural language string. After calling the
     * function it will have also the concepts disambiguated. Each tagged word
     * may have more than one candidate for the concept representing it
     * @param context a list of concepts
     */
    void wordSenseDisambiguate(NLText nlText, List<IConcept> context);

    /**
     * Disambiguates the column header names of a resource that typically comes
     * from a catalog.
     *
     * @param table the content of the resource
     * @param context the metadata of the resource, typically found in the
     * catalog
     * @return the column names enriched with the meaning, ranked by confidence
     */
    List<NLText> disambiguateColumns(ITableResource table, IResourceContext context);

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
     * @param nlText an input natural language string
     * @return the natural language text object with all the entities and
     * concepts found and disambiguated
     */
    NLText runNLP(String nlText);
    

}
