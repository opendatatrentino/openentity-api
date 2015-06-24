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
import eu.trentorise.opendata.semtext.MeaningKind;
import org.immutables.value.Value;

/**
 * Represents a search result that can contain either an entity or a concept
 * 
 * @author David Leoni
 */
@Value.Immutable
@BuilderStylePublic
@JsonSerialize(as=TermSearchResult.class)
@JsonDeserialize(as=TermSearchResult.class)
public abstract class ATermSearchResult extends SearchResult {    
    
            
    @Value.Default    
    public MeaningKind getKind(){
        return MeaningKind.UNKNOWN;
    }
}
