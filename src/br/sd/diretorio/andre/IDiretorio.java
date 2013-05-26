package br.sd.diretorio.andre;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDiretorio extends Remote {
	
	public static final int PORTA = 7251;

	public String lookup(String nome) throws RemoteException;

        public void addName(String ufid, String nome, int userId) throws RemoteException, Exception;

        public void unName(String diretorio, String nome) throws RemoteException, Exception;

        public void reName(String diretorio, String nome, String novoNome) throws RemoteException, Exception;

        public String[] getNames(String diretorio, String expressao) throws RemoteException, Exception;


}
