package eu.trentorise.opendata.semantics.services.mock;

import eu.trentorise.opendata.semantics.services.ISchemaMatchingService;
import eu.trentorise.opendata.traceprov.data.DcatMetadata;
import eu.trentorise.opendata.traceprov.types.TraceType;
import eu.trentorise.opendata.semantics.services.SchemaMapping;
import java.util.List;

/**
 *
 * @author David Leoni
 */
public class MockSchemaMatchingService implements ISchemaMatchingService {

    public MockSchemaMatchingService() {
    }

    @Override
    public List<SchemaMapping> matchSchemas(DcatMetadata dcatMetadata, TraceType schema, Object data) {
        throw new UnsupportedOperationException("TODO IMPLEMENT ME!");
        /*
        Ekb ekb = OdrPlugin.getEkb();
        EtypeServiceController etsc = ekb.getEtypeService();


        List<ISchemaCorrespondence> ret = new ArrayList<ISchemaCorrespondence>();

        {

            EtypeController facilityEtype = etsc.readEtype(MockEkb.FACILITY);
            
            List<AttrCorrespondence> acl = new ArrayList<AttrCorrespondence>();

            acl.add(new MockAttrCorrespondence(0, etsc.getAttrDef(MockEkb.FACILITY_NAME_ATTR).getOriginal()));
            acl.add(new MockAttrCorrespondence(1, etsc.getAttrDef(MockEkb.FACILITY_OPENING_HOURS_ATTR).getOriginal()));
            
            acl.add(new MockAttrCorrespondence(3, etsc.getAttrDef(MockEkb.FACILITY_CLASS_ATTR).getOriginal()));
            // inverting order so Italian gets preserved when simplifier cuts the needed merge
            acl.add(new MockAttrCorrespondence(2, etsc.getAttrDef(MockEkb.FACILITY_CLASS_ATTR).getOriginal()));
            acl.add(new MockAttrCorrespondence(4, etsc.getAttrDef(MockEkb.FACILITY_LATITUDE_ATTR).getOriginal()));
            acl.add(new MockAttrCorrespondence(5, etsc.getAttrDef(MockEkb.FACILITY_LONGITUDE_ATTR).getOriginal()));

            MockSchemaCorrespondence sc_1 = new MockSchemaCorrespondence(facilityEtype.getOriginal(), 0.3f, acl);

            ret.add(sc_1);
        }
        {

            EtypeController certifiedProductEtype = etsc.readEtype(MockEkb.CERTIFIED_PRODUCT);
            
            List<AttrCorrespondence> acl = new ArrayList<AttrCorrespondence>();

            acl.add(new MockAttrCorrespondence(0, etsc.getAttrDef(MockEkb.CERTIFIED_PRODUCT_NAME_ATTR).getOriginal()));

            MockSchemaCorrespondence sc_2 = new MockSchemaCorrespondence(certifiedProductEtype.getOriginal(), 0.2f, acl);

            ret.add(sc_2);
        }

        return ret;
        */

    }

}
