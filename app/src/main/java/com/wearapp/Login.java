package com.wearapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private Button btLog;
    private PrefManager prefManager;
    private TextView regtxt,tv_reset_password;

    private AutoCompleteTextView etEmail, etPassword;
    private ProgressBar loading;
    private ProgressDialog progressBar;
    private static String URL_LOGIN = "http://192.168.1.130/android_register_login/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etEmail = findViewById(R.id.userEmailLog);
        etPassword = findViewById(R.id.userPasswordLog);
        loading = findViewById(R.id.loading);

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


        btLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        /*btLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = etEmail.getText().toString().trim();
                String mPass = etPassword.getText().toString().trim();

                if (!mEmail.isEmpty() || !mPass.isEmpty()) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    etEmail.setError("Please insert your email");
                    etPassword.setError("Please insert your password");
                }
            }
        });*/

        /*private void Login(final String etEmail, final String etPassword) {

            loading.setVisibility(View.VISIBLE);
            btLog.setVisibility(View.GONE);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                JSONArray jsonArray = jsonObject.getJSONArray("login");

                                if (success.equals("1")) {

                                    for (int i=0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);

                                        String name = object.getString("name").trim();
                                        String email = object.getString("email").trim();

                                        Toast.makeText(Login.this, "Successfully Logged in "+name, Toast.LENGTH_SHORT).show();
                                        loading.setVisibility(View.GONE);

                                    }

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                loading.setVisibility(View.GONE);
                                btLog.setVisibility(View.VISIBLE);
                                Toast.makeText(Login.this, "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            loading.setVisibility(View.GONE);
                            btLog.setVisibility(View.VISIBLE);
                            Toast.makeText(Login.this, "Error "+error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }*/


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






    }

