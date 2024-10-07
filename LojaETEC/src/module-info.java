module LojaETEC {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires javafx.graphics;
	
	opens br.com.etec.model to javafx.graphics, javafx.fxml;
}
