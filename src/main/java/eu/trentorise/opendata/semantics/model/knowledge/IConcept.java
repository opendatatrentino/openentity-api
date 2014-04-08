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

import java.util.Locale;

/**
 * A concept is a language independent element that gives meaning
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Sergey Kanshin <kanshin@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date Apr 8, 2014
 */
public interface IConcept {

    /**
     * Gets the URL of the concept
     *
     * @return the concept URL as string
     */
    String getURL();
    

    /**
     * Gets the common name for the concept in the given language
     * @return the common name for the concept in the default languages if available
     */
    INLString getCommonlyReferredAs();

    /**
     * Gets the summary of the concept 
     *
     * @return the summary of the concept in the default languages if available
     */
    INLString getSummary();

    /**
     * Gets the description on the concept
     * @return the description on the concept in the default languages if available
     */
    INLString getDescription();

    /**
     * Gets the Part of Speech (POS) of the concept 
     * @return the Part of Speech (POS) of the concept in the default languages if available
     */
    INLString getPartOfSpeech();
}
