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
import static eu.trentorise.opendata.commons.validation.Preconditions.checkScore;

import java.io.Serializable;
import org.immutables.value.Value;

/**
 * A mapping from a property path in a source file schema to a target etype
 * attribute path. Source schema may be generic and even don't have proper IRIs
 * as property ids, while target etype is required to be a well-defined schema.
 *
 * @author David Leoni
 */
@Value.Immutable
@SimpleStyle
@JsonSerialize(as = AttributeMapping.class)
@JsonDeserialize(as = AttributeMapping.class)
abstract class AAttributeMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * A reference to a property path in the source schema, which is intended to
     * be in commmon tree format - see
     * {@link eu.trentorise.opendata.traceprov.data.ProvFile}. For allowed
     * source properties see {@link SchemaPaths}.
     *
     */
    public abstract ImmutableList<String> getSourcePath();

    /**
     * A reference to a target etype attribute path of IRIs, like
     * ["http://some.entitybase.org/workplace","http://some.entitybase.org/address","http://some.entitybase.org/zip"]
     * *
     */
    public abstract ImmutableList<String> getTargetPath();

    /**
     * The optional confidence for the mapping in the range [0,1]. By default
     * it's 1.0
     */
    @Value.Default
    public double getScore() {
        return 1.0;
    }

    @Value.Check
    protected void check() {
        checkScore(getScore(), "Invalid score!");
        Mappings.checkSourcePath(getSourcePath(), "Invalid source path!");
    }
    

}
