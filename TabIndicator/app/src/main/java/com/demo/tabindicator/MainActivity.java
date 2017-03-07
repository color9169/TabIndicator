package com.demo.tabindicator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.demo.tabindicator.view.TabGroupView;
import com.demo.tabindicator.view.TabText;

public class MainActivity extends AppCompatActivity {

    FrameLayout root;
    TabGroupView tabGroupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = (FrameLayout) findViewById(R.id.root);

        TabText tabText = new TabText(this);
        tabText.setText("tab选项卡1号");

        TabText tabText1 = new TabText(this);
        tabText1.setText("tab2");

        TabText tabText2 = new TabText(this);
        tabText2.setText("tab3");


        tabGroupView = new TabGroupView(this);
        tabGroupView.setTextSize(14);
        tabGroupView.setTabGravity(Gravity.CENTER);
        tabGroupView.setPadding(20);
        tabGroupView.addTab(tabText);
        tabGroupView.addTab(tabText1);
        tabGroupView.addTab(tabText2);
        tabGroupView.setOnTabChangeListener(new TabGroupView.OnTabChangeListener() {
            @Override
            public void onTabChange(int position) {
                Toast.makeText(MainActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });

        root.addView(tabGroupView);

    }
}
