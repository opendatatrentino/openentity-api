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
import javax.annotation.concurrent.Immutable;

/**
 * A sentence contained in a ISemanticText. Implementations of this interface
 * must be immutable.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 11 Apr 2014
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

    List<IWord> getWords();

}
