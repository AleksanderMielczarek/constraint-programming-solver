package com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers;

import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType;
import javafx.util.StringConverter;

import java.util.ResourceBundle;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-03
 */
public enum IndomainTypeWrapper {
    INDOMAIN_MIN_WRAPPER(IndomainType.INDOMAIN_MIN, "combo.indomain.min"),
    INDOMAIN_MAX_WRAPPER(IndomainType.INDOMAIN_MAX, "combo.indomain.max"),
    INDOMAIN_MIDDLE_WRAPPER(IndomainType.INDOMAIN_MIDDLE, "combo.indomain.middle"),
    INDOMAIN_RANDOM_WRAPPER(IndomainType.INDOMAIN_RANDOM, "combo.indomain.random"),
    INDOMAIN_MEDIAN_WRAPPER(IndomainType.INDOMAIN_MEDIAN, "combo.indomain.median"),
    INDOMAIN_SIMPLE_RANDOM_WRAPPER(IndomainType.INDOMAIN_SIMPLE_RANDOM, "combo.indomain.simple.random");

    private final IndomainType indomainType;
    private final String key;

    IndomainTypeWrapper(IndomainType indomainType, String key) {
        this.indomainType = indomainType;
        this.key = key;
    }

    public IndomainType getIndomainType() {
        return indomainType;
    }

    public String getKey() {
        return key;
    }

    public static StringConverter<IndomainTypeWrapper> getStringConverter(ResourceBundle resources) {
        return new StringConverter<IndomainTypeWrapper>() {
            @Override
            public String toString(IndomainTypeWrapper object) {
                return resources.getString(object.getKey());
            }

            @Override
            public IndomainTypeWrapper fromString(String string) {
                return valueOf(string);
            }
        };
    }
}
