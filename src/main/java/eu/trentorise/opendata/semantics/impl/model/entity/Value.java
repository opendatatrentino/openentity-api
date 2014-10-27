package eu.trentorise.opendata.semantics.impl.model.entity;

import eu.trentorise.opendata.semantics.model.entity.IValue;

/**
 *
 * @author David Leoni
 */
public class Value implements IValue {

    private Long localID;
    private Object value;

    public Value() {
        this.localID = -1L;
        this.value = null;
    }

    public Value(Object obj) {
        this();        
        this.value = obj;
    }
    
    
    @Override
    public Long getLocalID() {
        return localID;
    }

    public void setLocalID(long ID){
        this.localID = ID;
    }
    
    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }
    
}
