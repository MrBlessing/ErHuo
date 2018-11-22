package yuol.secondary.market.erhuo.bean;

import java.io.Serializable;

public class Personal{

    /**
     * code : 200
     * message : 获取数据成功！
     * data : {"nick":"听歌成瘾","sign":"重要的东西是用眼睛看不到的，要用心才能看到","birth":"1998-07-23","sex":"女","place":"校内","intro":"你猜"}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * nick : 听歌成瘾
         * sign : 重要的东西是用眼睛看不到的，要用心才能看到
         * birth : 1998-07-23
         * sex : 女
         * place : 校内
         * intro : 你猜
         */

        private String nick;
        private String sign;
        private String birth;
        private String sex;
        private String place;
        private String intro;

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }
    }
}
