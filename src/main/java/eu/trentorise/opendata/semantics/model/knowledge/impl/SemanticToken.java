package eu.trentorise.opendata.semantics.model.knowledge.impl;

import eu.trentorise.opendata.semantics.model.knowledge.IMeaning;
import eu.trentorise.opendata.semantics.model.knowledge.ISemanticToken;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * @author David Leoni <david.leoni@unitn.it>
 * @date 10 Apr 2014
 */
@Immutable
public class SemanticToken implements ISemanticToken {

    private int startOffset;
    private int endOffset;
    private List<IMeaning> meanings;
    @Nullable
    private IMeaning selectedMeaning;
    @Nullable
    private MeaningStatus meaningStatus;

    /**
     * Meaning probabilities are normalized
     *
     * @param startOffset
     * @param endOffset the position of the character immediately *after* the
     * token itself. Position is absolute with respect to the
     * text stored in the SemanticText container.
     * @param meaningStatus
     * @param selectedMeaning
     * @param meanings a new collection is created internally to store the
     * meanings.
     */
    public SemanticToken(int startOffset,
            int endOffset,
            MeaningStatus meaningStatus,
            IMeaning selectedMeaning,
            Collection<IMeaning> meanings) {
        if (meanings.isEmpty()) {
            throw new RuntimeException("OdrToken must have at least one meaning!");
        }
        List<IMeaning> mgs = new ArrayList<IMeaning>();

        float total = 0;
        for (IMeaning m : meanings) {
            total += m.getProbability();
        }
        for (IMeaning m : meanings) {
            mgs.add(new Meaning(m.getURL(), m.getProbability() / total, m.getKind()));
        }

        Collections.sort(mgs);
        this.meanings = Collections.unmodifiableList(mgs);
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.meanings = mgs;
        this.meaningStatus = meaningStatus;
        this.selectedMeaning = selectedMeaning;

    }

    @Override
    public int getStartOffset() {
        return startOffset;
    }

    @Override
    public int getEndOffset() {
        return endOffset;
    }

    /**
     *
     * @return the meanings sorted, the first having the highest probability
     */
    @Override
    public List<IMeaning> getMeanings() {
        return meanings;
    }

    @Nullable
    @Override
    public IMeaning getSelectedMeaning() {
        return selectedMeaning;
    }

    @Override
    public MeaningStatus getMeaningStatus() {
        return meaningStatus;
    }

}
