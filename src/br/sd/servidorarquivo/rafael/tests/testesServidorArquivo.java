/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sd.servidorarquivo.rafael.tests;

import br.sd.servicos.andre.ServicoArquivo;
import br.sd.servidorarquivo.rafael.ServidorArquivoImpl;
import br.sd.servidorarquivo.rafael.utils.Utils;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class testesServidorArquivo {

    public static void main(String[] args) {

        ServicoArquivo s;
        try {
            s = new ServidorArquivoImpl();
            String ufid = s.create();

            byte[] data = "Rafael".getBytes();

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(s.getAtributes(ufid).getHoraCriacao()));
            System.out.println("Data de criação: " + Utils.formataData(cal));
            
            s.write(ufid, 0, data);
            cal.setTime(new Date(s.getAtributes(ufid).getHoraModificacao()));
            System.out.println("Data de escrita: " + Utils.formataData(cal));


            data = s.read(ufid, 3, data.length);
            cal.setTime(new Date(s.getAtributes(ufid).getHoraLeitura()));
            String horaLeitura = Utils.formataData(cal);

            cal.setTime(new Date(s.getAtributes(ufid).getHoraModificacao()));
            System.out.println("Data de leitura: " + horaLeitura + " Data de escrita anterior: " + Utils.formataData(cal));
            
            System.out.println("resultado 1: " + new String(data));
            
            s.write(ufid, data.length, data);
            data = s.read(ufid, 0, 2*data.length);
            System.out.println("resultado: " + new String(data));

            s.write(ufid, 2 * data.length + 2, data);
            data = s.read(ufid, 0, 2*data.length);
            System.out.println("resultado: " + new String(data));

        } catch (Exception ex) {
            Logger.getLogger(testesServidorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return;
    }
}