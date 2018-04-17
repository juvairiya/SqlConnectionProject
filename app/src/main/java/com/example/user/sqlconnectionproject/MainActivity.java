package com.example.user.sqlconnectionproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView ls;
    Connection connection = new Connection();
    java.sql.Connection con;
    ArrayList<HashMap<String,String>> data;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ls = (ListView) findViewById(R.id.listView);
        data=new ArrayList<>();
        new Get().execute();

    }

    class Get extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {

                con = connection.connectionclass();        // Connect to database
                if (con == null) {
                    Log.d("check", "no");
                } else {
                    Log.d("connected", "yes");
                    Statement s1=con.createStatement();
                    ResultSet r1=s1.executeQuery("SELECT * from country ");
                    while(r1.next()){
                        HashMap<String,String> hashMap=new HashMap<>();//create a hashmap to store the data in key value pair
                        hashMap.put("code",r1.getString("country code"));
                        hashMap.put("name",r1.getString("country name"));
                       data.add(hashMap);
                    }

                }

            } catch (Exception ex) {
                String z = ex.getMessage();
                Log.d("catch", z);
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            Log.d("data",data.toString());
            String fromArray[]={"code","name"};
            int to[]={R.id.column1,R.id.column2};
            adapter=new SimpleAdapter (MainActivity.this, data, R.layout.mylist, fromArray,to);
                     ls.setAdapter(adapter);
        }
    }
}
