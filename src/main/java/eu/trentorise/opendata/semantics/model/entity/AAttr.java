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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableList;
import eu.trentorise.opendata.commons.BuilderStylePublic;
import eu.trentorise.opendata.semantics.Checker;
import eu.trentorise.opendata.semantics.exceptions.OpenEntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.immutables.value.Value;

/**
 * An attribute is used in entities. It holds one or more values. null values
 * are not allowed.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 *
 */
@Value.Immutable
@BuilderStylePublic
@JsonSerialize(as = Attr.class)
@JsonDeserialize(as = Attr.class)
abstract class AAttr {

    /**
     * The local identifier represented as Long. May be null for synthetic
     * attributes. By default it's -1.
     */
    @Value.Default
    public long getLocalID() {
        return -1;
    }

    /**
     * The attribute definition URL of the attribute
     *
     */
    @Value.Default
    public String getAttrDefId() {
        return "";
    }

    /**
     * Returns all the value of the attribute
     *
     * @return immutable list of all values of the attribute. It can be empty.
     * Null values are not allowed.
     */
    public abstract List<Val> getValues();

    /**
     * Gets the first value of the attribute
     *
     * @return the first value of the attribute
     *
     * @throws
     * eu.trentorise.opendata.semantics.exceptions.OpenEntityNotFoundException
     * if no value is present
     */
    public Val firstValue() {
        if (getValues().isEmpty()) {
            throw new OpenEntityNotFoundException("Tried to get first value from attribute, but there is none!");
        }
        return getValues().get(0);
    }

    /**
     * Returns a list of objects contained inside provided attribute values.
     */
    public ImmutableList asRawObjectList() {
        List rawValues = new ArrayList();
        for (Val val : getValues()) {
            rawValues.add(val.getObj());
        }
        return ImmutableList.copyOf(rawValues);
    }

    /**
     * Creates an Attr out of provided object(s).
     *
     * @param obj the object to store in Val. If it is a collection a new value
     * for each object will be created. *Notice no other casts are done*
     */
    public static Attr ofObject(AttrDef attrDef, Object obj) {
	
	
	
        Attr.Builder b = Attr.builder();
        b.setAttrDefId(attrDef.getId());

        if (obj instanceof Collection) {

            for (Object subObj : (Collection) obj) {
        	Checker.checkObj(subObj, attrDef.getType(), true);
                b.addValues(Val.builder().setObj(subObj).build());
            }
        } else {
            Checker.checkObj(obj, attrDef.getType(), true);
            b.setAttrDefId(attrDef.getId()).addValues(Val.builder().setObj(obj).build()).build();
        }
        return b.build();
    }
}
