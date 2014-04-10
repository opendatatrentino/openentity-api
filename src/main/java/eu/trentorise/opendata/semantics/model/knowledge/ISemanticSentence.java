package eu.trentorise.opendata.semantics.model.knowledge;

import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * A sentence contained in an ISemanticText. Implementations of this interface
 * must be immutable.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 10 Apr 2014
 */
@Immutable
public interface ISemanticSentence {

    /**
     * An absolute end offset based on the text of the SemanticText containing
     * this sentence. The offset is positioned *after* the last character of the
     * sentence, so for sentence "I see" endOffset would be 5.
     */
    int getEndOffset();

    /**
     * An absolute start offset based on the text of the SemanticText containing
     * this sentence. The offset is positioned at the first character of the
     * sentence, so for sentence "I see" endOffset would be 0.
     */    
    int getStartOffset();

    List<ISemanticToken> getTokens();

}
