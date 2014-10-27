package eu.trentorise.opendata.semantics.model.entity;

import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import javax.annotation.Nullable;

/**
 *
 * @author David Leoni
 */
public interface ITypeSig {
    
    boolean isList();
    
    String getDataType();
    
    @Nullable
    String getEtypeURL();
    
    @Nullable
    IDict getEtypeName();

    
}
