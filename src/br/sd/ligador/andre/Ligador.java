package br.sd.ligador.andre;

import br.sd.arquivo.andre.IArquivo;
import br.sd.diretorio.andre.IDiretorio;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;





public class Ligador implements ILigador{

	//TABELA HASH MAPEANDO NOME DO SERVIÇO E ENDEREÇO
	private Hashtable<String, String> servicosTable;

	public Ligador() {
		this.servicosTable = new Hashtable<String, String>();
		this.servicosTable.put("diretorio1", "localhost:" + IDiretorio.PORTA);
		this.servicosTable.put("arquivo1", "localhost:" + IArquivo.PORTA);
		this.servicosTable.put("diretorio3", "Compute");
	}
	
	public String retornaServico(String busca) throws RemoteException {
		String res = "";

		res = this.servicosTable.get(busca.trim());
		if (res != null)
			return res;
		return "nack";
		
	}
	
	public  static void main(String args[]){
        try {
            
            Ligador ligador = new Ligador();
            
            ILigador stubMestre = (ILigador)UnicastRemoteObject.exportObject(ligador, 0);

            Registry registry = LocateRegistry.createRegistry(ILigador.PORTA);

            registry.bind("Ligador", stubMestre); //mudei de mestre p ligador

            System.out.println("Ligador esta rodando");
            
        } catch (AlreadyBoundException ex) {
             ex.printStackTrace();
        } catch (AccessException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
	

}
