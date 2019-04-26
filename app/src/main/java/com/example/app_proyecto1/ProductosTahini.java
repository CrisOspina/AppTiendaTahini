package com.example.app_proyecto1;

public class ProductosTahini
{
    private String nombre;
    private String info;
    private int foto;

    //Constructor
    public ProductosTahini(String nombre, String info, int foto)
    {
        this.nombre = nombre;
        this.info = info;
        this.foto = foto;
    }

    public ProductosTahini()
    {

    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public int getFoto()
    {
        return foto;
    }

    public void setFoto(int foto)
    {
        this.foto = foto;
    }
}
