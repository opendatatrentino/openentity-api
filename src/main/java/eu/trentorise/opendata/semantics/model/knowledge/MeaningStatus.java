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
 * @author David Leoni <david.leoni@unitn.it>
 * @date 12 May
 *
 */
public enum MeaningStatus {

    /**
     * Either the system found it is not possible to assign a meaning (i.e. for
     * entities with insufficient identifying attributes) or the user explicitly
     * declares the sense is missing
     */
    MISSING,
    /**
     * Only entities can be declared as new.
     */
    NEW,
    /**
     * The user can declare he is unsure about a concept meaning. Only available
     * for concepts.
     */
    NOT_SURE,
    /**
     * A meaning has been selected.
     */
    SELECTED,
    /**
     * System is unsure about the meaning but offers a set of candidates to
     * choose from.
     */
    TO_DISAMBIGUATE

}
