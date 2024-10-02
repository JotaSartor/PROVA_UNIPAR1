import java.util.Scanner;

public class AvaliacaoFilmes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quantidadeFilmes = 0;


        while (true) {
            System.out.print("Digite a quantidade de filmes que deseja avaliar: ");
            if (scanner.hasNextInt()) {
                quantidadeFilmes = scanner.nextInt();
                if (quantidadeFilmes > 0) {
                    break;
                } else {
                    System.out.println("Por favor, insira um número positivo.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.next();
            }
        }

        double[] avaliacoes = new double[quantidadeFilmes];
        int bons = 0;
        int ruins = 0;
        double soma = 0.0;


        for (int i = 0; i < quantidadeFilmes; i++) {
            while (true) {
                System.out.print("Digite a avaliação para o filme " + (i + 1) + " (0 a 10): ");
                if (scanner.hasNextDouble()) {
                    double nota = scanner.nextDouble();
                    if (nota >= 0 && nota <= 10) {
                        avaliacoes[i] = nota;
                        soma += nota;
                        if (nota >= 6) {
                            bons++;
                        } else {
                            ruins++;
                        }
                        break;
                    } else {
                        System.out.println("Alerta: Nota inválida! A avaliação deve estar entre 0 e 10.");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número entre 0 e 10.");
                    scanner.next();
                }
            }
        }


        double media = soma / quantidadeFilmes;


        System.out.printf("nMédia das avaliações: %.2f%n", media);
        System.out.println("Quantidade de filmes avaliados como bons (nota >= 6): " + bons);
        System.out.println("Quantidade de filmes avaliados como ruins (nota < 6): " + ruins);

        scanner.close();
    }
}


mport java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Candidato {
    private int codigo;
    private String nome;
    private int votos;

    public Candidato(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.votos = 0;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getVotos() {
        return votos;
    }

    public void votar() {
        votos++;
    }
}

public class SimulacaoEleicao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Candidato> candidatos = new ArrayList<>();


        candidatos.add(new Candidato(99, "Taffe"));
        candidatos.add(new Candidato(88, "Rodrigo"));
        candidatos.add(new Candidato(77, "Lucas"));
        candidatos.add(new Candidato(66, "Alessandro"));

        int numeroVotantes = 0;


        while (true) {
            System.out.print("Digite a quantidade de pessoas que irão votar: ");
            if (scanner.hasNextInt()) {
                numeroVotantes = scanner.nextInt();
                scanner.nextLine();
                if (numeroVotantes > 0) {
                    break;
                } else {
                    System.out.println("Por favor, insira um número positivo.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.next();
            }
        }

        System.out.println("\n--- Início da Votação ---");


        for (int i = 1; i <= numeroVotantes; i++) {
            System.out.println("\nVotante " + i + ":");
            exibirCandidatos(candidatos);
            int voto = -1;

            while (true) {
                System.out.print("Digite o código do candidato em que deseja votar: ");
                if (scanner.hasNextInt()) {
                    voto = scanner.nextInt();
                    scanner.nextLine();

                    Candidato candidatoEscolhido = buscarCandidatoPorCodigo(candidatos, voto);
                    if (candidatoEscolhido != null) {
                        candidatoEscolhido.votar();
                        System.out.println("Voto computado para " + candidatoEscolhido.getNome() + ".");
                        break;
                    } else {
                        System.out.println("Alerta: Código de candidato inválido! Por favor, tente novamente.");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número correspondente ao código de um candidato.");
                    scanner.next();
                }
            }
        }


        System.out.println("\n--- Resultados da Eleição ---");
        exibirResultados(candidatos);
        declararVencedor(candidatos);

        scanner.close();
    }


    private static void exibirCandidatos(List<Candidato> candidatos) {
        System.out.println("Lista de Candidatos:");
        for (Candidato candidato : candidatos) {
            System.out.println("Código: " + candidato.getCodigo() + " - Nome: " + candidato.getNome());
        }
    }


    private static Candidato buscarCandidatoPorCodigo(List<Candidato> candidatos, int codigo) {
        for (Candidato candidato : candidatos) {
            if (candidato.getCodigo() == codigo) {
                return candidato;
            }
        }
        return null;
    }


    private static void exibirResultados(List<Candidato> candidatos) {
        for (Candidato candidato : candidatos) {
            System.out.println(candidato.getNome() + ": " + candidato.getVotos() + " voto(s)");
        }
    }


    private static void declararVencedor(List<Candidato> candidatos) {
        int maxVotos = 0;
        for (Candidato candidato : candidatos) {
            if (candidato.getVotos() > maxVotos) {
                maxVotos = candidato.getVotos();
            }
        }

        List<Candidato> vencedores = new ArrayList<>();
        for (Candidato candidato : candidatos) {
            if (candidato.getVotos() == maxVotos) {
                vencedores.add(candidato);
            }
        }

        if (maxVotos == 0) {
            System.out.println("Nenhum voto foi computado.");
        } else if (vencedores.size() == 1) {
            System.out.println("Vencedor: " + vencedores.get(0).getNome() + " com " + maxVotos + " voto(s).");
        } else {
            System.out.print("Empate entre os candidatos: ");
            for (int i = 0; i < vencedores.size(); i++) {
                System.out.print(vencedores.get(i).getNome());
                if (i < vencedores.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(" com " + maxVotos + " voto(s) cada.");
        }
    }
}