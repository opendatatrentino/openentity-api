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

import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import java.util.Locale;

/**
 * An entity is a representation of real world object.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date Apr 7, 2014
 */
public interface IEntity extends IStructure {

	/**
	 * Gets the name of the entity.
	 *	 
	 * @return the name of the entity in the default languages if available
	 */
	IDict getName();

	/**
	 * Sets the name in the given locale
	 * @param locale language in which the name is expressed
	 * @param name the provided name
	 */
	void setName(Locale locale, String name);

	/**
	 * Gets the description on the entity in the given language
	 *	 
	 * @return the description of the entity in the default languages if available
	 */
	IDict getDescription();    


	/**
	 * Sets the description on the entity in the given language
	 *
	 * @param language the language of the description
	 * @param description the description of the entity in the given language      
	 */
	void setDescription(Locale language, String description);    

}
