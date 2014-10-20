package eu.trentorise.opendata.semantics.services.model;

import eu.trentorise.opendata.semantics.model.knowledge.MeaningKind;

/**
 * Represents a search result that can contain either an entity or a concept
 * 
 * @author David Leoni
 */
public interface IWordSearchResult extends ISearchResult {    
    MeaningKind getKind();            
}
