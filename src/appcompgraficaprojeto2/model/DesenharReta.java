
package appcompgraficaprojeto2.model;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;


public class DesenharReta {
    public DesenharReta(){
        
    }
    
    public BufferedImage desenharReta(BufferedImage imagem,String opcao,int x1,int y1,int x2,int y2){
        switch(opcao){
            case "Equacao Reta":
                imagem=retaEquacao( imagem, x1, y1, x2, y2);
                System.out.println("Entrei na opcao");
                break;
            
            case "DDA":
                imagem=DDA(imagem, x1, y1, x2, y2);
                break;
            
            case "Ponto medio":
                imagem=PontoMedio(imagem, x1, y1, x2, y2);
                break;
            case "apagarReta":
                imagem=apagarReta(imagem, x1, y1, x2, y2);
                break;
               
        }
        return imagem;  
    }
    
    public BufferedImage retaEquacao(BufferedImage imagem,int x1,int y1,int x2,int y2){
        double dx=x2-x1;
        double dy=y2-y1;
        double m=dy/dx;
        double y,x;
        double pixel[]={0,0,0,0};
        
        WritableRaster raster=imagem.getRaster();
        
        if(x2>x1 && y2>y1){ //1 e 2 quadrante
            if(Math.abs(dx)>Math.abs(dy)){
                for (x = x1; x <= x2; x++)
                {
                    y = y1 + m*(x-x1);  
                    raster.getPixel((int)x, (int)Math.round(y), pixel);
                    pixel[0]=255;
                    pixel[1]=pixel[2]=0;
                    raster.setPixel((int)x, (int)Math.round(y),pixel);
                }
            }else{
                for (y = y1; y <= y2; y++)
                {
                    x = x1 + (y-y1)/m;  
                    raster.getPixel((int)x, (int)Math.round(y), pixel);
                    pixel[0]=255;
                    pixel[1]=pixel[2]=0;
                    raster.setPixel((int)x, (int)Math.round(y),pixel);
                }
            }
        }else{ //7 e 8 quarante
            if(x2>x1 && y1>y2){
                if(Math.abs(dx)>Math.abs(dy)){
                    for (x = x1; x <= x2; x++)
                    {
                        y = y1 + m*(x-x1);  
                        raster.getPixel((int)x, (int)Math.round(y), pixel);
                        pixel[0]=255;
                        pixel[1]=pixel[2]=0;
                        raster.setPixel((int)x, (int)Math.round(y),pixel);
                    }
                }else{
                    for (y = y2; y <= y1; y++)
                    {
                        x = x2 + (y-y2)/m;  
                        raster.getPixel((int)x, (int)Math.round(y), pixel);
                        pixel[0]=255;
                        pixel[1]=pixel[2]=0;
                        raster.setPixel((int)x, (int)Math.round(y),pixel);
                    }
                }
            }
            else{ //5 e 6 quadrante
                if(x1>x2 && y1>y2){
                    if(Math.abs(dx)>Math.abs(dy)){
                        for (x = x2; x <= x1; x++)
                        {
                            y = y2 + m*(x-x2);  
                            raster.getPixel((int)x, (int)Math.round(y), pixel);
                            pixel[0]=255;
                            pixel[1]=pixel[2]=0;
                            raster.setPixel((int)x, (int)Math.round(y),pixel);
                        }
                    }else{
                        for (y = y2; y <= y1; y++)
                        {
                            x = x2 + (y-y2)/m;  
                            raster.getPixel((int)x, (int)Math.round(y), pixel);
                            pixel[0]=255;
                            pixel[1]=pixel[2]=0;
                            raster.setPixel((int)x, (int)Math.round(y),pixel);
                        }
                    }
                }else{ //3 e 4 quadrante
                    if(Math.abs(dx)>Math.abs(dy)){
                        for (x = x2; x <= x1; x++)
                        {
                            y = y2 + (x-x2)*m;  
                            raster.getPixel((int)x, (int)Math.round(y), pixel);
                            pixel[0]=255;
                            pixel[1]=pixel[2]=0;
                            raster.setPixel((int)x, (int)Math.round(y),pixel);
                        }
                    }else{
                        for (y = y1; y <= y2; y++)
                        {
                            x = x1 + (y-y1)/m;  
                            raster.getPixel((int)x, (int)Math.round(y), pixel);
                            pixel[0]=255;
                            pixel[1]=pixel[2]=0;
                            raster.setPixel((int)x, (int)Math.round(y),pixel);
                        }
                    }
                    
                }
            }
        }

        return imagem;
    }
    
    
    public BufferedImage DDA(BufferedImage bimag,int x1,int y1,int x2, int y2){
        WritableRaster raster=bimag.getRaster();
        int pixel[]={0,0,0,0};
        int Length, I,aux;
        double x,y,Xinc,Yinc;
        
       
        Length = Math.abs(x2 -x1);

        if (Math.abs(y2 -y1) > Length)
               Length = Math.abs(y2-y1);
        Xinc= (double)(x2 -x1)/Length;
        Yinc= (double)(y2 -y1)/Length;
        
        x = x1; y = y1;
        
        if(x2>x1){
            while(x<x2){
                raster.getPixel((int)Math.round(x), (int)Math.round(y), pixel);
                pixel[0]=0;
                pixel[1]=255;
                pixel[2]=0;
                raster.setPixel((int)Math.round(x), (int)Math.round(y), pixel);
                x = x + Xinc;
                y = y + Yinc;
            }
        }
        else {
            if(x2<x1){
                while(x>x2){
                    raster.getPixel((int)Math.round(x), (int)Math.round(y), pixel);
                    pixel[0]=0;
                    pixel[1]=255;
                    pixel[2]=0;
                    raster.setPixel((int)Math.round(x), (int)Math.round(y), pixel);
                    x = x + Xinc;
                    y = y + Yinc;
                }
            }
            else {
                if(y2<y){
                    aux=y2;
                    y2=(int)y;
                    y=aux;
                }
                Yinc=Math.abs(Yinc);
                while(y<y2) {
                    raster.getPixel((int)Math.round(x), (int)Math.round(y), pixel);
                    pixel[0]=0;
                    pixel[1]=255;
                    pixel[2]=0;
                    raster.setPixel((int)Math.round(x), (int)Math.round(y), pixel);
                    y = y + Yinc;
                }
            }
        }
        return bimag;
    }
    
    public BufferedImage PontoMedio(BufferedImage bimag,int x1,int y1,int x2, int y2) {
        WritableRaster raster=bimag.getRaster();
        int pixel[]={0,0,0,0};
        int declive=1; 
        int dx, dy, incE, incNE, d, x, y, aux;
        
        if(x2<x1) {
            aux=x2;
            x2=x1;
            x1=aux;
            
            aux=y2;
            y2=y1;
            y1=aux;
        }
        
        if(y2<y1){
            y1*=-1;
            y2*=-1;
        }
        
        if(Math.abs(y2-y1)>Math.abs(x2-x1)){
            dx = x2 - x1; 
            dy = y2 - y1; 

            // Constante de Bresenham 
            incE = 2 * dx; 
            incNE = 2 * dx - 2 * dy; 
            d = 2 * dx - dy; 
            x = x1; 
            for (y = y1; y <= y2; y++){ 
                raster.getPixel((int)Math.round(x), (int)Math.abs(Math.round(y)), pixel);
                pixel[0]=0;
                pixel[1]=0;
                pixel[2]=255;
                raster.setPixel((int)Math.round(x), (int)Math.abs(Math.round(y)), pixel);
                if (d <= 0){ 
                      d += incE; 
                } 
                else{ 
                    d += incNE; 
                    x += declive; 
                } 
            }
            
        }
        else{
            dx = x2 - x1; 
            dy = y2 - y1; 

            // Constante de Bresenham 
            incE = 2 * dy; 
            incNE = 2 * dy - 2 * dx; 
            d = 2 * dy - dx; 
            y = y1; 
            for (x = x1; x <= x2; x++){ 
                raster.getPixel((int)Math.round(x), (int)Math.abs(Math.round(y)), pixel);
                pixel[0]=0;
                pixel[1]=0;
                pixel[2]=255;
                raster.setPixel((int)Math.round(x), (int)Math.abs(Math.round(y)), pixel);
                if (d <= 0){ 
                      d += incE; 
                } 
                else{ 
                    d += incNE; 
                    y += declive; 
                } 
            }
        }
        return bimag;
    }
    
    public BufferedImage apagarReta(BufferedImage bimag,int x1,int y1,int x2, int y2) {
        WritableRaster raster=bimag.getRaster();
        int pixel[]={0,0,0,0};
        int declive=1; 
        int dx, dy, incE, incNE, d, x, y, aux;
        
        if(x2<x1) {
            aux=x2;
            x2=x1;
            x1=aux;
            
            aux=y2;
            y2=y1;
            y1=aux;
        }
        
        if(y2<y1){
            y1*=-1;
            y2*=-1;
        }
        
        if(Math.abs(y2-y1)>Math.abs(x2-x1)){
            dx = x2 - x1; 
            dy = y2 - y1; 

            // Constante de Bresenham 
            incE = 2 * dx; 
            incNE = 2 * dx - 2 * dy; 
            d = 2 * dx - dy; 
            x = x1; 
            for (y = y1; y <= y2; y++){ 
                raster.getPixel((int)Math.round(x), (int)Math.abs(Math.round(y)), pixel);
                pixel[0]=255;
                pixel[1]=255;
                pixel[2]=255;
                raster.setPixel((int)Math.round(x), (int)Math.abs(Math.round(y)), pixel);
                if (d <= 0){ 
                      d += incE; 
                } 
                else{ 
                    d += incNE; 
                    x += declive; 
                } 
            }
            
        }
        else{
            dx = x2 - x1; 
            dy = y2 - y1; 

            // Constante de Bresenham 
            incE = 2 * dy; 
            incNE = 2 * dy - 2 * dx; 
            d = 2 * dy - dx; 
            y = y1; 
            for (x = x1; x <= x2; x++){ 
                raster.getPixel((int)Math.round(x), (int)Math.abs(Math.round(y)), pixel);
                pixel[0]=255;
                pixel[1]=255;
                pixel[2]=255;
                raster.setPixel((int)Math.round(x), (int)Math.abs(Math.round(y)), pixel);
                if (d <= 0){ 
                      d += incE; 
                } 
                else{ 
                    d += incNE; 
                    y += declive; 
                } 
            }
        }
        return bimag;
    }
}


