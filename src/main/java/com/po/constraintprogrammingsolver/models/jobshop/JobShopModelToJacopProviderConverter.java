package com.po.constraintprogrammingsolver.models.jobshop;

import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProvider;
import com.po.constraintprogrammingsolver.problems.strategy.JacopStrategyProviders;
import com.po.constraintprogrammingsolver.problems.strategy.comparatorvariable.ComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.strategy.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.strategy.selectchoicepoint.SelectChoicePointStoreType;

import java.util.function.Function;

/**
 * Created by Aleksander on 2015-01-21.
 */
public class JobShopModelToJacopProviderConverter implements Function<JobShopModel, JacopStrategyProvider> {
    @Override
    public JacopStrategyProvider apply(JobShopModel model) {
        JacopStrategyProvider jacopStrategyProvider;
        IndomainType indomainType = model.getIndomainTypeWrapper().getIndomainType();
        if (model.getSelectChoicePointTypeWrapper().isStore()) {
            SelectChoicePointStoreType selectChoicePointType = model.getSelectChoicePointTypeWrapper().getSelectChoicePointStoreType().get();
            jacopStrategyProvider = JacopStrategyProviders.simpleJacopStrategyProvider(indomainType, selectChoicePointType);
        } else {
            ComparatorVariableType comparatorVariableType = model.getComparatorVariableTypeWrapper().getComparatorVariableType();
            SelectChoicePointComparatorVariableType selectChoicePointComparatorVariableType = model.getSelectChoicePointTypeWrapper().getSelectChoicePointComparatorVariableType().get();
            jacopStrategyProvider = JacopStrategyProviders.comparatorVariableJacopStrategyProvider(indomainType, comparatorVariableType, selectChoicePointComparatorVariableType);
        }

        return jacopStrategyProvider;
    }
}
