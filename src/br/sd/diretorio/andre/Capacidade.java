package br.sd.diretorio.andre;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Capacidade implements Serializable{
	
	private String url;
	private Set<Integer> idsPermitidos;
	private String nomeArquivo;
	
	public Capacidade(String url) {
		this.url = url;
		// TODO Auto-generated constructor stub
		this.idsPermitidos = new HashSet<Integer>();
		idsPermitidos.add(1);
		idsPermitidos.add(2);
		this.nomeArquivo = "imagem";
	}
	public Capacidade() {
		//this.url = url;
		// TODO Auto-generated constructor stub
		this.idsPermitidos = new HashSet<Integer>();
		idsPermitidos.add(1);
		idsPermitidos.add(2);
		this.nomeArquivo = "imagem";
	}




	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public boolean checarPermissao(Integer idCliente){
		if(idsPermitidos.contains(idCliente))
			return true;
		return false;
	}

        public void setIdsPermitidos(int id){
            idsPermitidos.add(id);

        }

	public String getArquivoURL(Integer idCliente){
		if(checarPermissao(idCliente))
			return url;
		System.out.println("Voce nao tem permissao");
		return null;
	}
}
