package yuol.secondary.market.erhuo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.erhuo.Fragments.HomeFragment;
import yuol.secondary.market.erhuo.Fragments.PersonalCenterFragment;
import yuol.secondary.market.erhuo.Fragments.ReleaseFragment;
import yuol.secondary.market.erhuo.Utils.BasedActivity;
import yuol.secondary.market.erhuo.autoUpdate.AutoUpdate;


public class HomePage extends BasedActivity {
    public boolean updateState = true;//检测更新状态，改值为真则检测更新，保证了该应用每打开一次只会检测一次更新
    private long first=0;//给按键计时
    private HomeFragment homeFragment;
    private ReleaseFragment releaseFragment;
    private PersonalCenterFragment personalCenterFragment;
    private Fragment currentFragment ;
    private BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        findView();
        setEvent();
    }

    private void setEvent() {
        initFragments();
        setBottomNavigation();
    }

    private void initFragments() {
        homeFragment = new HomeFragment();
        currentFragment = homeFragment;
        //每次都是replease就不用担心重复添加了，真是个天才
        //每次都是replease会让系统反复加载一个页面，造成系统卡顿，无法保存页面中的数据
        //所以还是用show和hide方法
        if(!homeFragment.isAdded()){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.home_page_container,currentFragment,"home")
                    .commit();
        }
    }

    private void setBottomNavigation() {

        //基础设置
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_DEFAULT)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .setBarBackgroundColor("#ffffff")
                .setActiveColor("#d65d5d")//选中颜色
                //设置点击事件
                .setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        switch (position){
                            case 0:
                                //防止反复构建对象
                                if(homeFragment == null){
                                    homeFragment = new HomeFragment();
                                }
                                //已经添加的就不必继续添加了
                                if(!homeFragment.isAdded()){
                                    transaction.add(R.id.home_page_container,homeFragment,"home");
                                }
                                //隐藏当前页面，显示第一个碎片
                                transaction
                                        .hide(currentFragment)
                                        .show(homeFragment);
                                currentFragment = homeFragment;
                                break;
                            case 1:
                                if(releaseFragment == null){
                                    releaseFragment = new ReleaseFragment();
                                }
                                if(!releaseFragment.isAdded()){
                                    transaction
                                            .add(R.id.home_page_container,releaseFragment,"release");
                                }
                                transaction
                                        .hide(currentFragment)
                                        .show(releaseFragment);
                                currentFragment = releaseFragment;
                                break;
                            case 2:
                                if(personalCenterFragment == null){
                                    personalCenterFragment = new PersonalCenterFragment();
                                }
                                if(!personalCenterFragment.isAdded()){
                                    transaction
                                            .add(R.id.home_page_container,personalCenterFragment,"personalCenter");
                                }
                                transaction
                                        .hide(currentFragment)
                                        .show(personalCenterFragment);
                                currentFragment = personalCenterFragment;
                                break;
                        }
                        transaction.commit();//事务提交
                    }

                    @Override
                    public void onTabUnselected(int position) {

                    }

                    @Override
                    public void onTabReselected(int position) {

                    }
                });
         //按钮设置
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home,"首页"))
                .addItem(new BottomNavigationItem(R.drawable.release,"发布"))
                .addItem(new BottomNavigationItem(R.drawable.personal_center,"个人"))
                .initialise();

    }

    private void findView() {
        bottomNavigationBar = findViewById(R.id.home_page_bottomNavigation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //只进行一次更新
        if(updateState){
            updateState = false;
            AutoUpdate.Update(this,manager);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if((System.currentTimeMillis()-first)<2000){
                       finish();
                    }else {
                        first = System.currentTimeMillis();
                        Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }break;
        }
        return super.onKeyDown(keyCode, event);
    }

}
