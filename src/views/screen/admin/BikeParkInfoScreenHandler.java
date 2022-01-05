package views.screen.admin;

import com.google.gson.Gson;
import entities.BikePark;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

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

    private List<BikePark> bikeParks;

    public BikeParkInfoScreenHandler(Stage stage,String screenPath, BikePark bikePark , List<BikePark> bikeParks, String method) throws IOException {
        super(stage,screenPath);
        this.bikePark = bikePark;
        this.bikeParks = bikeParks;
        if (method == "edit") {
            int index = bikeParks.indexOf(bikePark);
            editHandler(index);
        } else if (method == "add"){
            addHandler();
        } else {
            infoHandler();
        }
        btnDeleteBikePark.setOnMouseClicked(event -> {
            deleteBikePark();
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

    private void saveBikeParks(){
        Gson gson = new Gson();
        try {
            FileWriter fileWriter = new FileWriter("src/entities/data/bikeparks.json");
            gson.toJson(bikeParks, fileWriter);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateInfoBikePark(int index){
        bikeParks.get(index).setAddress(textAddress.getText());
        bikeParks.get(index).setName(textName.getText());
        bikeParks.get(index).setNumOfEBikes(Integer.parseInt(textEbikes.getText()));
        bikeParks.get(index).setNumOfBikes(Integer.parseInt(textBikes.getText()));
        bikeParks.get(index).setNumOfEmptyDocks(Integer.parseInt(textEmptyDocks.getText()));
    }

    private void addBikePark(String newCode){
        BikePark bikePark = new BikePark();
        bikePark.setCode(newCode);
        bikePark.setName(textName.getText());
        bikePark.setNumOfBikes(Integer.parseInt(textEbikes.getText()));
        bikePark.setAddress(textAddress.getText());
        bikePark.setNumOfEBikes(Integer.parseInt(textEbikes.getText()));
        bikePark.setNumOfEmptyDocks(Integer.parseInt(textEmptyDocks.getText()));
        bikeParks.add(bikePark);
        saveBikeParks();
    }

    private void showPopup(String title, String content){
        ButtonType buttonTypeCancel = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
        showAlert(Alert.AlertType.NONE, title, content,buttonTypeCancel );
    }

    private boolean validateNewBike(String newCode){
        BikePark bk = bikeParks.stream().filter(t -> t.getCode().equals(newCode)).findFirst().orElse(null);
        if (bk != null){
            showPopup("Fail","Bãi xe code " + newCode + " đã tồn tại");
            return false;
        }
        return true;
    }

    private void editHandler(int index){
        setInfo();
        setEditable(true);
        codeBikePark.setEditable(false);
        btnAddBikePark.setText("Lưu");
        btnAddBikePark.setOnMouseClicked(e -> {
            updateInfoBikePark(index);
            saveBikeParks();
            try {
                BikeParkManageScreenHandler bikeParkManageScreenHandler = new BikeParkManageScreenHandler(stage, Configs.ADMIN_SCREEN_PATH);
                bikeParkManageScreenHandler.show();
                showPopup("Success", "Bãi xe đã được chỉnh sửa");
            } catch (Exception exception){
                showPopup("Fail", "Unknown error");
            }
        });
    }

    private void addHandler(){
        setEditable(true);
        codeBikePark.setEditable(true);
        btnAddBikePark.setText("Thêm bãi xe");
        btnDeleteBikePark.setVisible(false);
        btnAddBikePark.setOnMouseClicked(e -> {
            try{
                String newCode = codeBikePark.getText();
                if (!validateNewBike(newCode)) return;
                addBikePark(newCode);
                BikeParkManageScreenHandler bikeParkManageScreenHandler = new BikeParkManageScreenHandler(stage, Configs.ADMIN_SCREEN_PATH);
                bikeParkManageScreenHandler.show();
                showPopup("Success","Bãi xe đã được thêm");
            } catch (Exception exception){
                showPopup("Fail", "Unknown error");
            }
        });
    }

    private void infoHandler(){
        setInfo();
        setEditable(false);
        codeBikePark.setEditable(false);
        btnAddBikePark.setVisible(false);
    }

    private void deleteBikePark(){
        bikeParks.remove(bikePark);
        saveBikeParks();
        try {
            BikeParkManageScreenHandler bikeParkManageScreenHandler = new BikeParkManageScreenHandler(stage, Configs.ADMIN_SCREEN_PATH);
            bikeParkManageScreenHandler.show();
            showPopup("Success", "Bãi xe đã được xóa");
        } catch (Exception exception){
            showPopup("Fail", "Unknown error");
        }
    }
}
