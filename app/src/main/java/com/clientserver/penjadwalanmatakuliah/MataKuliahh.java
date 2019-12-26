package com.clientserver.penjadwalanmatakuliah;

public class MataKuliahh {
    private String infoMK;
    private String kodemk;
    private String namamk;
    private String smt;
    private String sks;

    public MataKuliahh() {}

    public MataKuliahh(String kodemk, String namamk, String smt, String sks) {
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

    public String getInfoMK() {
        return infoMK;
    }

    public void setInfoMK(String infoMK) {
        this.infoMK = infoMK;
    }
}