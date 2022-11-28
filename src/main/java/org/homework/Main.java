package org.homework;

import org.homework.di.Injector;

import java.sql.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {

        String url = "jdbc:postgresql://127.10.10.10:5432/homework";
        Properties props = new Properties();
        props.setProperty("user", "app");
        props.setProperty("password", "secret");
        Connection conn = DriverManager.getConnection(url, props);


        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM accounts");
        while (rs.next()) {
            System.out.print("Column 1 returned ");
            System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();

//        Injector.createServices();
//        Injector.start();

    }

}
