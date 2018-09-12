package com.wearapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/*import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;*/

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {

    private AutoCompleteTextView etname,etEmail, etMobile, etPassword, retype;
    private Button Reg;
    TextView tv_login;
    ProgressDialog pDialog;
    private CheckBox chkIos;
    private ProgressBar loading;
    private static String URL_REGIST = "http://oufit.herokuapp.com/api/register";

    private ProgressDialog progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        getWindow().setBackgroundDrawableResource(R.drawable.back);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        progressBar=new ProgressDialog(this);
        etname=(AutoCompleteTextView)findViewById(R.id.username);
        etEmail=(AutoCompleteTextView)findViewById(R.id.userEmail);
        etMobile = (AutoCompleteTextView)findViewById(R.id.mobile);
        etPassword=(AutoCompleteTextView)findViewById(R.id.userPassword);
        retype=(AutoCompleteTextView)findViewById(R.id.userRetypePassword);
        tv_login = (TextView)findViewById(R.id.tv_login);
        Reg=(Button)findViewById(R.id.btReg);
        chkIos = (CheckBox) findViewById(R.id.chkIos);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reg();

            }
        });

        /*setContentView(R.layout.activity_register);*/

    }
    /*private void Regist() {
        loading.setVisibility(View.VISIBLE);
        Reg.setVisibility(View.GONE);

        final String name = this.etname.getText().toString().trim();
        final String email = this.etEmail.getText().toString().trim();
        final String password = this.etPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Register.this, "Registration Error" + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            Reg.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, "Registration Error" + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        Reg.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }*/

    /*private void registerUser() {
        String name=etname.getText().toString().trim();
        final String mail=etEmail.getText().toString().trim();
        String pass=etPassword.getText().toString().trim();
        String retypepass=retype.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        *//*if(!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(retypepass)&&
            (chkIos.isChecked()))*//*
        {
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getApplicationContext(), "Enter username!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(mail)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                return;
            }


            if (!mail.matches(emailPattern)) {
                Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();

                return;
            }

            if (TextUtils.isEmpty(pass)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (pass.length() < 6) {
                etPassword.setError("Password too Short!");
                //Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(pass)) {
                retype.setError("error, Doesnt Match!");
                //Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!chkIos.isChecked()) {
                Toast.makeText(getApplicationContext(), "You should agree to terms and conditions to continue!", Toast.LENGTH_SHORT).show();
            }


            else {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                progressBar.setMessage("Signing in ...");
                progressBar.show();

            }


                        }
                    }*/


    public void reg(){

        pDialog = new ProgressDialog(Register.this);
        pDialog.setMessage("Signing Up...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        String mName = etname.getText().toString().trim();
        String mEmail1 = etEmail.getText().toString().trim();
        String mMobile = etMobile.getText().toString().trim();
        String mPass1 = etPassword.getText().toString().trim();

        Map<String, String> params = new HashMap<String, String>();
        /*params.put("email", "milo77@example.org");
        params.put("password", "et"); */
        params.put("name", mName);
        params.put("email", mEmail1);
        params.put("phone_number", mMobile);
        params.put("password", mPass1);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, URL_REGIST, params,
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        //google.setVisibility(View.INVISIBLE);
                        try {
                            Log.d("Registration step one!", response.toString());

                            //Toast.makeText(Login.this, "response "+response, Toast.LENGTH_LONG).show();
                            Toast.makeText(Register.this, "Registration successful\n Use Credentials to Login ", Toast.LENGTH_LONG).show();

                            final String success = response.getString("token");
                            // final int status = response.getInt("status");


                            Log.d("Register Successful!", response.toString());

                            Log.d("Register Successful!", success);

                            //  Toast.makeText(Login.this, "user ", Toast.LENGTH_LONG).show();

                            //Staring MainActivity
                            Intent i = new Intent(getApplicationContext(), Login.class);
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
                Toast.makeText(Register.this, "Registration Failed \n Please Try Again.", Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
