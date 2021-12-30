package views.screen.admin;

import entities.BikePark;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;

import java.io.IOException;
import java.util.List;

public class BikeParkInfoScreenHandler extends BaseScreenHandler {

    @FXML
    private TextField textName;

    @FXML
    private TextField textAddress;

    @FXML
    private TextField textBikes;

    @FXML
    private TextField textEbikes;

    @FXML
    private TextField textTwinBikes;

    @FXML
    private TextField textEmptyDocks;

    @FXML
    private Button btnAddBikePark;

    @FXML
    private Button btnCancel;

    private BikePark bikePark;

    public BikeParkInfoScreenHandler(Stage stage,String screenPath, BikePark bikePark , List<BikePark> bikeParks, String method) throws IOException {
        super(stage,screenPath);
        this.bikePark = bikePark;
        setInfo();
        if (method == "edit") {
            setEditable(true);
            btnAddBikePark.setText("Lưu");
        }
        else if (method == "add"){
            setEditable(false);
            btnAddBikePark.setText("Thêm bãi xe");
        } else {
            setEditable(false);
            btnAddBikePark.setVisible(false);
        }
        btnCancel.setOnMouseClicked(event -> {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setEditable(boolean b){
        textAddress.setEditable(b);
        textName.setEditable(b);
        textEbikes.setEditable(b);
        textBikes.setEditable(b);
        textTwinBikes.setEditable(b);
        textEmptyDocks.setEditable(b);
    }
    private void setInfo(){
        textAddress.setText(bikePark.getAddress());
        textName.setText(bikePark.getName());
        textBikes.setText(String.valueOf(bikePark.getNumOfBikes()));
        textEbikes.setText(String.valueOf(bikePark.getNumOfEBikes()));
        textEmptyDocks.setText(String.valueOf(bikePark.getNumOfEmptyDocks()));
    }
}
