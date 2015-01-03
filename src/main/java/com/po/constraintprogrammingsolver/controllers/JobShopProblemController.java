package com.po.constraintprogrammingsolver.controllers;

import com.google.common.collect.Multimap;
import com.po.constraintprogrammingsolver.models.ProblemService;
import com.po.constraintprogrammingsolver.models.ValidatorResult;
import com.po.constraintprogrammingsolver.models.jobshop.*;
import com.po.constraintprogrammingsolver.problems.ProblemSolver;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopData;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopProblemSolver;
import com.po.constraintprogrammingsolver.problems.jobshop.TaskIntVarWrapper;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.TaskSeriesCollection;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Aleksander on 2014-12-19.
 */
public class JobShopProblemController implements ProblemController<JobShopModel, JobShopData, Multimap<Integer, TaskIntVarWrapper>, TaskSeriesCollection> {
    private static final String CHART_TITLE = "chart.title";
    private static final String CHART_AXIS_X = "chart.axis.x";
    private static final String CHART_AXIS_Y = "chart.axis.y";

    private static final String DEFAULT_JOBS = new StringBuilder("10;2 1 3 4;10 5 15 5")
            .append(System.getProperty("line.separator"))
            .append("5;3 2 1 4;10 5 5 10")
            .append(System.getProperty("line.separator"))
            .append("0;1 3 4 2;5 5 5 5")
            .append(System.getProperty("line.separator"))
            .append("5;3 2 4;5 10 15")
            .toString();
    private static final IndomainType DEFAULT_INDOMAIN = IndomainType.INDOMAIN_MIN;

    @FXML
    private BorderPane borderPane;
    @FXML
    private ComboBox<IndomainType> indomainType;
    @FXML
    private TextArea jobs;

    @FXML
    private ResourceBundle resources;

    private ObjectProperty<TaskSeriesCollection> datasetProperty;

    private ProblemService<JobShopModel, JobShopData, Multimap<Integer, TaskIntVarWrapper>, TaskSeriesCollection> problemService;
    private JobShopModel model;
    private JobShopModelToDataConverter modelToDataConverter;
    private JobShopProblemSolver problemSolver;
    private JobShopSolutionToResultConverter solutionToResultConverter;
    private JobShopValidator validator;
    private JobShopDefaultResultSupplier defaultResultSupplier;

    private StringProperty timeProperty;
    private StringProperty errorProperty;
    private DoubleProperty progressProperty;

    @FXML
    public void initialize() {
        //combo box and default values
        indomainType.setItems(FXCollections.observableList(Arrays.asList(IndomainType.values())));
        indomainType.setConverter(IndomainType.getStringConverter(resources));
        indomainType.valueProperty().set(DEFAULT_INDOMAIN);

        jobs.textProperty().set(DEFAULT_JOBS);

        //chart
        datasetProperty = new SimpleObjectProperty<>();
        TaskSeriesCollection dataset = new TaskSeriesCollection();
        ChartViewer chartViewer = new ChartViewer(createChart(dataset));
        borderPane.setCenter(chartViewer);

        //change chart
        datasetProperty.addListener((observable, oldValue, newValue) -> {
            dataset.removeAll();
            int series = newValue.getSeriesCount();
            for (int i = 0; i < series; i++) {
                dataset.add(newValue.getSeries(i));
            }
        });

        //models
        model = new JobShopModel();
        modelToDataConverter = new JobShopModelToDataConverter();
        problemSolver = new JobShopProblemSolver();
        solutionToResultConverter = new JobShopSolutionToResultConverter(resources);
        validator = new JobShopValidator(resources);
        defaultResultSupplier = new JobShopDefaultResultSupplier();
        problemService = new ProblemService<>(model, modelToDataConverter, problemSolver, solutionToResultConverter, validator, defaultResultSupplier, resources);

        //bind model
        model.indomainTypeProperty().bind(indomainType.valueProperty());
        model.jobsProperty().bind(jobs.textProperty());

        //start service listeners
        problemService.setOnSucceeded(event -> {
            datasetProperty.bind(problemService.valueProperty().get().solutionProperty());
            timeProperty.bind(problemService.valueProperty().get().timeProperty());
            errorProperty.bind(problemService.valueProperty().get().errorProperty());
        });
        problemService.setOnRunning(event -> progressProperty.bind(problemService.progressProperty()));
    }

    private JFreeChart createChart(IntervalCategoryDataset dataset) {
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
    public ProblemService<JobShopModel, JobShopData, Multimap<Integer, TaskIntVarWrapper>, TaskSeriesCollection> getProblemService() {
        return problemService;
    }

    @Override
    public void setTimeProperty(StringProperty timeProperty) {
        this.timeProperty = timeProperty;
    }

    @Override
    public void setErrorProperty(StringProperty errorProperty) {
        this.errorProperty = errorProperty;
    }

    @Override
    public void setProgressProperty(DoubleProperty progressProperty) {
        this.progressProperty = progressProperty;
    }

    @Override
    public JobShopModel getModel() {
        return model;
    }

    @Override
    public Function<JobShopModel, JobShopData> getModelToDataConverter() {
        return modelToDataConverter;
    }

    @Override
    public ProblemSolver<JobShopData, Multimap<Integer, TaskIntVarWrapper>> getProblemSolver() {
        return problemSolver;
    }

    @Override
    public Function<Multimap<Integer, TaskIntVarWrapper>, TaskSeriesCollection> getSolutionToResultConverter() {
        return solutionToResultConverter;
    }

    @Override
    public Function<JobShopModel, ValidatorResult> getValidator() {
        return validator;
    }

    @Override
    public Supplier<TaskSeriesCollection> getDefaultResultSupplier() {
        return defaultResultSupplier;
    }
}
