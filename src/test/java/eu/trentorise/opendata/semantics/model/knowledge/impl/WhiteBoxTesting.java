package eu.trentorise.opendata.semantics.impl.model.knowledge;

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
