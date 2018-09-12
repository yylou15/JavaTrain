package Controller;

import dao.BookDao;
import global.GlobalConst;
import global.PageIndex;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LenderComment implements Initializable {

    private int val;
    public static int nowRid;
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
        setEvents();
    }

    private void setLayout() {
        scoreSlider.setMin(-10);
        scoreSlider.setMax(10);
        scoreSlider.setValue(0);
        scoreSlider.setShowTickLabels(true);
        scoreSlider.setShowTickMarks(true);
        scoreSlider.setMajorTickUnit(5);
        scoreSlider.setMinorTickCount(1);
        scoreSlider.setBlockIncrement(1);

        scoreLabel.setText(String.format("%d", (int)scoreSlider.getValue()));
    }

    @FXML
    void onToBackBtnClicked() {
        System.out.println("toBackBtn Clicked");
        app.showBookRecordRequest();
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
        BookDao.confirmReturn(nowRid, val);
        return true;
    }

    private void setEvents() {
        scoreSlider.valueProperty().addListener((
                ObservableValue< ? extends Number> ov, Number oldVal, Number newVal) -> {
            this.val = (int)(double)newVal;
            scoreLabel.setText(String.format("%d", (int)(double)newVal));
        });

    }
}
