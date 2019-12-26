package com.clientserver.penjadwalanmatakuliah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.clientserver.penjadwalanmatakuliah.Util.AppController;
import com.clientserver.penjadwalanmatakuliah.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertM extends AppCompatActivity {
    EditText kodemk, namamk, smt, sks;
    Button btnbatal,btnsimpan;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_datam);

        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_kodemk = data.getStringExtra("kode_matakuliah");
        String intent_namamk = data.getStringExtra("nama_matakuliah");
        String intent_smt = data.getStringExtra("semester");
        String intent_sks = data.getStringExtra("sks");
        /*end get data from intent*/

        kodemk = (EditText) findViewById(R.id.inp_idm);
        namamk = (EditText) findViewById(R.id.inp_namam);
        smt = (EditText) findViewById(R.id.inp_semesterm);
        sks = (EditText) findViewById(R.id.inp_sksm);
        btnbatal = (Button) findViewById(R.id.btn_cancelm);
        btnsimpan = (Button) findViewById(R.id.btn_simpanm);
        pd = new ProgressDialog(InsertM.this);

        btnsimpan = (Button) findViewById(R.id.btn_simpanm);
        btnbatal = (Button) findViewById(R.id.btn_cancelm);

        /*kondisi update / insert*/
        if(update == 1)
        {
            btnsimpan.setText("Update Data");
            kodemk.setText(intent_kodemk);
            namamk.setText(intent_namamk);
            smt.setText(intent_smt);
            sks.setText(intent_sks);

        }


        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update == 1)
                {
                    Update_data();
                }else {
                    simpanData();
                }
            }
        });

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(InsertM.this,MainActivity.class);
                startActivity(main);
            }
        });
    }

    private void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.UPDM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertM.this, ""+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertM.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertM.this, "Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("kode_matakuliah",kodemk.getText().toString());
                map.put("nama_matakuliah",namamk.getText().toString());
                map.put("semester",smt.getText().toString());
                map.put("sks",sks.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);
    }



    private void simpanData()
    {

        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.INSM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertM.this, ""+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertM.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertM.this, "Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("kode_matakuliah",kodemk.getText().toString());
                map.put("nama_matakuliah",namamk.getText().toString());
                map.put("semester",smt.getText().toString());
                map.put("sks",sks.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}
