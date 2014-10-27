package eu.trentorise.opendata.semantics.impl.model.entity;

import eu.trentorise.opendata.semantics.IntegrityChecker;
import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.entity.ITypeSig;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.model.knowledge.impl.Dict;
import eu.trentorise.opendata.semantics.services.model.DataTypes;
import javax.annotation.Nullable;

/**
 *
 * @author David Leoni
 */
public class AttributeDef implements IAttributeDef {

    private String URL;
    private Dict names;
    private boolean mandatory;
    private String regularExpression;
    @Nullable
    private Concept concept;
    private TypeSig typeSig;
    private String etypeURL;

    private AttributeDef() {
        this.URL = "";
        this.names = new Dict();
        this.regularExpression = "";
        this.typeSig = new TypeSig(DataTypes.STRING);
        this.concept = null;
    }

    public AttributeDef(String URL,
            IDict names,
            ITypeSig typeSig,
            String etypeURL,
            boolean mandatory,
            String regularExpression,
            @Nullable
            IConcept concept) {
        this();
        
        IntegrityChecker.checkURL(URL);
        
        this.URL = URL;

        if (names == null){
            throw new IllegalArgumentException("names can't be null in "+this.getClass().getSimpleName()+"!");
        }
        
        this.names = Dict.copyOf(names);
        if (typeSig == null){
            throw new IllegalArgumentException("typeSig can't be null in "+this.getClass().getSimpleName()+"!");
        }

        IntegrityChecker.checkTypeSig(typeSig);
        
        this.typeSig = TypeSig.copyOf(typeSig);
        if (etypeURL == null){
            throw new IllegalArgumentException("etype URL can't be null in "+this.getClass().getSimpleName()+"!");
        }
        this.etypeURL = etypeURL;
        this.mandatory = mandatory;
        
        if (regularExpression == null){
            throw new IllegalArgumentException("regular expression can't be null in "+this.getClass().getSimpleName()+"!");
        }
        this.regularExpression = regularExpression;
        if (concept != null) {
            this.concept = Concept.copyOf(concept);
        }

    }

    public static AttributeDef copyOf(IAttributeDef ad) {
        if (ad instanceof AttributeDef) {
            return (AttributeDef) ad;
        } else {
            if (ad == null) {
                throw new IllegalArgumentException("Can't copy a null attribute def!");
            } else {
                return new AttributeDef(ad.getURL(),
                        ad.getName(),
                        ad.getTypeSig(),
                        ad.getEtypeURL(),
                        ad.isMandatory(),
                        ad.getRegularExpression(),
                        ad.getConcept());
            }
        }
    }


    @Override
    public IDict getName() {
        return names;
    }
    
    @Override
    public String getDataType() {
        return typeSig.getDataType();
    }

    public TypeSig getTypeSig() {
        return typeSig;
    }

    
    @Override
    public IConcept getConcept() {
        return concept;
    }

    
    @Override
    public boolean isSet() {
        return typeSig.isList();
    }

    @Override
    public String getRegularExpression() {
        return regularExpression;
    }

    @Override
    public void setRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;

    }

    @Override
    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public void setDatatype(String datatype) {
        this.typeSig = new TypeSig(datatype);
    }

    public void setURL(String url) {
        this.URL = url;
    }

    @Override
    public String getURL() {
        return this.URL;
    }

    public void setSet(boolean isSet) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public IEntityType getRangeEType() {
        throw new UnsupportedOperationException("Deprecated!");        
    }

    @Override
    public String getEtypeURL() {
        return etypeURL;
    }

    /**
     * @deprecated
     */
    @Override
    public Long getGUID() {
        throw new UnsupportedOperationException("Deprecated!"); 
    }

    @Override
    public Long getEType() {
        throw new UnsupportedOperationException("Deprecated!"); //To chancopyOf body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRangeEtypeURL() {
        return typeSig.getEtypeURL();
    }

    @Override
    public String getConceptURL() {
        return concept.getURL();
    }

    @Override
    public boolean isList() {
        return typeSig.isList();
    }

}
