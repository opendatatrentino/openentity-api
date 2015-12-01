package eu.trentorise.opendata.semantics.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.commons.TodConfig;
import eu.trentorise.opendata.semantics.exceptions.OpenEntityNotFoundException;
import eu.trentorise.opendata.semantics.model.entity.AttrDef;
import eu.trentorise.opendata.semantics.model.entity.Entity;
import eu.trentorise.opendata.semantics.model.entity.Etype;
import eu.trentorise.opendata.semantics.services.IEkb;
import eu.trentorise.opendata.semantics.services.IEtypeService;
import eu.trentorise.opendata.semantics.services.mock.MockEkb;

public class EntityTest {

    @BeforeClass
    public static void setUpClass() {
        TodConfig.init(EntityTest.class);
    }
    
    @Test
    public void testBuilder() {

        Entity.Builder enb = Entity.builder();

        IEkb ekb = new MockEkb();
        IEtypeService ets = ekb.getEtypeService();

        Etype rootEtype = ets.readRootEtype();

        enb.setNameAttr(Dict.of("a"), rootEtype, null);
        enb.setDescrAttr(Dict.of("b"), rootEtype);

        Entity en = enb.build();
        assertEquals(en.getEtypeId(), rootEtype.getId());
        assertEquals(en.getName(), Dict.of("a"));
        assertEquals(en.getDescription(), Dict.of("b"));

        // todo test name structure
    }
    
    @Test    
    public void testAttr(){

        MockEkb ekb = new MockEkb();
        
        AttrDef myattr = AttrDef.builder()
                .setId("myattr")
                .setName(Dict.of("My Attr"))
                .setConceptId("myconceptid")
                .build();        
                
        Etype myetype = Etype.builder()
                .setId("myetype")
                .putAttrDef(ekb.getEtypeService().readAttrDef(MockEkb.TEST_NAME_ATTR_ID))                
                .putAttrDef(ekb.getEtypeService().readAttrDef(MockEkb.TEST_DESCR_ATTR_ID))                
                .putAttrDef(myattr)                
                .build();
        
        assertEquals(myattr, myetype.attrDefByIdOrName(myattr.getId()));        
        assertEquals(myattr, myetype.attrDefByIdOrName("My Attr")); 
        assertEquals(myattr, myetype.attrDefByIdOrName("my attr"));
        try {
            myetype.attrDefByIdOrName("666");
            Assert.fail("Shouldn't arrive here");
        } catch (OpenEntityNotFoundException ex){
            
        }
        assertEquals(myattr, myetype.attrDefById(myattr.getId()));
        try {
            myetype.attrDefById("666");
            Assert.fail("Shouldn't arrive here");
        } catch (OpenEntityNotFoundException ex){
            
        }
        
        assertEquals(myattr, myetype.attrDefByConceptId(myattr.getConceptId()));
        
        try {
            myetype.attrDefByConceptId("666");
            Assert.fail("Shouldn't arrive here");
        } catch (OpenEntityNotFoundException ex){
            
        }
        
    }    
}
