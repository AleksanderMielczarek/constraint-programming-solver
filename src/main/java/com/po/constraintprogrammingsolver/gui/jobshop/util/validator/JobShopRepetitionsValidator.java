package com.po.constraintprogrammingsolver.gui.jobshop.util.validator;

import com.google.common.base.Strings;
import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;

import java.text.MessageFormat;
import java.time.temporal.ValueRange;
import java.util.ResourceBundle;

/**
 * @author Aleksander Mielczarek
 * @since 2015-01-24
 */
public class JobShopRepetitionsValidator extends AbstractJobShopValidatorDecorator {
    private static final String ERROR_REPETITIONS_EMPTY = "label.error.repetitions.empty";
    private static final String ERROR_NOT_NUMBER = "label.error.not.number";
    private static final String ERROR_NOT_IN_RANGE = "label.error.not.in.range";

    private static final String VALIDATION_REGEX = "-?\\d+";
    private static final int LOWER = 1;
    private static final int UPPER = 1000;
    private static final ValueRange VALUE_RANGE = ValueRange.of(LOWER, UPPER);

    private final JobShopModel model;
    private final ResourceBundle resources;

    public JobShopRepetitionsValidator(JobShopValidator validator, JobShopModel model, ResourceBundle resources) {
        super(validator);
        this.model = model;
        this.resources = resources;
    }

    @Override
    public boolean validate() {
        if (Strings.isNullOrEmpty(model.getRepetitions())) {
            valueUpdate(model::setError, resources.getString(ERROR_REPETITIONS_EMPTY));
            return false;
        }

        if (!model.getRepetitions().matches(VALIDATION_REGEX)) {
            valueUpdate(model::setError, resources.getString(ERROR_NOT_NUMBER));
            return false;
        }

        if (!VALUE_RANGE.isValidValue(Integer.parseInt(model.getRepetitions()))) {
            valueUpdate(model::setError, MessageFormat.format(resources.getString(ERROR_NOT_IN_RANGE), LOWER, UPPER));
            return false;
        }

        return validator.validate();
    }
}
