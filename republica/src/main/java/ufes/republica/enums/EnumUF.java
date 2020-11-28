package ufes.republica.enums;

/**
 *
 * @author Lucas
 */
public enum EnumUF {
    AC(1),
    AL(2),
    AM(3),
    AP(4),
    BA(5),
    CE(6),
    DF(7),
    ES(8),
    GO(9),
    MA(10),
    MT(11),
    MS(12),
    MG(13),
    PA(14),
    PB(15),
    PR(16),
    PE(17),
    PI(18),
    RN(19),
    RS(20),
    RJ(21),
    RO(22),
    RR(23),
    SC(24),
    SP(25),
    SE(26),
    TO(27);
    
    private int valor;
    
    EnumUF(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
}
