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
package eu.trentorise.opendata.semantics.model.knowledge;

import eu.trentorise.opendata.commons.Dict;

/**
 * Disgusting implementation for disgusting interface
 * @author David Leoni 
 */
public class Concept implements IConcept {

    private Dict name;
    private String URL;
    private Dict description;
    private Long GUID;

    public Concept() {
        name = Dict.of();
        description = Dict.of();
        URL = null;
        GUID = null;
    }
    
    
    
    @Override
    public Dict getName() {
        return name;
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public Long getGUID() {
        return GUID;
    }

    @Override
    public Dict getDescription() {
        return description;
    }

    public void setName(Dict name) {
        this.name = name;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setDescription(Dict description) {
        this.description = description;
    }

    public void setGUID(Long GUID) {
        this.GUID = GUID;
    }
    
    
    
}