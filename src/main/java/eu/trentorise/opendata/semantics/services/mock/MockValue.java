package eu.trentorise.opendata.semantics.services.mock;

import eu.trentorise.opendata.semantics.model.entity.IValue;

/**
 *
 * @author David Leoni
 */
public class MockValue implements IValue {

    private Long localID;
    private Object value;

    public MockValue() {
        this.localID = -1L;
        this.value = null;
    }

    public MockValue(Object obj) {
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
    
    public void setValue(Object value) {
        this.value = value;
    }
    
}
