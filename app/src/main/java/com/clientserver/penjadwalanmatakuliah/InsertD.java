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

public class InsertD extends AppCompatActivity {
    EditText nip, namadosen, fotodosen;
    Button btnbatal,btnsimpan;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_datad);

        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_nip = data.getStringExtra("nip");
        String intent_namadosen = data.getStringExtra("nama_dosen");
        String intent_fotodosen = data.getStringExtra("foto_dosen");
        /*end get data from intent*/

        nip = (EditText) findViewById(R.id.inp_idd);
        namadosen = (EditText) findViewById(R.id.inp_namad);
        fotodosen = (EditText) findViewById(R.id.inp_fotod);
        btnbatal = (Button) findViewById(R.id.btn_canceld);
        btnsimpan = (Button) findViewById(R.id.btn_simpand);
        pd = new ProgressDialog(InsertD.this);

        btnsimpan = (Button) findViewById(R.id.btn_simpand);
        btnbatal = (Button) findViewById(R.id.btn_canceld);

        /*kondisi update / insert*/
        if(update == 1)
        {
            btnsimpan.setText("Update Data");
            nip.setText(intent_nip);
            namadosen.setText(intent_namadosen);
            fotodosen.setText(intent_fotodosen);

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
                Intent main = new Intent(InsertD.this,MainActivity.class);
                startActivity(main);
            }
        });
    }

    private void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.UPDD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertD.this, ""+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertD.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertD.this, "Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nip",nip.getText().toString());
                map.put("nama_dosen",namadosen.getText().toString());
                map.put("foto_dosen",fotodosen.getText().toString());

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

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.INSD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertD.this, ""+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertD.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertD.this, "Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nip",nip.getText().toString());
                map.put("nama_dosen",namadosen.getText().toString());
                map.put("foto_dosen",fotodosen.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}
