package eu.trentorise.opendata.semantics.services.model;

import eu.trentorise.opendata.semantics.nlp.model.MeaningKind;

/**
 * Represents a search result that can contain either an entity or a concept
 * 
 * @author David Leoni
 */
public interface ITermSearchResult extends ISearchResult {    
    MeaningKind getKind();            
}
