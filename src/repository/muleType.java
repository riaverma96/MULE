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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Sumi
 */
public class muleType extends Stage {
    Stage owner;
    Stage stage;
    BorderPane root;
    private String answer;

    /**
     *
     * @param owner
     * @param title
     */
    public muleType(Stage owner, String title) {
        root = new BorderPane();
        stage = this;
        this.owner = owner;
        initModality(Modality.APPLICATION_MODAL);
        initOwner(owner);
        initStyle(StageStyle.UTILITY);
        setTitle(title);
        setContents();
       
    }

    /**
     *
     */
    public void setContents() {

        Scene scene = new Scene(root, 650, 300);
        setScene(scene);

        Group groupInDialog = new Group();
        groupInDialog.getChildren().add(new Label("What type of MULE would you like to buy?"
                + " Choose either a Food, Energy, Ore or "
                + "Crystite MULE?\n Food:125 \n Energy: 150 \n Ore: 175"));
        root.setCenter(groupInDialog);
        


        Button yes = new Button("Food");
        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                answer = "Food";
                stage.close();
           
            }
        });


        Button no = new Button("Energy");
        no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                answer = "Energy";
                stage.close(); 
            }

        });
        
        Button no1 = new Button("Ore");
        no1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                answer = "Ore";
                stage.close(); 
            }

        });
        
       Button no2 = new Button("Crystite");
        no1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                answer = "Crystite";
                stage.close(); 
            }

        });

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10);
        buttonPane.getChildren().addAll(yes, no, no1, no2);
        root.setBottom(buttonPane);
        stage.showAndWait();

    }

    /**
     *
     * @return
     */
    public String getAnswer() {
        return answer;
    }

}
 