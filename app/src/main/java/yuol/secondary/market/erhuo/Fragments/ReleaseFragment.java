package yuol.secondary.market.erhuo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import yuol.secondary.market.erhuo.Adapter.SortRecyclerAdapter;
import yuol.secondary.market.erhuo.R;
import yuol.secondary.market.erhuo.Utils.ActivityCollector;
import yuol.secondary.market.erhuo.Utils.InputMethod;
import yuol.secondary.market.erhuo.Utils.LogUtil;
import yuol.secondary.market.erhuo.Utils.NetworkUtils;
import yuol.secondary.market.erhuo.Utils.Popup;
import yuol.secondary.market.erhuo.Utils.ReleaseInfo;

import static android.app.Activity.RESULT_OK;


public class ReleaseFragment extends Fragment {
    private View view;
    private RelativeLayout price;//价格栏
    private RelativeLayout sort;//分类栏
    private RelativeLayout number;//新旧程度栏
    private RelativeLayout contact;//联系方式栏
    private RelativeLayout position;//交易地点栏
    private EditText goodsName;
    private EditText goodsContent;
    private EditText goodsPromiss;
    private TextView showPrice;
    private TextView showSort;
    private TextView showCondition;
    private TextView showContact;
    private TextView showPosition;
    private Context context ;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView currentImage;
    private Button submit;
    private ReleaseInfo info;//储存发布的内容
    private HashMap<Integer,String> pic ;//辅助识别图片
    private static final String TAG = "ReleaseFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = ActivityCollector.currentActivity();
        info = new ReleaseInfo();
        pic = new HashMap<>();
        view = inflater.inflate(R.layout.fragment_release, container, false);
        findView();
        setEvent();
        LogUtil.d(TAG,"create");
        return view;
    }

    private void findView() {
        price = view.findViewById(R.id.fragment_release_price);
        sort = view.findViewById(R.id.fragment_release_sort);
        number = view.findViewById(R.id.fragment_release_number);
        showPrice = view.findViewById(R.id.fragment_release_price_showPrice);
        showSort = view.findViewById(R.id.fragment_release_sort_showTag);
        showCondition = view.findViewById(R.id.fragment_release_number_showNumber);
        contact = view.findViewById(R.id.fragment_release_contact);
        showContact = view.findViewById(R.id.fragment_release_price_showContact);
        position = view.findViewById(R.id.fragment_release_position);
        showPosition = view.findViewById(R.id.fragment_release_position_showPosition);
        image1 = view.findViewById(R.id.fragment_release_image1);
        image2 = view.findViewById(R.id.fragment_release_image2);
        image3 = view.findViewById(R.id.fragment_release_image3);
        image4 = view.findViewById(R.id.fragment_release_image4);
        image5 = view.findViewById(R.id.fragment_release_image5);
        goodsName = view.findViewById(R.id.fragment_release_goods_name);
        goodsContent = view.findViewById(R.id.fragment_release_goods_content);
        goodsPromiss = view.findViewById(R.id.fragment_release_goods_promiss);
        submit = view.findViewById(R.id.fragment_release_submit);
    }

    private void setEvent() {
        setClickEvent();
        setUploadImage();
        submitEvent();
    }

    private void submitEvent() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getAnotherData();
                if(!info.isNull()) {
                    //开启加载
                    Popup.processPopupWindow(view);
                    //添加内容
                    MultipartBody.Builder builder = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("name",info.getName())
                            .addFormDataPart("content",info.getContent())
                            .addFormDataPart("price",info.getPrice())
                            .addFormDataPart("oldprice",info.getOldPrice())
                            .addFormDataPart("hide",info.getHide())
                            .addFormDataPart("bargin",info.getBargin())
                            .addFormDataPart("promise",info.getPromise())
                            .addFormDataPart("payway",info.getPayway())
                            .addFormDataPart("hownew",info.getHownew())
                            .addFormDataPart("school",info.getSchool())
                            .addFormDataPart("detailwhere",info.getDetailWhere())
                            .addFormDataPart("contact",info.getContact())
                            .addFormDataPart("chatway",info.getChatway())
                            .addFormDataPart("catname",info.getCatName());
                    LogUtil.d(TAG, info.getPic().size()+"");
                    int i=1;
                    //添加图片
                    for(String fileName : info.getPic()){
                        File file = new File(fileName);
                        RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"),file);
                        builder.addFormDataPart("pic"+i,fileName,body);
                        i++;
                    }
                    NetworkUtils.requestByPostWithCookie(NetworkUtils.RELEASE, builder.build(), new Callback() {
                            @Override
                        public void onFailure(Call call, IOException e) {
                            ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Popup.easyPopup.dismiss();
                                    Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            LogUtil.d(TAG,response.body().string());
                            PictureFileUtils.deleteCacheDirFile(context);//删除文件缓存，在上传成功后使用
                            ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Popup.easyPopup.dismiss();
                                    Popup.hintPopupWindow(getView().getRootView(),"发布成功",3000);
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_page_container,new ReleaseFragment()).commit();
                                }
                            });

                        }
                    });
                }else {
                    Popup.hintPopupWindow(view,"请输入完整内容",Popup.HINT_TIME);
                }
            }
        });
    }

    private void setUploadImage() {
        imageClickEvent(image1);
        imageClickEvent(image2);
        imageClickEvent(image3);
        imageClickEvent(image4);
        imageClickEvent(image5);
    }

    private void imageLongClickEvent(final ImageView imageView) {
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                imageView.setImageResource(R.drawable.add);
                info.removePic(pic.get(imageView.getId()));
                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                //将删除事件置为空
                imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        return true;
                    }
                });
                return true;
            }
        });
    }

    private void imageClickEvent(final ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPictureSelector();
                currentImage = imageView;
            }
        });
    }

    private void setClickEvent() {
        setPrice();
        setSort();
        setCondition();
        setContact();
        setPosition();
    }

    private void setPosition() {
        //加载弹窗布局
        final View view_position = LayoutInflater.from(context).inflate(R.layout.popup_release_position,null);
        position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.bigPopupWindow(view,view_position,Gravity.BOTTOM);
            }
        });

        //设置弹窗内部视图的点击事件
        final Button submit = view_position.findViewById(R.id.popup_release_position_submit);
        final EditText input = view_position.findViewById(R.id.popup_release__position_inputPosition);
        final RadioGroup group = view_position.findViewById(R.id.popup_release_position_about);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(input.getText())){
                    String text = "";
                    switch (group.getCheckedRadioButtonId()){
                        case R.id.popup_release_position_about_east :
                            text = "东校区/";
                            info.setSchool("长江大学东校区");
                            break;
                        case R.id.popup_release_position_about_west :
                            text = "西校区/";
                            info.setSchool("长江大学西校区");
                            break;
                        case R.id.popup_release_position_about_wuhan :
                            text = "武汉校区/";
                            info.setSchool("武汉校区");
                            break;
                    }
                    info.setDetailWhere(input.getText().toString());
                    String show = text +input.getText();
                    showPosition.setText(show);
                    //关闭弹窗
                    Popup.easyPopup.dismiss();
                    //隐藏输入法
                    InputMethod.hideInputMethod(input,context);
                }else {
                    Popup.hintPopupWindow(ReleaseFragment.this.view,  "请输入完整内容",Popup.HINT_TIME);
                }
            }
        });
    }

    private void setContact() {

        //加载弹窗布局
        final View view_contact = LayoutInflater.from(context).inflate(R.layout.popup_release_contact,null);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.bigPopupWindow(view,view_contact,Gravity.BOTTOM);
            }
        });

        //设置弹窗内部视图的点击事件
        Button submit = view_contact.findViewById(R.id.popup_release_contact_submit);
        final EditText input = view_contact.findViewById(R.id.popup_release_contact_inputContact);
        final RadioGroup group = view_contact.findViewById(R.id.popup_release_contact_way);
        switch (group.getCheckedRadioButtonId()){
            case R.id.popup_release_contact_way_QQ :
                info.setContact("qq");
                break;
            case R.id.popup_release_contact_way_phone:
                info.setContact("phone");
                break;
            case R.id.popup_release_contact_way_wechat:
                info.setContact("wechat");
                break;
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(input.getText())){
                    showContact.setText(input.getText());
                    info.setChatway(input.getText().toString());
                    Popup.easyPopup.dismiss();
                }else {
                    Popup.hintPopupWindow(ReleaseFragment.this.view,  "请输入完整内容",Popup.HINT_TIME);
                }
            }
        });
    }

    private void setCondition() {
        //加载弹窗布局
        final View view_number = LayoutInflater.from(context).inflate(R.layout.popup_release_condition,null);

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.bigPopupWindow(view,view_number,Gravity.BOTTOM);
            }
        });

        //设置按钮的点击事件
        Button submit = view_number.findViewById(R.id.popup_release_condition_submit);
        final EditText inputNumber = view_number.findViewById(R.id.popup_release_condition_inputNumber);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(inputNumber.getText())&&Integer.parseInt(inputNumber.getText().toString())<=10&&Integer.parseInt(inputNumber.getText().toString())>0){
                    Popup.easyPopup.dismiss();
                    showCondition.setText(inputNumber.getText()+"成新");
                    info.setHownew(inputNumber.getText().toString());
                }else {
                    Popup.hintPopupWindow(ReleaseFragment.this.view,  "请输入正确内容",Popup.HINT_TIME);
                }

            }
        });
    }

    private void setSort() {
        //加载分类列表
        final View view_sort = LayoutInflater.from(context).inflate(R.layout.popup_release_sort,null);
        RecyclerView recyclerView = view_sort.findViewById(R.id.popup_release_sort_recycler);

        //准备数据
        List<String> data = new ArrayList<>();
        data.add("代步工具");data.add("家用电器");data.add("考研考证");data.add("学生笔记");data.add("鞋服配饰");data.add("特长爱好");data.add("运动健身");data.add("二手书籍");data.add("手机数码");data.add("其他商品");
        //设置RecyclerView
        LinearLayoutManager manager1 = new LinearLayoutManager(ActivityCollector.currentActivity());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager1);
        SortRecyclerAdapter adapter = new SortRecyclerAdapter(data);

        //实现适配器的回调接口
        adapter.setOnItemClickListener(new SortRecyclerAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String data) {
                showSort.setText(data);
                info.setCatName(data);
                ActivityCollector.currentActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Popup.easyPopup.dismiss();
                    }
                });
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(context, "纠结什么呢.",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.hintPopupWindow(ReleaseFragment.this.view,view_sort);
            }
        });
    }

    private void setPrice() {
        //加载价格输入布局
        final View view_price = LayoutInflater.from(context).inflate(R.layout.popup_release_price,null);
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.bigPopupWindow(view,view_price,Gravity.BOTTOM);
            }
        });

        //设置view_price内部控件事件

        //议价选择
        final CheckBox discuss = view_price.findViewById(R.id.popup_release_price_discuss);
        Button submit = view_price.findViewById(R.id.popup_release_price_submit);
        final EditText inputPrice = view_price.findViewById(R.id.popup_release_price_inputPrice);
        final EditText inputFirstPrice = view_price.findViewById(R.id.popup_release_price_inputFirstPrice);
        inputPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);//只能输入数字和小数点
        inputFirstPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断复选框状态
                if(discuss.isChecked())
                    info.setBargin("0");
                else
                    info.setBargin("1");
                //将输入的信息输入到主页面
                if(!TextUtils.isEmpty(inputPrice.getText()) && !TextUtils.isEmpty(inputFirstPrice.getText())){
                    showPrice.setText("￥"+inputPrice.getText()+" / ￥"+inputFirstPrice.getText());
                    info.setPrice(inputPrice.getText().toString());
                    info.setOldPrice(inputFirstPrice.getText().toString());
                    Popup.easyPopup.dismiss();
                }else {
                    //加载提示弹窗
                    Popup.hintPopupWindow(ReleaseFragment.this.view,"请输入完整内容",Popup.HINT_TIME);
                }
            }
        });
    }

    private void setPictureSelector(){
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(5)// 最大图片选择数量 int
                .minSelectNum(0)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .previewVideo(false)// 是否可预览视频 true or false
                .enablePreviewAudio(false) // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .compress(true)// 是否压缩 true or false
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
               .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    LocalMedia media =selectList.get(0);
                    if(media.isCompressed()){
                        LogUtil.d(TAG,media.getCompressPath());
                        //为了辨识是哪个图片做的一些处理
                        pic.put(currentImage.getId(),media.getCompressPath());
                        //储存图片
                        info.addPic(media.getCompressPath());
                        Glide.with(context).load(media.getCompressPath()).into(currentImage);
                    }
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    Toast.makeText(context, "长按照片删除", Toast.LENGTH_SHORT).show();
                    //设置删除点击事件
                    imageLongClickEvent(currentImage);
                    break;
            }
        }
    }

    public void getAnotherData() {
        //获取主页面三个大框的内容
        info.setName(goodsName.getText().toString());
        info.setContent(goodsContent.getText().toString());
        info.setPromise(goodsPromiss.getText().toString());
        //获取是否匿名
        RadioGroup hide = view.findViewById(R.id.fragment_release_cryptonym);
        if(hide.getCheckedRadioButtonId() == R.id.fragment_release_cryptonym_true)
            info.setHide("1");
        else
            info.setHide("0");
    }
}
