package appcompgraficaprojeto2.model;

import java.util.ArrayList;
import java.util.List;

public class Poligono {
    private String nome;
    private List<Ponto> listPontos;
    private List<Ponto> listAtuais;
    private double[][] matAcumulada;
    private boolean preenchido;
    public Poligono(String nome) {
        this.nome = nome;
        this.listAtuais = new ArrayList<Ponto>();
        this.listPontos = new ArrayList<Ponto>();
        this.matAcumulada= new double[3][3];
        this.matAcumulada[0][0]= 1.0;
        this.matAcumulada[1][1]= 1.0;
        this.matAcumulada[2][2]= 1.0;
        this.preenchido=false;
    }
    
    public void calculaPontosAtuais(){
        this.listAtuais.clear();
        double[][] matPonto= new double[3][1];
        double[][] matNovo= new double[3][1];
        double soma=0.0;
        for(int pos=0;pos<this.listPontos.size();pos++){
            matPonto[0][0]=this.listPontos.get(pos).getX();
            matPonto[1][0]=this.listPontos.get(pos).getY();
            matPonto[2][0]=1;
            
            for(int i=0;i<this.matAcumulada.length;i++){
                soma=0;
                for(int j = 0; j< 3 ;j ++){
                    soma+=matAcumulada[i][j]*matPonto[j][0];
                }
                matNovo[i][0]=soma;
            }
            this.listAtuais.add(new Ponto((int)matNovo[0][0],(int)matNovo[1][0]));
        }
    }
    
    public int[] pontoMediio(){
        int[] pontoM = new int[2];
       double somaX=0,somaY=0;
       List<Ponto> listaPontoM;
       if(listAtuais.size()==0)
            listaPontoM=listPontos;
        else
            listaPontoM=listAtuais;
        
       for(int i=0;i<listaPontoM.size();i++){
           somaX+=listaPontoM.get(i).getX();
           somaY+=listaPontoM.get(i).getY();
       }
        somaX=(Double)somaX/listaPontoM.size();
        somaY=(Double)somaY/listaPontoM.size();
        pontoM[0]=(int)somaX;
        pontoM[1]=(int)somaY;
        return pontoM;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Ponto> getListPontos() {
        return listPontos;
    }

    public void setListPontos(List<Ponto> listPontos) {
        this.listPontos = listPontos;
    }

    public double[][] getMatAcumulada() {
        return matAcumulada;
    }

    public void setMatAcumulada(double[][] matNovo) {
        for(int i= 0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print("  "+matNovo[i][j]);
                this.matAcumulada[i][j]=matNovo[i][j];
            }
            System.out.println("");
        }
    }

    public List<Ponto> getListAtuais() {
        return listAtuais;
    }

    public void setListAtuais(List<Ponto> listAtuais) {
        this.listAtuais = listAtuais;
    }

    public boolean isPreenchido() {
        return preenchido;
    }

    public void setPreenchido(boolean preenchido) {
        this.preenchido = preenchido;
    }
    
    
    
}
