package com.po.constraintprogrammingsolver.gui.jobshop.controller;

import com.po.constraintprogrammingsolver.Context;
import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.service.JobShopBenchmarkService;
import com.po.constraintprogrammingsolver.gui.jobshop.service.JobShopGeneratorService;
import com.po.constraintprogrammingsolver.gui.jobshop.service.JobShopLoaderService;
import com.po.constraintprogrammingsolver.gui.jobshop.service.JobShopProblemService;
import com.po.constraintprogrammingsolver.gui.jobshop.util.data.JobShopTestData;
import com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue.DefaultInitValuesSupplier;
import com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue.DefaultJobShopProblemResultValuesSupplier;
import com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue.DefaultValuesSupplier;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.ComparatorVariableTypeWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.IndomainTypeWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.ParameterWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.SelectChoicePointTypeWrapper;
import com.po.constraintprogrammingsolver.gui.main.ProblemController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * Created by Aleksander on 2014-12-19.
 */
public class JobShopProblemController implements ProblemController {
    private static final String CHART_TITLE = "chart.title";
    private static final String CHART_AXIS_X = "chart.axis.x";
    private static final String CHART_AXIS_Y = "chart.axis.y";

    @FXML
    private Label labelNodes;

    @FXML
    private Label labelWrongDecisions;

    @FXML
    private ComboBox<ComparatorVariableTypeWrapper> comboBoxComparatorVariable;

    @FXML
    private ComboBox<IndomainTypeWrapper> comboBoxIndomain;

    @FXML
    private Label labelBacktracks;

    @FXML
    private TextArea textAreaJobShopResult;

    @FXML
    private Label labelMaximumDepth;

    @FXML
    private Label labelTime;

    @FXML
    private Label labelError;

    @FXML
    private Label labelUpdateMessage;

    @FXML
    private Label labelDecisions;

    @FXML
    private ProgressBar progressBarService;

    @FXML
    private TextArea textAreaJobShopData;

    @FXML
    private Label labelCost;

    @FXML
    private Label labelComparatorVariable;

    @FXML
    private ComboBox<SelectChoicePointTypeWrapper> comboBoxSelectChoicePoint;

    @FXML
    private TextField textFieldRepetitions;

    @FXML
    private BorderPane borderPaneSolution;

    @FXML
    private LineChart<String, Number> lineChartBacktracks;

    @FXML
    private LineChart<String, Number> lineChartDecisions;

    @FXML
    private LineChart<String, Number> lineChartMaximumDepth;

    @FXML
    private LineChart<String, Number> lineChartNodes;

    @FXML
    private LineChart<String, Number> lineChartWrongDecisions;

    @FXML
    private LineChart<String, Number> lineChartTime;

    @FXML
    private TitledPane titledPaneOptions;

    @FXML
    private TitledPane titledPaneSolution;

    @FXML
    private TitledPane titledPaneBenchmark;

    @FXML
    private Accordion accordionMenu;

    @FXML
    private ComboBox<JobShopTestData> comboBoxTestData;
    @FXML
    private ResourceBundle resources;

    private final JobShopModel model = new JobShopModel();
    private Service<Void> jobShopProblemService;
    private Service<Void> jobShopBenchmarkService;
    private Service<Void> jobShopLoaderService;
    private Service<Void> jobShopGeneratorService;

    @FXML
    public void initialize() {
        //create model and services
        jobShopProblemService = new JobShopProblemService(model, resources);
        jobShopProblemService.setExecutor(Context.INSTANCE.getExecutor());
        jobShopBenchmarkService = new JobShopBenchmarkService(model, resources);
        jobShopBenchmarkService.setExecutor(Context.INSTANCE.getExecutor());
        jobShopLoaderService = new JobShopLoaderService(model, resources);
        jobShopGeneratorService = new JobShopGeneratorService(model, resources);
        jobShopGeneratorService.setExecutor(Context.INSTANCE.getExecutor());
        bindModel();

        //chart
        TaskSeriesCollection taskSeriesCollection = new TaskSeriesCollection();
        JFreeChart jFreeChart = createChart(taskSeriesCollection, resources);
        ChartViewer chartViewer = new ChartViewer(jFreeChart);
        borderPaneSolution.setCenter(chartViewer);

        //set listeners
        comboBoxSelectChoicePoint.valueProperty().addListener((observable, oldValue, newValue) -> model.setComparatorVariableVisible(newValue.isComparatorVariable()));

        comboBoxTestData.valueProperty().addListener((observable, oldValue, newValue) -> model.setJobShopData(newValue.getData()));

        Stream.of(jobShopProblemService, jobShopBenchmarkService, jobShopGeneratorService).forEach(service -> service.setOnRunning(event -> {
            progressBarService.progressProperty().bind(service.progressProperty());
            labelUpdateMessage.textProperty().bind(service.messageProperty());
        }));

        model.getTaskSeriesCollection().addListener((ListChangeListener<TaskSeries>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    c.getAddedSubList().forEach(taskSeriesCollection::add);
                } else if (c.wasRemoved()) {
                    c.getRemoved().forEach(taskSeriesCollection::remove);
                }
            }
        });

        //set init values
        accordionMenu.setExpandedPane(titledPaneOptions);
        setValuesInComboBox();
        setDefaultInitValues(model);
    }

    @FXML
    private void onButtonStartBenchmarkClicked() {
        jobShopBenchmarkService.restart();
    }

    @FXML
    private void onButtonLoadFileClicked() {
        jobShopLoaderService.restart();
    }


    @FXML
    private void onButtonGenerateDataClicked() {
        jobShopGeneratorService.restart();
    }

    private void bindModel() {
        model.indomainProperty().bindBidirectional(comboBoxIndomain.valueProperty());
        model.selectChoicePointProperty().bindBidirectional(comboBoxSelectChoicePoint.valueProperty());
        model.comparatorVariableProperty().bindBidirectional(comboBoxComparatorVariable.valueProperty());
        comboBoxComparatorVariable.visibleProperty().bind(model.comparatorVariableVisibleProperty());
        labelComparatorVariable.visibleProperty().bind(model.comparatorVariableVisibleProperty());
        model.repetitionsProperty().bindBidirectional(textFieldRepetitions.textProperty());
        model.jobShopDataProperty().bindBidirectional(textAreaJobShopData.textProperty());

        model.jobShopTestDataProperty().bindBidirectional(comboBoxTestData.valueProperty());

        StringConverter<Number> stringConverter = new NumberStringConverter();
        labelCost.textProperty().bindBidirectional(model.costProperty(), stringConverter);
        labelBacktracks.textProperty().bindBidirectional(model.backtracksProperty(), stringConverter);
        labelDecisions.textProperty().bindBidirectional(model.decisionsProperty(), stringConverter);
        labelMaximumDepth.textProperty().bindBidirectional(model.maximumDepthProperty(), stringConverter);
        labelNodes.textProperty().bindBidirectional(model.nodesProperty(), stringConverter);
        labelWrongDecisions.textProperty().bindBidirectional(model.wrongDecisionsProperty(), stringConverter);
        labelTime.textProperty().bindBidirectional(model.timeProperty(), stringConverter);

        textAreaJobShopResult.textProperty().bind(model.jobShopResultProperty());

        labelError.textProperty().bind(model.errorProperty());

        Map<ParameterWrapper, LineChart<String, Number>> lineChartMap = new EnumMap<>(ParameterWrapper.class);
        lineChartMap.put(ParameterWrapper.BACKTRACKS_WRAPPER, lineChartBacktracks);
        lineChartMap.put(ParameterWrapper.DECISIONS_WRAPPER, lineChartDecisions);
        lineChartMap.put(ParameterWrapper.MAXIMUM_DEPTH_WRAPPER, lineChartMaximumDepth);
        lineChartMap.put(ParameterWrapper.NODES_WRAPPER, lineChartNodes);
        lineChartMap.put(ParameterWrapper.WRONG_DECISIONS_WRAPPER, lineChartWrongDecisions);
        lineChartMap.put(ParameterWrapper.TIME_WRAPPER, lineChartTime);

        lineChartMap.entrySet().stream()
                .forEach(entry -> entry.getValue().dataProperty().bindBidirectional(model.getLineChartDataMap().get(entry.getKey())));

        titledPaneSolution.expandedProperty().bindBidirectional(model.solutionExpandedProperty());
        titledPaneBenchmark.expandedProperty().bindBidirectional(model.benchmarkExpandedProperty());
    }

    private void setValuesInComboBox() {
        comboBoxIndomain.setItems(FXCollections.observableList(Arrays.asList(IndomainTypeWrapper.values())));
        comboBoxIndomain.setConverter(IndomainTypeWrapper.getStringConverter(resources));

        comboBoxSelectChoicePoint.setItems(FXCollections.observableList(Arrays.asList(SelectChoicePointTypeWrapper.values())));
        comboBoxSelectChoicePoint.setConverter(SelectChoicePointTypeWrapper.getStringConverter(resources));

        comboBoxComparatorVariable.setItems(FXCollections.observableList(Arrays.asList(ComparatorVariableTypeWrapper.values())));
        comboBoxComparatorVariable.setConverter(ComparatorVariableTypeWrapper.getStringConverter(resources));

        comboBoxTestData.setItems(FXCollections.observableList(Arrays.asList(JobShopTestData.values())));
        comboBoxTestData.setConverter(JobShopTestData.getStringConverter(resources));
    }

    private static void setDefaultInitValues(JobShopModel model) {
        DefaultValuesSupplier initValuesSupplier = new DefaultInitValuesSupplier(model);
        initValuesSupplier.supplyDefaultValues();

        DefaultValuesSupplier jobShopProblemResultValuesSupplier = new DefaultJobShopProblemResultValuesSupplier(model);
        jobShopProblemResultValuesSupplier.supplyDefaultValues();
    }

    private static JFreeChart createChart(IntervalCategoryDataset dataset, ResourceBundle resources) {
        return ChartFactory.createGanttChart(
                resources.getString(CHART_TITLE),
                resources.getString(CHART_AXIS_Y),
                resources.getString(CHART_AXIS_X),
                dataset,
                true,
                true,
                false
        );
    }

    @Override
    public Service<?> getService() {
        return jobShopProblemService;
    }
}
