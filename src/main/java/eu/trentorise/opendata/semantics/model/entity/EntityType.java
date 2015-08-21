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
package eu.trentorise.opendata.semantics.model.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.model.knowledge.Concept;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import java.util.List;
import java.util.Locale;

/**
 * Disgusting implementation for disgusting interface
 *
 * @author David Leoni
 */
public class EntityType implements IEntityType {

    private ImmutableMap<String, IAttributeDef> attributeDefs;
    private String URL;
    private Dict names;
    private IConcept concept;
    private ImmutableList<IUniqueIndex> uniqueIndexes;
    private String nameAttrDefURL;
    private String descrAttrDefURL;

    public EntityType() {
        URL = null;
        attributeDefs = ImmutableMap.of();
        names = Dict.of();
        concept = null;
        uniqueIndexes = ImmutableList.of();
        nameAttrDefURL = null;
        descrAttrDefURL = null;
    }

    /**
     * Creates a structure
     */
    public EntityType(String URL, String engName, String itName, List<IAttributeDef> attrs, String conceptURL) {
        this();
        names = Dict.builder().put(Locale.ENGLISH, engName).put(Locale.ITALIAN, itName).build();
        this.URL = URL;
        ImmutableMap.Builder<String, IAttributeDef> adBuilder = ImmutableMap.<String, IAttributeDef>builder();
        for (IAttributeDef ad : attrs) {
            adBuilder.put(ad.getURL(), ad);
        }
        this.attributeDefs = adBuilder.build();
        this.nameAttrDefURL = ""; // we have a structure
        this.descrAttrDefURL = ""; // we have a structure
        Concept con = new Concept();
        con.setURL(conceptURL);
        this.concept = con;
        
    }

    public EntityType(String URL, String engName, String itName, String conceptURL, String nameAttrDefURL, String descrAttrDefURL, List<IAttributeDef> attrs) {
        this();
        names = names.with(Locale.ENGLISH, engName).with(Locale.ITALIAN, itName);
        this.URL = URL;
        ImmutableMap.Builder<String, IAttributeDef> adBuilder = ImmutableMap.<String, IAttributeDef>builder();
        for (IAttributeDef ad : attrs) {
            adBuilder.put(ad.getURL(), ad);
        }
        this.nameAttrDefURL = nameAttrDefURL;
        this.descrAttrDefURL = descrAttrDefURL;
        Concept con = new Concept();
        con.setURL(conceptURL);
        this.concept = con;
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public Dict getName() {
        return names;
    }

    @Override
    public IConcept getConcept() {
        return concept;
    }

    @Override
    public void setConcept(IConcept concept) {
        this.concept = concept;
    }

    @Override
    public List<IAttributeDef> getAttributeDefs() {
        return ImmutableList.copyOf(attributeDefs.values());
    }


    @Override
    public List<IUniqueIndex> getUniqueIndexes() {
        return uniqueIndexes;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setName(Dict dict) {
        this.names = dict;
    }

    @Override
    public IAttributeDef getNameAttrDef() {
        if (nameAttrDefURL == null) {
            return null;
        }
        return attributeDefs.get(nameAttrDefURL);

    }

    @Override
    public IAttributeDef getDescriptionAttrDef() {
        if (descrAttrDefURL == null) {
            return null;
        }

        return attributeDefs.get(descrAttrDefURL);

    }

    @Override
    public String getConceptURL() {
        if (concept != null) {
            return concept.getURL();
        } else {
            return null;
        }
    }

    @Override
    public IAttributeDef getAttrDef(String URL) {
        return attributeDefs.get(URL);
    }

}
