/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sd.servicos.andre;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author andre
 */
public interface ServicoDiretorio extends Remote{

    public String lookup(String diretorio, String nome) throws RemoteException, Exception;

    public void addName(String ufid, String nome, int userId) throws RemoteException, Exception;

    public void unName(String diretorio, String nome) throws RemoteException, Exception;

    public void reName(String diretorio, String nome, String novoNome) throws RemoteException, Exception;

    public String[] getNames(String diretorio, String expressao) throws RemoteException, Exception;
		

}
