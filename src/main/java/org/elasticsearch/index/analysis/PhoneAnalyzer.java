package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

public class PhoneAnalyzer extends Analyzer {
	
    @Override
    protected TokenStreamComponents createComponents(String arg0) {
        Tokenizer tokenizer = new PhoneTokenizer();
        return new TokenStreamComponents(tokenizer);
    }

}