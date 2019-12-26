package com.clientserver.penjadwalanmatakuliah;

import java.io.Serializable;

public class Dosen implements Serializable {
    private String nip;
    private String namadosen;
    private String fotodosen;

    public Dosen() {
    }

    public Dosen(String nip, String namadosen, String fotodosen) {
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
}