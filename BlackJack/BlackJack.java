package questao01;

import java.util.Objects;
import java.util.Scanner;

public class BlackJack {

    Scanner scanner = new Scanner(System.in);

    private final Deck deck;

    BlackJack (Deck deck) {
        this.deck = deck;
    }

    public void theGame (Player banca, Player player) {

        int ans = 0;
        int nDisputa = 1;
        int fichasIniciais = player.getFichas();
        String hitOrStay;

        playerSetQtdDeck();

        System.out.println("Iniciando partida...");
        System.out.println("=============================================");

        while (ans != -1 && player.getFichas() > 0 && deck.getListaCartas().size() > 0) {

            int apertouS = 0;

            System.out.printf("Disputa n. %d\n", nDisputa);
            System.out.printf("Quantidade de cartas no montante: %d\n", deck.getListaCartas().size());
            System.out.printf("Quantidade de fichas: %d\n", player.getFichas());
            ans = playerApostando();

                if (player.getFichas() >= ans) {

                        player.setAposta(ans);

                        banca.zerarCartas();
                        player.zerarCartas();

                        banca.atribuirCartas(1);
                        player.atribuirCartas(2);

                        while (player.totalPontosPlayer() <= 21 && banca.totalPontosPlayer() <= 21 && ans != -1 && apertouS == 0) {

                            printarGameStats(player, banca);

                            System.out.println("\nO que você deseja fazer?");
                            System.out.println("(H) HIT - para pedir mais cartas ou");
                            System.out.println("(S) STAY – para parar e deixar a banca jogar");
                            System.out.print("Digite: ");
                            hitOrStay = scanner.next();

                            if (Objects.equals(hitOrStay.charAt(0), 'H') || Objects.equals(hitOrStay.charAt(0), 'h')) {
                                if (deck.getListaCartas().size() > 0) {
                                    player.atribuirCartas(1);
                                }

                            } else if (Objects.equals(hitOrStay.charAt(0), 'S') || Objects.equals(hitOrStay.charAt(0), 's')) {
                                while (banca.totalPontosPlayer() <= player.totalPontosPlayer() && deck.getListaCartas().size() > 0) {
                                    banca.atribuirCartas(1);
                                    printarGameStats(player, banca);

                                    apertouS++;

                                }

                            } else {
                                System.out.println("Digite uma opção válida!");

                            }

                            // Player estourou
                            if (player.totalPontosPlayer() > 21) {
                                printarGameStats(player, banca);
                                System.out.printf("\nRESULTADO: A banca ganhou e você perdeu %d fichas por estourar!\n", player.getAposta());

                                player.perdeuAposta(player.getAposta());

                                // Banca estourou
                            } else if (banca.totalPontosPlayer() > 21) {
                                System.out.println("\nRESULTADO: A banca estourou 21 pontos!");
                                System.out.printf("Você ganhou e recebeu %d fichas!\n", player.getAposta());

                                player.ganhouAposta(player.getAposta());

                                // Banca ganhou ao player apertar S
                            } else if (apertouS > 0 && banca.totalPontosPlayer() > player.totalPontosPlayer() && banca.totalPontosPlayer() <= 21 && player.totalPontosPlayer() <= 21) {
                                System.out.printf("\nRESULTADO: A banca ganhou e você perdeu %d fichas!\n", player.getAposta());

                                player.perdeuAposta(player.getAposta());

                                // Player ganhou ao aperto do S
                            } else if (apertouS > 0 && banca.totalPontosPlayer() < player.totalPontosPlayer() && player.totalPontosPlayer() <= 21 && banca.totalPontosPlayer() <= 21) {
                                System.out.printf("\nRESULTADO: Você ganhou e recebeu %d fichas!\n", player.getAposta());

                                player.ganhouAposta(player.getAposta());

                                // Empate
                            } else if (apertouS > 0 && banca.totalPontosPlayer() == player.totalPontosPlayer() && player.totalPontosPlayer() <= 21 && banca.totalPontosPlayer() <= 21) {
                                System.out.println("Não houve vencedor!");

                            }
                        }

                    player.setAposta(0);

                    } else {
                        System.out.println("Você não tem fichas suficientes para apostar essa quantidade!");
                }
                nDisputa++;

                System.out.print("\n");
        }

        int difFichas = player.getFichas() - fichasIniciais;

        // Se o player perdeu fichas no final
        if (difFichas < 0 && player.getFichas() > 0 && ans == -1) {
            System.out.printf("Que pena, você perdeu %d fichas. Tente outra vez!\n", difFichas*(-1));

            // Se o player ganhou fichas no final
        } else if (difFichas > 0 && player.getFichas() > 0 && ans == -1) {
            System.out.printf("PARABÉNS!!! Você ganhou %d fichas\n", difFichas);

            // Se o player nem ganhou nem perdeu
        } else if (difFichas == 0 && player.getFichas() > 0 && ans == -1) {
            System.out.println("Parece que alguém está com medo de arriscar!\n");

        }

        // Player perdeu todas as fichas
        if (player.getFichas() <= 0 && ans >= 0) {
            System.out.println("Você perdeu todas as fichas! Tente outra vez com mais esperteza!");

        }

        // As cartas dos baralhos acabaram
        if (deck.getListaCartas().size() == 0) {
            System.out.println("As cartas acabaram!");

            /* Imprimindo o placar se as cartas acabarem */
            // Se o player perdeu fichas no final
            if (difFichas < 0 && player.getFichas() > 0) {
                System.out.printf("Que pena, você perdeu %d fichas. Tente outra vez!\n", difFichas*(-1));

                // Se o player ganhou fichas no final
            } else if (difFichas > 0 && player.getFichas() > 0) {
                System.out.printf("PARABÉNS!!! Você ganhou %d fichas\n", difFichas);

                // Se o player nem ganhou nem perdeu
            } else if (difFichas == 0 && player.getFichas() > 0) {
                System.out.println("Parece que alguém está com medo de arriscar!\n");

            }
        }
    }

    public void playerSetQtdDeck () {
        int quantDecks = 0;

        while (quantDecks < 2 || quantDecks > 10) {
            System.out.print("Quantos decks você deseja utilizar nessa partida: ");
            quantDecks = scanner.nextInt();

            if (quantDecks < 2 || quantDecks > 10) {
                System.out.println("A quantidade deve ser maior ou igual a que 2 e menor ou igual a que 10!");
            }
        }

        deck.criarDeck(quantDecks);
    }

    public int playerApostando () {
        int ans;

        do {
            System.out.print("Digite sua aposta (mínimo de 2) ou ‘-1’ para sair do jogo: ");
            ans = scanner.nextInt();

            if (ans < 2 && ans >= 0) {
                System.out.println("Por favor atente-se ao número mínimo e responda.");
            }

            if (ans == -1) {
                System.out.println("\nVocê saiu do jogo!");
                System.out.println("Somatório final:");
            }
        } while (ans < 2 && ans != -1);

        return ans;
    }

    public void printarGameStats (Player player, Player banca) {
        System.out.println("\nEssas são as cartas da banca:");
        banca.getHandPlayer();
        System.out.printf("Total de pontos: %d\n", banca.totalPontosPlayer());

        System.out.println("-----X-----");

        System.out.println("Essas são as suas cartas:");
        player.getHandPlayer();
        System.out.printf("Total de pontos: %d\n", player.totalPontosPlayer());
    }
}
