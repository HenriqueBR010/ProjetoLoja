module LojaETEC {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens br.com.etec.model to javafx.graphics, javafx.fxml;
}
