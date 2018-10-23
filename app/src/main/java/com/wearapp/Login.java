package com.wearapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/*import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wearapp.PrefManager;*/

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wearapp.activity.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    private Button btLog;
    private PrefManager prefManager;
    private TextView regtxt, tv_reset_password;

    private AutoCompleteTextView etEmail, etPassword;
    private ProgressBar loading;
    ProgressDialog pDialog;
    private ProgressDialog progressBar;
    private static String URL_LOGIN = "http://oufit.herokuapp.com/api/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etEmail = findViewById(R.id.userEmailLog);
        etPassword = findViewById(R.id.userPasswordLog);
        /*loading = findViewById(R.id.loading);*/

        // Checking for first time launch - before calling setContentView()
        prefManager= new PrefManager(this);
        if (prefManager.isFirstTimeLaunch()) {
            prefManager.setFirstTimeLaunch(false);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);


        getWindow().setBackgroundDrawableResource(R.drawable.back);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        progressBar=new ProgressDialog(this);
        etEmail=(AutoCompleteTextView)findViewById(R.id.userEmailLog);
        etPassword=(AutoCompleteTextView)findViewById(R.id.userPasswordLog);
        tv_reset_password = (TextView)findViewById(R.id.tv_reset_password);
        btLog=(Button) findViewById(R.id.btLog);
        regtxt=(TextView) findViewById(R.id.regtxt);


        /*btLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });*/

        btLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = etEmail.getText().toString().trim();
                String mPass = etPassword.getText().toString().trim();

                if (!mEmail.isEmpty() || !mPass.isEmpty()) {
                    loginn();
                    /*startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();*/
                } else {
                    etEmail.setError("Please insert your email");
                    etPassword.setError("Please insert your password");
                }
            }
        });


        regtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
                finish();
            }
        });
        tv_reset_password .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ResetPasswordActivity.class));
            }
        });

    }

    private void loginn() {

        pDialog = new ProgressDialog(Login.this);
        pDialog.setMessage("Signing In...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        String mEmail1 = etEmail.getText().toString().trim();
        String mPass1 = etPassword.getText().toString().trim();

        Map<String, String> params = new HashMap<String, String>();
        /*params.put("email", "milo77@example.org");
        params.put("password", "et"); */
        params.put("email", mEmail1);
        params.put("password", mPass1);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, URL_LOGIN, params,
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();

                        try {
                            JSONObject jObj = new JSONObject(response.toString());
                            String token   = jObj.getString("token");

                            Log.d("tokensss", token);

                            SharedPreferences.Editor log_editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();
                            log_editor.putString("token",token);
                            log_editor.commit();


                            Toast.makeText(Login.this, "login success ", Toast.LENGTH_LONG).show();

                            final String success = response.getString("token");

                            //Staring MainActivity
                            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(i);
                            finish();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                pDialog.dismiss();
                //  google.setVisibility(View.INVISIBLE);
                Log.d("Response: ", response.toString());
//                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
//                        .setTitleText("OOPS")
//                        .setContentText("INCORRECT USERNAME OR PASSWORD")
//                        .show();
                Toast.makeText(Login.this, "INVALID CREDENTIALS", Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }


}

