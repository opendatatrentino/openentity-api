/**
 * *****************************************************************************
 * Copyright 2012-2013 Trento Rise (www.trentorise.eu/)
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the GNU Lesser General Public License (LGPL)
 * version 2.1 which accompanies this distribution, and is available at
 *
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************
 */
package eu.trentorise.opendata.semantics.impl.model.knowledge;

import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import eu.trentorise.opendata.semantics.model.knowledge.IMeaning;
import eu.trentorise.opendata.semantics.model.knowledge.MeaningKind;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 * Equality is checked only considering the URL and kind
 * @author David Leoni <david.leoni@unitn.it>
 * @date 11 Apr 2014
 */
@Immutable
public class Meaning implements IMeaning {

    private String URL;
    private double probability;
    private MeaningKind kind;
    private IDict name;

   protected Meaning(){
        this.URL = "";
        this.probability = 0.0;
        this.kind = MeaningKind.CONCEPT;
        this.name = new Dict();
    }

    /** Equality is checked only considering the URL and kind */
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.URL != null ? this.URL.hashCode() : 0);
        hash = 29 * hash + (this.kind != null ? this.kind.hashCode() : 0);
        return hash;
    }

    /** Equality is checked only considering the URL and kind */    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Meaning other = (Meaning) obj;
        if ((this.URL == null) ? (other.URL != null) : !this.URL.equals(other.URL)) {
            return false;
        }
        if (this.kind != other.kind) {
            return false;
        }
        return true;
    }

    
    
    /**
     *
     * @param URL the URL of the entity or concept this meaning represents.      
     * @param probability Must be greater or equal than 0
     * @param meaningKind The kind can be either an entity or a concept.
     */
    public Meaning(String URL, double probability, MeaningKind meaningKind) {
        this(URL,probability,meaningKind, new Dict());
    }    
    
    /**
     *
     * @param URL the URL of the entity or concept this meaning represents. 
     * @param name the name of the entity or concept this meaning represents. 
     * @param probability Must be greater or equal than 0
     * @param meaningKind The kind can be either an entity or a concept.
     */
    public Meaning(String URL, double probability, MeaningKind meaningKind, IDict name) {
        if (probability < 0){
            throw new IllegalArgumentException("Probability must be greater or equal than 0, found instead: " + probability);
        }
        this.URL = URL;
        this.probability = probability;
        this.kind = meaningKind;
        this.name = name;
    }

    /**
     * Sorting is done based on the probability values
     */
    public int compareTo(IMeaning om) {
        double diff = this.getProbability() - om.getProbability();
        if (diff > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Determines the best meaning among the given one according to theIR
     * probabilities. If no best meaning is found null is returned.
     *
     * @param meanings a sorted list of meanings
     * @return the disambiguated meaning or null if no meaning can be clearly
     * dismabiguated.
     */
    @Nullable
    public static IMeaning disambiguate(List<? extends IMeaning> meanings) {
        final double FACTOR = 1.5;

        if (meanings.isEmpty()) {
            return null;
        }
        if (meanings.size() == 1) {
            IMeaning m = meanings.iterator().next();
            if (m.getURL() == null){
                return null;
            } else {
                return m;
            }
        }

        if (meanings.get(0).getProbability() > FACTOR / meanings.size()
                && meanings.get(0).getURL() != null) {
            return meanings.get(0);
        } else {
            return null;
        }

    }

    public double getProbability() {
        return probability;
    }

    public String getURL() {
        return URL;
    }

    public MeaningKind getKind() {
        return kind;
    }

    public IDict getName() {
        return name;
    }
    
    private Meaning(IMeaning meaning){
        this.URL = meaning.getURL();
        this.kind = meaning.getKind();
        this.name = meaning.getName();
        this.probability = meaning.getProbability();
    }
    
    public static Meaning copyOf(IMeaning meaning){
        if (meaning instanceof Meaning){
            return (Meaning) meaning;
        } else {
            return new Meaning(meaning);
        }
    }
}