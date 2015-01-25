package com.po.constraintprogrammingsolver.gui.jobshop.util.data;

import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.ComparatorVariableTypeWrapper.*;
import static com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.IndomainTypeWrapper.*;
import static com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.SelectChoicePointTypeWrapper.INPUT_ORDER_SELECT_WRAPPER;
import static com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.SelectChoicePointTypeWrapper.SIMPLE_SELECT_WRAPPER;
import static com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProviders.comparatorVariableJacopStrategyProvider;
import static com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProviders.simpleJacopStrategyProvider;
import static com.po.constraintprogrammingsolver.problems.strategy.comparatorvariable.ComparatorVariableType.*;
import static com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType.*;
import static com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointComparatorVariableType.SIMPLE_SELECT;
import static com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreType.INPUT_ORDER_SELECT;

/**
 * Created by Aleksander on 2015-01-24.
 */
public enum BenchmarkCombination {
    COMBINATION_1(simpleJacopStrategyProvider(INDOMAIN_MAX, INPUT_ORDER_SELECT), INDOMAIN_MAX_WRAPPER.getKey(), INPUT_ORDER_SELECT_WRAPPER.getKey()),
    COMBINATION_2(simpleJacopStrategyProvider(INDOMAIN_MIN, INPUT_ORDER_SELECT), INDOMAIN_MIN_WRAPPER.getKey(), INPUT_ORDER_SELECT_WRAPPER.getKey()),
    COMBINATION_3(simpleJacopStrategyProvider(INDOMAIN_MEDIAN, INPUT_ORDER_SELECT), INDOMAIN_MEDIAN_WRAPPER.getKey(), INPUT_ORDER_SELECT_WRAPPER.getKey()),
    COMBINATION_4(comparatorVariableJacopStrategyProvider(INDOMAIN_MAX, LARGEST_DOMAIN, SIMPLE_SELECT), INDOMAIN_MAX_WRAPPER.getKey(), LARGEST_DOMAIN_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),
    COMBINATION_5(comparatorVariableJacopStrategyProvider(INDOMAIN_MAX, LARGEST_MAX, SIMPLE_SELECT), INDOMAIN_MAX_WRAPPER.getKey(), LARGEST_MAX_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),
    COMBINATION_6(comparatorVariableJacopStrategyProvider(INDOMAIN_MAX, LARGEST_MIN, SIMPLE_SELECT), INDOMAIN_MAX_WRAPPER.getKey(), LARGEST_MIN_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey());

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private final JacopStrategyProvider jacopStrategyProvider;
    private final String[] keys;

    BenchmarkCombination(JacopStrategyProvider jacopStrategyProvider, String... keys) {
        this.jacopStrategyProvider = jacopStrategyProvider;
        this.keys = keys;
    }

    public String name(ResourceBundle resources) {
        return Arrays.stream(keys)
                .map(resources::getString)
                .map(String::toLowerCase)
                .collect(Collectors.joining(StringUtils.SPACE));
    }

    public String label(ResourceBundle resources) {
        return Arrays.stream(keys)
                .map(resources::getString)
                .map(String::toLowerCase)
                .collect(Collectors.joining(LINE_SEPARATOR));
    }

    public JacopStrategyProvider getJacopStrategyProvider() {
        return jacopStrategyProvider;
    }
}
