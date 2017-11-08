package br.com.caminhoneiro.app;

import com.orm.SugarRecord;

/**
 * Created by 16254868 on 30/10/2017.
 */

public class RodadoGanho extends SugarRecord{
    private String mes;
    private Integer quilometro;
    private double bonus;

    public RodadoGanho(){ }


    public RodadoGanho(String mes, Integer quilometro){
        this.mes = mes;
        this.quilometro = quilometro;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Integer getQuilometro() {
        return quilometro;
    }

    public void setQuilometro(Integer km) {
        this.quilometro = km;
    }

    public double getBonus() {;
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
