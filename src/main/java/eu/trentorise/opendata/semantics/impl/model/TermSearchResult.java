package eu.trentorise.opendata.semantics.impl.model;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.nlp.model.MeaningKind;
import eu.trentorise.opendata.semantics.services.model.ITermSearchResult;
import javax.annotation.concurrent.Immutable;

/**
 * Immutable implementation of ITermSearchResult
 * @author David Leoni
 */
@Immutable
public class TermSearchResult extends SearchResult implements ITermSearchResult {
    
    private final MeaningKind kind;

    public TermSearchResult(String URL, Dict name, MeaningKind kind) {
        super(URL, name);
        this.kind = kind;
    }

    public MeaningKind getKind() {
        return kind;
    }    
    
}
