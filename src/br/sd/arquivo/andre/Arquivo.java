/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sd.arquivo.andre;

import java.io.Serializable;

/**
 *
 * @author rafael
 */
public class Arquivo implements Serializable {

    public Arquivo() {
    }

    public Arquivo(AtributoFile atributos, byte[] dados) {
        this.atributos = atributos;
        this.dados = dados;
    }

    public AtributoFile getAtributos() {
        return atributos;
    }

    public void setAtributos(AtributoFile atributos) {
        this.atributos = atributos;
    }

    public byte[] getDados() {
        return dados;
    }

    public void setDados(byte[] dados) {
        this.dados = dados;
    }
    private AtributoFile atributos;
    private byte[] dados;
}
