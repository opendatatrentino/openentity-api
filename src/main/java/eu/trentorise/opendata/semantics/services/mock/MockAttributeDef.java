package eu.trentorise.opendata.semantics.services.mock;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semantics.DataTypes;
import eu.trentorise.opendata.semantics.model.entity.AttrType;
import java.util.Locale;



/**
 *
 * @author David Leoni
 */
public class MockAttributeDef implements IAttributeDef {

    private Dict names;
    private String url;
    private boolean mandatory;
    private String regularExpression;
    private String conceptURL;
    private AttrType attrType;
    private String etypeURL;

        
    public MockAttributeDef() {
        this.url = "";
        this.names = Dict.of();
        this.mandatory = false;
        this.regularExpression = "";
        this.attrType = AttrType.of(DataTypes.STRING); 
        this.conceptURL =  null;
        this.etypeURL = "";       
    }

    public MockAttributeDef(String URL, String engName, String itName, AttrType attrType, String etypeURL){
        this();
        names = Dict.builder()
                .put(Locale.ENGLISH, engName)
                .put(Locale.ITALIAN, itName).build();
        this.url = URL;
        this.attrType = attrType;                
        this.etypeURL = etypeURL;        
    }

    @Override
    public AttrType getAttrType() {
        return attrType;
    }
    
    
    /**
     * @param ret 
     */
    public void setAttrType(AttrType attrType){
        this.attrType = attrType;
    }

    @Override
    public Dict getName() {
        return names;
    }
        
    public void setName(Locale locale, String name){
        names = names.with(locale, name);
    }
    

    @Override
    public String getDatatype() {
        return attrType.getDatatype();
    }
    
    public AttrType getOdrType(){
        return attrType;
    }
    

    public void setConcept(IConcept concept) {
        this.conceptURL = concept.getURL();
    }
    

    @Override
    public String getRegularExpression() {        
        return regularExpression;
    }

    public void setRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
                
    }

    @Override
    public boolean isMandatory() {
        return mandatory;
    }
    
    public void setMandatory(boolean mandatory){
        this.mandatory = mandatory;
    }
    
   
    public void setDatatype(String datatype){              
        this.attrType = AttrType.of(datatype);
    }
       

    public void setURL(String url){
        this.url = url;
    }
    
    @Override
    public String getURL() {
        return this.url;
    }
        
    
    @Override
    public String getEtypeURL() {
        return etypeURL;
    }       
    

    @Override
    public String getRangeEtypeURL() {
        return attrType.etypeURL();
    }

    @Override
    public String getConceptURL() {
        return conceptURL;
    }

    @Override
    public boolean isList() {
        return attrType.isList();
    }
    

}
