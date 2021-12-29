package views.screen.renter;

import controllers.PaymentController;
import controllers.RentBikeController;
import controllers.RenterHomeController;
import entities.Bike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author HieuNV
 */
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
            btnConfirmBikeCodeOnClick();
        });
        this.inputBikeCodeContent.lookup("#btnCancel").setOnMouseClicked(e->{
            btnCancelOnClick();
        });
        this.inputBikeCodeContent.lookup("#btnGuide").setOnMouseClicked(e->{
            btnGuideOnClick();
        });

        // init event cho màn hình thông tin thuê xe
        this.bikeRentalInfoContent.lookup("#btnConfirmDeposit").setOnMouseClicked(e->{
            btnConfirmDepositOnClick();
        });
        this.bikeRentalInfoContent.lookup("#btnRentOther").setOnMouseClicked(e->{
            btnRentOtherOnClick();
        });
        this.bikeRentalInfoContent.lookup("#btnCancel").setOnMouseClicked(e->{
            btnCancelOnClick();
        });
        this.bikeRentalInfoContent.lookup("#btnGuide").setOnMouseClicked(e->{
            btnGuideOnClick();
        });

        // init event cho màn hình thanh toán
        this.depositContent.lookup("#btnPay").setOnMouseClicked(e->{
            btnPayOnClick();
        });
        this.depositContent.lookup("#btnRentOther").setOnMouseClicked(e->{
            btnRentOtherOnClick();
        });
        this.depositContent.lookup("#btnCancel").setOnMouseClicked(e->{
            btnCancelOnClick();
        });
        this.depositContent.lookup("#btnGuide").setOnMouseClicked(e->{
            btnGuideOnClick();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuItemHome.setTooltip(new Tooltip("Trang chủ"));
        menuItemViewBikes.setTooltip(new Tooltip("Xem bãi xe"));
        menuItemRentBike.setTooltip(new Tooltip("Thuê xe"));
        menuItemReturnBike.setTooltip(new Tooltip("Trả xe"));

        menuItemHome.setOnAction(e->{
            getHomeScreen().show();
        });
    }

    public RentBikeController getBController(){
        return(RentBikeController) super.getBController();
    }

    public void btnConfirmBikeCodeOnClick() {
        String bikeCode = ((TextField) this.inputBikeCodeContent.lookup("#inputBikeCode")).getText();
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
                Bike bike = getBController().getMyBike();
                ((Label)this.bikeRentalInfoContent.lookup("#labelBikeCode")).setText("Mã xe: "+ bike.getBikeCode());
                ((Label)this.bikeRentalInfoContent.lookup("#labelBikeName")).setText("Loại xe: "+ bike.getName());
                ((Label)this.bikeRentalInfoContent.lookup("#labelBikeDepositAmount")).setText("Tiền đặt cọc: " + getBController().getDepositAmount()+"đ");
                ((Label)this.bikeRentalInfoContent.lookup("#labelBikeRentalInfo")).setText(getBController().getBikeRentalInfo());
                this.insertContent(this.mainContentPane, this.bikeRentalInfoContent);
                break;
            case 2:
                Alert alert1 = new Alert(Alert.AlertType.NONE);
                alert1.setTitle("Thông báo!");
                alert1.setContentText("Xe này đã được thuê. Vui lòng chọn xe khác!");
                ButtonType buttonTypeCancel1 = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert1.getButtonTypes().setAll(buttonTypeCancel1);
                alert1.show();
                break;
        }
    }

    public void btnCancelOnClick(){
        Alert alert= new Alert(Alert.AlertType.NONE);
        alert.setContentText("Hủy bỏ?");
        alert.setContentText("Bạn có muốn hủy bỏ việc thuê xe và trở về trang chủ?");
        ButtonType buttonTypeCancel = new ButtonType("Hủy", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonTypeYes = new ButtonType("Đồng ý", ButtonBar.ButtonData.YES);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()== buttonTypeYes){
            getHomeScreen().show();
        }
    }

    public void btnGuideOnClick(){
        System.out.println("Hướng dẫn sử dụng");
    }

    public void btnConfirmDepositOnClick(){
        ((Label)this.depositContent.lookup("#labelAmount")).setText(getBController().getDepositAmount()+ "đ");
        this.insertContent(this.mainContentPane, this.depositContent);
    }

    public void btnRentOtherOnClick(){
        Alert alert= new Alert(Alert.AlertType.NONE);
        alert.setContentText("Thuê xe khác?");
        alert.setContentText("Bạn có muốn thuê xe khác?");
        ButtonType buttonTypeCancel = new ButtonType("Hủy", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonTypeYes = new ButtonType("Đồng ý", ButtonBar.ButtonData.YES);
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()== buttonTypeYes){
            this.insertContent(this.mainContentPane, this.inputBikeCodeContent);
        }
    }

    public void btnPayOnClick(){
        String cardCode = ((TextField)this.depositContent.lookup("#inputCardCode")).getText();
        String owner = ((TextField)this.depositContent.lookup("#inputOwner")).getText();
        String cvvCode = ((TextField)this.depositContent.lookup("#inputCvvCode")).getText();
        String dateExpired = ((TextField)this.depositContent.lookup("#inputDateExpired")).getText();
        PaymentController paymentController = new PaymentController();
        Map<String, String> response = paymentController.pay(getBController().getDepositAmount(),
                getBController().getDepositTransactionContent(),
                cardCode, owner,dateExpired, cvvCode);
        System.out.println(response);

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(response.get("RESULT"));
        alert.setContentText(response.get("MESSAGE"));
        ButtonType buttonTypeCancel = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeCancel);
        alert.show();

        if(response.get("RESULT").equals("PAYMENT SUCCESSFUL!")){
            getBController().rent();
            this.insertContent(mainContentPane, this.useBikeProgressContent);
            ((Label)this.useBikeProgressContent.lookup("#labelBikeCode")).setText(getBController().getMyBike().getBikeCode());
            ((Label)this.useBikeProgressContent.lookup("#labelBikeName")).setText(getBController().getMyBike().getName());
            ((Label)this.useBikeProgressContent.lookup("#labelRentedTime")).textProperty().bind(getBController().totalTimeProperty());
            ((Label)this.useBikeProgressContent.lookup("#labelTotalRent")).textProperty().bind(getBController().totalRentProperty());
        }
        System.out.println(paymentController.getSuccessPayment());

        ((RenterHomeController)getHomeScreen().getBController()).setBikes(getBController().getBikes());
        ((RenterHomeController)getHomeScreen().getBController()).setThisBike(getBController().getMyBike());
//        // muốn lấy ds bike từ home
//        ((RenterHomeController)getHomeScreen().getBController()).getBikes()
    }


}
