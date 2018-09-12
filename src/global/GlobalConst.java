package global;

// 以出现书籍图片为主
// item_book size 130(min) x 360(pref)
public class GlobalConst {

    public final static int SIZE_SCENE_WIDTH = 380;
    public final static int SIZE_SCENE_HEIGHT = 640;

    public final static String BOOKSTATUS_FREE = "空闲";
    public final static String BOOKSTATUS_NONE = "无书";
    public final static String BOOKSTATUS_BORROWED = "在借";
    public final static String BOOKSTATUS_REQUESTING = "申请中";
    public final static String BOOKSTATUS_BORROWING = "在借";
    public final static String BOOKSTATUS_DENYED = "被拒绝";
    public final static String BOOKSTATUS_REQUESTED = "被申请";
    public final static String BOOKSTATUS_LENDING = "外借";
    public final static String BOOKSTATUS_OVER = "已归还";
    public final static String BOOKSTATUS_CONFIRMING = "归还状态待确认";

    public final static String TEST_BOOK_NAME = "长安乱";
    public final static String TEST_BOOK_AUTHOR = "韩寒 著";
    public final static String TEST_BOOK_SCORE = "7.5分";
    public final static String TEST_BOOK_PRESS = "中国青年出版社";
    public final static String TEST_BOOK_STATUS = BOOKSTATUS_FREE;
    public final static String TEST_BOOK_TIME = "2018.9.7";
    public final static String TEST_BOOK_IMG_PATH = "file:D:\\Lovecode\\Java\\JavaFX\\BookBorrow\\src\\res\\bookTest.jpg";
    public final static String TEST_USER_NAME = "白云舒";
    public final static String TEST_USER_SCORE = "100分";
    public final static String TEST_USER_UPLOAD_TIME = "2018.4.2";
    public final static String TEST_USER_LEFT = "好书好心情。";
    public final static String TEST_REQUESTER_LEFT = "已执牛耳，俯瞰却再无豪情。";
    public final static String TEST_USER_COMMENT = "虎头蛇尾";
    public final static String TEST_USER_INTRO = "在于螺旋";

    public final static String COMMON_IMG_BACKGROUD = "file:D:\\Lovecode\\Java\\JavaFX\\BookBorrowNew\\src\\res\\imgBg.png";

    public final static int SQL_RESULET_NOT_FOUND = 1;
    public final static int SQL_RESULET_QUERY_SUCCESS = 2;

    // Alert
    public final static String OPERATION_FAILED = "操作失败！请检查信息！";
    public final static String INFORMATION_NOT_FULL = "请完善信息！";

    // 书页 一会换成enum
    public final static int BOOK_BORROW = 1;
    public final static int BOOK_RECOMMEND = 2;
    public final static int BOOK_RECORD_BORROW = 3;
    public final static int BOOK_RECORD_UPLOAD = 4;
    public final static int BOOK_RECORD_REQUEST = 5;
    public final static int USER_INFO = 6;
    public final static int ACTION_CHOOSE = 7;
    public final static int BOOK_INFO = 8;
    public final static int BOOK_BORROW_LIST = 9;
    public final static int BOOK_COMMENT = 10;
    public final static int USER_COLLECTION = 11;
    public final static int BORROWER_COMMENT = 12;
    public final static int LENDER_COMMENT = 13;

}
