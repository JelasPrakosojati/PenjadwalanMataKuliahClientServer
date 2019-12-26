package com.clientserver.penjadwalanmatakuliah;

import java.io.Serializable;

public class Jadwal implements Serializable {
    private String id_jadwal;
    private String ruang;
    private String hari;
    private String jam_mulai;
    private String jam_akhir;
    private String matakuliah;
    private String dosen;


    public Jadwal() {
    }

    public Jadwal(String id_jadwal, String ruang, String hari, String jam_mulai, String jam_akhir, String matakuliah, String dosen) {
        this.id_jadwal = id_jadwal;
        this.ruang = ruang;
        this.hari = hari;
        this.jam_mulai = jam_mulai;
        this.jam_akhir = jam_akhir;
        this.matakuliah = matakuliah;
        this.dosen = dosen;
    }

    public String getIdJadwal() {
        return id_jadwal;
    }

    public void setIdJadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJamMulai() {
        return jam_mulai;
    }

    public void setJamMulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJamAkhir() {
        return jam_akhir;
    }

    public void setJamAkhir(String jam_akhir) {
        this.jam_akhir = jam_akhir;
    }

    public String getMataKuliah() {
        return matakuliah;
    }

    public void setMataKuliah(String matakuliah) {
        this.matakuliah = matakuliah;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

}
