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

public class InsertJ extends AppCompatActivity {
    EditText idj, ruang, hari, jammulai, jamakhir, matakuliah, dosen;
    Button btnbatal,btnsimpan;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_dataj);

        /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_idj = data.getStringExtra("id_jadwal");
        String intent_ruang = data.getStringExtra("ruang");
        String intent_hari = data.getStringExtra("hari");
        String intent_jammulai = data.getStringExtra("jam_mulai");
        String intent_jamakhir = data.getStringExtra("jam_akhir");
        String intent_matakuliah = data.getStringExtra("matakuliah");
        String intent_dosen = data.getStringExtra("dosen");
        /*end get data from intent*/

        idj = (EditText) findViewById(R.id.inp_idj);
        ruang = (EditText) findViewById(R.id.inp_ruangj);
        hari = (EditText) findViewById(R.id.inp_harij);
        jammulai = (EditText) findViewById(R.id.inp_jammulaij);
        jamakhir = (EditText) findViewById(R.id.inp_jamakhirj);
        matakuliah = (EditText) findViewById(R.id.inp_matakuliahj);
        dosen = (EditText) findViewById(R.id.inp_dosenj);
        btnbatal = (Button) findViewById(R.id.btn_cancelj);
        btnsimpan = (Button) findViewById(R.id.btn_simpanj);
        pd = new ProgressDialog(InsertJ.this);

        btnsimpan = (Button) findViewById(R.id.btn_simpanj);
        btnbatal = (Button) findViewById(R.id.btn_cancelj);

        /*kondisi update / insert*/
        if(update == 1)
        {
            btnsimpan.setText("Update Data");
            idj.setText(intent_idj);
            ruang.setText(intent_ruang);
            hari.setText(intent_hari);
            jammulai.setText(intent_jammulai);
            jamakhir.setText(intent_jamakhir);
            matakuliah.setText(intent_matakuliah);
            dosen.setText(intent_dosen);

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
                Intent main = new Intent(InsertJ.this,MainActivity.class);
                startActivity(main);
            }
        });
    }

    private void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.UPDJ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertJ.this, ""+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertJ.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertJ.this, "Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id_jadwal",idj.getText().toString());
                map.put("ruang",ruang.getText().toString());
                map.put("hari",hari.getText().toString());
                map.put("jam_mulai",jammulai.getText().toString());
                map.put("jam_akhir",jamakhir.getText().toString());
                map.put("matakuliah",matakuliah.getText().toString());
                map.put("dosen",dosen.getText().toString());

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

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.INSJ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertJ.this, ""+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertJ.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertJ.this, "Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id_jadwal",idj.getText().toString());
                map.put("ruang",ruang.getText().toString());
                map.put("hari",hari.getText().toString());
                map.put("jam_mulai",jammulai.getText().toString());
                map.put("jam_akhir",jamakhir.getText().toString());
                map.put("matakuliah",matakuliah.getText().toString());
                map.put("dosen",dosen.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}
