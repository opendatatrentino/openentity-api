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
 *
 * A complex structure that is represented by the set of attribute.
 *
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date Apr 10, 2014
 */
public interface IStructure {

     /**
     * Gets the local identifier for the entity
     * @deprecated use getURL() instead
     * @return the local identifier as Long
     */
    Long getLocalID();    
    
    /**
     * Gets the URL of the object
     *
     * @return a string that holds the URL of the object
     */
    String getURL();

    /**
     * Sets the URL of the object
     *
     * @param url a string that holds the URL of the object
     */
    void setURL(String url);

    /**
     * Gets the external identifier of the structure
     *
     * @return a string representing the external identifier of the structure
     */
    List<IAttribute> getStructureAttributes();

    /**
     * Sets the list of attributes in this structure
     *
     * @param attributes the list of attributes to be set in the structure
     */
    void setStructureAttributes(List<IAttribute> attributes);

    /**
     * Gets the structure type
     *
     * @return the structure type
     */
    IEntityType getEtype();

    /**
     * Sets the structure type
     *
     * @param type the structure type
     */
    void setEtype(IEntityType type);
}
