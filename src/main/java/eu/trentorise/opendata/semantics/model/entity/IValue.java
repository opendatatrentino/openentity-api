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

/**
 * Represents an attribute value. Java objects that can be stored in an IValue
 * are reported in
 * {@link eu.trentorise.opendata.semantics.services.model.DataTypes} class.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @date Jul 24, 2013
 *
 */
public interface IValue {

    /**
     * Gets the GUID of the value
     *
     * @return the GUID
     */
    Long getGUID();

    /**
     * Gets the value.
     *
     * @return the value as an Object. Java objects that can be returned are
     * reported in
     * {@link eu.trentorise.opendata.semantics.services.model.DataTypes} class.
     */
    Object getValue();

    /**
     * Sets the value
     *
     * @param value the value to be set. Java objects that can be used are
     * reported in
     * {@link eu.trentorise.opendata.semantics.services.model.DataTypes} class.
     */
    void setValue(Object value);
}
