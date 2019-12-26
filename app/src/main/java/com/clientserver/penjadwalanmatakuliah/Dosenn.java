package com.clientserver.penjadwalanmatakuliah;

public class Dosenn {
    private String infoDosen;
    private String nip;
    private String namadosen;
    private String fotodosen;

    public Dosenn() {}

    public Dosenn(String nip, String namadosen, String fotodosen) {
        this.nip = nip;
        this.namadosen = namadosen;
        this.fotodosen = fotodosen;
    }

    public String getNIP() {
        return nip;
    }

    public void setNIP(String nip) {
        this.nip = nip;
    }

    public String getNamaDosen() {
        return namadosen;
    }

    public void setNamaDosen(String namadosen) {
        this.namadosen = namadosen;
    }

    public String getFotoDosen() {
        return fotodosen;
    }

    public void setFotoDosen(String fotodosen) {
        this.fotodosen = fotodosen;
    }

    public String getInfoDosen() {
        return infoDosen;
    }

    public void setInfoDosen(String infoDosen) {
        this.infoDosen = infoDosen;
    }
}