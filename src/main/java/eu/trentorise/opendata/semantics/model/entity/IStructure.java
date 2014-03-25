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
 * @date Mar 25, 2014
 */
public interface IStructure {

    /**
     * Gets the local identifier for the entity
     *
     * @return the local identifier as Long
     */
    Long getLocalID();

    /**
     * Gets the Globally Unique Identifier (GUID) for the entity
     *
     * @return the Globally Unique Identifier (GUID) represented as Long
     */
    Long getGUID();
   
    /**
     * Gets the URI of the entity
     *
     * @return a string that holds the URI of the entity
     */
  
    String getURL();

    /**
     * Sets the URL of the entity
     *
     * @param url a string that holds the URL of the entity
     */
    void setURL(String url);

    /**
     * Gets the external identifier of the entity
     * 
     * @return a string representing the external identifier of the entity
     */
   
    List<IAttribute> getEntityAttributes();

    /**
     * Sets the list of attributes in this entity
     * 
     * @param attributes the list of attributes to be set in the entity
     */
    void setEntityAttributes(List<IAttribute> attributes);

    /**
     * Adds an attribute to the list of attributes in this entity
     * 
     * @param attribute the attribute to be added
     */
    void addAttribute(IAttribute attribute);

    /**
     * Gets the entity type
     * 
     * @return the entity type
     */
    IEntityType getEtype();

    /**
     * Sets the entity type
     * 
     * @param type the entity type
     */
    void setEtype(IEntityType type);
}
