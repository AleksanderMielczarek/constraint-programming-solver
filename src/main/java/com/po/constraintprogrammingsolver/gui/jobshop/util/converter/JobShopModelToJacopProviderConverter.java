package com.po.constraintprogrammingsolver.gui.jobshop.util.converter;

import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProviders;
import com.po.constraintprogrammingsolver.problems.strategy.comparatorvariable.ComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreType;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-21
 */
public class JobShopModelToJacopProviderConverter {
    private final JobShopModel model;

    public JobShopModelToJacopProviderConverter(JobShopModel model) {
        this.model = model;
    }

    public JacopStrategyProvider convert() {
        JacopStrategyProvider jacopStrategyProvider;
        IndomainType indomainType = model.getIndomain().getIndomainType();
        if (model.getSelectChoicePoint().isStore()) {
            SelectChoicePointStoreType selectChoicePointType = model.getSelectChoicePoint().getSelectChoicePointStoreType().get();
            jacopStrategyProvider = JacopStrategyProviders.simpleJacopStrategyProvider(indomainType, selectChoicePointType);
        } else {
            ComparatorVariableType comparatorVariableType = model.getComparatorVariable().getComparatorVariableType();
            SelectChoicePointComparatorVariableType selectChoicePointComparatorVariableType = model.getSelectChoicePoint().getSelectChoicePointComparatorVariableType().get();
            jacopStrategyProvider = JacopStrategyProviders.comparatorVariableJacopStrategyProvider(indomainType, comparatorVariableType, selectChoicePointComparatorVariableType);
        }

        return jacopStrategyProvider;
    }
}
