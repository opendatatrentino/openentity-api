package eu.trentorise.opendata.semantics.services.mock;

import eu.trentorise.opendata.semantics.services.ISchemaMatchingService;
import eu.trentorise.opendata.traceprov.data.DcatMetadata;
import eu.trentorise.opendata.traceprov.types.Type;
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
    public List<SchemaMapping> matchSchemas(DcatMetadata dcatMetadata, Type schema, Object data) {
        throw new UnsupportedOperationException("TODO IMPLEMENT ME!");
        /*
        Ekb ekb = OdrPlugin.getEkb();
        EntityTypeServiceController etsc = ekb.getEntityTypeService();


        List<ISchemaCorrespondence> ret = new ArrayList<ISchemaCorrespondence>();

        {

            EntityTypeController facilityEtype = etsc.readEntityType(MockEkb.FACILITY);
            
            List<AttributeCorrespondence> acl = new ArrayList<AttributeCorrespondence>();

            acl.add(new MockAttributeCorrespondence(0, etsc.getAttributeDef(MockEkb.FACILITY_NAME_ATTR).getOriginal()));
            acl.add(new MockAttributeCorrespondence(1, etsc.getAttributeDef(MockEkb.FACILITY_OPENING_HOURS_ATTR).getOriginal()));
            
            acl.add(new MockAttributeCorrespondence(3, etsc.getAttributeDef(MockEkb.FACILITY_CLASS_ATTR).getOriginal()));
            // inverting order so Italian gets preserved when simplifier cuts the needed merge
            acl.add(new MockAttributeCorrespondence(2, etsc.getAttributeDef(MockEkb.FACILITY_CLASS_ATTR).getOriginal()));
            acl.add(new MockAttributeCorrespondence(4, etsc.getAttributeDef(MockEkb.FACILITY_LATITUDE_ATTR).getOriginal()));
            acl.add(new MockAttributeCorrespondence(5, etsc.getAttributeDef(MockEkb.FACILITY_LONGITUDE_ATTR).getOriginal()));

            MockSchemaCorrespondence sc_1 = new MockSchemaCorrespondence(facilityEtype.getOriginal(), 0.3f, acl);

            ret.add(sc_1);
        }
        {

            EntityTypeController certifiedProductEtype = etsc.readEntityType(MockEkb.CERTIFIED_PRODUCT);
            
            List<AttributeCorrespondence> acl = new ArrayList<AttributeCorrespondence>();

            acl.add(new MockAttributeCorrespondence(0, etsc.getAttributeDef(MockEkb.CERTIFIED_PRODUCT_NAME_ATTR).getOriginal()));

            MockSchemaCorrespondence sc_2 = new MockSchemaCorrespondence(certifiedProductEtype.getOriginal(), 0.2f, acl);

            ret.add(sc_2);
        }

        return ret;
        */

    }

}
