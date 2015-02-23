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

/**
 * The context of a resource coming from a catalog such as i.e. Ckan.
 * 
 * @author David Leoni <david.leoni@unitn.it>
 * @date Mar 20, 2014
 */
public interface IResourceContext {

    /**
     * Gets the name of the resource as found in the catalog.
     * @return the name of the resource
     */
    String getResourceName();
    
    /**
     * Gets the resource filename. Notice sometimes it can be meaningless, like 'g83_f3.dat' 
     * @return the resource filename
     */
    String getResourceFilename();
    
    /**
     * Gets the name of the dataset which contains the resource.
     * @return the dataset name
     */
    String getDatasetName();
    
}
