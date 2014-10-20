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
