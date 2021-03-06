package views.screen.renter;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

import java.util.ResourceBundle;

import controllers.PaymentController;
import controllers.ReturnBikeController;
import entities.Invoice;
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

	 private AnchorPane selectBikeParkScreen;
	 private AnchorPane bikeCodeFormScreen;
	 private AnchorPane invoiceScreen;
	 private AnchorPane paymentFormScreen;
	 private AnchorPane paymentSucessScreen;
	
	 private ReturnBikeController returnBikeController = new ReturnBikeController();
	 private PaymentController paymentController = new PaymentController();
	 
	 private ChoiceBox<String> choiceBoxBikePark;
	 
	public ReturnBikeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		this.selectBikeParkScreen = this.loadContentPane(Configs.SELECT_BIKEPARK_SCREEN_PATH);
		this.bikeCodeFormScreen = this.loadContentPane(Configs.BIKECODE_FORM_SCREEN_PATH);
		this.invoiceScreen= this.loadContentPane(Configs.INVOICE_SCREEN_PATH);
		this.paymentFormScreen = this.loadContentPane(Configs.PAYMENT_FORM_SCREEN_PATH);
		this.paymentSucessScreen = this.loadContentPane(Configs.PAYMENT_SUCCESS_SCREEN_PATH);
		this.initialSelectBikeParkScreen();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		menuItemHome.setTooltip(new Tooltip("Trang ch???"));
        menuItemViewBikes.setTooltip(new Tooltip("Xem b??i xe"));
        menuItemRentBike.setTooltip(new Tooltip("Thu?? xe"));
        menuItemReturnBike.setTooltip(new Tooltip("Tr??? xe"));

        menuItemRentBike.setOnAction(e -> {
        	getHomeScreen().show();
        });
        menuItemHome.setOnAction(e->{
            getHomeScreen().show();
        });		
	}
	
	public String selectBikeParkHandler() {
		return this.choiceBoxBikePark.getValue().toString();
	}
	
	public void initialSelectBikeParkScreen() {
		this.insertContent(this.mainContentPane, this.selectBikeParkScreen);
		this.choiceBoxBikePark = new ChoiceBox(FXCollections.observableArrayList(this.returnBikeController.getListNameBikeParks()));
		
		AnchorPane choiceBoxContainer = ((AnchorPane) this.selectBikeParkScreen.lookup("#choicebox-container"));
		choiceBoxContainer.getChildren().add(this.choiceBoxBikePark);
		
		this.selectBikeParkScreen.lookup("#btnConfirmBikePark").setOnMouseClicked(e -> {
			this.confirmBikePark(this.selectBikeParkHandler());
		});
		this.selectBikeParkScreen.lookup("#btnCancel").setOnMouseClicked(e->{
        	getHomeScreen().show();
        });        
	}
	
	public void initialBikeCodeFormScreen() {
		this.insertContent(this.mainContentPane, this.bikeCodeFormScreen);
		this.bikeCodeFormScreen.lookup("#btnConfirmBikeCode").setOnMouseClicked(e->{
            this.confirmBikeCode();
        });
        this.bikeCodeFormScreen.lookup("#btnCancel").setOnMouseClicked(e->{
        	getHomeScreen().show();
        });
	}
	
	
	public void initialInvoiceScreen() {
		this.insertContent(this.mainContentPane, this.invoiceScreen);
		this.invoiceScreen.lookup("#btnConfirmPayment").setOnMouseClicked(e->{
        	this.confirmGotoPayment();
        });
		Invoice invoice = returnBikeController.getInvoice();
        ((Label)this.invoiceScreen.lookup("#labelBikeCode")).setText(invoice.getCurrentBike().getBikeCode());
        ((Label)this.invoiceScreen.lookup("#labelBikeName")).setText(invoice.getCurrentBike().getName());
        ((Label)this.invoiceScreen.lookup("#labelDeposit")).setText(invoice.getDeposit() + "??");
        ((Label)this.invoiceScreen.lookup("#labelRentTime")).setText(String.valueOf(invoice.getRentTime()) + "ph??t");
        ((Label)this.invoiceScreen.lookup("#labelRentCost")).setText(String.valueOf(invoice.getRentCost()) + "??");
	}
	
	public void initialPaymentFormScreen() {
		this.insertContent(this.mainContentPane, this.paymentFormScreen);
		this.paymentFormScreen.lookup("#btnPay").setOnMouseClicked(e->{
        	this.confirmPayment();
        });
        this.paymentFormScreen.lookup("#btnCancel").setOnMouseClicked(e->{
        	getHomeScreen().show();
        });
	}
	
	public void initialPaymentSuccessScreen() {
		this.insertContent(this.mainContentPane, this.paymentSucessScreen);
		this.paymentSucessScreen.lookup("#btnHome").setOnMouseClicked(e->{
        	this.gotoHome();
        });
		Invoice invoice = returnBikeController.getInvoice();
        ((Label)this.paymentSucessScreen.lookup("#labelBikeCode")).setText(invoice.getCurrentBike().getBikeCode());
        ((Label)this.paymentSucessScreen.lookup("#labelBikeName")).setText(invoice.getCurrentBike().getName());
        ((Label)this.paymentSucessScreen.lookup("#labelRentTime")).setText(String.valueOf(invoice.getRentTime()) + "ph??t");
        ((Label)this.paymentSucessScreen.lookup("#labelRentCost")).setText(String.valueOf(invoice.getRentCost()) + "??");
	}
		
	public void confirmBikePark(String value) {
		if (this.returnBikeController.checkBikeParkAvailable(value)) {
			this.initialBikeCodeFormScreen();
		} else {
			ButtonType buttonTypeCancel = new ButtonType("????ng", ButtonBar.ButtonData.CANCEL_CLOSE);
	        this.showAlert(Alert.AlertType.NONE, "L???i", "B??i xe ???? ?????y!", buttonTypeCancel);
		}
		
	}

	public String inputBikeCodeHandler() {
		String bikeCode = ((TextField) this.bikeCodeFormScreen.lookup("#inputBikeCode")).getText();
		return bikeCode;
	}
	
	public void confirmBikeCode() {
		if (this.returnBikeController.checkBikeCodeAvailable(this.inputBikeCodeHandler())) {
			this.initialInvoiceScreen();
		} else {
			ButtonType buttonTypeCancel = new ButtonType("????ng", ButtonBar.ButtonData.CANCEL_CLOSE);
	        this.showAlert(Alert.AlertType.NONE, "L???i", "Xe kh??ng ???????c s??? d???ng ho???c kh??ng t???n t???i !", buttonTypeCancel);
		}
		
	}
	public void confirmGotoPayment() {
		this.initialPaymentFormScreen();
	}
	
	public Map<String, String> inputPaymentForm() {
		Map<String, String> creditCardInput = new Hashtable<String, String>();
		String cardCode = ((TextField)this.paymentFormScreen.lookup("#inputCardCode")).getText();
        String owner = ((TextField)this.paymentFormScreen.lookup("#inputOwner")).getText();
        String cvvCode = ((TextField)this.paymentFormScreen.lookup("#inputCvvCode")).getText();
        String dateExpired = ((TextField)this.paymentFormScreen.lookup("#inputDateExpired")).getText();
        creditCardInput.put("cardCode", cardCode);
        creditCardInput.put("owner", owner);
        creditCardInput.put("cvvCode", cvvCode);
        creditCardInput.put("dateExpired", dateExpired);
		return creditCardInput;
	}
	
	public void confirmPayment() {
		Map<String, String> input = this.inputPaymentForm();
        if(!this.returnBikeController.validatePaymentForm(input)){
            ButtonType buttonTypeCancel = new ButtonType("????ng", ButtonBar.ButtonData.CANCEL_CLOSE);
            this.showAlert(Alert.AlertType.NONE, "C???nh b??o", "H??y ??i???n ?????y ????? th??ng tin thanh to??n tr?????c!",buttonTypeCancel);
        } else {
            Map<String, String> response = paymentController.pay(10,"payment",
                    input.get("cardCode"), input.get("owner"), input.get("dateExpired"), input.get("cvvCode"));
            
            ButtonType buttonTypeCancel = new ButtonType("????ng", ButtonBar.ButtonData.CANCEL_CLOSE);
            this.showAlert(Alert.AlertType.NONE, response.get("RESULT"),response.get("MESSAGE"), buttonTypeCancel);
            if(response.get("RESULT").equals("PAYMENT SUCCESSFUL!")){
            	this.returnBikeController.saveDataState();
            	Map<String, String> res = this.paymentController.refund(this.returnBikeController.getInvoice().getDeposit(),"refund deposit",
            		input.get("cardCode"), input.get("owner"), input.get("dateExpired"), input.get("cvvCode"));
            	this.initialPaymentSuccessScreen();
            }
        }
	}
	public void gotoHome() {
		this.getHomeScreen().show();
	}
}
