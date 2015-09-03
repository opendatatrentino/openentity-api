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

/**
 * Represents an attribute value. Java objects that can be stored in an IValue
 * are reported in
 * {@link eu.trentorise.opendata.semantics.services.model.DataTypes} class.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * 
 * 
 *
 */
public interface IValue {

    /**
     * Gets the local ID of the value. 
     *
     * @return the ID of the value. It is null for synthetic values
     */
    @Nullable
    Long getLocalID();

    /**
     * Gets the value.
     *
     * @return the value as an Object. Can't be null. Java objects that can be returned are
     * reported in
     * {@link eu.trentorise.opendata.semantics.services.model.DataTypes} class.
     */
    Object getValue();

}
