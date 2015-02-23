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
package eu.trentorise.opendata.semantics.model.knowledge;

import eu.trentorise.opendata.semantics.services.model.ISearchResult;
import java.util.List;

/**
 * A concept is a language independent element that gives meaning
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Sergey Kanshin <kanshin@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date Apr 11, 2014
 */
public interface IConcept {
    
    /**
     * Gets the name of the concept
     * @return the name of the concept in the default languages if available
     */    
    IDict getName();

    /**
     * Gets the URL of the concept
     *
     * @return the concept URL as string
     */
    String getURL();

    /**
     * Gets the global id of the concept
     * @deprecated use getURL instead
     * @return the concept global id
     */
    Long getGUID();
    
    /**
     * Gets the description of the concept
     * @return the description of the concept in the default languages if available
     */
    IDict getDescription();
    
}
