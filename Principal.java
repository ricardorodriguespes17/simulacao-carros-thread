import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import controller.ControladorTela;

/* ***************************************************************
* Autor: Ricardo Rodrigues Neto
* Matricula: 201710560
* Inicio: 31/07/2018
* Ultima 06/08/2018
* Nome: Simulacao de uma correncia de Threads
* Funcao: Mostrar a simulacao de uma estrada que esta interditada
*   em um ponto, e so pode passar um veiculo por vez.
*************************************************************** */

public class Principal extends Application{  //classe Principal que extende da classe Application

  /* ***************************************************************
  * Metodo: start
  * Funcao: metodo principal da classe Application que inicia o
  *   palco principal
  * Parametros: palco principal da aplicacao
  * Retorno: *sem retorno*
  *************************************************************** */
  @Override
	public void start(Stage palco) throws Exception{
    Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));  //carregando o caminho da tela fxml
    Scene cena = new Scene(root);  //instanciando a cena da tela
    palco.setScene(cena);  //passando a cena para o palco da tela
    palco.initStyle(StageStyle.UNDECORATED);  //tirando as bordas e a barra de titulo da tela
    palco.show();  //mostrando a tela
    ControladorTela.setPalco(palco);  //passando o palco como parametro para o controlador da tela, para poder fecha-lo
  }  //fim do metodo start

  /* ***************************************************************
  * Metodo: main
  * Funcao: metodo principal do projeto
  * Parametros: String[] args
  * Retorno: *sem retorno*
  *************************************************************** */
	public static void main(String[] args){
		launch(args);  //iniciando aplicação
	}  //fim do metodo main
}  //fim da classe Principal