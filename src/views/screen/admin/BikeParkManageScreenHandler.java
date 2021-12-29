package views.screen.admin;

import controllers.AdminHomeController;
import entities.BikePark;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BikeParkManageScreenHandler extends BaseScreenHandler implements Initializable{

    @FXML
    private Text labelUserName;

    @FXML
    private GridPane listBikePark;

    private List bikeParks;

    private final static int NUMBER_COLUMN_IN_GRID_PANE = 2;

    public AdminHomeController getBController() {
        return (AdminHomeController) super.getBController();
    }

    public BikeParkManageScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        labelUserName.setText("ADMIN");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setBController(new AdminHomeController());
            List<BikePark> bikeParks = getBController().getAllBikeParks();
            this.bikeParks = new ArrayList();

            for (BikePark item : bikeParks) {
                BikePark bikePark = item;
                BikeParkInGridHandler bikeParkInGridHandler = new BikeParkInGridHandler(Configs.BIKE_PARK_IN_GRID, item, this);
                this.bikeParks.add(bikeParkInGridHandler);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        addBikeParksToGrid(this.bikeParks);
    }
    public void addBikeParksToGrid(List list){
        this.listBikePark.getChildren().clear();
        int currentRows = 0;
        int currentCols = 0;
        for (int i = 0; i < list.size(); i++) {
            BikeParkInGridHandler tmp = (BikeParkInGridHandler) list.get(i);
            this.listBikePark.add(tmp.getContent(), currentCols, currentRows);
            if (currentCols == this.NUMBER_COLUMN_IN_GRID_PANE) {
                currentRows++;
                currentCols = 0;
            } else {
                currentCols++;
            }
        }
    }
    public Stage getStage(){
        return this.stage;
    }
}
