package eu.trentorise.opendata.semantics.impl.model.entity;

/**
 *
 * @author David Leoni
 */
import eu.trentorise.opendata.semantics.model.entity.IAttribute;
import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.impl.model.knowledge.Dict;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/**
 *
 * Experimental entity, it's likely to be removed once we have a proper entity
 * implementation in the OpenEntity API. MinimalEntity can have all parameters
 * missing, and might have some additional info, like name and description, but
 * they can still be empty (but never null). Entity URL and etypeURL can be empty, but not null. Trying to set
 * any structureAttribute will throw an exception.
 *
 * @author David Leoni
 */
public class MinimalEntity implements IEntity {

    private String URL;

    private Dict name;

    private Dict description;
    
    private String etypeURL;

    /**
     *
     * @param URL if URL is not available put an empty string, otherwise an exception will be thrown
     * @param name if not available put an empty dict.
     * @param description if not available put an empty dict.
     * @param etypeURL if not available put an empty string
     */
    public MinimalEntity(String URL,
            IDict name,
            IDict description,
            String etypeURL) {

        if (URL == null){
            throw new IllegalArgumentException("null URL is not allowed! Use an empty string if needed!");
        }
        
        

        if (name == null){
            throw new IllegalArgumentException("null name is not allowed!");
        }
        
        if (description == null){
            throw new IllegalArgumentException("null description is not allowed!");
        }
        
        if (etypeURL == null){
            throw new IllegalArgumentException("null etype URL is not allowed! Use an empty string if needed!");
        }
        
        this.URL = URL;
        this.name = Dict.copyOf(name);
        this.description = Dict.copyOf(description);
        this.etypeURL = etypeURL;

    }

    private MinimalEntity() {
    }

    @Override
    public Long getGUID() {
        throw new UnsupportedOperationException("Deprecated!"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IDict getName() {
        return name;
    }

    @Override
    public void setName(Locale locale, String name) {
        throw new UnsupportedOperationException("Setting name is not allowed in " + this.getClass().getSimpleName());
    }

    @Override
    public void setName(Locale locale, List<String> names) {
        throw new UnsupportedOperationException("Setting name is not allowed in " + this.getClass().getSimpleName());
    }

    @Override
    public IDict getDescription() {
        return description;
    }

    @Override
    public void setDescription(Locale language, String description) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public Long getLocalID() {
        throw new UnsupportedOperationException("Deprecated!");
    }

    
    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public void setURL(String url) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public List<IAttribute> getStructureAttributes() {
        return new ArrayList();
    }

    @Override
    public void setStructureAttributes(List<IAttribute> attributes) {
        throw new UnsupportedOperationException("Setting structure attributes is not allowed in " + this.getClass().getSimpleName());
    }

    @Override
    public IEntityType getEtype() {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public String getEtypeURL() {
        return etypeURL;
    }

    @Override
    public void setEtype(IEntityType type) {
        throw new UnsupportedOperationException("Deprecated!");
    }

    @Override
    public IAttribute getAttribute(String attrDefURL) {
        throw new UnsupportedOperationException("Getting strucutre attributes is not allowed in " + this.getClass().getSimpleName());
    }

}
