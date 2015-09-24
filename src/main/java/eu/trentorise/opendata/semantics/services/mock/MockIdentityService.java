package eu.trentorise.opendata.semantics.services.mock;

import eu.trentorise.opendata.commons.Dict;
import eu.trentorise.opendata.semantics.DataTypes;
import eu.trentorise.opendata.semantics.model.entity.Entity;
import eu.trentorise.opendata.semantics.model.entity.Etype;
import eu.trentorise.opendata.semantics.services.IEkb;
import eu.trentorise.opendata.semantics.services.IIdentityService;
import eu.trentorise.opendata.semantics.services.IdResult;
import eu.trentorise.opendata.semantics.services.mock.MockEtypeService;
import static com.google.common.base.Preconditions.checkNotNull;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import eu.trentorise.opendata.semantics.services.AssignmentResult;

/**
 *
 * @author David Leoni
 */
public class MockIdentityService implements IIdentityService {

    private IEkb ekb;
    private MockEntityService mockEnServ;
    
    public MockIdentityService(IEkb ekb) {
	checkNotNull(ekb);
	this.ekb = ekb;
	this.mockEnServ = new MockEntityService(ekb);
    }

    /**
     * 0,1,2: NEW   3: INVALID   > 3: REUSE with 2 candidates
    */
    @Override
    public List<IdResult> assignURL(List<Entity> entitiesToRecon, int numCandidates) {
        List<IdResult> ret = new ArrayList();
        for (int i = 0; i < entitiesToRecon.size(); i++){
             Entity e = entitiesToRecon.get(i);
            MockEtypeService ets = new MockEtypeService();
            Etype et = ets.readEtype(e.getEtypeId());
            
            Etype nameEtype = null;
            if (DataTypes.STRUCTURE.equals(et.nameAttrDef().getType().getDatatype())){                
                nameEtype = ets.readEtype(et.nameAttrDef().getType().getEtypeId());
            }
            
            
            
            Entity me_1 = mockEnServ.newEntity("http://odrtest.org/entities/new/mock-url-1",et, Dict.of("test name 1"), Dict.of("test descr 1"));            
            Entity me_2 = mockEnServ.newEntity("http://odrtest.org/entities/mock-url-2",et, Dict.of("test name 2"), Dict.of("test descr 2"));
            Entity me_3 = mockEnServ.newEntity("http://odrtest.org/entities/mock-url-3",et, Dict.of("test name 3"), Dict.of("test descr 3"));

            
            IdResult res = null;
            
            if (i < 3){        	        	       
                res = IdResult.builder().setResultEntity(me_1).setAssignmentResult(AssignmentResult.NEW).build();
            }
            if (i == 3) {
        	res = IdResult.of(); // INVALID
            } 
            if ( i > 3)  {                                
                res = IdResult.builder().setResultEntity(me_2)
                	.setAssignmentResult(AssignmentResult.REUSE)
                	.addEntities(me_2, me_3)
                	.build();
                
            }
            
            ret.add(res);
        }
        return ret;
    }


    
}