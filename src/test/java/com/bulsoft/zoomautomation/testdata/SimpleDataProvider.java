package com.bulsoft.zoomautomation.testdata;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleDataProvider {

    @DataProvider
    public static Object[][] testDataProvider(){
        return new Object[][]{{"atin","singh"},{"vivek","g"}};
    }

    @DataProvider(name = "mydataProvider")
    public static Iterator<Object[]> anotherDataProvider(){
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"Name1","Name2"});
        data.add(new Object[]{"Name1a","Name2a"});
        data.add(new Object[]{"Name3a","Name4a"});
        return data.iterator();
    }

    @DataProvider(name ="excelLoginProvider")
    public static Iterator<Object[]> loginDataProvider(){
        ExcelDataReader reader = new ExcelDataReader();
        return reader.readExcelData("LoginData",true).iterator();
    }

    @DataProvider(name = "empDataProvider")
    public static Iterator<Object[]> empDataProvider(){
        ExcelDataReader reader = new ExcelDataReader();
        List<Object[]> refindDataSet = new ArrayList<>();
        final List<Object[]> data = reader.readExcelData("Dashboard", true);
        for(Object[] objArray : data){
            refindDataSet.add(new Object[]{objArray[0],objArray[1]});
        }
        return refindDataSet.iterator();
    }


}
