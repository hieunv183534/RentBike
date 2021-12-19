package views.screen.renter;

import controllers.LoginController;
import controllers.RentBikeController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RentBikeScreenHandler extends BaseScreenHandler implements Initializable {
    @FXML
    private AnchorPane mainContentPane;

    @FXML
    private Button menuItemHome;

    @FXML
    private Button menuItemViewBikes;

    @FXML
    private Button menuItemRentBike;

    @FXML
    private Button menuItemReturnBike;

    private AnchorPane inputBikeCodeContent;
    private AnchorPane bikeRentalInfoContent;
    private AnchorPane depositContent;
    private AnchorPane useBikeProgressContent;

    /**
     * @param stage
     * @param screenPath
     * @throws IOException
     * @author HieuNV
     */
    public RentBikeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);

        this.inputBikeCodeContent = this.loadContentPane(Configs.RENTBIKE_INPUT_BIKECODE_CONTENT_PATH);
        this.bikeRentalInfoContent = this.loadContentPane(Configs.RENTBIKE_BIKE_RENTAL_INFO_CONTENT_PATH);
        this.depositContent = this.loadContentPane(Configs.RENTBIKE_DEPOSIT_CONTENT_PATH);
        this.useBikeProgressContent = this.loadContentPane(Configs.RENTBIKE_USE_BIKE_PROGRESS_CONTENT_PATH);

        this.insertContent(this.mainContentPane, this.inputBikeCodeContent);

        initEventForContents();
    }

    /**
     * @author HieuNV
     */
    public void initEventForContents(){
        // init event cho màn hình nhập code
        this.inputBikeCodeContent.lookup("#btnConfirmBikeCode").setOnMouseClicked(e->{
            TextField inputBikeCode = (TextField) this.inputBikeCodeContent.lookup("#inputBikeCode");
            String bikeCode = inputBikeCode.getText();
            switch (getBController().checkBikeCode(bikeCode)){
                case 0:
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setTitle("Thông báo!");
                    alert.setContentText("Mã xe không tồn tại!");
                    ButtonType buttonTypeCancel = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(buttonTypeCancel);
                    alert.show();
                    break;
                case 1:
                    this.insertContent(this.mainContentPane, this.bikeRentalInfoContent);
                    break;
                case 2:
                    Alert alert1 = new Alert(Alert.AlertType.NONE);
                    alert1.setTitle("Thông báo!");
                    alert1.setContentText("Xe đang trong quá trình được thuê!");
                    ButtonType buttonTypeCancel1 = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert1.getButtonTypes().setAll(buttonTypeCancel1);
                    alert1.show();
                    break;
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuItemHome.setTooltip(new Tooltip("Trang chủ"));
        menuItemViewBikes.setTooltip(new Tooltip("Xem bãi xe"));
        menuItemRentBike.setTooltip(new Tooltip("Thuê xe"));
        menuItemReturnBike.setTooltip(new Tooltip("Trả xe"));
    }

    public RentBikeController getBController(){
        return(RentBikeController) super.getBController();
    }
}
