package questao01;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private static Deck instance;
    private final ArrayList<Cards> listaCartas = new ArrayList<>();

    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    public void criarDeck (int quantidade) {
        if (quantidade >= 2 && quantidade <= 10) {

            for (int i = 0; i < quantidade; i++) {

                for (Suit naipeCarta : Suit.values()) {

                    for (Value valorCarta : Value.values()) {

                        listaCartas.add(new Cards(valorCarta, naipeCarta));
                    }
                }
            }
        }
    }

    public List<Cards> getListaCartas() {
        return listaCartas;
    }
}
