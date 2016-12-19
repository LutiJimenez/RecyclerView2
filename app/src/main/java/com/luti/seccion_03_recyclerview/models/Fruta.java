package com.luti.seccion_03_recyclerview.models;

/**
 * Created by Luti on 8/12/16.
 */
public class Fruta {

    public String name;
    public String description;
    public int imagen;
    public int icono;
    public int contador = 0;


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
        if (contador <= 10){
            this.contador = contador;
        }

    }
    public void setContadorCero(int contador){
        this.contador = 0;
    }
}
