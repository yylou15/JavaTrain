package Controller;

import global.GlobalConst;
import global.PageIndex;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

// main 被用来作为Stage的交互
// 难道说Show 或者 Register 只能由其它方来调用吗...
// 让所有对象都持有Main的引用这种做法真的没问题吗
// 好吧这种机制确实是有问题的
// 我只有在Initialize之后才能拿到它的app引用...啊...所以可能的蠢办法是设计一个自动的帮助获取app引用的方法
// 用户Id通过一个静态全局变量来控制？
// BookItem应该写成继承的，这样子也不用写那么多register

// 待完成
// 1. button的选中与禁用状态 还是要从Main去控制，还是initialize之后去执行...这套代码真的问题不小啊 ok
// 2. 收藏的撰写 别忘了取消收藏...那又要多写一个Item了 遇到了一个很奇怪的bug ok
// 3. 页面跳转，先正确返回 ok
// 3.5. BookItem的共用问题 ok
// 4. 撰写书籍评价 ok
// 5. 书籍归还确认 ok
// 6. 相互评价 ok
// 6.5 替换枚举类型
// 7. 传递数据
// 8. 处理图片
// 9. 拉取数据源
// 10. 美化图片

public class Main extends Application {

    private Stage stage;

    public void invokeTest() {
        System.out.println("You have the holder and object");
    }

    // login.fxml
    public void showLogin(){
        try {
            Login login = (Login) replaceSceneContent("/View/login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // register.fxml
    public void showRegister(){
        try {
            Register register = (Register) replaceSceneContent("/View/register.fxml");
            register.setApp(this);
            register.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // book_borrow.fxml
    public void showBookBorrow() {
        try {
            BookBorrow bookBorrow = (BookBorrow) replaceSceneContent("/View/book_borrow.fxml");
            bookBorrow.setApp(this);
            bookBorrow.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // book_recommend.fxml
    public void showBookRecommend() {
        try {
            BookRecommend bookRecommend = (BookRecommend) replaceSceneContent("/View/book_recommend.fxml");
            bookRecommend.setApp(this);
            bookRecommend.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // book_record_borrow.fxml
    public void showBookRecordBorrow() {
        try {
            BookRecordBorrow bookRecordBorrow = (BookRecordBorrow) replaceSceneContent("/View/book_record_borrow.fxml");
            bookRecordBorrow.setApp(this);
            bookRecordBorrow.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // book_record_upload.fxml
    public void showBookRecordUpload() {
        try {
            BookRecordUpload bookRecordUpload = (BookRecordUpload) replaceSceneContent("/View/book_record_upload.fxml");
            bookRecordUpload.setApp(this);
            bookRecordUpload.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // book_record_request.fxml
    public void showBookRecordRequest() {
        try {
            BookRecordRequest bookRecordRequest = (BookRecordRequest) replaceSceneContent("/View/book_record_request.fxml");
            bookRecordRequest.setApp(this);
            bookRecordRequest.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // user_info_fxml
    public void showUserInfo() {
        try {
            UserInfo userInfo = (UserInfo) replaceSceneContent("/View/user_info.fxml");
            userInfo.setApp(this);
            userInfo.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // action_choose.fxml
    public void showActionChoose() {
        try {
            ActionChoose actionChoose = (ActionChoose) replaceSceneContent("/View/action_choose.fxml");
            actionChoose.setApp(this);
            actionChoose.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // user_info_modify.fxml
    public void showUserInfoModify() {
        try {
            UserInfoModify userInfoModify = (UserInfoModify) replaceSceneContent("/View/user_info_modify.fxml");
            userInfoModify.setApp(this);
            userInfoModify.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // user_pwd_modify.fxml
    public void showUserPwdModify() {
        try {
            UserPasswordModify userPasswordModify = (UserPasswordModify) replaceSceneContent("/View/user_pwd_modify.fxml");
            userPasswordModify.setApp(this);
            userPasswordModify.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // user_collection.fxml
    public void showUserCollection() {
        try {
            UserCollection userCollection = (UserCollection) replaceSceneContent("/View/user_collection.fxml");
            userCollection.setApp(this);
            userCollection.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // book_upload
    public void showBookUpload(PageIndex fromId) {
        try {
            BookUpload bookUpload = (BookUpload) replaceSceneContent("/View/book_upload.fxml");
            bookUpload.setApp(this);
            bookUpload.setFromId(fromId);
            bookUpload.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // book_info.fxml
    public void showBookInfo(PageIndex fromId) {
        try {
            BookInfo bookInfo = (BookInfo) replaceSceneContent("/View/book_info.fxml");
            bookInfo.setApp(this);
            bookInfo.setFromId(fromId);
            bookInfo.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // book_comment.fxml
    public void showBookComment(PageIndex fromId) {
        try {
            BookComment bookComment = (BookComment) replaceSceneContent("/View/book_comment.fxml");
            bookComment.setApp(this);
            bookComment.setFromId(fromId);
            bookComment.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // book_borrow_list.fxml
    public void showBookBorrowList(PageIndex fromId) {
        try {
            BookBorrowList bookBorrowList = (BookBorrowList) replaceSceneContent("/View/book_borrow_list.fxml");
            bookBorrowList.setApp(this);
            bookBorrowList.setFromId(fromId);
            bookBorrowList.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // book_borrow_request.fxml
    public void showBookBorrowRequest(PageIndex rootId, PageIndex fromId) {
        try {
            BookBorrowRequest bookBorrowRequest = (BookBorrowRequest) replaceSceneContent("/View/book_borrow_request.fxml");
            bookBorrowRequest.setApp(this);
            bookBorrowRequest.setFromId(fromId);
            bookBorrowRequest.setRootId(rootId);
            bookBorrowRequest.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // book_borrow_confirm.fxml
    public void showBookBorrowConfirm() {
        try {
            BookBorrowConfirm bookBorrowConfirm = (BookBorrowConfirm) replaceSceneContent("/View/book_borrow_confirm.fxml");
            bookBorrowConfirm.setApp(this);
            bookBorrowConfirm.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // add_book_comment.fxml
    public void showAddBookComment(PageIndex fromId) {
        try {
            AddBookComment addBookComment = (AddBookComment) replaceSceneContent("/View/add_book_comment.fxml");
            addBookComment.setApp(this);
            addBookComment.setFromId(fromId);
            addBookComment.getHolder();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // user_comment.fxml
    public void showBorrowerComment(PageIndex fromId) {
        try {
            BorrowerComment borrowerComment = (BorrowerComment) replaceSceneContent("/View/borrower_comment.fxml");
            borrowerComment.setApp(this);
            borrowerComment.setFromId(fromId);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void showLenderComment(PageIndex fromId) {
        try {
            LenderComment lenderComment = (LenderComment) replaceSceneContent("/View/lender_comment.fxml");
            lenderComment.setApp(this);
            lenderComment.setFromId(fromId);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        Parent root;
        try {
            root = loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(root, GlobalConst.SIZE_SCENE_WIDTH, GlobalConst.SIZE_SCENE_HEIGHT);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    // CommonBottom
    public void registerCommonBottom(CommonBottom root) {
        root.setApp(this);
    }

    // RecordTop
    public void registerRecordTop(RecordTop root) {
        root.setApp(this);
    }

    // BookItem
    public void registerBookItem(BookItem root) {
        root.setApp(this);
    }

    // BookItemRecommend
    public void registerBookItemRecommend(BookItemRecommend root) {
        root.setApp(this);
    }

    // BookItemComment
    public void registerBookItemComment(BookItemComment root) {
        root.setApp(this);
    }

    // BookItemBorrow
    public void registerBookItemBorrow(BookItemBorrow root) {
        root.setApp(this);
    }

    // BookItemRequest
    public void registerBookItemRequest(BookItemRequest root) {
        root.setApp(this);
    }

    // BookItemCollection
    public void registerBookItemCollection(BookItemCollection root) {
        root.setApp(this);
    }

    public void registerBookItemConfirm(BookItemConfirm root) {
        root.setApp(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        stage.setTitle("Book Borrow");
//        Parent root = FXMLLoader.load(getClass().getResource("/View/welcome.fxml"));
//        scene = new Scene(root, GlobalConst.SIZE_SCENE_WIDTH, GlobalConst.SIZE_SCENE_HEIGHT);
//        stage.setScene(scene);
        showLogin();
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
