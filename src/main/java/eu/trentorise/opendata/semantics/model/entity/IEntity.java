/* 
 * Copyright 2013-2015   Trento Rise   trentorise.eu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.trentorise.opendata.semantics.model.entity;

import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import java.util.List;
import java.util.Locale;

/**
 * An entity is a representation of real world object.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date June 8, 2014
 */
public interface IEntity extends IStructure {

	/**
	 * Gets the Globally Unique Identifier (GUID) for the entity
	 * @deprecated use getURL instead
	 * @return the Globally Unique Identifier (GUID) represented as Long
	 */
	Long getGUID();
    
    
	/**
	 * Gets the name of the entity.
	 *	 
	 * @return the name of the entity in the default languages if available. Returned dict can be empty.
	 */
	IDict getName();

	/**
	 * Sets the name in the given locale
	 * @param locale language in which the name is expressed
	 * @param name the provided name
         * @deprecated Don't want setters in interfaces
	 */
	void setName(Locale locale, String name);

	/**
	 * Sets names for the entity in the given locale
	 * @param locale language in which the name is expressed
	 * @param names the provided names
         * @deprecated Don't want setters in interfaces
	 */
	void setName(Locale locale, List<String> names);        
        
	/**
	 * Gets the description on the entity in the given language
	 *	 
	 * @return the description of the entity in the default languages if available. Returned dict can be empty.
	 */
	IDict getDescription();    


	/**
	 * Sets the description on the entity in the given language
	 *
	 * @param language the language of the description
	 * @param description the description of the entity in the given language    
         * @deprecated Don't want setters in interfaces
	 */
	void setDescription(Locale language, String description);    

}
