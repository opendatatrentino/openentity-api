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
package eu.trentorise.opendata.semantics.impl.model.entity;

/**
 *
 * @author David Leoni
 */
import eu.trentorise.opendata.semantics.model.entity.IAttribute;
import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.model.knowledge.impl.Dict;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/**
 *
 * Experimental entity, it's likely to be removed once we have a proper entity
 * implementation in the OpenEntity API. MinimalEntity can have all parameters
 * missing., and might have some additional info, like name and description, but
 * they can still be empty (but never null). EtypeURL can be null. Trying to set
 * any structureAttribute will throw an exception.
 *
 * @author David Leoni
 */
public class MinimalEntity implements IEntity {

    private String URL;

    private IDict name;

    private IDict description;

    @Nullable
    private String etypeURL;

    /**
     *
     * @param URL
     * @param name if null will be silently converted to empty dict.
     * @param description if null will be silently converted to empty dict.
     * @param etypeURL May be null
     */
    public MinimalEntity(@Nullable String URL,
            IDict name,
            IDict description,
            @Nullable String etypeURL) {

        this.URL = URL;

        if (name == null) {
            this.name = new Dict();
        } else {
            this.name = name;
        }

        if (description == null) {
            this.description = new Dict();
        } else {
            this.description = description;
        }

        this.etypeURL = etypeURL;

    }

    private MinimalEntity() {
    }

    @Override
    public Long getGUID() {
        throw new UnsupportedOperationException("Deprecated!"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IDict getName() {
        return name;
    }

    @Override
    public void setName(Locale locale, String name) {
        throw new UnsupportedOperationException("Setting name is not allowed in " + this.getClass().getSimpleName());
    }

    @Override
    public void setName(Locale locale, List<String> names) {
        throw new UnsupportedOperationException("Setting name is not allowed in " + this.getClass().getSimpleName());
    }

    @Override
    public IDict getDescription() {
        return description;
    }

    @Override
    public void setDescription(Locale language, String description) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public Long getLocalID() {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Nullable
    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public void setURL(String url) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public List<IAttribute> getStructureAttributes() {
        return new ArrayList();
    }

    @Override
    public void setStructureAttributes(List<IAttribute> attributes) {
        throw new UnsupportedOperationException("Setting structure attributes is not allowed in " + this.getClass().getSimpleName());
    }

    @Override
    public IEntityType getEtype() {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    @Nullable
    public String getEtypeURL() {
        return etypeURL;
    }

    @Override
    public void setEtype(IEntityType type) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public IAttribute getAttribute(String attrDefURL) {
        throw new UnsupportedOperationException("Getting strucutre attributes is not allowed in " + this.getClass().getSimpleName());
    }

}
