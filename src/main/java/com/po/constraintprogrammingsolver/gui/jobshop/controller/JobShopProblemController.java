package com.po.constraintprogrammingsolver.gui.jobshop.controller;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.service.JobShopProblemService;
import com.po.constraintprogrammingsolver.gui.jobshop.util.defaultvalue.DefaultInitValuesSupplier;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.ComparatorVariableTypeWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.IndomainTypeWrapper;
import com.po.constraintprogrammingsolver.gui.jobshop.util.wrappers.SelectChoicePointTypeWrapper;
import com.po.constraintprogrammingsolver.gui.main.ProblemController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.TaskSeriesCollection;

import java.util.Arrays;
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
    private BorderPane borderPane;

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
    private ResourceBundle resources;

    private final JobShopModel model = new JobShopModel();
    private Service<Void> jobShopProblemService;

    private final ObjectProperty<TaskSeriesCollection> taskSeriesCollection = new SimpleObjectProperty<>();

    @FXML
    public void initialize() {
        jobShopProblemService = new JobShopProblemService(model, resources);
        bindModel();

        comboBoxSelectChoicePoint.valueProperty().addListener((observable, oldValue, newValue) -> model.setComparatorVariableVisible(newValue.isComparatorVariable()));

        setValuesInComboBox();
        setDefaultInitValues(model);

        Stream.of(jobShopProblemService).forEach(service -> service.setOnRunning(event -> {
            progressBarService.progressProperty().bind(service.progressProperty());
            labelUpdateMessage.textProperty().bind(service.messageProperty());
        }));


        /*//chart
        TaskSeriesCollection taskSeriesCollection = defaultResultSupplier.get().getTaskSeriesCollection();
        JFreeChart jFreeChart = createChart(taskSeriesCollection);
        ChartViewer chartViewer = new ChartViewer(jFreeChart);
        borderPane.setCenter(chartViewer);

        //extra consumer
        resultConsumer = new JobShopResultConsumer(taskSeriesCollection);

        jobs.textProperty().set(DEFAULT_JOBS);
        cost.textProperty().set(defaultResultSupplier.get().getCost());
        result.textProperty().set(defaultResultSupplier.get().getResult());*/
    }

    private void bindModel() {
        model.indomainProperty().bindBidirectional(comboBoxIndomain.valueProperty());
        model.selectChoicePointProperty().bindBidirectional(comboBoxSelectChoicePoint.valueProperty());
        model.comparatorVariableProperty().bindBidirectional(comboBoxComparatorVariable.valueProperty());

        comboBoxComparatorVariable.visibleProperty().bind(model.comparatorVariableVisibleProperty());
        labelComparatorVariable.visibleProperty().bind(model.comparatorVariableVisibleProperty());

        model.jobShopDataProperty().bindBidirectional(textAreaJobShopData.textProperty());

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
    }

    private void setValuesInComboBox() {
        comboBoxIndomain.setItems(FXCollections.observableList(Arrays.asList(IndomainTypeWrapper.values())));
        comboBoxIndomain.setConverter(IndomainTypeWrapper.getStringConverter(resources));

        comboBoxSelectChoicePoint.setItems(FXCollections.observableList(Arrays.asList(SelectChoicePointTypeWrapper.values())));
        comboBoxSelectChoicePoint.setConverter(SelectChoicePointTypeWrapper.getStringConverter(resources));

        comboBoxComparatorVariable.setItems(FXCollections.observableList(Arrays.asList(ComparatorVariableTypeWrapper.values())));
        comboBoxComparatorVariable.setConverter(ComparatorVariableTypeWrapper.getStringConverter(resources));
    }

    private static void setDefaultInitValues(JobShopModel model) {
        DefaultInitValuesSupplier initValuesSupplier = new DefaultInitValuesSupplier(model);
        initValuesSupplier.supplyDefaultValues();
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
