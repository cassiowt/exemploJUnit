package com.example;
public class Pessoa {

    private float altura;
    private float peso;

    public Pessoa(float altura, float pesso) {
        this.altura = altura;
        this.peso = pesso;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
}
