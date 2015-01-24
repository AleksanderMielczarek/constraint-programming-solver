package com.po.constraintprogrammingsolver.gui.jobshop.util;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.po.constraintprogrammingsolver.gui.jobshop.model.JobShopModel;
import com.po.constraintprogrammingsolver.gui.jobshop.util.converter.JobShopModelToDataConverter;

import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class JobShopValidator {
    private static final String ERROR_EMPTY = "label.jobshop.error.empty";
    private static final String ERROR_LINE = "label.jobshop.error.line";
    private static final String ERROR_MACHINES = "label.jobshop.error.more.machines";
    private static final String ERROR_TIMES = "label.jobshop.error.more.times";

    private static final String VALIDATION_REGEX = "[0-9]+(;[1-9][0-9]*( [1-9][0-9]*)*){2}";

    private final JobShopModel model;
    private final ResourceBundle resources;

    public JobShopValidator(JobShopModel model, ResourceBundle resources) {
        this.model = model;
        this.resources = resources;
    }

    public boolean validate() {
        if (Strings.isNullOrEmpty(model.getJobShopData())) {
            model.setError(resources.getString(ERROR_EMPTY));
            return false;
        }

        Scanner scanner = new Scanner(model.getJobShopData());
        for (int jobNumber = 1; scanner.hasNextLine(); jobNumber++) {
            String line = scanner.nextLine();

            if (!line.matches(VALIDATION_REGEX)) {
                model.setError(MessageFormat.format(resources.getString(ERROR_LINE), jobNumber));
                return false;
            }

            List<String> lines = Splitter.on(JobShopModelToDataConverter.PART_SEPARATOR).splitToList(line);
            long machines = Splitter.on(JobShopModelToDataConverter.NUMBER_SEPARATOR).splitToList(lines.get(1)).size();
            long times = Splitter.on(JobShopModelToDataConverter.NUMBER_SEPARATOR).splitToList(lines.get(2)).size();

            if (machines > times) {
                model.setError(MessageFormat.format(resources.getString(ERROR_MACHINES), jobNumber));
                return false;
            } else if (times > machines) {
                model.setError(MessageFormat.format(resources.getString(ERROR_TIMES), jobNumber));
                return false;
            }
        }

        return true;
    }
}
