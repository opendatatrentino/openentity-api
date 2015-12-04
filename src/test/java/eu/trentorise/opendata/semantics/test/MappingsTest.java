package eu.trentorise.opendata.semantics.test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

import eu.trentorise.opendata.commons.TodConfig;
import eu.trentorise.opendata.semantics.DataTypes;
import eu.trentorise.opendata.semantics.model.entity.AttrDef;
import eu.trentorise.opendata.semantics.model.entity.AttrType;
import eu.trentorise.opendata.semantics.model.entity.Etype;
import eu.trentorise.opendata.semantics.services.Schemas;
import eu.trentorise.opendata.semantics.services.mock.MockEkb;
import eu.trentorise.opendata.semantics.services.mock.MockEntityService;
import eu.trentorise.opendata.semantics.services.mock.MockEtypeService;

public class MappingsTest {

    @BeforeClass
    public static void setUpClass() {
        TodConfig.init(MappingsTest.class);
    }
    
    /**
     * <pre>
     *  etypeA
     *      |- attrA : StructB
     *              |- attrB : String
     *       
     * </pre>
     */
    @Test    
    public void testResolveAttr(){
        
        MockEtypeService ets = new MockEtypeService();
                
        AttrDef attrB = ets.registerAttrDef("attrDefB", "Attribute B", "attributo B",DataTypes.AT_STRING);
        Etype structB = ets.registerStructType("etypeB","etype b", "tipo entità b", attrB.getId());
                
        AttrDef attrA = ets.registerAttrDef("attrDefA", "Attribute A", "Attributo A",
                AttrType.of(DataTypes.STRUCTURE,
                        false, structB.getId()));
        
        Etype etypeA = ets.registerEtype("etypeA","Etype A", 
                "Tipo di entità A",
                "conceptA",
                MockEtypeService.TEST_NAME_ATTR_ID, 
                MockEtypeService.TEST_DESCR_ATTR_ID,
                attrA.getId());
        
        try {
            Schemas.resolveAttrPath(ImmutableList.<String>of(), "a", ets);
            Assert.fail("Shouldn't arrive here!");
        } catch (Exception ex){ // todo define better exception
            
        }
        
        
        assertEquals(attrA,   
                     Schemas.resolveAttrPath(ImmutableList.of(attrA.getId()), "etypeA", ets));
        
        assertEquals(attrA,   
                Schemas.resolveAttrPath(ImmutableList.of("Attribute A"), "etypeA", ets));

        
        assertEquals(attrB,   
                Schemas.resolveAttrPath(ImmutableList.of(attrA.getId(), attrB.getId()), etypeA.getId(), ets));
       
        try {            
            Schemas.resolveAttrPath(ImmutableList.of("attrA", "attr666"), "etypeA", ets);
            Assert.fail("Shouldn't arrive here!");
        } catch (Exception ex){ // todo define better exception
            
        }
        
    }
    
}
