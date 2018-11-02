package yuol.secondary.market.erhuo.bean;

import java.util.List;

public class Category {

    /**
     * code : 200
     * message : 栏目页数据获取成功
     * data : [{"cat_id":"29","catname":"qu去我我我我","num":"0"},{"cat_id":"28","catname":"qu去","num":"0"},{"cat_id":"27","catname":"哈哈哈","num":"0"},{"cat_id":"26","catname":"学习工具","num":"0"},{"cat_id":"24","catname":"手机电脑","num":"0"},{"cat_id":"21","catname":"动漫卡通","num":"8"},{"cat_id":"19","catname":"特长爱好","num":"0"},{"cat_id":"18","catname":"家具日用","num":"0"},{"cat_id":"17","catname":"闲置数码","num":"0"},{"cat_id":"8","catname":"其他商品","num":"0"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cat_id : 29
         * catname : qu去我我我我
         * num : 0
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
