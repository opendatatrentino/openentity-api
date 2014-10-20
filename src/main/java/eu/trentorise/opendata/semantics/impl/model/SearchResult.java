package eu.trentorise.opendata.semantics.impl.model;

import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.services.model.ISearchResult;
import javax.annotation.concurrent.Immutable;

/**
 * Immutable implementation of ISearchResult
 * @author David Leoni
 */
@Immutable
public class SearchResult implements ISearchResult {

    private String URL;
    private IDict name;

    public SearchResult(String URL, IDict name) {
        this.URL = URL;
        this.name = name;
    }
            
    public String getURL() {
        return URL;
    }

    public IDict getName() {
        return name;
    }
    
}
