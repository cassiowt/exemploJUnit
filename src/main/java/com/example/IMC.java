package com.example;
public class IMC {
    public float calculaIMC(Pessoa pessoa) throws Exception {


            return pessoa.getPeso()/( pessoa.getAltura() * pessoa.getAltura());

    }
}
