package com.po.constraintprogrammingsolver.problems.jobshop.factories;


import com.po.constraintprogrammingsolver.problems.jobshop.factories.comparatorvariable.ComparatorVariableFactory;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.comparatorvariable.ComparatorVariableType;
import com.po.constraintprogrammingsolver.problems.jobshop.factories.indomain.IndomainFactory;
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
public class ComparatorVariableJacopProvider implements JacopProvider {
    private final IndomainType indomainType;
    private final ComparatorVariableType comparatorVariableType;
    private final SelectChoicePointComparatorVariableType selectChoicePointType;

    private final IndomainFactory indomainFactory;
    private final ComparatorVariableFactory comparatorVariableFactory;

    private final Indomain<IntVar> indomain;
    private final ComparatorVariable<IntVar> comparatorVariable;

    public ComparatorVariableJacopProvider(IndomainType indomainType, ComparatorVariableType comparatorVariableType, SelectChoicePointComparatorVariableType selectChoicePointType) {
        this.indomainType = indomainType;
        this.comparatorVariableType = comparatorVariableType;
        this.selectChoicePointType = selectChoicePointType;

        indomainFactory = new IndomainFactory();
        indomain = indomainFactory.createJacopType(indomainType);

        comparatorVariableFactory = new ComparatorVariableFactory();
        comparatorVariable = comparatorVariableFactory.createJacopType(comparatorVariableType);
    }

    @Override
    public Indomain<IntVar> getIndomain() {
        return indomain;
    }

    @Override
    public SelectChoicePoint<IntVar> getSelectChoicePoint(IntVar[] variables, Store store, Indomain<IntVar> indomain) {
        SelectChoicePointComparatorVariableFactory selectChoicePointFactory = new SelectChoicePointComparatorVariableFactory(variables, indomain, comparatorVariable);
        return selectChoicePointFactory.createJacopType(selectChoicePointType);
    }
}
