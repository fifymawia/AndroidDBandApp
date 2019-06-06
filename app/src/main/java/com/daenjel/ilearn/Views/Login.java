package com.daenjel.ilearn.Views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class Login extends AppCompatActivity {
Button btnlog;
EditText edtname,edtpwd;
ProgressDialog pd;
String NAME,PASSWORD,EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         edtname = (EditText)findViewById(R.id.editLogin_UserName);
         edtpwd = (EditText)findViewById(R.id.editLogin_Pass);
         btnlog = (Button)findViewById(R.id.btnlogin);
        pd = new ProgressDialog(Login.this);



    }


    public void signUpPage(View view) {

        startActivity(new Intent(getApplicationContext(), SignUp.class));
    }
 public void forgotpass (View view){
        startActivity(new Intent(getApplicationContext(),Forgotpwd.class));
 }
    public void gotohome(View v) {
        String name = edtname.getText().toString();
        String password = edtpwd.getText().toString();

        if (name.length() == 0) {
            edtname.setError("Name Required");

        } else if (password.length() == 0) {

            edtpwd.setError("enter a password");

        } else {
            new signin().execute(name, password);
        }
    }

    private class signin extends AsyncTask<String, String, String> {

        String response;
        HttpURLConnection hurlcon;
        URL url = null;


        protected void onPreExecute() {
            pd.setMessage("Logging in");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {
            try {
                url = new URL("http://fredamawia.mightechsdcl.com/ilearn/login.php");

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
                        .appendQueryParameter("name", params[0])
                        .appendQueryParameter("password", params[1]);

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
            String err =null;
            try {
                JSONObject root = new JSONObject(resp);
                JSONObject user_data = root.getJSONObject("user_data");
                NAME = user_data.getString("name");
                PASSWORD = user_data.getString("password");
                EMAIL = user_data.getString("email");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }

            try{
                JSONArray users = new JSONArray(resp);

                for (int i =0; i< users.length(); i++) {
                    JSONObject user = users.getJSONObject(i);
                    String name = user.getString("name");
                    String email = user.getString("email");



                    if (!name.equals(null) && !email.equals(null)) {
                        edtname.setText("");
                        edtpwd.setText("");


                        Intent gm = new Intent(com.daenjel.ilearn.Views.Login.this, MainActivity.class);

                        gm.putExtra("name", name);
                        gm.putExtra("email", email);

                        startActivity(gm);


                    }


                }

            }catch (JSONException e){

               Toast.makeText(com.daenjel.ilearn.Views.Login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }



    }
}

