package eu.trentorise.opendata.semantics.model.knowledge.impl;

import eu.trentorise.opendata.semantics.model.knowledge.ISemanticToken;
import eu.trentorise.opendata.semantics.model.knowledge.ISemanticSentence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 10 Apr 2014
 */
@Immutable
public class SemanticSentence implements ISemanticSentence {

    private int startOffset;
    private int endOffset;

    private List<ISemanticToken> tokens;

    private SemanticSentence(){
    }
    
    public SemanticSentence(int startOffset, int endOffset, List<SemanticToken> tokens){
        this.startOffset = startOffset;
        this.endOffset = endOffset; 
        List<ISemanticToken> lst = new ArrayList<ISemanticToken>();        
        for (ISemanticToken st : tokens){
            lst.add(st);
        }      
        this.tokens = Collections.unmodifiableList(lst);
    }
    


    @Override
    public List<ISemanticToken> getTokens() {
        return tokens;
    }

     
    @Override
    public int getStartOffset() {
        return startOffset;
    }

    @Override
    public int getEndOffset() {
        return endOffset;
    }

}
