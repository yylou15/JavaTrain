package Utils;

public class returnObj {
    private boolean status = false;
    private String msg = "";

    public boolean getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}