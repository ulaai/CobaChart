package com.example.uli2.cobachart;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Created by user on 7/17/2017.
 */

public class ConnectionClass {
    String ip = "172.20.8.29";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String db = "ptmpgesqlsvrdev.pertamina.com";
    String uname = "sa";
    String pass = "sqlserver2012PGE";

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;

        try {
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";" + "databaseName=" + db + ";user=" +
                    uname + ";password=" + pass + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
        return conn;
    }
}
