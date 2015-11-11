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
public class storePopup extends Stage {
    Stage owner;
    Stage stage;
    BorderPane root;
    private String answer;

    /**
     *
     * @param owner
     * @param title
     */
    public storePopup(Stage owner, String title) {
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

        Scene scene = new Scene(root, 250, 100);
        setScene(scene);

        Group groupInDialog = new Group();
        groupInDialog.getChildren().add(new Label("Do you want to buy or sell?"));
        root.setCenter(groupInDialog);


        Button yes = new Button("Buy");
        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                answer = "Buy";
                stage.close();
           
            }
        });


        Button no = new Button("Sell");
        no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                answer = "Sell";
                stage.close(); 
            }

        });

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10);
        buttonPane.getChildren().addAll(yes, no);
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
 