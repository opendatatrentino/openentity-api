package eu.trentorise.opendata.semantics.services.mock;

import eu.trentorise.opendata.semantics.model.entity.IUniqueIndex;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author David Leoni
 */
public class MockUniqueIndex implements IUniqueIndex {

    @JsonIgnore
    private ArrayList<String> attributeDefURLs;
    private String URL;
   

    
    public MockUniqueIndex() {
        attributeDefURLs = new ArrayList();
        this.URL = "";
    }    

    public MockUniqueIndex(ArrayList<String> attributeDefURLs, String URL) {
        this.attributeDefURLs = attributeDefURLs;
        this.URL = URL;
    }
            
    
    
    @Override
    public String getURL() {
        return URL;
    }   


    @Override
    public List<String> getAttributeDefURLs() {
        return Collections.unmodifiableList(attributeDefURLs);
    }

    
}
