/* 
 * Copyright 2013-2015   Trento Rise   trentorise.eu
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
package eu.trentorise.opendata.semantics.model.knowledge;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nullable;

/**
 * Represents a dictionary of a string that may have translations in several
 * languages.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date Apr 17, 2014
 */
public interface IDict {

    /**
     * Gets the translations in the given locale.
     *
     * @param locale the language of the desired translation
     * @return the strings in the given locale if present. If no string is
     * present an empty list is returned.
     */
    List<String> getStrings(Locale locale);

    /**
     * Gets the first translation in the given locale.
     *
     * @param locale the language of the desired translation
     * @return the string in the given locale if present. If no string is
     * present null is returned.
     */
    @Nullable
    String getString(Locale locale);

    /**
     * Gets the locales for which translations are present in the IDict
     *
     * @return the available locales
     */
    Set<Locale> getLocales();

    /**
     * Checks if provided text is contained in any of the provided translations.
     * Both text and translations to check are lowercased according to their
     * locale.
     *
     * @param text the text to search for
     * @return true if text is contained in any of the translations, false
     * otherwise
     */
    boolean contains(String text);

    /**
     * @return the number of translations present in the dictionary
     */
    int translationsCount();

    /**
     * Tries its best to produce a meaningful string in one of the provided
     * languages
     *
     * @return A string in the first available language from the list of
     * provided locales. If no translation is available, in order, defaults to
     * English and then whatever it can find in the list of translations. Empty
     * or null strings are discarded. Dirty strings like strings containing the
     * string "null" are also discarded. If no valid translation
     * is available at all, returns the empty string with null locale.
     */
    public ISemanticText toSemText(List<Locale> locales);

}
