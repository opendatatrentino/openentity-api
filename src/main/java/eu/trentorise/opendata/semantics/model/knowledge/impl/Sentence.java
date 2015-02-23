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

    private List<? extends Word> words;

    public Sentence(int startOffset, int endOffset) {
        checkSpan(startOffset, endOffset);
        
        this.startOffset = startOffset;
        this.endOffset = endOffset;

        this.words = Collections.unmodifiableList(new ArrayList());
    }

    private Sentence(ISentence sentence){
        this.startOffset = sentence.getStartOffset();
        this.endOffset = sentence.getEndOffset();
        List<Word> ws = new ArrayList();
        for (IWord w : sentence.getWords()){
            Word wz = Word.copyOf(w);
            ws.add(wz);
        }
        this.words = Collections.unmodifiableList(ws);
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

        List<Word> lst = new ArrayList();
        IWord prevWord = null;
        for (IWord w : words) {
            if (prevWord != null && w.getStartOffset() < prevWord.getEndOffset()){                  
                throw new IllegalArgumentException("There cannot be overlapping words! Word " + prevWord + " overlaps with word " + w + " !");                
            }
            prevWord = w;
            lst.add(Word.copyOf(w));
        }
        this.words = Collections.unmodifiableList(lst);
    }

    /**
     * Creates a sentence copyOf one word.
     */
    public Sentence(int startOffset, int endOffset, IWord word) {
        this(startOffset, endOffset);
        List<Word> lst = new ArrayList<Word>();
        lst.add(Word.copyOf(word));
        this.words = Collections.unmodifiableList(lst);
    }

    static public Sentence copyOf(ISentence ss) {
        if (ss instanceof Sentence){
            return (Sentence) ss;
        } else {
            return new Sentence(ss);
        }
    }
    
    public List<? extends Word> getWords() {
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
