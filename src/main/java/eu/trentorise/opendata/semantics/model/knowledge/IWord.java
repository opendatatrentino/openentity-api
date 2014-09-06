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
package eu.trentorise.opendata.semantics.model.knowledge;

import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Represents a basic unit of text. Can contain multiwords, that is, sequences
 * of words separated by spaces like "hot dog". Implementations of this
 * interface must be immutable and implement equals() and hashCode() methods.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 11 Apr 2014
 */
@Immutable
public interface IWord {

    /**
     * Returns end offset based on the text of the SemanticText containing this
     * token. Positiong is absolute with reference to the SemanticText
     * containing the word and the offset is positioned *after* the last
     * character of the word, so for word "see" in "I see a car" text endOffset
     * would be 5.
     */
    int getEndOffset();

    /**
     * <ol>
     * <li> NEW: {@link #getSelectedMeaning()} returns a meaning of an
     * entity/concept yet to be created on the server, with a temporary URL</li>
     *  
     * <li> SELECTED: {@link #getSelectedMeaning()} will return the selected
     * meaning</li>
     * </ol>
     * @see MeaningStatus
     */
    MeaningStatus getMeaningStatus();

    /**
     *
     * Returns the sorted meanings, the first having the highest probability
     */
    List<IMeaning> getMeanings();

    /**
     *
     * Returns the selected meaning. If {@link #getMeaningStatus()} is different
     * from SELECTED or NEW it MAY return a meaning, which would be the 'best' among
     * the getMeanings(). In case {@link #getMeaningStatus()} returns NEW, this
     * function MUST return a meaning of an entity/concept yet to be created on
     * the server, with a temporary URL.
     */
    @Nullable
    IMeaning getSelectedMeaning();

    /**
     * An absolute start offset based on the text of the SemanticText containing
     * this token. The offset is positioned at the first character of the token,
     * so for token "see" in "I see a car" text startOffset would be 2.
     */
    int getStartOffset();

    /**
     * A new word is returned with the provided meaning added to the existing
     * meanings and set as the main one.
     */
    public IWord withMeaning(MeaningStatus status, IMeaning meaning);
}
