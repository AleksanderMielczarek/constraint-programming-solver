package com.po.constraintprogrammingsolver.gui.utils.jobshop;

import com.po.constraintprogrammingsolver.gui.utils.jobshop.wrappers.ComparatorVariableTypeWrapper;
import com.po.constraintprogrammingsolver.gui.utils.jobshop.wrappers.IndomainTypeWrapper;
import com.po.constraintprogrammingsolver.gui.utils.jobshop.wrappers.SelectChoicePointTypeWrapper;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopModel {
    private final StringProperty jobs;
    private final ObjectProperty<IndomainTypeWrapper> indomainTypeWrapper;
    private final ObjectProperty<SelectChoicePointTypeWrapper> selectChoicePointTypeWrapper;
    private final ObjectProperty<ComparatorVariableTypeWrapper> comparatorVariableTypeWrapper;

    public JobShopModel() {
        jobs = new SimpleStringProperty();

        indomainTypeWrapper = new SimpleObjectProperty<>();
        selectChoicePointTypeWrapper = new SimpleObjectProperty<>();
        comparatorVariableTypeWrapper = new SimpleObjectProperty<>();
    }

    public String getJobs() {
        return jobs.get();
    }

    public StringProperty jobsProperty() {
        return jobs;
    }

    public IndomainTypeWrapper getIndomainTypeWrapper() {
        return indomainTypeWrapper.get();
    }

    public ObjectProperty<IndomainTypeWrapper> indomainTypeWrapperProperty() {
        return indomainTypeWrapper;
    }

    public SelectChoicePointTypeWrapper getSelectChoicePointTypeWrapper() {
        return selectChoicePointTypeWrapper.get();
    }

    public ObjectProperty<SelectChoicePointTypeWrapper> selectChoicePointTypeWrapperProperty() {
        return selectChoicePointTypeWrapper;
    }

    public ComparatorVariableTypeWrapper getComparatorVariableTypeWrapper() {
        return comparatorVariableTypeWrapper.get();
    }

    public ObjectProperty<ComparatorVariableTypeWrapper> comparatorVariableTypeWrapperProperty() {
        return comparatorVariableTypeWrapper;
    }
}
