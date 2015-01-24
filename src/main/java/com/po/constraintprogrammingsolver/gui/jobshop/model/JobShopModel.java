package com.po.constraintprogrammingsolver.gui.jobshop.model;

import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.ComparatorVariableTypeWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.IndomainTypeWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.SelectChoicePointTypeWrapper;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jfree.data.gantt.TaskSeries;

/**
 * Created by Aleksander on 2015-01-23.
 */
public class JobShopModel {
    private final ObjectProperty<IndomainTypeWrapper> indomain = new SimpleObjectProperty<>();
    private final ObjectProperty<SelectChoicePointTypeWrapper> selectChoicePoint = new SimpleObjectProperty<>();
    private final ObjectProperty<ComparatorVariableTypeWrapper> comparatorVariable = new SimpleObjectProperty<>();

    private final BooleanProperty comparatorVariableVisible = new SimpleBooleanProperty();

    private final StringProperty jobShopData = new SimpleStringProperty();

    private final IntegerProperty cost = new SimpleIntegerProperty();
    private final IntegerProperty backtracks = new SimpleIntegerProperty();
    private final IntegerProperty decisions = new SimpleIntegerProperty();
    private final IntegerProperty maximumDepth = new SimpleIntegerProperty();
    private final IntegerProperty nodes = new SimpleIntegerProperty();
    private final IntegerProperty wrongDecisions = new SimpleIntegerProperty();
    private final LongProperty time = new SimpleLongProperty();

    private final StringProperty jobShopResult = new SimpleStringProperty();

    private final ObservableList<TaskSeries> taskSeriesCollection = FXCollections.observableArrayList();

    private final StringProperty error = new SimpleStringProperty();

    public IndomainTypeWrapper getIndomain() {
        return indomain.get();
    }

    public ObjectProperty<IndomainTypeWrapper> indomainProperty() {
        return indomain;
    }

    public void setIndomain(IndomainTypeWrapper indomain) {
        this.indomain.set(indomain);
    }

    public SelectChoicePointTypeWrapper getSelectChoicePoint() {
        return selectChoicePoint.get();
    }

    public ObjectProperty<SelectChoicePointTypeWrapper> selectChoicePointProperty() {
        return selectChoicePoint;
    }

    public void setSelectChoicePoint(SelectChoicePointTypeWrapper selectChoicePoint) {
        this.selectChoicePoint.set(selectChoicePoint);
    }

    public ComparatorVariableTypeWrapper getComparatorVariable() {
        return comparatorVariable.get();
    }

    public ObjectProperty<ComparatorVariableTypeWrapper> comparatorVariableProperty() {
        return comparatorVariable;
    }

    public void setComparatorVariable(ComparatorVariableTypeWrapper comparatorVariable) {
        this.comparatorVariable.set(comparatorVariable);
    }

    public boolean getComparatorVariableVisible() {
        return comparatorVariableVisible.get();
    }

    public BooleanProperty comparatorVariableVisibleProperty() {
        return comparatorVariableVisible;
    }

    public void setComparatorVariableVisible(boolean comparatorVariableVisible) {
        this.comparatorVariableVisible.set(comparatorVariableVisible);
    }

    public String getJobShopData() {
        return jobShopData.get();
    }

    public StringProperty jobShopDataProperty() {
        return jobShopData;
    }

    public void setJobShopData(String jobShopData) {
        this.jobShopData.set(jobShopData);
    }

    public int getCost() {
        return cost.get();
    }

    public IntegerProperty costProperty() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost.set(cost);
    }

    public int getBacktracks() {
        return backtracks.get();
    }

    public IntegerProperty backtracksProperty() {
        return backtracks;
    }

    public void setBacktracks(int backtracks) {
        this.backtracks.set(backtracks);
    }

    public int getDecisions() {
        return decisions.get();
    }

    public IntegerProperty decisionsProperty() {
        return decisions;
    }

    public void setDecisions(int decisions) {
        this.decisions.set(decisions);
    }

    public int getMaximumDepth() {
        return maximumDepth.get();
    }

    public IntegerProperty maximumDepthProperty() {
        return maximumDepth;
    }

    public void setMaximumDepth(int maximumDepth) {
        this.maximumDepth.set(maximumDepth);
    }

    public int getNodes() {
        return nodes.get();
    }

    public IntegerProperty nodesProperty() {
        return nodes;
    }

    public void setNodes(int nodes) {
        this.nodes.set(nodes);
    }

    public int getWrongDecisions() {
        return wrongDecisions.get();
    }

    public IntegerProperty wrongDecisionsProperty() {
        return wrongDecisions;
    }

    public void setWrongDecisions(int wrongDecisions) {
        this.wrongDecisions.set(wrongDecisions);
    }

    public long getTime() {
        return time.get();
    }

    public LongProperty timeProperty() {
        return time;
    }

    public void setTime(long time) {
        this.time.set(time);
    }

    public String getJobShopResult() {
        return jobShopResult.get();
    }

    public StringProperty jobShopResultProperty() {
        return jobShopResult;
    }

    public void setJobShopResult(String jobShopResult) {
        this.jobShopResult.set(jobShopResult);
    }

    public ObservableList<TaskSeries> getTaskSeriesCollection() {
        return taskSeriesCollection;
    }

    public String getError() {
        return error.get();
    }

    public StringProperty errorProperty() {
        return error;
    }

    public void setError(String error) {
        this.error.set(error);
    }
}
