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
package eu.trentorise.opendata.semantics.services.model;

/**
 * The status of a reconciliation operation against a single entity
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date May 13, 2014
 */
public enum AssignmentResult {
    /** Entity to reconcile was not found in the database and has sufficient attributes to get assigned an id *//** Entity to reconcile was not found in the database and has sufficient attributes to get assigned an id */
    NEW,
    /** Entity to reconcile doesn't have attributes that allow to uniquely identify it with sufficient certainty */
    INVALID,
    /** Entity to reconcile can be matched against one or more entities in the ekb */
    REUSE
}
