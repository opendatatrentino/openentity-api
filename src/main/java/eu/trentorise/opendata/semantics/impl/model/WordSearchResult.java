package eu.trentorise.opendata.semantics.impl.model;

import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningKind;
import eu.trentorise.opendata.semantics.services.model.IWordSearchResult;
import javax.annotation.concurrent.Immutable;

/**
 * Immutable implementation of IWordSearchResult
 * @author David Leoni
 */
@Immutable
public class WordSearchResult extends SearchResult implements IWordSearchResult {
    
    private final MeaningKind kind;

    public WordSearchResult(String URL, IDict name, MeaningKind kind) {
        super(URL, name);
        this.kind = kind;
    }

    public MeaningKind getKind() {
        return kind;
    }    
    
}
