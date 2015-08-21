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
import eu.trentorise.opendata.commons.SimpleStyle;
import eu.trentorise.opendata.semantics.model.entity.EntityType;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import java.util.List;
import org.immutables.value.Value;

/**
 * 
 * @author David Leoni
 */
@Value.Immutable
@SimpleStyle
@JsonSerialize(as = SchemaMapping.class)
@JsonDeserialize(as = SchemaMapping.class)
abstract class ASchemaMapping implements Comparable<SchemaMapping> {

    /**
     * Gets a list of mappings from source properties to target attributes.
     * First mappings will have highest score.
     *
     */
    public abstract List<AttributeMapping> getMappings();

    /**
     *
     * Returns the target entity type
     */
    @Value.Default
    public IEntityType getTargetEtype() {
        return new EntityType(); 
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

    @Override
    public int compareTo(SchemaMapping other) {
        double diff1 = this.getScore() - other.getScore();
        if (diff1 != 0.0) {
            if (diff1 > 0) {
                return +1;
            } else {
                return -1;
            }
        }
        int diff2 = getMappings().toString().compareTo(other.getMappings().toString());
        if (diff2 != 0) {
            return diff2;
        }

        if (getTargetEtype() != null) {
            int diff3 = getTargetEtype().toString().compareTo(other.getTargetEtype().toString());
            if (diff3 != 0) {
                return diff3;
            } 
        } 
        return 0;
        
    }

}
