/**
 * *****************************************************************************
 * Copyright 2012-2014 Trento Rise (www.trentorise.eu/)
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

import eu.trentorise.opendata.semantics.services.model.ISearchResult;
import java.util.List;

/**
 * A concept is a language independent element that gives meaning
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Sergey Kanshin <kanshin@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date Apr 11, 2014
 */
public interface IConcept {
    
    /**
     * Gets the name of the concept
     * @return the name of the concept in the default languages if available
     */    
    IDict getName();

    /**
     * Gets the URL of the concept
     *
     * @return the concept URL as string
     */
    String getURL();

    /**
     * Gets the global id of the concept
     * @deprecated use getURL instead
     * @return the concept global id
     */
    Long getGUID();
    
    /**
     * Gets the description of the concept
     * @return the description of the concept in the default languages if available
     */
    IDict getDescription();
    
}
