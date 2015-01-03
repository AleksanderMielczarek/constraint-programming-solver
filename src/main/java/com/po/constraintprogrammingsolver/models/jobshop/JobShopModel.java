package com.po.constraintprogrammingsolver.models.jobshop;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jacop.search.Indomain;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopModel {
    private final StringProperty jobs;
    private final ObjectProperty<IndomainType> indomainType;

    public JobShopModel() {
        jobs = new SimpleStringProperty();
        indomainType = new SimpleObjectProperty<>();
    }

    public String getJobs() {
        return jobs.get();
    }

    public StringProperty jobsProperty() {
        return jobs;
    }

    public IndomainType getIndomainType() {
        return indomainType.get();
    }

    public ObjectProperty<IndomainType> indomainTypeProperty() {
        return indomainType;
    }

    public Indomain getIndomain() {
        return indomainType.get().getIndomain();
    }
}
