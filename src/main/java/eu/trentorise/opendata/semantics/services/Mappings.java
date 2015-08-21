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
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import static eu.trentorise.opendata.commons.validation.Preconditions.checkNotEmpty;
import eu.trentorise.opendata.traceprov.data.DcatMetadata;
import eu.trentorise.opendata.traceprov.types.ProvRefs;

import java.util.Iterator;
import javax.annotation.Nullable;

/**
 * Holds builders for various source paths for {@link AttributeMapping}
 * @author David Leoni
 */
public class Mappings {
    
    public static final String SCHEMA_SOURCE = "schema";
    public static final String DCAT_METADATA_SOURCE = "dcatMetadata";
    public static final String CUSTOM_SOURCE = "custom";
    
   
    public static final ImmutableList<String> ALLOWED_SOURCES = 
            ImmutableList.of(SCHEMA_SOURCE, DCAT_METADATA_SOURCE, CUSTOM_SOURCE);
    
    
    private static ImmutableList<String> buildPath(String kind, Iterable<String> path){
        checkNotEmpty(path, "Invalid id path!");
        checkArgument(ALLOWED_SOURCES.contains(kind), "path kind must be one of %s, found instead: '%s'", ALLOWED_SOURCES, kind);
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        builder.add(kind);
        for (String property : path){
            checkNotEmpty(property, "Invalid property in path %s", path);
        }
        builder.addAll(path);
        return builder.build();
    }
    
    /**
     * Returns a path of property ids of a given source schema
     * @param idPath
     * @return 
     */
    
    public static ImmutableList<String> schemaSourcePath(Iterable<String> idPath) {
        /* todo
        for (String propertyId : idPath){
            for (PropertyDef propertyDef : schema.getPropertyDefs()){
                if (propertyDef.getId().equals());
            }            
        }*/
        return buildPath(SCHEMA_SOURCE, idPath);
    }    

    /**
     * Returns the property path for a field in a
     * {@link eu.trentorise.opendata.traceprov.data.DcatMetadata} metadata
     * object.
     *
     * @param propertyPath a path of property names like "dataset", "themes",
     * "uri"
     * @return a path prepended with 'dcatMetadata'
     */
    public static ImmutableList<String> dcatPath(Iterable<String> propertyPath) {
        ProvRefs.propertyRef(DcatMetadata.class, propertyPath);
        return buildPath(DCAT_METADATA_SOURCE, propertyPath);
    }

    /**
     * Returns a property path for a custom source.
     *
     * @param id An identifier of the referenced source.
     */
    public static ImmutableList<String> customPath(Iterable<String> propertyPath) {
        return buildPath(CUSTOM_SOURCE, propertyPath);
    }    
    
    /**
     *
     * Checks if provided path is valid
     *
     * @param prependedErrorMessage the exception message to use if the check
     * fails; will be converted to a string using String.valueOf(Object) and
     * prepended to more specific error messages.
     *
     * @throws IllegalArgumentException if provided path fails validation
     *
     * @return a valid immutable path
     */
    public static ImmutableList<String> checkSourcePath(Iterable<String> path, @Nullable Object prependedErrorMessage){
        Iterator<String> iter = path.iterator();
        if (iter.hasNext()){
            String selector = iter.next();
            switch (selector){
                
                case SCHEMA_SOURCE: {
                    return schemaSourcePath(Iterables.skip(path, 1));                    
                }
                
                case DCAT_METADATA_SOURCE: {
                    return dcatPath(Iterables.skip(path, 1));                    
                }
                
                case CUSTOM_SOURCE: {
                    return customPath(Iterables.skip(path, 1));
                }
                default : {
                    throw new IllegalArgumentException(String.valueOf(prependedErrorMessage) + " -- Reason: Input path should have first element starting with one of " + ALLOWED_SOURCES);
                }
            }
            
        } else {
            return ImmutableList.of();
        }
       
    } 
    
}
