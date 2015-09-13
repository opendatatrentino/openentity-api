package eu.trentorise.opendata.semantics.services.mock;

import eu.trentorise.opendata.semantics.model.entity.EntityType;
import eu.trentorise.opendata.semantics.model.entity.IAttribute;
import eu.trentorise.opendata.semantics.model.entity.IAttributeDef;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.entity.IStructure;
import eu.trentorise.opendata.semantics.services.IEkb;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David Leoni
 */
public class MockStructure implements IStructure {

    protected String URL;

    protected Map<String, IAttribute> structureAttributes;   

    protected IEntityType etype;

    /**
     * Makes a shallow copy of struct
     *
     * @param struct
     */
    public MockStructure(IStructure struct, IEntityType etype) {
        this();
        this.URL = struct.getUrl();
        this.etype = etype;
        for (IAttribute attr : struct.getAttributes()) {
            structureAttributes.put(attr.getAttrDefUrl(), attr);
        }
    }

    public MockStructure(String URL, IEntityType etype) {
        this();
        this.URL = URL;        
        this.etype = etype;
    }


    private MockStructure() {
        this.URL = "";
        this.etype = new EntityType();
        structureAttributes = new HashMap();
    }



    @Nullable
    @Override
    public IAttribute getAttribute(String attrDefURL) {
        return structureAttributes.get(attrDefURL);
    }

    @Override
    public List<IAttribute> getAttributes() {
        return Collections.unmodifiableList(new ArrayList(structureAttributes.values()));
    }


    @Override
    public String getUrl() {
        return URL;
    }



    /**
     * adds attribute with provided url and one value as obj
     */
    public final void setAttrAndValue(IAttributeDef attrDef, Object obj) {

        // odr d 0.3i a MockAttributeDefService would make more sense
        this.structureAttributes.put(attrDef.getURL(), new MockAttribute(attrDef.getURL(), obj));
    }

    /**
     * adds attribute with provided url and one value as obj
     */
    public final void setAttrAndValues(IAttributeDef attrDef, List<Object> objs) {
        // odr todo 0.3i a MockAttributeDefService would make more sense
        this.structureAttributes.put(attrDef.getURL(), new MockAttribute(objs, attrDef.getURL()));
    }

    @Override
    public String getEtypeURL() {
        return etype.getURL();
    }

}
