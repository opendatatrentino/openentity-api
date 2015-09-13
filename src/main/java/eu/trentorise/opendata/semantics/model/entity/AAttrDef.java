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
 * The attribute definition stores information about the attributes, such as the
 * Name, the Concept and if the attribute is set or not.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 *
 */
@Value.Immutable
@BuilderStylePublic
@JsonSerialize(as = AttrDef.class)
@JsonDeserialize(as = AttrDef.class)
abstract class AAttrDef {

    /**
     * The URL of the attribute definition
     *
     */
    @Value.Default
    public String getURL() {
        return "";
    }

    /**
     * New way to get type stuff for the definition. Default attr type is
     * {@link AttrType#of()}
     *
     * @since 0.27
     */
    @Value.Default
    public AttrType getAttrType() {
        return AttrType.of();
    }

    /**
     * The attribute name in the given language
     *
     * @return the attribute name in the default languages if available
     */
    @Value.Default
    public Dict getName() {
        return Dict.of();
    }

    /**
     * The concept URL of the attribute definition
     */
    @Value.Default
    public String getConceptURL() {
        return "";
    }
 
    
}
