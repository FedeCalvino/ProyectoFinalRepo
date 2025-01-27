package com.example.proyectofinalannedecor.Clases.ConfiguracionRoller;

public class TipoCadena {
    String TipoCadena;
    int IdTipoCadena;

    public TipoCadena(int IdTipoCadena) {
        this.IdTipoCadena = IdTipoCadena;
    }

    public String getTipoCadena() {
        return TipoCadena;
    }

    public void setTipoCadena(String tipoCadena) {
        TipoCadena = tipoCadena;
    }

    public int getIdTipoCadena() {
        return IdTipoCadena;
    }

    public void setIdTipoCadena(int idTipoCadena) {
        IdTipoCadena = idTipoCadena;
    }

    @Override
    public String toString() {
        return "TipoCadena{" +
                "TipoCadena='" + TipoCadena + '\'' +
                ", IdTipoCadena=" + IdTipoCadena +
                '}';
    }
}
