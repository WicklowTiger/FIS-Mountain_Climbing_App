package com.grinningwalrus.jfx;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.grinningwalrus.controller.BasicUserController;
import com.grinningwalrus.login.LoginController;
import com.grinningwalrus.login.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class JFX_APP extends Application {

    private static LoginController controller = new LoginController();
    private static BasicUserController usercontroller = new BasicUserController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("ViewTrips.fxml")));
        primaryStage.setTitle("Mountain Climbing Project");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML private PasswordField pass_field;
    @FXML private TextField user_field;
    @FXML private Button login_button;
    @FXML private Button register_button;

    @FXML
    void login(ActionEvent event)
    {
        if(controller.login(user_field.getText(), pass_field.getText()))
        {
            try {
                switch_scene(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void register(ActionEvent event)
    {
        controller.register(user_field.getText(), pass_field.getText());
    }

    void switch_scene(ActionEvent event) throws Exception
    {
        Parent secondwindow = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("SecondWindow.fxml")));
        Scene scene = new Scene(secondwindow);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
