package com.daenjel.ilearn.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daenjel.ilearn.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class Forgotpwd extends AppCompatActivity {
    EditText edt1;
    Button btn1;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpwd);

        edt1 = (EditText) findViewById(R.id.edtsendmail);
        btn1 = (Button) findViewById(R.id.sendmail);
        pd = new ProgressDialog(Forgotpwd.this);

    }

    public void sendemail(View view) {
        String email = edt1.getText().toString();


        if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {

            edt1.setError("Invalid Email Address");
        } else {
            new getpwd().execute(email);
        }


    }


    private class getpwd extends AsyncTask<String, String, String> {
        String response;
        HttpURLConnection hurlcon;
        URL url = null;


        protected void onPreExecute() {
            pd.setMessage("Processing Password, Check Email");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {
            try {
                url = new URL("http://192.168.43.155/ilearnscripts/forgotpwd.php");


            } catch (MalformedURLException e) {
                response = "URL " + e.getMessage();
            }
            try {
                hurlcon = (HttpURLConnection) url.openConnection();
                hurlcon.setConnectTimeout(10000);
                hurlcon.setReadTimeout(15000);
                hurlcon.setRequestMethod("POST");
                hurlcon.setDoInput(true);
                hurlcon.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()

                        .appendQueryParameter("email", params[0]);


                String data = builder.build().getEncodedQuery();
                OutputStream out = hurlcon.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                bw.write(data);
                bw.flush();
                bw.close();
                hurlcon.connect();
            } catch (IOException io) {
                response = "2 " + io.getMessage();
            }
            try {
                int responseCode = hurlcon.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream in = hurlcon.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String result;
                    while ((result = br.readLine()) != null) {
                        sb.append(result);

                    }
                    response = sb.toString();
                }

            } catch (IOException ex) {
                response = "3 " + ex.getMessage();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String resp) {
            pd.dismiss();

            try {
                JSONArray users = new JSONArray(resp);

                for (int i = 0; i < users.length(); i++) {
                    JSONObject user = users.getJSONObject(i);
                    String email = user.getString("email");


                    if (!email.equals(null) && !email.equals(null)) {

                        edt1.setText("");

                        Intent gm = new Intent(Forgotpwd.this, Login.class);


                        gm.putExtra("email", email);

                        startActivity(gm);


                    }


                }

            } catch (JSONException e) {

                Toast.makeText(Forgotpwd.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}


