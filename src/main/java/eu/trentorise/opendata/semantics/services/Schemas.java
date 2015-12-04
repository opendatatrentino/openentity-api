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
package eu.trentorise.opendata.semantics.services;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import static eu.trentorise.opendata.commons.validation.Preconditions.checkNotEmpty;

import eu.trentorise.opendata.semantics.Checker;
import eu.trentorise.opendata.semantics.DataTypes;
import eu.trentorise.opendata.semantics.model.entity.AttrDef;
import eu.trentorise.opendata.semantics.model.entity.Etype;
import eu.trentorise.opendata.traceprov.data.DcatMetadata;
import eu.trentorise.opendata.traceprov.tracel.PropertyPath;
import eu.trentorise.opendata.traceprov.tracel.TraceQueries;
import eu.trentorise.opendata.traceprov.tracel.Tracel;
import eu.trentorise.opendata.traceprov.types.TraceRefs;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Holds builders for various source paths for {@link AttrMapping}
 * 
 * @author David Leoni
 */
public class Schemas {

    /**
     * The origin path of property ids or names of a given source schema
     * 
     * @param idPath
     */
    public static final String SCHEMA_SOURCE = "schema";
    /**
     * The origin field for a property path for a field in a
     * {@link eu.trentorise.opendata.traceprov.data.DcatMetadata} metadata
     * object.
     *
     * @param propertyPath
     *            a path of property names like "dataset", "themes", "uri"
     * @return a path prepended with 'dcatMetadata'
     */
    public static final String DCAT_SOURCE = "dcat";

    /**
     * The origin field for a property path for a custom source.
     *
     * @param id
     *            An identifier of the referenced source.
     */
    public static final String CUSTOM_SOURCE = "custom";

    public static final ImmutableList<String> ALLOWED_SOURCES = ImmutableList.of(SCHEMA_SOURCE, DCAT_SOURCE,
            CUSTOM_SOURCE);

    /**
     * Checks provided path is a valid path for a mapping.
     * 
     * @param kind
     *            one of the values speficied in {@link #ALLOWED_SOURCES}
     * @throw InvalidArgumentException if path is not a valid
     *        {@link AttrMapping} path.
     */
    public static void checkMappingPath(List<String> path) {

       
    }

    /**
     * Returns the attribute definition id indicated by provided attribute path.
     * May fetch etypes from the server.
     * 
     * @path Attribute definition path made either by attr ids (preferred) or
     *       natural language names todo decide excepion to throw on error
     */
    public static AttrDef resolveAttrPath(List<String> path, String etypeId, IEtypeService ets) {

        checkNotEmpty(etypeId, "Invalid etypeId!");
        checkNotEmpty(path, "Invalid attribute path!");
        checkNotNull(ets);

        Etype curEtype = ets.readEtype(etypeId);
        AttrDef curAttrDef = null;

        for (String element : path) {

            checkNotEmpty(element, "Invalid element in attribute path!");

            curAttrDef = curEtype.attrDefByIdOrName(element);
            String datatype = curAttrDef.getType()
                                        .getDatatype();
            if (datatype.equals(DataTypes.STRUCTURE) || datatype.equals(DataTypes.ENTITY)) {
                curEtype = ets.readEtype(curAttrDef.getType()
                                                   .etypeId());
            }
        }
        return curAttrDef;
    }

    /**
     * Checks if provided schema correspondence complies with open entity specs.
     *
     * @param schemaMapping
     *            to check
     * @throws IllegalArgumentException
     *             if provided correspondence is not conformant to OpenEntity
     *             specs.
     */
    public void checkSchemaMapping(SchemaMapping schemaMapping) {
        if (schemaMapping == null) {
            throw new IllegalArgumentException("Schema mapping is null!");
        }

        try {
            Checker.checkEtype(schemaMapping.getTargetEtype());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid etype in schema mapping!", ex);
        }

        for (AttrMapping attrMapping : schemaMapping.getMappings()) {
            checkAttrMapping(attrMapping);
        }

    }

    /**
     *
     * Checks if provided path is valid
     *
     * @param prependedErrorMessage
     *            the exception message to use if the check fails; will be
     *            converted to a string using String.valueOf(Object) and
     *            prepended to more specific error messages.
     *
     * @throws IllegalArgumentException
     *             if provided path fails validation
     *
     * @return a valid immutable path
     */
    public static List<String> checkSourcePath(@Nullable Iterable<String> path, @Nullable Object prependedErrorMessage) {
        checkNotNull(path);
        if (Iterables.isEmpty(path)){
            return ImmutableList.of();
        }

        String kind = path.iterator().next();
        checkArgument(ALLOWED_SOURCES.contains(kind), "path kind must be one of %s, found instead: '%s'",
                ALLOWED_SOURCES, kind);
        if (!SCHEMA_SOURCE.equals(kind)) {
            checkNotEmpty(path, "Invalid id path!");

        }

        if (DCAT_SOURCE.equals(kind)) {
            Tracel.checkPathFromClass(DcatMetadata.class, PropertyPath.of(path));
        }

        for (String property : path) {
            checkNotEmpty(property, "Invalid property in path %s", path);
        }

        return ImmutableList.copyOf(path);
    }

    private void checkAttrMapping(AttrMapping attrMapping) {

        checkSourcePath(attrMapping.getSourcePath(), "Invalid attribute mapping!");
        // target path can be empty
    }
}
