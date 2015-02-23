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

import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Lightweight interface to hold semantically enriched text. There are only
 * tokens and no multiwords. Text is only stored in root ISemanticText object
 * and offsets are always absolute and calculated with respects to it.
 * Overlappings are not allowed. Implementations of this interface must be
 * immutable and implement equals() and hashCode() methods.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 23 Sept 2014
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
     * @deprecated we don't really need sentences (for now)
     *
     * @return the sentences in which the text is divided.
     */
    List<? extends ISentence> getSentences();

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

    /**
     * Convenience method. If the semantic text is made of only one word
     * spanning the whole text, it is given back.
     *
     * @return the word if the semantic text is made by one word, null
     * otherwise.
     */
    @Nullable
    public IWord getWord();

    /**
     * Gets all the words in the text (regardless of the sentences).
     */
    List<? extends IWord> getWords();
    
    
}
