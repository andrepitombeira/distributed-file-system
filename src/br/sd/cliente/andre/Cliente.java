package br.sd.cliente.andre;

import br.sd.arquivo.andre.AtributoFile;
import br.sd.arquivo.andre.IArquivo;
import br.sd.diretorio.andre.IDiretorio;
import br.sd.diretorio.andre.Capacidade;
import br.sd.ligador.andre.ILigador;
import br.ufc.excecoes.andre.AcessoInvalidoException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Cliente {

    private static Integer meuId = 1;

    private Cliente() {
    }

    public static void main(String[] args) {

        try {
            //Ligador
            Registry registryLigador = LocateRegistry.getRegistry(ILigador.PORTA);

            ILigador ligador = (ILigador) registryLigador.lookup("Ligador");

            String servicoDiretorio = ligador.retornaServico("diretorio1");

            String servicoArquivo = ligador.retornaServico("arquivo1");

            String[] s = servicoDiretorio.split(":");

            String[] s1 = servicoArquivo.split(":");

            //Serviço de Diretorio
            Registry registryDiretorio = LocateRegistry.getRegistry(Integer.parseInt(s[1]));

            IDiretorio diretorio = (IDiretorio) registryDiretorio.lookup("diretorio");


            //Serviço de Arquivo Plano
            Registry registryArquivo = LocateRegistry.getRegistry(Integer.parseInt(s1[1]));

            IArquivo arquivo = (IArquivo) registryArquivo.lookup("arquivo");

            //Operações sobre diretorio
            String uFid = diretorio.lookup("arquivo");
            System.out.println("UFID:" + uFid);


            //Cria capacidade
            Capacidade cap = new Capacidade(uFid);





            //Operações sobre o sistema de arquivos planos
            String ufid = arquivo.create();
            diretorio.addName(ufid, "queijo", 10);
            System.out.println(ufid);

            if (cap.checarPermissao(meuId)) {
                arquivo.write(ufid, 0, "andre".getBytes());
                System.out.println(ufid + new String(arquivo.read(ufid, 2, 3)));
            } else {
                throw new AcessoInvalidoException("O usuario nao pode acessar o arquivo");
            }

            //AtributoFile af = arquivo.getAtributes(ufid);

            String ufid2 = diretorio.lookup("queijo");
            System.out.println(ufid2);

            boolean exit = false;

            Scanner scanner = new Scanner(System.in);
            String input;
            int option;
            String nome;
            int offset;
            int tamanho;

            System.out.println("Servico de arquivos");
            System.out.println("Digite o id de usuario: ");
            int id = scanner.nextInt();

            do {
                System.out.println("Servico de arquivos");
                System.out.println("Escolha uma opção: ");
                System.out.println("1.Criar Arquivo");
                System.out.println("2.Ler Arquivo");
                System.out.println("3.Escrever no Arquivo");
                System.out.println("4.Ler atributos de arquivo");
                System.out.println("5.Alterar atributos de Arquivo");
                System.out.println("6.Sair");
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Digite o nome do arquivo: ");
                        nome = scanner.next();

                        System.out.println("Digite o conteudo do arquivo: ");
                        String conteudo = scanner.next();

                        if (cap.checarPermissao(id)) {
                            //Operações sobre o sistema de arquivos planos
                            ufid = arquivo.create();
                            diretorio.addName(ufid, nome, 1);
                            arquivo.write(ufid, 0, conteudo.getBytes());
                            System.out.println("Arquivo: " + nome + " criado com sucesson\n");
                        } else {
                            System.out.println("Erro: O usuario nao pode criar arquivos.\n");
                        }
                        break;
                    case 2:
                        System.out.println("Digite o nome do arquivo: ");
                        nome = scanner.next();

                        System.out.println("Digite o offset de leitura: ");
                        offset = scanner.nextInt();

                        System.out.println("Digite a quantidade de elementos: ");

                        tamanho = scanner.nextInt();

                        ufid = diretorio.lookup(nome);
                        System.out.println("Conteudo lido: " + new String(arquivo.read(ufid, offset, tamanho)));
                        break;
                    case 3:
                        System.out.println("Digite o nome do arquivo: ");
                        nome = scanner.next();

                        System.out.println("Digite o offset de escrita: ");
                        offset = scanner.nextInt();

                        System.out.println("Digite o conteudo a ser escrito: ");

                        conteudo = scanner.next();

                        ufid = diretorio.lookup(nome);
                        arquivo.write(ufid, offset, conteudo.getBytes());

                        System.out.println("Arquivo alterado com sucesso");
                        break;
                    case 4:
                        System.out.println("Digite o nome do arquivo: ");
                        nome = scanner.next();

                        ufid = diretorio.lookup(nome);
                        AtributoFile atributos = arquivo.getAtributes(ufid);
                        System.out.println("Timestamp de criacao: " + atributos.getHoraCriacao());
                        System.out.println("Timestamp de leitura: " + atributos.getHoraLeitura());
                        System.out.println("Timestamp de Escrita: " + atributos.getHoraModificacao());
                        System.out.println("Tamanho do arquivo: " + atributos.getTamanho());

                        //System.out.println("Data de criacao: " + new Date(atributos.getHoraCriacao()).toString());

                        break;
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                        break;
                }


            } while (exit != true);


        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
