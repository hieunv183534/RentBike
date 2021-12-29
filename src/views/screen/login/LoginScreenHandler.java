package views.screen.login;

import controllers.AdminHomeController;
import controllers.LoginController;
import controllers.RenterHomeController;
import entities.Account;
import entities.BikePark;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.admin.BikeParkManageScreenHandler;
import views.screen.renter.RenterHomeScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginScreenHandler extends BaseScreenHandler implements Initializable {
    @FXML
    private Button btnLogin;

    @FXML
    private TextField inputUserName;

    @FXML
    private TextField inputPassword;

    @FXML
    private RadioButton rbRenter;

    @FXML
    private RadioButton rbAdmin;

    @FXML
    private ToggleGroup roleLogin;


    public LoginScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    public LoginController getBController(){
        return(LoginController) super.getBController();
    }

    /**
     * @param url
     * @param resourceBundle
     * @author HieuNV
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rbRenter.setUserData(1);
        rbAdmin.setUserData(0);

        btnLogin.setOnAction(e->{
            String userName = inputUserName.getText();
            String password = inputPassword.getText();
            if(roleLogin.getSelectedToggle() == null){
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("Thông báo!");
                alert.setContentText("Bạn chưa chọn vai trò đăng nhập");
                ButtonType buttonTypeCancel = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeCancel);
                alert.show();
            }else{
                int role = (int)roleLogin.getSelectedToggle().getUserData();
                int check = this.getBController().checkAccount(new Account(userName,password,role));
                switch (check){
                    case 0:
                        try {
                            BikeParkManageScreenHandler bikeParkManageScreenHandler = new BikeParkManageScreenHandler(this.stage, Configs.ADMIN_SCREEN_PATH);
                            bikeParkManageScreenHandler.setScreenTitle("Admin home");
                            bikeParkManageScreenHandler.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            RenterHomeScreenHandler renterHomeScreen = new RenterHomeScreenHandler(this.stage, Configs.RENTER_HOME_SCREEN_PATH, userName);
                            renterHomeScreen.setScreenTitle("Renter Home");
                            renterHomeScreen.setBController(new RenterHomeController());
                            renterHomeScreen.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 2:
                        Alert alert = new Alert(Alert.AlertType.NONE);
                        alert.setTitle("Thông báo!");
                        alert.setContentText("UserName hoặc Password không hợp lệ");
                        ButtonType buttonTypeCancel = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
                        alert.getButtonTypes().setAll(buttonTypeCancel);
                        alert.show();
                        break;
                }
            }
        });

    }
}
