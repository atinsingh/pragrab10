package com.bulsoft.zoomautomation.testdata;

import com.bulsoft.zoomautomation.configuration.DriverConfig;

public class ExcelDataProvider{

    private String fileName = DriverConfig.getPropertyValue("testdata.excel.path");

    // Put your excel file in location
    // Read the file as inpuStream
    // pass it XFFWorkWook
    // Sheet
    // ROws ==> Using Row Iterator
    // Row  -- >   You need get Cell Iterator
    // Cell Iterator -- you need to get Cell
    // Read cell value either of type
    //  Numeric/String/Boolean/Date
    // return in the format of
    // List.iterator<Object[]>ls
}
