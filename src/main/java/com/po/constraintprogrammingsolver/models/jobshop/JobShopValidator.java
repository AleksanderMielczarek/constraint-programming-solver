package com.po.constraintprogrammingsolver.models.jobshop;

import com.google.common.base.Strings;
import com.po.constraintprogrammingsolver.models.ValidatorResult;
import org.apache.commons.lang3.StringUtils;

import java.util.ResourceBundle;
import java.util.function.Function;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopValidator implements Function<JobShopModel, ValidatorResult> {
    private static final String ERROR_EMPTY = "label.jobshop.error.empty";

    private final ResourceBundle resources;

    public JobShopValidator(ResourceBundle resources) {
        this.resources = resources;
    }

    @Override
    public ValidatorResult apply(JobShopModel model) {
        if (Strings.isNullOrEmpty(model.getJobs())) {
            return new ValidatorResult(true, resources.getString(ERROR_EMPTY));
        }

        return new ValidatorResult(false, StringUtils.EMPTY);
    }
}
