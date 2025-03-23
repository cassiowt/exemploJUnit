package com.example;

public class Baskhara {

    private float a, b, c;
    private float delta;


    public Baskhara(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public float calculaDelta() {
        this.delta = (this.b*this.b) - (4*this.a*this.c);
       return this.delta;
    }

    public float calculaRaizUm() {
        return (float) ((this.b + Math.sqrt(this.delta)) / 2 * this.a);
    }

    public float calculaRaizDois() {
        return (float) ((this.b - Math.sqrt(this.delta)) / 2 * this.a);
    }
}
