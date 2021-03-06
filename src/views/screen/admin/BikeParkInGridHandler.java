package views.screen.admin;

import controllers.BikeParkController;
import entities.BikePark;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;

import java.io.IOException;

public class BikeParkInGridHandler extends FXMLScreenHandler {

    @FXML
    private Label lbAddress;

    @FXML
    private Label lbStatus;

    @FXML
    private Label lbName;

    @FXML
    private Button infoBikePark;

    @FXML
    private Button editBikePark;

    private BikePark bikePark;

    private BikeParkManageScreenHandler bikeParkManageScreenHandler;

    public BikeParkInGridHandler(String screenPath, BikePark bikePark, BikeParkManageScreenHandler bikeParkManageScreenHandler) throws IOException {
        super(screenPath);
        this.bikePark = bikePark;
        this.bikeParkManageScreenHandler = bikeParkManageScreenHandler;
        infoBikePark.setOnMouseClicked(e -> {
            try{
                BikeParkInfoScreenHandler bikeParkInfoScreenHandler = new BikeParkInfoScreenHandler(this.bikeParkManageScreenHandler.getStage(),
                        Configs.BIKE_PARK_INFO, bikePark, bikeParkManageScreenHandler.getBikeParkObjects(), "info");
                bikeParkInfoScreenHandler.show();
            } catch (Exception exception){
                exception.printStackTrace();
            }
        });
        editBikePark.setOnMouseClicked(e -> {
            try{
                BikeParkInfoScreenHandler bikeParkInfoScreenHandler = new BikeParkInfoScreenHandler(this.bikeParkManageScreenHandler.getStage(),
                        Configs.BIKE_PARK_INFO, bikePark, bikeParkManageScreenHandler.getBikeParkObjects(),"edit");
                bikeParkInfoScreenHandler.show();
            } catch (Exception exception){
                exception.printStackTrace();
            }
        });
        setInfo();
    }

    private void setInfo(){
        lbName.setText(bikePark.getName());
        lbAddress.setText(bikePark.getAddress());
        if (bikePark.getNumOfEmptyDocks() > 0) {
            lbStatus.setText("C??n ch??? tr???ng");
            lbStatus.setTextFill(Color.web("#03ab52"));
        } else {
            lbStatus.setText("???? h???t ch???");
            lbStatus.setTextFill(Color.web("#f44336"));
        }
    }
}
