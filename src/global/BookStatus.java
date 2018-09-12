package global;

public enum BookStatus {

    /**
     * 状态变化的控制是一个难点
     * status: 借：在借、申请中、被拒绝
     * 有无：空闲、无书
     * 上传：被申请、空闲、外借
     * FREE: 此书空闲
     * NONE：此书没有任何用户上传有书信息
     * BORROWED：此书所有上传本都处于外借状态
     * REQUESTING：借书者的借书申请等待有书人确认
     * BORROWING： 借书人正在占有此书
     * DENIED：借书人的借书申请被拒绝
     * OVER：借书人发出的书籍确认归还申请被有书人确认，整一个借书环节结束
     * REQUESTED：有书人的书被申请借阅
     * LENDING：有书人的书处于被借阅状态
     * CONFIRMING： 有书人对还书确认申请进行确认
     */
    // 单本书
    FREE, NONE, BORROWED,
    // 借者对书的状态
    REQUESTING, BORROWING, DENIED, OVER,
    // 原有书者对书的状态
    REQUESTED, LENDING, CONFIRMING
}
