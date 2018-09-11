/**
 * Sample Skeleton for 'common_bottom.fxml' Controller Class
 */

package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class CommonBottom extends HBox implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }

    @FXML // fx:id="bottomRecordTab"
    public Button bottomRecordTab; // Value injected by FXMLLoader
    @FXML // fx:id="bottomPersonTab"
    public Button bottomPersonTab; // Value injected by FXMLLoader
    @FXML // fx:id="bottomBorrowTab"
    public Button bottomBorrowTab; // Value injected by FXMLLoader
    @FXML // fx:id="bottomRecommendTab"
    public Button bottomRecommendTab; // Value injected by FXMLLoader

    public CommonBottom() {
        System.out.println("CommonBottom Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/common_bottom.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
//            app.registerCommonBottom(this);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLayout();
        setEvents();
    }

    private void setLayout() {

    }

    private void setEvents() {
        // borrow
        bottomBorrowTab.setOnAction((ActionEvent e) -> {
            System.out.println("bottomBorrow Clicked");
            app.showBookBorrow();
        });

        // recommend
        bottomRecommendTab.setOnAction((ActionEvent e) -> {
            System.out.println("bottomRecommend Clicked");
            app.showBookRecommend();
        });

        // record
        bottomRecordTab.setOnAction((ActionEvent e) -> {
            System.out.println("bottomRecord Clicked");
            app.showBookRecordBorrow();
        });

        // person
        bottomPersonTab.setOnAction((ActionEvent e) -> {
            System.out.println("BottomPerson Clicked");
            app.showUserInfo();
        });
    }
}
