package com.po.constraintprogrammingsolver.models.jobshop;

import javafx.util.StringConverter;
import org.jacop.core.IntVar;
import org.jacop.search.*;

import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2015-01-03.
 */
public enum IndomainType {
    INDOMAIN_MIN(new IndomainMin<>(), "combo.indomain.min"),
    INDOMAIN_MAX(new IndomainMax<>(), "combo.indomain.max"),
    INDOMAIN_MIDDLE(new IndomainMiddle<>(), "combo.indomain.middle"),
    INDOMAIN_RANDOM(new IndomainRandom<>(), "combo.indomain.random"),
    INDOMAIN_MEDIAN(new IndomainMedian<>(), "combo.indomain.median"),
    INDOMAIN_SIMPLE_RANDOM(new IndomainSimpleRandom<>(), "combo.indomain.simplerandom");

    private final Indomain<IntVar> indomain;
    private final String key;

    IndomainType(Indomain<IntVar> indomain, String key) {
        this.indomain = indomain;
        this.key = key;
    }

    public Indomain<IntVar> getIndomain() {
        return indomain;
    }

    public String getKey() {
        return key;
    }

    public static StringConverter<IndomainType> getStringConverter(ResourceBundle resources) {
        return new StringConverter<IndomainType>() {
            @Override
            public String toString(IndomainType object) {
                return resources.getString(object.getKey());
            }

            @Override
            public IndomainType fromString(String string) {
                return valueOf(string);
            }
        };
    }
}
