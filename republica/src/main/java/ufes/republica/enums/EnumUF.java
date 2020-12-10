package ufes.republica.enums;

/**
 *
 * @author Lucas
 */
public enum EnumUF {
    AC("AC"),
    AL("AL"),
    AM("AN"),
    AP("AP"),
    BA("BA"),
    CE("CE"),
    DF("DF"),
    ES("ES"),
    GO("GO"),
    MA("MA"),
    MT("MT"),
    MS("MS"),
    MG("MG"),
    PA("PA"),
    PB("PB"),
    PR("PR"),
    PE("PE"),
    PI("PI"),
    RN("RN"),
    RS("RS"),
    RJ("RJ"),
    RO("RO"),
    RR("RR"),
    SC("SC"),
    SP("SP"),
    SE("SE"),
    TO("TO");
    
    private String estado;
    
    EnumUF(String estado) {
        this.estado = estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getValor() {
        return estado;
    }
    
}
