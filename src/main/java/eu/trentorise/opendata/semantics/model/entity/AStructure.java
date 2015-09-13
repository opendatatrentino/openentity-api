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

import static eu.trentorise.opendata.commons.validation.Preconditions.checkNotEmpty;
import eu.trentorise.opendata.semantics.exceptions.OpenEntityNotFoundException;
import java.util.Map;
import org.immutables.value.Value;

/**
 *
 * A complex structure that is represented by the set of attribute.
 *
 * todo think about specializations: Name DateTime Interval Duration Location
 *
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 *
 */
public abstract class AStructure {

    /**
     * Gets the URL of the object
     *
     * @return a string that holds the URL of the object
     */
    @Value.Default
    public String getUrl() {
        return "";
    }

    /**
     * Gets the external identifier of the structure
     *
     * @return a string representing the external identifier of the structure
     */
    public abstract Map<String, Attr> getAttrs();

    /**
     * Gets the structure type
     *
     * @return the structure type URL
     *
     */
    @Value.Default
    public String getEtypeURL() {
        return "";
    }

    /**
     * Gets an attribute from the structure.
     *
     * @param attrDefURL the URL of the attribute definition corresponding to
     * the desired attribute.
     * @return the attribute corresponding to the given attribute def, if
     * present.
     * @throws
     * eu.trentorise.opendata.semantics.exceptions.OpenEntityNotFoundException
     * if not found.
     */
    public Attr getAttr(String attrDefURL) {
        checkNotEmpty(attrDefURL, "Invalid url!");

        Attr ret = getAttrs().get(attrDefURL);
        if (ret == null) {
            throw new OpenEntityNotFoundException("Couldn't find attribute with attribute definition URL " + attrDefURL + " in structure with URL " + getUrl());
        } else {
            return ret;
        }
    }
}
