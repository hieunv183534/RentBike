package views.screen.renter;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import controllers.PaymentController;
import controllers.ReturnBikeController;
import entities.Bike;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

public class ReturnBikeScreenHandler extends BaseScreenHandler implements Initializable {
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
	 
	 private Alert alert;

	 private AnchorPane returnBikeInputBikeParkScreenHandler;
	 private AnchorPane returnBikeInputBikeCodeScreenHandler;
	 private AnchorPane returnBikeInfoScreenHandler;
	 private AnchorPane paymentScreenHandler;
	 private AnchorPane paymentSucessScreenHandler;
	
	 private ReturnBikeController returnBikeController = new ReturnBikeController();
	 
	 private ChoiceBox choiceBoxBikePark = new ChoiceBox(FXCollections.observableArrayList(this.returnBikeController.getListNameBikeParks()));
	 
	public ReturnBikeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		this.returnBikeInputBikeParkScreenHandler = this.loadContentPane(Configs.RETURNBIKE_INPUT_BIKEPARK_PATH);
		this.returnBikeInputBikeCodeScreenHandler = this.loadContentPane(Configs.RETURNBIKE_INPUT_BIKECODE_PATH);
		this.returnBikeInfoScreenHandler = this.loadContentPane(Configs.RETURNBIKE_INFO_PATH);
		this.paymentScreenHandler = this.loadContentPane(Configs.RETURNBIKE_PAYMENT_PATH);
		this.paymentSucessScreenHandler = this.loadContentPane(Configs.RETURNBIKE_PAYMENT_SUCCESS_PATH);
		this.insertContent(this.mainContentPane, this.returnBikeInputBikeParkScreenHandler);
		this.initEventForContents();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		menuItemHome.setTooltip(new Tooltip("Trang chủ"));
        menuItemViewBikes.setTooltip(new Tooltip("Xem bãi xe"));
        menuItemRentBike.setTooltip(new Tooltip("Thuê xe"));
        menuItemReturnBike.setTooltip(new Tooltip("Trả xe"));

        menuItemHome.setOnAction(e->{
            getHomeScreen().returnToHome();
        });

		menuItemRentBike.setOnAction(e->{
			getHomeScreen().goToRentBike();
		});
	}

	public RenterHomeScreenHandler getHomeScreen(){
		return(RenterHomeScreenHandler) super.getHomeScreen();
	}
	
	public void showErrorAlert(String titleText, String contentText) {
		this.alert = new Alert(Alert.AlertType.NONE);
		this.alert.setTitle(titleText);
		this.alert.setContentText(contentText);
		ButtonType buttonTypeCancel = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
        this.alert.getButtonTypes().setAll(buttonTypeCancel);
        this.alert.initOwner(this.stage);
        this.alert.show();
	}
	
	public void initEventForContents() {
		//initial event for screen input bike park
		AnchorPane choiceBoxContainer = ((AnchorPane) this.returnBikeInputBikeParkScreenHandler.lookup("#choicebox-container"));
		choiceBoxContainer.getChildren().add(this.choiceBoxBikePark);
		
		this.returnBikeInputBikeParkScreenHandler.lookup("#btnConfirmBikePark").setOnMouseClicked(e -> {
			btnConfirmBikeParkOnclick(this.choiceBoxBikePark.getValue().toString());
		});
		
		// initial event for screen input bike code
        this.returnBikeInputBikeCodeScreenHandler.lookup("#btnConfirmBikeCode").setOnMouseClicked(e->{
            btnConfirmBikeCodeOnClick();
        });
        this.returnBikeInputBikeCodeScreenHandler.lookup("#btnCancel").setOnMouseClicked(e->{
            //btnCancelOnClick();
        });
        this.returnBikeInputBikeCodeScreenHandler.lookup("#btnGuide").setOnMouseClicked(e->{
            //btnGuideOnClick();
        });
        
//        this.returnBikeCodeContent.getChildren().add(this.cb);
        
 
        //initial event for information of rent bike screen
        this.returnBikeInfoScreenHandler.lookup("#btnConfirmPayment").setOnMouseClicked(e->{
        	btnConfirmPaymentOnclick();
        });
        //initial event for payment screen
        this.paymentScreenHandler.lookup("#btnPay").setOnMouseClicked(e->{
        	btnPaymentOnclick();
        });
        
        //initial event payment success screen
        this.paymentSucessScreenHandler.lookup("#btnHome").setOnMouseClicked(e->{
        	btnHomeOnclick();
        });
	}
	
	public void btnConfirmBikeParkOnclick(String value) {
		if (this.returnBikeController.checkBikePark(value)) {
			this.insertContent(this.mainContentPane, this.returnBikeInputBikeCodeScreenHandler);
		} else {
			this.showErrorAlert("Lỗi", "Bãi xe đã đầy!");
		}
		
	}
	
	
	public void btnConfirmBikeCodeOnClick() {
		String bikeCode = ((TextField) this.returnBikeInputBikeCodeScreenHandler.lookup("#inputBikeCode")).getText();
		if (this.returnBikeController.checkBikeCode(bikeCode)) {
			this.insertContent(this.mainContentPane, this.returnBikeInfoScreenHandler);
			Bike bike = this.returnBikeController.getCurrentBike();
	        ((Label)this.returnBikeInfoScreenHandler.lookup("#labelBikeCode")).setText(bike.getBikeCode());
	        ((Label)this.returnBikeInfoScreenHandler.lookup("#labelBikeName")).setText(bike.getName());
	        ((Label)this.returnBikeInfoScreenHandler.lookup("#labelRentTime")).setText(String.valueOf(this.returnBikeController.getRentTime()) + "phút");
	        ((Label)this.returnBikeInfoScreenHandler.lookup("#labelRentCost")).setText(String.valueOf(this.returnBikeController.getRentCost()) + "đ");
		} else {
			this.showErrorAlert("Lỗi", "Xe không được sử dụng hoặc không tồn tại !");
		}
		
	}
	public void btnConfirmPaymentOnclick() {
		this.insertContent(this.mainContentPane, this.paymentScreenHandler);
	}
	
	public void btnPaymentOnclick() {
		String cardCode = ((TextField)this.paymentScreenHandler.lookup("#inputCardCode")).getText();
        String owner = ((TextField)this.paymentScreenHandler.lookup("#inputOwner")).getText();
        String cvvCode = ((TextField)this.paymentScreenHandler.lookup("#inputCvvCode")).getText();
        String dateExpired = ((TextField)this.paymentScreenHandler.lookup("#inputDateExpired")).getText();
        PaymentController paymentController = new PaymentController();
        Map<String, String> response = paymentController.pay(10,"payment",
                cardCode, owner,dateExpired, cvvCode);
        
        this.showErrorAlert(response.get("RESULT"), response.get("MESSAGE"));
    
        if(response.get("RESULT").equals("PAYMENT SUCCESSFUL!")){
        	this.insertContent(this.mainContentPane, this.paymentSucessScreenHandler);
        }
	}
	public void btnHomeOnclick() {
		this.getHomeScreen().show();
	}
}
