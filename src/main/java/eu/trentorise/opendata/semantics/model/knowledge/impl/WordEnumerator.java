/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.trentorise.opendata.semantics.model.knowledge.impl;

import eu.trentorise.opendata.semantics.model.knowledge.ISemanticText;
import eu.trentorise.opendata.semantics.model.knowledge.ISentence;
import eu.trentorise.opendata.semantics.model.knowledge.IWord;
import java.util.Enumeration;

/**
 *
 * @author David Leoni
 */
public class WordEnumerator implements Enumeration<IWord> {

    ISemanticText semanticText;
    int sentenceIndex;
    int wordIndex;

    public WordEnumerator(ISemanticText semanticText) {
        this.semanticText = semanticText;
        this.sentenceIndex = 0;
        this.wordIndex = 0;
    }

    public boolean hasMoreElements() {
        throw new UnsupportedOperationException("todo implement me");
    }

    public IWord nextElement() {
        throw new UnsupportedOperationException("todo implement me");
        /*
         if (sentenceIndex < semanticText.getSentences().size()) {
         ISentence sentence = semanticText.getSentences().get(sentenceIndex);
         int wordsSize = sentence.getWords().size();
         if (wordIndex < wordsSize) {
         IWord ret = sentence.getWords().get(wordIndex);
         if (wordIndex == wordsSize - 1) {
         sentenceIndex += 1;
         wordIndex = 0;
         }
         return ret;
         } else {

         }
         } 
         */

    }

}
