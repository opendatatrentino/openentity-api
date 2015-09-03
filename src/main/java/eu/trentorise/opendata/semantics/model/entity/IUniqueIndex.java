/* 
 * Copyright 2015 TrentoRISE   (trentorise.eu).
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

import java.util.List;

/**
 * The unique indexes are a set of attribute definitions to be used in entity
 * matching
 * 
 */
public interface IUniqueIndex {

    /**
     * Gets the URL of the unique index
     *
     * @return a string that holds the URL of the unique index
     */
    String getURL();

    /**
     * Gets the attribute definitions for the unique index
     *
     * @return the attribute definitions for the unique index
     */
    List<String> getAttributeDefURLs();


}
