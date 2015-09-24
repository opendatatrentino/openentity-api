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
package eu.trentorise.opendata.semantics.services;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eu.trentorise.opendata.commons.BuilderStylePublic;
import eu.trentorise.opendata.semantics.model.entity.Entity;

import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/**
 * A reconciliation result, expressed as a set of candidate entities or a new
 * one if none was found during the matching.
 *
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * 
 */
@Value.Immutable
@BuilderStylePublic
@JsonSerialize(as = IdResult.class)
@JsonDeserialize(as = IdResult.class)
abstract class AIdResult {

    /**
     * Gets the result of the id matching. By default it's
     * {@link AssignmentResult#INVALID}
     *
     * @return the assignment result.
     */
    @Value.Default
    public AssignmentResult getAssignmentResult() {
	return AssignmentResult.INVALID;
    }

    /**
     * Gets the selected entity.
     *
     * @return an entity if getAssignmentResult is REUSE or NEW, null otherwise.
     *         If getAssignmentResult is REUSE, returns the 'best' candidate
     *         among the possible matching entities. If the assignment result is
     *         NEW, a deep copy of the original entity is returned containing an
     *         assigned URL. This new entity shoudln't be stored on the server
     *         by identity services, though. In order to store it, call
     *         {@link eu.trentorise.opendata.semantics.services.EntityService#createEntity(eu.trentorise.opendata.semantics.model.entity.Entity)}
     *         with the new entity.
     */
    @Nullable
    public abstract Entity getResultEntity();

    /**
     * A set of possible suggested entities in order of preference, with the
     * first one being the best match.
     *
     * @return a list of possible suggested entities if getAssignmentResult is
     *         REUSE. If assignment result is NEW, returns a list containing
     *         only one new entity with newly assigned url. Otherwise, returns
     *         an empty list.
     */
    public abstract List<Entity> getEntities();


}
