package com.sinieco.materialdesigndemo;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar tool;
    private DrawerLayout drawer;
    private NavigationView navi;
    private AppBarLayout appbar;

    // 控制ToolBar的变量
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;

    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;
    private TextView mTvToolbarTitle;
    private LinearLayout mLlTitleContainer;
    private FrameLayout mFlTitleContainer;
    private ImageView mIvPlaceholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navi = (NavigationView) findViewById(R.id.navi);
//        setSupportActionBar(tool);
        initToolBar();
        initAppBar();
        initDrawer();
        initListener();
        initParallaxValues();
    }

    private void initAppBar() {
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        mTvToolbarTitle = (TextView)findViewById(R.id.main_tv_title);
        mLlTitleContainer = (LinearLayout) findViewById(R.id.main_ll_title_container);
        mFlTitleContainer = (FrameLayout)findViewById(R.id.main_fl_title);
        mIvPlaceholder = (ImageView)findViewById(R.id.main_iv_placeholder);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int maxScroll = appBarLayout.getTotalScrollRange();
                float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;
                handleAlphaOnTitle(percentage);
                handleToolbarTitleVisibility(percentage);
            }
        });
    }

    // 处理ToolBar的显示
    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(mTvToolbarTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTvToolbarTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    // 控制Title的显示
    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(mLlTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mLlTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    private void initListener() {
        navi.setCheckedItem(R.id.news);
        navi.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.news :
                        Toast.makeText(MainActivity.this,"点击新闻按钮",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.photo :
                        Toast.makeText(MainActivity.this,"点击美图按钮",Toast.LENGTH_SHORT).show();
                        break ;
                    case R.id.video :
                        Toast.makeText(MainActivity.this,"点击视频按钮",Toast.LENGTH_SHORT).show();
                        break ;
                    case R.id.game :
                        Toast.makeText(MainActivity.this,"点击游戏按钮",Toast.LENGTH_SHORT).show();
                        break ;
                    case R.id.share :
                        Toast.makeText(MainActivity.this,"点击分享按钮",Toast.LENGTH_SHORT).show();
                        break ;
                    case R.id.setting :
                        Toast.makeText(MainActivity.this,"点击设置按钮",Toast.LENGTH_SHORT).show();
                        break ;
                }
                return true;
            }
        });
        View view = navi.getHeaderView(0);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"点击头部",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,0,0);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

    private void initToolBar() {
        tool = (Toolbar) findViewById(R.id.toolbar);
        tool.setTitle("");
        tool.setSubtitleTextColor(getResources().getColor(R.color.colorAccent));
        tool.collapseActionView();
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrCloseDrawer();
            }
        });
    }

    private void openOrCloseDrawer() {
        if(navi != null){
            if(drawer.isDrawerOpen(navi)){
                drawer.closeDrawer(navi);
            }else{
                drawer.openDrawer(navi);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add :
                Toast.makeText(this,"点击添加按钮",Toast.LENGTH_SHORT).show();
                break ;
            case R.id.daytime :
                Toast.makeText(this,"点击日间模式按钮",Toast.LENGTH_SHORT).show();
                break ;
            case R.id.nighttime :
                Toast.makeText(this,"点击夜间模式按钮",Toast.LENGTH_SHORT).show();
                break ;
        }
        return super.onOptionsItemSelected(item);
    }

    // 设置渐变的动画
    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    private void initParallaxValues() {
        CollapsingToolbarLayout.LayoutParams petDetailsLp =
                (CollapsingToolbarLayout.LayoutParams) mIvPlaceholder.getLayoutParams();

        CollapsingToolbarLayout.LayoutParams petBackgroundLp =
                (CollapsingToolbarLayout.LayoutParams) mFlTitleContainer.getLayoutParams();

        petDetailsLp.setParallaxMultiplier(0.9f);
        petBackgroundLp.setParallaxMultiplier(0.3f);

        mIvPlaceholder.setLayoutParams(petDetailsLp);
        mFlTitleContainer.setLayoutParams(petBackgroundLp);
    }
}
