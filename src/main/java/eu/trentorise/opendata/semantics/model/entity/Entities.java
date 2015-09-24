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

import com.google.common.collect.ImmutableMap;
import static eu.trentorise.opendata.commons.validation.Preconditions.checkNotEmpty;
import eu.trentorise.opendata.semantics.DataTypes;
import eu.trentorise.opendata.semantics.exceptions.OpenEntityNotFoundException;
import eu.trentorise.opendata.semantics.services.IEtypeService;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author David Leoni
 */
public final class Entities {

    private static final Logger LOG = Logger.getLogger(Entities.class.getName());

    /**
     * Resolves needed etypes to manipulate entities of provided etype i.e. to
     * handle relational attributes and structured names.
     * 
     * @return a map from etype url to etype
     */
    public static Map<String, Etype> resolveEtypesById(Iterable<String> etypeIds, IEtypeService ets) {

	List<Etype> etypes = ets.readEtypes(etypeIds);
	return resolveEtypes(etypes, ets);

    }

    /**
     * Resolves needed etypes to manipulate entities of provided etype i.e. to
     * handle relational attributes and structured names.
     * 
     * @return a map from etype url to etype
     */
    public static Map<String, Etype> resolveEtypes(String etypeId, IEtypeService ets) {
	Etype etype = ets.readEtype(etypeId);
	return resolveEtypes(etype, ets);
    }

    /**
     * Resolves needed etypes to manipulate the entities of provided etype i.e.
     * to handle relational attributes and structured names.
     * 
     * @return a map from etype url to etype
     */
    public static Map<String, Etype> resolveEtypes(Etype etype, IEtypeService ets) {
	Map<String, Etype> ret = new HashMap();

	ret.put(etype.getId(), etype);

	for (AttrDef attrDef : etype.getAttrDefs().values()) {
	    AttrType attrType = attrDef.getType();
	    if (DataTypes.STRUCTURE.equals(attrType.getDatatype()) || DataTypes.ENTITY.equals(attrType.getDatatype())) {
		ret.put(attrType.getEtypeId(), ets.readEtype(attrType.getEtypeId()));
	    }

	}
	return ret;

    }

    /**
     * Resolves needed etypes to manipulate the entities of provided etypes i.e.
     * to handle relational attributes and structured names.
     * 
     * @return a map from etype url to etype
     */
    public static Map<String, Etype> resolveEtypes(Iterable<Etype> etypes, IEtypeService ets) {
	Map<String, Etype> ret = new HashMap();

	List<String> subEtypeIds = new ArrayList();

	for (Etype etype : etypes) {
	    ret.put(etype.getId(), etype);
	    for (AttrDef attrDef : etype.getAttrDefs().values()) {
		AttrType attrType = attrDef.getType();
		if (DataTypes.STRUCTURE.equals(attrType.getDatatype())
			|| DataTypes.ENTITY.equals(attrType.getDatatype())) {
		    subEtypeIds.add(attrType.getEtypeId());
		}
	    }
	}

	List<Etype> subEtypes = ets.readEtypes(subEtypeIds);

	for (Etype subEtype : subEtypes) {
	    ret.put(subEtype.getId(), subEtype);
	}

	return ret;

    }

    /**
     * Resolves needed etypes to manipulate the entity i.e. to handle relational
     * attributes and structured names.
     * 
     * @return a map from etype url to etype
     */
    public static Map<String, Etype> resolveEtypes(AStruct struct, IEtypeService ets) {	
	Etype etype = ets.readEtype(struct.getEtypeId());
	return resolveEtypes(etype, ets);
    }

    /*
     * FOR WITH NAME
     * 
     * 
     * /** Resolves needed etypes to manipulate the entity todo for name is
     * easy, what about relation entities? How many levels are needed?
     * 
     * public static List<Etype> resolveEtypes(Entity entity, IEtypeService ets)
     * { ImmutableList.Builder<Etype> ret = ImmutableList.builder();
     * 
     * Etype etype = ets.readEtype(entity.getEtypeId());
     * 
     * AttrDef nameAttrDef = etype.nameAttrDef(); AttrType attrType =
     * nameAttrDef.getType(); Object valueToCast;
     * 
     * if (DataTypes.STRUCTURE.equals(attrType.getDatatype())) {
     * 
     * Etype nameEtype = ets.readEtype(attrType.getEtypeId());
     * 
     * AttrDef nestedNameAd = nameEtype.nameAttrDef();
     * 
     * 
     * MockStructure nameStruct = new MockStructure(MockEkb.makeURL(),
     * nameEtype); // odr todo hardcoded english Object nestedCastedValue =
     * OdrDataTypes.cast(name, new OdrType(nestedNameAd), Locale.ENGLISH); if
     * (nestedCastedValue instanceof List) {
     * nameStruct.setAttrAndValues(nestedNameAd, (List) nestedCastedValue); }
     * else { nameStruct.setAttrAndValue(nestedNameAd, nestedCastedValue); }
     * valueToCast = nameStruct;
     * 
     * 
     * } else { valueToCast = name; }
     * 
     * // odr todo using ENGLISH for locale Object castedValue =
     * OdrDataTypes.cast(valueToCast, attrType, Locale.ENGLISH);
     * 
     * if (castedValue instanceof List) {
     * setAttrAndValues(entityType.getNameAttrDef(), (List) castedValue); } else
     * { setAttrAndValue(entityType.getNameAttrDef(), castedValue); } }
     */

}
