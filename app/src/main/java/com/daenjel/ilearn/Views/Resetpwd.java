package com.daenjel.ilearn.Views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daenjel.ilearn.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Resetpwd extends AppCompatActivity {

    EditText edt1, edt2, edt3;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpwd);

        edt1 = (EditText) findViewById(R.id.email);
        edt2 = (EditText) findViewById(R.id.newpass);
        edt3 = (EditText) findViewById(R.id.cnewpass);
        pd = new ProgressDialog(Resetpwd.this);
    }
    public void resetpwd (View v){

        String email= edt1.getText().toString();
        String cnewpwd= edt2.getText().toString();
        String newpwd = edt3.getText().toString();


        if (email.length()==0){
            edt1.setError("Email Required");

        } else if (newpwd.length()==0) {

            edt2.setError("Enter  New Password");

        } else if (cnewpwd.length()==0) {
            edt3.setError("Confirm Password");
        }
        else if (!newpwd. equals(cnewpwd)) {
            edt3.setError("Match password");
        }else {
            new Reset().execute(email,newpwd);

        }

    }
    private class Reset extends AsyncTask<String, String, String> {
        String response;
        HttpURLConnection hurlcon;
        URL url = null;

        //execute before sending data
        @Override
        protected void onPreExecute(){
            pd.setMessage("Resetting password...");
            pd.setCancelable(false);
            pd.show();
        }


        //execute when sending and receiving data
        @Override
        protected String doInBackground(String... params){

            try{
                url = new URL("http://192.168.43.155/ilearnscripts/changepwd.php");//access to php script

            }catch (MalformedURLException e){
                response = "URL "+e.getMessage();

            }
            try {
                hurlcon = (HttpURLConnection) url.openConnection(); //create connection to url
                hurlcon.setConnectTimeout(10000);//set connection timeout
                hurlcon.setReadTimeout(15000);//read timeout
                hurlcon.setRequestMethod("POST");//set request method
                hurlcon.setDoOutput(true);//allow send data out
                hurlcon.setDoInput(true);//allow read data in
                //append variables
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("email",params[0])
                        .appendQueryParameter("newpwd",params[1]);

                //encode to one string
                String data = builder.build().getEncodedQuery();

                //send data to php
                OutputStream out = hurlcon.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                bw.write(data);
                bw.flush();
                bw.close();
                //out.close();
                hurlcon.connect(); //Reconnect to server

            }catch (IOException io){
                response = "2 "+io.getMessage();

            }

            try{
                //receive data from php
                int responseCode = hurlcon.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {//check if connection is ok

                    InputStream in = hurlcon.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb = new StringBuilder();
                    String result;
                    while ((result=br.readLine())!= null){
                        sb.append(result);
                    }
                    //response = br.readLine(); //read response
                    response = sb.toString();
                }

            }catch (IOException ex){
                response = "3 "+ex.getMessage();

            }
            return  response;
        }
        //execute after receiving data
        @Override
        protected  void onPostExecute(String resp){

            pd.dismiss();

            if (resp.equals("Password Updated")){


                edt1.setText("");
                edt2.setText("");
                edt3.setText("");

                Intent resetpwd = new Intent(Resetpwd.this,MainActivity.class);
                startActivity(resetpwd);



            }
            Toast.makeText(Resetpwd.this, resp, Toast.LENGTH_SHORT).show();

        }
    }

}
