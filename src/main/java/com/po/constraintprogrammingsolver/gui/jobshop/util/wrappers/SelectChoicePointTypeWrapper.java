package com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers;

import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.StringConverter;

import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2015-01-04.
 */
public enum SelectChoicePointTypeWrapper {
    INPUT_ORDER_SELECT(SelectChoicePointStoreType.INPUT_ORDER_SELECT, "combo.select.input.order.select"),
    SIMPLE_SELECT(SelectChoicePointComparatorVariableType.SIMPLE_SELECT, "combo.select.simple.select"),
    SPLIT_SELECT(SelectChoicePointComparatorVariableType.SPLIT_SELECT, "combo.select.split.select");

    private final Optional<SelectChoicePointStoreType> selectChoicePointStoreType;
    private final Optional<SelectChoicePointComparatorVariableType> selectChoicePointComparatorVariableType;
    private final String key;
    private final boolean store;
    private final boolean comparatorVariable;
    private final BooleanProperty visible;

    SelectChoicePointTypeWrapper(SelectChoicePointStoreType selectChoicePointStoreType, String key) {
        this.selectChoicePointStoreType = Optional.of(selectChoicePointStoreType);
        this.selectChoicePointComparatorVariableType = Optional.empty();
        this.key = key;
        store = true;
        comparatorVariable = false;
        visible = new SimpleBooleanProperty(false);
    }

    SelectChoicePointTypeWrapper(SelectChoicePointComparatorVariableType selectChoicePointComparatorVariableType, String key) {
        this.selectChoicePointStoreType = Optional.empty();
        this.selectChoicePointComparatorVariableType = Optional.of(selectChoicePointComparatorVariableType);
        this.key = key;
        store = false;
        comparatorVariable = true;
        visible = new SimpleBooleanProperty(true);
    }

    public Optional<SelectChoicePointStoreType> getSelectChoicePointStoreType() {
        return selectChoicePointStoreType;
    }

    public Optional<SelectChoicePointComparatorVariableType> getSelectChoicePointComparatorVariableType() {
        return selectChoicePointComparatorVariableType;
    }

    public String getKey() {
        return key;
    }

    public boolean isStore() {
        return store;
    }

    public boolean isComparatorVariable() {
        return comparatorVariable;
    }

    public boolean getVisible() {
        return visible.get();
    }

    public BooleanProperty visibleProperty() {
        return visible;
    }

    public static StringConverter<SelectChoicePointTypeWrapper> getStringConverter(ResourceBundle resources) {
        return new StringConverter<SelectChoicePointTypeWrapper>() {
            @Override
            public String toString(SelectChoicePointTypeWrapper object) {
                return resources.getString(object.getKey());
            }

            @Override
            public SelectChoicePointTypeWrapper fromString(String string) {
                return valueOf(string);
            }
        };
    }
}
