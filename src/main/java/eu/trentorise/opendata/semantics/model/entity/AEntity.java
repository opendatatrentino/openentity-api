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
import eu.trentorise.opendata.commons.Dict;
import org.immutables.value.Value;

/**
 * An entity is a representation of real world object.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 *
 */
@Value.Immutable
@BuilderStylePublic
@JsonSerialize(as = Entity.class)
@JsonDeserialize(as = Entity.class)
abstract class AEntity extends AStructure {

    /**
     * Gets the name of the entity.
     *
     * @return the name of the entity in the default languages if available.
     * Returned dict can be empty.
     */
    @Value.Default
    public Dict getName() {
        return Dict.of();
    }

    /**
     * Gets the description on the entity in the given language
     *
     * @return the description of the entity in the default languages if
     * available. Returned dict can be empty.
     */
    @Value.Default
    public Dict getDescription() {
        return Dict.of();
    }

   

}
