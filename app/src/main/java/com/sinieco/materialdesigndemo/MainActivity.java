package com.sinieco.materialdesigndemo;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar tool;
    private DrawerLayout drawer;
    private NavigationView navi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navi = (NavigationView) findViewById(R.id.navi);
//        setSupportActionBar(tool);
        initToolBar();
        initDrawer();
        initListener();
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
        tool.setTitle("新闻");
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
}
