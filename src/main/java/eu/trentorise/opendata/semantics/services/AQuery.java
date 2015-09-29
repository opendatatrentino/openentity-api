package eu.trentorise.opendata.semantics.services;

import org.immutables.value.Value;


/**
 * A generic query
 *
 */
public abstract class AQuery {    
    
    public static long DEFAULT_PAGE_SIZE = 10L;

    /**
     * Default page size is {@link #DEFAULT_PAGE_SIZE}
     */
    @Value.Default
    public long getPageSize(){
	return DEFAULT_PAGE_SIZE;
    };
    
    /**
     * The page index, starting from 0
     */
    @Value.Default
    public long getPageIndex(){
	return 0;
    }
	  
}


