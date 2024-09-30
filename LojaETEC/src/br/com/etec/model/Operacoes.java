package br.com.etec.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Operacoes {
	
	@FXML
	private TextField txfUsuario;
	
	@FXML
	private PasswordField psfSenha;
	
	@FXML
	private Button btnAcessar;
	
	@FXML
	private Button btnCadastrar;
	//
	@FXML
	private Button btn_fechar;
	
	@FXML
	private Stage acpPalco;
	
	@FXML
	private void validarUsuario(ActionEvent event) {
		String nomeUsuario;
		nomeUsuario = txfUsuario.getText();
		
		String senhaUsuario;
		senhaUsuario = psfSenha.getText();
		
		if(nomeUsuario.isEmpty() || senhaUsuario.isEmpty()) {
			if(nomeUsuario.isEmpty()) {
				mostrarAlerta(Alert.AlertType.WARNING, "INFORME O SEU NOME, ANIMAL!", "É necessário colocar o nome do úsuario.");
			} else {
				if(senhaUsuario.isEmpty()) {
					mostrarAlerta(Alert.AlertType.WARNING, "INFORME A SENHA!", "É necessário colocar a senha do úsuario.");
			}
			}
		}
		else {
			if(nomeUsuario.equals("admin") && senhaUsuario.equals("123456")) {
				mostrarAlerta(Alert.AlertType.CONFIRMATION, "ACESSO PERMITIDO!", "LOGIN BEM SUCEDIDO");
			}
			else {
				mostrarAlerta(Alert.AlertType.ERROR, "Acesso Negado!", "Usúario ou senha inválido");
			}
		}
		
	}
	
	private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
		Alert alerta = new Alert(tipo);
		alerta.setTitle(titulo);
		alerta.setContentText(mensagem);
		alerta.showAndWait();
	}
	
	@FXML
	private void fecharTelaLogin(ActionEvent event) {
		acpPalco = (Stage) btn_fechar.getScene().getWindow();
		acpPalco.close();
	}
	
	 private boolean verificarUsuarioSenha(String usuario, String senha) throws SQLException {
	        Connection conexao = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        boolean usuarioValido = false;

	        try {
	            conexao = ClasseConexao.conectar();
	            String sql = "SELECT * FROM tabelasenha WHERE usuario = ? AND senha = ?";
	            stmt = conexao.prepareStatement(sql);
	            stmt.setString(1, usuario);
	            stmt.setString(2, senha);
	            rs = stmt.executeQuery();

	            if (rs.next()) {
	                usuarioValido = true;
	            }
	        } finally {
	            if (rs != null) {
	                rs.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }
	            ClasseConexao.fechar(conexao);
	        }

	        return usuarioValido;
	    }
}
