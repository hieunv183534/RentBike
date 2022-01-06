package views.screen.admin;

import controllers.BaseController;
import controllers.BikeParkController;
import entities.Bike;
import entities.BikePark;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

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

    private String method;

    public BikeParkController getBController(){
        return (BikeParkController) super.getBController();
    }

    public BikeParkInfoScreenHandler(Stage stage, String screenPath, BikePark bikePark, List<BikePark> bikeParks, String method) throws IOException {
        super(stage,screenPath);
        this.method = method;
        setBController(new BikeParkController());
        btnDeleteBikePark.setOnMouseClicked(event -> {
            deleteBikePark(bikeParks,bikePark);
        });
        if (method == "edit") {
            editSaveHandler(bikeParks,bikePark);
        } else if (method == "add"){
            addSaveHandler(bikeParks);
        } else {
            infoHandler(bikePark);
        }
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
    private void setInfo(BikePark bikePark){
        codeBikePark.setText(bikePark.getCode());
        textAddress.setText(bikePark.getAddress());
        textName.setText(bikePark.getName());
        textBikes.setText(String.valueOf(bikePark.getNumOfBikes()));
        textEbikes.setText(String.valueOf(bikePark.getNumOfEBikes()));
        textEmptyDocks.setText(String.valueOf(bikePark.getNumOfEmptyDocks()));
    }

    private void showPopup(String title, String content){
        ButtonType buttonTypeCancel = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
        showAlert(Alert.AlertType.NONE, title, content,buttonTypeCancel );
    }

    private void editSaveHandler(List<BikePark> bikeParks,BikePark bikePark){
        setInfo(bikePark);
        setEditable(true);
        codeBikePark.setEditable(false);
        btnAddBikePark.setText("Lưu");
        btnAddBikePark.setOnMouseClicked(e -> {
            if (!getBController().validateInfoBikePark(textEbikes.getText(),
                    textBikes.getText(), textEmptyDocks.getText())){
                showPopup("Fail","Thông tin nhập vào không hợp lệ");
                return;
            }
            getBController().editBikePark(bikeParks, bikePark, textAddress.getText(), textName.getText(), textEbikes.getText(),
                    textBikes.getText(), textEmptyDocks.getText());
            try {
                BikeParkManageScreenHandler bikeParkManageScreenHandler = new BikeParkManageScreenHandler(stage, Configs.ADMIN_SCREEN_PATH);
                bikeParkManageScreenHandler.show();
                showPopup("Success", "Bãi xe đã được chỉnh sửa");
            } catch (Exception exception){
                showPopup("Fail", "Unknown error");
            }
        });
    }

    private void addSaveHandler(List<BikePark> bikeParks){
        setEditable(true);
        codeBikePark.setEditable(true);
        btnAddBikePark.setText("Thêm bãi xe");
        btnDeleteBikePark.setVisible(false);
        btnAddBikePark.setOnMouseClicked(e -> {
            try{
                String newCode = codeBikePark.getText();
                if (!getBController().validateNewCode(bikeParks,newCode)){
                    showPopup("Fail","Bãi xe code " + newCode + " đã tồn tại");
                    return;
                }
                if (!getBController().validateInfoBikePark(textEbikes.getText(),
                        textBikes.getText(), textEmptyDocks.getText())){
                    showPopup("Fail","Thông tin nhập vào không hợp lệ");
                    return;
                }
                getBController().addBikePark(bikeParks,newCode,textAddress.getText(), textName.getText(), textEbikes.getText(),
                       textBikes.getText(), textEmptyDocks.getText());
                BikeParkManageScreenHandler bikeParkManageScreenHandler = new BikeParkManageScreenHandler(stage, Configs.ADMIN_SCREEN_PATH);
                bikeParkManageScreenHandler.show();
                showPopup("Success","Bãi xe đã được thêm");
            } catch (Exception exception){
                showPopup("Fail", "Unknown error");
            }
        });
    }

    private void infoHandler(BikePark bikePark){
        setInfo(bikePark);
        setEditable(false);
        codeBikePark.setEditable(false);
        btnAddBikePark.setVisible(false);
    }

    private void deleteBikePark(List<BikePark> bikeParks,BikePark bikePark){
        if (getBController().deleteBikePark(bikeParks,bikePark)) {
            BikeParkManageScreenHandler bikeParkManageScreenHandler = null;
            try {
                bikeParkManageScreenHandler = new BikeParkManageScreenHandler(stage, Configs.ADMIN_SCREEN_PATH);
            } catch (IOException e) {
                showPopup("Fail", "Unknown error");
            }
            bikeParkManageScreenHandler.show();
            showPopup("Success", "Bãi xe đã được xóa");
        } else {
            showPopup("Fail", "Unknown error");
        }
    }
}
