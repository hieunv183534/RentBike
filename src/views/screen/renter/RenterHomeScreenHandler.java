package views.screen.renter;

import controllers.RentBikeController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class RenterHomeScreenHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private Button menuItemHome;

    @FXML
    private Button menuItemViewBikes;

    @FXML
    private Button menuItemRentBike;

    @FXML
    private Button menuItemReturnBike;

    @FXML
    private Button btnRent;

    @FXML
    private Text labelUserName;

    private String userName;


    public RenterHomeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public RenterHomeScreenHandler(Stage stage, String screenPath, String userName) throws IOException {
        this(stage,screenPath);
        this.userName = userName;
        this.labelUserName.setText(this.userName.toUpperCase(Locale.ROOT));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuItemHome.setTooltip(new Tooltip("Trang chủ"));
        menuItemViewBikes.setTooltip(new Tooltip("Xem bãi xe"));
        menuItemRentBike.setTooltip(new Tooltip("Thuê xe"));
        menuItemReturnBike.setTooltip(new Tooltip("Trả xe"));

        menuItemRentBike.setOnAction(e->{
            goToRentBike();
        });

        btnRent.setOnAction(e->{
            goToRentBike();
        });
    }

    public void goToRentBike(){
        try {
            RentBikeScreenHandler rentBikeScreen = new RentBikeScreenHandler(this.stage, Configs.RENTBIKE_LAYOUT_SCREEN_PATH);
            rentBikeScreen.setScreenTitle("Rent Bike Screen");
            rentBikeScreen.setBController(new RentBikeController());
            rentBikeScreen.setHomeScreen(this);
            rentBikeScreen.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
