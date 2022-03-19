package com.spartan.DDF_tests.tests;

import com.spartan.DDF_tests.utilities.ExcelUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class DDF_data_provider_test {




    @DataProvider
    public Object [][] userData(){

        ExcelUtil dummyData = new ExcelUtil("src/test/resources/dummy_spartans.xlsx", "data");

        String [][] dataArray = dummyData.getDataArrayWithoutFirstRow();

        return dataArray;

    }


    @Test(dataProvider = "userData")
    public void getAllSpartansInFile(String name, String gender, String phone){

        BigDecimal bigDecimal = new BigDecimal(String.valueOf(phone));
        System.out.println("bigDecimal = " + bigDecimal);

    }


}
