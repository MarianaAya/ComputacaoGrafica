package appcompgraficaprojeto2.model;

public class NoPilha {
    private Ponto ponto;
    private NoPilha prox;
    public NoPilha(Ponto ponto,NoPilha prox){
        this.ponto=ponto;
        this.prox=prox;
    }
    public NoPilha(Ponto ponto,int pos){
        this(ponto,null);
    }

    public Ponto getPonto() {
        return ponto;
    }

    public void setNo(Ponto ponto) {
        this.ponto = ponto;
    }

    public NoPilha getProx() {
        return prox;
    }

    public void setProx(NoPilha prox) {
        this.prox = prox;
    }
}
