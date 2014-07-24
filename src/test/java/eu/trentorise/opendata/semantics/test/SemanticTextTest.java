package eu.trentorise.opendata.semantics.test;

import eu.trentorise.opendata.semantics.IntegrityChecker;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningKind;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningStatus;
import eu.trentorise.opendata.semantics.model.knowledge.impl.Dict;
import eu.trentorise.opendata.semantics.model.knowledge.impl.Meaning;
import eu.trentorise.opendata.semantics.model.knowledge.impl.SemanticText;
import eu.trentorise.opendata.semantics.model.knowledge.impl.Sentence;
import eu.trentorise.opendata.semantics.model.knowledge.impl.Word;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.junit.Assert;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author David Leoni
 */
public class SemanticTextTest {
    
    @Test
    public void testDict_0(){
        IDict dict = new Dict();
        assertTrue(dict.getLocales().isEmpty());
        IntegrityChecker.checkDict(dict);
    }
    
    @Test
    public void testDict_1(){
        String text = "hello";
        IDict dict = new Dict(text);
        assertEquals(dict.getString(Locale.ENGLISH), text);   
        IntegrityChecker.checkDict(dict);
    }
    
    @Test
    public void testDict_2(){
        
        Dict dict_1 = new Dict("hello");
        Dict dict_2 = dict_1.putTranslation(Locale.ITALIAN, "ciao");
        
        assertEquals("hello", dict_1.getString(Locale.ENGLISH));
        assertEquals(null, dict_1.getString(Locale.ITALIAN));
        
        assertEquals("hello", dict_2.getString(Locale.ENGLISH));
        assertEquals("ciao", dict_2.getString(Locale.ITALIAN));        
        
        IntegrityChecker.checkDict(dict_1);
        IntegrityChecker.checkDict(dict_2);
    }    
    
    
    @Test
    public void testDict_3(){
        
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
    
    @Test
    public void testDictContains(){
        assertFalse(new Dict().contains(""));
        assertTrue(new Dict().putTranslation(Locale.ENGLISH, "Hello").contains("h"));
        
        IDict dict = new Dict().putTranslation(Locale.ENGLISH, "Hello")
                             .putTranslation(Locale.ITALIAN, new ArrayList(){{add("Ciao"); add("MONDO");}});
        assertTrue(dict.contains("ndo"));
        assertTrue(dict.contains(""));        
        assertFalse(dict.contains("123"));
        
        IntegrityChecker.checkDict(dict);
        
    }
    
    @Test
    public void testEquality(){
        assertEquals(new SemanticText(), new SemanticText());
        assertEquals(new SemanticText("a"), new SemanticText("a"));
        
        assertNotEquals(new SemanticText("a"), new SemanticText("b"));
        assertEquals(new SemanticText("a", Locale.ITALY), new SemanticText("a", Locale.ITALY));
        
        Sentence s1 = new Sentence(0,2);
        Sentence s2 = new Sentence(0,2);
        
        assertEquals(new SemanticText("ab", Locale.ITALY,s1), new SemanticText("ab", Locale.ITALY, s2));
        assertNotEquals(new SemanticText("ab", Locale.ITALY,s1), new SemanticText("ab", Locale.ITALY));
        
        assertEquals(new Meaning("a",0.1,MeaningKind.CONCEPT), new Meaning("a",0.1,MeaningKind.ENTITY));
        assertNotEquals(new Meaning("a",0.1,MeaningKind.CONCEPT), new Meaning("b",0.1,MeaningKind.CONCEPT));
        
        assertEquals(new Word(0,2,MeaningStatus.MISSING,null, new ArrayList()), new Word(0,2,MeaningStatus.MISSING,null, new ArrayList()));
        assertNotEquals(new Word(0,2,MeaningStatus.MISSING,null, new ArrayList()), new Word(0,3,MeaningStatus.MISSING,null, new ArrayList()));
        
    }
}
