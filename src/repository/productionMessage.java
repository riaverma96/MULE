package repository;


/**
 * @author Diya
 */

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class productionMessage extends Stage {
    Stage owner;
    Stage stage;
    BorderPane root;
    private String answer;
    private int numberAnswer;
    public productionMessage(Stage owner, String title) {
        root = new BorderPane();
        stage = this;
        this.owner = owner;
        initModality(Modality.APPLICATION_MODAL);
        initOwner(owner);
        initStyle(StageStyle.UTILITY);
        setTitle(title);
        setContents();
       
    }

    public void setContents() {

      stage.setTitle("How much??");
     
      Label label1 = new Label("Not Enough money for production");
     
  
     
       final Label label = new Label("Click Ok to close");
     
      Button buttonEnter = new Button("Ok");
      buttonEnter.setOnAction(new EventHandler<ActionEvent>() {
 
          @Override
          public void handle(ActionEvent arg0) {
              
              stage.close();
              
          }
      });
 
      HBox hBox = new HBox();
      hBox.getChildren().addAll(label1, buttonEnter);
      hBox.setSpacing(10);
      hBox.setMaxHeight(25);
     
      VBox vBox = new VBox();
      vBox.getChildren().addAll(hBox, label);
     
      StackPane root = new StackPane();
      root.getChildren().add(vBox);
      stage.setScene(new Scene(root, 300, 75));



      stage.showAndWait();

    }
    public int getNumberAnswer() {
        return numberAnswer;
    }

}
 