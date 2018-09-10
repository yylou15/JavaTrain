package Controller;

import global.GlobalConst;
import global.PageIndex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class BorrowerComment implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    private PageIndex fromId;
    public void setFromId(PageIndex fromId) {
        this.fromId = fromId;
    }

    @FXML
    private Label scoreLabel;
    @FXML
    private TextArea leftTxtArea;
    @FXML
    private Button confirmBtn, toBackBtn;
    @FXML
    private Slider scoreSlider;

    private String userGivenScore, userLeft;

    private final String COMMON_TITLE = "归还确认";
    private final String INFORMATION_SUCCESS = "评价与确认归还成功";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLayout();
    }

    private void setLayout() {

    }

    @FXML
    void onToBackBtnClicked() {
        System.out.println("toBackBtn Clicked");
        app.showBookRecordBorrow();
    }

    @FXML
    void onConfirmBtnClicked(ActionEvent event) {
        System.out.println("confirmBtn Clicked");
        // 确认评分
        if(checkValid()) { // 上传
            if(confirmReturn()) {
                Alert information = new Alert(Alert.AlertType.INFORMATION, INFORMATION_SUCCESS);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
                onToBackBtnClicked();
            } else {
                Alert information = new Alert(Alert.AlertType.INFORMATION, GlobalConst.OPERATION_FAILED);
                information.setTitle(COMMON_TITLE);
                information.showAndWait();
            }
        }

        else {
            Alert information = new Alert(Alert.AlertType.INFORMATION, GlobalConst.INFORMATION_NOT_FULL);
            information.setTitle(COMMON_TITLE);
            information.showAndWait();
        }
    }

    private boolean checkValid() {
        return true;
    }

    private boolean confirmReturn() {
        System.out.println("confirm return...");
        return true;
    }
}
