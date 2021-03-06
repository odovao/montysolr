/**
 * 
 */
package org.apache.solr.analysis.author;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

/**
 * @author jluker
 *
 */
public class AuthorNormalizeFilterFactory extends TokenFilterFactory {

	public AuthorNormalizeFilterFactory(Map<String,String> args) {
    super(args);
    if (!args.isEmpty()) {
      throw new IllegalArgumentException("Unknown parameter(s): " + args);
    }
  }

  /* (non-Javadoc)
	 * @see org.apache.solr.analysis.TokenFilterFactory#create(org.apache.lucene.analysis.TokenStream)
	 */
	public AuthorNormalizeFilter create(TokenStream input) {
		return new AuthorNormalizeFilter(input);
	}
}
