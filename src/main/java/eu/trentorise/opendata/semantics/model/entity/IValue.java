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

import javax.annotation.Nullable;

/**
 * Represents an attribute value. Java objects that can be stored in an IValue
 * are reported in
 * {@link eu.trentorise.opendata.semantics.services.model.DataTypes} class.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * 
 * 
 *
 */
public interface IValue {

    /**
     * Gets the local ID of the value. 
     *
     * @return the ID of the value. It is null for synthetic values
     */
    @Nullable
    Long getLocalID();

    /**
     * Gets the value.
     *
     * @return the value as an Object. Can't be null. Java objects that can be returned are
     * reported in
     * {@link eu.trentorise.opendata.semantics.services.model.DataTypes} class.
     */
    Object getValue();

    /**
     * Sets the value
     * @deprecated we don't need methods to change objects
     *
     * @param value the value to be set. Can't be null. Java objects that can be used are
     * reported in
     * {@link eu.trentorise.opendata.semantics.services.model.DataTypes} class.
     */
    void setValue(Object value);
}
