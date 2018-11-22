package yuol.secondary.market.erhuo.bean;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GoodsInfo_brief {


    /**
     * code : 200
     * message : 数据传输成功
     * data : {"goods":[{"name":"小王子","pic":"erhuo/upload/2018/11/02/75857.jpg","pubtime":"2018-11-02 15:12:52","price":"15","good_id":"1"},{"name":"小王子","pic":"erhuo/upload/2018/11/02/41179.jpg","pubtime":"2018-11-02 15:13:02","price":"15","good_id":"2"},{"name":"简爱","pic":"erhuo/upload/2018/11/02/57807.jpg","pubtime":"2018-11-02 15:14:49","price":"22","good_id":"3"},{"name":"烙饼锅","pic":"erhuo/upload/2018/11/02/67345.jpg","pubtime":"2018-11-02 15:17:45","price":"30","good_id":"4"},{"name":"假如给我三天光明","pic":"erhuo/upload/2018/11/02/18074.jpg","pubtime":"2018-11-02 17:05:40","price":"8","good_id":"19"},{"name":"静音巨轮","pic":"erhuo/upload/2018/11/02/90483.jpg","pubtime":"2018-11-02 17:10:12","price":"200","good_id":"20"},{"name":"vivox21","pic":"erhuo/upload/2018/11/02/42961.jpg","pubtime":"2018-11-02 17:15:57","price":"2000","good_id":"21"},{"name":"吉他","pic":"erhuo/upload/2018/11/02/74624.jpg","pubtime":"2018-11-02 17:19:32","price":"159","good_id":"22"},{"name":"羽绒服","pic":"erhuo/upload/2018/11/02/66552.jpg","pubtime":"2018-11-02 17:22:13","price":"200","good_id":"23"},{"name":"不锈钢挂钩","pic":"erhuo/upload/2018/11/02/70850.jpg","pubtime":"2018-11-02 17:24:22","price":"12","good_id":"24"},{"name":"面膜","pic":"erhuo/upload/2018/11/02/79895.jpg","pubtime":"2018-11-02 17:26:28","price":"30","good_id":"25"},{"name":"针织衫","pic":"erhuo/upload/2018/11/02/36010.jpg","pubtime":"2018-11-02 23:23:23","price":"100","good_id":"26"},{"name":"鞋柜","pic":"erhuo/upload/2018/11/02/79859.jpg","pubtime":"2018-11-02 23:24:52","price":"200","good_id":"27"},{"name":"躺椅","pic":"erhuo/upload/2018/11/02/69323.jpg","pubtime":"2018-11-02 23:38:19","price":"120","good_id":"28"},{"name":"躺椅","pic":"erhuo/upload/2018/11/02/72328.jpg","pubtime":"2018-11-02 23:41:22","price":"120","good_id":"29"},{"name":"耳机","pic":"erhuo/upload/2018/11/03/71493.jpg","pubtime":"2018-11-03 09:02:14","price":"100","good_id":"30"},{"name":"耳机","pic":"erhuo/upload/2018/11/03/46897.jpg","pubtime":"2018-11-03 09:03:12","price":"200","good_id":"31"},{"name":"头戴式耳机","pic":"erhuo/upload/2018/11/03/67571.jpg","pubtime":"2018-11-03 09:03:57","price":"200","good_id":"32"},{"name":"耳机","pic":"erhuo/upload/2018/11/03/46680.jpg","pubtime":"2018-11-03 09:04:34","price":"300","good_id":"33"},{"name":"挪威的森林","pic":"erhuo/upload/2018/11/03/79491.jpg","pubtime":"2018-11-03 14:01:53","price":"20","good_id":"34"},{"name":"7","pic":"erhuo/upload/2018/11/03/56384.jpg","pubtime":"2018-11-03 18:53:54","price":"77","good_id":"35"},{"name":"耳机","pic":"erhuo/upload/2018/11/03/52344.png","pubtime":"2018-11-03 23:29:06","price":"50","good_id":"36"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/45502.jpg","pubtime":"2018-11-03 23:31:24","price":"12","good_id":"37"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/23971.jpg","pubtime":"2018-11-03 23:31:35","price":"12","good_id":"38"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/26995.jpg","pubtime":"2018-11-03 23:31:39","price":"12","good_id":"39"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/79579.jpg","pubtime":"2018-11-03 23:31:43","price":"12","good_id":"40"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/94754.jpg","pubtime":"2018-11-03 23:31:46","price":"12","good_id":"41"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/20533.jpg","pubtime":"2018-11-03 23:31:49","price":"12","good_id":"42"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/17800.jpg","pubtime":"2018-11-03 23:31:52","price":"12","good_id":"43"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/50388.jpg","pubtime":"2018-11-03 23:31:56","price":"12","good_id":"44"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/23595.jpg","pubtime":"2018-11-03 23:31:59","price":"12","good_id":"45"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/32387.jpg","pubtime":"2018-11-03 23:32:02","price":"12","good_id":"46"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/55538.jpg","pubtime":"2018-11-03 23:32:05","price":"12","good_id":"47"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/96959.jpg","pubtime":"2018-11-03 23:32:08","price":"12","good_id":"48"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/20464.jpg","pubtime":"2018-11-03 23:32:11","price":"12","good_id":"49"},{"name":"了不起的盖茨比","pic":"erhuo/upload/2018/11/03/79222.jpg","pubtime":"2018-11-03 23:32:14","price":"12","good_id":"50"},{"name":"耳机","pic":"erhuo/upload/2018/11/03/82597.png","pubtime":"2018-11-03 23:35:30","price":"52","good_id":"51"},{"name":"1","pic":"erhuo/upload/2018/11/05/75843.jpg","pubtime":"2018-11-05 14:22:23","price":"1","good_id":"52"},{"name":"风景照","pic":"erhuo/upload/2018/11/05/16701.jpg","pubtime":"2018-11-05 14:49:40","price":"2000","good_id":"53"},{"name":"试试","pic":"erhuo/upload/2018/11/07/75332.jpg","pubtime":"2018-11-07 14:20:29","price":"34","good_id":"54"},{"name":"test1","pic":"erhuo/upload/2018/11/07/73479.jpg","pubtime":"2018-11-07 14:32:30","price":"123","good_id":"55"},{"name":"test1","pic":"erhuo/upload/2018/11/07/47276.jpg","pubtime":"2018-11-07 14:44:20","price":"233","good_id":"56"},{"name":"test2","pic":"erhuo/upload/2018/11/07/96432.jpg","pubtime":"2018-11-07 14:45:55","price":"233","good_id":"57"},{"name":"test3","pic":"erhuo/upload/2018/11/07/98335.jpg","pubtime":"2018-11-07 14:46:04","price":"233","good_id":"58"},{"name":"test4","pic":"erhuo/upload/2018/11/07/52984.jpg","pubtime":"2018-11-07 14:46:14","price":"233","good_id":"59"},{"name":"test5","pic":"erhuo/upload/2018/11/07/17171.jpg","pubtime":"2018-11-07 14:46:23","price":"233","good_id":"60"},{"name":"test6","pic":"erhuo/upload/2018/11/07/23851.jpg","pubtime":"2018-11-07 14:46:35","price":"233","good_id":"61"},{"name":"1","pic":"erhuo/upload/2018/11/07/66964.jpg","pubtime":"2018-11-07 20:00:02","price":"1","good_id":"62"},{"name":"风景","pic":"erhuo/upload/2018/11/07/82369.jpg","pubtime":"2018-11-07 20:01:12","price":"11","good_id":"63"},{"name":"我再试试","pic":"erhuo/upload/2018/11/08/28366.jpg","pubtime":"2018-11-08 20:26:44","price":"20","good_id":"64"},{"name":"34434c","pic":"erhuo/upload/2018/11/09/48089.jpg","pubtime":"2018-11-09 14:46:14","price":"22","good_id":"65"},{"name":"1","pic":"erhuo/upload/2018/11/09/47523.jpg","pubtime":"2018-11-09 17:04:58","price":"1","good_id":"66"},{"name":"1","pic":"erhuo/upload/2018/11/09/19313.jpg","pubtime":"2018-11-09 17:05:01","price":"1","good_id":"67"},{"name":"1","pic":"erhuo/upload/2018/11/09/14251.jpg","pubtime":"2018-11-09 17:05:04","price":"1","good_id":"68"},{"name":"1","pic":"erhuo/upload/2018/11/09/85467.jpg","pubtime":"2018-11-09 17:05:07","price":"1","good_id":"69"},{"name":"1","pic":"erhuo/upload/2018/11/09/61619.jpg","pubtime":"2018-11-09 17:05:10","price":"1","good_id":"70"},{"name":"1","pic":"erhuo/upload/2018/11/09/53843.jpg","pubtime":"2018-11-09 17:05:14","price":"1","good_id":"71"}],"cat":[{"cat_id":"1","catname":"手机数码","num":"20"},{"cat_id":"2","catname":"二手书籍","num":"19"},{"cat_id":"3","catname":"运动健身","num":"1"},{"cat_id":"5","catname":"特长爱好","num":"1"},{"cat_id":"15","catname":"家用电器","num":"6"},{"cat_id":"17","catname":"代步工具","num":"0"},{"cat_id":"8","catname":"鞋服配饰","num":"2"},{"cat_id":"10","catname":"其他商品","num":"12"}]}
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

    public static class DataBean {
        private ArrayList<GoodsBean> goods;
        private List<CatBean> cat;

        public ArrayList<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(ArrayList<GoodsBean> goods) {
            this.goods = goods;
        }

        public List<CatBean> getCat() {
            return cat;
        }

        public void setCat(List<CatBean> cat) {
            this.cat = cat;
        }

        public static class GoodsBean implements Serializable{
            /**
             * name : 小王子
             * pic : erhuo/upload/2018/11/02/75857.jpg
             * pubtime : 2018-11-02 15:12:52
             * price : 15
             * good_id : 1
             */

            private String name;
            private String pic;
            private String pubtime;
            private String price;
            private String good_id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPubtime() {
                return pubtime;
            }

            public void setPubtime(String pubtime) {
                this.pubtime = pubtime;
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

        public static class CatBean {
            /**
             * cat_id : 1
             * catname : 手机数码
             * num : 20
             */

            private String cat_id;
            private String catname;
            private String num;

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getCatname() {
                return catname;
            }

            public void setCatname(String catname) {
                this.catname = catname;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }
    }
}
