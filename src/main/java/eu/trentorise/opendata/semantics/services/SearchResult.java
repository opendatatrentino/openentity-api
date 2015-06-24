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

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import static com.google.common.base.Preconditions.checkNotNull;
import eu.trentorise.opendata.commons.Dict;
import java.io.Serializable;
import java.util.Objects;

import javax.annotation.concurrent.Immutable;
import org.immutables.value.Value;

/**
 *
 * Represents the result of a search for an object that has an URL and a
 * multilingual name, such as entity types and concepts.
 *
 * @author David Leoni
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSerialize(as = SearchResult.class)
@JsonDeserialize(as = SearchResult.class)
@Immutable
public class SearchResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final SearchResult INSTANCE = new SearchResult();

    private String id;    
    private Dict name;
        
    
    SearchResult() {
        this.id = "";
        this.name = Dict.of();
    }

    private SearchResult(String id, Dict name) {
        checkNotNull(id);
        checkNotNull(name);
        this.id = id;
        this.name = name;
    }
           
    /**
     * The id of the object (which may be an IRI)
     */
    @Value.Default
    public String getId(){
        return id;
    }

    
    /**
     * The object name, in the default locales for the ekb.
     */
    @Value.Default
    public Dict getName(){
        return name;
    }
    
    
    /**
     * Returns an empty search result.
     */
    public static SearchResult of() {
        return INSTANCE;
    }
    
    /**
     * Creates a new search result
     */
    public static SearchResult of(String id, Dict name){
        return new SearchResult(id, name);
    };

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SearchResult other = (SearchResult) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    

    
}
