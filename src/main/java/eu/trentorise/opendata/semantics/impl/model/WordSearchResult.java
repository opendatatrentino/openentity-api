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
package eu.trentorise.opendata.semantics.impl.model;

import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningKind;
import eu.trentorise.opendata.semantics.services.model.IWordSearchResult;
import javax.annotation.concurrent.Immutable;

/**
 * Immutable implementation of IWordSearchResult
 * @author David Leoni
 */
@Immutable
public class WordSearchResult extends SearchResult implements IWordSearchResult {
    
    private final MeaningKind kind;

    public WordSearchResult(String URL, IDict name, MeaningKind kind) {
        super(URL, name);
        this.kind = kind;
    }

    public MeaningKind getKind() {
        return kind;
    }    
    
}
