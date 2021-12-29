import controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.login.LoginScreenHandler;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            LoginScreenHandler loginScreen =  new LoginScreenHandler(primaryStage, Configs.LOGIN_SCREEN_PATH);
            loginScreen.setScreenTitle("Login Screen");
            loginScreen.show();
            loginScreen.setBController(new LoginController());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
