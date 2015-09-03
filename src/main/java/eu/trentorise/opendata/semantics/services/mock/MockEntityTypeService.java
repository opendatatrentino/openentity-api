package eu.trentorise.opendata.semantics.services.mock;


import eu.trentorise.opendata.semantics.DataTypes;
import static eu.trentorise.opendata.semantics.DataTypes.AT_CONCEPT;
import static eu.trentorise.opendata.semantics.DataTypes.AT_FLOAT;
import static eu.trentorise.opendata.semantics.DataTypes.AT_STRING;
import eu.trentorise.opendata.semantics.model.entity.AttrType;
import eu.trentorise.opendata.semantics.model.entity.EntityType;
import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.services.IEntityTypeService;
import eu.trentorise.opendata.semantics.services.SearchResult;
import static eu.trentorise.opendata.semantics.services.mock.MockEkb.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

/**
 *
 * @author David Leoni
 */
public class MockEntityTypeService implements IEntityTypeService {

    private final Map<String, IEntityType> registeredETypes;
    private final Map<String, IAttributeDef> registeredAttrDefs;    

    public final void storeNameDescr(String nameURL, String descrURL, String parentEtypeURL){
        storeAttrDef(nameURL, "Name", "Nome", AttrType.of(DataTypes.STRING, true), parentEtypeURL);
        storeAttrDef(descrURL, "Description", "Descrizione", AttrType.of(DataTypes.SEMANTIC_TEXT,true), parentEtypeURL);        
    }
    
    public MockEntityTypeService() {
        this.registeredETypes = new HashMap();
        this.registeredAttrDefs = new HashMap();
                
        storeStructureType(TEST_ROAT_STRUCTURE, "test root structure", "struttura radice di test");
        
        storeNameDescr(ROAT_NAME_ATTR, ROAT_DESCR_ATTR, ROAT_ETYPE);
        storeEType(ROAT_ETYPE, "test root etype", "etype radice di test", ROAT_CONCEPT,
                   ROAT_NAME_ATTR,
                   ROAT_DESCR_ATTR);

        
        storeAttrDef(TEST_ATTR_DEF_1, TEST_ATTR_DEF_1_EN_NAME, TEST_ATTR_DEF_1_IT_NAME, AT_STRING, TEST_ENTITY_TYPE);
        storeAttrDef(TEST_ATTR_DEF_2, TEST_ATTR_DEF_2_EN_NAME, TEST_ATTR_DEF_2_IT_NAME, AT_STRING, TEST_ENTITY_TYPE);
        storeAttrDef(TEST_ATTR_DEF_3, TEST_ATTR_DEF_3_EN_NAME, TEST_ATTR_DEF_3_IT_NAME, AT_STRING, TEST_ENTITY_TYPE);
        
        storeNameDescr(TEST_NAME_ATTR, TEST_DESCR_ATTR, TEST_ENTITY_TYPE);
        storeEType(TEST_ENTITY_TYPE, "test etype", "etype di test", ROAT_CONCEPT,
                   TEST_NAME_ATTR,
                   TEST_DESCR_ATTR,
                   TEST_ATTR_DEF_1, 
                   TEST_ATTR_DEF_2,
                   TEST_ATTR_DEF_3);
        
        // recursive stuff begin
        storeAttrDef(TEST_REC_ATTR_DEF, "test rec attr 1 in recursive structure", "Attributo di test in struttura ricorsiva", AttrType.of(DataTypes.STRUCTURE, false, TEST_REC_STRUCTURE), TEST_REC_STRUCTURE);
        storeStructureType(TEST_REC_STRUCTURE, "test rec structure", "struttura ricorsiva di test", TEST_REC_ATTR_DEF);
        
        
        storeAttrDef(TEST_REC_OUTER_ATTR_DEF, "test rec struct attr 1", "Attribute di test per struttura ricorsiva", AttrType.of(DataTypes.STRUCTURE, false, TEST_REC_STRUCTURE), TEST_REC_ENTITY_TYPE);
        
        storeNameDescr(TEST_REC_NAME_ATTR, TEST_REC_DESCR_ATTR, TEST_REC_ENTITY_TYPE);
        storeEType(TEST_REC_ENTITY_TYPE, "test recursive etype", "etype ricorsivo di test", ROAT_CONCEPT,
                   TEST_REC_NAME_ATTR,
                   TEST_REC_DESCR_ATTR,
                   TEST_REC_OUTER_ATTR_DEF);
        // recursive stuff end
        

        storeAttrDef(ADDRESS_MUNICIPALITY_ATTR, "Municipality", "Comune", AttrType.of(DataTypes.ENTITY, false, LOCATION), ADDRESS);        

        storeStructureType(ADDRESS, "Address", "Indirizzo", 
                           ADDRESS_MUNICIPALITY_ATTR);                
        
        storeNameDescr(LOCATION_NAME_ATTR, LOCATION_DESCR_ATTR, LOCATION);
        storeEType(LOCATION, "Location", "Località", ROAT_CONCEPT, LOCATION_NAME_ATTR, LOCATION_DESCR_ATTR);
        
        storeNameDescr(FARMFED_PROD_NAME_ATTR, FARMFED_PROD_DESCR_ATTR, FARMFED_PRODUCER);
        storeEType(FARMFED_PRODUCER, "Producer", "Produttore di alimentari", ROAT_CONCEPT, FARMFED_PROD_NAME_ATTR, FARMFED_PROD_DESCR_ATTR);               
        
        storeAttrDef(CERTIFIED_PRODUCT_CERTIFICATE_ATTR, "certificate", "certificazione", AT_STRING, CERTIFIED_PRODUCT);        
        storeAttrDef(CERTIFIED_PRODUCT_REGULATIONS_ATTR, "regulations", "norme", AT_STRING, CERTIFIED_PRODUCT);
        storeAttrDef(CERTIFIED_PRODUCT_PRODUCTION_SITE_ATTR, "production site", "luogo di produzione", AttrType.of(DataTypes.ENTITY, true, LOCATION), CERTIFIED_PRODUCT);
        storeAttrDef(CERTIFIED_PRODUCT_PRODUCER_ATTR, "producer", "produttore", AttrType.of(DataTypes.ENTITY, false, FARMFED_PRODUCER), CERTIFIED_PRODUCT);
        storeAttrDef(CERTIFIED_PRODUCT_WEBSITE_ATTR, "website", "sito web", AT_STRING, CERTIFIED_PRODUCT);
        
        storeNameDescr(CERTIFIED_PRODUCT_NAME_ATTR, CERTIFIED_PRODUCT_DESCR_ATTR, CERTIFIED_PRODUCT);
        storeEType(CERTIFIED_PRODUCT, "Certified product", "Prodotto certificato", ROAT_CONCEPT,
                CERTIFIED_PRODUCT_NAME_ATTR,
                CERTIFIED_PRODUCT_DESCR_ATTR,
                CERTIFIED_PRODUCT_CERTIFICATE_ATTR,                
                CERTIFIED_PRODUCT_REGULATIONS_ATTR,
                CERTIFIED_PRODUCT_PRODUCTION_SITE_ATTR,
                CERTIFIED_PRODUCT_PRODUCER_ATTR,
                CERTIFIED_PRODUCT_WEBSITE_ATTR);
                     
        storeAttrDef(OPENING_TIME_ATTR, "Opening time", "Orario di apertura", AT_STRING, OPENING_HOURS);
        storeAttrDef(CLOSING_TIME_ATTR, "Closing time", "Orario di chiusura", AT_STRING, OPENING_HOURS);

        storeStructureType(OPENING_HOURS, "Opening Hours", "Orario di apertura",
                OPENING_TIME_ATTR,
                CLOSING_TIME_ATTR);

        storeAttrDef(FACILITY_LATITUDE_ATTR, "Latitude", "Latitudine", AT_FLOAT, FACILITY);
        storeAttrDef(FACILITY_LONGITUDE_ATTR, "Longitude", "Longitudine", AT_FLOAT, FACILITY);
        storeAttrDef(FACILITY_OPENING_HOURS_ATTR, "Opening hours", "Orari", AttrType.of(DataTypes.STRUCTURE, false, OPENING_HOURS), FACILITY);
        storeAttrDef(FACILITY_CLASS_ATTR, "Class", "Classe", AT_CONCEPT, FACILITY);

        
        storeAttrDef(COMPLEX_NAME_NAME_ATTR, "Name", "Nome", AttrType.of(DataTypes.STRING,true), COMPLEX_NAME);
        storeStructureType(COMPLEX_NAME, "NameType", "Tipo nome", COMPLEX_NAME_NAME_ATTR);        
        
        storeAttrDef(FACILITY_NAME_ATTR, "Name", "Nome", AttrType.of(DataTypes.STRUCTURE, true, COMPLEX_NAME), FACILITY);
        storeAttrDef(FACILITY_DESCR_ATTR, "Description", "Descrizione", AttrType.of(DataTypes.SEMANTIC_TEXT,true), FACILITY);         
        storeAttrDef(FACILITY_ADDRESS_ATTR, "Address", "Indirizzo", AttrType.of(DataTypes.STRUCTURE, false, ADDRESS), FACILITY);
        
        storeEType(FACILITY, "Facility", "Infrastruttura", FACILITY_CONCEPT,
                FACILITY_NAME_ATTR,
                FACILITY_DESCR_ATTR,
                FACILITY_LATITUDE_ATTR,
                FACILITY_LONGITUDE_ATTR,
                FACILITY_OPENING_HOURS_ATTR,
                FACILITY_CLASS_ATTR, 
                FACILITY_ADDRESS_ATTR);

    }

    private void storeStructureType(String URL, String engName, String itName, String... attrsURLs) {
        List<IAttributeDef> attrs = new ArrayList();
        for (String au : attrsURLs) {
            attrs.add(registeredAttrDefs.get(au));
        }
        
        registeredETypes.put(URL, new EntityType(URL, engName, itName, attrs, ROAT_CONCEPT));
    }    
    
    private void storeEType(String URL, String engName, String itName, @Nullable String conceptURL,  String nameAttrDefURL, String descrAttrDefURL, String... attrsURLs) {
        List<IAttributeDef> attrs = new ArrayList();
        for (String au : attrsURLs) {
            attrs.add(registeredAttrDefs.get(au));
        }
        attrs.add(registeredAttrDefs.get(nameAttrDefURL));
        attrs.add(registeredAttrDefs.get(descrAttrDefURL));
        
        registeredETypes.put(URL, new EntityType(URL, engName, itName,conceptURL, nameAttrDefURL, descrAttrDefURL, attrs));
    }

    private void storeAttrDef(String URL, String engName, String itName, AttrType attrType, String etypeURL) {
        registeredAttrDefs.put(URL, new MockAttributeDef(URL, engName, itName, attrType, etypeURL));
    }
 
    /**
     * @return unmodifiable list
     */
    public Map<String, IAttributeDef> getRegisteredAttrDefs() {
        return Collections.unmodifiableMap(registeredAttrDefs);
    }

    /**
     * @return unmodifiable list
     */
    public Map<String, IEntityType> getRegisteredETypes() {
        return Collections.unmodifiableMap(registeredETypes);
    }

    @Override
    public IEntityType readEntityType(String URL) {
        for (IEntityType et : this.registeredETypes.values()) {
            if (et.getURL().equals(URL)) {
                return et;
            }
        }
        return null;
    }

    @Override
    public List<IEntityType> readEntityTypes(Iterable<String> URLs) {
        List<IEntityType> ret = new ArrayList();
        
        for (String URL : URLs){
            ret.add(readEntityType(URL));
        }
        return ret;
    }


    /**
     * Returns all the mock entity types
     */
    @Override
    public List<SearchResult> searchEntityTypes(String partialName, Locale locale) {
        List<SearchResult> ret = new ArrayList();


        for (IEntityType et : readAllEntityTypes()) {
            if (et.getName().contains(partialName)) {
                SearchResult es = SearchResult.of(et.getURL(), et.getName());
                ret.add(es);
            }
        }
        
        return ret;
    }

    @Override
    public IEntityType readRootEtype() {
        return registeredETypes.get(MockEkb.ROAT_ETYPE);
    }

    @Override
    public IEntityType readRootStructure() {
        return registeredETypes.get(MockEkb.TEST_ROAT_STRUCTURE);    }

    @Override
    public List<IEntityType> readAllEntityTypes() {
        return new ArrayList(registeredETypes.values());
    }

    @Override
    public IAttributeDef readAttrDef(String url) {
        return registeredAttrDefs.get(url);
    }

}
