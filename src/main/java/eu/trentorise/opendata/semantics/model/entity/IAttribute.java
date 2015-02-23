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

import javax.annotation.Nullable;
import java.util.List;

/**
 * An attribute is used in entities. It holds one or more values. null values are not allowed.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date June 8, 2014
 */
public interface IAttribute {

    /**
     * Gets the Local identifier for the attribute
     *
     * @return the local identifier represented as Long. May be null for synthetic attributes.
     */
    @Nullable
    Long getLocalID();

    /**
     * Gets the attribute definition of the attribute
     * @deprecated use {@link #getAttrDef() instead}
     * @return the attribute definition of the attribute
     */
    IAttributeDef getAttributeDefinition();
    
    
    /**
     * Gets the attribute definition of the attribute
     *
     * @return the attribute definition of the attribute
     */
    IAttributeDef getAttrDef();    

    /**
     * Sets the attribute definition of the attribute
     * @deprecated there's no need for methods that mutate values in interfaces
     * 
     * @param ad the attribute definition of the attribute
     */
    void setAttributeDefinition(IAttributeDef ad);

    /**
     * Adds a value to the attribute
     * @deprecated there's no need for methods that mutate values in interfaces
     * 
     * @param value the value to be added
     */
    void addValue(IValue value);

    /**
     * Removes a value from the attribute
     * @deprecated there's no need for methods that mutate values in interfaces
     * @param valueID the local ID of the value to be removed
     */
    void removeValue(long valueID);

    /**
     * Returns all the value of the attribute
     *
     * @return immutable list of all values of the attribute. It can be empty. Null values are not allowed.
     */
    List<IValue> getValues();

    /**
     * Gets the first value of the attribute
     * @return the first value of the attribute
     *
     * @throws java.util.NoSuchElementException if no value is present
     */
    IValue getFirstValue();

    /**
     * Gets the number of values in the attribute. Can be zero.
     *
     * @return the number of values in the attribute
     */
    Long getValuesCount();
}
