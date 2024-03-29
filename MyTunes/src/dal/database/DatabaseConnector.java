/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 *
 * @author Jacob, Jonas Charlie & René
 */
public class DatabaseConnector
{

    private SQLServerDataSource dataSource;

    /**
     * Constructor for our DatabaseConnector.
     *
     * @throws IOException
     */
    public DatabaseConnector() throws IOException
    {
        Properties props = new Properties();
        props.load(new FileReader("DBSettings.txt"));
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(props.getProperty("database"));
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));
        dataSource.setServerName(props.getProperty("server"));
    }

    /**
     * Gets connection to our database and returns it.
     *
     * @return
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }
}
