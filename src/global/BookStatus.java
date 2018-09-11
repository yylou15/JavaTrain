package global;

public enum BookStatus {

    /**
     * 状态变化的控制是一个难点
     * status: 借：在借、申请中、被拒绝
     * 有无：空闲、无书
     * 上传：被申请、空闲、外借
     */
    // 单本书
    FREE, NONE, BORROWED,
    // 借者对书的状态
    REQUESTING, BORROWING, DENIED,
    // 原有书者对书的状态
    REQUESTED, LENDING
}
