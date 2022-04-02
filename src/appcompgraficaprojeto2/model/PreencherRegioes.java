package appcompgraficaprojeto2.model;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

public class PreencherRegioes {
    public BufferedImage FloodFill(BufferedImage image,int x, int y) {
        WritableRaster raster=image.getRaster();
        Pilha p = new Pilha();
        NoPilha no = null;
        double pixel[]={0,0,0,0}, pixelFundo[] = {0,0,0,0} ;
        raster.getPixel(x, y, pixelFundo); //pegar a cor do pixel do fundo
        p.push(new Ponto(x,y));
        pintarPixel(image, raster,x,y,pixel,0,0,0);
        while(!p.isEmpty()) {
            
            no = p.pop();
            x=no.getPonto().getX();
            y=no.getPonto().getY();
            if(x+1<image.getWidth()) {
                raster.getPixel(x+1, y, pixel);
                if(pixel[0]==pixelFundo[0] && pixel[1]==pixelFundo[1] && pixel[2]==pixelFundo[2]) {
                    p.push(new Ponto(x+1,y));
                    pintarPixel(image, raster,x+1,y,pixel,0,0,0);
                }
            
            }
            if(x-1>=0) {
                raster.getPixel(x-1, y, pixel);
                if(pixel[0]==pixelFundo[0] && pixel[1]==pixelFundo[1] && pixel[2]==pixelFundo[2]) {
                    pintarPixel(image, raster,x-1,y,pixel,0,0,0);
                    p.push(new Ponto(x-1,y));
                }
            }
            if(y+1<image.getHeight()) {
                raster.getPixel(x, y+1, pixel);
                if(pixel[0]==pixelFundo[0] && pixel[1]==pixelFundo[1] && pixel[2]==pixelFundo[2]) {
                    pintarPixel(image, raster,x,y+1,pixel,0,0,0);
                    p.push(new Ponto(x,y+1));
                }
            }
            if(y-1>=0) {
                raster.getPixel(x, y-1, pixel);
                if(pixel[0]==pixelFundo[0] && pixel[1]==pixelFundo[1] && pixel[2]==pixelFundo[2]) {
                    pintarPixel(image, raster,x,y-1,pixel,0,0,0);
                    p.push(new Ponto(x,y-1));
                }
            }
            
            
        }
        return image;
    }
    
    public BufferedImage Scanline(List<Ponto> listaPontos,BufferedImage image) {
        int tam = image.getHeight();
        List<List<ET>> tabelaET = new ArrayList<>(tam);
        for(int i=0;i<tam;i++) {
            tabelaET.add(new ArrayList<>());
        }
        int ymax=0, xmin=0, ymin=0;
        double incr=0;
        for(int i=0;i<listaPontos.size();i++) {
            if(i<listaPontos.size()-1){
                if(listaPontos.get(i).getY()> listaPontos.get(i+1).getY()) {
                    ymax = listaPontos.get(i).getY();
                    xmin = listaPontos.get(i+1).getX();
                    ymin = listaPontos.get(i+1).getY();
                }
                else {
                    ymax = listaPontos.get(i+1).getY();
                    xmin = listaPontos.get(i).getX();
                    ymin = listaPontos.get(i).getY();
                    
                }
                if(listaPontos.get(i+1).getY()-listaPontos.get(i).getY()!=0){
                    incr = (listaPontos.get(i+1).getX()-listaPontos.get(i).getX())/(double)(listaPontos.get(i+1).getY()-listaPontos.get(i).getY()) ;
                }
                else
                    incr=0;
            }
            else {
                if(listaPontos.get(i).getY()> listaPontos.get(0).getY()) {
                    ymax = listaPontos.get(i).getY();
                    xmin = listaPontos.get(0).getX();
                    ymin = listaPontos.get(0).getY();
      
                }
                else {
                    ymax = listaPontos.get(0).getY();
                    xmin = listaPontos.get(i).getX();
                    ymin = listaPontos.get(i).getY();
                }
                if(listaPontos.get(i).getY()-listaPontos.get(0).getY()!=0){
                    incr = (listaPontos.get(i).getX()-listaPontos.get(0).getX())/(double)(listaPontos.get(i).getY()-listaPontos.get(0).getY()) ;
                }
                else
                    incr=0;
            }
            
            tabelaET.get(ymin).add(new ET(ymax, xmin, incr));
            
            
        }
        
        for(int i=0;i<image.getHeight();i++) {
            
            if(tabelaET.get(i).size()>0) {
                System.out.println("i = "+i);
                for(int j=0;j<tabelaET.get(i).size();j++) {
                    System.out.println("Ymax = "+tabelaET.get(i).get(j).getYmax());
                    System.out.println("Xmin = "+tabelaET.get(i).get(j).getXmin());
                    System.out.println("incr = "+tabelaET.get(i).get(j).getIncr());
                    System.out.println("");
                }
            }
        }
        pintarScanline(tabelaET,image);
        return image;
    }
    
    public List<ET> ordenarLista(List<ET> lista) {
        ET aux=null;
        for(int i=0;i<lista.size()-1;i++) {
            for(int j=i;j<lista.size();j++) {
                if(lista.get(i).getXmin()>lista.get(j).getXmin()) {
                    aux = lista.get(i);
                    lista.set(i,lista.get(j));
                    lista.set(j, aux);
                }
            }
        }
        return lista;
    }
    
    public void pintarScanline(List<List<ET>> tabelaET, BufferedImage image) {
        WritableRaster raster=image.getRaster();
        double pixel[]={0,0,0,0};
        List<ET> lista = new ArrayList<>();
        List<ET> listaExclusao = new ArrayList<>();
        int yinicio = 0,i=0;
        while(yinicio<image.getHeight() && tabelaET.get(yinicio).size()==0) {
            yinicio++;
        }
        i=yinicio;
        
        if(tabelaET.get(i).size()>0) { //pegar os primeiros nós do y minimo
            for(int x=0;x<tabelaET.get(i).size();x++) {
                lista.add(tabelaET.get(i).get(x));
            }
        }
        
        while(lista.size()>0) {
            if(i != yinicio) { //para não incrementar os primeiros nós colocados
                for(int j=0;j<lista.size();j++) { //incrementar os nós
                    lista.get(j).setXmin(lista.get(j).getXmin()+lista.get(j).getIncr());
                }
            }
            for(int j=0;j<lista.size();j++) { //se o ymax for igual ao i, voi tirar
                if(lista.get(j).getYmax()==i) {
                    listaExclusao.add(lista.get(j));
                }
            }

            lista.removeAll(listaExclusao);
            listaExclusao.removeAll(listaExclusao);
            if(i != yinicio) { //os nós desse y já foram colocados antes do while
                if(tabelaET.get(i).size()>0) {
                    for(int x=0;x<tabelaET.get(i).size();x++) {
                        lista.add(tabelaET.get(i).get(x));
                    }
                }
            }
            
            lista=ordenarLista(lista);

            for(int j=0;j<lista.size();j+=2) {
                for(int x=(int)Math.ceil(lista.get(j).getXmin());x<(int)Math.ceil(lista.get(j+1).getXmin());x++) {
                    pintarPixel(image,raster,x,i,pixel,0,0,0);
                }
            }
            
            i++;
        }
    }
    
    public void pintarPixel(BufferedImage image, WritableRaster raster,int x, int y,double pixel[],int r,int g,int b){
        raster.getPixel(x, y, pixel);
        pixel[0]=r;
        pixel[1]=g;
        pixel[2]=b;
        raster.setPixel(x,y,pixel);
    }
}
