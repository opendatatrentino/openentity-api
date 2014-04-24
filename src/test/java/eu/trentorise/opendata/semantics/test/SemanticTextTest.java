package eu.trentorise.opendata.semantics.test;

import eu.trentorise.opendata.semantics.IntegrityChecker;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.model.knowledge.impl.Dict;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
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
}
