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

import eu.trentorise.opendata.semantics.model.entity.AttrDef;
import eu.trentorise.opendata.semantics.model.entity.Etype;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Locale;

/**
 * Entity type services allow reading and modifying entity types
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 *
 */
public interface IEtypeService {

    /**
     * Reads all entity types available in the system
     *
     * @return list of all entity types in the system
     */
    List<Etype> readAllEtypes();

    /**
     * Returns the entity types with the given URLs
     *
     * @param URLs a list of URLs for entity types
     * @return the entity types. For entity types that were not found, the
     * corresponding item in the list will contain null.
     */
    List<Etype> readEtypes(Iterable<String> URLs);

    /**
     * Returns a list of possible entity types with name similar to provided
     * partial name.
     *
     * @param partialName a partial entity type name. It is assumed to be in one
     * of the default locales of the ekb.
     * @return a list of candidate entity types, ordered by probability. The
     * first one is the most probable.
     */
    List<SearchResult> searchEtypes(String partialName, Locale locale);

    /**
     * Return the entity type by the given URL
     *
     * @param URL The URL of the entity type
     * @return the entity type, null if not found.
     */
    @Nullable
    Etype readEtype(String URL);

    /**
     * Return the attribute definition by the given URL
     *
     * @param URL The URL of the attribute definition
     * @return the attribute definition, null if not found.
     */
    @Nullable
    public AttrDef readAttrDef(String url);


    /**
     * Returns the parent of all structures
     *
     * @return the parent of all structures
     * @see #getRootEtype()
     */
    Etype readRootStructure();


    /**
     * Returns the parent of all etypes.
     *
     * @return the parent of all etypes. Must inherit from value returned by
     * {@link #getRootStructure()}
     * @see #getRootStructure()
     */
    Etype readRootEtype();

}
