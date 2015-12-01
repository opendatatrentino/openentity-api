package eu.trentorise.opendata.semantics.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import eu.trentorise.opendata.commons.TodConfig;
import eu.trentorise.opendata.semantics.model.entity.Etype;

public class TestEtype {

    @BeforeClass
    public static void setUpClass() {
        TodConfig.init(TestEtype.class);
    }
    
    @Test
    @Ignore
    public void testAttrDefBy(){
       throw new UnsupportedOperationException("TODO IMPLEMENT ME!");
       
    }
    
}
