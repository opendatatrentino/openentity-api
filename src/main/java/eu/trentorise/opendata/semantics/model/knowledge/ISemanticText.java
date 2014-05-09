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
import java.util.Locale;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Lightweight interface to hold semantically enriched text. There are only
 * tokens and no multiwords. Text is only stored in root ISemanticText object
 * and offsets are always absolute and calculated with respects to it.
 * Overlappings are not allowed. Implementations of this interface must be
 * immutable.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 10 Apr 2014
 */
@Immutable
public interface ISemanticText {

    /**
     * Gets the language of the whole text
     *
     * @return the locale of the whole text
     */
    @Nullable
    Locale getLocale();

    /**
     * Gets the sentences of the text
     *
     * @return the sentences in which the text is divided.
     */
    List<ISentence> getSentences();

    /**
     * Gets the original text
     *
     * @return the whole text, without annotations
     */
    String getText();

    /**
     * Gets the text of a given sentence
     *
     * @return the text of the sentence, without annotations
     */
    String getText(ISentence sentence);

    /**
     * Gets the text of a given word
     *
     * @return the text of the word, without annotations
     */
    String getText(IWord word);
    
}
