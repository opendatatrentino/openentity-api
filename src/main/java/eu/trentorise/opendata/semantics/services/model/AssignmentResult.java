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

package eu.trentorise.opendata.semantics.services.model;

/**
 * The status of a reconciliation operation against a single entity
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * 
 */
public enum AssignmentResult {
    /** Entity to reconcile was not found in the database and has sufficient attributes to get assigned an id *//** Entity to reconcile was not found in the database and has sufficient attributes to get assigned an id */
    NEW,
    /** Entity to reconcile doesn't have attributes that allow to uniquely identify it with sufficient certainty */
    INVALID,
    /** Entity to reconcile can be matched against one or more entities in the ekb */
    REUSE
}
