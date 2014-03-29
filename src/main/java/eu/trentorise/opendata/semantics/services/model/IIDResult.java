package eu.trentorise.opendata.semantics.services.model;

import eu.trentorise.opendata.semantics.model.entity.IEntity;
import java.util.Set;

/**
 * A reconciliation result, expressed as a set of candidate entities or a new
 * one if none was found during the matching.
 *
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@trentorise.eu>
 * @date Mar 27, 2014
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
     * @return an entity if getAssignmentResult is different than MISSING, null
     * otherwise.
     */
    IEntity getResultEntity();

    /**
     * Gets a set of possible suggested entities.
     *
     * @return a set of possible suggested entities if getAssignmentResult is
     * REUSE, an empty set otherwiose.
     */
    Set<IEntity> getEntities();

}
