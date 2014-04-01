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
package eu.trentorise.opendata.semantics.model.provenance;

import java.util.Date;

/**
 *
 * @author Moaz
 */
public class Evidence {

    // The date of the import process
    private Date modificationDate;
    // The user who executes the import process
    private String userName;
    // The URL of the dataset which was used during the import
    private String url;
    // The name of the idenifier property in the dataset, e.g. "Codice Fiscale"
    private String idPropertyName;
    // The value of the identifier property in the dataset, e.g. "MHM34389C10"
    private String idPropertyValue;

    /**
     * @return the modificationDate
     */
    public Date getModificationDate() {
        return modificationDate;
    }

    /**
     * @param modificationDate the modificationDate to set
     */
    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the idPropertyName
     */
    public String getIdPropertyName() {
        return idPropertyName;
    }

    /**
     * @param idPropertyName the idPropertyName to set
     */
    public void setIdPropertyName(String idPropertyName) {
        this.idPropertyName = idPropertyName;
    }

    /**
     * @return the idPropertyValue
     */
    public String getIdPropertyValue() {
        return idPropertyValue;
    }

    /**
     * @param idPropertyValue the idPropertyValue to set
     */
    public void setIdPropertyValue(String idPropertyValue) {
        this.idPropertyValue = idPropertyValue;
    }
}
