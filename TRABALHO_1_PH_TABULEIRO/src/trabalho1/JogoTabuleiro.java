package trabalho1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class JogoTabuleiro {
    private final Scanner scanner;
    private final List<Jogador> listaJogadores;
    private final Random random;
    private final TabuleiroDebug tabuleiro;
    private boolean modoDebug;  // Variável de modo de debug

    public JogoTabuleiro() {
        scanner = new Scanner(System.in);
        listaJogadores = new ArrayList<>();
        random = new Random();
        tabuleiro = new TabuleiroDebug();
    }

    // Resto do código continua sem mudanças.



   public List<Jogador> getListaJogadores() {
       return listaJogadores;
   }


   public void selecionarModo() {
       System.out.println("Escolha o modo de jogo:");
       System.out.println("1. Modo Debug");
       System.out.println("2. Modo Normal");
       System.out.print("Digite sua escolha: ");
       while (true) {
           String entrada = scanner.nextLine();
           if ("1".equals(entrada)) {
               modoDebug = true;
               System.out.println("\nModo Debug ativado.");
               break;
           } else if ("2".equals(entrada)) {
               modoDebug = false;
               System.out.println("\nModo Normal ativado.");
               break;
           } else {
               System.out.print("\nEntrada inválida. Digite 1 para Modo Debug ou 2 para Modo Normal: ");
           }
       }
   }


   public void configurarJogadores() {
       System.out.print("\nQuantos jogadores irão jogar? (2-6): ");
       int numJogadores = lerNumeroJogadores();


       for (int i = 0; i < numJogadores; i++) {
           adicionarJogador("Jogador " + (i + 1));
       }


       if (modoDebug) {
           configurarPosicoesDebug(); // Se o modo debug estiver ativo, configurar as posições manualmente
       }
   }


   private void configurarPosicoesDebug() {
       System.out.println("\nVocê está no modo Debug. Configure as posições iniciais dos jogadores.");
       for (Jogador jogador : listaJogadores) {
           System.out.print("\nDigite a posição inicial para " + jogador.getNome() + ": ");
           while (true) {
               String entrada = scanner.nextLine();
               try {
                   int posicao = Integer.parseInt(entrada);
                   if (posicao >= 0 && posicao < tabuleiro.getTamanhoTabuleiro()) {
                       jogador.setPosicao(posicao);
                       System.out.println(jogador.getNome() + " agora está na posição " + posicao + ".");
                       break;
                   } else {
                       System.out.print("\nPosição inválida. Digite um número entre 0 e " + (tabuleiro.getTamanhoTabuleiro() - 1) + ": ");
                   }
               } catch (NumberFormatException e) {
                   System.out.print("\nEntrada inválida. Digite um número válido: ");
               }
           }
       }
   }


   private int lerNumeroJogadores() {
       int numJogadores = 0;
       while (numJogadores < 2 || numJogadores > 6) {
           String entrada = scanner.nextLine();
           try {
               numJogadores = Integer.parseInt(entrada);
               if (numJogadores < 2 || numJogadores > 6) {
                   System.out.print("\nNúmero inválido. Escolha entre 2 e 6 jogadores: ");
               }
           } catch (NumberFormatException e) {
               System.out.print("\nEntrada inválida. Escolha entre 2 e 6 jogadores: ");
           }
       }
       return numJogadores;
   }

   
   public void adicionarJogador(String nome) {
       if (listaJogadores.size() >= 6) {
           System.out.println("\nNúmero máximo de jogadores alcançado.");
           return;
       }


       String cor = perguntarCorJogador(nome);
       Jogador jogador = criarJogadorAleatorio(nome, cor);


       listaJogadores.add(jogador);
       System.out.println(nome + " adicionado com sucesso como um jogador " + jogador.getTipoJogador() + ".");
   }

   
   private Jogador criarJogadorAleatorio(String nome, String cor) {
       Jogador jogador;
       do {
           int tipo = random.nextInt(3); // Gera um número aleatório entre 0 e 2
           jogador = switch (tipo) {
               case 0 -> new JogadorNormal(nome, cor);
               case 1 -> new JogadorAzarado(nome, cor);
               case 2 -> new JogadorSortudo(nome, cor);
               default -> null;
           };
       } while (listaJogadores.size() == 1 && jogador != null && jogador.getClass().equals(listaJogadores.get(0).getClass()));


       return jogador;
   }

   
   private String perguntarCorJogador(String nomeJogador) {
       String[] coresValidas = {"Azul", "Verde", "Amarelo", "Roxo", "Rosa", "Vermelho"};
       String corEscolhida = null;


       while (corEscolhida == null) {
           System.out.println("\nEscolha uma cor para " + nomeJogador + ":");
           for (int i = 0; i < coresValidas.length; i++) {
               System.out.println((i + 1) + ". " + coresValidas[i]);
           }
           System.out.print("\nDigite o número correspondente à cor: ");
           corEscolhida = processarEscolhaCor(coresValidas);
       }
       return corEscolhida;
   }

   
   private String processarEscolhaCor(String[] coresValidas) {
       String entrada = scanner.nextLine();
       try {
           int indice = Integer.parseInt(entrada) - 1;
           if (indice >= 0 && indice < coresValidas.length) {
               String corEscolhida = coresValidas[indice];
               if (listaJogadores.stream().noneMatch(j -> j.getCor().equals(corEscolhida))) {
                   return corEscolhida;
               } else {
                   System.out.println("\nEsta cor já foi escolhida.");
               }
           } else {
               System.out.println("\nNúmero inválido.");
           }
       } catch (NumberFormatException e) {
           System.out.println("\nEntrada inválida.");
       }
       return null;
   }

   
   public void iniciarJogo() {
       System.out.println("\n\nIniciando o jogo...");
       pausarExecucao(1000);


       boolean jogoContinua = true;
       int turno = 1;


       while (jogoContinua) {
           System.out.println("\n---------------------------------------------------------------");
           System.out.println("\nTURNO " + turno + ":");


           for (Jogador jogador : listaJogadores) {
               if (jogador.isPularProxima()) {
                   System.out.println("\n\n***** Jogador " + jogador.getCor() + " (" + jogador.getTipoJogador() + ") está pulando este turno. *****");
                   jogador.setPularProxima(false);
                   continue;
               }


               System.out.println("\n\n***** Jogador " + jogador.getCor() + " (" + jogador.getTipoJogador() + ") está jogando! *****");


               jogoContinua = processarTurnoJogador(jogador);
               if (!jogoContinua) break;
           }
           turno++;
       }
       System.out.println("\n\nFim do jogo!\n");
   }

   private boolean processarTurnoJogador(Jogador jogador) {
       boolean lancouDados = false;
       while (!lancouDados) {
           System.out.print("\nPressione 1 para rolar os dados: ");
           String resposta = scanner.nextLine();


           if ("1".equals(resposta)) {
               int resultadoDado = jogador.lancarDados(random);
               if (resultadoDado == -1) {
                   System.out.println("\n\nJogador " + jogador.getCor() + " venceu o jogo!\n");
                   listaJogadores.forEach(j -> System.out.println("\nA quantidade de turnos do jogador " + j.getCor() + " foi " + j.getTurno()));
                   return false;
               } else {
                   jogador.setTurno(jogador.getTurno() + 1);
                   aplicarRegrasEspeciais(jogador);
               }
               lancouDados = true;
           }
       }
       return true;
   }

   
   private void aplicarRegrasEspeciais(Jogador jogador) {
       int posicao = jogador.getPosicao();


       if (tabuleiro.CasaEspecial(posicao)) {
           System.out.println("Jogador " + jogador.getCor() + " caiu em uma casa especial e vai pular o próximo turno.");
           jogador.setPularProxima(true);
       }


       if (tabuleiro.CasaSurpresa(posicao)) {
           System.out.println("Jogador " + jogador.getCor() + " caiu em uma casa surpresa! Trocará de tipo de jogador.");
           trocarTipoJogador(jogador);
       }


       if (tabuleiro.CasaSorte(posicao)) {
           if (!(jogador instanceof JogadorAzarado)) {
               System.out.println("Jogador " + jogador.getCor() + " caiu em uma casa de sorte! Avança 3 casas.");
               jogador.mover(3);
               jogador.imprimirPosicao();
           } else {
               System.out.println("Jogador " + jogador.getCor() + " caiu em uma casa de sorte, mas é Azarado. Não avança 3 casas.");
           }
       }


       if (tabuleiro.CasaEscolha(posicao)) {
           System.out.println("Jogador " + jogador.getCor() + " caiu em uma casa de escolha! Escolha um jogador para voltar ao início.");
           escolherJogadorParaVoltar(jogador);
       }


       if (tabuleiro.CasaMagica(posicao)) {
           System.out.println("Jogador " + jogador.getCor() + " caiu em uma casa mágica! Troca de lugar com o jogador mais atrás.");
           trocarLugarComUltimo(jogador);
       }
   }

   
   private void trocarTipoJogador(Jogador jogador) {
       Jogador novoJogador;
       int tipo = random.nextInt(3); // Gera um número aleatório entre 0 e 2
       novoJogador = switch (tipo) {
           case 0 -> new JogadorNormal(jogador.getNome(), jogador.getCor());
           case 1 -> new JogadorAzarado(jogador.getNome(), jogador.getCor());
           case 2 -> new JogadorSortudo(jogador.getNome(), jogador.getCor());
           default -> null;
       };


       if (novoJogador != null) {
           novoJogador.setPosicao(jogador.getPosicao());
           novoJogador.setTurno(jogador.getTurno());


           int index = listaJogadores.indexOf(jogador);
           listaJogadores.set(index, novoJogador);
           System.out.println("Jogador " + jogador.getCor() + " agora é um jogador " + novoJogador.getTipoJogador() + ".");
       }
   }

   
   private void escolherJogadorParaVoltar(Jogador jogadorAtual) {
       boolean escolhaValida = false;


       while (!escolhaValida) {
           System.out.println("\nEscolha um jogador para voltar ao início:");
           for (int i = 0; i < listaJogadores.size(); i++) {
               Jogador jogador = listaJogadores.get(i);
               if (!jogador.equals(jogadorAtual)) {
                   System.out.println((i + 1) + ". " + jogador.getCor() + " (" + jogador.getTipoJogador() + ")");
               }
           }
           System.out.print("\nDigite o número correspondente ao jogador: ");


           String entrada = scanner.nextLine();
           try {
               int indice = Integer.parseInt(entrada) - 1;
               if (indice >= 0 && indice < listaJogadores.size() && !listaJogadores.get(indice).equals(jogadorAtual)) {
                   listaJogadores.get(indice).setPosicao(0);
                   listaJogadores.get(indice).imprimirPosicao();
                   escolhaValida = true;
               } else {
                   System.out.println("\nEscolha inválida.");
               }
           } catch (NumberFormatException e) {
               System.out.println("\nEntrada inválida.");
           }
       }
   }
   
   
   private void trocarLugarComUltimo(Jogador jogadorAtual) {
       Jogador jogadorMaisAtras = listaJogadores.stream()
               .min((j1, j2) -> Integer.compare(j1.getPosicao(), j2.getPosicao()))
               .orElse(null);


       if (jogadorMaisAtras != null) {
           int posicaoTemp = jogadorAtual.getPosicao();
           jogadorAtual.setPosicao(jogadorMaisAtras.getPosicao());
           jogadorMaisAtras.setPosicao(posicaoTemp);


           System.out.println("\nJogador " + jogadorAtual.getCor() + " trocou de lugar com o jogador mais atrás.");
       }
   }


   private void pausarExecucao(int milissegundos) {
       try {
           Thread.sleep(milissegundos);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }
}

