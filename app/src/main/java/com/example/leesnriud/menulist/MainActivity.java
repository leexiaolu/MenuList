package com.example.leesnriud.menulist;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //定义不同颜色的菜单项的标识:
    final private int RED = 110;
    final private int GREEN = 111;
    final private int BLUE = 112;
    final private int YELLOW = 113;
    final private int GRAY= 114;
    final private int CYAN= 115;
    final private int BLACK= 116;

    private  MenuInflater inflater = null;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bt_option)
    Button btOption;
    @BindView(R.id.bt_context)
    Button btContext;
    @BindView(R.id.bt_sub)
    Button btSub;
    @BindView(R.id.bt_popup)
    Button btPopup;
    @BindView(R.id.rg_radiogroup)
    RadioGroup rgRadioGroup;
    @BindView(R.id.rb_contextmenu)
    RadioButton rbContextmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        //默认注册上下文menu
        registerForContextMenu(btContext);

        rgRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                String status = (String) radbtn.getText();
                //判断用户选择的menu类型，通过改变注册状态使其中一个失去长按效果
                if(status.equals("contextmenu")){
                    registerForContextMenu(btContext);
                    unregisterForContextMenu(btSub);
                }else{
                    registerForContextMenu(btSub);
                    unregisterForContextMenu(btContext);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(1,RED,4,"红色");
        menu.add(1,GREEN,2,"绿色");
        menu.add(1,BLUE,3,"蓝色");
        menu.add(1,YELLOW,1,"黄色");
        menu.add(1,GRAY,5,"灰色");
        menu.add(1,CYAN,6,"蓝绿色");
        menu.add(1,BLACK,7,"黑色");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case RED:
                btOption.setTextColor(Color.RED);
                break;
            case GREEN:
                btOption.setTextColor(Color.GREEN);
                break;
            case BLUE:
                btOption.setTextColor(Color.BLUE);
                break;
            case YELLOW:
                btOption.setTextColor(Color.YELLOW);
                break;
            case GRAY:
                btOption.setTextColor(Color.GRAY);
                break;
            case CYAN:
                btOption.setTextColor(Color.CYAN);
                break;
            case BLACK:
                btOption.setTextColor(Color.BLACK);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(rbContextmenu.isChecked()){
            inflater = new MenuInflater(MainActivity.this);
            inflater.inflate(R.menu.menu_context,menu);
        }else{
            inflater = new MenuInflater(MainActivity.this);
            inflater.inflate(R.menu.menu_sub,menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(rbContextmenu.isChecked()){
            switch (item.getItemId()){
                case R.id.blue:
                    btContext.setTextColor(Color.BLUE);
                    break;
                case R.id.green:
                    btContext.setTextColor(Color.GREEN);
                    break;
                case R.id.red:
                    btContext.setTextColor(Color.RED);
                    break;
            }
        }else {
            switch (item.getItemId()){
                case R.id.one:
                    btSub.setTextColor(Color.BLUE);
                    break;
                case R.id.two:
                    btSub.setTextColor(Color.GREEN);
                    break;
                case R.id.three:
                    btSub.setTextColor(Color.RED);
                    break;
            }
        }

        return true;
    }

    @OnClick({R.id.bt_option, R.id.bt_context,R.id.bt_sub, R.id.bt_popup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_option:
                Toast.makeText(MainActivity.this,"选项菜单可以改变我的颜色，试试看",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_context:
                Toast.makeText(MainActivity.this,"长按显示",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_sub:
                Toast.makeText(MainActivity.this,"长按显示",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_popup:
                showPopupmenu();
                break;
        }
    }

    public void showPopupmenu(){
        PopupMenu popupMenu = new PopupMenu(MainActivity.this,btPopup);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.man:
                        Toast.makeText(MainActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.woman:
                        Toast.makeText(MainActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }
}
