package org.elasticsearch.plugins.analysis.phone;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.elasticsearch.index.analysis.NonStrictPhoneAnalyzer;

import java.io.IOException;

public class NonStrictPhoneAnalyzerProvider extends AbstractIndexAnalyzerProvider<NonStrictPhoneAnalyzer> {

    protected NonStrictPhoneAnalyzer analyzer;

    @Inject
    public NonStrictPhoneAnalyzerProvider(IndexSettings indexSettings, Environment env, String name,
                                          Settings settings) throws IOException {
        super(indexSettings, name, settings);
        boolean addCountryCode = settings.getAsBoolean("addCountryCode", true);
        boolean addExtension = settings.getAsBoolean("addExtension", false);
        boolean generateNGrams = settings.getAsBoolean("generateNGrams", false);
        analyzer = new NonStrictPhoneAnalyzer(addCountryCode, addExtension, generateNGrams);
    }

    @Override
    public NonStrictPhoneAnalyzer get() {
        return analyzer;
    }

}