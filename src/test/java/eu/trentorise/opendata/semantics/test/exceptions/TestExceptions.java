package eu.trentorise.opendata.semantics.test.exceptions;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import eu.trentorise.opendata.semantics.exceptions.OpenEntityNotFoundException;

public class TestExceptions {

    
    @Test
    public void testNotFound(){
	
	OpenEntityNotFoundException ex = new OpenEntityNotFoundException(ImmutableList.of("a", "b"), ImmutableList.of("a"));	
	assertEquals(ImmutableSet.of("b"), ex.getMissingIds());
    }

    @Test
    public void testNotFoundNull(){
	
	OpenEntityNotFoundException ex = new OpenEntityNotFoundException(ImmutableList.of("a", "b", "c"), Lists.newArrayList("a",null));	
	assertEquals(ImmutableSet.of("b", "c"), ex.getMissingIds());
    }
    
    
}
