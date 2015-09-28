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
import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.collect.Lists;
import eu.trentorise.opendata.commons.BuilderStylePublic;
import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.DataTypes;
import eu.trentorise.opendata.semantics.exceptions.CastException;
import eu.trentorise.opendata.semantics.services.IEtypeService;
import eu.trentorise.opendata.semtext.SemText;

import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/**
 * An entity is a representation of real world object.
 *
 * @author Juan Pane <pane@disi.unitn.it>
 * @author Moaz Reyad <reyad@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 *
 */
@Value.Immutable
@BuilderStylePublic
@JsonSerialize(as = Entity.class)
@JsonDeserialize(as = Entity.class)
public abstract class AEntity extends AStruct {

    /**
     * The name of the entity. Notice this field is totally optional and
     * decoupled from the eventual name value present in {@link #getAttrs()} and
     * applications should use this field only if they don't know how to process
     * relative attribute.
     *
     * @return the name of the entity in the default languages if available.
     *         Returned dict can be empty.
     */
    @Value.Default
    public Dict getName() {
	return Dict.of();
    }

    /**
     * The description of an entity. Notice this field is totally decoupled from
     * the eventual description value present in {@link #getAttrs()} and
     * applications should use this field only if they don't know how to process
     * the description attribute.
     *
     * @return the description of the entity in the default languages if
     *         available. Returned dict can be empty.
     */
    @Value.Default
    public Dict getDescription() {
	return Dict.of();
    }

    
    
    /**
     * He who needs a custom builder is a man in trouble.
     * 
     * David Leoni
     *
     */
    public static abstract class Builder  {

	public abstract Entity.Builder setName(Dict name);

	public abstract Entity.Builder setDescription(Dict description);

	public abstract Entity.Builder setEtypeId(String etypeId);

	public abstract Entity.Builder putAttrs(String key, Attr value);

	/**
	 * Sets both description in attrs AND description dictionary property
	 * 
	 * @param etypeId
	 *            the id of the entity's etype
	 */
	public Entity.Builder setDescrAttr(Dict descr, String etypeId, IEtypeService ets) {
	    Map<String, Etype> etypes = Entities.resolveEtypes(etypeId, ets);
	    Etype etype = etypes.get(etypeId);
	    return setDescrAttr(descr, etype);
	}

	/**
	 * Sets both description in attrs AND description dictionary property
	 * 
	 * @param etype
	 *            the entity etype of the entity
	 */
	public Entity.Builder setDescrAttr(Dict descr, Etype etype) {
	    checkNotNull(etype);
	    AttrDef descrAttrDef = etype.descrAttrDef();
	    Object valueToCast = dictToTerminal(descr, descrAttrDef.getType());

	    return setDescription(descr).putAttrs(descrAttrDef.getId(), Attr.ofObject(descrAttrDef, valueToCast));
	}

	/**
	 * Sets both descrption in attrs AND name dictionary property
	 * 
	 * @param etypeId
	 *            the entity etype.
	 */
	public Entity.Builder setNameAttr(Dict name, String etypeId, IEtypeService ets) {
	    Map<String, Etype> etypes = Entities.resolveEtypes(etypeId, ets);
	    Etype etype = etypes.get(etypeId);
	    checkNotNull(etype);
	    AttrDef nameAttrDef = etype.nameAttrDef();
	    Etype nameEtype = null;
	    if (DataTypes.STRUCTURE.equals(nameAttrDef.getType().getDatatype())) {
		nameEtype = ets.readEtype(nameAttrDef.getType().etypeId());
	    }
	    return setNameAttr(name, etype, nameEtype);

	}

	/**
	 * Sets both name in attrs AND name dictionary property
	 * 
	 * @param etype
	 *            the entity etype.
	 * @param nameEtype
	 *            the etype for the name in case the name is a structure.
	 */
	public Entity.Builder setNameAttr(Dict name, Etype etype, @Nullable Etype nameEtype) {
	    checkNotNull(name);
	    checkNotNull(etype, "Need entity etype in the parameters, but found none!");

	    setEtypeId(etype.getId());

	    AttrDef nameAttrDef = etype.nameAttrDef();
	    AttrType attrType = nameAttrDef.getType();
	    Object valueToCast;

	    if (DataTypes.STRUCTURE.equals(attrType.getDatatype())) {

		checkNotNull(nameEtype,
			"Need entity etype " + attrType.getEtypeId() + " in the parameters, but found none!");
		AttrDef nestedNameAd = nameEtype.nameAttrDef();

		Struct.Builder nameStructB = Struct.builder();
		// new MockStructure(MockEkb.makeURL(), nameEtype);

		// odr todo hardcoded english
		// Dict -> (String | LocalizedString | Dict) []
		Object nestedCastedValue = dictToTerminal(name, nestedNameAd.getType());
											
		nameStructB.setEtypeId(nameEtype.getId());									// nestedNameAd.getType(),
											
		nameStructB.putAttrs(nestedNameAd.getId(), Attr.ofObject(nestedNameAd, nestedCastedValue));
		valueToCast = autobox(nameStructB.build(), attrType.isList());

	    } else {
		valueToCast = dictToTerminal(name, attrType);
	    }

	    setName(name);
	    return putAttrs(nameAttrDef.getId(), Attr.ofObject(nameAttrDef, valueToCast));
	}

	/**
	 * Wraps the object into the hideous Attr and Val stuff
	 * 
	 * @param obj
	 *            the object to store in Val. If it is a collection a new
	 *            value for each object will be created. *Notice no other
	 *            casts are done*
	 */
	public Entity.Builder putObj(AttrDef attrDef, Object obj) {
	    return putAttrs(attrDef.getId(), Attr.ofObject(attrDef, obj));
	}

	private static Object autobox(Object obj, boolean isList) {
	    if (isList) {
		return Lists.newArrayList(obj);
	    } else {
		return obj;
	    }
	}

	/**
	 * Casts dict to terminal type (which may be a list but not a structure)
	 * todo better define terminal types
	 */
	private static Object dictToTerminal(Dict dict, AttrType targetType) {

	    String nestedDatatype = targetType.getDatatype();

	    boolean isList = targetType.isList();
	    if (nestedDatatype.equals(DataTypes.DICT)) {
		return autobox(dict, isList);
	    } else if (nestedDatatype.equals(DataTypes.STRING)) {
		return autobox(dict.some().str(), isList);
	    } else if (nestedDatatype.equals(DataTypes.LOCALIZED_STRING)) {
		if (isList) {
		    return dict.asLocalizedStrings();
		} else {
		    return dict.some();
		}
	    } else if (nestedDatatype.equals(DataTypes.SEMANTIC_TEXT)) {
		return autobox(SemText.of(dict), isList);
	    } else {
		throw new CastException(dict, targetType, Locale.ROOT,
			"Unsupported target datatype: " + targetType.getDatatype());
	    }

	}
    }

    /**
     * Returns a copy of the entity with provided name set.
     *
     * @param etype
     *            the entity etype
     * @param nameEtype
     *            the entity name etype, if needed, null otherwise.
     */
    // public Entity withName(Dict name, Etype etype, @Nullable Etype nameEtype)
    // {

    // }

}
