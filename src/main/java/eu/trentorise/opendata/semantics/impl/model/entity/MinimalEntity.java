package eu.trentorise.opendata.semantics.impl.model.entity;

/**
 *
 * @author David Leoni
 */
import eu.trentorise.opendata.semantics.model.entity.IAttribute;
import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.model.entity.IEntityType;
import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.model.knowledge.impl.Dict;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/**
 * Always has URL, and might have some additional info, like name
 * and description, but they can still be empty (but never null). EtypeURL can be empty.
 * Trying to set any structureAttribute will throw an exception.
 * 
 * @author David Leoni
 */
public class MinimalEntity implements IEntity {

    private String URL;

    private IDict name;

    private IDict description;

    @Nullable
    private String etypeURL;

    /**
     * 
     * @param URL Must be provided.
     * @param name if null will be silently converted to empty dict.
     * @param description if null will be silently converted to empty dict.
     * @param etypeURL May be null
     */
    public MinimalEntity(String URL, IDict name, IDict description, @Nullable String etypeURL) {
        if (URL == null) {
            throw new IllegalArgumentException("At least URL must be non null in " + this.getClass().getSimpleName());
        }

        this.URL = URL;

        if (name == null) {
            this.name = new Dict();
        } else {
            this.name = name;
        }

        if (description == null) {
            this.description = new Dict();
        } else {
            this.description = description;
        }

        this.etypeURL = etypeURL;
    }
    
    private MinimalEntity(){}

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

    /**
     * @return 
     */
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
    @Nullable
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
