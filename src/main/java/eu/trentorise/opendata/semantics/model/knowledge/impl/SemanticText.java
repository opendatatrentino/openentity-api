package eu.trentorise.opendata.semantics.model.knowledge.impl;

import eu.trentorise.opendata.semantics.model.knowledge.ISemanticSentence;
import eu.trentorise.opendata.semantics.model.knowledge.ISemanticText;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * 
 * @author David Leoni <david.leoni@unitn.it>
 * @date 10 Apr 2014
 */
public class SemanticText implements Serializable, ISemanticText {

    private String text;
    private Locale locale;

    private List<ISemanticSentence> sentences;

    public SemanticText() {
        this.text = "";
        this.locale = Locale.ENGLISH;
        this.sentences = new ArrayList<ISemanticSentence>();
    }

    /**
     * Creates OdrText with the provided string. The string is not enriched.
     *
     * @param s
     */
    public SemanticText(String s) {
        this();
        this.text = s;
        this.locale = Locale.ENGLISH;
    }

    public SemanticText(String text, Locale locale, List<SemanticSentence> sentences) {
        this();
        this.text = text;
        this.locale = locale;        
        
        List<ISemanticSentence> lst = new ArrayList<ISemanticSentence>();
        for (ISemanticSentence ss : sentences) {
            lst.add(ss);
        }
        this.sentences = Collections.unmodifiableList(lst);
    }

    @Override
    public List<ISemanticSentence> getSentences() {
        return sentences;
    }

    @Override
    public Locale getLocale(){
        return locale;
    }
    
    @Override
    public String getText() {
        return text;
    }

}
