package com.example.uli2.cobachart;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by uli2 on 06/07/17.
 */

public class Mysingleton {
    private static Mysingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private Mysingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public  RequestQueue getRequestQueue() {
        if(mRequestQueue==null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized Mysingleton getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new Mysingleton(context);
        }

        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
