package questao01;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Player {

    private int fichas;
    private int aposta;
    private int pontos;
    private final Deck deck;
    private final ArrayList<Cards> handPlayer = new ArrayList<>();

    Player () {
        fichas = 100;
        aposta = 0;
        deck = Deck.getInstance();
        pontos = 0;
    }

    public void setAposta(int aposta) {
        this.aposta = aposta;
    }

    public int getAposta() {
        return aposta;
    }

    public void perdeuAposta (int aposta) {
        this.fichas -= aposta;
    }

    public void ganhouAposta (int aposta) {
        this.fichas += aposta;
    }

    public int getFichas() {
        return fichas;
    }

    public void zerarCartas () {
        handPlayer.clear();
    }

    public void atribuirCartas (int quantidade) {

        Random random = new Random();
        int randomN;

        for (int i = 0; i < quantidade; i++) {

            randomN = random.nextInt(deck.getListaCartas().size());

            handPlayer.add(deck.getListaCartas().get(randomN));
            deck.getListaCartas().remove(randomN);
        }
    }

    public void getHandPlayer() {
        for (Cards cards : handPlayer) {
            System.out.print(cards);
            System.out.print(" | ");
        }
    }

    public int totalPontosPlayer () {
        Value as = Value.AS;
        pontos = 0;

        if (!(handPlayer.isEmpty())) {

            for (Cards cards : handPlayer) {
                pontos += cards.getValor();
            }

            for (Cards cards : handPlayer) {
                if (Objects.equals(cards.getNome(), Value.AS.name()) && pontos > 21) {
                    pontos -= cards.getValor();

                    as.setValorCarta(1);
                    pontos += as.getValorCarta();
                }
            }
        }

        return pontos;
    }
}
