package com.julius.pinjambukuandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Book> bookList ;
    private RvAdapter rvAdapter;
    private RecyclerView myRv;
    private BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRv=findViewById(R.id.myRv);
        nav = findViewById(R.id.bottomNav);
        nav.setSelectedItemId(R.id.bookAvail);
//        url = "";
//        myRv.invalidate();
        changeRv("http://10.0.2.2:7000/listBukuTersedia","");
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            String url = "http://10.0.2.2:7000/listBukuTersedia";
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bookAvail:

                        url = "http://10.0.2.2:7000/listBukuTersedia";
                        myRv.invalidate();
                        changeRv(url,"");
                        return true;
                    case R.id.rentBook:

                         url = "http://10.0.2.2:7000/listBukuTerpinjam";
                        myRv.invalidate();
                         changeRv(url,"pinjam");
                        return true;
                }
                return false;
            }
        });




    }
    protected void changeRv(String url, String type){

        bookList= new ArrayList<>();
        Intent intent = getIntent();
        if (intent!=null){

        }
        String username = intent.getStringExtra("username");
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("apiresult", response);
                        JSONObject object ;
                        try {
                            object= new JSONObject(response);
                            JSONArray arr = object.getJSONArray("response");
                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject bookData = arr.getJSONObject(i);
                                Book newBook = new Book(
                                        bookData.getInt("idbuku"),
                                        bookData.getString("judul_buku"),
                                        bookData.getString("tahun_terbit"),
                                        bookData.getString("author"),
                                        bookData.getString("penerbit"),
                                        bookData.getString("jumlah_halaman"),
                                        bookData.getInt("status")
                                );
                                bookList.add(newBook);
                            }
                            rvAdapter = new RvAdapter(bookList,MainActivity.this,type);
                            rvAdapter.setCallBack(new RvCallback() {
                                @Override
                                public void onitemClick(int position) {
                                    Toast.makeText(MainActivity.this, "Pinjam Berhasil", Toast.LENGTH_SHORT).show();
                                    String url = "http://10.0.2.2:7000/pinjamBuku";
                                    JSONObject object = new JSONObject();
                                    try {
                                        object.put("username",username);
                                        object.put("idbuku",bookList.get(position).getIdbuku());
                                    }catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                    JsonObjectRequest stringRequest = new JsonObjectRequest(
                                            Request.Method.POST,url,object,
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {

                                                    try {
                                                        Toast.makeText(MainActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                                                        String msg = response.getString("message");
                                                       String url = "http://10.0.2.2:7000/listBukuTerpinjam";
                                                        myRv.invalidate();
                                                        changeRv(url,"pinjam");


                                                    }
                                                    catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }

                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Log.e("apiresult", error.getMessage() != null ? error.getMessage() : "Unknown error");
                                                }

                                            });
                                    // Add the request to the RequestQueue.
                                    Volley.newRequestQueue(MainActivity.this).add(stringRequest);


                                }
                            });

                            myRv.setAdapter(rvAdapter);
                            myRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            myRv.invalidate();

                            rvAdapter.notifyDataSetChanged();

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("apiresult", error.getMessage() != null ? error.getMessage() : "Unknown error");
                    }
                });

        // Add the request to the RequestQueue.
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
