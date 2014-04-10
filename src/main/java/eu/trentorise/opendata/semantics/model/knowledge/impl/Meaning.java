package eu.trentorise.opendata.semantics.model.knowledge.impl;

import eu.trentorise.opendata.semantics.model.knowledge.IMeaning;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningKind;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 10 Apr 2014
 */
@Immutable
public class Meaning implements IMeaning {

    private String URL;
    private float probability;
    private MeaningKind meaningKind;

    public Meaning(String URL, float probability, MeaningKind meaningKind) {
        this.URL = URL;
        this.probability = probability;
        this.meaningKind = meaningKind;
    }

    /**
     * To have high probability meanings as first ones in collections.
     */
    @Override
    public int compareTo(IMeaning om) {
        float diff = om.getProbability() - this.getProbability();
        if (diff > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Determines the best meaning among the given one according to theIR
     * probabilities. If no best meaning is found null is returned.
     *
     * @param meanings a sorted list of meanings
     * @return the disambiguated meaning or null if no meaning can be clearly
     * dismabiguated.
     */
    @Nullable
    public static IMeaning disambiguate(List<IMeaning> meanings) {
        final double FACTOR = 1.5;

        if (meanings.isEmpty()) {
            return null;
        }
        if (meanings.size() == 1) {
            return meanings.iterator().next();
        }

        if (meanings.get(0).getProbability() > FACTOR / meanings.size()) {
            return meanings.get(0);
        } else {
            return null;
        }

    }

    @Override
    public float getProbability() {
        return probability;
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public MeaningKind getKind() {
        return meaningKind;
    }
}
