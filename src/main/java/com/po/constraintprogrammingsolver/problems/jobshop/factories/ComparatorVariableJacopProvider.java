package com.po.constraintprogrammingsolver.problems.jobshop.factories;


import com.po.constraintprogrammingsolver.problems.jobshop.factories.comparatorvariable.ComparatorVariableFactory;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.comparatorvariable.ComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.indomain.IndomainType;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.selectchoicepoint.SelectChoicePointComparatorVariableFactory;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.selectchoicepoint.SelectChoicePointComparatorVariableType;
import org.jacop.core.IntVar;
import org.jacop.core.Store;
import org.jacop.search.ComparatorVariable;
import org.jacop.search.Indomain;
import org.jacop.search.SelectChoicePoint;

/**
 * Created by Aleksander on 2015-01-04.
 */
public class ComparatorVariableJacopProvider extends AbstractJacopProvider {
    private final SelectChoicePointComparatorVariableType selectChoicePointType;
    private final ComparatorVariable<IntVar> comparatorVariable;

    public ComparatorVariableJacopProvider(IndomainType indomainType, ComparatorVariableType comparatorVariableType, SelectChoicePointComparatorVariableType selectChoicePointType) {
        super(indomainType);

        this.selectChoicePointType = selectChoicePointType;

        ComparatorVariableFactory comparatorVariableFactory = new ComparatorVariableFactory();
        comparatorVariable = comparatorVariableFactory.createJacopType(comparatorVariableType);
    }

    @Override
    public SelectChoicePoint<IntVar> getSelectChoicePoint(IntVar[] variables, Store store, Indomain<IntVar> indomain) {
        SelectChoicePointComparatorVariableFactory selectChoicePointFactory = new SelectChoicePointComparatorVariableFactory(variables, indomain, comparatorVariable);
        return selectChoicePointFactory.createJacopType(selectChoicePointType);
    }
}
