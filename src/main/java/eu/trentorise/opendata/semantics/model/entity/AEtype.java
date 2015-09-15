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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eu.trentorise.opendata.commons.BuilderStylePublic;
import eu.trentorise.opendata.commons.Dict;
import static eu.trentorise.opendata.commons.validation.Preconditions.checkNotEmpty;
import eu.trentorise.opendata.semantics.exceptions.OpenEntityNotFoundException;
import eu.trentorise.opendata.traceprov.types.UniqueIndex;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.immutables.value.Value;

/**
 * The entity type defines the attributes that the entity can have
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 *
 */
@Value.Immutable
@BuilderStylePublic
@JsonSerialize(as = Etype.class)
@JsonDeserialize(as = Etype.class)
abstract class AEtype {

    /**
     * The name of the entity type
     *
     * @return the name of the entity type in the default languages if
     * available. Returned dict can be empty.
     */
    @Value.Default
    public Dict getName() {
        return Dict.of();
    }

    /**
     * The description of the entity type
     *
     * @return the description of the entity type in the default languages if
     * available. Returned dict can be empty.
     */
    @Value.Default
    public Dict getDescription() {
        return Dict.of();
    }

    /**
     * The concept URL of the entity type
     *
     */
    @Value.Default
    public String getConceptId() {
        return "";
    }

    /**
     * The attribute definitions for the entity type
     *
     */
    public abstract Map<String, AttrDef> getAttrDefs();

    /**
     * Gets the unique indexes
     *
     * @return the unique indexes
     */
    public abstract List<UniqueIndex> getUniqueIndexes();

    /**
     * Gets the URL of the entity type
     *
     * @return the URL of the entity type
     */
    @Value.Default
    public String getId() {
        return "";
    }

    /**
     * Returns the attribute def used for name.
     *
     * @return the attribute def used for name if the entity type represents an
     * entity. Returns the empty string otherwise.
     * @see #nameAttrDef()
     */
    @Value.Default
    public String getNameAttrDefId() {
        return "";
    }

    /**
     * Returns the attribute def used for name.
     *
     * @return the attribute def used for name if the entity type represents an
     * entity. If it represents a struct or it is not found, throws
     * {@link OpenEntityNotFoundException}.
     * @throws OpenEntityNotFoundException
     */
    public AttrDef nameAttrDef() {
        AttrDef ret = getAttrDefs().get(getNameAttrDefId());
        if (ret == null) {
            throw new OpenEntityNotFoundException("Couldn't find name attr def with URL " + getNameAttrDefId() + " in etype " + getId());
        }
        return ret;
    }

    /**
     * True if the etype represents just a struct, false if it represents
     * entities. False by default.
     */
    @Value.Default
    public boolean isStruct() {
        return false;
    }

    /**
     * Returns the attribute def used for description.
     *
     * @return the attribute def used for description if the entity type
     * represents an entity. Returns the empty string otherwise.
     *
     * @see #descrAttrDef()
     */
    @Value.Default
    public String getDescrAttrDefId() {
        return "";
    }

    /**
     * Returns the attribute def used for name.
     *
     * @return the attribute def used for name if the entity type represents an
     * entity. If it represents a struct or it is not found, throws
     * {@link OpenEntityNotFoundException}.
     * @throws OpenEntityNotFoundException
     */
    public AttrDef descrAttrDef() {
        AttrDef ret = getAttrDefs().get(getDescrAttrDefId());
        if (ret == null) {
            throw new OpenEntityNotFoundException("Couldn't find description attr def with URL " + getDescrAttrDefId() + " in etype " + getId());
        }
        return ret;
    }

    /**
     * Returns the attribute def indicated by the provided URL.
     *
     * @return Returns the attribute def indicated by the provided URL if it
     * exists, throws exception otherwise.
     *
     * @throws
     * eu.trentorise.opendata.semantics.exceptions.OpenEntityNotFoundException
     * if attribute is not found.
     */
    public AttrDef getAttrDef(String URL) {
        checkNotEmpty(URL, "Invalid url!");

        AttrDef ret = getAttrDefs().get(URL);
        if (ret == null) {
            throw new OpenEntityNotFoundException("Couldn't find attribute definition with URL " + URL + " in etype with URL " + getId());
        } else {
            return ret;
        }
    }

    /**
     * Creates a struct
     */
    public static Etype ofStruct(String URL, String engName, String itName, List<AttrDef> attrs, String conceptURL) {
        Etype.Builder b = Etype.builder();
        b.setName(Dict.builder().put(Locale.ENGLISH, engName).put(Locale.ITALIAN, itName).build());
        b.setId(URL);

        for (AttrDef ad : attrs) {
            b.putAttrDefs(ad.getId(), ad);
        }

        b.setConceptId(conceptURL);
        return b.build();
    }

    public static Etype of(String URL, String engName, String itName, String conceptURL, String nameAttrDefURL, String descrAttrDefURL, List<AttrDef> attrs) {

        Etype.Builder b = Etype.builder();
        b.setName(Dict.builder().put(Locale.ENGLISH, engName).put(Locale.ITALIAN, itName).build());
        b.setId(URL);
        for (AttrDef ad : attrs) {
            b.putAttrDefs(ad.getId(), ad);
        }
        b.setNameAttrDefId(nameAttrDefURL);
        b.setDescrAttrDefId(descrAttrDefURL);

        b.setConceptId(conceptURL);
        return b.build();
    }

}
