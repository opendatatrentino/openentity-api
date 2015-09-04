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

import javax.annotation.Nullable;
import java.util.List;

/**
 *
 * A complex structure that is represented by the set of attribute.
 *
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * 
 */
public interface IStructure {
    
    /**
     * Gets the URL of the object
     *
     * @return a string that holds the URL of the object
     */
    String getUrl();


    /**
     * Gets the external identifier of the structure
     *
     * @return a string representing the external identifier of the structure
     */
    List<IAttribute> getStructureAttributes();

    /**
     * Gets the structure type
     * @return the structure type URL
     *
    */
    String getEtypeURL();

    /**
     * Gets an attribute from the structure.
     *
     * @param attrDefURL the URL of the attribute definition corresponding to the desired attribute.
     * @return the attribute corresponding to the given attribute def, if present. Throws NotFoundException otherwise.
     * @throws NotFoundException if attribute is not present.
     */
    
    IAttribute getAttribute(String attrDefURL);
}
