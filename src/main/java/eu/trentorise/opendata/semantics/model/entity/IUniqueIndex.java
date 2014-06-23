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

package eu.trentorise.opendata.semantics.model.entity;

import java.util.List;

/**
 * The unique indexes are a set of attribute definitions to be used in entity
 * matching
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date June 13, 2014
 */
public interface IUniqueIndex {

    /**
     * Gets the URL of the unique index
     * @deprecated use getURL instead
     * @return ID of the URL of the unique index
     */
    Long getLocalID();

    /**
     * Gets the URL of the unique index
     *
     * @return a string that holds the URL of the unique index
     */
    String getURL();        
    
    /**
     * Gets the attribute definitions for the unique index
     * @deprecated Use {@link #getAttributeDefURLs()} instead.
     * @return the attribute definitions for the unique indexes
     */
    List<IAttributeDef> getAttributeDefs();

    /**
     * Gets the attribute definitions for the unique index
     *
     * @return the attribute definitions for the unique index
     */
    List<String> getAttributeDefURLs();

    /**
     * Adds an attribute definition to the unique index
     * @deprecated
     * @param attrDef the attribute definition to be added
     */
    void addAttributeDef(IAttributeDef attrDef);

    /**
     * Removes an attribute definition from the unique indexes
     * @deprecated we don't need methods to change objects
     * @param attrDefID the local ID of the attribute definition to be removed
     */    
    void removeAttributeDef(long attrDefID);  
    
    /**
     * Removes an attribute definition from the unique index
     * @deprecated
     * @param attrURL the URL of the attribute definition to be removed
     */
    void removeAttributeDef(String attrURL);
}
