package com.example.james.recordiapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class register extends AppCompatActivity {

    private ProgressDialog pDialog;

    // JSON Response node names
    private static String KEY_SUCCESS = "success";


    private static String url_register = "http://www.djcridef.co.ke/register.php";




    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    String strusername, strpassword, stracctype, strcpass, strid;

    int intid = 0;


    ArrayAdapter<CharSequence> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button btnreg = (Button)findViewById(R.id.btnreg);
        assert btnreg != null;
        assert btnreg != null;
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText txtusernamereg = (EditText) findViewById(R.id.txtusernamereg);
                strusername = txtusernamereg.getText().toString().trim();

                EditText txtpassreg = (EditText) findViewById(R.id.txtpassreg);
                strpassword = txtpassreg.getText().toString().trim();

                EditText txtcpassreg = (EditText) findViewById(R.id.txtcpassreg);
                strcpass = txtcpassreg.getText().toString().trim();

                EditText txtacctype = (EditText) findViewById(R.id.txtacctype);
                stracctype = txtacctype.getText().toString().trim();

               /* Spinner txtacctype = (Spinner)findViewById(R.id.txtacctype);
                adapter = ArrayAdapter.createFromResource(this, R.array.accounttype,R.layout.activity_register);
                adapter.setDropDownViewResource(R.layout.activity_register);
                txtacctype.setAdapter(adapter);
                txtacctype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        TextView mytext = (TextView)view;
                        stracctype = mytext.getText().toString().trim();
                    }
                });

*/




                intid = intid++;
                strid = Integer.toString(intid);

                if (!strusername.equals("")&&!strpassword.equals("")&&!strcpass.equals("")) {

                    if (strcpass.equals(strpassword)) {


                        new registeruser().execute();


                    } else {

                        Toast.makeText(register.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }

                else {

                    Toast.makeText(register.this,"Enter all values",Toast.LENGTH_SHORT).show();
                }



            }
        });


    }


    private class registeruser extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(register.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", strusername));
            params.add(new BasicNameValuePair("password", strpassword));
            params.add(new BasicNameValuePair("acctype", stracctype));
            params.add(new BasicNameValuePair("id", strid));


            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url_register, ServiceHandler.GET,params);
            Log.e("Content",jsonStr.toString());
            JSONObject json = null;
            try {
                json = new JSONObject(jsonStr);



                if (json.getString(KEY_SUCCESS) != null) {
                    // loginErrorMsg.setText("");
                    String res = json.getString(KEY_SUCCESS);

                    if (Integer.parseInt(res) == 1) {
                        // user successfully logged in



                        // Launch Home Screen
                        Intent intCategory = new Intent(
                                getApplicationContext(), login.class);
                        intCategory.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intCategory);

                        finish();
                    } else {
                        // Error in login
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                // loginErrorMsg.setText("Incorrect PhoneNumber/Password");
                           /*     alert.showAlertDialog(
                                        register.this,
                                        "Register Error",
                                        "Please Try Again!",
                                        false);*/
                            }
                        });
                    }
                }

                else {
                    /*alert.showAlertDialog(register.this, "Register Error",
                            "Failed to Register. Please Try Again!", false);*/
                }




            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();

                //shows the response that we got from the http request on the logcat
                Log.d("Response: ", "> " + jsonStr);

                // check for login response
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
