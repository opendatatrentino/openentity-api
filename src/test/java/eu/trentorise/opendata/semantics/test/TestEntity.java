package eu.trentorise.opendata.semantics.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.commons.TodConfig;
import eu.trentorise.opendata.semantics.model.entity.AEntity;
import eu.trentorise.opendata.semantics.model.entity.AEntity.Builder;
import eu.trentorise.opendata.semantics.model.entity.Entity;
import eu.trentorise.opendata.semantics.model.entity.Etype;
import eu.trentorise.opendata.semantics.services.IEkb;
import eu.trentorise.opendata.semantics.services.IEtypeService;
import eu.trentorise.opendata.semantics.services.mock.MockEkb;

import static org.junit.Assert.*;

public class TestEntity {

    @BeforeClass
    public static void setUpClass() {
        TodConfig.init(TestEntity.class);
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
}
