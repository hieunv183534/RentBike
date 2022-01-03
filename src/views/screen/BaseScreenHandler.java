package views.screen;

import controllers.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.screen.renter.RenterHomeScreenHandler;

import java.io.IOException;
import java.util.Optional;

public class BaseScreenHandler extends FXMLScreenHandler{
    protected FXMLLoader loader;
    protected AnchorPane content;
    private Scene scene;
    private BaseScreenHandler prev;
    protected final Stage stage;
    private BaseController bController;
    private BaseScreenHandler homeScreen;
    private Alert alert;

    private BaseScreenHandler(String screenPath) throws IOException {
        super(screenPath);
        this.stage = new Stage();
    }

    public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
        super(screenPath);
        this.stage = stage;
        this.stage.setResizable(false);
        this.loader = new FXMLLoader(getClass().getResource(screenPath));
        // Set this class as the controller
        this.loader.setController(this);
        this.content = (AnchorPane) loader.load();
    }

    public void setPreviousScreen(BaseScreenHandler prev) {
        this.prev = prev;
    }

    public BaseScreenHandler getPreviousScreen() {
        return this.prev;
    }

    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.content);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void setScreenTitle(String string) {
        this.stage.setTitle(string);
    }

    public void setBController(BaseController bController){
        this.bController = bController;
    }

    public BaseController getBController(){
        return this.bController;
    }

    public AnchorPane loadContentPane(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        AnchorPane newPane = loader.load();
        return newPane;
    }

    public void insertContent(AnchorPane paneMainContent, AnchorPane newPane){
        paneMainContent.getChildren().setAll(newPane);
        // Reset the anchors
        AnchorPane.setBottomAnchor(newPane, 0.0);
        AnchorPane.setLeftAnchor(newPane, 0.0);
        AnchorPane.setRightAnchor(newPane, 0.0);
        AnchorPane.setTopAnchor(newPane, 0.0);
    }

    public BaseScreenHandler getHomeScreen() {
        return homeScreen;
    }

    public void setHomeScreen(BaseScreenHandler homeScreen) {
        this.homeScreen = homeScreen;
    }

    public Optional<ButtonType> showAlert(Alert.AlertType alertType, String title, String content,ButtonType... buttonTypes){
        if(this.alert == null)
            this.alert = new Alert(alertType);
        else this.alert.setAlertType(alertType);
        this.alert.setTitle(title);
        this.alert.setContentText(content);
        this.alert.getButtonTypes().setAll(buttonTypes);
        Optional<ButtonType> result = this.alert.showAndWait();
        return result;
    }
}
