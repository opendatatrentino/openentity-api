package eu.trentorise.opendata.semantics.model.knowledge;

import javax.annotation.concurrent.Immutable;

/**
 * Represents a meaning along with its probability. Implementations of this
 * interface must be immutable.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 10 Apr 2014
 */
@Immutable
public interface IMeaning extends Comparable<IMeaning> {

    /**
     * @return the probability of this meaning
     */
    double getProbability();

    /**     
     * @return the URL of the entity or the concept represented by this meaning
     */
    String getURL();

    /**
     * Gets the name of the entity or concept represented by this meaning
     *
     * @return the name of the entity or concept represented by the meaning. The
     * dictionary can be empty.
     */
    IDict getName();

    /**
     * Gets the kind of the meaning.
     *
     * @return the kind of the meaning, which can either be an entity or a
     * concept
     */
    MeaningKind getKind();

}
