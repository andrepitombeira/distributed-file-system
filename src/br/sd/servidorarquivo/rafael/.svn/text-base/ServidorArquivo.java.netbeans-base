/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sd.servidorarquivo.rafael;

import br.sd.servicos.andre.ServicoArquivo;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author andre
 */
public class ServidorArquivo {

    public ServidorArquivo(){

        try {
          
            Registry r = LocateRegistry.getRegistry();
			r.bind("rmi://localhost:1097/ServidorArquivo", new ServidorArquivoImpl());

            /*ServicoArquivo sa = (ServicoArquivo) new ServidorArquivoImpl();
            Naming.bind("ServidorArquivo", sa);*/

            System.out.println("Servidor de arquivo está pronto.");
           
        } catch (Exception e) {
        }

        

    }

    public static void main(String[] args){
        new ServidorArquivo();
    }

}
