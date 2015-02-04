package eu.trentorise.opendata.semantics.impl.model;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.services.model.ISearchResult;
import javax.annotation.concurrent.Immutable;

/**
 * Immutable implementation of ISearchResult
 * @author David Leoni
 */
@Immutable
public class SearchResult implements ISearchResult {

    private String URL;
    private Dict name;

    public SearchResult(String URL, Dict name) {
        this.URL = URL;
        this.name = name;
    }
            
    public String getURL() {
        return URL;
    }

    public Dict getName() {
        return name;
    }
    
}
