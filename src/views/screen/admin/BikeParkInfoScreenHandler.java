package views.screen.admin;

import com.google.gson.Gson;
import entities.BikePark;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.FileWriter;
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
    private TextField codeBikePark;

    @FXML
    private TextField textEmptyDocks;

    @FXML
    private Button btnAddBikePark;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnDeleteBikePark;

    private BikePark bikePark;

    public BikeParkInfoScreenHandler(Stage stage,String screenPath, BikePark bikePark , List<BikePark> bikeParks, String method) throws IOException {
        super(stage,screenPath);
        this.bikePark = bikePark;
        int index = bikeParks.indexOf(bikePark);
        if (method == "edit") {
            setInfo();
            setEditable(true);
            codeBikePark.setEditable(false);
            btnAddBikePark.setText("Lưu");
            btnAddBikePark.setOnMouseClicked(e -> {
                updateInfoBikePark(bikeParks, index);
                saveBikeParks(bikeParks);
                try {
                    BikeParkManageScreenHandler bikeParkManageScreenHandler = new BikeParkManageScreenHandler(stage, Configs.ADMIN_SCREEN_PATH);
                    bikeParkManageScreenHandler.show();
                    PopupScreen.success("Success");
                } catch (Exception exception){
                    try {
                        PopupScreen.error("Error");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        }
        else if (method == "add"){
            setEditable(true);
            codeBikePark.setEditable(true);
            btnAddBikePark.setText("Thêm bãi xe");
            btnDeleteBikePark.setVisible(false);
            btnAddBikePark.setOnMouseClicked(e -> {
                try{
                    String newCode = codeBikePark.getText();
                    BikePark bk = bikeParks.stream().filter(t -> t.getCode().equals(newCode)).findFirst().orElse(null);
                    if (bk != null){
                        PopupScreen.error("Bike park code " + codeBikePark.getText() + " already exist");
                        return;
                    }
                    BikePark bikePark1 = new BikePark();
                    bikePark1.setCode(newCode);
                    bikePark1.setName(textName.getText());
                    bikePark1.setNumOfBikes(Integer.parseInt(textEbikes.getText()));
                    bikePark1.setAddress(textAddress.getText());
                    bikePark1.setNumOfEBikes(Integer.parseInt(textEbikes.getText()));
                    bikePark1.setNumOfEmptyDocks(Integer.parseInt(textEmptyDocks.getText()));
                    bikeParks.add(bikePark1);
                    saveBikeParks(bikeParks);
                    BikeParkManageScreenHandler bikeParkManageScreenHandler = new BikeParkManageScreenHandler(stage, Configs.ADMIN_SCREEN_PATH);
                    bikeParkManageScreenHandler.show();
                    PopupScreen.success("Success");
                } catch (Exception exception){
                    try {
                        PopupScreen.error("Error");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        } else {
            setInfo();
            setEditable(false);
            codeBikePark.setEditable(false);
            btnAddBikePark.setVisible(false);
        }
        btnDeleteBikePark.setOnMouseClicked(event -> {
            bikeParks.remove(bikePark);
            saveBikeParks(bikeParks);
            try {
                BikeParkManageScreenHandler bikeParkManageScreenHandler = new BikeParkManageScreenHandler(stage, Configs.ADMIN_SCREEN_PATH);
                bikeParkManageScreenHandler.show();
                PopupScreen.success("Success");
            } catch (Exception exception){
                try {
                    PopupScreen.error("Error");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        btnCancel.setOnMouseClicked(event -> {
            try {
                BikeParkManageScreenHandler bikeParkManageScreenHandler = new BikeParkManageScreenHandler(stage, Configs.ADMIN_SCREEN_PATH);
                bikeParkManageScreenHandler.show();
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
        textEmptyDocks.setEditable(b);
    }
    private void setInfo(){
        codeBikePark.setText(bikePark.getCode());
        textAddress.setText(bikePark.getAddress());
        textName.setText(bikePark.getName());
        textBikes.setText(String.valueOf(bikePark.getNumOfBikes()));
        textEbikes.setText(String.valueOf(bikePark.getNumOfEBikes()));
        textEmptyDocks.setText(String.valueOf(bikePark.getNumOfEmptyDocks()));
    }

    private void saveBikeParks(List<BikePark> bikeParks){
        Gson gson = new Gson();
        try {
            FileWriter fileWriter = new FileWriter("src/entities/data/bikeparks.json");
            gson.toJson(bikeParks, fileWriter);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateInfoBikePark(List<BikePark> bikeParks, int index){
        bikeParks.get(index).setAddress(textAddress.getText());
        bikeParks.get(index).setName(textName.getText());
        bikeParks.get(index).setNumOfEBikes(Integer.parseInt(textEbikes.getText()));
        bikeParks.get(index).setNumOfBikes(Integer.parseInt(textBikes.getText()));
        bikeParks.get(index).setNumOfEmptyDocks(Integer.parseInt(textEmptyDocks.getText()));
    }
}
