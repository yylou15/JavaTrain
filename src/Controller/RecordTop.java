package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecordTop extends HBox implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }

    @FXML // fx:id="topUploadRecord"
    public Button topUploadRecord; // Value injected by FXMLLoader

    @FXML // fx:id="topBorrowRecord"
    public Button topBorrowRecord; // Value injected by FXMLLoader

    @FXML
    public Button topRequestRecord;


    public RecordTop() {
        System.out.println("RecordTop Construct invoked");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/record_top.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        }catch (IOException e){
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
        topBorrowRecord.setOnAction((ActionEvent e) -> {
            app.showBookRecordBorrow();
        });

        topUploadRecord.setOnAction((ActionEvent e) -> {
            app.showBookRecordUpload();
        });

        topRequestRecord.setOnAction((ActionEvent e) -> {
            app.showBookRecordRequest();
        });
    }
}
