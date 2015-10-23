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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static eu.trentorise.opendata.commons.validation.Preconditions.checkNotEmpty;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.exceptions.OpenEntityNotFoundException;
import java.util.Map;
import org.immutables.value.Value;

import com.google.common.collect.ImmutableMap;

/**
 *
 * A complex struct that is represented by the set of attribute.
 *
 * todo think about specializations: Name DateTime Interval Duration Location
 *
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 *
 */
public abstract class AStruct {

    /**
     * Gets the URL of the object
     *
     * @return a string that holds the URL of the object
     */
    @Value.Default
    public String getId() {
	return "";
    }

    /**
     * A map of attribute definition url to the corresponding attribute.
     * Iteration order is predictable and repsects insertion order.
     */
    public abstract Map<String, Attr> getAttrs();

    /**
     * Retrieves attribute with given attribute definition locator in provided
     * {@code entity}
     * 
     * @param attrDefLocator
     *            either the id or the natural language name in any locale
     * @param etype
     */
    public Attr attr(String attrDefLocator, Etype etype) {

	checkNotEmpty(attrDefLocator, "Invalid attribute definition locator!");
	checkNotNull(etype);

	checkArgument(getEtypeId().equals(etype.getId()), "entity etype id %s is different from provided etype %s!",
		getEtypeId(), etype.getId());

	Map<String, Attr> attrs = getAttrs();
	Attr attr = attrs.get(attrDefLocator); // let's try it as url
	if (attr == null) {
	    AttrDef selectedAttrDef = etype.attrDefByName(attrDefLocator);
	    Attr attr2 = getAttrs().get(selectedAttrDef.getId());
	    if (attr2 == null) {
		throw new OpenEntityNotFoundException("Couldn't find any attribute for attribute definition "
			+ selectedAttrDef + " searched with locator " + attrDefLocator + " in etype " + etype.getId());
	    } else {
		return attr2;
	    }
	} else {
	    return attr;
	}

    }

    /**
     * Gets the struct type url.
     *
     * @see #etypeId()
     */
    @Value.Default
    public String getEtypeId() {
	return "";
    }

    /**
     * Gets the struct type
     *
     * @see #getEtypeId()
     * @throw OpenEntityNotFoundException if etype is not present
     */
    public String etypeId() {
	if (getEtypeId().isEmpty()) {
	    throw new OpenEntityNotFoundException("Tried to get unset etypeId from Struct!");
	} else {
	    return getEtypeId();
	}
    }

    /**
     * Gets an attribute from the struct given its attribute definition id.
     *
     * @param attrDefURL
     *            the URL of the attribute definition corresponding to the
     *            desired attribute.
     * @return the attribute corresponding to the given attribute def, if
     *         present.
     * @throws eu.trentorise.opendata.semantics.exceptions.
     *             OpenEntityNotFoundException if not found.
     */
    public Attr attr(String attrDefURL) {
	checkNotEmpty(attrDefURL, "Invalid url!");

	Attr ret = getAttrs().get(attrDefURL);
	if (ret == null) {
	    throw new OpenEntityNotFoundException("Couldn't find attribute with attribute definition URL " + attrDefURL
		    + " in struct with URL " + getId());
	} else {
	    return ret;
	}
    }



}
