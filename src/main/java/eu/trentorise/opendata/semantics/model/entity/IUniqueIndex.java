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
 * @date Mar 27, 2014 
 */
public interface IUniqueIndex {

    /**
     * Gets the URL of the attribute definition
     *
     * @return a string that holds the URL of the attribute definition
     */
    String getURL();        
    
    /**
     * Gets the attribute definitions for the unique indexes
     *
     * @return the attribute definitions for the unique indexes
     */
    List<IAttributeDef> getAttributeDefs();

    /**
     * Adds an attribute definition to the unique indexes
     *
     * @param attrDef the attribute definition to be added
     */
    void addAttributeDef(IAttributeDef attrDef);

    /**
     * Removes an attribute definition from the unique indexes
     *
     * @param attrDefID the local ID of the attribute definition to be removed
     */
    void removeAttributeDef(long attrDefID);
}
