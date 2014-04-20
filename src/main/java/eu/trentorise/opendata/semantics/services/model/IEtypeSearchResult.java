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
package eu.trentorise.opendata.semantics.services.model;

import eu.trentorise.opendata.semantics.model.knowledge.IDict;

/**
 * 
 * Represents the result of an entity type search.
 * 
 * @author David Leoni
 * @date 20 Apr 2014
 */
public interface IEtypeSearchResult {

    /**
     * Gets the URL of the etype
     * 
     * @return the etype URL
     */
    String getURL();

    /**
     * Gets the entity type name
     * 
     * @return the entity type name, in the default locales for the ekb.
     */
    IDict getName();
}
