module Tablita2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.desktop;
	requires java.sql;
	requires javafx.graphics;
	requires jbcrypt;
	 opens Model to javafx.base;
	
	opens Controller to javafx.graphics , javafx.fxml;

	
}
