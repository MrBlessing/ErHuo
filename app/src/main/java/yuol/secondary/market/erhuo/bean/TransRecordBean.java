package yuol.secondary.market.erhuo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransRecordBean {

    /**
     * code : 200
     * message : 数据传输成功
     * data : [{"name":"白夜行","price":"20","good_id":"9"},{"name":"平凡的世界","price":"50","good_id":"10"},{"name":"月亮与六便士","price":"30","good_id":"11"},{"name":"西游记","price":"20","good_id":"12"},{"name":"我也不知道是啥","price":"20","good_id":"13"},{"name":"手机","price":"3000","good_id":"14"},{"name":"创意礼物","price":"30","good_id":"15"},{"name":"哑铃","price":"20","good_id":"16"},{"name":"风景图片","price":"200","good_id":"60"}]
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
         * name : 白夜行
         * price : 20
         * good_id : 9
         */

        private String name;
        private String price;
        private String good_id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGood_id() {
            return good_id;
        }

        public void setGood_id(String good_id) {
            this.good_id = good_id;
        }
    }
}
