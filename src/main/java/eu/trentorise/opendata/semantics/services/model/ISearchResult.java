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

import eu.trentorise.opendata.commons.Dict;

/**
 *
 * Represents the result of a search for an object that has an URL and a
 * multilingual name, such as entity types and concepts.
 *
 * @author David Leoni
 * 
 */
public interface ISearchResult {

    /**
     * Gets the URL of the object
     *
     * @return the object URL
     */
    String getURL();

    /**
     * Gets the object name
     *
     * @return the object name, in the default locales for the ekb.
     */
    Dict getName();
}
