
package appcompgraficaprojeto2;

import appcompgraficaprojeto2.model.DesenharCirculo;
import appcompgraficaprojeto2.model.DesenharElipse;
import appcompgraficaprojeto2.model.DesenharReta;
import appcompgraficaprojeto2.model.Poligono;
import appcompgraficaprojeto2.model.Ponto;
import appcompgraficaprojeto2.model.PreencherRegioes;
import appcompgraficaprojeto2.model.Transformacao;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


public class TelaPrincipalController implements Initializable {
    
    @FXML
    private ComboBox<String> cbTipoReta;
    private Image imagem;
    private BufferedImage bimag;
    private File file=null;
    private List<Poligono> poligonos;
    private int indexPoligono;
    private String opcaoReta="";
    private String opcaoCirculo="";
    private String opcaoElipse="";
    private String opcaoPoligono="";
    private String opcaoPreencher="";
    private int x1,x2,y1,y2;
    private PreencherRegioes ferramentaPreencher;
    private DesenharReta ferramentaReta;
    private DesenharElipse ferramentaElipse;
    private DesenharCirculo ferramentaCirculo;
    private Transformacao transformacao;
    @FXML
    private ImageView imageview;
    @FXML
    private ComboBox<String> cbTipoCirculo;
    @FXML
    private TableView<Ponto> tabelaPontos;
    @FXML
    private TableColumn<Ponto, Integer> colX;
    @FXML
    private TableColumn<Ponto, Integer> colY;
    @FXML
    private TableView<Poligono> tabelaPoligonos;
    @FXML
    private TableColumn<Poligono, String> colNomePol;
    @FXML
    private TextField txGrau;
    @FXML
    private RadioButton radOpcaoCentro;
    @FXML
    private RadioButton radOpcaoOrigem;
    @FXML
    private TextField txTranslacaoX;
    @FXML
    private TextField txTranslacaoY;
    @FXML
    private TextField txEscalaX;
    @FXML
    private TextField txEscalaY;
    @FXML
    private TextField txCisalhamentoX;
    @FXML
    private TextField txCisalhamentoY;
    @FXML
    private ComboBox<String> cbEspelhamento;
    @FXML
    private VBox vbPrincipal;
    @FXML
    private VBox vbRetas;
    @FXML
    private VBox vbCirculo;
    @FXML
    private VBox vbPoligonos;
    @FXML
    private VBox vbTransformacoes;
    @FXML
    private VBox vbElipse;
    @FXML
    private ComboBox<String> cbTipoPreencher;
    @FXML
    private VBox vbPreencher;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        poligonos=new ArrayList<Poligono>();
        String[] opcaoesReta={"Equacao Reta","DDA","Ponto medio"};
        String[] opcaoesCirculo={"Equacao Circulo","Trigonometrico","Ponto medio"};
        String[] opcoesEspelhamento={"EspelhamentoX","EspelhamentoY","EspelhamentoXY"};
        String[] opcoesPreencher={"Flood Fill","Scanline"};
        cbTipoReta.setItems(FXCollections.observableArrayList(opcaoesReta));
        cbTipoCirculo.setItems(FXCollections.observableArrayList(opcaoesCirculo));
        cbEspelhamento.setItems(FXCollections.observableArrayList(opcoesEspelhamento));
        cbTipoPreencher.setItems(FXCollections.observableArrayList(opcoesPreencher));
        
        ferramentaReta=new DesenharReta();
        ferramentaCirculo=new DesenharCirculo();
        ferramentaElipse=new DesenharElipse();
        ferramentaPreencher=new PreencherRegioes();
        transformacao=new Transformacao();
        opcaoReta="";
        opcaoCirculo="";
        opcaoElipse="";
        
        colNomePol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colX.setCellValueFactory(new PropertyValueFactory<>("X"));
        colY.setCellValueFactory(new PropertyValueFactory<>("Y"));
        
        
        imagem=new Image("appcompgraficaprojeto2/branco2.jpg");
        bimag=SwingFXUtils.fromFXImage(imagem, null);
        //imageview.setFitWidth(imagem.getWidth());
        //imageview.setFitHeight(imagem.getHeight());
        imageview.setFitWidth(imagem.getWidth());
        imageview.setFitHeight(imagem.getHeight());
        imageview.setImage(imagem);
        
        vbPrincipal.getChildren().remove(vbPoligonos);
        vbPrincipal.getChildren().remove(vbTransformacoes);
        vbPrincipal.getChildren().remove(vbPreencher);
    }    

    @FXML
    private void btnClickDesenharReta(ActionEvent event) {
        opcaoReta=cbTipoReta.getValue();
        opcaoCirculo="";
        opcaoElipse="";
        System.out.println(""+opcaoReta);
    }

    @FXML
    private void evtAbrirImagem(ActionEvent event) {
        FileChooser fchooser=new FileChooser();
        file =fchooser.showOpenDialog(null);
        if(file!=null) //um arquivo de imagem foi selecionado
        {
            imagem=new Image(file.toURI().toString());
            bimag=SwingFXUtils.fromFXImage(imagem, null);
            //imageview.setFitWidth(imagem.getWidth());
            //imageview.setFitHeight(imagem.getHeight());
            imageview.setFitWidth(imagem.getWidth());
            imageview.setFitHeight(imagem.getHeight());
            imageview.setImage(imagem);
            
        }
    }

    @FXML
    private void evtOnMouseReleased(MouseEvent event) {
        x2=(int)event.getX();
        y2=(int)event.getY();
        
        if(opcaoReta.length()>0 || opcaoCirculo.length()>0 || opcaoElipse.length()>0){
            if(opcaoReta.length()>0)
                bimag=ferramentaReta.desenharReta(bimag,opcaoReta,x1,y1,x2,y2);
            if(opcaoCirculo.length()>0)
                bimag=ferramentaCirculo.desenharCirculo(bimag,opcaoCirculo,x1,y1,x2,y2);
            if(opcaoElipse.length()>0)
                bimag=ferramentaElipse.desenharElipse(bimag, x1, y1, x2, y2);
                
        }
 
        imagem = SwingFXUtils.toFXImage(bimag, null);
        imageview.setImage(imagem);
    }

    @FXML
    private void evtOnMousePressed(MouseEvent event) {
        x1=(int)event.getX();
        y1=(int)event.getY();
    }

    @FXML
    private void btnClickDesenharCirculo(ActionEvent event) {
        opcaoReta="";
        opcaoElipse="";
        opcaoCirculo=cbTipoCirculo.getValue();
        System.out.println(""+opcaoCirculo);
    }

    @FXML
    private void evtDesenharElipse(ActionEvent event) {
        opcaoCirculo="";
        opcaoReta="";
        opcaoElipse="elipse";
    }

    @FXML
    private void evtAddPoligono(ActionEvent event) {
        opcaoCirculo="";
        opcaoReta="";
        opcaoElipse="";
        opcaoPoligono="add";
        indexPoligono=poligonos.size();
        Poligono novo=new Poligono("poligono"+(poligonos.size()+1));
        poligonos.add(novo);
        tabelaPoligonos.setItems(FXCollections.observableArrayList(poligonos));
    }

    @FXML
    private void evtOnMouseClicked(MouseEvent event) {
        //event.getClickCount()==2
        if(opcaoPoligono.length()>0 && opcaoPoligono.equals("add")){
            if(event.getClickCount()==1){
                boolean flag=true;
                if(poligonos.get(indexPoligono).getListPontos().size()>0){
                    Ponto ultimo=poligonos.get(indexPoligono).getListPontos().get(poligonos.get(indexPoligono).getListPontos().size()-1);
                    if((int)event.getX()==ultimo.getX() && (int)event.getY()==ultimo.getY())
                        flag=false;
                }
                if(flag){
                    poligonos.get(indexPoligono).getListPontos().add(new Ponto((int)event.getX(),(int)event.getY()));
                    tabelaPontos.setItems(FXCollections.observableArrayList(poligonos.get(indexPoligono).getListPontos()));
                    if(poligonos.get(indexPoligono).getListPontos().size()>1){

                        int tam=poligonos.get(indexPoligono).getListPontos().size();
                        Ponto inicio=poligonos.get(indexPoligono).getListPontos().get(tam-2);
                        Ponto fim=poligonos.get(indexPoligono).getListPontos().get(tam-1);;


                        bimag=ferramentaReta.desenharReta(bimag,"Ponto medio",
                                inicio.getX(),inicio.getY(),fim.getX(),fim.getY());
                        imagem = SwingFXUtils.toFXImage(bimag, null);
                        imageview.setImage(imagem);
                    }
                }
            }else{
                int tam=poligonos.get(indexPoligono).getListPontos().size();
                Ponto inicio=poligonos.get(indexPoligono).getListPontos().get(0);
                Ponto fim=poligonos.get(indexPoligono).getListPontos().get(tam-1);

                bimag=ferramentaReta.desenharReta(bimag,"Ponto medio",
                        inicio.getX(),inicio.getY(),fim.getX(),fim.getY());
                imagem = SwingFXUtils.toFXImage(bimag, null);
                imageview.setImage(imagem);
                opcaoPoligono="";
            }
        }else {
            if(opcaoPreencher.length()>0) {
                if(opcaoPreencher.equals("Flood Fill")){
                    bimag=ferramentaPreencher.FloodFill(bimag, (int)event.getX(), (int)event.getY());
                    imagem = SwingFXUtils.toFXImage(bimag, null);
                    imageview.setImage(imagem);
                }
                else {
                    System.out.println("Scanline");
                    poligonos.get(indexPoligono).calculaPontosAtuais();
                    poligonos.get(indexPoligono).setPreenchido(true);
                    bimag=ferramentaPreencher.Scanline(poligonos.get(indexPoligono).getListAtuais(),bimag);
                    imagem = SwingFXUtils.toFXImage(bimag, null);
                    imageview.setImage(imagem);
                }
            }
        }
        
    }

    @FXML
    private void evtClickTabelaPoligonos(MouseEvent event) {
        if(tabelaPoligonos.getSelectionModel().getSelectedIndex()>=0){
            indexPoligono=tabelaPoligonos.getSelectionModel().getSelectedIndex();
            tabelaPontos.setItems(FXCollections.observableArrayList(poligonos.get(indexPoligono).getListPontos()));
        }
    }

    @FXML
    private void evtRotacao(ActionEvent event) {
        List<Ponto> listaPonto;
        if(poligonos.get(indexPoligono).getListAtuais().size()==0)
            listaPonto=poligonos.get(indexPoligono).getListPontos();
        else
            listaPonto=poligonos.get(indexPoligono).getListAtuais();
        
        int[] pontoM=poligonos.get(indexPoligono).pontoMediio();
       
        if(!radOpcaoOrigem.isSelected()){
            //leva ao centro
            transformacao.translacao(poligonos.get(indexPoligono), (pontoM[0]*-1),(pontoM[1]*-1));
        }
        
        String valorGrau=txGrau.getText();
        valorGrau=valorGrau.replace('.', ',');
        //  inicia a transfomrção
        transformacao.rotacao(poligonos.get(indexPoligono), Double.parseDouble(valorGrau));
        
        
        if(!radOpcaoOrigem.isSelected()){
            //volta ao ponto original
            transformacao.translacao(poligonos.get(indexPoligono), pontoM[0],pontoM[1]);
        }
        
        //calcula os novos pontos a partir da matriz acumulada
        poligonos.get(indexPoligono).calculaPontosAtuais();
        
        //Exibir pontos atuais
        for(int k=0;k<poligonos.get(indexPoligono).getListAtuais().size();k++){
            System.out.println("x="+poligonos.get(indexPoligono).getListAtuais().get(k).getX());
            System.out.println("y="+poligonos.get(indexPoligono).getListAtuais().get(k).getY());
        }
        
        limparImagemConstruirPoligonos();
    }

    @FXML
    private void evtTranslacao(ActionEvent event) {
        List<Ponto> listaPonto;
        // apaga linhas originais
        if(poligonos.get(indexPoligono).getListAtuais().size()==0)
            listaPonto=poligonos.get(indexPoligono).getListPontos();
        else
            listaPonto=poligonos.get(indexPoligono).getListAtuais();

        //  inicia a transfomrção
        transformacao.translacao(poligonos.get(indexPoligono), Integer.parseInt(txTranslacaoX.getText()), Integer.parseInt(txTranslacaoY.getText()));
        poligonos.get(indexPoligono).calculaPontosAtuais();
        
        //Exibir pontos atuais
        for(int k=0;k<poligonos.get(indexPoligono).getListAtuais().size();k++){
            System.out.println("x="+poligonos.get(indexPoligono).getListAtuais().get(k).getX());
            System.out.println("y="+poligonos.get(indexPoligono).getListAtuais().get(k).getY());
        }
        
        limparImagemConstruirPoligonos();
    }

    @FXML
    private void evtEscala(ActionEvent event) {
          List<Ponto> listaPonto;
        // apaga linhas originais
        if(poligonos.get(indexPoligono).getListAtuais().size()==0)
            listaPonto=poligonos.get(indexPoligono).getListPontos();
        else
            listaPonto=poligonos.get(indexPoligono).getListAtuais();
        
        int[] pontoM=poligonos.get(indexPoligono).pontoMediio();
       
        if(!radOpcaoOrigem.isSelected()){
            //leva ao centro
            transformacao.translacao(poligonos.get(indexPoligono), (pontoM[0]*-1),(pontoM[1]*-1));
        }
        String valorX=txEscalaX.getText().replace(',', '.');
        String valorY=txEscalaY.getText().replace(',', '.');
        //  inicia a transfomrção
        transformacao.escala(poligonos.get(indexPoligono), Double.parseDouble(valorX),Double.parseDouble(valorY));
        if(!radOpcaoOrigem.isSelected()){
            //volta ao ponto original
            transformacao.translacao(poligonos.get(indexPoligono), pontoM[0],pontoM[1]);
        }
        ////////////////////////////////////////////////////////////////////////
        
        //calcula os pontos a partir da matriz acumulada
        poligonos.get(indexPoligono).calculaPontosAtuais();
        
        //Exibir pontos atuais
        for(int k=0;k<poligonos.get(indexPoligono).getListAtuais().size();k++){
            System.out.println("x="+poligonos.get(indexPoligono).getListAtuais().get(k).getX());
            System.out.println("y="+poligonos.get(indexPoligono).getListAtuais().get(k).getY());
        }
        
        limparImagemConstruirPoligonos();
    }

    @FXML
    private void radClickCentro(ActionEvent event) {
        radOpcaoOrigem.setSelected(false);
    }

    @FXML
    private void radClickOrigem(ActionEvent event) {
        radOpcaoCentro.setSelected(false);
    }

    @FXML
    private void evtCisalhamento(ActionEvent event) {
        List<Ponto> listaPonto;
        // apaga linhas originais /////////////////////////////////////////////
        if(poligonos.get(indexPoligono).getListAtuais().size()==0)
            listaPonto=poligonos.get(indexPoligono).getListPontos();
        else
            listaPonto=poligonos.get(indexPoligono).getListAtuais();
        
        int[] pontoM=poligonos.get(indexPoligono).pontoMediio();
       
        if(!radOpcaoOrigem.isSelected()){
            //leva ao centro
            transformacao.translacao(poligonos.get(indexPoligono), (pontoM[0]*-1),(pontoM[1]*-1));
        }
        
        String valorX=txCisalhamentoX.getText().replace(',', '.');
        String valorY=txCisalhamentoY.getText().replace(',', '.');
        //  inicia a transfomrção
        transformacao.cisalhamento(poligonos.get(indexPoligono), Double.parseDouble(valorX),Double.parseDouble(valorY));
        if(!radOpcaoOrigem.isSelected()){
            //volta ao ponto original
            transformacao.translacao(poligonos.get(indexPoligono), pontoM[0],pontoM[1]);
        }
        ////////////////////////////////////////////////////////////////////////
        
        //Calcula os pontos atuais pela matriz acumulada
        poligonos.get(indexPoligono).calculaPontosAtuais();
        
        //Exibir pontos atuais
        for(int k=0;k<poligonos.get(indexPoligono).getListAtuais().size();k++){
            System.out.println("x="+poligonos.get(indexPoligono).getListAtuais().get(k).getX());
            System.out.println("y="+poligonos.get(indexPoligono).getListAtuais().get(k).getY());
        }
        
        limparImagemConstruirPoligonos();
    }

    @FXML
    private void evtEspelhamento(ActionEvent event) {
        List<Ponto> listaPonto;
        // apaga linhas originais /////////////////////////////////////////////
        if(poligonos.get(indexPoligono).getListAtuais().size()==0)
            listaPonto=poligonos.get(indexPoligono).getListPontos();
        else
            listaPonto=poligonos.get(indexPoligono).getListAtuais();
        
        String espelho=cbEspelhamento.getValue();
        int ex=1,ey=1;
        if(espelho.equals("EspelhamentoX")){
            ey=-1;
        }else
        if(espelho.equals("EspelhamentoY")){
            ex=-1;
        }else
        if(espelho.equals("EspelhamentoXY")){
            ey=-1;
            ex=-1;
        }
        ////////////////////////////////////////////////////////////////////////
        int[] pontoM=poligonos.get(indexPoligono).pontoMediio();
       
        //leva ao centro
        transformacao.translacao(poligonos.get(indexPoligono), (pontoM[0]*-1),(pontoM[1]*-1));
        //  inicia a transfomrção
        transformacao.espelhamento(poligonos.get(indexPoligono), ex,ey);
        //volta ao ponto original
        transformacao.translacao(poligonos.get(indexPoligono), pontoM[0],pontoM[1]);
        
        ////////////////////////////////////////////////////////////////////////
        
        //calcula os pontos atuais pela matriz aumulada
        poligonos.get(indexPoligono).calculaPontosAtuais();
        
        //Exibir pontos atuais
        for(int k=0;k<poligonos.get(indexPoligono).getListAtuais().size();k++){
            System.out.println("x="+poligonos.get(indexPoligono).getListAtuais().get(k).getX());
            System.out.println("y="+poligonos.get(indexPoligono).getListAtuais().get(k).getY());
        }
        
        limparImagemConstruirPoligonos();
    }
    
    public void limparImagemConstruirPoligonos(){
        if(file!=null) //um arquivo de imagem foi selecionado
            imagem=new Image(file.toURI().toString());
        else
            imagem=new Image("appcompgraficaprojeto2/branco2.jpg");
        
            bimag=SwingFXUtils.fromFXImage(imagem, null);
            //imageview.setFitWidth(imagem.getWidth());
            //imageview.setFitHeight(imagem.getHeight());
            imageview.setFitWidth(imagem.getWidth());
            imageview.setFitHeight(imagem.getHeight());
            imageview.setImage(imagem);
            
            List<Ponto> listaPonto;
            for(int pos=0;pos<poligonos.size();pos++){
                if(poligonos.get(pos).getListAtuais().size()==0)
                    listaPonto=poligonos.get(pos).getListPontos();
                else
                    listaPonto=poligonos.get(pos).getListAtuais();
                for(int i=0;i<listaPonto.size()-1;i++){
                    bimag=ferramentaReta.desenharReta(bimag,"Ponto medio",
                            listaPonto.get(i).getX(),
                            listaPonto.get(i).getY(),
                            listaPonto.get(i+1).getX(),
                            listaPonto.get(i+1).getY());
                    imagem = SwingFXUtils.toFXImage(bimag, null);
                    imageview.setImage(imagem);
                }
                bimag=ferramentaReta.desenharReta(bimag,"Ponto medio",
                        listaPonto.get(listaPonto.size()-1).getX(),
                        listaPonto.get(listaPonto.size()-1).getY(),
                        listaPonto.get(0).getX()
                        ,listaPonto.get(0).getY());
                if(poligonos.get(pos).isPreenchido()) {
                    bimag=ferramentaPreencher.Scanline(listaPonto,bimag);
                }
                imagem = SwingFXUtils.toFXImage(bimag, null);
                imageview.setImage(imagem);
            }
            
        
    }

    @FXML
    private void evtLimparTela(ActionEvent event) {

        if(file!=null) //um arquivo de imagem foi selecionado
            imagem=new Image(file.toURI().toString());
        else
            imagem=new Image("appcompgraficaprojeto2/branco2.jpg");
        poligonos.removeAll(poligonos);
        tabelaPoligonos.setItems(null);
        tabelaPontos.setItems(null);
        bimag=SwingFXUtils.fromFXImage(imagem, null);
        //imageview.setFitWidth(imagem.getWidth());
        //imageview.setFitHeight(imagem.getHeight());
        imageview.setFitWidth(imagem.getWidth());
        imageview.setFitHeight(imagem.getHeight());
        imageview.setImage(imagem);
    }

    @FXML
    private void evtPrimitivas(ActionEvent event) {
        removeFilhos();
        vbPrincipal.getChildren().add(vbRetas);
        vbPrincipal.getChildren().add(vbCirculo);
        vbPrincipal.getChildren().add(vbElipse);
    }

    @FXML
    private void evtPoligonos(ActionEvent event) {
        removeFilhos();
        vbPrincipal.getChildren().add(vbPoligonos);
        vbPrincipal.getChildren().add(vbTransformacoes);
        vbPrincipal.getChildren().add(vbPreencher);
    }
    public void removeFilhos(){
        vbPrincipal.getChildren().remove(vbPoligonos);
        vbPrincipal.getChildren().remove(vbTransformacoes);
        vbPrincipal.getChildren().remove(vbRetas);
        vbPrincipal.getChildren().remove(vbCirculo);
        vbPrincipal.getChildren().remove(vbElipse);
        vbPrincipal.getChildren().remove(vbPreencher);
    }

    @FXML
    private void btnClickPreencher(ActionEvent event) {
        opcaoCirculo="";
        opcaoReta="";
        opcaoElipse="";
        opcaoPoligono="";
        opcaoPreencher=cbTipoPreencher.getValue();
    }
}
