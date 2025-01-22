package trabalho1;

public class TabuleiroDebug {
    private final int tamanhoTabuleiro;
 
 
    public TabuleiroDebug() {
        // Defina o tamanho do tabuleiro, por exemplo, 30 casas
        this.tamanhoTabuleiro = 40;
    }
 
 
    public int getTamanhoTabuleiro() {
        return tamanhoTabuleiro;
    }
 
 
    // Métodos para verificar se o jogador está em uma casa especial
    public boolean CasaEspecial(int posicao) {
        // Lógica para verificar casa especial
        return posicao == 10 || posicao == 25 || posicao == 38;
    }
 
 
    public boolean CasaSurpresa(int posicao) {
        // Lógica para verificar casa surpresa
        return posicao == 13;
    }
 
 
    public boolean CasaSorte(int posicao) {
        // Lógica para verificar casa de sorte
        return posicao == 5 || posicao == 15 || posicao == 30 ;
    }
 
 
    public boolean CasaEscolha(int posicao) {
        // Lógica para verificar casa de escolha
        return posicao == 17 || posicao == 27 ;
    }
 
 
    public boolean CasaMagica(int posicao) {
        // Lógica para verificar casa mágica
        return posicao == 20 || posicao == 35 ;
    }
 }
 