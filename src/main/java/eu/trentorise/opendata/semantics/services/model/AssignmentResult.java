/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.trentorise.opendata.semantics.services.model;

/**
 * The status of a reconciliation operation against a single entity
 * @author Ivan Tankoyeu <tankoyeui@disi.unitn.it>
 * @author David Leoni <david.leoni@unitn.it>
 * @date Mar 27, 2014
 */
public enum AssignmentResult {
    NEW,
    MISSING,
    REUSE
}
