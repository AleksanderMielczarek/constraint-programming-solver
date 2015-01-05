package com.po.constraintprogrammingsolver.models.jobshop;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.po.constraintprogrammingsolver.models.ValidatorResult;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopValidator implements Function<JobShopModel, ValidatorResult> {
    private static final String ERROR_EMPTY = "label.jobshop.error.empty";
    private static final String ERROR_LINE = "label.jobshop.error.line";
    private static final String ERROR_MACHINES = "label.jobshop.error.more.machines";
    private static final String ERROR_TIMES = "label.jobshop.error.more.times";

    private static final String VALIDATION_REGEX = "[0-9]+(;[1-9][0-9]*( [1-9][0-9]*)*){2}";

    private final ResourceBundle resources;

    public JobShopValidator(ResourceBundle resources) {
        this.resources = resources;
    }

    @Override
    public ValidatorResult apply(JobShopModel model) {
        if (Strings.isNullOrEmpty(model.getJobs())) {
            return new ValidatorResult(true, resources.getString(ERROR_EMPTY));
        }

        Scanner scanner = new Scanner(model.getJobs());
        for (int jobNumber = 1; scanner.hasNextLine(); jobNumber++) {
            String line = scanner.nextLine();

            if (!line.matches(VALIDATION_REGEX)) {
                return new ValidatorResult(true, MessageFormat.format(resources.getString(ERROR_LINE), jobNumber));
            }

            List<String> lines = Splitter.on(JobShopModelToDataConverter.PART_SEPARATOR).splitToList(line);
            long machines = Splitter.on(JobShopModelToDataConverter.NUMBER_SEPARATOR).splitToList(lines.get(1)).size();
            long times = Splitter.on(JobShopModelToDataConverter.NUMBER_SEPARATOR).splitToList(lines.get(2)).size();

            if (machines > times) {
                return new ValidatorResult(true, MessageFormat.format(resources.getString(ERROR_MACHINES), jobNumber));
            } else if (times > machines) {
                return new ValidatorResult(true, MessageFormat.format(resources.getString(ERROR_TIMES), jobNumber));
            }
        }

        return new ValidatorResult(false, StringUtils.EMPTY);
    }
}
