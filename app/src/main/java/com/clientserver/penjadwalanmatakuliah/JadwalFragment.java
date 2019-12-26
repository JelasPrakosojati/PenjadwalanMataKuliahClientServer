package com.clientserver.penjadwalanmatakuliah;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JadwalFragment extends Fragment {

    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Jadwall> list;
    private RecyclerView.Adapter adapter;
    
    private String url = "http://jordanjelasfikri.dx.am/api/getJadwal.php";

    Button btnInsert, btnDelete;

    public JadwalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_jadwal, container, false);
        btnInsert = (Button) rootView.findViewById(R.id.btn_insertj);
        btnDelete = (Button) rootView.findViewById(R.id.btn_deletej);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InsertJ.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DeleteJ.class);
                startActivity(intent);
            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        adapter = new JadwallAdapter(getContext(), list);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        getData();

        return rootView;
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Jadwall obj = new Jadwall();
                        obj.setInfoJadwal("Jadwal");
                        obj.setIdJadwal(jsonObject.getString("id_jadwal"));
                        obj.setRuang(jsonObject.getString("ruang"));
                        obj.setHari(jsonObject.getString("hari"));
                        obj.setJamMulai(jsonObject.getString("jam_mulai"));
                        obj.setJamAkhir(jsonObject.getString("jam_akhir"));
                        obj.setMataKuliah(jsonObject.getString("matakuliah"));
                        obj.setDosen(jsonObject.getString("dosen"));

                        list.add(obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

}
