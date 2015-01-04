package com.po.constraintprogrammingsolver.models.jobshop.wrappers;

import com.po.constraintprogrammingsolver.problems.jobshop.factories.costfunction.CostFunctionType;
import javafx.util.StringConverter;

import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2015-01-04.
 */
public enum CostFunctionTypeWrapper {
    NO_COST_FUNCTION("combo.cost.no.function"),
    MIN_MAKESPAN(CostFunctionType.MIN_MAKESPAN, "combo.cost.min.makespan");

    private final Optional<CostFunctionType> costFunctionType;
    private final String key;

    CostFunctionTypeWrapper(CostFunctionType costFunctionType, String key) {
        this.costFunctionType = Optional.of(costFunctionType);
        this.key = key;
    }

    CostFunctionTypeWrapper(String key) {
        costFunctionType = Optional.empty();
        this.key = key;
    }

    public Optional<CostFunctionType> getCostFunctionType() {
        return costFunctionType;
    }

    public String getKey() {
        return key;
    }

    public static StringConverter<CostFunctionTypeWrapper> getStringConverter(ResourceBundle resources) {
        return new StringConverter<CostFunctionTypeWrapper>() {
            @Override
            public String toString(CostFunctionTypeWrapper object) {
                return resources.getString(object.getKey());
            }

            @Override
            public CostFunctionTypeWrapper fromString(String string) {
                return valueOf(string);
            }
        };
    }
}
