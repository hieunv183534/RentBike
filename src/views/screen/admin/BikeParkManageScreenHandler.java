package views.screen.admin;

import controllers.AdminHomeController;
import entities.BikePark;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
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

    @FXML
    private Button btnAddBikePark;

    private List<BikePark> bikeParkObjects;

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
            this.bikeParkObjects = bikeParks;
            this.bikeParks = new ArrayList();

            for (BikePark item : bikeParks) {
                BikeParkInGridHandler bikeParkInGridHandler = new BikeParkInGridHandler(Configs.BIKE_PARK_IN_GRID, item, this);
                this.bikeParks.add(bikeParkInGridHandler);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        addBikeParksToGrid(this.bikeParks);
        btnAddBikePark.setOnMouseClicked(e -> {
            try{
                BikeParkInfoScreenHandler bikeParkInfoScreenHandler = new BikeParkInfoScreenHandler(this.stage, Configs.BIKE_PARK_INFO,
                        null, bikeParkObjects, "add");
                bikeParkInfoScreenHandler.show();
            } catch (Exception exception){
                exception.printStackTrace();
            }
        });
    }
    public void addBikeParksToGrid(List list){
        this.listBikePark.getChildren().clear();
        int currentRows = 1;
        int currentCols = 0;
        for (int i = 0; i < list.size(); i++) {
            setAttrGrid(listBikePark);
            BikeParkInGridHandler tmp = (BikeParkInGridHandler) list.get(i);
            if (currentCols == this.NUMBER_COLUMN_IN_GRID_PANE) {
                currentRows++;
                currentCols = 0;
            }
            this.listBikePark.add(tmp.getContent(), currentCols++, currentRows);
            setAttrGrid(listBikePark);
            GridPane.setMargin(tmp.getContent(), new Insets(5));
        }
    }


    public Stage getStage(){
        return this.stage;
    }

    public List getBikeParkObjects() {
        return bikeParkObjects;
    }

    private void setAttrGrid(GridPane gridPane) {
        gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
        gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
        gridPane.setMaxWidth(Region.USE_PREF_SIZE);
        gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
        gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
        gridPane.setMaxHeight(Region.USE_PREF_SIZE);
    }
}
