package de.mikebudimon.weatherstationpi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Connect to and transfer data between MYSQL Database
 */

public class ConnectDB {

    private Connection mConnect = null;
    private PreparedStatement mPreparedStatement = null;


    /**
     * load MySQL driver
     *
     * @throws Exception
     */
    public void useDatabase() throws Exception {

        // This will load the MySQL driver, each DB has its own driver
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        Class.forName(JDBC_DRIVER);

        String DB_URL = "jdbc:mysql://"; // enter your database
        String USER = ""; // enter db username
        String PASS = ""; // enter db password
        mConnect = DriverManager.getConnection(DB_URL, USER, PASS);
    }


    public void writeDatabase(float temperature, float humidity, float cpuTemp, float outsideTemp, float outsideHum) throws Exception {

        // PreparedStatements can use variables and are more efficient than normal statements, exchange XX for your db name
        mPreparedStatement = mConnect.prepareStatement("insert into XX values (null, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)");

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

        if (mPreparedStatement != null) {
            mPreparedStatement.close();
        }
    }
}
