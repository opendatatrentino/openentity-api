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


import eu.trentorise.opendata.traceprov.data.DcatMetadata;
import eu.trentorise.opendata.traceprov.types.Type;
import java.util.List;

/**
 * Superceeds {@link ISemanticMatchingService}
 * @author David Leoni
 */
public interface ISchemaMatchingService {
    
    /**
     * Given a resource in tree format guesses a target
     * entitytype and returns a matching between the source schema elements and the
     * attributes of the target entity type. 
     *
     * @parameter dcatMetadata
     * @parameter schema The source schema. If unknown use {@link eu.trentorise.opendata.traceprov.types.AnyType#of() AnyType.of()}
     * @parameter data a sample of data organized as list of tree-like data, with objects belonging to one of the followong types: Map, Iterable, String, Number, null
     * 
     * @return - a list of scored schema to schema mappings ordered in decreasing order of relevance.
     */   
    List<SchemaMapping> matchSchemas(DcatMetadata dcatMetadata, Type schema, Iterable data);
}
