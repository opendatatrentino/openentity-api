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

import eu.trentorise.opendata.commons.BuilderStylePublic;
import eu.trentorise.opendata.commons.TodUtils;
import eu.trentorise.opendata.semantics.Checker;

import static eu.trentorise.opendata.commons.validation.Preconditions.checkScore;

import java.io.Serializable;
import java.util.List;
import org.immutables.value.Value;

/**
 * A mapping from a property path in a source file schema to a target etype
 * attribute path. Source schema may be generic and even don't have proper IRIs
 * as property ids, while target etype is required to be a well-defined schema.
 *
 * @author David Leoni
 */
@Value.Immutable
@BuilderStylePublic
@JsonSerialize(as = AttrMapping.class)
@JsonDeserialize(as = AttrMapping.class)
abstract class AAttrMapping implements Serializable, Comparable<AttrMapping> {

    private static final long serialVersionUID = 1L;

    /**
     * A reference to a property path in the source schema, which is intended to
     * be in common tree format - see
     * {@link eu.trentorise.opendata.traceprov.data.TraceFile}. For allowed
     * root source properties see {@link Schemas#ALLOWED_SOURCES}.
     *s     
     *
     */    
    public abstract List<String> getSourcePath();

    /**
     * A reference to a target etype attribute path made of attribute names
     * preferably in canonical format, i.e. ["workingPlace","address","zip"] .
     * Can be empty.
     */
    public abstract List<String> getTargetPath();

    /**
     * todo javadoc
     */
    public static AttrMapping of(Iterable<String> sourcePath, Iterable<String> targetPath, double score) {
        return AttrMapping.builder()
                          .addAllSourcePath(sourcePath)
                          .addAllTargetPath(targetPath)
                          .setScore(score)
                          .build();
    }

    /**
     * The optional confidence for the mapping in the range [0,1] with tolerance
     * {@link TodUtils#TOLERANCE}. By default it's 1.0
     */
    @Value.Default
    public double getScore() {
        return 1.0;
    }

    @Value.Check
    protected void check() {
        checkScore(getScore(), "Invalid attribute mapping!");
        Schemas.checkSourcePath(getSourcePath(), "Invalid attribute mapping!");
    }

    @Override
    public int compareTo(AttrMapping other) {
        double diff1 = this.getScore() - other.getScore();
        if (diff1 != 0.0) {
            if (diff1 > 0) {
                return +1;
            } else {
                return -1;
            }
        }
        int diff2 = getSourcePath().toString()
                                   .compareTo(other.getSourcePath()
                                                   .toString());
        if (diff2 != 0) {
            return diff2;
        }

        int diff3 = getTargetPath().toString()
                                   .compareTo(other.getTargetPath()
                                                   .toString());
        if (diff3 != 0) {
            return diff3;
        } else {
            return 0;
        }
    }

}
