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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertBox extends Stage {
    Stage owner;
    Stage stage;
    BorderPane root;
    static boolean postStatus;
    private static int answer;

    public AlertBox(Stage owner, String title) {
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

        Scene scene = new Scene(root, 250, 100);
        setScene(scene);

        Group groupInDialog = new Group();
        groupInDialog.getChildren().add(new Label("Would you like to buy land ?"));
        root.setCenter(groupInDialog);


        Button yes = new Button("Yes");
        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {                
                postStatus = true;
                answer = 1;
                stage.close();
             
            }
        });


        Button no = new Button("No");
        no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
                postStatus = false;
                answer = 2;
                stage.close(); // Close the pop up only
            }

        });

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10);
        buttonPane.getChildren().addAll(yes, no);
        root.setBottom(buttonPane);

        stage.showAndWait();

    }

    public static boolean confirmTranactionPosting(Stage owner, String title) {

        new AlertBox(owner, title);

        return postStatus;
    }

    public int getAnswer() {
        return answer;
    }

    private void handleButtonAction(ActionEvent event) throws IOException {
         Button b = (Button) event.getSource();
        if (b.getText().equals("Yes")) {
            answer = 1;
        } else {
            answer = 2;
        }


    }

}
 