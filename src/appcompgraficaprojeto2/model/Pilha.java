package appcompgraficaprojeto2.model;

public class Pilha {
    private NoPilha topo;
    public Pilha(){
        topo=null;
    }
    
    public void push(Ponto ponto){
        NoPilha novo=new NoPilha(ponto,topo);
        topo=novo;
    }
    public NoPilha pop(){
        NoPilha aux=null;
        if(topo!=null){
            aux=topo;
            topo=topo.getProx();
        }
        return aux;
    }
    public boolean isEmpty(){
        return topo==null;
    }
}
