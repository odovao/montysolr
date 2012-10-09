package org.apache.solr.analysis.author;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.solr.analysis.author.AuthorNameVariantsCollectorFactory;


public class TestAuthorTransliterationFilter extends BaseTokenStreamTestCase {
	
	final class TestFilter extends TokenFilter {
		private final TypeAttribute typeAtt = addAttribute(TypeAttribute.class);
		public TestFilter(TokenStream input) {
			super(input);
		}
		public boolean incrementToken() throws IOException {
		    if (!input.incrementToken()) return false;
		    typeAtt.setType(AuthorUtils.TOKEN_TYPE_AUTHOR);
	        return true;
		}
	}
	
	public void testAuthorSynonyms() throws Exception {
		Reader reader = new StringReader("Müller, Bill");
		Tokenizer tokenizer = new KeywordTokenizer(reader);
		
	  	
		AuthorTransliterationFactory factory = new AuthorTransliterationFactory();
		
		TokenStream stream = factory.create(new TestFilter(tokenizer));
		
		String[] expected = { "Müller, Bill", "Mueller, Bill", "Muller, Bill" };
		assertTokenStreamContents(stream, expected);
	}
}
