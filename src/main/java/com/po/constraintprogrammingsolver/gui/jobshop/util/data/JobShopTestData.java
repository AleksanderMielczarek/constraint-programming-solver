package com.po.constraintprogrammingsolver.gui.jobshop.util.data;

import javafx.util.StringConverter;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Created by Aleksander on 2015-01-25.
 */
public enum JobShopTestData {
    DATA_1("10;2 1 3 4;10 5 15 5" + System.getProperty("line.separator") + "5;3 2 1 4;10 5 5 10" + System.getProperty("line.separator") + "0;1 3 4 2;5 5 5 5" + System.getProperty("line.separator") + "5;3 2 4;5 10 15"),
    DATA_2("10;2 1 3 4;10 5 15 5" + System.getProperty("line.separator") + "5;3 2 1 4;10 5 5 10" + System.getProperty("line.separator") + "15;1 3 4 2;5 5 5 5" + System.getProperty("line.separator") + "5;3 2 4 1;5 10 15 10");

    private static final String KEY = "combo.test.data";

    private final String data;
    private int number;

    JobShopTestData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    static {
        int number = 1;
        for (JobShopTestData data : values()) {
            data.number = number++;
        }
    }

    public static StringConverter<JobShopTestData> getStringConverter(ResourceBundle resources) {
        return new StringConverter<JobShopTestData>() {
            @Override
            public String toString(JobShopTestData object) {
                return MessageFormat.format(resources.getString(KEY), object.number);
            }

            @Override
            public JobShopTestData fromString(String string) {
                return valueOf(string);
            }
        };
    }
}
