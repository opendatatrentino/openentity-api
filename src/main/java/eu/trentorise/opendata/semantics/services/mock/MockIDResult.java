package eu.trentorise.opendata.semantics.services.mock;

import eu.trentorise.opendata.semantics.model.entity.IEntity;
import eu.trentorise.opendata.semantics.services.model.AssignmentResult;
import eu.trentorise.opendata.semantics.services.model.IIDResult;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author David Leoni
 */
public class MockIDResult implements IIDResult {
    
    AssignmentResult assignmentResult;
    IEntity resultEntity;
    Set<IEntity> entities;

    /**
     * Sets INVALID assignment result
     */
    public MockIDResult() {
        this.assignmentResult = AssignmentResult.INVALID;
        this.resultEntity = null;
        this.entities = new HashSet();        
    }
    
    public MockIDResult(AssignmentResult assignmentResult, IEntity resultEntity, Set<IEntity> entities) {
        this.assignmentResult = assignmentResult;
        this.resultEntity = resultEntity;
        this.entities = entities;
    }

    @Override
    public AssignmentResult getAssignmentResult() {
        return assignmentResult;
    }

    @Override
    public IEntity getResultEntity() {
        return resultEntity;
    }

    /**
     * @return unmodifiable set 
     */
    @Override
    public Set<IEntity> getEntities() {
        return Collections.unmodifiableSet(entities);
    }

    @Override
    public Long getGUID() {
        throw new UnsupportedOperationException("Deprecated!"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getURL() {
        if (resultEntity == null) {
            return null;
        } else {
            return resultEntity.getUrl();
        }
    }
    
}
    

