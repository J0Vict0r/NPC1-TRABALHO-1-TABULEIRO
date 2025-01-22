package trabalho1;

import java.util.Random;
import java.util.Scanner;

class JogadorAzarado extends Jogador {
    private Scanner scanner;

    public JogadorAzarado(String nome, String cor) {
        super(nome, cor);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int lancarDados(Random dado) {
        int dado1;
        int dado2;
        int soma;
        do {
            dado1 = dado.nextInt(6) + 1;
            dado2 = dado.nextInt(6) + 1;
            soma = dado1 + dado2;
        } while (soma > 6);

        System.out.println("\nDado 1: " + dado1 + "\tDado 2: "+ dado2);
        System.out.println("Resultado dos dados: " + soma);
        mover(soma);
        imprimirPosicao();
        if(qualVencedor()){
            return -1;
        }

        if(dado1 == dado2){
            System.out.print("Você tirou dados iguais! Pressione 1 para jogar novamente: ");
            boolean jogouNovamente = false;
            while (!jogouNovamente) {
                String resposta = scanner.nextLine();
                if (resposta.equals("1")) {
                    return lancarDados(dado);
                } else {
                    System.out.print("Entrada inválida. Tente novamente: ");
                }
            }
        }

        return soma;
    }

    @Override
    public String getTipoJogador() {
        return "Azarado";
    }
}