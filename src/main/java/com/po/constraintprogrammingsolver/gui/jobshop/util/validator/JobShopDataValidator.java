package com.po.constraintprogrammingsolver.gui.jobshop.util.validator;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.util.converter.JobShopModelToDataConverter;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopDataValidator implements JobShopValidator {
    private static final String ERROR_EMPTY = "label.jobshop.error.empty";
    private static final String ERROR_LINE = "label.jobshop.error.line";
    private static final String ERROR_REPEATED = "label.jobshop.error.repeated.machine";
    private static final String ERROR_MACHINES = "label.jobshop.error.more.machines";
    private static final String ERROR_TIMES = "label.jobshop.error.more.times";

    private static final String VALIDATION_REGEX = "[0-9]+(;[1-9][0-9]*( [1-9][0-9]*)*){2}";

    private final JobShopModel model;
    private final ResourceBundle resources;

    public JobShopDataValidator(JobShopModel model, ResourceBundle resources) {
        this.model = model;
        this.resources = resources;
    }

    @Override
    public boolean validate() {
        if (Strings.isNullOrEmpty(model.getJobShopData())) {
            valueUpdate(model::setError, resources.getString(ERROR_EMPTY));
            return false;
        }

        Scanner scanner = new Scanner(model.getJobShopData());
        for (int jobNumber = 1; scanner.hasNextLine(); jobNumber++) {
            String line = scanner.nextLine();

            if (!line.matches(VALIDATION_REGEX)) {
                valueUpdate(model::setError, MessageFormat.format(resources.getString(ERROR_LINE), jobNumber));
                return false;
            }

            List<String> lines = Splitter.on(JobShopModelToDataConverter.PART_SEPARATOR).splitToList(line);

            List<Integer> machines = Splitter.on(JobShopModelToDataConverter.NUMBER_SEPARATOR).splitToList(lines.get(1)).stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            long machinesNumber = machines.size();

            if (machinesNumber != new HashSet<>(machines).size()) {
                valueUpdate(model::setError, MessageFormat.format(resources.getString(ERROR_REPEATED), jobNumber));
                return false;
            }

            long timesNumber = Splitter.on(JobShopModelToDataConverter.NUMBER_SEPARATOR).splitToList(lines.get(2)).size();

            if (machinesNumber > timesNumber) {
                valueUpdate(model::setError, MessageFormat.format(resources.getString(ERROR_MACHINES), jobNumber));
                return false;
            }
            if (timesNumber > machinesNumber) {
                valueUpdate(model::setError, MessageFormat.format(resources.getString(ERROR_TIMES), jobNumber));
                return false;
            }
        }

        return true;
    }
}
