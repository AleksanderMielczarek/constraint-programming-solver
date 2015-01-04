package com.po.constraintprogrammingsolver.models.jobshop;

import com.po.constraintprogrammingsolver.models.jobshop.wrappers.ComparatorVariableTypeWrapper;
import com.po.constraintprogrammingsolver.models.jobshop.wrappers.CostFunctionTypeWrapper;
import com.po.constraintprogrammingsolver.models.jobshop.wrappers.IndomainTypeWrapper;
import com.po.constraintprogrammingsolver.models.jobshop.wrappers.SelectChoicePointTypeWrapper;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopModel {
    private final StringProperty jobs;
    private final ObjectProperty<CostFunctionTypeWrapper> costFunctionTypeWrapper;
    private final ObjectProperty<IndomainTypeWrapper> indomainTypeWrapper;
    private final ObjectProperty<SelectChoicePointTypeWrapper> selectChoicePointTypeWrapper;
    private final ObjectProperty<ComparatorVariableTypeWrapper> comparatorVariableTypeWrapper;

    public JobShopModel() {
        jobs = new SimpleStringProperty();
        costFunctionTypeWrapper = new SimpleObjectProperty<>();
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

    public CostFunctionTypeWrapper getCostFunctionTypeWrapper() {
        return costFunctionTypeWrapper.get();
    }

    public ObjectProperty<CostFunctionTypeWrapper> costFunctionTypeWrapperProperty() {
        return costFunctionTypeWrapper;
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
