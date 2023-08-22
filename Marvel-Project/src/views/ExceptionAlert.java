package views;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ExceptionAlert 
{
	public static void display (String title, String message)
	{
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setWidth(500);
		window.setHeight(250);
		
		Label label = new Label ();
		label.setText (message);
		Button close = new Button("OK");
		close.setFont(new Font ("Times New Roman", 14));
		close.setOnAction(e -> window.close());
		
		VBox layout = new VBox (10);
		layout.getChildren().addAll(label, close);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene (layout);
		window.setScene(scene);
		window.showAndWait();
		
	}
}
