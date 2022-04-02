
package appcompgraficaprojeto2.model;

public class Transformacao {
    public void translacao(Poligono poligono,int tx,int ty){
        double mat[][]=new double[3][3];
        mat[0][0]=1;
        mat[1][1]=1;
        mat[2][2]=1;
        mat[0][2]=tx;
        mat[1][2]=ty;
        double[][] matAcumulada=poligono.getMatAcumulada();
        double[][] novo=new double[3][3];
        double soma=0.0;
        for(int i=0; i< mat.length;i++){
            
            for (int j=0;j<mat.length;j++){
                soma=0.0;
                for(int b=0;b<3;b++){
                    soma+=mat[i][b]*matAcumulada[b][j];
                }
                novo[i][j]=soma;
            }
        }
        poligono.setMatAcumulada(novo);
    }
    public void rotacao(Poligono poligono,double grau){
        double mat[][]=new double[3][3];
        mat[0][0]=Math.cos(Math.toRadians(grau));
        mat[0][1]=-1*Math.sin(Math.toRadians(grau));
        mat[1][0]=Math.sin(Math.toRadians(grau));
        mat[1][1]=Math.cos(Math.toRadians(grau));
        mat[2][2]=1;
        mat[0][2]=0;
        mat[1][2]=0;
        double[][] matAcumulada=poligono.getMatAcumulada();
        double[][] novo=new double[3][3];
        double soma=0.0;
        for(int i=0; i< mat.length;i++){
            
            for (int j=0;j<mat.length;j++){
                soma=0.0;
                for(int b=0;b<3;b++){
                    soma+=mat[i][b]*matAcumulada[b][j];
                }
                novo[i][j]=soma;
            }
        }
        poligono.setMatAcumulada(novo);
    }
    public void escala(Poligono poligono, double sx,double sy){
        double mat[][]=new double[3][3];
        mat[0][0]=sx;
        mat[1][1]=sy;
        mat[2][2]=1.0;
        double[][] matAcumulada=poligono.getMatAcumulada();
        double[][] novo=new double[3][3];
        double soma=0.0;
        for(int i=0; i< mat.length;i++){
            
            for (int j=0;j<mat.length;j++){
                soma=0.0;
                for(int b=0;b<3;b++){
                    soma+=mat[i][b]*matAcumulada[b][j];
                }
                novo[i][j]=soma;
            }
        }
        poligono.setMatAcumulada(novo);
    }
    
    public void espelhamento(Poligono poligono, double ex,double ey){
        double mat[][]=new double[3][3];
        mat[0][0]=ex;
        mat[1][1]=ey;
        mat[2][2]=1.0;
        double[][] matAcumulada=poligono.getMatAcumulada();
        double[][] novo=new double[3][3];
        double soma=0.0;
        for(int i=0; i< mat.length;i++){
            
            for (int j=0;j<mat.length;j++){
                soma=0.0;
                for(int b=0;b<3;b++){
                    soma+=mat[i][b]*matAcumulada[b][j];
                }
                novo[i][j]=soma;
            }
        }
        poligono.setMatAcumulada(novo);
    }
    
    public void cisalhamento(Poligono poligono, double cx,double cy){
        double mat[][]=new double[3][3];
        mat[0][0]=1.0;
        mat[1][1]=1.0;
        mat[2][2]=1.0;
        mat[0][1]=cx;
        mat[1][0]=cy;
        double[][] matAcumulada=poligono.getMatAcumulada();
        double[][] novo=new double[3][3];
        double soma=0.0;
        for(int i=0; i< mat.length;i++){
            
            for (int j=0;j<mat.length;j++){
                soma=0.0;
                for(int b=0;b<3;b++){
                    soma+=mat[i][b]*matAcumulada[b][j];
                }
                novo[i][j]=soma;
            }
        }
        poligono.setMatAcumulada(novo);
    }
}
