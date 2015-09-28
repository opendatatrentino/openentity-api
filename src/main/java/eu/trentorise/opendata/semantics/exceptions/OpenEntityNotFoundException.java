/* 
 * Copyright 2015 Trento Rise  (trentorise.eu) 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.trentorise.opendata.semantics.exceptions;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;


/**
 * A runtime exception to raise when something is not found.
 * 
 * @author David Leoni <david.leoni@unitn.it>
 */
public class OpenEntityNotFoundException extends OpenEntityException {
    
    private static final long serialVersionUID = 1L;

    private static Logger LOG = Logger.getLogger(OpenEntityNotFoundException.class.getSimpleName());    
    
    private Set<String> expectedIds = new HashSet();
    private Set<String> foundIds = new HashSet();;
    
    
    /**
     * Creates the NotFoundException using the provided throwable
     */
    public OpenEntityNotFoundException(Throwable tr) {
        super(tr);       
    }

    /**
     * Creates the NotFoundException using the provided message and throwable
     */
    public OpenEntityNotFoundException(String msg, Throwable tr) {
        super(msg, tr);
       
    }

    /**
     * Creates the NotFoundException using the provided message
     */
    public OpenEntityNotFoundException(String msg) {
        super(msg);
       
    }
    
    public OpenEntityNotFoundException(Iterable<String> expectedIds, Iterable<String> foundIds){    
	super(makeMessage("", expectedIds, foundIds));
	
	this.expectedIds = Sets.newHashSet(expectedIds);
	this.foundIds = Sets.newHashSet(foundIds);
		
    }
    
    public static Set<String> missingIds(Iterable<String> expectedIds, Iterable<String> foundIds){
	Set expectedIdsSet = Sets.newHashSet(expectedIds);
	Set foundIdsSet = Sets.newHashSet(foundIds);
	
	if (expectedIdsSet.size() <= foundIdsSet.size()){
	    LOG.severe(" EXPECTED DIFFERENT IDs ARE " + expectedIdsSet.size()  + ", WHICH IS LESS OR EQUAL THAN FOUND DIFFERENT IDs: " + foundIdsSet.size() + ", IGNORING THEM!");
	    return new HashSet();
	}
	
	SetView<String> difference = Sets.difference(expectedIdsSet, foundIdsSet);
	
	return difference;	
    }
    
    private Set<String> getFoundIds() {
	return foundIds;
    }
    
    private static String makeMessage(String msg, Iterable<String> expectedIds, Iterable<String> foundIds){
	if (expectedIds == null){
	    LOG.warning("FOUND NULL EXPECTED IDS, IGNORING THEM!");
	    return msg;
	} 
	if (foundIds == null) {
	    LOG.warning("FOUND NULL FOUND IDS, IGNORING THEM!");
	    return msg;
	}
	String postfix =  Iterables.size(expectedIds) > Iterables.size(foundIds) ? "  EXPECTED " + Iterables.size(expectedIds) + " RESULTS, BUT FOUND ONLY " + Iterables.size(foundIds) + ". Following ids were not found: " + missingIds(expectedIds, foundIds).toString() : "";
	return msg + postfix;
    }
    
    public Set<String> getMissingIds(){
	return missingIds(this.expectedIds, this.foundIds);
    }
    
}
