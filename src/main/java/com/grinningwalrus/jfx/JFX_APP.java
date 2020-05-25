package com.grinningwalrus.jfx;

import com.grinningwalrus.controller.BasicUserController;
import com.grinningwalrus.login.LoginController;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class JFX_APP extends Application {

    private static LoginController controller = new LoginController();
    private static BasicUserController usercontroller = new BasicUserController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("ViewTrips.fxml")));
        trip_list.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                System.out.println("List invalidated");
            }
        });
        trip_list.add(String.valueOf(76));
        primaryStage.setTitle("Mountain Climbing Project");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML private PasswordField pass_field;
    @FXML private TextField user_field;
    @FXML private Button login_button;
    @FXML private Button register_button;
    @FXML private Button admin_button;

    /**Trip list**/
    private ObservableList<String> trip_list = FXCollections.observableArrayList();

    @FXML
    void register(ActionEvent event)
    {
        controller.register(user_field.getText(), pass_field.getText());
    }

    @FXML
    void login(ActionEvent event)
    {
        String rank = controller.login(user_field.getText(), pass_field.getText());
        if(!rank.equalsIgnoreCase("error"))
        {
            try {
                if(rank.equalsIgnoreCase("admin"))
                    switch_scene(event, "Admin_menu.fxml");
                else
                    switch_scene(event, "Hiker_menu.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void access_panel(ActionEvent event)
    {
        try{
            switch_scene(event, "AdminPanel.fxml");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    void switch_scene(ActionEvent event, String panel_name) throws Exception
    {
        Parent new_window;
        new_window = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(panel_name)));
        Scene scene = new Scene(new_window);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
