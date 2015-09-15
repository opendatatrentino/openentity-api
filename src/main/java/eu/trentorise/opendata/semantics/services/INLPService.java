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

import eu.trentorise.opendata.semtext.SemText;
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
 * 
 */
public interface INLPService {

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
     * @throws
     * eu.trentorise.opendata.semantics.exceptions.UnsupportedFeatureException
     * if nlp is not supported by the Ekb

     */
    List<SemText> runNLP(Iterable<String> texts, @Nullable String domainURL);

    /** 
     * Searches for concepts or entities 
     * 
     * @param partialName a partial entity or concept name. It is assumed to be in the provided locale.
     * @param locale if unknown use {@link Locale#ROOT}
     * @return a list of candidate entities and/or concepts, ordered by probability. The first one is the most probable.
     */
    List<TermSearchResult> freeSearch(String partialName, Locale locale);          
    
    
    /**
     * Detects the language of a series of strings.
     * @return the language of the input strings, or {@link Locale#ROOT} if locale can't be detected.
     * @throws
     * eu.trentorise.opendata.semantics.exceptions.UnsupportedFeatureException
     * if language detection is not supported by the Ekb
     */
    Locale detectLanguage(Iterable<String> strings);
}
