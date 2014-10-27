
package eu.trentorise.opendata.semantics.impl.model.entity;


import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.model.knowledge.impl.Dict;


/**
 *
 * @author David Leoni
 */
public final class Concept implements IConcept{
    private String URL;    
    private Dict name;      
    private Dict  description;        

    public static Concept copyOf(IConcept concept){
        if (concept instanceof Concept){
            return (Concept) concept;
        } else {
            if (concept == null){
                throw new IllegalArgumentException("Can't copy a null concept!");
            }
            return new Concept(concept.getURL(), 
                               concept.getName(),
                               concept.getDescription());
        }
    }
    
    public Concept(String URL, IDict name, IDict description) {
        if (URL == null){
            throw new IllegalArgumentException("null URL is not allowed for concepts!");
        }
        if (name == null){
            throw new IllegalArgumentException("null name is not allowed for concepts!");
        }   
        if (description == null){
            throw new IllegalArgumentException("null description is not allowed for concepts!");
        }        
        this.URL = URL;
        this.name = Dict.copyOf(name);
        this.description = Dict.copyOf(description);
    }
    
    
    
    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public IDict getName() {
        return name;
    }   

  
    @Override
    public IDict getDescription(){
        return description;
    }
    
  

    @Override
    public Long getGUID() {
        throw new UnsupportedOperationException("Deprecated|"); 
    }

}
