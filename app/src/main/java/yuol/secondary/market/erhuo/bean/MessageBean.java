package yuol.secondary.market.erhuo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageBean {

    /**
     * code : 200
     * message : 数据传输成功！
     * data : [{"id":"1","title":"双十一","content":"降降降啦降降降啦降降降啦降降降啦降降降啦降降","pubtime":"2018-11-15 14:01:03"},{"id":"2","title":"元旦快乐","content":"祝大家元旦快乐","pubtime":"2018-11-15 14:02:00"}]
     */

    private int code;
    private String message;
    private ArrayList<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * title : 双十一
         * content : 降降降啦降降降啦降降降啦降降降啦降降降啦降降
         * pubtime : 2018-11-15 14:01:03
         */

        private String id;
        private String title;
        private String content;
        private String pubtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPubtime() {
            return pubtime;
        }

        public void setPubtime(String pubtime) {
            this.pubtime = pubtime;
        }
    }
}
