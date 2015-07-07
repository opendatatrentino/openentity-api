/*
 * Copyright 2015 Trento Rise.
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
package eu.trentorise.opendata.semantics.services;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableList;
import eu.trentorise.opendata.commons.SimpleStyle;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import org.immutables.value.Value;

/**
 *
 * @author David Leoni
 */
@Value.Immutable
@SimpleStyle
@JsonSerialize(as = SchemaMapping.class)
@JsonDeserialize(as = SchemaMapping.class)
public abstract class ASchemaMapping {

    /**
     * Gets a list of mappings from source properties to target attributes.
     * First mappings will have highest score.
     *
     */
    public abstract ImmutableList<AttributeMapping> getMappings();

    /**
     *
     * Returns the target entity type
     */
    @Value.Default
    public IEntityType getTargetEtype() {
        return null; // don't like this, but don't want to make an implementation right now..
    }

    /**
     * Gets the score assigned to the mapping. It ranges from 0.0 (worst score)
     * to 1.0 (best score). By default returns 1.0.
     *
     * @see #OdtUtils#TOLERANCE
     */
    @Value.Default
    public double getScore() {
        return 1.0;
    }

}
