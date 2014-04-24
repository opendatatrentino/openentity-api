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
package eu.trentorise.opendata.semantics;

/**
 * Thrown whenever implementations of open entity interfaces do not comply with
 * the specs.
 *
 * @author David Leoni
 */
public class IntegrityException extends OpenEntityException {

    public IntegrityException(String s) {
        super(s);
    }

    public IntegrityException(String s, Exception ex) {
        super(s, ex);
    }

    public IntegrityException(Exception ex) {
        super(ex);
    }

}
