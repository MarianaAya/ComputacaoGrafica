
package appcompgraficaprojeto2.model;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;


public class DesenharElipse {
    private List<Integer> px=new ArrayList<>();
    private List<Integer> py=new ArrayList<>();
    public BufferedImage desenharElipse(BufferedImage imagem,int x1,int y1,int x2,int y2) {
        //calcular a e b
        int a=0,b=0;
        
        a = Math.abs(x1-x2)/2;
        b = Math.abs(y1-y2)/2;
        
        int x, y;
        double d1, d2;// Valores iniciais 
        WritableRaster raster=imagem.getRaster();
        int pixel[]={0,0,0,0};
        x = 0;
        y = b;
        d1 = Math.pow(b,2) - Math.pow(a, 2) * b + Math.pow(a,2) / 4.0;
        
        px.add(x);
        py.add(y);
        
        while(a * a * (y - 0.5) > Math.pow(b,2) * (x + 1)){// Regi~ao 1 
  
            if (d1 < 0){
                d1 = d1 + Math.pow(b,2)* (2 * x + 3);
                x++;
            }
            else{
                d1 = d1 + Math.pow(b,2) * (2 * x + 3) + Math.pow(a,2) * (-2 * y + 2);
                x++;
                y--;
            }//end if
            
            px.add(x);
            py.add(y);
            
        }// end while 
        
        d2 = Math.pow(b,2) * (x + 0.5) * (x + 0.5) + Math.pow(a,2) * (y - 1) * (y - 1) - Math.pow(a,2) * Math.pow(b,2);
        while(y >0){// Regi~ao 2 
            if (d2 < 0){
                d2 = d2 +Math.pow(b,2) * (2 * x + 2) + Math.pow(a,2) * (-2 * y + 3);
                x++;
                y--;
            }else{
                d2 = d2 + a * a * (-2 * y + 3);
                y--;
            }//end if
            
            px.add(x);
            py.add(y);
            
        }// end while 
        simetria();
        corrigirCentro((x1+x2)/2,(y1+y2)/2);
        
        for(int i=0;i<px.size();i++) {
            if(py.get(i)>0 && py.get(i)<imagem.getHeight() && px.get(i)>0 && px.get(i)<imagem.getWidth()){
                raster.getPixel(px.get(i), py.get(i), pixel);
                pixel[0]=0;
                pixel[1]=0;
                pixel[2]=0;
                raster.setPixel(px.get(i), py.get(i), pixel);
            }
            
        }
        px.removeAll(px);
        py.removeAll(py);
        return imagem;
    }
    
    public void corrigirCentro(int cx,int cy) {
        for(int i=0;i<px.size();i++) {
            px.set(i, px.get(i)+cx);
            py.set(i, py.get(i)+cy);
        }
        
    }
    
    public void simetria() {
        for(int i = px.size() - 1; i >= 0; i--) {
            int x = px.get(i);
            int y = py.get(i);
            
            px.add(-1 * x);py.add(-1 * y);
            px.add(x);py.add(-1 * y);
            px.add(-1 * x);py.add(y);       
        }
    }
}
