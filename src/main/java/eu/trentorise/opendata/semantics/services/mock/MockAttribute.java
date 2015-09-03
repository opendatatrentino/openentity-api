package eu.trentorise.opendata.semantics.services.mock;

import eu.trentorise.opendata.semantics.model.entity.IAttribute;
import eu.trentorise.opendata.semantics.model.entity.IValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author David Leoni
 */
public class MockAttribute implements IAttribute {

        
    private Long localID;        
    private List<IValue> values;    
    private String attrDefUrl;

    @Override
    public String getAttrDefUrl() {
        return attrDefUrl;
    }
        
    public MockAttribute(){
        localID = -1L;
        this.values = new ArrayList();        
        this.values = new ArrayList();
    }

    public MockAttribute(String attrDefUrl, Object obj){
        this();
        this.attrDefUrl = attrDefUrl;
        MockValue nv = new MockValue();
        nv.setValue(obj);
        this.values.add(new MockValue(obj));
    }
    
    public MockAttribute(List objs, String attrDefUrl){
        this();
        
        for (Object obj : objs){
            MockValue nv = new MockValue();
            nv.setValue(obj);            
            this.values.add(new MockValue(obj));
        }
        
    }    
    
    
    

    public void addValue(IValue value) {
        values.add(value);
    }


    public void removeValue(long valueID) {
        for (int i = 0; i < values.size(); i++){
            if (values.get(i).getLocalID().equals(valueID)){
                values.remove(i);
                break;
            }
        }
        
    }

    @Override
    public List<IValue> getValues() {
        return Collections.unmodifiableList(values);
    }

    @Override
    public IValue getFirstValue() {
        return values.get(0);
    }

    @Override
    public Long getValuesCount() {
        return (long) values.size();
    }

    @Override
    public Long getLocalID() {
        return localID;
    }

    
    public void setLocalID(long ID){
        this.localID = ID;
    }
    
    /**
     * 
     * @return  a list of objects contained inside provided attribute values. 
     */
    public static List makeRawObjectList(IAttribute attr){
        List rawValues = new ArrayList();
        for (IValue val : attr.getValues()){
            rawValues.add(val.getValue());
        }
        return rawValues;
    }
     
    
    
}
