package com.example.james.recordiapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {

    private ProgressDialog pDialog;

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ACCTYPE = "actype";


    private static String url_login = "http://djcridef.co.ke/retreive.php";

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btnregister = (Button) findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_activity = new Intent(login.this, register.class);
                startActivity(register_activity);
            }
        });


        Button btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtusernamelgn = (EditText) findViewById(R.id.txtusernamelgn);
                EditText txtpasslgn = (EditText) findViewById(R.id.txtpasslgn);

                username = txtusernamelgn.getText().toString().trim();
                password = txtpasslgn.getText().toString().trim();


                if (!username.equals("") && !password.equals("")) {

                    new LoginUser().execute();

                } else {

                    Toast.makeText(login.this, "Enter Username and Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private class LoginUser extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(login.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));

            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url_login, ServiceHandler.GET, params);
            Log.e("Content", jsonStr.toString());
            JSONObject json = null;
            try {

                json = new JSONObject(jsonStr);

                if (json.getString(KEY_SUCCESS) != null) {
                    // loginErrorMsg.setText("");
                    String res = json.getString(KEY_SUCCESS);

                    if (Integer.parseInt(res) == 1) {
                        // user successfully logged in

                        String type = json.getString(KEY_ACCTYPE);

                        //System.out.println(type);
                        if (type.equalsIgnoreCase("producer"))
                        // Launch Home Screen
                        {
                            Log.d("Type",type);
                            Intent intCategory = new Intent(
                                    login.this, home.class);
                            intCategory.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intCategory);
                            finish();
                        } else {
                            if (type.equalsIgnoreCase("artist"))

                            {
                                Log.d("Type", type);
                                Intent intCategory = new Intent(login.this, homeartist.class);
                                intCategory.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intCategory);

                                finish();
                            } else {
                                if (type.equalsIgnoreCase("listener"))

                                {
                                    Log.d("Type",type);
                                    Intent intCategory = new Intent(login.this, listener.class);
                                    intCategory.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intCategory);

                                    finish();
                                } else {
                                    Intent intCategory = new Intent(
                                            getApplicationContext(), login.class);
                                    intCategory.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intCategory);

                                    finish();
                                }

                            }
                        }


                    } else {
                        // Error in login
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                // loginErrorMsg.setText("Incorrect PhoneNumber/Password");
                                /*alert.showAlertDialog(
                                        login.this,
                                        "Login Error",
                                        "Incorrect Username/Password. Please Try Again!",
                                        false);*/
                            }
                        });
                    }
                } else {
                   /* alert.showAlertDialog(login.this, "Login Error",
                            "Failed to Login. Please Try Again!", false);*/
                }


            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();

                //shows the response that we got from the http request on the logcat
                Log.d("Response: ", "> " + jsonStr);


                e1.printStackTrace();


                Log.d("userdetails: ", "> " + jsonStr);


                e1.printStackTrace();


            }
            // }
            // });
            // thread.start();
            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }
    }


}
