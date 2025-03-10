package br.com.fiap.moneyou.Enum;

public enum TipoCofrinho {
    RESGATE_IMEDIATO(0),
    RESGATE_365_DIAS(365);

    private final int diasParaResgate;

    TipoCofrinho(int diasParaResgate) {
        this.diasParaResgate = diasParaResgate;
    }

    public int getDiasParaResgate() {
        return diasParaResgate;
    }
}
