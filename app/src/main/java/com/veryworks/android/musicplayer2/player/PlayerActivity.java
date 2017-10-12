package com.veryworks.android.musicplayer2.player;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.veryworks.android.musicplayer2.BaseActivity;
import com.veryworks.android.musicplayer2.Const;
import com.veryworks.android.musicplayer2.PlayerPagerAdapter;
import com.veryworks.android.musicplayer2.PlayerService;
import com.veryworks.android.musicplayer2.R;
import com.veryworks.android.musicplayer2.domain.Music;

public class PlayerActivity extends BaseActivity implements View.OnClickListener{
    Music music;
    int current = -1;
    private android.support.v4.view.ViewPager viewPager;
    private android.widget.RelativeLayout controller;
    private android.widget.SeekBar seekBar;
    private android.widget.TextView textCurrentTime;
    private android.widget.TextView textDuration;
    private android.widget.ImageButton btnPlay;
    private android.widget.ImageButton btnFf;
    private android.widget.ImageButton btnRew;
    private android.widget.ImageButton btnNext;
    private android.widget.ImageButton btnPrev;

    Intent serviceIntent;

    @Override
    public void init() {
        setContentView(R.layout.activity_player);
        Intent intent = getIntent();
        if(intent != null)
            current = intent.getIntExtra(Const.KEY_POSITION, 0);
        serviceIntent = new Intent(this, PlayerService.class);

        load();
        initView();
        initViewPager();
    }
    void load(){
        music = Music.getInstance();
        music.load(this);
        playerSet();
    }
    void initViewPager(){
        PlayerPagerAdapter adapter = new PlayerPagerAdapter(this, music.data);
        viewPager.setAdapter(adapter);
        // 뷰페이저에 리스너를 달기전에 페이지를 변경해서 onPageSelected가 호출되지 않는다
        if(current > 0)
            viewPager.setCurrentItem(current);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                current = position;
                playerSet();
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        controller = (RelativeLayout) findViewById(R.id.controller);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textCurrentTime = (TextView) findViewById(R.id.textCurrentTime);
        textDuration = (TextView) findViewById(R.id.textDuration);

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnFf = (ImageButton) findViewById(R.id.btnFf);
        btnRew = (ImageButton) findViewById(R.id.btnRew);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);

        btnPlay.setOnClickListener(this);
        btnFf.setOnClickListener(this);
        btnRew.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPlay:
                start();
                break;
            case R.id.btnFf:
                break;
            case R.id.btnRew:
                break;
            case R.id.btnNext:
                break;
            case R.id.btnPrev:
                break;
        }
    }

    void playerSet(){
        serviceIntent.setAction(Const.ACTION_SET);
        serviceIntent.putExtra(Const.KEY_POSITION, current);
        startService(serviceIntent);
    }
    void start(){
        serviceIntent.setAction(Const.ACTION_START);
        startService(serviceIntent);
    }
    void pause(){
        serviceIntent.setAction(Const.ACTION_PAUSE);
        startService(serviceIntent);
    }
    void stop(){
        startService(serviceIntent);
    }
}
