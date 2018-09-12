package Utils;

import global.BookStatus;
import global.GlobalConst;

public class BookStatusText {
    public static String getBookStatusTxt(BookStatus bookStatus) {
        switch (bookStatus) {
            case FREE:
                return GlobalConst.BOOKSTATUS_FREE;
            case NONE:
                return GlobalConst.BOOKSTATUS_NONE;
            case BORROWED:
                return GlobalConst.BOOKSTATUS_BORROWED;
            case REQUESTING:
                return GlobalConst.BOOKSTATUS_REQUESTING;
            case BORROWING:
                return GlobalConst.BOOKSTATUS_BORROWING;
            case DENIED:
                return GlobalConst.BOOKSTATUS_DENYED;
            case REQUESTED:
                return GlobalConst.BOOKSTATUS_REQUESTED;
            case LENDING:
                return GlobalConst.BOOKSTATUS_LENDING;
            case OVER:
                return GlobalConst.BOOKSTATUS_OVER;
            case CONFIRMING:
                return GlobalConst.BOOKSTATUS_CONFIRMING;
            default:
                return GlobalConst.BOOKSTATUS_FREE;
        }
    }
}
