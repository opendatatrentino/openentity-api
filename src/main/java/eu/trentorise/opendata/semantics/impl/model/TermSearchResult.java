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
package eu.trentorise.opendata.semantics.impl.model;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semtext.MeaningKind;
import eu.trentorise.opendata.semantics.services.model.ITermSearchResult;
import javax.annotation.concurrent.Immutable;

/**
 * Immutable implementation of ITermSearchResult
 * @author David Leoni
 */
@Immutable
public class TermSearchResult extends SearchResult implements ITermSearchResult {
    
    private final MeaningKind kind;

    public TermSearchResult(String URL, Dict name, MeaningKind kind) {
        super(URL, name);
        this.kind = kind;
    }

    public MeaningKind getKind() {
        return kind;
    }    
    
}
