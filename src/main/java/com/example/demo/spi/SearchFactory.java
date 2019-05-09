package com.example.demo.spi;

import java.sql.Connection;
import com.mysql.jdbc.Driver;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;

public class SearchFactory {
    private SearchFactory() {
    }

    public static Search newSearch() {
        Search search = null;
        ServiceLoader<Search> loader = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = loader.iterator();
        if (iterator.hasNext()) {
            search = iterator.next();
        }
        System.out.println(loader.toString());
        return search;
    }

    public static Driver newDriver() {
        Driver search = null;
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        if (iterator.hasNext()) {
            search = iterator.next();
        }
        System.out.println(loader.toString());
        Properties info=new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");
        try {
            Connection connect = search.connect("jdbc:mysql://127.0.0.1:3306/frsp?useUnicode=true&amp;characterEncoding=utf-8", info);
            Statement statement = connect.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return search;
    }
    public static void main(String[] args) {
//        Search search = SearchFactory.newSearch();
//        String spiTest = search.search("java spi test");
//        System.out.println(spiTest);
        SearchFactory.newDriver();
    }
}
