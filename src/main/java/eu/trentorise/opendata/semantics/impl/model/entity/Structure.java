package eu.trentorise.opendata.semantics.impl.model.entity;


import com.google.common.collect.ImmutableMap;
import eu.trentorise.opendata.semantics.model.entity.IAttribute;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.entity.IStructure;
import eu.trentorise.opendata.semantics.model.entity.IValue;

import javax.annotation.Nullable;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * An immutable implementation of IStructure
 * @author David Leoni
 */
@Immutable
public final class Structure implements IStructure {

    protected String URL;

    protected ImmutableMap<String, List<? extends IValue>> values;

    @Nullable
    protected EntityType etype;   

    public static Structure copyOf(IStructure struct){
        if (struct instanceof Structure){
            return (Structure) struct;
        } else {
            Structure ret = new Structure();
            ret.URL = struct.getURL();
            ret.etype = EntityType.copyOf(struct.getEtype());
            ret.etypeURL = struct.getEtypeURL();
            if (I)
            this.values = ImmutableMap.copyOf(struct.getValues());
            
        }
    }
    

    public Structure(String URL, IEntityType etype) {
        this();
        this.URL = URL;
        this.entityType = etype;
        this.etypeURL = etype.getURL();
    }

    public Structure(String URL, IEntityType etype, IEntityType nameEtype) {
        this();
        this.URL = URL;
        this.entityType = etype;
        this.etypeURL = etype.getURL();
    }

    private Structure() {
        this.URL = "";
        values = ImmutableMap.of();
    }

    @Nullable
    @Override
    public IEntityType getEtype() {        
        return etype;
    }

    /**
     * @deprecated
     */
    @Override
    public void setEtype(IEntityType entityType) {
        throw new UnsupportedOperationException("Deprecated!");
    }


    /** @deprecated */
    @Nullable
    @Override
    public IAttribute getAttribute(String attrDefURL) {
        throw new UnsupportedOperationException("Deprecated!");    }

    /** @deprecated */
    @Override
    public List<IAttribute> getStructureAttributes() {
        throw new UnsupportedOperationException("Deprecated!");    
    }

    /** @deprecated */
    @Override
    public void setStructureAttributes(List<IAttribute> structureAttributes) {
        throw new UnsupportedOperationException("Deprecated!");    
    }    

    @Override
    public String getURL() {
        return URL;
    }

    /** @deprecated */
    @Override
    public void setURL(String URL) {
        throw new UnsupportedOperationException("Deprecated!");    
    }

    /**
     * @deprecated
     */
    @Override
    public Long getLocalID() {
        throw new UnsupportedOperationException("deprecated"); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String getEtypeURL() {
        return etype.getURL();
    }

    public List<? extends IValue> getValues(String attrDefURL) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<? extends IValue> getValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
