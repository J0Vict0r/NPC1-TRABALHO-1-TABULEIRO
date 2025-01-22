package trabalho1;

import java.util.List;

class Tabuleiro {
    private static final int casaVencedor = 40; // Define uma constante casaVencedor com o valor 40.
    private List<Integer> casasEspeciais;
    private List<Integer> casasSurpresa;
    private List<Integer> casasSorte;
    private List<Integer> casasEscolha;
    private List<Integer> casasMagicas;

    public Tabuleiro() { //inicializando as casas no construtor
        casasEspeciais = List.of(10, 25, 38);
        casasSurpresa = List.of(13);
        casasSorte = List.of(5, 15, 30);
        casasEscolha = List.of(17, 27);
        casasMagicas = List.of(20, 35);
    }

    public boolean CasaEspecial(int posicao) {
        return casasEspeciais.contains(posicao);
    }

    public boolean CasaSurpresa(int posicao) {
        return casasSurpresa.contains(posicao);
    }

    public boolean CasaSorte(int posicao) {
        return casasSorte.contains(posicao);
    }

    public boolean CasaEscolha(int posicao) {
        return casasEscolha.contains(posicao);
    }

    public boolean CasaMagica(int posicao) {
        return casasMagicas.contains(posicao);
    }

    public int getCasaVencedor() {
        return casaVencedor;
    }
}

