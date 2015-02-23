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
package eu.trentorise.opendata.semantics.model.knowledge.impl;

import eu.trentorise.opendata.semantics.IntegrityChecker;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * To access protected fields
 * @author David Leoni
 */
public class WhiteBoxTesting {
    
    @Test
    public void testDict() {

        Dict dict_1 = new Dict("hello");
        Dict dict_2 = new Dict(dict_1);

        assertEquals("hello", dict_1.getString(Locale.ENGLISH));

        assertEquals("hello", dict_2.getString(Locale.ENGLISH));

        List<String> words = new ArrayList<String>();
        words.add("ciao");
        words.add("buongiorno");

        Dict dict_3 = dict_2.putTranslation(Locale.ITALIAN, words);

        assertEquals(null, dict_1.getString(Locale.ITALIAN));
        assertEquals(null, dict_2.getString(Locale.ITALIAN));

        assertEquals("ciao", dict_3.getString(Locale.ITALIAN));
        assertEquals("ciao", dict_3.getStrings(Locale.ITALIAN).get(0));
        assertEquals("buongiorno", dict_3.getStrings(Locale.ITALIAN).get(1));

        IntegrityChecker.checkDict(dict_1);
        IntegrityChecker.checkDict(dict_2);
        IntegrityChecker.checkDict(dict_3);
    }
        
    
}
