package com.example.servletdemo;

import javax.activation.DataSource;
import javax.activation.URLDataSource;
import java.net.URL;

/**
 * @author : Walker
 * @date : Created in 2022/5/7 9:04 AM
 * @description:
 */
public class DataSourceException extends Exception {
    public DataSourceException() {

    }

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(URL url) {
        this.dataSource = new URLDataSource(url);
    }
}

