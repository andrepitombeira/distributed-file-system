package br.sd.diretorio.andre;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

public class Diretorio implements IDiretorio {

    private Hashtable<String, String> servicosTable;
    private Capacidade capacidade;

    public Diretorio() {
        this.servicosTable = new Hashtable<String, String>();
        capacidade = new Capacidade();
        // Capacidade uFid = new Capacidade("http://www.ufc.br/portal/templates/ol_lipe_2/images/brasao_ufc.jpg");;
        this.servicosTable.put("arquivo", "1");
    }

    @Override
    public String lookup(String nomeTextual) throws RemoteException {

        String uFid = "";
        uFid = this.servicosTable.get(nomeTextual.trim());
        return uFid;
    }

    public void addName(String ufid, String nome, int userId) throws RemoteException, Exception {
        this.servicosTable.put(nome, ufid);
        capacidade.setIdsPermitidos(userId);
    }

    public Capacidade getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Capacidade capacidade) {
        this.capacidade = capacidade;
    }

    public void unName(String diretorio, String nome) throws RemoteException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void reName(String diretorio, String nome, String novoNome) throws RemoteException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[] getNames(String diretorio, String expressao) throws RemoteException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        Diretorio diretorio = new Diretorio();
        IDiretorio stub = (IDiretorio) UnicastRemoteObject.exportObject(diretorio, 0);

        Registry registry = LocateRegistry.createRegistry(IDiretorio.PORTA);

        registry.bind("diretorio", stub); //registra um objeto pelo nome

        System.out.println("Diretorio esta rodando.");
    }
}
