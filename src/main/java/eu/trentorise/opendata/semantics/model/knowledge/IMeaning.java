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
package eu.trentorise.opendata.semantics.model.knowledge;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Represents a meaning along with its probability. Implementations of this
 * interface must be immutable and implement equals() and hashCode() methods.
 * Equality is checked only considering the URL and meaning kind.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 09 Sept 2014
 */
@Immutable
public interface IMeaning extends Comparable<IMeaning> {

    /**
     * Equality must only check the URL
     */
    @Override
    boolean equals(Object o);

    /**
     * Equality must only check the URL
     */
    @Override
    int hashCode();

    /**
     * @return the probability of this meaning
     */
    double getProbability();

    /**
     * @return the URL of the entity or the concept represented by this meaning.
     * If no URL was assigned to meaning, returns null.
     */
    @Nullable
    String getURL();

    /**
     * Gets the name of the entity or concept represented by this meaning
     *
     * @return the name of the entity or concept represented by the meaning. The
     * dictionary can be empty.
     */
    IDict getName();

    /**
     * Gets the kind of the meaning.
     *
     * @return the kind of the meaning, which can either be an entity or a
     * concept
     */
    MeaningKind getKind();

}
