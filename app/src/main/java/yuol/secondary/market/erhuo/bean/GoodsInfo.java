package yuol.secondary.market.erhuo.bean;

import java.io.Serializable;
import java.util.List;

public class GoodsInfo {

    /**
     * code : 200
     * message : 数据传输成功
     * data : {"goods":{"name":"java必过","content":"1111额鹅鹅鹅","pic":["erhuo/test/2018/11/24/17591.jpg","erhuo/test/2018/11/24/89510.jpg"],"honest":"100","price":"111","way":"线下","gwhere":"长江大学东校区","hide":"否","lockum":"0","hownew":"1","promise":"11","manylike":"0","wechat":null,"phone":null,"qqnum":"1316710421","oldprice":"1111","bargin":"能够议价","pubtime":"2018-11-24 11:13:38"},"user":{"nick":"听歌成瘾","touxiang":"erhuo/touxiang/123@qq.com/59103.jpg"}}
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
         * goods : {"name":"java必过","content":"1111额鹅鹅鹅","pic":["erhuo/test/2018/11/24/17591.jpg","erhuo/test/2018/11/24/89510.jpg"],"honest":"100","price":"111","way":"线下","gwhere":"长江大学东校区","hide":"否","lockum":"0","hownew":"1","promise":"11","manylike":"0","wechat":null,"phone":null,"qqnum":"1316710421","oldprice":"1111","bargin":"能够议价","pubtime":"2018-11-24 11:13:38"}
         * user : {"nick":"听歌成瘾","touxiang":"erhuo/touxiang/123@qq.com/59103.jpg"}
         */

        private GoodsBean goods;
        private UserBean user;

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class GoodsBean implements Serializable{
            /**
             * name : java必过
             * content : 1111额鹅鹅鹅
             * pic : ["erhuo/test/2018/11/24/17591.jpg","erhuo/test/2018/11/24/89510.jpg"]
             * honest : 100
             * price : 111
             * way : 线下
             * gwhere : 长江大学东校区
             * hide : 否
             * lockum : 0
             * hownew : 1
             * promise : 11
             * manylike : 0
             * wechat : null
             * phone : null
             * qqnum : 1316710421
             * oldprice : 1111
             * bargin : 能够议价
             * pubtime : 2018-11-24 11:13:38
             */

            private String name;
            private String content;
            private String honest;
            private String price;
            private String way;
            private String gwhere;
            private String hide;
            private String lockum;
            private String hownew;
            private String promise;
            private String manylike;
            private Object wechat;
            private Object phone;
            private String qqnum;
            private String oldprice;
            private String bargin;
            private String pubtime;
            private List<String> pic;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHonest() {
                return honest;
            }

            public void setHonest(String honest) {
                this.honest = honest;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getWay() {
                return way;
            }

            public void setWay(String way) {
                this.way = way;
            }

            public String getGwhere() {
                return gwhere;
            }

            public void setGwhere(String gwhere) {
                this.gwhere = gwhere;
            }

            public String getHide() {
                return hide;
            }

            public void setHide(String hide) {
                this.hide = hide;
            }

            public String getLockum() {
                return lockum;
            }

            public void setLockum(String lockum) {
                this.lockum = lockum;
            }

            public String getHownew() {
                return hownew;
            }

            public void setHownew(String hownew) {
                this.hownew = hownew;
            }

            public String getPromise() {
                return promise;
            }

            public void setPromise(String promise) {
                this.promise = promise;
            }

            public String getManylike() {
                return manylike;
            }

            public void setManylike(String manylike) {
                this.manylike = manylike;
            }

            public Object getWechat() {
                return wechat;
            }

            public void setWechat(Object wechat) {
                this.wechat = wechat;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public String getQqnum() {
                return qqnum;
            }

            public void setQqnum(String qqnum) {
                this.qqnum = qqnum;
            }

            public String getOldprice() {
                return oldprice;
            }

            public void setOldprice(String oldprice) {
                this.oldprice = oldprice;
            }

            public String getBargin() {
                return bargin;
            }

            public void setBargin(String bargin) {
                this.bargin = bargin;
            }

            public String getPubtime() {
                return pubtime;
            }

            public void setPubtime(String pubtime) {
                this.pubtime = pubtime;
            }

            public List<String> getPic() {
                return pic;
            }

            public void setPic(List<String> pic) {
                this.pic = pic;
            }
        }

        public static class UserBean implements Serializable{
            /**
             * nick : 听歌成瘾
             * touxiang : erhuo/touxiang/123@qq.com/59103.jpg
             */

            private String nick;
            private String touxiang;

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public String getTouxiang() {
                return touxiang;
            }

            public void setTouxiang(String touxiang) {
                this.touxiang = touxiang;
            }
        }
    }
}
