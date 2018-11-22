package yuol.secondary.market.erhuo.bean;

import java.io.Serializable;

public class GoodsInfo {

    /**
     * code : 200
     * message : 数据传输成功
     * data : {"goods":{"name":"白夜行","content":"我没有看过，但是听说挺好看的","pic":"erhuo/upload/2018/11/14/53368.jpg","honest":"100","price":"20","way":"线下","gwhere":"长江大学东校区","hide":"否","lockum":"0","hownew":"7","promise":"10天包退","manylike":"0","wechat":null,"phone":null,"qqnum":"1316710421","oldprice":"50","bargin":"能够议价","pubtime":"2018-11-14 15:59:13"},"user":{"nick":"发","touxiang":null}}
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
         * goods : {"name":"白夜行","content":"我没有看过，但是听说挺好看的","pic":"erhuo/upload/2018/11/14/53368.jpg","honest":"100","price":"20","way":"线下","gwhere":"长江大学东校区","hide":"否","lockum":"0","hownew":"7","promise":"10天包退","manylike":"0","wechat":null,"phone":null,"qqnum":"1316710421","oldprice":"50","bargin":"能够议价","pubtime":"2018-11-14 15:59:13"}
         * user : {"nick":"发","touxiang":null}
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
             * name : 白夜行
             * content : 我没有看过，但是听说挺好看的
             * pic : erhuo/upload/2018/11/14/53368.jpg
             * honest : 100
             * price : 20
             * way : 线下
             * gwhere : 长江大学东校区
             * hide : 否
             * lockum : 0
             * hownew : 7
             * promise : 10天包退
             * manylike : 0
             * wechat : null
             * phone : null
             * qqnum : 1316710421
             * oldprice : 50
             * bargin : 能够议价
             * pubtime : 2018-11-14 15:59:13
             */

            private String name;
            private String content;
            private String pic;
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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
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
        }

        public static class UserBean implements Serializable{
            /**
             * nick : 发
             * touxiang : null
             */

            private String nick;
            private Object touxiang;

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public Object getTouxiang() {
                return touxiang;
            }

            public void setTouxiang(Object touxiang) {
                this.touxiang = touxiang;
            }
        }
    }
}
