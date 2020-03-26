package controller;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import model.Carro1;
import model.Carro2;

public class ControladorTela implements Initializable{  //classe ControladorTela que extende da Initializable
	
  @FXML
  public ImageView carro1;  //declarando o ImageView do carro1
  @FXML
  public ImageView carro2;  //declarando o ImageView do carro2
  @FXML
  private ImageView luz1;  //declarando o ImageView da luz sinalizadora da parte de cima da pista
  @FXML
  private ImageView luz2;  //declarando o ImageView da luz sinalizadora da parte de baixo da pista
  @FXML
  private Label texto;  //declarando a Label da descrição na parte de cima da tela
  @FXML
  private Spinner<Integer> velocidade1;  //declarando o Spinner que muda a velocidade do carro1
  @FXML
  private Spinner<Integer> velocidade2;  //declarando o Spinner que muda a velocidade do carro2

  public Carro1 c1;  //declarando Thread tipo Carro1
  public Carro2 c2;  //declarando Thread tipo Carro2
  public static Stage palco;  //declarando um palco para receber o palco principal

  double posicaoY1;  //posicaoY1 recebe a posicao Y atual do ImageView
  double posicaoX1;  //posicaoX1 recebe a posicao X atual do ImageView
  double rotacao1;  //rotacao1 recebe a rotacao atual do ImageView

  public ControladorTela(){
    carro1 = new ImageView();  //instanciando o ImageView do carro1
    carro2 = new ImageView();  //instanciando o ImageView do carro2
    luz1 = new ImageView();  //instanciando o ImageView da luz sinalizadora da parte de cima da pista
    luz2 = new ImageView();  //instanciando o ImageView da luz sinalizadora da parte de baixo da pista
    velocidade1 = new Spinner<>();  //instanciando o Spinner da velocidade do carro1
    velocidade2 = new Spinner<>();  //instanciando o Spinner da velocidade do carro2
    texto = new Label();  //instanciando a Label do titulo descritivo
    c1 = new Carro1();  //instanciando a Thread do carro1
    c2 = new Carro2();  //instanciando a Thread do carro2
  }  //fim do metodo contrutor dessa classe

  /* ***************************************************************
  * Metodo: setPalco
  * Funcao: recebe o palco principal para poder fecha-lo quando for
  *   necessario
  * Parametros: palco principal da tela
  * Retorno: *sem retorno*
  *************************************************************** */
  public static void setPalco(Stage palcoPrincipal){
    palco = palcoPrincipal;  //palco da classe recebe o palco principal
  }  //fim do metodo setPalco

  /* ***************************************************************
  * Metodo: fechar
  * Funcao: fecha o palco principal, quando clica no botao de fechar
  * Parametros: *sem parametro*
  * Retorno: *sem retorno*
  *************************************************************** */
  @FXML
  public void fechar(){
    palco.close();  //fechando o palco principal
  }  //fim do metodo fechar

  /* ***************************************************************
  * Metodo: velocidade1
  * Funcao: atualiza a velocidade quando clica no Spinner de 
  *   velocidade
  * Parametros: evento do tipo de mouse
  * Retorno: *sem retorno*
  *************************************************************** */
  @FXML
  public void velocidade1(MouseEvent event){
    c1.setVelocidade((int) velocidade1.getValue());  //mudando a velocidade de acordo com o valor do Spinner do carro1
  }  //fim do metodo velocidade1

  /* ***************************************************************
  * Metodo: velocidade2
  * Funcao: atualiza a velocidade quando clica no Spinner de 
  *   velocidade
  * Parametros: evento do tipo de mouse
  * Retorno: *sem retorno*
  *************************************************************** */
  @FXML
  public void velocidade2(MouseEvent event){
    c2.setVelocidade((int) velocidade2.getValue());  //mudando a velocidade de acordo com o valor do Spinner do carro2
  }  //fim do metodo velocidade2

  /* ***************************************************************
  * Metodo: carro1Acelera
  * Funcao: realiza o movimento do ImageView do carro1
  * Parametros: *sem parametro*
  * Retorno: *sem retorno*
  *************************************************************** */
  public void carro1Acelera(){
    double posicaoX = carro1.getLayoutX();  //posicaoX recebe a posicao X atual do ImageView
    if(posicaoX == 760){  //se posicaoX for 100, tenta mover o carro1
      if(carro2Passando()){  //se o valor booleano da luz de cima for verdadeiro, muda a posicao da imagem do carro1
        luz1.setImage(new Image("/imagens/parte3-cima.png"));  //muda a imagem do sinalizador de passagem para vermelho
      }else{
        luz1.setImage(new Image("/imagens/parte4-cima.png"));  //muda a imagem do sinalizador de passagem para verde
        Platform.runLater(() -> {
          carro1.setLayoutX(posicaoX - 1);  //muda a posicaoX do ImageView carro1   
        });
      }  //fim do if - else
    }else{
      Platform.runLater(() -> {
        carro1.setLayoutX(posicaoX - 1);  //muda a posicaoX do ImageView carro1   
      });
    }
  }  //fim do metodo carro1Acelera

  /* ***************************************************************
  * Metodo: carro2Acelera
  * Funcao: realiza o movimento do ImageView do carro2
  * Parametros: *sem parametro*
  * Retorno: *sem retorno*
  *************************************************************** */
  public void carro2Acelera(){
    posicaoY1 = carro2.getLayoutY();  //posicaoY1 recebe a posicao Y atual do ImageView
    posicaoX1 = carro2.getLayoutX();  //posicaoX1 recebe a posicao X atual do ImageView
    rotacao1 = carro2.getRotate();  //rotacao1 recebe a rotacao atual do ImageView
    if(posicaoX1 == 210){  //se posicaoX1 for 100, tenta mover o carro2
      if(carro1Passando()){  //se o valor booleano da luz de cima for verdadeiro, muda a posicao da imagem do carro2
        luz2.setImage(new Image("/imagens/parte3-baixo.png"));  //muda a imagem do sinalizador de passagem para vermelho
      }else{
        luz2.setImage(new Image("/imagens/parte4-baixo.png"));  //muda a imagem do sinalizador de passagem para verde
        posicaoX1++;  //posicao de x aumenta
      }
    }else{
      posicaoX1++;  // posicao de x aumenta
    }
    if(posicaoX1 >= 240 && posicaoX1 <= 330){  //se posicaoX1 estiver entre 150 e 198 muda a rotacao do ImageView do carro2, para fazer a curva
      posicaoY1 = carro2.getLayoutY();  //recebe o valor atual da posicao Y do ImageView
      posicaoY1 -= 2 * Math.sin((Math.PI/180) * (carro2.getLayoutX() - 240));    //posicaoY1 diminui de acordo ao seno do valor de X
      if(posicaoX1 < 285){  //se posicaoX1 for maior que 175, rotacao do ImageView diminui 2 graus
        rotacao1 -= 0.3;
      }  //fim do if
      if(posicaoX1 > 285){  //se posicaoX1 for maior que 175, rotacao do ImageView aumenta 2 graus
        rotacao1 += 0.34;
      }  //fim do if
      if(posicaoX1 == 330){  //se posicaoX1 for 198 rotacao1 do ImageView estabiliza em 0 graus
        rotacao1 = 0;
      }  //fim do if
    }else if(posicaoX1 >= 570 && posicaoX1 <= 660){  //se posicao X estiver entre 470 e 519 muda a posicao Y do ImageView do carro2
      posicaoY1 = carro2.getLayoutY();  //recebe o valor atual da posicao Y do ImageView
      posicaoY1 += 2 * Math.sin((Math.PI/180) * (carro2.getLayoutX() - 570));  //posicaoY1 aumenta de acordo ao seno do valor de X
      if(posicaoX1 < 615){  //se posicaoX1 for menos que 495, rotacao do ImageView aumenta 2 grau
        rotacao1 += 0.34; 
      }  //fim do if
      if(posicaoX1 > 615){  //se posicaoX1 for menos que 495, rotacao do ImageView diminui 2 grau 
        rotacao1 -= 0.34;
      }  //fim do if
      if(posicaoX1 == 660){  //se posicaoX1 for 519, rotacao do ImageView estabiliza em 0 graus
        rotacao1 = 0;
      }  //fim do if
    }
    Platform.runLater(() -> {
      carro2.setLayoutX(posicaoX1);  //muda a posicaoX1 do ImageView do carro2
      carro2.setLayoutY(posicaoY1);  //atribui 2 ao valor atual da posicao Y do ImageView
      carro2.setRotate(rotacao1);
    });
  }  //fim do metodo carro2Acelera

  public boolean carro1Passando(){
    return (carro1.getLayoutX() >= 240 && carro1.getLayoutX() <= 760);
  }

  public boolean carro2Passando(){
    return (carro2.getLayoutX() >= 240 && carro2.getLayoutX() <= 760);
  }

  /* ***************************************************************
  * Metodo: initialize
  * Funcao: metodo principal da classe Initializable
  * Parametros: URL, ResourceBundle
  * Retorno: *sem retorno*
  *************************************************************** */
	@Override
	public void initialize(URL url, ResourceBundle rb){
    texto.setText("Representando a passagem pela ponte sobre o Rio Jequitinhonha\n" + 
      "na cidade de Almenara - MG, quando houve a reforma em sua estrutura, em 2017");  //muda o texto descritivo que aparece na tela

    c1.setControlador(this);  //passa esta classe por parametro para a classe Carro1
    c2.setControlador(this);  //passa esta classe por parametro para a classe Carro1
    c1.setDaemon(true);  //habilita o daemon da Thread. Quando o main terminar execucao a Thread encerra
    c2.setDaemon(true);  //habilita o daemon da Thread. Quando o main terminar execucao a Thread encerra
    c1.setPriority(10);  //muda a prioridade da Thread Carro1
    c2.setPriority(10);  //muda a prioridade da Thread Carro2
    c1.start();  //inicia a Thread Carro1
    c2.start();  //inicia a Thread Carro2
	}  //fim do metodo initialize
}  //fim da classe ControladorTela