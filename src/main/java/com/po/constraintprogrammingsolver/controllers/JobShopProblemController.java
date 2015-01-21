package com.po.constraintprogrammingsolver.controllers;

import com.po.constraintprogrammingsolver.models.ProblemService;
import com.po.constraintprogrammingsolver.models.jobshop.*;
import com.po.constraintprogrammingsolver.models.jobshop.wrappers.ComparatorVariableTypeWrapper;
import com.po.constraintprogrammingsolver.models.jobshop.wrappers.IndomainTypeWrapper;
import com.po.constraintprogrammingsolver.models.jobshop.wrappers.SelectChoicePointTypeWrapper;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopData;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopProblemSolver;
import com.po.constraintprogrammingsolver.problems.jobshop.JobShopSolution;
import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
public class JobShopProblemController implements ProblemController {
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

    @FXML
    private BorderPane borderPane;
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
    private TextArea result;
    @FXML
    private TextField cost;
    @FXML
    private ProgressBar computationProgressBar;
    @FXML
    private Label timeLabel;
    @FXML
    private Label errorLabel;

    @FXML
    private ResourceBundle resources;

    private ProblemService<JobShopModel, JobShopData, JobShopSolution, JobShopResult> problemService;
    private JobShopModel model;
    private JobShopModelToDataConverter modelToDataConverter;
    private JobShopModelToJacopProviderConverter modelToJacopProviderConverter;
    private JobShopProblemSolver problemSolver;
    private JobShopSolutionToResultConverter solutionToResultConverter;
    private JobShopValidator validator;
    private JobShopDefaultResultSupplier defaultResultSupplier;
    private JobShopResultConsumer resultConsumer;

    @FXML
    public void initialize() {
        //models
        model = new JobShopModel();
        modelToDataConverter = new JobShopModelToDataConverter();
        modelToJacopProviderConverter = new JobShopModelToJacopProviderConverter();
        problemSolver = new JobShopProblemSolver();
        solutionToResultConverter = new JobShopSolutionToResultConverter(resources);
        validator = new JobShopValidator(resources);
        defaultResultSupplier = new JobShopDefaultResultSupplier();

        //chart
        TaskSeriesCollection taskSeriesCollection = defaultResultSupplier.get().getTaskSeriesCollection();
        JFreeChart jFreeChart = createChart(taskSeriesCollection);
        ChartViewer chartViewer = new ChartViewer(jFreeChart);
        borderPane.setCenter(chartViewer);

        //extra consumer
        resultConsumer = new JobShopResultConsumer(taskSeriesCollection);

        //service
        problemService = new ProblemService<>(model,
                modelToDataConverter,
                modelToJacopProviderConverter,
                problemSolver,
                solutionToResultConverter,
                validator,
                defaultResultSupplier,
                resultConsumer,
                resources);

        //hide comparator variable combo
        selectChoicePointType.valueProperty().addListener((observable, oldValue, newValue) -> {
            comparatorVariableType.visibleProperty().set(newValue.isComparatorVariable());
            comparatorVariableLabel.visibleProperty().set(newValue.isComparatorVariable());
        });

        //combo box and default values
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
        cost.textProperty().set(defaultResultSupplier.get().getCost());
        result.textProperty().set(defaultResultSupplier.get().getResult());

        //bind model
        model.indomainTypeWrapperProperty().bind(indomainType.valueProperty());
        model.selectChoicePointTypeWrapperProperty().bind(selectChoicePointType.valueProperty());
        model.comparatorVariableTypeWrapperProperty().bind(comparatorVariableType.valueProperty());
        model.jobsProperty().bind(jobs.textProperty());

        //start service listeners
        problemService.setOnSucceeded(event -> {
            cost.textProperty().bind(problemService.valueProperty().get().getResult().costProperty());
            result.textProperty().bind(problemService.valueProperty().get().getResult().resultProperty());
            timeLabel.textProperty().bind(problemService.valueProperty().get().timeProperty());
            errorLabel.textProperty().bind(problemService.valueProperty().get().errorProperty());
        });
        problemService.setOnRunning(event -> computationProgressBar.progressProperty().bind(problemService.progressProperty()));
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
    public Service<?> getService() {
        return problemService;
    }

}
