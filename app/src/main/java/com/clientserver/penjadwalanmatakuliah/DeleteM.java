package com.clientserver.penjadwalanmatakuliah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class DeleteM extends AppCompatActivity {
    EditText deleteID ;
    Button btnDelete;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletem);

        deleteID = (EditText) findViewById(R.id.id_paramm);
        btnDelete = (Button) findViewById(R.id.btn_deletedm);
        pd = new ProgressDialog(DeleteM.this);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
    }



    private void deleteData()
    {
        pd.setMessage("Delete Data ...");
        pd.setCancelable(false);
        pd.show();

        StringRequest delReq = new StringRequest(Request.Method.POST,ServerAPI.DELM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(DeleteM.this,"" +res.getString("message"), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(DeleteM.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                        Toast.makeText(DeleteM.this, "", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("kode_matakuliah",deleteID.getText().toString());
                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(delReq);
    }
}
