package com.elevisjang.smartstudy;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.elevisjang.smartstudy.fragment.HomeFragment;
import com.elevisjang.smartstudy.fragment.PersonFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    public HomeFragment homeFragment;
    public PersonFragment personFragment;

    private ImageButton homeImageButton;
    private ImageButton personImageButton;
    private ImageButton signImageButton;
    private ImageButton classImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_home);
        personFragment = (PersonFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_person);

        homeImageButton = (ImageButton) findViewById(R.id.ib_home);
        personImageButton = (ImageButton) findViewById(R.id.ib_person);
        signImageButton = (ImageButton) findViewById(R.id.ib_sign);
        classImageButton = (ImageButton) findViewById(R.id.ib_class);

        homeImageButton.setOnClickListener(this);
        personImageButton.setOnClickListener(this);
        signImageButton.setOnClickListener(this);
        classImageButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_home:
                imageButtonSetSelect(v);
                break;
            case R.id.ib_person:
                imageButtonSetSelect(v);
                break;
            case R.id.ib_sign:

                break;
            case R.id.ib_class:

                break;
        }

    }

    /**
     * 底部导航栏按钮选择器
     * @param view
     */
    public void imageButtonSetSelect(View view){
        //选择器，改变底部导航
        //将所有的选择器都设置成false，在判断是哪个按钮后，再进行设置
        homeImageButton.setSelected(false);
        personImageButton.setSelected(false);
        //getSupportFragmentManager()得到一个Fragment容器，
        //beginTransaction()开启一个事务
        //commit()提交事务
        getSupportFragmentManager().beginTransaction()
                .hide(homeFragment)
                .hide(personFragment).commit();
        //判断点击的是哪个按钮，然后将设置相应的选择器，和显示相应的fragment
        switch (view.getId()){
            case R.id.ib_home:
                homeImageButton.setSelected(true);
                getSupportFragmentManager().beginTransaction().show(homeFragment).commit();
                break;
            case R.id.ib_person:
                personImageButton.setSelected(true);
                getSupportFragmentManager().beginTransaction().show(personFragment).commit();
                break;
            //默认首页
            default:
                homeImageButton.setSelected(true);
                getSupportFragmentManager().beginTransaction().hide(personFragment).commit();
                getSupportFragmentManager().beginTransaction().show(homeFragment).commit();
                break;
        }
    }
}
