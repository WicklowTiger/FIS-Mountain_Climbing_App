package com.grinningwalrus.jfx;

import com.grinningwalrus.baseclasses.*;
import com.grinningwalrus.controller.BasicUserController;
import com.grinningwalrus.login.LoginController;
import com.grinningwalrus.login.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class JFX_APP extends Application {

    private static LoginController controller = new LoginController();
    private static BasicUserController usercontroller = new BasicUserController();
    private static int image_index = 1;
    private static int guide_index = 1;
    private static Scene scene;

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
        RecordXMLController.initialize(record_obs_list);
        for(Record r:record_obs_list)
        {
            record_string_obs_list.add(r.toString());
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("LoginScreen.fxml")));
        primaryStage.setTitle("Mountain Climbing Project");
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML private PasswordField pass_field;
    @FXML private TextField user_field;
    @FXML private Button login_button;
    @FXML private Button register_button;
    @FXML private Button admin_button;
    @FXML private ImageView gallery_image;

    private static final ObservableList<Trip> trip_obs_list =
            FXCollections.observableArrayList();
    private static final ObservableList<String> trip_string_obs_list =
            FXCollections.observableArrayList();
    private static final ObservableList<String> leaderboard_obs_list =
            FXCollections.observableArrayList();
    private static final ObservableList<Record> record_obs_list =
            FXCollections.observableArrayList();
    private static final ObservableList<String> record_string_obs_list =
            FXCollections.observableArrayList();

    private static void copyFile(String source, String dest) throws IOException {
        Path sourcepath = Paths.get("classes/" + source);
        Path destinationepath = Paths.get("classes/" + dest);
        Files.copy(sourcepath, destinationepath, StandardCopyOption.REPLACE_EXISTING);
    }


    @FXML
    void register(ActionEvent event)
    {
        if(controller.register(user_field.getText(), pass_field.getText()).equalsIgnoreCase("exists"))
            BasicUserController.message_box("Username already exists");
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
                else if(rank.equalsIgnoreCase("triporganizer"))
                    switch_scene(event, "Trip_org_menu.fxml");
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

    @FXML
    void apply_for_trip(ActionEvent event)
    {
        ListView trips_list = (ListView)((StackPane)scene.getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().get(0);
        int trip_index = trips_list.getSelectionModel().getSelectedIndex();
        if(trip_index != -1)
        {
            if (TripXMLController.getTrips().get(trip_index).getSigned_up_users().contains(BasicUserController.getUser().getUsername())) {
                BasicUserController.message_box("You are already signed up for that trip!");
            } else {
                TripXMLController.getTrips().get(trip_index).getSigned_up_users().add(BasicUserController.getUser().getUsername());
                trip_string_obs_list.set(trip_index, TripXMLController.getTrips().get(trip_index).toString() + " Signed up for this");
            }
            TripXMLController.updateXML();
        }
    }

    @FXML
    void cancel_apply(ActionEvent event)
    {
        ListView trips_list = (ListView)((StackPane)scene.getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().get(0);
        int trip_index = trips_list.getSelectionModel().getSelectedIndex();
        if(trip_index != -1)
        {
            if (!TripXMLController.getTrips().get(trip_index).getSigned_up_users().contains(BasicUserController.getUser().getUsername())) {
                BasicUserController.message_box("You are not signed up for that trip!");
            } else {
                TripXMLController.getTrips().get(trip_index).getSigned_up_users().remove(BasicUserController.getUser().getUsername());
                trip_string_obs_list.set(trip_index, TripXMLController.getTrips().get(trip_index).toString());
            }
            TripXMLController.updateXML();
        }
    }

    @FXML
    void switch_leaderboard(ActionEvent event)
    {
        try{
            switch_scene(event, "ViewLeaderBoard.fxml");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    @FXML
    void switch_records(ActionEvent event)
    {
        try {
            switch_scene(event, "SubmitRecord.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    @FXML
    void switch_profile(ActionEvent event)
    {
        try {
            switch_scene(event, "MyProfile.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    @FXML
    void switch_pending(ActionEvent event)
    {
        try {
            switch_scene(event, "viewPendingRecords.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    @FXML
    void accept_record(ActionEvent event)
    {
        ListView records_list = (ListView)((StackPane)scene.getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().get(0);
        int record_index = records_list.getSelectionModel().getSelectedIndex();
        if(record_index != -1)
        {
            String username = RecordXMLController.getRecords().get(record_index).getName();
            for(User u:controller.getUsers())
            {
                if(u.getName().equalsIgnoreCase(username))
                {
                    u.setScore(u.getScore() + 1);
                    switch(u.getScore())
                    {
                        case 1: {
                            u.setHiker_rank("Advanced");
                            break;
                        }
                        case 5: {
                            u.setHiker_rank("Experienced");
                            break;
                        }
                        case 25: {
                            u.setHiker_rank("Mountain man");
                            break;
                        }
                    }
                    BasicUserController.message_box("You have approved " + username + "'s record.");
                    RecordXMLController.getRecords().remove(record_index);
                    record_string_obs_list.remove(record_index);
                    RecordXMLController.updateXML();
                    controller.updateXML();
                    return;
                }
            }
        }
    }

    @FXML
    void decline_record(ActionEvent event)
    {
        ListView records_list = (ListView)((StackPane)scene.getRoot().getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().get(0);
        int record_index = records_list.getSelectionModel().getSelectedIndex();
        if(record_index != -1)
        {
            String username = RecordXMLController.getRecords().get(record_index).getName();
            BasicUserController.message_box("You have declined " + username + "'s record.");
            RecordXMLController.getRecords().remove(record_index);
            RecordXMLController.updateXML();
            record_string_obs_list.remove(record_index);
        }
    }

    @FXML
    void submit_record(ActionEvent event)
    {
        String location = ((TextField) scene.getRoot().getChildrenUnmodifiable().get(0)).getText();
        String altitude = ((TextField) scene.getRoot().getChildrenUnmodifiable().get(1)).getText();
        for(Record r:RecordXMLController.getRecords())
        {
            if(r.getName().equalsIgnoreCase(BasicUserController.getUser().getUsername()) && r.getAltitude().equalsIgnoreCase(altitude))
            {
                BasicUserController.message_box("You have already submitted that record!");
                return;
            }
        }
        RecordXMLController.getRecords().add(new Record(BasicUserController.getUser().getUsername(), location, altitude));
        RecordXMLController.updateXML();
    }

    @FXML
    void switch_manage_trips(ActionEvent event)
    {
        try {
            switch_scene(event, "ManageTrips.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    @FXML
    void add_trip(ActionEvent event)
    {
        String trip_name = ((TextField)scene.getRoot().getChildrenUnmodifiable().get(0)).getText();
        String location_name = ((TextField)scene.getRoot().getChildrenUnmodifiable().get(1)).getText();
        String date_text = ((TextField)scene.getRoot().getChildrenUnmodifiable().get(2)).getText();
        for(Trip t:TripXMLController.getTrips())
        {
            if (t.getName().equalsIgnoreCase(trip_name)) {
                BasicUserController.message_box("That trip already exists");
                return;
            }
        }
        Trip new_trip = new Trip(trip_name, location_name, date_text);
        TripXMLController.getTrips().add(new_trip);
        trip_obs_list.add(new_trip);
        trip_string_obs_list.add(new_trip.toString());
        TripXMLController.updateXML();
        BasicUserController.message_box("You have added a new trip!");
    }

    @FXML
    void change_trip(ActionEvent event)
    {
        String trip_name = ((TextField)scene.getRoot().getChildrenUnmodifiable().get(0)).getText();
        String location_name = ((TextField)scene.getRoot().getChildrenUnmodifiable().get(1)).getText();
        String date_text = ((TextField)scene.getRoot().getChildrenUnmodifiable().get(2)).getText();
        for(Trip t:TripXMLController.getTrips())
        {
            if (t.getName().equalsIgnoreCase(trip_name)) {
                t.setDate(date_text);
                t.setLocation(location_name);
                TripXMLController.updateXML();
                trip_obs_list.setAll(TripXMLController.getTrips());
                trip_string_obs_list.clear();
                for(Trip s:trip_obs_list)
                {
                    trip_string_obs_list.add(s.toString());
                }
                BasicUserController.message_box("You have changed a trip!");
                return;
            }
        }
        BasicUserController.message_box("That trip name does not exist!");
    }

    @FXML
    void delete_trip(ActionEvent event)
    {
        String trip_name = ((TextField)scene.getRoot().getChildrenUnmodifiable().get(0)).getText();
        for(Trip t:TripXMLController.getTrips())
        {
            if (t.getName().equalsIgnoreCase(trip_name)) {
                TripXMLController.getTrips().remove(t);
                TripXMLController.updateXML();
                trip_string_obs_list.remove(t.toString());
                BasicUserController.message_box("You have deleted a trip!");
                return;
            }
        }
        BasicUserController.message_box("That trip name does not exist!");
    }

    @FXML
    void switch_promote(ActionEvent event)
    {
        try {
            switch_scene(event, "PromoteDemote.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    @FXML
    void promote_user(ActionEvent event)
    {
        String username = ((TextField) scene.getRoot().getChildrenUnmodifiable().get(0)).getText();
        for(User u:controller.getUsers())
        {
            if(u.getName().equalsIgnoreCase(username))
            {
                switch(u.getHiker_rank())
                {
                    case "Amateur":
                    {
                        u.setHiker_rank("Advanced");
                        BasicUserController.message_box("You have promoted " + username + " to Advanced Hiker rank.");
                        break;
                    }
                    case "Advanced":
                    {
                        u.setHiker_rank("Experienced");
                        BasicUserController.message_box("You have promoted " + username + " to Experienced Hiker rank.");
                        break;
                    }
                    case "Experienced":
                    {
                        u.setHiker_rank("Mountain Man");
                        BasicUserController.message_box("You have promoted " + username + " to Mountain Man Hiker rank.");
                        break;
                    }
                    case "Mountain Man":
                    {
                        BasicUserController.message_box("You cannot promote " + username + " to a higher rank.");
                        break;
                    }
                }
                controller.updateXML();
                return;
            }
        }
    }

    @FXML
    void demote_user(ActionEvent event)
    {
        String username = ((TextField) scene.getRoot().getChildrenUnmodifiable().get(0)).getText();
        for(User u:controller.getUsers())
        {
            if(u.getName().equalsIgnoreCase(username))
            {
                switch(u.getHiker_rank())
                {
                    case "Amateur":
                    {
                        BasicUserController.message_box("You cannot demote " + username + " to a lower rank.");
                        break;
                    }
                    case "Advanced":
                    {
                        u.setHiker_rank("Amateur");
                        BasicUserController.message_box("You have demoted " + username + " to Amateur Hiker rank.");
                        break;
                    }
                    case "Experienced":
                    {
                        u.setHiker_rank("Advanced");
                        BasicUserController.message_box("You have demoted " + username + " to Advanced Hiker rank.");
                        break;
                    }
                    case "Mountain Man":
                    {
                        u.setHiker_rank("Experienced");
                        BasicUserController.message_box("You have demoted " + username + " to Experienced Hiker rank.");
                        break;
                    }
                }
                controller.updateXML();
                return;
            }
        }
    }

    @FXML
    void switch_guides(ActionEvent event)
    {
        try {
            switch_scene(event, "ViewGuides.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    @FXML
    void next_guide(ActionEvent event)
    {
        ImageView image = (ImageView)((AnchorPane)scene.getRoot().getChildrenUnmodifiable().get(0)).getChildren().get(4);
        guide_index += 1;
        Image Image_to_change;
        try {
            Image_to_change = new Image("images/guide_" + guide_index + ".png");
        }
        catch(Exception e) {
            guide_index -= 1;
            return;
        }
        image.setImage(Image_to_change);
    }

    @FXML
    void previous_guide(ActionEvent event)
    {
        ImageView image = (ImageView)((AnchorPane)scene.getRoot().getChildrenUnmodifiable().get(0)).getChildren().get(4);
        guide_index -= 1;
        Image Image_to_change;
        try {
            Image_to_change = new Image("images/guide_" + guide_index + ".png");
        }
        catch(Exception e) {
            guide_index += 1;
            return;
        }
        image.setImage(Image_to_change);
    }

    @FXML
    void switch_gallery(ActionEvent event) {
        try {
            switch_scene(event, "ViewGallery.fxml");
            image_index = 1;
            guide_index = 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    @FXML
    void next_image(ActionEvent event)
    {
        ImageView image = (ImageView)((AnchorPane)scene.getRoot().getChildrenUnmodifiable().get(0)).getChildren().get(4);
        image_index += 1;
        Image Image_to_change;
        try {
            Image_to_change = new Image("images/gallery_" + image_index + ".png");
        }
        catch(Exception e) {
            image_index -= 1;
            return;
        }
        image.setImage(Image_to_change);
    }

    @FXML
    void previous_image(ActionEvent event)
    {
        ImageView image = (ImageView)((AnchorPane)scene.getRoot().getChildrenUnmodifiable().get(0)).getChildren().get(4);
        image_index -= 1;
        Image Image_to_change;
        try {
            Image_to_change = new Image("images/gallery_" + image_index + ".png");
        }
        catch(Exception e) {
            image_index += 1;
            return;
        }
        image.setImage(Image_to_change);
    }

    @FXML
    void submit_photo(ActionEvent event)
    {
        for(User u:controller.getUsers())
        {
            if(u.getName().equalsIgnoreCase(BasicUserController.getUser().getUsername()))
            {
                if(!u.getHiker_rank().equalsIgnoreCase("mountain man") && !u.getHiker_rank().equalsIgnoreCase("experienced"))
                {
                    BasicUserController.message_box("You have to be a higher rank in order to do that!");
                    return;
                }
            }
        }
        String image_text = "";
        try {
            image_text = ((TextField) ((AnchorPane) scene.getRoot().getChildrenUnmodifiable().get(0)).getChildrenUnmodifiable().get(8)).getText();
            Image image_to_add = new Image(image_text);
        }
        catch(Exception e)
        {
            BasicUserController.message_box("That photo does not exist" + image_text);
            return;
        }
        int i = 1;
        String photo_name = "";
        while(true)
        {
            try
            {
                Image test_image = new Image("images/gallery_" + i + ".png");
                i += 1;
            }
            catch(Exception e)
            {
                photo_name = "gallery_" + i + ".png";
                break;
            }
        }
        try {
            copyFile(image_text,"images/" + photo_name);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            BasicUserController.message_box("Something went wrong!");
            return;
        }
        BasicUserController.message_box("Photo added.");
    }

    @FXML
    void submit_guide(ActionEvent event)
    {
        for(User u:controller.getUsers())
        {
            if(u.getName().equalsIgnoreCase(BasicUserController.getUser().getUsername()))
            {
                if(!u.getHiker_rank().equalsIgnoreCase("mountain man"))
                {
                    BasicUserController.message_box("You have to be a higher rank in order to do that!");
                    return;
                }
            }
        }
        String image_text = "";
        try {
            image_text = ((TextField) ((AnchorPane) scene.getRoot().getChildrenUnmodifiable().get(0)).getChildrenUnmodifiable().get(8)).getText();
            Image image_to_add = new Image(image_text);
        }
        catch(Exception e)
        {
            BasicUserController.message_box("That guide does not exist" + image_text);
            return;
        }
        int i = 1;
        String photo_name = "";
        while(true)
        {
            try
            {
                Image test_image = new Image("images/guide_" + i + ".png");
                i += 1;
            }
            catch(Exception e)
            {
                photo_name = "guide_" + i + ".png";
                break;
            }
        }
        try {
            copyFile(image_text,"images/" + photo_name);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            BasicUserController.message_box("Something went wrong!");
            return;
        }
        BasicUserController.message_box("Guide added.");
    }

    @FXML
    void back_button(ActionEvent event)
    {
        String menu_string = "";
        switch (BasicUserController.getUser().getRank())
        {
            case "triporganizer":
                menu_string = "Trip_org_menu.fxml";
                break;
            case "hiker":
                menu_string = "Hiker_menu.fxml";
                break;
            case "admin":
                menu_string = "Admin_menu.fxml";
                break;
        }
        try
        {
            switch_scene(event, menu_string);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    void switch_scene(ActionEvent event, String panel_name) throws Exception
    {
        Parent new_window;
        new_window = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(panel_name)));
        scene = new Scene(new_window);
        if(panel_name.equalsIgnoreCase("viewtrips.fxml") || panel_name.equalsIgnoreCase("viewleaderboard.fxml")
           || panel_name.equalsIgnoreCase("viewPendingRecords.fxml"))
        {
            init_list(panel_name);
        }
        if(panel_name.equalsIgnoreCase("MyProfile.fxml"))
        {
            Text user_text = (Text)scene.getRoot().getChildrenUnmodifiable().get(5);
            Text rank_text = (Text)scene.getRoot().getChildrenUnmodifiable().get(6);
            Text score_text = (Text)scene.getRoot().getChildrenUnmodifiable().get(7);
            for(User u:controller.getUsers())
            {
                if(u.getName().equalsIgnoreCase(BasicUserController.getUser().getUsername()))
                {
                    user_text.setText(u.getName());
                    if(u.getRank().equalsIgnoreCase("hiker"))
                        rank_text.setText(u.getHiker_rank() + " " + u.getRank());
                    else
                        rank_text.setText(u.getRank());
                    score_text.setText(u.getScore().toString());
                    break;
                }
            }

        }
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private void init_list(String panel_name)
    {
        Timer timer = new Timer(true);
        final ListView<String> listView;
        if(panel_name.equalsIgnoreCase("viewtrips.fxml"))
            listView = new ListView<String>(trip_string_obs_list);
        else if(panel_name.equalsIgnoreCase("viewleaderboard.fxml"))
            listView = new ListView<String>(leaderboard_obs_list);
        else if(panel_name.equalsIgnoreCase("viewPendingRecords.fxml"))
            listView = new ListView<String>(record_string_obs_list);
        else
            listView = null;

        if(listView != null)
        {
            listView.setPrefSize(200, 270);
            listView.setEditable(true);

            if(panel_name.equalsIgnoreCase("viewtrips.fxml"))
            {
                for(int i = 0; i < trip_string_obs_list.size(); i++)
                {
                    if(TripXMLController.getTrips().get(i).getSigned_up_users().contains(BasicUserController.getUser().getUsername()))
                    {
                        trip_string_obs_list.set(i, TripXMLController.getTrips().get(i).toString() + " Signed up for this");
                    }
                }
                listView.setItems(trip_string_obs_list);
            }
            else if(panel_name.equalsIgnoreCase("viewleaderboard.fxml"))
                listView.setItems(leaderboard_obs_list);
            else if(panel_name.equalsIgnoreCase("viewPendingRecords.fxml"))
                listView.setItems(record_string_obs_list);
            ((StackPane) scene.getRoot().getChildrenUnmodifiable().get(1)).getChildren().add(listView);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
