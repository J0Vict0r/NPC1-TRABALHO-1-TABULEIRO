package trabalho1;
import java.util.Random;

abstract class Jogador {
    protected String nome;
    protected String cor;
    protected int posicao;
    protected boolean pularProxima;
    protected int turno;

    public Jogador(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
        this.posicao = 0;
        this.pularProxima = false;
        this.turno = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public int getPosicao() {
        return posicao;
    }

    public int getTurno() {
        return turno;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public boolean isPularProxima() {
        return pularProxima;
    }

    public void setPularProxima(boolean pularProxima) {
        this.pularProxima = pularProxima;
    }

    public void setTurno(int turno){
        this.turno = turno;
    }

    public void mover(int casas) {
        this.posicao += casas;
        if (this.posicao > 40) {
            this.posicao = 40;
        }
    }

    public boolean qualVencedor() {
        return this.posicao >= 40;
    }

    public abstract int lancarDados(Random dado);

    public abstract String getTipoJogador();

    public void imprimirPosicao() {
        System.out.println("O jogador " + cor + " está na casa " + posicao);
    }

    public void trocaTipoJogador(Jogador novoJogador) {
        this.nome = novoJogador.getNome();
        this.cor = novoJogador.getCor();
        this.posicao = novoJogador.getPosicao();
        this.pularProxima = novoJogador.isPularProxima();
        this.turno = novoJogador.getTurno();
    }
}