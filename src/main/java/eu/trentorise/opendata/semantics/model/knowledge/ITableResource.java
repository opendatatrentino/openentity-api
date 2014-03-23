/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.trentorise.opendata.semantics.model.knowledge;

import java.util.List;

/**
 * A model for a resource in a tabular format. 
 * @author David Leoni <david.leoni@trentorise.eu>
 * @date 13 Feb 2014
 */
public interface ITableResource {
    
    /**
     * 
     * @return The table headers as strings.
     */
    List<String> getHeaders();
        
    /**
     * 
     * @return  the column bodies. Each column is a list of strings. 
     */
    List<List<String>> getColumns();

    
}
