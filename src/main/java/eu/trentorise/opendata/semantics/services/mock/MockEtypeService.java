package eu.trentorise.opendata.semantics.services.mock;


import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.DataTypes;
import static eu.trentorise.opendata.semantics.DataTypes.AT_CONCEPT;
import static eu.trentorise.opendata.semantics.DataTypes.AT_FLOAT;
import static eu.trentorise.opendata.semantics.DataTypes.AT_STRING;
import eu.trentorise.opendata.semantics.model.entity.AttrType;
import eu.trentorise.opendata.semantics.model.entity.AttrDef;
import eu.trentorise.opendata.semantics.model.entity.Etype;
import eu.trentorise.opendata.semantics.services.IEtypeService;
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
public class MockEtypeService implements IEtypeService {

    private final Map<String, Etype> registeredETypes;
    private final Map<String, AttrDef> registeredAttrDefs;    

    public final void storeNameDescr(String nameURL, String descrURL, String parentEtypeURL){
        storeAttrDef(nameURL, "Name", "Nome", AttrType.of(DataTypes.STRING, true));
        storeAttrDef(descrURL, "Description", "Descrizione", AttrType.of(DataTypes.SEMANTIC_TEXT,true));        
    }
    
    public MockEtypeService() {
        this.registeredETypes = new HashMap();
        this.registeredAttrDefs = new HashMap();
                
        storeStructType(TEST_ROOT_STRUCTURE, "test root struct", "struttura radice di test");
        
        storeNameDescr(ROOT_NAME_ATTR, ROOT_DESCR_ATTR, ROOT_ETYPE);
        storeEType(ROOT_ETYPE, "test root etype", "etype radice di test", ROOT_CONCEPT,
                   ROOT_NAME_ATTR,
                   ROOT_DESCR_ATTR);

        
        storeAttrDef(TEST_ATTR_DEF_1, TEST_ATTR_DEF_1_EN_NAME, TEST_ATTR_DEF_1_IT_NAME, AT_STRING);
        storeAttrDef(TEST_ATTR_DEF_2, TEST_ATTR_DEF_2_EN_NAME, TEST_ATTR_DEF_2_IT_NAME, AT_STRING);
        storeAttrDef(TEST_ATTR_DEF_3, TEST_ATTR_DEF_3_EN_NAME, TEST_ATTR_DEF_3_IT_NAME, AT_STRING);
        
        storeNameDescr(TEST_NAME_ATTR, TEST_DESCR_ATTR, TEST_ENTITY_TYPE);
        storeEType(TEST_ENTITY_TYPE, "test etype", "etype di test", ROOT_CONCEPT,
                   TEST_NAME_ATTR,
                   TEST_DESCR_ATTR,
                   TEST_ATTR_DEF_1, 
                   TEST_ATTR_DEF_2,
                   TEST_ATTR_DEF_3);
        
        // recursive stuff begin
        storeAttrDef(TEST_REC_ATTR_DEF, "test rec attr 1 in recursive struct", "Attributo di test in struttura ricorsiva", AttrType.of(DataTypes.STRUCTURE, false, TEST_REC_STRUCTURE));
        storeStructType(TEST_REC_STRUCTURE, "test rec struct", "struttura ricorsiva di test", TEST_REC_ATTR_DEF);
        
        
        storeAttrDef(TEST_REC_OUTER_ATTR_DEF, "test rec struct attr 1", "Attr di test per struttura ricorsiva", AttrType.of(DataTypes.STRUCTURE, false, TEST_REC_STRUCTURE));
        
        storeNameDescr(TEST_REC_NAME_ATTR, TEST_REC_DESCR_ATTR, TEST_REC_ENTITY_TYPE);
        storeEType(TEST_REC_ENTITY_TYPE, "test recursive etype", "etype ricorsivo di test", ROOT_CONCEPT,
                   TEST_REC_NAME_ATTR,
                   TEST_REC_DESCR_ATTR,
                   TEST_REC_OUTER_ATTR_DEF);
        // recursive stuff end
        

        storeAttrDef(ADDRESS_MUNICIPALITY_ATTR, "Municipality", "Comune", AttrType.of(DataTypes.ENTITY, false, LOCATION));        

        storeStructType(ADDRESS, "Address", "Indirizzo", 
                           ADDRESS_MUNICIPALITY_ATTR);                
        
        storeNameDescr(LOCATION_NAME_ATTR, LOCATION_DESCR_ATTR, LOCATION);
        storeEType(LOCATION, "Location", "Località", ROOT_CONCEPT, LOCATION_NAME_ATTR, LOCATION_DESCR_ATTR);
        
        storeNameDescr(FARMFED_PROD_NAME_ATTR, FARMFED_PROD_DESCR_ATTR, FARMFED_PRODUCER);
        storeEType(FARMFED_PRODUCER, "Producer", "Produttore di alimentari", ROOT_CONCEPT, FARMFED_PROD_NAME_ATTR, FARMFED_PROD_DESCR_ATTR);               
        
        storeAttrDef(CERTIFIED_PRODUCT_CERTIFICATE_ATTR, "certificate", "certificazione", AT_STRING);        
        storeAttrDef(CERTIFIED_PRODUCT_REGULATIONS_ATTR, "regulations", "norme", AT_STRING);
        storeAttrDef(CERTIFIED_PRODUCT_PRODUCTION_SITE_ATTR, "production site", "luogo di produzione", AttrType.of(DataTypes.ENTITY, true, LOCATION));
        storeAttrDef(CERTIFIED_PRODUCT_PRODUCER_ATTR, "producer", "produttore", AttrType.of(DataTypes.ENTITY, false, FARMFED_PRODUCER));
        storeAttrDef(CERTIFIED_PRODUCT_WEBSITE_ATTR, "website", "sito web", AT_STRING);
        
        storeNameDescr(CERTIFIED_PRODUCT_NAME_ATTR, CERTIFIED_PRODUCT_DESCR_ATTR, CERTIFIED_PRODUCT);
        storeEType(CERTIFIED_PRODUCT, "Certified product", "Prodotto certificato", ROOT_CONCEPT,
                CERTIFIED_PRODUCT_NAME_ATTR,
                CERTIFIED_PRODUCT_DESCR_ATTR,
                CERTIFIED_PRODUCT_CERTIFICATE_ATTR,                
                CERTIFIED_PRODUCT_REGULATIONS_ATTR,
                CERTIFIED_PRODUCT_PRODUCTION_SITE_ATTR,
                CERTIFIED_PRODUCT_PRODUCER_ATTR,
                CERTIFIED_PRODUCT_WEBSITE_ATTR);
                     
        storeAttrDef(OPENING_TIME_ATTR, "Opening time", "Orario di apertura", AT_STRING);
        storeAttrDef(CLOSING_TIME_ATTR, "Closing time", "Orario di chiusura", AT_STRING);

        storeStructType(OPENING_HOURS, "Opening Hours", "Orario di apertura",
                OPENING_TIME_ATTR,
                CLOSING_TIME_ATTR);

        storeAttrDef(FACILITY_LATITUDE_ATTR, "Latitude", "Latitudine", AT_FLOAT);
        storeAttrDef(FACILITY_LONGITUDE_ATTR, "Longitude", "Longitudine", AT_FLOAT);
        storeAttrDef(FACILITY_OPENING_HOURS_ATTR, "Opening hours", "Orari", AttrType.of(DataTypes.STRUCTURE, false, OPENING_HOURS));
        storeAttrDef(FACILITY_CLASS_ATTR, "Class", "Classe", AT_CONCEPT);   
        storeAttrDef(COMPLEX_NAME_NAME_ATTR, "Name", "Nome", AttrType.of(DataTypes.STRING,true));
        storeStructType(COMPLEX_NAME, "NameType", "Tipo nome", COMPLEX_NAME_NAME_ATTR);        
        
        storeAttrDef(FACILITY_NAME_ATTR, "Name", "Nome", AttrType.of(DataTypes.STRUCTURE, true, COMPLEX_NAME));
        storeAttrDef(FACILITY_DESCR_ATTR, "Description", "Descrizione", AttrType.of(DataTypes.SEMANTIC_TEXT,true));         
        storeAttrDef(FACILITY_ADDRESS_ATTR, "Address", "Indirizzo", AttrType.of(DataTypes.STRUCTURE, false, ADDRESS));
        
        storeEType(FACILITY, "Facility", "Infrastruttura", FACILITY_CONCEPT,
                FACILITY_NAME_ATTR,
                FACILITY_DESCR_ATTR,
                FACILITY_LATITUDE_ATTR,
                FACILITY_LONGITUDE_ATTR,
                FACILITY_OPENING_HOURS_ATTR,
                FACILITY_CLASS_ATTR, 
                FACILITY_ADDRESS_ATTR);

    }

    private void storeStructType(String URL, String engName, String itName, String... attrsURLs) {
        List<AttrDef> attrs = new ArrayList();
        for (String au : attrsURLs) {
            attrs.add(registeredAttrDefs.get(au));
        }
        
        registeredETypes.put(URL, newEtype(URL, engName, itName, attrs, ROOT_CONCEPT));
    }    
    
    private Etype newEtype(String URL, String engName, String itName, List<AttrDef> attrs, String conceptUrl){
        Etype.Builder b = Etype.builder();
        b.setId(URL);
        b.setName(Dict.builder().put(Locale.ENGLISH, engName).put(Locale.ITALIAN, itName).build());
        
        for (AttrDef attrDef : attrs){
            b.putAttrDefs(attrDef.getId(), attrDef);
        }
        b.setConceptId(conceptUrl);
        return b.build();                
    }
    
    private void storeEType(String URL, String engName, String itName, @Nullable String conceptURL,  String nameAttrDefURL, String descrAttrDefURL, String... attrsURLs) {
        List<AttrDef> attrs = new ArrayList();
        for (String au : attrsURLs) {
            attrs.add(registeredAttrDefs.get(au));
        }
        attrs.add(registeredAttrDefs.get(nameAttrDefURL));
        attrs.add(registeredAttrDefs.get(descrAttrDefURL));
        
        registeredETypes.put(URL, Etype.of(URL, engName, itName,conceptURL, nameAttrDefURL, descrAttrDefURL, attrs));
    }

    private void storeAttrDef(String URL, String engName, String itName, AttrType attrType) {
        registeredAttrDefs.put(URL, newAttrDef(URL, engName, itName, attrType));
    }
    
    static AttrDef newAttrDef(String URL, String engName, String itName, AttrType attrType){
        return AttrDef.builder()
                .setId(URL)
                .setName(Dict.builder().put(Locale.ENGLISH, engName).put(Locale.ITALIAN, itName).build())
                .setType(attrType)
                .build();
    }
 
    /**
     * @return unmodifiable list
     */
    public Map<String, AttrDef> getRegisteredAttrDefs() {
        return Collections.unmodifiableMap(registeredAttrDefs);
    }

    /**
     * @return unmodifiable list
     */
    public Map<String, Etype> getRegisteredETypes() {
        return Collections.unmodifiableMap(registeredETypes);
    }

    @Override
    public Etype readEtype(String URL) {
        for (Etype et : this.registeredETypes.values()) {
            if (et.getId().equals(URL)) {
                return et;
            }
        }
        return null;
    }

    @Override
    public List<Etype> readEtypes(Iterable<String> URLs) {
        List<Etype> ret = new ArrayList();
        
        for (String URL : URLs){
            ret.add(readEtype(URL));
        }
        return ret;
    }


    /**
     * Returns all the mock entity types
     */
    @Override
    public List<SearchResult> searchEtypes(String partialName, Locale locale) {
        List<SearchResult> ret = new ArrayList();


        for (Etype et : readAllEtypes()) {
            if (et.getName().contains(partialName)) {
                SearchResult es = SearchResult.of(et.getId(), et.getName());
                ret.add(es);
            }
        }
        
        return ret;
    }

    @Override
    public Etype readRootEtype() {
        return registeredETypes.get(MockEkb.ROOT_ETYPE);
    }

    @Override
    public Etype readRootStruct() {
        return registeredETypes.get(MockEkb.TEST_ROOT_STRUCTURE);    }

    @Override
    public List<Etype> readAllEtypes() {
        return new ArrayList(registeredETypes.values());
    }

    @Override
    public AttrDef readAttrDef(String url) {
        return registeredAttrDefs.get(url);
    }

}
