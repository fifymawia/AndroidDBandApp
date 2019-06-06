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

public class SignUp extends AppCompatActivity {
    Button btn1;
    EditText edtname, edtemail, edtpwd, edtcpwd;
    ProgressDialog pd;
    String NAME = null, PASSWORD = null, EMAIL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtname = (EditText) findViewById(R.id.name);
        edtemail = (EditText) findViewById(R.id.email);
        edtpwd = (EditText) findViewById(R.id.password);
        edtcpwd = (EditText) findViewById(R.id.cpassword);
        btn1 = (Button) findViewById(R.id.regbtn);
        pd = new ProgressDialog(SignUp.this);
    }
    public void loginPage(View view) {


        String name = edtname.getText().toString();
        String email = edtemail.getText().toString();
        String password = edtpwd.getText().toString();
        String cpassword = edtcpwd.getText().toString();

        if (name.length() == 0) {
            edtname.setError("Enter UserName");

        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {

            edtemail.setError("Invalid Email Address");


        } else if (password.length() == 0) {
            edtpwd.setError("Enter a Password");
        } else if (!password.equals(cpassword)) {
            Toast.makeText(SignUp.this, "Password Not matching", Toast.LENGTH_SHORT).show();
        } else {
            new registration().execute(name, email, password);
        }
    }



private class registration extends AsyncTask<String, String, String> {
    String response;
    HttpURLConnection hurlcon;
    URL url = null;


    protected void onPreExecute() {
        pd.setMessage("signing up");
        pd.setCancelable(false);
        pd.show();
    }

    protected String doInBackground(String... params) {
        try {
            url = new URL("http://fredamawia.mightechsdcl.com/ilearn/register.php");


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
                    .appendQueryParameter("email", params[1])
                    .appendQueryParameter("password", params[2]);

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
        String err = null;
        if (resp.equals("you have registered verify email")) {
            edtname.setText("");
            edtemail.setText("");
            edtpwd.setText("");

        }
        try {
            JSONObject root = new JSONObject(resp);
            JSONObject user_data = root.getJSONObject("user_data");
            NAME = user_data.getString("name");
            PASSWORD = user_data.getString("password");
            EMAIL = user_data.getString("email");


        } catch (JSONException e) {
            e.printStackTrace();
            err = "Exception: " + e.getMessage();
        }


        Intent login = new Intent(SignUp.this, Login.class);
        startActivity(login);


        Toast.makeText(SignUp.this, resp, Toast.LENGTH_SHORT).show();
    }
}

    public void Gotologin(View view) {
        Intent login = new Intent(SignUp.this, Login.class);
        startActivity(login);
    }

}
