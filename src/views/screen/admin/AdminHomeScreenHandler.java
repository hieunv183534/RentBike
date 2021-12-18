package views.screen.admin;

import entities.Account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomeScreenHandler extends BaseScreenHandler{

    @FXML
    private Text labelUserName;

    private Account account;

//    @FXML
//    private ImageView aimsImage;
//
//    @FXML
//    private ImageView cartImage;
//
//    @FXML
//    private VBox vboxMedia1;
//
//    @FXML
//    private VBox vboxMedia2;
//
//    @FXML
//    private VBox vboxMedia3;
//
//    @FXML
//    private HBox hboxMedia;
//
//    @FXML
//    private SplitMenuButton splitMenuBtnSearch;

    public AdminHomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public void setUsername(String username){
        labelUserName.setText(username);
        show();
    }
}
