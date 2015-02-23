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
package eu.trentorise.opendata.semantics.services.model;

import eu.trentorise.opendata.semantics.model.knowledge.IDict;

/**
 *
 * Represents the result of a search for an object that has an URL and a
 * multilingual name, such as entity types and concepts.
 *
 * @author David Leoni
 * @date 28 Aug 2014
 */
public interface ISearchResult {

    /**
     * Gets the URL of the object
     *
     * @return the object URL
     */
    String getURL();

    /**
     * Gets the object name
     *
     * @return the object name, in the default locales for the ekb.
     */
    IDict getName();
}
