package views.screen.renter;

import controllers.PaymentController;
import controllers.RentBikeController;
import controllers.RenterHomeController;
import entities.Bike;
import javafx.beans.property.StringProperty;
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
        this.viewInputBikeCode();
    }

    public RentBikeController getBController(){
        return(RentBikeController) super.getBController();
    }

    public RenterHomeScreenHandler getHomeScreen(){
        return(RenterHomeScreenHandler) super.getHomeScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuItemHome.setTooltip(new Tooltip("Trang chủ"));
        menuItemViewBikes.setTooltip(new Tooltip("Xem bãi xe"));
        menuItemRentBike.setTooltip(new Tooltip("Thuê xe"));
        menuItemReturnBike.setTooltip(new Tooltip("Trả xe"));
        menuItemHome.setOnAction(e->{
            getHomeScreen().returnToHome();
        });
        menuItemReturnBike.setOnAction(e->{
            getHomeScreen().goToReturnBike();
        });
    }

    /**
     * chuyển sang màn hình nhập mã xe
     * @throws IOException
     */
    public void viewInputBikeCode() throws IOException {
        if(this.inputBikeCodeContent == null){
            this.inputBikeCodeContent = this.loadContentPane(Configs.RENTBIKE_INPUT_BIKECODE_CONTENT_PATH);
            // init event cho màn hình nhập code
            this.inputBikeCodeContent.lookup("#btnConfirmBikeCode").setOnMouseClicked(e->{
                try {
                    btnConfirmBikeCodeOnClick();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            this.inputBikeCodeContent.lookup("#btnCancel").setOnMouseClicked(e->{
                btnCancelOnClick();
            });
            this.inputBikeCodeContent.lookup("#btnGuide").setOnMouseClicked(e->{
                btnGuideOnClick();
            });
        } else{
            ((TextField) this.inputBikeCodeContent.lookup("#inputBikeCode")).setText("");
        }
        this.insertContent(this.mainContentPane, this.inputBikeCodeContent);
    }

    /**
     * chuyển sang màn hình xem thông tin thuê xe
     * @throws IOException
     */
    public void  viewBikeRentalInfo(String bikeCode, String bikeName, int depositAmount, String rentalInfo) throws IOException {
        if(this.bikeRentalInfoContent == null){
            this.bikeRentalInfoContent = this.loadContentPane(Configs.RENTBIKE_BIKE_RENTAL_INFO_CONTENT_PATH);
            // init event cho màn hình thông tin thuê xe
            this.bikeRentalInfoContent.lookup("#btnConfirmDeposit").setOnMouseClicked(e->{
                try {
                    btnConfirmDepositOnClick();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            this.bikeRentalInfoContent.lookup("#btnRentOther").setOnMouseClicked(e->{
                try {
                    btnRentOtherOnClick();
                } catch (IOException ex) {
                    ex.printStackTrace(); 
                }
            });
            this.bikeRentalInfoContent.lookup("#btnCancel").setOnMouseClicked(e->{
                btnCancelOnClick();
            });
            this.bikeRentalInfoContent.lookup("#btnGuide").setOnMouseClicked(e->{
                btnGuideOnClick();
            });
        }
        ((Label)this.bikeRentalInfoContent.lookup("#labelBikeCode")).setText("Mã xe: "+ bikeCode);
        ((Label)this.bikeRentalInfoContent.lookup("#labelBikeName")).setText("Loại xe: "+ bikeName);
        ((Label)this.bikeRentalInfoContent.lookup("#labelBikeDepositAmount")).setText("Tiền đặt cọc: "+depositAmount +"đ");
        ((Label)this.bikeRentalInfoContent.lookup("#labelBikeRentalInfo")).setText(rentalInfo);
        this.insertContent(this.mainContentPane, this.bikeRentalInfoContent);
    }

    /**
     * chuyển sang màn hình thanh toán đặt cọc
     * @throws IOException
     */
    public void viewDeposit(int amount) throws IOException {
        if(this.depositContent == null){
            this.depositContent = this.loadContentPane(Configs.RENTBIKE_DEPOSIT_CONTENT_PATH);
            // init event cho màn hình thanh toán
            this.depositContent.lookup("#btnPay").setOnMouseClicked(e->{
                try {
                    btnPayOnClick();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            this.depositContent.lookup("#btnRentOther").setOnMouseClicked(e->{
                try {
                    btnRentOtherOnClick();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            this.depositContent.lookup("#btnCancel").setOnMouseClicked(e->{
                btnCancelOnClick();
            });
            this.depositContent.lookup("#btnGuide").setOnMouseClicked(e->{
                btnGuideOnClick();
            });
        }
        ((Label)this.depositContent.lookup("#labelAmount")).setText(amount+ "đ");
        this.insertContent(this.mainContentPane, this.depositContent);
    }

    /**
     * chuyển sang màn hình sử dụng xe
     * @throws IOException
     */
    public void  viewUseBikeProgress(String bikeCode, String bikeName,
                                     StringProperty rentedTime,  StringProperty totalMoney) throws IOException {
        if(this.useBikeProgressContent == null){
            this.useBikeProgressContent = this.loadContentPane(Configs.RENTBIKE_USE_BIKE_PROGRESS_CONTENT_PATH);
            // init event cho màn hình thanh toán
            this.useBikeProgressContent.lookup("#btnReturnBike").setOnMouseClicked(e->{
                // code to return bike
            });
            this.useBikeProgressContent.lookup("#btnRentOther").setOnMouseClicked(e->{
                try {
                    btnRentOtherOnClick();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            this.useBikeProgressContent.lookup("#btnCancel").setOnMouseClicked(e->{
                btnCancelOnClick();
            });
            this.useBikeProgressContent.lookup("#btnGuide").setOnMouseClicked(e->{
                btnGuideOnClick();
            });
        }
        ((Label)this.useBikeProgressContent.lookup("#labelBikeCode")).setText(bikeCode);
        ((Label)this.useBikeProgressContent.lookup("#labelBikeName")).setText(bikeName);
        ((Label)this.useBikeProgressContent.lookup("#labelRentedTime")).textProperty().bind(rentedTime);
        ((Label)this.useBikeProgressContent.lookup("#labelTotalRent")).textProperty().bind(totalMoney);
        this.insertContent(mainContentPane, this.useBikeProgressContent);
    }

    public void btnCancelOnClick(){
        ButtonType buttonTypeCancel = new ButtonType("Hủy", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonTypeYes = new ButtonType("Đồng ý", ButtonBar.ButtonData.YES);
        Optional<ButtonType> result = showAlert(Alert.AlertType.NONE, "Hủy bỏ?",
                "Bạn có muốn hủy bỏ việc thuê xe và trở về trang chủ?",buttonTypeCancel,buttonTypeYes );
        if (result.get()== buttonTypeYes){
            getHomeScreen().show();
        }
    }

    public void btnGuideOnClick(){
        ButtonType buttonTypeCancel = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
        showAlert(Alert.AlertType.NONE, "Hướng dẫn", "Hãy đọc kỹ hướng dẫn trước khi dùng. OK",buttonTypeCancel );
    }

    public void btnRentOtherOnClick() throws IOException {
        ButtonType buttonTypeCancel = new ButtonType("Hủy", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonTypeYes = new ButtonType("Đồng ý", ButtonBar.ButtonData.YES);
        Optional<ButtonType> result = showAlert(Alert.AlertType.NONE,"Thuê xe khác?",
                "Bạn có muốn thuê xe khác?",buttonTypeCancel, buttonTypeYes);
        if (result.get()== buttonTypeYes){
            this.viewInputBikeCode();
        }
    }

    public void btnConfirmBikeCodeOnClick() throws IOException {
        String bikeCode = ((TextField) this.inputBikeCodeContent.lookup("#inputBikeCode")).getText();
        switch (getBController().checkBikeCode(bikeCode)){
            case 0:
                ButtonType buttonTypeCancel = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
                showAlert(Alert.AlertType.NONE, "Thông báo!", "Mã xe không tồn tại!", buttonTypeCancel);
                break;
            case 1:
                Bike bike = getBController().getMyBike();
                this.viewBikeRentalInfo(bike.getBikeCode(),bike.getName(),
                        getBController().getDepositAmount(), getBController().getBikeRentalInfo());
                break;
            case 2:
                buttonTypeCancel = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
                showAlert(Alert.AlertType.NONE, "Thông báo!",
                        "Xe này đã được thuê. Vui lòng chọn xe khác!", buttonTypeCancel);
                break;
        }
    }

    public void btnConfirmDepositOnClick() throws IOException {
        this.viewDeposit(getBController().getDepositAmount());
    }

    public void btnPayOnClick() throws IOException {
        String cardCode = ((TextField)this.depositContent.lookup("#inputCardCode")).getText();
        String owner = ((TextField)this.depositContent.lookup("#inputOwner")).getText();
        String cvvCode = ((TextField)this.depositContent.lookup("#inputCvvCode")).getText();
        String dateExpired = ((TextField)this.depositContent.lookup("#inputDateExpired")).getText();

        if(cardCode.isEmpty() || owner.isEmpty() || cvvCode.isEmpty() || dateExpired .isEmpty()){
            ButtonType buttonTypeCancel = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
            showAlert(Alert.AlertType.NONE, "Cảnh báo", "Hãy điền đầy đủ thông tin thanh toán trước!",buttonTypeCancel );
        }else{
            PaymentController paymentController = new PaymentController();
            Map<String, String> response = paymentController.pay(getBController().getDepositAmount(),
                    getBController().getDepositTransactionContent(),
                    cardCode, owner,dateExpired, cvvCode);
            System.out.println(response);

            ButtonType buttonTypeCancel = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
            showAlert(Alert.AlertType.NONE, response.get("RESULT"),response.get("MESSAGE"), buttonTypeCancel );

            if(response.get("RESULT").equals("PAYMENT SUCCESSFUL!")){
                getBController().rent();
                this.viewUseBikeProgress(getBController().getMyBike().getBikeCode(), getBController().getMyBike().getName(),
                        getBController().totalTimeProperty(), getBController().totalRentProperty());
            }
            System.out.println(paymentController.getSuccessPayment());
        }
    }
}
