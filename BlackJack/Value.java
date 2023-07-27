package questao01;

public enum Value {

    DOIS(2), TRES(3), QUATRO(4), CINCO(5), SEIS(6), SETE(7),
    OITO(8), NOVE(9), DEZ(10), J(10), K(10), Q(10), AS(11);

    public int valorCarta;
    Value (int valor) {
        valorCarta = valor;
    }

    public void setValorCarta (int valor) {
        valorCarta = valor;
    }

    public int getValorCarta() {
        return valorCarta;
    }
}
