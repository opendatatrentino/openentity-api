package eu.trentorise.opendata.semantics.impl.model.entity;
import com.google.common.collect.ImmutableList;
import eu.trentorise.opendata.semantics.model.entity.IAttribute;
import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * Immutable implementation of attribute interface
 * @deprecated Avoid Attributes if possible
 * @author David Leoni
 */
@Immutable
public class Attribute implements IAttribute {

        
    private Long localID;        
    private List<IValue> values;
    private IAttributeDef attributeDef;
        
    public Attribute(){
        localID = -1L;
        this.values = ImmutableList.of();
        this.attributeDef = new AttributeDef();
        this.values = new ArrayList<IValue>();        
    }

    public Attribute(IAttributeDef attrDef, Object obj){
        this();
        attributeDef = attrDef;
        IValue nv = new Value();
        nv.setValue(obj);
        this.values.add(new Value(obj));
    }
    
    public Attribute(List objs, IAttributeDef attrDef){
        this();
        attributeDef = attrDef;
        for (Object obj : objs){
            IValue nv = new Value();
            nv.setValue(obj);            
            this.values.add(new Value(obj));
        }
        
    }    
    
    
    @Override
    public IAttributeDef getAttributeDefinition() {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public IAttributeDef getAttrDef() {
        return attributeDef;
    }

    @Override
    public void setAttributeDefinition(IAttributeDef ad) {
        this.attributeDef = ad;
    }

    @Override
    public void addValue(IValue value) {
        values.add(value);
    }

    @Override
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
