package eu.trentorise.opendata.semantics.impl.model.entity;


import com.google.common.collect.ImmutableMap;
import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.entity.IUniqueIndex;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.model.knowledge.impl.Dict;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;



/**
 *
 * @author David Leoni
 */
public class EntityType implements IEntityType {
   
    private ImmutableMap<String, IAttributeDef> attrDefs;
    private String URL;
    private Dict names;
    @Nullable
    private Concept concept;
    private List<IUniqueIndex> uniqueIndexes;



    public EntityType(String URL, 
                      IDict names, 
                      IDict description,  
                      Collection<? extends IAttributeDef> attrs, 
                      IConcept concept,
                      Collection<? extends IUniqueIndex> uniqueIndexes,
                      String nameAttrDefURL,
                      String descrAttrDefURL) {
        this.names = Dict.copyOf(names);  
        this.URL = URL;
        
        ImmutableMap.Builder b = ImmutableMap.builder();
        for (IAttributeDef ad : attrs) {            
            b.put(ad.getURL(),AttributeDef.copyOf(ad));
        }
        attrDefs = b.build();
        
        this.nameAttrDefURL = nameAttrDefURL; 
        this.descrAttrDefURL = descrAttrDefURL; 
        this.concept = Concept.copyOf(concept);
    }
    
    
    public EntityType(String URL, String engName, String itName,  String conceptURL,  String nameAttrDefURL, String descrAttrDefURL, List<IAttributeDef> attrs) {
        this();
        names = names.putTranslation(Locale.ENGLISH, engName).putTranslation(Locale.ITALIAN, itName);
        this.URL = URL;
        for (IAttributeDef ad : attrs) {
            attrDefs.put(ad.getURL(), ad);
        }
        this.nameAttrDefURL = nameAttrDefURL;
        this.descrAttrDefURL = descrAttrDefURL;
        this.conceptURL = conceptURL;
    }

    static EntityType copyOf(IEntityType etype) {
        
    }
    
    
    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public IDict getName() {
        return names;
    }

    @Override
    public IConcept getConcept() {
        return OdrPlugin.getEkb().getKnowledgeService().readConcept(getConceptURL()).getControlledObject();
    }

    
    @Override
    public void setConcept(IConcept concept) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public List<IAttributeDef> getAttributeDefs() {
        return Collections.unmodifiableList(new ArrayList(attrDefs.values()));
    }

    @Override
    public void addAttributeDef(IAttributeDef attrDef) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public void removeAttributeDef(String attrDefURL) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public List<IUniqueIndex> getUniqueIndexes() {
        return uniqueIndexes;
    }

    @Override
    public void removeUniqueIndex(String uniqueIndexURL) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public void addUniqueIndex(IUniqueIndex uniqueIndex) {
        throw new UnsupportedOperationException("Deprecated!");
    }


    /**
     * @deprecated
     */
    @Override
    public void removeAttributeDef(long attrDefID) {
        throw new UnsupportedOperationException("Deprecated!");    
    }

    /**
     * @deprecated
     */
    @Override
    public void removeUniqueIndex(long uniqueIndexID) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    public void setName(IDict dict) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public IAttributeDef getNameAttrDef() {
        if (nameAttrDefURL == null){
            return null;
        }
        
        return attrDefs.get(nameAttrDefURL);
        
    }

    @Override
    public IAttributeDef getDescriptionAttrDef() {
        if (descrAttrDefURL == null){
            return null;
        }
        
        return attrDefs.get(descrAttrDefURL);        
        
    }

    @Override
    public String getConceptURL() {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public IAttributeDef getAttrDef(String URL) {
        return attrDefs.get(URL);
    }
    
}
