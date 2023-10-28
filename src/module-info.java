module Tablita2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.desktop;
	requires java.sql;
	requires javafx.graphics;
	
	opens Controller to javafx.graphics , javafx.fxml;

	
}
