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


import eu.trentorise.opendata.semantics.services.model.ISchemaCorrespondence;
import eu.trentorise.opendata.traceprov.data.ProvFile;
import java.util.List;

/**
 * Superceeds {@link ISemanticMatchingService}
 * @author David Leoni
 */
public interface ISchemaMatchingService {
    
    /**
     * Given a resource in tabular format guesses a target
     * entitytype and returns a matching between the source headers and the
     * attributes of the target entity type. 
     *
     * @param resourceContext the context of the resource as found in the catalog of provenance
     * @param tableResource the names of the resource columns     
     * @return - a list of scored correspondences ordered in decreasing order of scoring.
     */   
    List<ISchemaCorrespondence> matchSchemas(ProvFile provFile);
}
