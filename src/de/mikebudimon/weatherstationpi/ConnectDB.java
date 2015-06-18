package de.mikebudimon.weatherstationpi;

import java.sql.*;

/**
 * Connect to and transfer data between MYSQL Database
 */

public class ConnectDB {

    private Connection mConnect = null;
    private PreparedStatement mPreparedStatement = null;

    // JDBC driver name and database URL
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://"; // enter here your database URL

    //  Database credentials
    private final String USER = ""; // enter here your db user
    private final String PASS = ""; // enter here your db password


    public void useDatabase() throws Exception {

        // This will load the MySQL driver, each DB has its own driver
        Class.forName(JDBC_DRIVER);

        mConnect = DriverManager.getConnection(DB_URL, USER, PASS);
    }


    public void writeDatabase(float temperature, float humidity, float cpuTemp, float outsideTemp, float outsideHum) throws Exception {

        // PreparedStatements can use variables and are more efficient than normal statements
        mPreparedStatement = mConnect.prepareStatement("insert into '' values (null, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)"); // enter here your database table

        mPreparedStatement.setFloat(1, humidity);
        mPreparedStatement.setFloat(2, temperature);
        mPreparedStatement.setFloat(3, cpuTemp);
        mPreparedStatement.setFloat(4, outsideTemp);
        mPreparedStatement.setFloat(5, outsideHum);

        mPreparedStatement.executeUpdate();
    }

    public void close() throws Exception {

            if (mConnect != null) {
                mConnect.close();
            }

            if (mPreparedStatement != null){
                mPreparedStatement.close();
            }
    }
}
