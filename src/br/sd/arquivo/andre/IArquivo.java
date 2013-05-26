/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sd.arquivo.andre;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author andre
 */
public interface IArquivo extends Remote{
    
    public static final int PORTA = 1099;

    public byte[] read(String ufid, int i, int n) throws RemoteException, Exception;

    public void write(String ufid, int i, byte[] data) throws RemoteException, Exception;

    public String create() throws RemoteException;

    public void truncate(String ufid, String l) throws RemoteException;

    public AtributoFile getAtributes(String ufid) throws RemoteException;

    public void setAtributes(String ufid, AtributoFile descritores) throws RemoteException;

    

}
