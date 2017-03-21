package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Analyzer;

public class NonStrictPhoneAnalyzer extends Analyzer {
    private boolean addCountryCode;
    private boolean addExtension;
    private boolean generateNGrams;

    public NonStrictPhoneAnalyzer(boolean addCountryCode, boolean addExtension, boolean generateNGrams) {
        this.addCountryCode = addCountryCode;
        this.addExtension = addExtension;
        this.generateNGrams = generateNGrams;
    }

    @Override
    protected TokenStreamComponents createComponents(String field) {
        NonStrictPhoneTokenizer tokenizer = new NonStrictPhoneTokenizer(addCountryCode, addExtension, generateNGrams);
        return new TokenStreamComponents(tokenizer);
    }

}