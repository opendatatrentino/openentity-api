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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eu.trentorise.opendata.commons.BuilderStylePublic;
import org.immutables.value.Value;

/**
 * This class is needed to generate implementation of struct
 * @author David Leoni
 */
@Value.Immutable
@BuilderStylePublic
@JsonSerialize(as = Struct.class)
@JsonDeserialize(as = Struct.class)
abstract class AbstractStruct extends AStruct {

    /**
     * Creates immutable copy of {@link AStruct}.
     * Uses accessors to get values to initialize immutable instance.
     * If an instance is already immutable, it is returned as is.
     * @param instance instance to copy
     * @return copied immutable Struct instance
     */
    public static Struct copyOf(AStruct instance) {
      if (instance instanceof Struct) {
        return (Struct) instance;
      }
      return Struct.builder()
          .from(instance)
          .build();
    }
    
    /**
     * He who needs a custom builder is in trouble.
     * 
     * David Leoni
     *
     */
    public static abstract class Builder {

	public abstract Struct.Builder putAttrs(String key, Attr value);

	/**
	 * Wraps the object into the hideous Attr and Val stuff
	 * 
	 * @param obj
	 *            the object to store in Val. If it is a collection a new
	 *            value for each object will be created. *Notice no other
	 *            casts are done*
	 */
	 public Struct.Builder putObj(AttrDef attrDef, Object obj) {
	    return putAttrs(attrDef.getId(), Attr.ofObject(attrDef, obj));
	}
    }    
    
}
