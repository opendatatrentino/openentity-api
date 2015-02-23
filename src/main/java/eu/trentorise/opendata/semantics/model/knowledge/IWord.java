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
package eu.trentorise.opendata.semantics.model.knowledge;

import eu.trentorise.opendata.semantics.services.IEntityService;
import java.util.Collection;
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
     *
     * @see MeaningStatus
     */
    MeaningStatus getMeaningStatus();

    /**
     *
     * Returns the sorted meanings, the first having the highest probability
     */
    List<? extends IMeaning> getMeanings();

    /**
     *
     * Returns the selected meaning. If {@link #getMeaningStatus()} is different
     * from SELECTED or NEW it MAY return a meaning, which would be the 'best'
     * among the getMeanings(). In case {@link #getMeaningStatus()} returns NEW,
     * this function MUST return a meaning of an entity/concept yet to be
     * created on the server, with a temporary URL. Temporary URLs are
     * recognized with {@link IEntityService#isTemporaryURL(java.lang.String)}
     * method.
     */
    @Nullable
    IMeaning getSelectedMeaning();

    /**
     * An absolute start offset based on the text of the SemanticText containing
     * this token. The offset is positioned at the first character of the token,
     * so for token "see" in "I see a car" text startOffset would be 2.
     */
    int getStartOffset();

}
