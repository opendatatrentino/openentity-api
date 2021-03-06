/*
 * Copyright 2015 Trento Rise.
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
package eu.trentorise.opendata.semantics.test;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.Checker;
import eu.trentorise.opendata.semantics.services.mock.MockEkb;
import eu.trentorise.opendata.traceprov.types.Concept;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author David Leoni
 */
public class CheckerTest {
    
    private Checker checker;
    
    @Before
    public void before(){
        checker = Checker.of(new MockEkb());
    }
    
    @Test
    public void testChecker(){
        checker.checkConcept(Concept.builder().setId("bla").setName(Dict.of()).setDescription(Dict.of()).build());
        
    }
}
