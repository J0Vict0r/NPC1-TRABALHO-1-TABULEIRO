package trabalho1;

public class Main {
    public static void main(String[] args) {
        JogoTabuleiro jogo = new JogoTabuleiro();
        jogo.selecionarModo(); // Permite escolher o modo no início do jogo
        jogo.configurarJogadores();
 
 
        System.out.println("\nJogadores adicionados:");
        for (Jogador jogador : jogo.getListaJogadores()) {
            System.out.println("- " + jogador.getNome() + " (" + jogador.getTipoJogador() + ")");
        }
 
 
        jogo.iniciarJogo();
    }
 }
 