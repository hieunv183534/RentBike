package views.screen.login;

import controllers.LoginController;
import entities.Account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.admin.AdminHomeScreenHandler;
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
            var userName = inputUserName.getText();
            var password = inputPassword.getText();
            if(roleLogin.getSelectedToggle() == null){
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("Thông báo!");
                alert.setContentText("Bạn chưa chọn vai trò đăng nhập");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeCancel);
                alert.show();
            }else{
                int role = (int)roleLogin.getSelectedToggle().getUserData();
                int check = this.getBController().checkAccount(new Account(userName,password,role));
                switch (check){
                    case 0:
                        try {
                            AdminHomeScreenHandler adminHomeScreen = new AdminHomeScreenHandler(this.stage, Configs.ADMIN_SCREEN_PATH);
                            adminHomeScreen.setScreenTitle("Admin home");
                            adminHomeScreen.show();
                            this.hide();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            RenterHomeScreenHandler renterHomeScreen = new RenterHomeScreenHandler(this.stage, Configs.RENTER_HOME_SCREEN_PATH, userName);
                            renterHomeScreen.setScreenTitle("Renter Home");
                            renterHomeScreen.show();
                            this.hide();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case 2:
                        Alert alert = new Alert(Alert.AlertType.NONE);
                        alert.setTitle("Thông báo!");
                        alert.setContentText("UserName hoặc Password không hợp lệ");
                        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                        alert.getButtonTypes().setAll(buttonTypeCancel);
                        alert.show();
                        break;
                }
            }
        });

    }
}
