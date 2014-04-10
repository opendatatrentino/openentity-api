package eu.trentorise.opendata.semantics.model.knowledge;

import java.util.List;
import javax.annotation.Nullable;

/**
 * Represents a basic unit of text. Can contain multiwords, that is, sequences
 * of words separated by spaces like "hot dog". Implementations of this interface
 * must be immutable.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 10 Apr 2014
 */
public interface ISemanticToken {

    /**
     * @return absolute end offset based on the text of the SemanticText
     * containing this token. The offset is positioned *after* the last
     * character of the token, so for token "see" in "I see a car" text
     * endOffset would be 5.
     */
    int getEndOffset();

    MeaningStatus getMeaningStatus();

    /**
     *
     * @return the sorted meanings, the first having the highest probability
     */
    List<IMeaning> getMeanings();

    @Nullable
    IMeaning getSelectedMeaning();

    /**
     * An absolute start offset based on the text of the SemanticText containing
     * this token. The offset is positioned at the first character of the token,
     * so for token "see" in "I see a car" text startOffset would be 2.
     */
    int getStartOffset();

}
