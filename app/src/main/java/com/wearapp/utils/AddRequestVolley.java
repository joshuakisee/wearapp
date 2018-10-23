package com.wearapp.utils;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class AddRequestVolley {
    public static void request(Context mContext, JsonObjectRequest req)
    {
        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(req);

        req.setRetryPolicy(new DefaultRetryPolicy(
                25000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}
