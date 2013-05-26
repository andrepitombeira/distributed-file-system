/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sd.servicos.andre;

import br.sd.servidorarquivo.rafael.AtributoArquivo;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;

/**
 *
 * @author andre
 */
public interface ServicoArquivo extends Remote{

    public byte[] read(String ufid, int i, int n) throws RemoteException, Exception;

    public void write(String ufid, int i, byte[] data) throws RemoteException, Exception;

    public String create() throws RemoteException;

    public void truncate(String ufid, String l) throws RemoteException;

    public AtributoArquivo getAtributes(String ufid) throws RemoteException;

    public void setAtributes(String ufid, AtributoArquivo descritores) throws RemoteException;

}
