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
package eu.trentorise.opendata.semantics.model.entity;

/**
 *
 * @author David Leoni
 */
import eu.trentorise.opendata.commons.Dict;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * Experimental entity, it's likely to be removed once we have a proper entity
 * implementation in the OpenEntity API. MinimalEntity can have all parameters
 * missing, and might have some additional info, like name and description, but
 * they can still be empty (but never null). Entity URL and etypeURL can be empty, but not null. Trying to set
 * any structureAttribute will throw an exception.
 *
 * @author David Leoni
 */
public class MinimalEntity implements IEntity {

    private String URL;

    private Dict name;

    private Dict description;
    
    private String etypeURL;

    /**
     *
     * @param URL if URL is not available put an empty string, otherwise an exception will be thrown
     * @param name if not available put an empty dict.
     * @param description if not available put an empty dict.
     * @param etypeURL if not available put an empty string
     */
    public MinimalEntity(String URL,
            Dict name,
            Dict description,
            String etypeURL) {

        if (URL == null){
            throw new IllegalArgumentException("null URL is not allowed! Use an empty string if needed!");
        }
        
        

        if (name == null){
            throw new IllegalArgumentException("null name is not allowed!");
        }
        
        if (description == null){
            throw new IllegalArgumentException("null description is not allowed!");
        }
        
        if (etypeURL == null){
            throw new IllegalArgumentException("null etype URL is not allowed! Use an empty string if needed!");
        }
        
        this.URL = URL;
        this.name = name;
        this.description = description;
        this.etypeURL = etypeURL;

    }

    private MinimalEntity() {
    }


    @Override
    public Dict getName() {
        return name;
    }

    @Override
    public Dict getDescription() {
        return description;
    }

    
    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public List<IAttribute> getAttributes() {
        return new ArrayList();
    }
      

    @Override
    public String getEtypeURL() {
        return etypeURL;
    }


    @Override
    public IAttribute getAttribute(String attrDefURL) {
        throw new UnsupportedOperationException("Getting strucutre attributes is not allowed in " + this.getClass().getSimpleName());
    }

}
