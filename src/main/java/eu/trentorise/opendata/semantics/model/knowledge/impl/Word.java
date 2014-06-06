/**
 * *****************************************************************************
 * Copyright 2012-2013 Trento Rise (www.trentorise.eu/)
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the GNU Lesser General Public License (LGPL)
 * version 2.1 which accompanies this distribution, and is available at
 *
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************
 */
package eu.trentorise.opendata.semantics.model.knowledge.impl;

import eu.trentorise.opendata.semantics.model.knowledge.IMeaning;
import eu.trentorise.opendata.semantics.model.knowledge.IWord;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * @author David Leoni <david.leoni@unitn.it>
 * @date 11 Apr 2014
 */
@Immutable
public class Word implements IWord {

    private int startOffset;
    private int endOffset;
    private List<IMeaning> meanings;
    @Nullable
    private IMeaning selectedMeaning;
    @Nullable
    private MeaningStatus meaningStatus;

    /**
     * Constructor for a Word. Meaning probabilities are normalized so total sum
     * is 1.0
     *
     * @param startOffset
     * @param endOffset the position of the character immediately *after* the
     * word itself. Position is absolute with respect to the text stored in the
     * SemanticText container.
     * @param meaningStatus
     * @param selectedMeaning
     * @param meanings a new collection is created internally to store the
     * meanings.
     */
    public Word(int startOffset,
            int endOffset,
            MeaningStatus meaningStatus,
            IMeaning selectedMeaning,
            Collection<IMeaning> meanings) {

        if (meanings.isEmpty()) {
            throw new RuntimeException("Word must have at least one meaning!");
        }
        List<IMeaning> mgs = new ArrayList<IMeaning>();

        float total = 0;
        for (IMeaning m : meanings) {
            total += m.getProbability();
        }
        if (total <= 0) {
            total = meanings.size();
        }

        for (IMeaning m : meanings) {
            mgs.add(new Meaning(m.getURL(), m.getProbability() / total, m.getKind(), m.getName()));
        }

        Collections.sort(mgs, Collections.reverseOrder());
        this.meanings = Collections.unmodifiableList(mgs);
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.meanings = mgs;
        this.meaningStatus = meaningStatus;
        this.selectedMeaning = selectedMeaning;

    }

    public int getStartOffset() {
        return startOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }

    /**
     *
     * @return the meanings sorted, the first having the highest probability
     */
    public List<IMeaning> getMeanings() {
        return meanings;
    }

    @Nullable
    public IMeaning getSelectedMeaning() {
        return selectedMeaning;
    }

    public MeaningStatus getMeaningStatus() {
        return meaningStatus;
    }

}
