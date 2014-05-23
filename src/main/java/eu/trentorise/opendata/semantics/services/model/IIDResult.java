package eu.trentorise.opendata.semantics.services.model;

import eu.trentorise.opendata.semantics.model.entity.IEntity;
import java.util.Set;
import javax.annotation.Nullable;

/**
 * A reconciliation result, expressed as a set of candidate entities or a new
 * one if none was found during the matching.
 *
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date May 12, 2014
 */
public interface IIDResult {

    /**
     * Gets the result of the id matching.
     *
     * @return the assignment result.
     */
    AssignmentResult getAssignmentResult();

    /**
     * Gets the selected entity. 
     *
     * @return an entity if getAssignmentResult is NEW or REUSE, null
     * otherwise.
     */
    @Nullable IEntity getResultEntity();

    /**
     * Gets a set of possible suggested entities.
     *
     * @return a set of possible suggested entities if getAssignmentResult is
     * REUSE, an empty set otherwise.
     */
    Set<IEntity> getEntities();
    
    /** Gets a global ID of the matched entity
     * @deprecated use getURL instead
     * @return global identifier of the entity
     */
    Long getGUID();
    
    
    /** Gets the URL of the matched entity
     * @return the URL of the matched entity if getAssignmentResult is NEW or REUSE, null
     * otherwise.
     */
    @Nullable String getURL();

}
