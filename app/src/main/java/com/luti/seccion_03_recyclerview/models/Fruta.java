package com.luti.seccion_03_recyclerview.models;

/**
 * Created by Luti on 8/12/16.
 */
public class Fruta {

    private String name;
    private String description;
    private int imagen;
    private int icono;
    private int contador = 0;

    public static final int LIMITE = 10;
    public static final int RESET = 0;


    public Fruta(){

    }
    public Fruta(String name, String description, int imagen, int icono, int contador){
        this.name = name;
        this.description = description;
        this.imagen = imagen;
        this.icono = icono;
        this.contador = contador;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImagen() {
        return imagen;
    }

    public int getIcono(){
        return icono;
    }

    public int getContador() {
        return contador;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setIcono (int icono){
        this.icono = icono;
    }

    public void setContador(int contador){
        if (contador <= LIMITE){
            this.contador = contador;
        }

    }
    public void setContadorCero(){
        this.contador = RESET;
    }
}
