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

import eu.trentorise.opendata.semantics.model.knowledge.IResourceContext;
import eu.trentorise.opendata.semantics.model.knowledge.ISemanticText;
import eu.trentorise.opendata.semantics.model.knowledge.ITableResource;
import eu.trentorise.opendata.semantics.services.model.IWordSearchResult;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/**
 * The NLP interface provides natural language processing services such as named
 * entity recognition and word sense disambiguation
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date Oct 08, 2014
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
     * Takes natural language strings and assigns to each string candidate
     * concept or entity meanings according to the domainURL. If a concept domain
     * is provided, all the candidate meanings will be concepts descending from
     * the provided concept. If an entity type domain is provided, all the candidate
     * meanings will be entities having as ancestor type the provided etype.
     *
     * @param texts an input natural language string
     * @param domainURL Either null, an entity type URL or a concept URL. 
     * @return the list of enriched strings, one for each of the provided texts.
     * Each enriched string will be a semantic text of only one word.
     */
    List<ISemanticText> runNLP(List<String> texts, @Nullable String domainURL);

    /** 
     * Searches for concepts or entities 
     * 
     * @param partialName a partial entity or concept name. It is assumed to be in the provided locale.
     * @return a list of candidate entities and/or concepts, ordered by probability. The first one is the most probable.
     */
    List<IWordSearchResult> freeSearch(String partialName, Locale locale);          
}
