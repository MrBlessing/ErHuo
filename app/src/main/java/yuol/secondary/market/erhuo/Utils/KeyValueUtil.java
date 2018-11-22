package yuol.secondary.market.erhuo.Utils;

public class KeyValueUtil {
    //key
    public static final String TEMPLATE_TITLE = "title" ; //Template页面显示的标题l
    public static final String TEMPLATE_TAG = "tag"; //选择Template页面加载的碎片
    public static final String CAT_NAME = "cat_name";//商品类名称
    public static final String GOODS_INFO ="info";//切换页面时候传递的商品信息,由RecyclerUtils传给Good_Details
    public static final String COOKIE = "cookie";//存放登陆的时候放入的cookie
    public static final String USER_NAME = "cookie_name";//存放登陆用户的名字
    public static final String USER_INFO ="user_info";//存放用户的整体信息，节约流量


    //value
    public static final String TEMPLATE_GOODS_LIST = "goods_list";//调用Template显示GoodsList碎片时候使用的value
    public static final String TEMPLATE_PERSONAL_INFO = "personal_info";//调用Template显示PersonalInfo碎片时候使用的value
    public static final String TEMPLATE_SETTING = "setting";//调用Template显示setting碎片时候使用的value
    public static final String TEMPLATE_TRANSRECORD = "trans_record";//调用Template显示transRecord碎片时候使用的value
    public static final String TEMPLATE_MESSAGE = "message";//调用Template显示message碎片时候使用的value

    //广播
    public static final String BROADCAST_UPDATE = "yuol.secondary.market.toake.update";
}

