/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sd.servidorarquivo.rafael;

import java.io.Serializable;

/**
 *
 * @author rafael
 */
public class AtributoArquivo implements Serializable{
    private int tamanho;
    private long horaCriacao;
    private long horaModificacao;
    private long horaLeitura;
    private long horaAtributo;

    public AtributoArquivo(int tamanho, long horaCriacao, long horaModificacao, long horaLeitura, long horaAtributo) {
        this.tamanho = tamanho;
        this.horaCriacao = horaCriacao;
        this.horaModificacao = horaModificacao;
        this.horaLeitura = horaLeitura;
        this.horaAtributo = horaAtributo;
    }

    public long getHoraAtributo() {
        return horaAtributo;
    }

    public void setHoraAtributo(long horaAtributo) {
        this.horaAtributo = horaAtributo;
    }

    public long getHoraCriacao() {
        return horaCriacao;
    }

    public void setHoraCriacao(long horaCriacao) {
        this.horaCriacao = horaCriacao;
    }

    public long getHoraLeitura() {
        return horaLeitura;
    }

    public void setHoraLeitura(long horaLeitura) {
        this.horaLeitura = horaLeitura;
    }

    public long getHoraModificacao() {
        return horaModificacao;
    }

    public void setHoraModificacao(long horaModificacao) {
        this.horaModificacao = horaModificacao;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    
}
