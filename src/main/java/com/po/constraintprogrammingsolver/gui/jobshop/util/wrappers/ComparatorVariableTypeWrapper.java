package com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers;

import com.po.constraintprogrammingsolver.problems.strategy.comparatorvariable.ComparatorVariableType;
import javafx.util.StringConverter;

import java.util.ResourceBundle;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-04
 */
public enum ComparatorVariableTypeWrapper {
    LARGEST_DOMAIN_WRAPPER(ComparatorVariableType.LARGEST_DOMAIN, "combo.comparator.largest.domain"),
    LARGEST_MAX_WRAPPER(ComparatorVariableType.LARGEST_MAX, "combo.comparator.largest.max"),
    LARGEST_MIN_WRAPPER(ComparatorVariableType.LARGEST_MIN, "combo.comparator.largest.min"),
    MAX_REGRET_WRAPPER(ComparatorVariableType.MAX_REGRET, "combo.comparator.max.regret"),
    MIN_DOMAIN_OVER_DEGREE_WRAPPER(ComparatorVariableType.MIN_DOMAIN_OVER_DEGREE, "combo.comparator.min.domain.over.degree"),
    MOST_CONSTRAINED_DYNAMIC_WRAPPER(ComparatorVariableType.MOST_CONSTRAINED_DYNAMIC, "combo.comparator.most.constrained.dynamic"),
    MOST_CONSTRAINED_STATIC_WRAPPER(ComparatorVariableType.MOST_CONSTRAINED_STATIC, "combo.comparator.most.constrained.static"),
    SMALLEST_DOMAIN_WRAPPER(ComparatorVariableType.SMALLEST_DOMAIN, "combo.comparator.smallest.domain"),
    SMALLEST_MAX_WRAPPER(ComparatorVariableType.SMALLEST_MAX, "combo.comparator.smallest.max"),
    SMALLEST_MIN_WRAPPER(ComparatorVariableType.SMALLEST_MIN, "combo.comparator.smallest.min"),
    WEIGHTED_DEGREE_WRAPPER(ComparatorVariableType.WEIGHTED_DEGREE, "combo.comparator.weighted.degree");

    private final ComparatorVariableType comparatorVariableType;
    private final String key;

    ComparatorVariableTypeWrapper(ComparatorVariableType comparatorVariableType, String key) {
        this.comparatorVariableType = comparatorVariableType;
        this.key = key;
    }

    public ComparatorVariableType getComparatorVariableType() {
        return comparatorVariableType;
    }

    public String getKey() {
        return key;
    }

    public static StringConverter<ComparatorVariableTypeWrapper> getStringConverter(ResourceBundle resources) {
        return new StringConverter<ComparatorVariableTypeWrapper>() {
            @Override
            public String toString(ComparatorVariableTypeWrapper object) {
                return resources.getString(object.getKey());
            }

            @Override
            public ComparatorVariableTypeWrapper fromString(String string) {
                return valueOf(string);
            }
        };
    }
}
