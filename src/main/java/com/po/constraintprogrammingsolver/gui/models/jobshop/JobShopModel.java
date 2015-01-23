package com.po.constraintprogrammingsolver.gui.models.jobshop;

import com.po.constraintprogrammingsolver.gui.utils.jobshop.wrappers.ComparatorVariableTypeWrapper;
import com.po.constraintprogrammingsolver.gui.utils.jobshop.wrappers.IndomainTypeWrapper;
import com.po.constraintprogrammingsolver.gui.utils.jobshop.wrappers.SelectChoicePointTypeWrapper;
import javafx.beans.property.*;

/**
 * Created by Aleksander on 2015-01-23.
 */
public class JobShopModel {
    private final ObjectProperty<IndomainTypeWrapper> indomain = new SimpleObjectProperty<>();
    private final ObjectProperty<SelectChoicePointTypeWrapper> selectChoicePoint = new SimpleObjectProperty<>();
    private final ObjectProperty<ComparatorVariableTypeWrapper> comparatorVariable = new SimpleObjectProperty<>();

    private final StringProperty jobShopData = new SimpleStringProperty();

    private final IntegerProperty cost = new SimpleIntegerProperty();
    private final IntegerProperty backtracks = new SimpleIntegerProperty();
    private final IntegerProperty decisions = new SimpleIntegerProperty();
    private final IntegerProperty maximumDepth = new SimpleIntegerProperty();
    private final IntegerProperty nodes = new SimpleIntegerProperty();
    private final IntegerProperty wrongDecisions = new SimpleIntegerProperty();
    private final LongProperty time = new SimpleLongProperty();

    private final StringProperty jobSHopResult = new SimpleStringProperty();

    private final StringProperty error = new SimpleStringProperty();
    private final StringProperty updateMessage = new SimpleStringProperty();
    private final DoubleProperty progress = new SimpleDoubleProperty();


}
