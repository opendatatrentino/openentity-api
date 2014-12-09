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
package eu.trentorise.opendata.semantics.impl.model.knowledge;

import eu.trentorise.opendata.semantics.model.knowledge.IMeaning;
import eu.trentorise.opendata.semantics.model.knowledge.ISemanticText;
import eu.trentorise.opendata.semantics.model.knowledge.ISentence;
import eu.trentorise.opendata.semantics.model.knowledge.IWord;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Class to hold semantically enriched text, suitable for data transfer to the
 * browser.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 23 Sept 2014
 */
@Immutable
public class SemanticText implements Serializable, ISemanticText {

    private String text;
    private Locale locale;

    private List<? extends Sentence> sentences;

    public SemanticText() {
        this.text = "";
        this.locale = Locale.ENGLISH;
        this.sentences = new ArrayList();
    }

    /**
     * Creates a semantic text copyOf one word with only one meaning.
     */
    public SemanticText(String text, Locale locale, MeaningStatus meaningStatus, @Nullable final IMeaning selectedMeaning) {
        this(text, locale, new Sentence(0, text.length(), new Word(0, text.length(), meaningStatus, selectedMeaning,
                selectedMeaning == null
                        ? new ArrayList()
                        : new ArrayList() {
                    {
                        add(selectedMeaning);
                    }
                })));
    }

    public SemanticText(String text, Locale locale, MeaningStatus meaningStatus, @Nullable IMeaning selectedMeaning, Collection<? extends IMeaning> meanings) {
        this(text, locale, new Sentence(0, text.length(), new Word(0, text.length(), meaningStatus, selectedMeaning, meanings)));
    }

    /**
     * Creates SemanticText with the provided string. Locale is set to english
     * and the string is not enriched.
     *
     * @param text
     */
    public SemanticText(String text) {
        this(text, Locale.ENGLISH);
    }

    /**
     * Creates SemanticText with the provided string. Locale is set to english
     * and the string is not enriched.
     *
     * @param text
     * @param locale
     */
    public SemanticText(String text, Locale locale) {
        this();
        if (text == null) {
            throw new IllegalArgumentException("Text in SemnaticText must not be null!");
        }
        this.text = text;
        this.locale = Locale.ENGLISH;
        this.locale = locale;
    }

    /**
     * @deprecated @param sentences a list copyOf sentences. Internally, a new
     * copy copyOf it is created.
     */
    public SemanticText(String text, @Nullable Locale locale, Collection<? extends ISentence> sentences) {
        this(text, locale);

        List<Sentence> lst = new ArrayList();
        for (ISentence ss : sentences) {
            lst.add(Sentence.copyOf(ss));
        }
        this.sentences = Collections.unmodifiableList(lst);
    }

    /**
     * @param sentence a list of sentences. Internally, a new copy copyOf it is
     * created.
     * @deprecated
     */
    public SemanticText(String text, Locale locale, final ISentence sentence) {
        this(text, locale, new ArrayList<ISentence>() {
            {
                add(sentence);
            }
        });
    }

    public SemanticText(final String text, Locale locale, final List<? extends IWord> words) {
        this(text, locale, new ArrayList<ISentence>() {
            {
                add(new Sentence(0, text.length(), words));
            }
        });
    }

    /**
     * todo make private
     */
    protected SemanticText(ISemanticText semText) {
        this(semText.getText(), semText.getLocale(), semText.getSentences());
    }

    public static SemanticText copyOf(ISemanticText semText) {
        if (semText instanceof SemanticText) {
            return (SemanticText) semText;
        } else {
            return new SemanticText(semText);
        }
    }

    /**
     * @deprecated
     */
    public List<? extends ISentence> getSentences() {
        return sentences;
    }

    @Nullable
    public Locale getLocale() {
        return locale;
    }

    public String getText() {
        return text;
    }

    /**
     * @deprecated
     */
    public String getText(ISentence sentence) {
        return text.substring(sentence.getStartOffset(), sentence.getEndOffset());
    }

    public String getText(IWord word) {
        return text.substring(word.getStartOffset(), word.getEndOffset());
    }

    /**
     * Returns a description copyOf the SemanticText
     */
    @Override
    public String toString() {
        IWord w = getWord();
        String wordDescr = "";
        if (w != null) {
            wordDescr = "Single word semantic text, with ";
        }
        return wordDescr + "locale: " + locale + ", text: " + text;
        // todo put more meaningful word descr
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.text != null ? this.text.hashCode() : 0);
        hash = 17 * hash + (this.locale != null ? this.locale.hashCode() : 0);
        hash = 17 * hash + (this.sentences != null ? this.sentences.hashCode() : 0);
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
        final SemanticText other = (SemanticText) obj;
        if ((this.text == null) ? (other.text != null) : !this.text.equals(other.text)) {
            return false;
        }
        if (this.locale != other.locale && (this.locale == null || !this.locale.equals(other.locale))) {
            return false;
        }
        if (this.sentences != other.sentences && (this.sentences == null || !this.sentences.equals(other.sentences))) {
            return false;
        }
        return true;
    }

    public Word getWord() {
        if (!(getSentences().size() == 1)) {
            return null;
        }
        Sentence sentence = sentences.get(0);

        if (!(sentence.getWords().size() == 1)) {
            return null;
        }

        Word word = sentence.getWords().get(0);
        if (word.getStartOffset() == 0
                && word.getEndOffset() == getText().length()) {
            return word;
        } else {
            return null;
        }
    }

    /**
     * If the semantic text is made copyOf only one word spanning the whole
     * text, the provided meaning is added to the existing meanings and set as
     * the main one.
     *
     */
    public ISemanticText with(MeaningStatus status, IMeaning meaning) {
        SemanticText ret = new SemanticText(this);
        IWord oldWord = getWord();
        IWord newWord;
        if (oldWord == null) {
            newWord = new Word(0, text.length(), status, meaning);
        } else {
            newWord = new Word(oldWord).with(status, meaning);
        }

        List<Sentence> arr = new ArrayList();
        arr.add(new Sentence(0, text.length(), newWord));
        ret.sentences = Collections.unmodifiableList(arr);
        return ret;
    }

    public List<? extends Word> getWords() {
        List<Word> ret = new ArrayList();
        for (Sentence s : sentences) {
            for (Word w : s.getWords()) {
                ret.add(w);
            }
        }
        return Collections.unmodifiableList(ret);
    }

    /**
     * Returns a copy copyOf this object with the provided lcoale set.
     *
     * @param locale null if unknown
     */
    public SemanticText with(Locale locale) {
        SemanticText ret = new SemanticText(this);
        ret.locale = locale;
        return ret;
    }

    /**
     * Returns a copy copyOf this object with the provided text set.
     */
    public SemanticText with(String text) {
        SemanticText ret = new SemanticText(this);
        ret.text = text;
        for (ISentence s : sentences) {
            if (s.getEndOffset() > text.length()) {
                throw new IllegalArgumentException("Tried to change text of semantic text, but there is a sentence longer thean the provided text!");
            }
        }
        return ret;
    }

    /**
     * Returns a copy copyOf this object with the provided words set. Existing
     * words won't be present in the returned object.
     */
    public SemanticText with(List<? extends IWord> words) {
        SemanticText ret = new SemanticText(this);

        Sentence s = new Sentence(0, text.length(), words);
        List<Sentence> ss = new ArrayList();
        ss.add(s);
        ret.sentences = Collections.unmodifiableList(ss);
        return ret;
    }

    /**
     * Returns a new semantic text having existing words plus the provided one.
     * If the new word exactly covers another one, the existing is replaced and
     * meanings copyOf the new one will be merged with the old one. If the word
     * partially overlaps with other ones, existing overlapping words are
     * removed.
     */
    public SemanticText update(IWord word) {
        List<IWord> words = new ArrayList();
        words.add(word);
        return update(words);
    }

    /**
     * @return null if iter has no next
     */
    @Nullable
    private IWord nextWord(Iterator<? extends IWord> iter) {
        if (iter.hasNext()) {
            return iter.next();
        } else {
            return null;
        }
    }

    /**
     * Returns a new semantic text having existing words plus the provided ones.
     * If new words overlaps with other ones, existing overlapping words are
     * removed. If meaning status copyOf a provided word is null, then any
     * existing words overlapping it will be removed. If a word is precisely
     * overlapping an existing one, resulting word will have meaning status and
     * selected meaning copyOf provided word and the list copyOf meanings will
     * be a merge copyOf the meanings found in the provided word plus the
     * meanings copyOf the existing word.
     */
    public SemanticText update(List<? extends IWord> wordsToAdd) {

        if (wordsToAdd.size() == 0) {
            return this;
        } else {
            List<? extends IWord> origWords = getWords();
            List<IWord> newWords = new ArrayList();
            Iterator<? extends IWord> origWordIter = origWords.iterator();
            IWord curOrigWord = null;

            curOrigWord = nextWord(origWordIter);

            for (IWord wordToAdd : wordsToAdd) {

                // adds orig words until they overlap with new one
                while (curOrigWord != null
                        && curOrigWord.getEndOffset() <= wordToAdd.getStartOffset()) {
                    newWords.add(curOrigWord);
                    curOrigWord = nextWord(origWordIter);
                }
                if (curOrigWord != null // words coincide, we merge
                        && wordToAdd.getStartOffset() == curOrigWord.getStartOffset()
                        && wordToAdd.getEndOffset() == curOrigWord.getEndOffset()) {

                    if (wordToAdd.getMeaningStatus() != null) {
                        newWords.add(Word.copyOf(wordToAdd).add(curOrigWord.getMeanings()));
                    }

                } else { // words don't coincide
                    if (wordToAdd.getMeaningStatus() != null) {
                        newWords.add(wordToAdd);
                    }
                }
                while (curOrigWord != null && curOrigWord.getStartOffset() < wordToAdd.getEndOffset()) {
                    curOrigWord = nextWord(origWordIter);
                }
            }

            while (curOrigWord != null
                    && curOrigWord.getStartOffset() >= wordsToAdd.get(wordsToAdd.size() - 1).getEndOffset()) {
                newWords.add(curOrigWord);
                curOrigWord = nextWord(origWordIter);
            }

            return this.with(newWords);

        }

    }

}
