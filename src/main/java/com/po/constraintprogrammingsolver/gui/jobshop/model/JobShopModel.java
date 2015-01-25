package com.po.constraintprogrammingsolver.gui.jobshop.model;

import com.po.constraintprogrammingsolver.gui.jobshop.util.data.JobShopTestData;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.ComparatorVariableTypeWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.IndomainTypeWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.ParameterWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.SelectChoicePointTypeWrapper;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import org.jfree.data.gantt.TaskSeries;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Aleksander on 2015-01-23.
 */
public class JobShopModel {
    private final ObjectProperty<IndomainTypeWrapper> indomain = new SimpleObjectProperty<>();
    private final ObjectProperty<SelectChoicePointTypeWrapper> selectChoicePoint = new SimpleObjectProperty<>();
    private final ObjectProperty<ComparatorVariableTypeWrapper> comparatorVariable = new SimpleObjectProperty<>();
    private final BooleanProperty comparatorVariableVisible = new SimpleBooleanProperty();
    private final StringProperty repetitions = new SimpleStringProperty();
    private final StringProperty jobShopData = new SimpleStringProperty();

    private final ObjectProperty<JobShopTestData> jobShopTestData = new SimpleObjectProperty<>();

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

    private final Map<ParameterWrapper, ObjectProperty<ObservableList<XYChart.Series<String, Number>>>> lineChartDataMap = new EnumMap<>(ParameterWrapper.class);

    private final BooleanProperty solutionExpanded = new SimpleBooleanProperty();
    private final BooleanProperty benchmarkExpanded = new SimpleBooleanProperty();

    public JobShopModel() {
        Arrays.stream(ParameterWrapper.values())
                .forEach(wrapper -> lineChartDataMap.put(wrapper, new SimpleObjectProperty<>(FXCollections.observableArrayList(new XYChart.Series<>()))));
    }

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

    public String getRepetitions() {
        return repetitions.get();
    }

    public StringProperty repetitionsProperty() {
        return repetitions;
    }

    public void setRepetitions(String repetitions) {
        this.repetitions.set(repetitions);
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

    public JobShopTestData getJobShopTestData() {
        return jobShopTestData.get();
    }

    public ObjectProperty<JobShopTestData> jobShopTestDataProperty() {
        return jobShopTestData;
    }

    public void setJobShopTestData(JobShopTestData jobShopTestData) {
        this.jobShopTestData.set(jobShopTestData);
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

    public Map<ParameterWrapper, ObjectProperty<ObservableList<XYChart.Series<String, Number>>>> getLineChartDataMap() {
        return lineChartDataMap;
    }

    public boolean getSolutionExpanded() {
        return solutionExpanded.get();
    }

    public BooleanProperty solutionExpandedProperty() {
        return solutionExpanded;
    }

    public void setSolutionExpanded(boolean solutionExpanded) {
        this.solutionExpanded.set(solutionExpanded);
    }

    public boolean getBenchmarkExpanded() {
        return benchmarkExpanded.get();
    }

    public BooleanProperty benchmarkExpandedProperty() {
        return benchmarkExpanded;
    }

    public void setBenchmarkExpanded(boolean benchmarkExpanded) {
        this.benchmarkExpanded.set(benchmarkExpanded);
    }
}
