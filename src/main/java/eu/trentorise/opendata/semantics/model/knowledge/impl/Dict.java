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
package eu.trentorise.opendata.semantics.model.knowledge.impl;

import eu.trentorise.opendata.semantics.model.knowledge.IDict;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author David Leoni
 */
/**
 * An immutable dictionary of translations
 *
 * @author David Leoni
 */
@Immutable
public class Dict implements IDict {

    private HashMap<Locale, List<String>> translations;

    public Dict() {
        translations = new HashMap<Locale, List<String>>();
    }

    public Dict(@Nullable IDict dict) {
        this();
        if (dict != null) {
            for (Locale loc : dict.getLocales()) {
                List<String> names = new ArrayList<String>();
                for (String s : dict.getStrings(loc)) {
                    names.add(s);
                }
                this.translations.put(loc, Collections.unmodifiableList(names));
            }
        }
    }

    private Dict(HashMap<Locale, List<String>> inputMap) {
        this();
        this.translations = inputMap;
    }

    /**
     * Sets english translation to provided text
     * @param text the given text
     */
    public Dict(final String text) {
        this(text, Locale.ENGLISH);
    }
    
    /**
     * Sets english translation to provided text
     * @param text the given text in the fiven locale
     * @param locale the locale of the provided text
     */
    public Dict(final String text, Locale locale) {
        this();
        translations.put(locale, new ArrayList() {
            {
                add(text);
            }
        });
    }    

    @Override
    public Set<Locale> getLocales() {
        return translations.keySet();
    }


    public boolean contains(String text) {
        
        for (Locale loc : getLocales()) {
            String lowText = text.toLowerCase(loc);
            for (String t : translations.get(loc)){
                if (t.toLowerCase(loc).contains(lowText)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @return a new dictionary. The original one is not mutated.
     */
    public Dict putTranslation(Locale locale, List<String> strings) {
        HashMap<Locale, List<String>> newTranslations = new HashMap<Locale, List<String>>();

        List<String> lst = new ArrayList<String>();
        for (String s : strings) {
            lst.add(s);
        }
        for (Locale loc : translations.keySet()) {
            newTranslations.put(loc, translations.get(loc));
        }
        newTranslations.put(locale, Collections.unmodifiableList(lst));

        return new Dict(newTranslations);
    }

    /**
     *
     * @return a new dictionary. The original one is not mutated.
     */
    public Dict putTranslation(Locale locale, String string) {
        HashMap<Locale, List<String>> newTranslations = new HashMap<Locale, List<String>>();

        List<String> lst = new ArrayList<String>();

        lst.add(string);

        for (Locale loc : translations.keySet()) {
            newTranslations.put(loc, translations.get(loc));
        }
        newTranslations.put(locale, Collections.unmodifiableList(lst));

        return new Dict(newTranslations);
    }

    @Nullable
    public String getString(Locale locale) {
        List<String> as = translations.get(locale);
        if (as == null) {
            return null;
        }
        if (as.isEmpty()) {
            return null;
        } else {
            return as.get(0);
        }
    }

    /**
     * Checks if there is at least one translation in the given locale
     *
     * @param locale
     * @return true if there is at least one translation in the given locale,
     * false otherwise.
     */
    public boolean hasTranslation(Locale locale) {
        if (translations.containsKey(locale)) {
            return !translations.get(locale).isEmpty();
        } else {
            return false;
        }
    }

    @Override
    public List<String> getStrings(Locale locale) {
        if (translations.containsKey(locale)) {
            return translations.get(locale);
        } else {
            return new ArrayList<String>();
        }
    }

    /**
     * Checks if there is at least one non-empty translation
     */
    public boolean isEmpty() {
        for (Locale locale : getLocales()) {
            if (!translations.get(locale).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pads white spaces until maxLength is reached
     *
     * @param msg the message to pad with spaces
     * @param maxLength length after which msg is truncated
     * @return the padded msg
     */
    private static String padLeft(String msg, int maxLength) {

        String nmot;
        if (msg.length() > maxLength) {
            nmot = msg.substring(0, msg.length() - 3) + "...";
        } else {
            nmot = String.format("%" + maxLength + "s", msg);
        }
        return nmot;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (Locale loc : translations.keySet()) {
            sb.append(padLeft(loc.toString(), 10))
                    .append(": [");
            boolean first = true;
            for (String t : translations.get(loc)) {
                if (first) {
                    first = false;
                } else {
                    sb.append(", ");
                }
                sb.append(t);
            }
            sb.append("]\n");

        }
        return sb.toString();
    }
}
