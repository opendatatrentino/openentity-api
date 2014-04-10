/**
 * *****************************************************************************
 * Copyright 2013-2014 Trento Rise (www.trentorise.eu/)
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

import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Represents a dictionary of a string that may have translations in several
 * languages.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date Apr 10, 2014
 */
public interface IDict {

    /**
     * Gets the translations in the given locale.
     *
     * @param locale the language of the desired translation
     * @return the strings in the given locale if present. If no string is
     * present an empty list is returned.
     */
    List<String> getStrings(Locale locale);

    /**
     * Gets the locales for which translations are present in the IDict
     *
     * @return the available locales
     */
    Set<Locale> getLocales();
}
