package com.wearapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {

    private AutoCompleteTextView etname,etEmail, etPassword, retype;
    private Button Reg;
    TextView tv_login;
    private CheckBox chkIos;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.1.130/android_register_login/register.php";

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
        etPassword=(AutoCompleteTextView)findViewById(R.id.userPassword);
        retype=(AutoCompleteTextView)findViewById(R.id.userRetypePassword);
        tv_login = (TextView)findViewById(R.id.tv_login);
        Reg=(Button)findViewById(R.id.btReg);
        chkIos = (CheckBox) findViewById(R.id.chkIos);
        loading = findViewById(R.id.loading);
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
                registerUser();

            }
        });

        setContentView(R.layout.activity_register);

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

    private void registerUser() {
        String name=etname.getText().toString().trim();
        final String mail=etEmail.getText().toString().trim();
        String pass=etPassword.getText().toString().trim();
        String retypepass=retype.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        /*if(!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(retypepass)&&
            (chkIos.isChecked()))*/
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
                    }



    @Override
    protected void onResume() {
        super.onResume();

    }
}
