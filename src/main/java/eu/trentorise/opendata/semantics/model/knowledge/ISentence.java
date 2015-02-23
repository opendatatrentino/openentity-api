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
import javax.annotation.concurrent.Immutable;

/**
 * A sentence contained in a ISemanticText. Implementations of this interface
 * must be immutable. Implementations of this interface must be
 * immutable and implement equals() and hashCode().
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 21 Aug 2014
 */
@Immutable
public interface ISentence {

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

    List<? extends IWord> getWords();

}
