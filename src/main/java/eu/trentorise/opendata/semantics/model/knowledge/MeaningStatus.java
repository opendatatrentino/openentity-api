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
package eu.trentorise.opendata.semantics.model.knowledge;

/**
 * Represents the status of a meaning assigned to an
 * {@link eu.trentorise.opendata.semantics.model.knowledge.IWord}
 *
 * Note it is not possible to specify that a word has no meaning because there
 * is no entity/concept in the knowledge base to link to. In this case, word tag
 * should be removed from the semantic text containing it.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 28 Aug 2014
 *
 */
public enum MeaningStatus {

    /**
     * The entity/concept is not present in the knowledge base and cannot be
     * inserted in it, because, for example, it doesn't have sufficient
     * identifying attributes
     */
    INVALID,
    /**
     * The entity/concept is not present in the knowledge base but it is still
     * valid and can be inserted in it
     */
    NEW,
    /**
     * The entity/concept has candidates that are too similar in the knowledge
     * base
     */
    NOT_SURE,
    /**
     * A meaning has been selected.
     */
    SELECTED,
    /**
     * Entity/concept should be disambiguated.
     */
    TO_DISAMBIGUATE

}
