package com.clientserver.penjadwalanmatakuliah;

import java.io.Serializable;

public class MataKuliah implements Serializable {
    private String kodemk;
    private String namamk;
    private String smt;
    private String sks;

    public MataKuliah() {
    }

    public MataKuliah(String kodemk, String namamk, String smt, String sks) {
        this.kodemk = kodemk;
        this.namamk = namamk;
        this.smt = smt;
        this.sks = sks;
    }

    public String getKodeMK() {
        return kodemk;
    }

    public void setKodeMK(String kodemk) {
        this.kodemk = kodemk;
    }

    public String getNamaMK() {
        return namamk;
    }

    public void setNamaMK(String namamk) {
        this.namamk = namamk;
    }

    public String getSemester() {
        return smt;
    }

    public void setSemester(String smt) {
        this.smt = smt;
    }

    public String getSKS() {
        return sks;
    }

    public void setSKS(String sks) {
        this.sks = sks;
    }

}