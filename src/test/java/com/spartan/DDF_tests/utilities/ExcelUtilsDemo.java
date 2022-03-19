package com.spartan.DDF_tests.utilities;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExcelUtilsDemo {
    @Test
    public void readExcelFile(){

        // Create an object from ExcelUtils class
        // it accepts 2 arguments
        // Ar1: Location of the file
        // Ar2: sheet that we want to open

        ExcelUtil dummy_data = new ExcelUtil("src/test/resources/dummy_spartans.xlsx", "data");

        // how many rows in the sheet, rowCount() returns the number of rows in that sheet
        System.out.println("dummy_data.rowCount() = " + dummy_data.rowCount());

        // HOW MANY COLUMNS in the sheet, columnCount() returns number of columns i the sheet
        System.out.println("dummy_data.columnCount() = " + dummy_data.columnCount());

        // get all column names
        System.out.println("dummy_data.getColumnsNames() = " + dummy_data.getColumnsNames());

        // get all data in list of maps
        List<Map<String, String>> dataList = dummy_data.getDataList();
        System.out.println("dataList = " + dataList);


    }
}
