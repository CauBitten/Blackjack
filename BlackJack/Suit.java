package questao01;

public enum Suit {

    COPAS('C'), OUROS('O'),
    PAUS('P'), ESPADAS('E');

    public final char naipe;

    Suit (char nap) {
        naipe = nap;
    }

    public char getNaipe() {
        return naipe;
    }
}
