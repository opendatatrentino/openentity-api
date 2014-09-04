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

import eu.trentorise.opendata.semantics.IntegrityChecker;
import eu.trentorise.opendata.semantics.model.knowledge.IMeaning;
import eu.trentorise.opendata.semantics.model.knowledge.ISemanticText;
import eu.trentorise.opendata.semantics.model.knowledge.ISentence;
import eu.trentorise.opendata.semantics.model.knowledge.IWord;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Class to hold semantically enriched text, suitable for data transfer to the
 * browser.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 11 Apr 2014
 */
@Immutable
public class SemanticText implements Serializable, ISemanticText {

    private String text;
    private Locale locale;

    private List<? extends ISentence> sentences;

    public SemanticText() {
        this.text = "";
        this.locale = Locale.ENGLISH;
        this.sentences = new ArrayList<ISentence>();
    }

    /**
     * Creates a semantic text of one word with only one meaning.
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
     * @param sentences a list of sentences. Internally, a new copy of it is
     * created.
     */
    public SemanticText(String text, @Nullable Locale locale, Collection<? extends ISentence> sentences) {
        this(text, locale);

        List<ISentence> lst = new ArrayList();
        for (ISentence ss : sentences) {
            lst.add(ss);
        }
        this.sentences = Collections.unmodifiableList(lst);
    }

    /**
     * @param sentence a list of sentences. Internally, a new copy of it is
     * created.
     */
    public SemanticText(String text, Locale locale, final ISentence sentence) {
        this(text, locale, new ArrayList<ISentence>() {
            {
                add(sentence);
            }
        });
    }

    public SemanticText(ISemanticText semText) {
        this(semText.getText(), semText.getLocale(), semText.getSentences());
    }

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

    public String getText(ISentence sentence) {
        return text.substring(sentence.getStartOffset(), sentence.getEndOffset());
    }

    public String getText(IWord word) {
        return text.substring(word.getStartOffset(), word.getEndOffset());
    }

    /**
     * Returns a description of the SemanticText
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

    public IWord getWord() {
        if (!(getSentences().size() == 1)) {
            return null;
        }
        ISentence sentence = sentences.get(0);

        if (!(sentence.getWords().size() == 1)) {
            return null;
        }

        IWord word = sentence.getWords().get(0);
        if (word.getStartOffset() == 0
                && word.getEndOffset() == getText().length()) {
            return word;
        } else {
            return null;
        }
    }

    public ISemanticText withMeaning(MeaningStatus status, IMeaning meaning) {
        SemanticText ret = new SemanticText(this);
        IWord oldWord = getWord();
        IWord newWord;
        if (oldWord == null) {
            newWord = new Word(0, text.length(), status, meaning);
        } else {
            newWord = new Word(oldWord).withMeaning(status, meaning);
        }

        List<ISentence> arr = new ArrayList();
        arr.add(new Sentence(0, text.length(), newWord));
        ret.sentences = Collections.unmodifiableList(arr);
        return ret;
    }


    public ISemanticText withLocale(Locale locale) {
        SemanticText ret = new SemanticText(this);
        ret.locale = locale;
        return ret;
    }

    public ISemanticText withText(String text) {
        SemanticText ret = new SemanticText(this);
        ret.text = text;
        for (ISentence s : sentences) {
            if (s.getEndOffset() > text.length()) {
                throw new IllegalArgumentException("Tried to change text of semantic text, but there is a sentence longer thean the provided text!");
            }
        }
        return ret;
    }
}
