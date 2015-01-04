package com.po.constraintprogrammingsolver.controllers;

import com.po.constraintprogrammingsolver.models.ProblemService;
import com.po.constraintprogrammingsolver.models.jobshop.*;
import com.po.constraintprogrammingsolver.models.jobshop.wrappers.ComparatorVariableTypeWrapper;
import com.po.constraintprogrammingsolver.models.jobshop.wrappers.CostFunctionTypeWrapper;
import com.po.constraintprogrammingsolver.models.jobshop.wrappers.IndomainTypeWrapper;
import com.po.constraintprogrammingsolver.models.jobshop.wrappers.SelectChoicePointTypeWrapper;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopData;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopProblemSolver;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopSolution;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.TaskSeriesCollection;

import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2014-12-19.
 */
public class JobShopProblemController implements ProblemController<JobShopModel, JobShopData, JobShopSolution, JobShopResult> {
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
    private static final IndomainTypeWrapper DEFAULT_INDOMAIN = IndomainTypeWrapper.INDOMAIN_MIN;
    private static final SelectChoicePointTypeWrapper DEFAULT_SELECT_CHOICE_POINT = SelectChoicePointTypeWrapper.INPUT_ORDER_SELECT;
    private static final ComparatorVariableTypeWrapper DEFAULT_COMPARATOR_VARIABLE = ComparatorVariableTypeWrapper.SMALLEST_MIN;
    private static final CostFunctionTypeWrapper DEFAULT_COST_FUNCTION = CostFunctionTypeWrapper.NO_COST_FUNCTION;
    
    @FXML
    private BorderPane borderPane;
    @FXML
    private ComboBox<CostFunctionTypeWrapper> costFunctionType;
    @FXML
    private ComboBox<IndomainTypeWrapper> indomainType;
    @FXML
    private ComboBox<SelectChoicePointTypeWrapper> selectChoicePointType;
    @FXML
    private ComboBox<ComparatorVariableTypeWrapper> comparatorVariableType;
    @FXML
    private TextArea jobs;
    @FXML
    private Label comparatorVariableLabel;

    @FXML
    private ResourceBundle resources;

    private ProblemService<JobShopModel, JobShopData, JobShopSolution, JobShopResult> problemService;
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
        //models
        model = new JobShopModel();
        modelToDataConverter = new JobShopModelToDataConverter();
        problemSolver = new JobShopProblemSolver();
        solutionToResultConverter = new JobShopSolutionToResultConverter(resources);
        validator = new JobShopValidator(resources);
        defaultResultSupplier = new JobShopDefaultResultSupplier();
        problemService = new ProblemService<>(model, modelToDataConverter, problemSolver, solutionToResultConverter, validator, defaultResultSupplier, resources);

        //hide comparator variable combo
        selectChoicePointType.valueProperty().addListener((observable, oldValue, newValue) -> {
            comparatorVariableType.visibleProperty().set(newValue.isComparatorVariable());
            comparatorVariableLabel.visibleProperty().set(newValue.isComparatorVariable());
        });

        //combo box and default values
        costFunctionType.setItems(FXCollections.observableList(Arrays.asList(CostFunctionTypeWrapper.values())));
        costFunctionType.setConverter(CostFunctionTypeWrapper.getStringConverter(resources));
        costFunctionType.valueProperty().set(DEFAULT_COST_FUNCTION);
        
        indomainType.setItems(FXCollections.observableList(Arrays.asList(IndomainTypeWrapper.values())));
        indomainType.setConverter(IndomainTypeWrapper.getStringConverter(resources));
        indomainType.valueProperty().set(DEFAULT_INDOMAIN);

        selectChoicePointType.setItems(FXCollections.observableList(Arrays.asList(SelectChoicePointTypeWrapper.values())));
        selectChoicePointType.setConverter(SelectChoicePointTypeWrapper.getStringConverter(resources));
        selectChoicePointType.valueProperty().set(DEFAULT_SELECT_CHOICE_POINT);

        comparatorVariableType.setItems(FXCollections.observableList(Arrays.asList(ComparatorVariableTypeWrapper.values())));
        comparatorVariableType.setConverter(ComparatorVariableTypeWrapper.getStringConverter(resources));
        comparatorVariableType.valueProperty().set(DEFAULT_COMPARATOR_VARIABLE);

        jobs.textProperty().set(DEFAULT_JOBS);

        //chart
        ObjectProperty<TaskSeriesCollection> datasetProperty = new SimpleObjectProperty<>();
        JobShopResult result = defaultResultSupplier.get();
        ChartViewer chartViewer = new ChartViewer(createChart(result.getTaskSeriesCollection()));
        borderPane.setCenter(chartViewer);

        //change chart
        datasetProperty.addListener((observable, oldValue, newValue) -> {
            result.getTaskSeriesCollection().removeAll();
            int series = newValue.getSeriesCount();
            for (int i = 0; i < series; i++) {
                result.getTaskSeriesCollection().add(newValue.getSeries(i));
            }
        });

        //bind model
        model.costFunctionTypeWrapperProperty().bind(costFunctionType.valueProperty());
        model.indomainTypeWrapperProperty().bind(indomainType.valueProperty());
        model.selectChoicePointTypeWrapperProperty().bind(selectChoicePointType.valueProperty());
        model.comparatorVariableTypeWrapperProperty().bind(comparatorVariableType.valueProperty());
        model.jobsProperty().bind(jobs.textProperty());

        //start service listeners
        problemService.setOnSucceeded(event -> {
            datasetProperty.bind(problemService.valueProperty().get().getResult().taskSeriesCollectionProperty());
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
    public ProblemService<JobShopModel, JobShopData, JobShopSolution, JobShopResult> getProblemService() {
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
}
