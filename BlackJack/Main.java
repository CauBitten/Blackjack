package questao01;

public class Main {
    public static void main(String[] args) {

        Deck deck = Deck.getInstance();
        Player eu = new Player();
        Player banca = new Player();

        BlackJack blackJack = new BlackJack(deck);

        blackJack.theGame(banca, eu);

    }
}