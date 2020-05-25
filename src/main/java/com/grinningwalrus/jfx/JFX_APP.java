package com.grinningwalrus.jfx;

import com.grinningwalrus.baseclasses.LeaderboardController;
import com.grinningwalrus.baseclasses.Trip;
import com.grinningwalrus.baseclasses.TripXMLController;
import com.grinningwalrus.controller.BasicUserController;
import com.grinningwalrus.login.LoginController;
import com.grinningwalrus.login.User;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class JFX_APP extends Application {

    private static LoginController controller = new LoginController();
    private static BasicUserController usercontroller = new BasicUserController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        TripXMLController.initialize(trip_obs_list);
        for(Trip t:trip_obs_list)
        {
            trip_string_obs_list.add(t.toString());
        }
        LeaderboardController.sort(controller.getUsers());
        for(User u:LeaderboardController.getLeaderboard())
        {
            leaderboard_obs_list.add(u.toString());
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("LoginScreen.fxml")));
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

    private static final ObservableList<Trip> trip_obs_list =
            FXCollections.observableArrayList();
    private static final ObservableList<String> trip_string_obs_list =
            FXCollections.observableArrayList();
    private static final ObservableList<String> leaderboard_obs_list =
            FXCollections.observableArrayList();

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

    @FXML
    void switch_trips(ActionEvent event)
    {
        try{
            switch_scene(event, "ViewTrips.fxml");
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
        if(panel_name.equalsIgnoreCase("viewtrips.fxml") || panel_name.equalsIgnoreCase("viewleaderboard.fxml"))
        {
            init_list(scene, panel_name);
        }
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    class Test_task extends TimerTask{
        @Override
        public void run() {
        }
    }

    private void init_list(Scene scene, String panel_name)
    {
        Timer timer = new Timer(true);
        Test_task my_task = new Test_task();
        final ListView<String> listView;
        if(panel_name.equalsIgnoreCase("viewtrips.fxml"))
            listView = new ListView<String>(trip_string_obs_list);
        else if(panel_name.equalsIgnoreCase("viewleaderboard.fxml"))
            listView = new ListView<String>(leaderboard_obs_list);
        else
            listView = null;

        if(listView != null)
        {
            listView.setPrefSize(200, 270);
            listView.setEditable(true);

            listView.setItems(trip_string_obs_list);
            ((StackPane) scene.getRoot().getChildrenUnmodifiable().get(0)).getChildren().add(listView);
            System.out.println(scene.getRoot().getChildrenUnmodifiable().get(0).toString());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
