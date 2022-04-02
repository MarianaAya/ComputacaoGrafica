package appcompgraficaprojeto2.model;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class DesenharCirculo {
    public BufferedImage desenharCirculo(BufferedImage imagem,String opcao,int x1,int y1,int x2,int y2){
        int auxValor=(int)Math.abs((int)Math.pow((x1-x2), 2))+(int)Math.abs(Math.pow((y1-y2), 2));
	double raio = Math.sqrt(auxValor);
        System.out.println("Raio="+raio);
        switch(opcao){
            case "Equacao Circulo":
                System.out.println("Equacao circul do circulo");
                imagem=circuloEquacao( imagem, x1, y1, raio);
                break;
            
            case "Trigonometrico":
                System.out.println("Trigonometrico do circulo");
                imagem=trigonometrico(imagem, x1, y1, raio);
                break;
            
            case "Ponto medio":
                System.out.println("Ponto medio do circulo");
                imagem=pontoMedio(imagem, x1, y1,raio);
                break;
               
        }
        return imagem;  
    }
    
    public BufferedImage circuloEquacao(BufferedImage imagem,int x1,int y1,double raio){
        int x = 0;

        double result=Math.pow(raio,2)-Math.pow(x,2);
	int y=(int)Math.abs(Math.sqrt(result));
        
        int xlimit= (int)(raio/Math.sqrt(2));
	imagem=pontoCirculoSimetrico (imagem,x1,y1,x, y, 100,100,100);
	while (x<xlimit+1) {
	  x++;
          result=Math.pow(raio,2)-Math.pow(x,2);
          y=(int)Math.abs(Math.sqrt(result));
	  imagem=pontoCirculoSimetrico (imagem,x1,y1,x, y, 100,100,100); //função de simetria
        }
        
        return imagem;
    }
    
    public BufferedImage trigonometrico(BufferedImage imagem,int x1,int y1,double raio){
        int x = 0;
	int y=0;
        
        double gap=360/(2*Math.PI*raio);
        
        if(gap==0)
            gap=360;
        for(double i=0;i<=45;i+=gap){
            x=(int)(raio*Math.cos(i*Math.PI/180));
            y=(int)(raio*Math.sin(i*Math.PI/180));
            
            imagem=pontoCirculoSimetrico (imagem,x1,y1,x, y, 70,70,255);
        }
        return imagem;
    }
    
    public BufferedImage pontoMedio(BufferedImage imagem,int x1,int y1,double raio){
        int x = 0;
	int y = (int)raio;
	double d=1-raio;
	imagem=pontoCirculoSimetrico (imagem,x1,y1,x, y, 255,0,0);
	while (y > x) {
	  if (d < 0) /* escolhe E */
	     d += 2*x + 3;
	  else { /*escolhe SE*/
                 d+=(2*(x-y)+5);
  	         y--;
           }
	  x++;
	  imagem=pontoCirculoSimetrico (imagem,x1,y1,x, y, 255,0,0); //função de simetria
      }
        return imagem;
    }
    
    public BufferedImage pontoCirculoSimetrico(BufferedImage image,int cx, int cy,int x,int y,int r,int g,int b){
        WritableRaster raster=image.getRaster();
        double pixel[]={0,0,0,0};
        
        if(cx+x<image.getWidth() && cy+y<image.getHeight() && cx+x>=0 && cy+y>=0)
            pintarPixel(image,raster, cx+x, cy+y, pixel, r, g, b);
        
        if(cx+y<image.getWidth() && cy+x<image.getHeight() && cx+y>0 && cy+x>0)
            pintarPixel(image,raster, cx+y, cy+x, pixel, r, g, b);
        
        if(cx+y<image.getWidth() && cy-x<image.getHeight() && cx+y>0 && cy-x>0)
            pintarPixel(image,raster, cx+y, cy-x, pixel, r, g, b);
        
        if(cx+x<image.getWidth() && cy-y<image.getHeight() && cx+x>0 && cy-y>0)
            pintarPixel(image,raster, cx+x, cy-y, pixel, r, g, b);
        
        if(cx-x<image.getWidth() && cy-y<image.getHeight() && cx-x>0 && cy-y>0)
            pintarPixel(image,raster, cx-x, cy-y, pixel, r, g, b);
        
        if(cx-y<image.getWidth() && cy-x<image.getHeight() && cx-y>0 && cy-x>0)
            pintarPixel(image,raster, cx-y, cy-x, pixel, r, g, b);
        
        if(cx-y<image.getWidth() && cy+x<image.getHeight() && cx-y>0 && cy+x>0)
            pintarPixel(image,raster, cx-y, cy+x, pixel, r, g, b);
        
        if(cx-x<image.getWidth() && cy+y<image.getHeight() && cx-x>0 && cy+y>0)
            pintarPixel(image,raster, cx-x, cy+y, pixel, r, g, b);
        return image;
    }
    
    public void pintarPixel(BufferedImage image, WritableRaster raster,int x, int y,double pixel[],int r,int g,int b){
        raster.getPixel(x, y, pixel);
        pixel[0]=r;
        pixel[1]=g;
        pixel[2]=b;
        raster.setPixel(x,y,pixel);
    }
}
