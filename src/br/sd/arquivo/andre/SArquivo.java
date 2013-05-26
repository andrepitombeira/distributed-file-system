/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sd.arquivo.andre;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author andre
 */
public class SArquivo implements IArquivo {

    private FileOutputStream fos;
    private FileInputStream fis;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public SArquivo() throws RemoteException {
        super();
    }

    public byte[] read(String ufid, int i, int n) throws RemoteException, Exception {
         // Stream to read file
        Arquivo arquivo = new Arquivo();
		try
        {
            arquivo = lerArquivo(ufid);


            byte[] aux = null;
            byte[] dataArquivo = arquivo.getDados();
            int ultimoIndice = dataArquivo.length;

            if(i < 0 || i > dataArquivo.length){
                throw new Exception("posicao invalida");
            }
            else{

                //atualiza atributos do arquivo
                long horaLeitura = System.currentTimeMillis();
                arquivo.getAtributos().setHoraLeitura(horaLeitura);
                arquivo.getAtributos().setHoraAtributo(horaLeitura);

                //ler os dados do arquivo
                ultimoIndice = dataArquivo.length < i + n ? dataArquivo.length : i + n;
                aux = new byte[ultimoIndice - i];
                for (int j = i, k = 0; j < ultimoIndice; j++, k++) {
                    aux[k] = dataArquivo[j];
                }

                //armazena em disco as alteracoes
                escreverArquivo(ufid, arquivo);

                System.out.println("Dados lidos com sucesso!");
                return aux;
            }
        }
        catch( Exception e ){
                e.printStackTrace( );
        }

        return null;
    }

    public void write(String ufid, int i, byte[] data) throws RemoteException, Exception {

            Arquivo arquivo = lerArquivo(ufid);
            byte[] aux = null;
            byte[] dataArquivo = arquivo.getDados();

            if(i < 0 || i > dataArquivo.length){
                throw new Exception("posicao invalida");
            }
            else{

                //escreve os dados
                int tamanho = dataArquivo.length > data.length + i ? dataArquivo.length : data.length + i;
                aux = new byte[tamanho];
                for (int j = 0; j < dataArquivo.length; j++) {
                    aux[j] = dataArquivo[j];
                }
                for (int j = i, k = 0; j < data.length + i; j++, k++) {
                    aux[j] = data[k];
                }

                //atualiza dados e atributos do arquivo
                long horaEscrita = System.currentTimeMillis();
                arquivo.getAtributos().setHoraModificacao(horaEscrita);
                arquivo.getAtributos().setHoraAtributo(horaEscrita);
                arquivo.getAtributos().setTamanho(tamanho);
                arquivo.setDados(aux);

                //armazena em disco as alteraçoes
                escreverArquivo(ufid,arquivo);
                System.out.println("Dados armazenados com sucesso!");
            }
    }

    public String create() throws RemoteException {
       String ufid = String.valueOf(System.currentTimeMillis());
       long time = System.currentTimeMillis();

       try
        {
            escreverArquivo(ufid,new Arquivo(new AtributoFile(0,  time, time, time, time),new byte[0]));
            System.out.println("Dados armazenados com sucesso!");
            return ufid;
        }
        catch( Exception e ){
                e.printStackTrace( );
        }

       return null;
    }

    public void truncate(String ufid, String l) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public AtributoFile getAtributes(String ufid) throws RemoteException {
        Arquivo arquivo = new Arquivo();
		try
        {
            arquivo = lerArquivo(ufid);
            return arquivo.getAtributos();
        }
        catch( Exception e ){
                e.printStackTrace( );
        }
        return null;
    }

    public void setAtributes(String ufid, AtributoFile descritores) throws RemoteException {
        Arquivo arquivo = new Arquivo();
		try
        {
            arquivo = lerArquivo(ufid);
            arquivo.setAtributos(descritores);
            escreverArquivo(ufid, arquivo);
        }
        catch( Exception e ){
                e.printStackTrace( );
        }
    }

    private String getDiretorioArquivos() throws IOException{
        String dir = new File(".").getCanonicalPath();
        return dir + "/ArquivosGerados";
    }


    private Arquivo lerArquivo(String ufid) throws Exception{
        String dir = getDiretorioArquivos();
        Arquivo arquivo = new Arquivo();
        try
        {
        fis = new FileInputStream(new File(dir, ufid));
            ois = new ObjectInputStream(fis);
            arquivo = (Arquivo)ois.readObject();
            ois.close();
            fis.close();

            return arquivo;
        }
        catch( Exception e ){
                e.printStackTrace( );
        }

        return null;
    }

    private void escreverArquivo(String ufid, Arquivo arquivo) throws Exception{
        String dir = getDiretorioArquivos();
        try{
            fos = new FileOutputStream(new File(dir, ufid));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(arquivo);
            oos.flush();
            oos.close();
            fos.flush();
            fos.close();
        }
        catch( Exception e ){
                e.printStackTrace( );
        }
    }

    
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        SArquivo arquivo = new SArquivo();
        IArquivo stub = (IArquivo) UnicastRemoteObject.exportObject(arquivo, 0);

        Registry registry = LocateRegistry.createRegistry(IArquivo.PORTA);

        registry.bind("arquivo", stub); //registra um objeto pelo nome

        System.out.println("Sistema de Arquivo Plano esta rodando esta rodando.");
    }
}
