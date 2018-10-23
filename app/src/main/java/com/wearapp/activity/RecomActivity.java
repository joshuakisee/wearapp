package com.wearapp.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wearapp.MainActivity;
import com.wearapp.R;
import com.wearapp.adaptor.RecomentationAdaptor;
import com.wearapp.utils.AddRequestVolley;
import com.wearapp.utils.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.wearapp.Login.MyPREFERENCES;

public class RecomActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    String token;
    private ArrayList<Model> models;
    private RecomentationAdaptor mAdapter;
    RecyclerView home_recycler;
    SwipeRefreshLayout Drama_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Wear Recommendation");

        SharedPreferences editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        token = editor.getString("token", "null");

        home_recycler = (RecyclerView) findViewById(R.id.home_recycler);
        Drama_refresh   = (SwipeRefreshLayout) findViewById(R.id.Drama_refresh);
        Drama_refresh.setOnRefreshListener(this);


        home_recycler.setHasFixedSize(true);

        models = new ArrayList<>();
        mAdapter = new RecomentationAdaptor(RecomActivity.this, models);
        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);

        LinearLayoutManager mLayoutManager
                = new LinearLayoutManager(RecomActivity.this, LinearLayoutManager.VERTICAL, false);

        home_recycler.setLayoutManager(mLayoutManager);
        home_recycler.setItemAnimator(new DefaultItemAnimator());
        home_recycler.setAdapter(mAdapter);

        getLatest();
    }

    private void getLatest() {
        final ProgressDialog pDialog = ProgressDialog.show(RecomActivity.this,"Loading", "Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, getString(R.string.base_url)+"outfits?token="+token,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();


                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    Model data;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        data = new Model();
                        try {
                            JSONObject object = (JSONObject) jsonArray.get(i);

                            data.setUser_id(object.getString("user_id"));
                            data.setName(object.getString("name"));
                            data.setPhoto_url(object.getString("photo_url"));

                            models.add(data);

                        } catch (JSONException e) {
                            // Log.e(TAG, "the error: " + e.getMessage());

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pDialog.hide();

                NetworkResponse response = error.networkResponse;

                if (response != null && response.data != null) {


                } else {
                    Toast.makeText(RecomActivity.this, "error occurred try again", Toast.LENGTH_LONG).show();

                }
            }
        });

        AddRequestVolley.request(RecomActivity.this, req);
    }

    @Override
    public void onRefresh() {
        getLatest();

        models = new ArrayList<>();
        mAdapter = new RecomentationAdaptor(RecomActivity.this, models);

        home_recycler.setAdapter(mAdapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Drama_refresh.setRefreshing(false);
            }
        }, 1000);
    }

}
