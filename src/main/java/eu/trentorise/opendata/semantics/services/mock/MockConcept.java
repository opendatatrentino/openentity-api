package eu.trentorise.opendata.semantics.services.mock;

import static com.google.common.base.Preconditions.checkNotNull;
import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.model.knowledge.IConcept;
import java.util.Locale;


/**
 *
 * @author David Leoni
 */
public class MockConcept implements IConcept{
    private String URL;
    
    private Dict name;      
    private Dict  description;        

    public MockConcept() {
        name = Dict.of();  
        description = Dict.of();
        URL = "";
    }

    public MockConcept(String URL, Dict name, Dict description) {
        checkNotNull(URL);
        checkNotNull(name);
        checkNotNull(description);
        this.URL = URL;
        this.name = name;
        this.description = description;
    }
    
    
    
    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public Dict getName() {
        return name;
    }
    
    public void setLabel(Locale locale, String label){
        this.name = this.name.with(locale, label);
    }    

  
    @Override
    public Dict getDescription(){
        return description;
    }
    
    /**
     *
     * @param description
     * @param locale
     */
    public void setDescription(Locale locale, String description){
        this.description = this.description.with(locale, description);
    }
  
    
    public void setURL(String URL) {
        checkNotNull(URL);
        this.URL = URL;
    }

    public void setName(Dict label) {
        checkNotNull(label);
        this.name = label;
    }

    public void setDescription(Dict description) {
        checkNotNull(description);
        this.description = description;
    }


    
}

