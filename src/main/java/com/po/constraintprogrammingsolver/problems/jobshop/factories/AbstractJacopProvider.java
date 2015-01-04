package com.po.constraintprogrammingsolver.problems.jobshop.factories;

import com.po.constraintprogrammingsolver.problems.jobshop.costfunction.CostFunction;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.costfunction.CostFunctionFactory;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.costfunction.CostFunctionType;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.indomain.IndomainFactory;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.selectchoicepoint.SelectChoicePointComparatorVariableFactory;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.Indomain;
import org.jacop.search.SelectChoicePoint;

import java.util.Optional;

/**
 * Created by Aleksander on 2015-01-04.
 */
public abstract class AbstractJacopProvider implements JacopProvider {
    private final Indomain<IntVar> indomain;
    private final Optional<CostFunction> costFunction;

    public AbstractJacopProvider(IndomainType indomainType, CostFunctionType costFunctionType) {
        IndomainFactory indomainFactory = new IndomainFactory();
        indomain = indomainFactory.createJacopType(indomainType);

        CostFunctionFactory costFunctionFactory = new CostFunctionFactory();
        costFunction = Optional.of(costFunctionFactory.createJacopType(costFunctionType));
    }

    public AbstractJacopProvider(IndomainType indomainType) {
        IndomainFactory indomainFactory = new IndomainFactory();
        indomain = indomainFactory.createJacopType(indomainType);

        costFunction = Optional.empty();
    }

    @Override
    public Indomain<IntVar> getIndomain() {
        return indomain;
    }

    @Override
    public Optional<CostFunction> getCostFunction() {
        return costFunction;
    }
}
