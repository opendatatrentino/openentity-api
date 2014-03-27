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
import java.util.Locale;

/**
 * An entity is a representation of real world object.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@trentorise.eu>
 * @date Mar 27, 2014
 */
public interface IEntity extends IStructure {

    /**
     * Gets the name of the entity in the given language
     *
     * @param locale the language used to return the entity name
     * @return the name of the entity if translation is present, null otherwise.
     */
    String getName(Locale locale);
    
    /**
     * Sets the name in the given locale
     * @param locale language in which the name is expressed
     * @param name the provided name
     */
    void setName(Locale locale, String name);

    /**
     * Gets the description on the entity in the given language
     *
     * @param language the natural language
     * @return the description of the entity in the given language if translation is present, null otherwise.
     */
    String getDescription(Locale language);    
    
    
    /**
     * Sets the description on the entity in the given language
     *
     * @param language the language of the description
     * @param description the description of the entity in the given language      
     */
    String setDescription(Locale language, String description);    
    
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
    String getURI();

    /**
     * Gets the URL of the entity
     *
     * @return a string that holds the URL of the entity
     */
    String getURL();

    /**
     * Sets the URL of the entity
     *
     * @param url a string that holds the URL of the entity
     */
    void setURL(String url);


}
