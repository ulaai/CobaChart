package com.example.uli2.cobachart;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText textUname, textPass;
    ProgressBar progressBar;

    Connection con;
    String ip = "172.20.8.29"; //ptmpgesqlsvrdev.pertamina.com
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String db = "UserProfileManagement";
    String uname = "sa";
    String pass = "sqlserver2012PGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.login);
        textUname = (EditText) findViewById(R.id.textUname);
        textPass = (EditText) findViewById(R.id.textPass);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String username = textUname.getText().toString();
                String password = textPass.getText().toString();
                AuthLogin authLogin = new AuthLogin();
                authLogin.execute(username, password);
            }
        });
        }

    public class AuthLogin extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected String doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];

            if(username.trim().equals("") || password.trim().equals("")) {
                z = "Please enter Username or Password";
            }
            else {
                try {
                    con = ConnectionClass(username, password, db, ip);
                    if(con == null) {
                        z = "Cannot connect. Check your internet access!";
                    }
                    else {
                        String query = "select * from ";
                        Statement statement = con.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);
                        if(resultSet.next()) {
                            z = "Login successful";
                            isSuccess = true;
                            con.close();
                        } else {
                            z = "Wrong username or password";
                            isSuccess = false;
                        }

                    }
                }
                catch (Exception e) {
                    isSuccess = false;
                    z = e.getMessage();
                }
            }
            return z;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
            if(isSuccess) {
                Toast.makeText(LoginActivity.this, "Login succesful", Toast.LENGTH_LONG).show();
            }
        }
    }

    public Connection ConnectionClass(String user, String password, String database, String
            ipserver) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;

        try {
            Class.forName(classs).newInstance();
            ConnURL = "jdbc:jtds:sqlserver://" + ipserver + ";" + "databaseName=" + database +
                    ";user=" +
                    user + ";password=" + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
            e.printStackTrace();
        }
        return conn;
//
    }
}
