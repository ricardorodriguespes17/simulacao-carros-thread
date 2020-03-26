package model;

import controller.ControladorTela;
import java.lang.Thread;
import javafx.application.Platform;

public class Carro1 extends Thread{  //classe Carro1 que extende da classe Thread
	ControladorTela controlador;  //declarando controlador para receber o controlador da tela principal
  int velocidade = 10;  //inteiro que controla a velocidade da movimentacao do carro1

  /* ***************************************************************
  * Metodo: run
  * Funcao: metodo principal da classe Thread que inicia um Thread
  * Parametros: *sem parametro*
  * Retorno: *sem retorno*
  *************************************************************** */
  @Override
	public void run(){
    for(int i = 1; i > 0; i++){  //for para repetir o movimento do carro1 infinitamente
      Platform.runLater(() -> {
        controlador.carro1.setLayoutX(1010);  //muda a posicao X do ImageView do carro1 para iniciar nessa posicao
      });
      while(controlador.carro1.getLayoutX() > 0){  //enquanto a posicaoX do ImageView do carro1 for maior que 1 executa as instrucoes
        controlador.carro1Acelera();  //chama o metodo do controlador que move o ImageView do carro
        try{  //tenta executar o metodo sleep
          sleep(velocidade);  //hiberna a Thread por velocidade segundos de tempo
        }catch(InterruptedException e){  //pega a exececao InterruptedException, se ocorrer
          e.printStackTrace();  //imprime a excecao
        }  //fim do try - catch
      }  //fim do while
    }  //fim do for
	}  //fim do metodo run

  /* ***************************************************************
  * Metodo: setVelocidade
  * Funcao: muda o valor da velocidade de movimentos da ImageView
  *   do carro1
  * Parametros: inteiro correspondente a velocidade
  * Retorno: *sem retorno*
  *************************************************************** */
  public void setVelocidade(int velocidade){
    this.velocidade = 60 - (velocidade * 10);  //inteiro velocidade da classe recebe o resultado desta equacao
  }  //fim do metodo setVelocidade

  /* ***************************************************************
  * Metodo: setControlador
  * Funcao: metodo que recebe a classe controladora da tela 
  *   principal, para esta classe poder controlar a tela
  * Parametros: controlador da tela principal
  * Retorno: *sem retorno*
  *************************************************************** */
	public void setControlador(ControladorTela controlador){
		this.controlador = controlador;  //controlador da classe recebe o controlador da tela principal passado por paramentro
	}  //fim do metodo setControlador

}  //fim da classe Carro1