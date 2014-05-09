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

import java.util.Locale;

/**
 * Represents a string in the given locale
 * 
 * @deprecated use {link eu.trentorise.opendata.semantics.model.knowledge.IDict} instead
 * @author David Leoni <david.leoni@unitn.it>
 * @date Apr 10, 2014
 */
public interface INLString {
    
    /**
     * Gets the string locale
     * @return the locale of the string
     */
    Locale getLocale();
    
    /**
     * Gets the string
     * @return the string in the locale of the INLString
     */    
    String getString();
}
