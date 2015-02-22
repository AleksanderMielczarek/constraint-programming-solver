package com.po.constraintprogrammingsolver.gui.jobshop.util.data;

import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.ComparatorVariableTypeWrapper.*;
import static com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.IndomainTypeWrapper.*;
import static com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.SelectChoicePointTypeWrapper.*;
import static com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProviders.comparatorVariableJacopStrategyProvider;
import static com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProviders.simpleJacopStrategyProvider;
import static com.po.constraintprogrammingsolver.problems.strategy.comparatorvariable.ComparatorVariableType.*;
import static com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType.*;
import static com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointComparatorVariableType.SIMPLE_SELECT;
import static com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointComparatorVariableType.SPLIT_SELECT;
import static com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreType.INPUT_ORDER_SELECT;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-24
 */
public enum BenchmarkCombination {

    /*
    * Indomain max, min, median and input order select
    * */

    COMBINATION_1(simpleJacopStrategyProvider(INDOMAIN_MAX, INPUT_ORDER_SELECT),
            INDOMAIN_MAX_WRAPPER.getKey(), INPUT_ORDER_SELECT_WRAPPER.getKey()),

    COMBINATION_2(simpleJacopStrategyProvider(INDOMAIN_MIN, INPUT_ORDER_SELECT),
            INDOMAIN_MIN_WRAPPER.getKey(), INPUT_ORDER_SELECT_WRAPPER.getKey()),

    COMBINATION_3(simpleJacopStrategyProvider(INDOMAIN_MEDIAN, INPUT_ORDER_SELECT),
            INDOMAIN_MEDIAN_WRAPPER.getKey(), INPUT_ORDER_SELECT_WRAPPER.getKey()),

    /*
    * Indomain max, min, median and largest max and simple select
    * */

    COMBINATION_4(comparatorVariableJacopStrategyProvider(INDOMAIN_MAX, LARGEST_MAX, SIMPLE_SELECT),
            INDOMAIN_MAX_WRAPPER.getKey(), LARGEST_MAX_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),

    COMBINATION_5(comparatorVariableJacopStrategyProvider(INDOMAIN_MIN, LARGEST_MAX, SPLIT_SELECT),
            INDOMAIN_MIN_WRAPPER.getKey(), LARGEST_MAX_WRAPPER.getKey(), SPLIT_SELECT_WRAPPER.getKey()),

    COMBINATION_6(comparatorVariableJacopStrategyProvider(INDOMAIN_MEDIAN, LARGEST_MAX, SIMPLE_SELECT),
            INDOMAIN_MEDIAN_WRAPPER.getKey(), LARGEST_MAX_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),

    /*
    * Indomain max, min, median and largest min and simple select
    * */

    COMBINATION_7(comparatorVariableJacopStrategyProvider(INDOMAIN_MAX, LARGEST_MIN, SIMPLE_SELECT),
            INDOMAIN_MAX_WRAPPER.getKey(), LARGEST_MIN_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),

    COMBINATION_8(comparatorVariableJacopStrategyProvider(INDOMAIN_MIN, LARGEST_MIN, SPLIT_SELECT),
            INDOMAIN_MIN_WRAPPER.getKey(), LARGEST_MIN_WRAPPER.getKey(), SPLIT_SELECT_WRAPPER.getKey()),

    COMBINATION_9(comparatorVariableJacopStrategyProvider(INDOMAIN_MEDIAN, LARGEST_MIN, SIMPLE_SELECT),
            INDOMAIN_MEDIAN_WRAPPER.getKey(), LARGEST_MIN_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),

    /*
    * Indomain max, min, median and largest domain and simple select
    * */

    COMBINATION_10(comparatorVariableJacopStrategyProvider(INDOMAIN_MAX, LARGEST_DOMAIN, SIMPLE_SELECT),
            INDOMAIN_MAX_WRAPPER.getKey(), LARGEST_DOMAIN_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),

    COMBINATION_11(comparatorVariableJacopStrategyProvider(INDOMAIN_MIN, LARGEST_DOMAIN, SPLIT_SELECT),
            INDOMAIN_MIN_WRAPPER.getKey(), LARGEST_DOMAIN_WRAPPER.getKey(), SPLIT_SELECT_WRAPPER.getKey()),

    COMBINATION_12(comparatorVariableJacopStrategyProvider(INDOMAIN_MEDIAN, LARGEST_DOMAIN, SIMPLE_SELECT),
            INDOMAIN_MEDIAN_WRAPPER.getKey(), LARGEST_DOMAIN_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),

    /*
    * Indomain max, min, median and smallest max and simple select
    * */

    COMBINATION_13(comparatorVariableJacopStrategyProvider(INDOMAIN_MAX, SMALLEST_MAX, SIMPLE_SELECT),
            INDOMAIN_MAX_WRAPPER.getKey(), SMALLEST_MAX_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),

    COMBINATION_14(comparatorVariableJacopStrategyProvider(INDOMAIN_MIN, SMALLEST_MAX, SPLIT_SELECT),
            INDOMAIN_MIN_WRAPPER.getKey(), SMALLEST_MAX_WRAPPER.getKey(), SPLIT_SELECT_WRAPPER.getKey()),

    COMBINATION_15(comparatorVariableJacopStrategyProvider(INDOMAIN_MEDIAN, SMALLEST_MAX, SIMPLE_SELECT),
            INDOMAIN_MEDIAN_WRAPPER.getKey(), SMALLEST_MAX_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),

    /*
    * Indomain max, min, median and smallest min and simple select
    * */

    COMBINATION_16(comparatorVariableJacopStrategyProvider(INDOMAIN_MAX, SMALLEST_MIN, SIMPLE_SELECT),
            INDOMAIN_MAX_WRAPPER.getKey(), SMALLEST_MIN_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),

    COMBINATION_17(comparatorVariableJacopStrategyProvider(INDOMAIN_MIN, SMALLEST_MIN, SPLIT_SELECT),
            INDOMAIN_MIN_WRAPPER.getKey(), SMALLEST_MIN_WRAPPER.getKey(), SPLIT_SELECT_WRAPPER.getKey()),

    COMBINATION_18(comparatorVariableJacopStrategyProvider(INDOMAIN_MEDIAN, SMALLEST_MIN, SIMPLE_SELECT),
            INDOMAIN_MEDIAN_WRAPPER.getKey(), SMALLEST_MIN_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),

    /*
    * Indomain max, min, median and smallest domain and simple select
    * */

    COMBINATION_19(comparatorVariableJacopStrategyProvider(INDOMAIN_MAX, SMALLEST_DOMAIN, SIMPLE_SELECT),
            INDOMAIN_MAX_WRAPPER.getKey(), SMALLEST_DOMAIN_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey()),

    COMBINATION_20(comparatorVariableJacopStrategyProvider(INDOMAIN_MIN, SMALLEST_DOMAIN, SPLIT_SELECT),
            INDOMAIN_MIN_WRAPPER.getKey(), SMALLEST_DOMAIN_WRAPPER.getKey(), SPLIT_SELECT_WRAPPER.getKey()),

    COMBINATION_21(comparatorVariableJacopStrategyProvider(INDOMAIN_MEDIAN, SMALLEST_DOMAIN, SIMPLE_SELECT),
            INDOMAIN_MEDIAN_WRAPPER.getKey(), SMALLEST_DOMAIN_WRAPPER.getKey(), SIMPLE_SELECT_WRAPPER.getKey());


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
