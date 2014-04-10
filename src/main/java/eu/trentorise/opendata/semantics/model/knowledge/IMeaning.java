package eu.trentorise.opendata.semantics.model.knowledge;

import javax.annotation.concurrent.Immutable;

/**
 * Represents a meaning along with its probability. Implementations of this interface
 * must be immutable.
 * 
 * @author David Leoni <david.leoni@unitn.it>
 * @date 10 Apr 2014
 */
@Immutable
public interface IMeaning extends Comparable<IMeaning> {
    
    /**
     * @return the probability of this meaning
    */
    float getProbability();
    
    /**
     * @return the URL of the entity or the concept represented by this meaning
     */
    String getURL();
    
    /**
     * 
     * @return the kind of the meaning, which can either be an entity or a concept 
     */
    MeaningKind getKind();    
}
