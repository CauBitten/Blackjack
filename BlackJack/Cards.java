package questao01;

import java.util.Objects;

public class Cards {

    private final int valor;
    private final char naipe;
    private final String nome;

    Cards (Value value, Suit suit) {
        valor = value.getValorCarta();
        naipe = suit.getNaipe();
        nome = value.name();
    }

    public int getValor () {
        return valor;
    }

    public String getNome() {
        return nome;
    }

    public String toString () {
        String result;

        if (Objects.equals(nome, Value.AS.name()) || Objects.equals(nome, Value.J.name()) || Objects.equals(nome, Value.K.name()) || Objects.equals(nome, Value.Q.name())) {
            result = String.format("%s-%c", nome.charAt(0), naipe);
        } else {
            result = String.format("%d-%c", valor, naipe);
        }

        return result;
    }
}
