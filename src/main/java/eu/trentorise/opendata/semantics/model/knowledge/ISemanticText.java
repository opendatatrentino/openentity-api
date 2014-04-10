package eu.trentorise.opendata.semantics.model.knowledge;

import java.util.List;
import java.util.Locale;
import javax.annotation.concurrent.Immutable;

/**
 * Lightweight interface to hold semantically enriched text. Method names are
 * modeled after methods of {@link NLText}. There are only tokens and no
 * multiwords. Text is only stored in root ISemanticText object and offsets are
 * always absolute and calculated with respects to it. Overlappings are not
 * allowed. Implementations of this interfac must be immutable.
 *
 * @author David Leoni <david.leoni@unitn.it>
 * @date 10 Apr 2014
 */
@Immutable
public interface ISemanticText {

    Locale getLocale();

    List<ISemanticSentence> getSentences();

    String getText();
}
