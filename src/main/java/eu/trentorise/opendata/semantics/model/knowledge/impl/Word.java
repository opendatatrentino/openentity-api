/* 
 * Copyright 2013-2015   Trento Rise   trentorise.eu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.trentorise.opendata.semantics.model.knowledge.impl;

import static eu.trentorise.opendata.semantics.IntegrityChecker.checkSpan;
import eu.trentorise.opendata.semantics.model.knowledge.IMeaning;
import eu.trentorise.opendata.semantics.model.knowledge.IWord;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * @author David Leoni <david.leoni@unitn.it>
 * @date 23 Sept 2014
 */
@Immutable
public class Word implements IWord {

    private int startOffset;
    private int endOffset;
    private List<? extends Meaning> meanings;
    @Nullable
    private IMeaning selectedMeaning;
    @Nullable
    private MeaningStatus meaningStatus;

    /**
     * Constructor for a Word with only one meaning. Meaning probabilities are
     * normalized so total sum is 1.0
     *
     * @param startOffset
     * @param endOffset the position copyOf the character immediately *after* the
 word itself. Position is absolute with respect to the text stored in the
 SemanticText container.
     */
    public Word(int startOffset,
            int endOffset,
            MeaningStatus meaningStatus,
            @Nullable final IMeaning selectedMeaning) {
        this(startOffset, endOffset, meaningStatus, selectedMeaning,
                selectedMeaning == null
                ? new ArrayList()
                : new ArrayList() {
                    {
                        add(selectedMeaning);
                    }
                });
    }

    /**
     * Constructors a Word. Meaning probabilities are stored deduplicated and
     * normalized so total sum is 1.0 . The selected meaning if not null is also
     * merged in the meanings.
     *
     * @param startOffset
     * @param endOffset the position copyOf the character immediately *after* the
 word itself. Position is absolute with respect to the text stored in the
 SemanticText container.
     * @param meaningStatus
     * @param selectedMeaning
     * @param meanings a new collection is created internally to store the
     * deduplicated meanings. If the selectedMeaning is present it is merged in
     * the stored meanings
     */
    public Word(int startOffset,
            int endOffset,
            MeaningStatus meaningStatus,
            @Nullable IMeaning selectedMeaning,
            Collection<? extends IMeaning> meanings) {

        checkSpan(startOffset, endOffset);

        normalizeMeanings(meanings, selectedMeaning);

        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.meaningStatus = meaningStatus;

    }

    public int getStartOffset() {
        return startOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }

    /**
     * @return the meanings sorted, the first having the highest probability
     */
    public List<? extends IMeaning> getMeanings() {
        return meanings;
    }

    @Nullable
    public IMeaning getSelectedMeaning() {
        return selectedMeaning;
    }

    public MeaningStatus getMeaningStatus() {
        return meaningStatus;
    }

    /**
     * so serialization libraries don't complain
     */
    protected Word() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.startOffset;
        hash = 67 * hash + this.endOffset;
        hash = 67 * hash + (this.meanings != null ? this.meanings.hashCode() : 0);
        hash = 67 * hash + (this.selectedMeaning != null ? this.selectedMeaning.hashCode() : 0);
        hash = 67 * hash + (this.meaningStatus != null ? this.meaningStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        if (this.startOffset != other.startOffset) {
            return false;
        }
        if (this.endOffset != other.endOffset) {
            return false;
        }
        if (this.meanings != other.meanings && (this.meanings == null || !this.meanings.equals(other.meanings))) {
            return false;
        }
        if (this.selectedMeaning != other.selectedMeaning && (this.selectedMeaning == null || !this.selectedMeaning.equals(other.selectedMeaning))) {
            return false;
        }
        if (this.meaningStatus != other.meaningStatus) {
            return false;
        }
        return true;
    }

    public Word(IWord word) {
        this.startOffset = word.getStartOffset();
        this.endOffset = word.getEndOffset();
        this.meaningStatus = word.getMeaningStatus();
        List<Meaning> ms = new ArrayList();
        for(IMeaning m : word.getMeanings()){
            ms.add(Meaning.copyOf(m));
        }
        this.meanings = Collections.unmodifiableList(ms);
        this.selectedMeaning = word.getSelectedMeaning();
    }

    public static Word copyOf(IWord word) {
        if (word instanceof Word) {
            return (Word) word;
        } else {
            return new Word(word);
        }
    }

    /**
     * A new word is returned with the provided meaning added to the existing
     * meanings and set as the selected meaning.
     */
    public Word with(MeaningStatus status, IMeaning selectedMeaning) {       
        Word ret = new Word(this);
        ret.meaningStatus = status;
        ret.normalizeMeanings(meanings, selectedMeaning);
        return ret;
    }

    /**
     * A new word is returned with the provided meanings merged to the existing
     * ones.
     */
    public Word add(Collection<? extends IMeaning> meanings) {

        Set<IMeaning> newMeanings = new HashSet();

        for (IMeaning m1 : this.meanings) {
            newMeanings.add(m1);
        }
        for (IMeaning m2 : meanings) {
            newMeanings.add(m2);
        }
        return this.with(newMeanings);
    }

    /**
     * Returns a new word with the provided meanings. If current selected
     * meaning is not among the new meanings, the stored meanings will also have
     * the current selected meaning.
     */
    public Word with(Collection<? extends IMeaning> meanings) {
        Word ret = new Word(this);
        ret.normalizeMeanings(meanings, selectedMeaning);
        return ret;
    }

    /**
     * Normalizes the provided meaning probabilities and the selected meaning
     * accordingly
     *
     * @param meanings won't be changed by the method
     * @param selectedMeaning
     */
    private void normalizeMeanings(Collection<? extends IMeaning> meanings, IMeaning selectedMeaning) {

        Set<IMeaning> dedupMeanings = new HashSet(meanings);
        if (selectedMeaning != null){
            dedupMeanings.add(selectedMeaning);
        }

        float total = 0;
        for (IMeaning m : dedupMeanings) {
            total += m.getProbability();
        }
        if (total <= 0) {
            total = dedupMeanings.size();
        }

        Meaning newSelectedMeaning = null;
        List<Meaning> mgs = new ArrayList();
        for (IMeaning m : dedupMeanings) {
            Meaning newM = new Meaning(m.getURL(), m.getProbability() / total, m.getKind(), m.getName());
            if (newM.equals(selectedMeaning)) {
                newSelectedMeaning = newM;
            }
            mgs.add(newM);
        }

        Collections.sort(mgs, Collections.reverseOrder());

        this.meanings = Collections.unmodifiableList(mgs);
        this.selectedMeaning = newSelectedMeaning;
    }

}
