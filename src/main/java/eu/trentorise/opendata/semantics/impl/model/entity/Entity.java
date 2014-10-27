package eu.trentorise.opendata.semantics.impl.model.entity;

package eu.trentorise.opendata.opendatarise.debug.ekb;

import eu.trentorise.opendata.semantics.model.entity.IAttribute;
import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.entity.IStructure;
import eu.trentorise.opendata.semantics.model.entity.IValue;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.model.knowledge.impl.Dict;
import eu.trentorise.opendata.semantics.services.model.DataTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/**
 * @author David Leoni
 */
public final class Entity implements IEntity {

    private Structure structure;
    
    /**
     * Non null only if attribute name is of datatype STRUCTURE
     */
    @Nullable
    protected IEntityType nameEtype;
    
    public Entity(IEntityType etype) {
        this("", etype, new Dict(""), new Dict(""));
    }

    public Entity(IEntityType etype, IEntityType nameEtype) {
        this("", etype, new Dict(""), new Dict(""), nameEtype);
    }
    
    
    public Entity(String URL, IEntityType etype, IDict name, IDict description) {
        this(URL, etype, name, description, null);
    }

    /**
     * @param nameEtype The entity type in case name attr def is a STRUCTURE
    */
    public Entity(String URL, IEntityType etype, IDict name, IDict description, IEntityType nameEtype) {
        this.structure = new Structure(URL, etype);
        if (DataTypes.STRUCTURE.equals(etype.getNameAttrDef().getDataType()) && nameEtype == null){
            throw new IllegalArgumentException("Found name attr def of type STRUCTURE, but no etype for name was provided!");
        } 
        this.nameEtype = nameEtype;
        if (Dict.copyOf(name).prettyString().length() > 0){
            setName(name);
        }
        if (Dict.copyOf(description).prettyString().length() > 0){
            setDescription(description);
        }                
    }
    
    
    public Entity copyOf(IEntity entity){
        if (entity instanceof Entity){
            Entity ent = (Entity) entity;
            this.structure = ent.structure;
            this.nameEtype = ent.nameEtype;
        } else {
            this.structure = Structure.copyOf(entity);
            this.nameEtype = EntityType.copyOf(entity.);
        }
        
    }
    
    public final void setName(IDict name) {
        
        IAttributeDef nameAttrDef = entityType.getNameAttrDef();
        TypeSig typeSig = new TypeSig(nameAttrDef);
        Object valueToCast;

        if (DataTypes.STRUCTURE.equals(typeSig.getDataType())) {            
            
            IAttributeDef nestedNameAdc = null;
            for (IAttributeDef ad : nameEtype.getAttributeDefs()) {
                // odr todo 0.3 hardcoded stuff
                if ("Name".equals(ad.getName().getString(Locale.ENGLISH))) {
                    nestedNameAdc = ad;
                }
            }
            
            if (nestedNameAdc == null) {
                throw new IllegalStateException("Couldn't find 'Name' attribute def in structure for name with etype: " + nameEtype.getURL());
            } else {
                Structure nameStruct = new Structure(MockEkb.makeURL(), nameEtype);
                // odr todo hardcoded english 
                Object nestedCastedValue = OdrDataTypes.cast(name, new OdrType(nestedNameAdc), Locale.ENGLISH);
                if (nestedCastedValue instanceof List) {
                    nameStruct.setAttrAndValues(nestedNameAdc, (List) nestedCastedValue);
                } else {
                    nameStruct.setAttrAndValue(nestedNameAdc, nestedCastedValue);
                }
                valueToCast = nameStruct;
            }
            
        } else {
            valueToCast = name;
        }
                
        // odr todo using ENGLISH for locale
        Object castedValue = OdrDataTypes.cast(valueToCast, odrType, Locale.ENGLISH);
        
        if (castedValue instanceof List) {
            setAttrAndValues(entityType.getNameAttrDef(), (List) castedValue);
        } else {
            setAttrAndValue(entityType.getNameAttrDef(), castedValue);
        }

    }

    public final void setDescription(IDict descr) {
        
        IAttributeDef descrAttrDef = entityType.getDescriptionAttrDef();
        TypeSig typeSig = new TypeSig(descrAttrDef);
        // odr todo using ENGLISH for locale
        Object castedValue = OdrDataTypes.cast(descr, typeSig, Locale.ENGLISH);
        if (castedValue instanceof List) {
            setAttrAndValues(entityType.getDescriptionAttrDef(), (List) castedValue);
        } else {
            setAttrAndValue(entityType.getDescriptionAttrDef(), castedValue);
        }

    }

    @Override
    public IEntityType getEtype() {        
        return entityType;        
    }

    /**
     * If name attribute is not found defaults to returning empty dict.          
     */    
    @Override
    public IDict getName() {
        
        AttributeDefController nameAttrDef = OdrPlugin.getEkb().getEntityTypeService().readEntityType(entityType.getURL()).getNameAttrDef();
        OdrType nameOdrType = nameAttrDef.getOdrType();
        
        if (DataTypes.STRUCTURE.equals(nameOdrType.getDataType())){

            IAttribute nameAttr = structureAttributes.get(nameAttrDef.getURL());
            if (nameAttr == null || nameAttr.getValuesCount() == 0) {
                return new Dict();
            } else {
                IStructure nameStruct = (IStructure) nameAttr.getValues().get(0).getValue();

                IAttributeDef nestedNameAttrDef = null;

                // odr todo 0.3 much hard coded
                for (IAttributeDef ad : nameStruct.getEtype().getAttributeDefs()){
                    if ("Name".equals(ad.getName().getString(Locale.ENGLISH))){
                        nestedNameAttrDef = ad;
                        for (IAttribute attr : nameStruct.getStructureAttributes()){
                            if (nestedNameAttrDef.getURL().equals(attr.getAttrDef().getURL())){
                                // odr todo ENGLISH hardcoded
                                return (IDict) OdrDataTypes.cast(MockAttribute.makeRawObjectList(attr), OT_NLSTRING, Locale.ENGLISH);
                            }
                        }
                        OdrPlugin.logger.warn("Couldn't find name attribute in name-like structure " + nameStruct + " with URL " + nameStruct.getURL() + ", returning empty dict.");
                        return new OdrDict();
                    }
                }

                OdrPlugin.logger.warn("Couldn't find attr def 'Name' in name structure with etype URL " + nameStruct.getEtypeURL()+ ", returning empty dict.");
                return new OdrDict();

            }


            
        } else {
            IAttribute nameAttr = structureAttributes.get(nameAttrDef.getURL());
            
            if (nameAttr == null){
                OdrPlugin.logger.warn("Couldn't find name attribute in entity with URL " + getURL() + ", returning empty dict");
                return new Dict();
            } else {
                // odr todo ENGLISH hardcoded
                return (IDict) OdrDataTypes.cast(MockAttribute.makeRawObjectList(nameAttr), OT_NLSTRING, Locale.ENGLISH);
            }                                 
        }                        
    }

    protected void setDictAttr(String attrDefURL, Locale locale, String translation) {
        List<String> ts = new ArrayList<String>();
        ts.add(translation);
        setDictAttr(attrDefURL, locale, ts);
    }

    protected void setDictAttr(String attrDefURL, Locale locale, List<String> translations) {

        IAttribute attr = structureAttributes.get(attrDefURL);

        IDict newName;
        if (attr == null) {
            newName = new Dict().putTranslation(locale, translations);
        } else {
            if (attr.getValuesCount() > 0) {
                newName = Dict.copyOf((IDict) attr.getFirstValue().getValue());
            } else {
                newName = new Dict().putTranslation(locale, translations);
            }
        }

        setAttrAndValue(attr.getAttrDef(), newName);

    }

    
    @Override
    public void setName(Locale locale, String name) {
        throw new UnsupportedOperationException("entity setName currently sucks big time, don't use it");
    }

    /**
     * If attribute is not found defaults to returning empty dict.          
     */
    private IDict getDict(String attrDefURL) {
        List realValues = new ArrayList();
        IAttribute attrs = structureAttributes.get(attrDefURL);
        if (attrs == null){            
            return new Dict();
        } else {
            for (IValue val : attrs.getValues()) {
                realValues.add(val.getValue());
            }
            return (IDict) OdrDataTypes.cast(realValues, OT_NLSTRING);
        }
    }

    /**
     * If attribute is not found defaults to returning empty dict.          
     */    
    @Override
    public IDict getDescription() {        
        return getDict(OdrPlugin.getEkb().getEntityTypeService().readEntityType(entityType.getURL()).getDescrAttrDefURL());
    }

    @Override
    public void setDescription(Locale locale, String name) {
        throw new UnsupportedOperationException("entity setDescription currently sucks big time, don't use it");

    }

    /**
     * @deprecated
     */
    @Override
    public Long getGUID() {
        throw new UnsupportedOperationException("Not supported yet."); //To chancopyOf bcopyOfy of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(Locale locale, List<String> names) {
        throw new UnsupportedOperationException("entity setName currently sucks big time, don't use it");
    }

    private Structure getStructure() {
        return structure;
    }

        
    
}

