package Controller;

import dao.BookDao;
import global.BookStatus;
import global.PageIndex;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Book;

import java.net.URL;
import java.util.*;

//import sun.security.krb5.internal.PAEncTSEnc;

public class BookRecommend implements Initializable {

    private Main app;
    public void setApp(Main app) {
        this.app = app;
    }
    public void getHolder() {
        app.invokeTest();
        // common bottom
        app.registerCommonBottom(commonBottom);
        // tap
        for(BookItemRecommend bookItemRecommend : bookList) {
            app.registerBookItemRecommend(bookItemRecommend);
        }
        setFocus();
    }
    private void setFocus() {
        commonBottom.bottomRecommendTab.requestFocus();
    }

    @FXML
    private Label recommendTitle;
    @FXML
    private GridPane bookGrid;
    @FXML
    private CommonBottom commonBottom;

    private String bookName, bookScore, bookStatusStr;
    private BookStatus bookStatus;
    private String bookImgPath;

    private BookItemRecommend bookItem;
    private List<BookItemRecommend> bookList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("BookRecommend initialize");
        try {
            setLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setLayout() throws Exception{
        // 图片轮播

        // 书籍Grid
        bookGrid.setVgap(5);
        bookGrid.setHgap(5);
        getBookList();
        for(int i=1; i<=bookList.size(); i++) { // 根据书籍数量而添加
            if(i%2 != 0) {
                bookGrid.add(bookList.get(i-1), 0,(i-1)/2);
            }else
                bookGrid.add(bookList.get(i-1), 1, i/2-1);
        }

        // 底部导航栏
        commonBottom.setLayoutY(588);
        // 这里还没有持有app的对象，必须要在initialize之后才会有app的对象
//        app.invokeTest();
        commonBottom.setApp(app);
    }

    // 封装List 获取书籍信息
    private void getBookList() throws Exception{
        bookList = new ArrayList<>();
        List<Book> rs = getData();
//        for(int i=0; i<10; i++) {
//            bookItem = new BookItemRecommend(bookName, bookScore, bookStatus, bookImgPath, PageIndex.BOOK_RECOMMEND);
////            bookItem.setBelongId(PageIndex.BOOK_RECOMMEND);
//            bookList.add(bookItem);
//        }

        Collections.sort(rs, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if(o1.getScore() < o2.getScore())
                    return 1;
                else if(o1.getScore() == o2.getScore())
                    return 0;
                return -1;
            }
        });

        for(int i=0; i<10; i++) {
            boolean status;
            if(rs.get(i).getStatus()==1) {
                status = true;
            }else{
                status = false;
            }
            bookItem = new BookItemRecommend(rs.get(i).getBid(), rs.get(i).getName(), String.valueOf(rs.get(i).getScore()), bookStatus, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536639768170&di=75abf1f0a7671488a1936dc652b95863&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F16%2F06%2F64%2F83G58PICEbM_1024.jpg", PageIndex.BOOK_RECOMMEND);
            bookList.add(bookItem);
        }

//        for(Book book : rs){
//            boolean status;
//            if(book.getStatus()==1) {
//                status = true;
//            }else{
//                status = false;
//            }
//            bookItem = new BookItemRecommend(book.getBid(), book.getName(), String.valueOf(book.getScore()), bookStatus, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536639768170&di=75abf1f0a7671488a1936dc652b95863&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F16%2F06%2F64%2F83G58PICEbM_1024.jpg", PageIndex.BOOK_RECOMMEND);
//            bookList.add(bookItem);
//        }
    }



    private List<Book> getData() throws Exception {
//        bookName = GlobalConst.TEST_BOOK_NAME;
//        bookScore = GlobalConst.TEST_BOOK_SCORE;
        bookStatus = BookStatus.FREE;

        return BookDao.getValidBook();
    }

}
