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

import static eu.trentorise.opendata.semantics.IntegrityChecker.checkSpan;
import eu.trentorise.opendata.semantics.model.knowledge.ISentence;
import eu.trentorise.opendata.semantics.model.knowledge.IWord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 21 Aug 2014
 */
@Immutable
public class Sentence implements ISentence {

    private int startOffset;
    private int endOffset;

    private List<? extends IWord> words;

    public Sentence(int startOffset, int endOffset) {
        checkSpan(startOffset, endOffset);
        
        this.startOffset = startOffset;
        this.endOffset = endOffset;

        this.words = Collections.unmodifiableList(new ArrayList<IWord>());
    }

    public Sentence(int startOffset, int endOffset, List<? extends IWord> words) {
        this(startOffset, endOffset);
        if (!words.isEmpty()) {
            int lowerBound = words.get(0).getStartOffset();
            int upperBound = words.get(words.size() - 1).getEndOffset();
            if (lowerBound < startOffset
                    || upperBound > endOffset) {
                throw new IllegalArgumentException("Tried to create a sentence, but word offsets exceed sentence bounds!Expected: [" + startOffset + "," + endOffset + "] - Found: [" + lowerBound + "," + upperBound + "]");
            }
        }

        List<IWord> lst = new ArrayList<IWord>();
        IWord prevWord = null;
        for (IWord st : words) {
            if (prevWord != null && st.getStartOffset() < prevWord.getEndOffset()){                  
                throw new IllegalArgumentException("There cannot be overlapping words! Word " + prevWord + " overlaps with word " + st + " !");                
            }
            prevWord = st;
            lst.add(st);
        }
        this.words = Collections.unmodifiableList(lst);
    }

    /**
     * Creates a sentence of one word.
     */
    public Sentence(int startOffset, int endOffset, IWord word) {
        this(startOffset, endOffset);
        List<IWord> lst = new ArrayList<IWord>();
        lst.add(word);
        this.words = Collections.unmodifiableList(lst);
    }

    @Override
    public List<? extends IWord> getWords() {
        return words;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }

    /** so serialization libraries don't complain */
    protected Sentence(){
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.startOffset;
        hash = 37 * hash + this.endOffset;
        hash = 37 * hash + (this.words != null ? this.words.hashCode() : 0);
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
        final Sentence other = (Sentence) obj;
        if (this.startOffset != other.startOffset) {
            return false;
        }
        if (this.endOffset != other.endOffset) {
            return false;
        }
        if (this.words != other.words && (this.words == null || !this.words.equals(other.words))) {
            return false;
        }
        return true;
    }
    
    
}
