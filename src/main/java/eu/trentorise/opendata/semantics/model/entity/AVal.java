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
import eu.trentorise.opendata.commons.BuilderStylePublic;
import org.immutables.value.Value;

/**
 * Represents an attribute value. Java objects that can be stored in an Val
 * are reported in
 * {@link package eu.trentorise.opendata.semantics.services.DataTypes} class.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 *
 */
@Value.Immutable
@BuilderStylePublic
@JsonSerialize(as = Value.class)
@JsonDeserialize(as = Value.class)
abstract class AVal {

    /**
     * Gets the local ID of the value.
     *
     * @return the ID of the value. It is -1 for synthetic values
     */
    @Value.Default
    public long getLocalID() {
        return -1;
    }

    /**
     * Gets the value.
     *
     * @return the value as an Object. Can't be null. Java objects that can be
     * returned are reported in
     * {@link package eu.trentorise.opendata.semantics.services.DataTypes}
     * class. Defaults to empty string.
     */
    @Value.Default
    Object getObj() {
        return "";
    }

}
