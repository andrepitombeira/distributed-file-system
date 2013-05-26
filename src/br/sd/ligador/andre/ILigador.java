package br.sd.ligador.andre;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILigador extends Remote{
	
	public static final int PORTA = 7854;

    public String retornaServico(String busca) throws RemoteException;

}
