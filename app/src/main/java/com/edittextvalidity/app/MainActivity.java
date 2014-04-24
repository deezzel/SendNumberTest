package com.edittextvalidity.app;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;


public class MainActivity extends ActionBarActivity implements PopupFragment.PopupDialogListener {

    private PopupWindow popup_window;
    private Button btn_show;
    private Button btn_send;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView showNumber = (TextView) findViewById(R.id.show_number);
        showNumber.setText("No input");
        btn_show = (Button)findViewById(R.id.show_popup);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup();
            }
        });

    }


    private void showPopup(){
        DialogFragment popupFragment = new PopupFragment();
        popupFragment.show(getFragmentManager(),"PopupFragment");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onButtonSendClick(DialogFragment dialogFragment, CharSequence value) {
        if (value.toString().equalsIgnoreCase("")){
            value = "";
        } else if (!value.toString().matches("[0-9]*")){
            value = "";
        } else {
            TextView tw = (TextView) findViewById(R.id.show_number);
            tw.setText(value);
            dialogFragment.dismiss();
        }
    }
}
