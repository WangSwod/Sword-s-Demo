package com.example.apple.dialogdemo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends ActionBarActivity {

    static final String TAG= "MainActivity";

    static  final String KEY_DATE ="key_date";
    static  final String KEY_TIME = "key_time";
    static  final String KEY_WEIGHT = "key_weight";
    static  final String PREFERENCE = "MainActivity_Preference";

    private  EditText date_Input,time_Input,weight_inpt;

    private DatePickerDialog date_Picker ;
    private TimePickerDialog time_Picker;

    private  int year,month, day,hour,minute;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date_Input = (EditText)findViewById(R.id.date_Input);
        time_Input = (EditText)findViewById(R.id.time_Input);
        weight_inpt = (EditText)findViewById(R.id.weight_input);
        //刷新为当前时间
        refreshTime();
        //初始化dialogs
        initDialog();


        date_Input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(v.getWindowToken(),0);

                date_Input.setInputType(InputType.TYPE_NULL);
                date_Picker.show();
                return true;
            }
        });

        time_Input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                time_Input.setInputType(InputType.TYPE_NULL);
                time_Picker.show();
                return true;
            }
        });


        restoreUI();

    }



    private  void restoreUI(){
        SharedPreferences restore_Preference = getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE);

        String restore_Date = restore_Preference.getString(KEY_DATE,year+"/"+month +"/"+day);
        String restore_Time = restore_Preference.getString(KEY_TIME,hour+":"+minute);
        String restore_Weight = restore_Preference.getString(KEY_WEIGHT,"0");

        weight_inpt.setText(restore_Weight);
        time_Input.setText(restore_Time);
        date_Input.setText(restore_Date);

    }

  public void refreshTime(){
        //获取当前date 和 time
        Time time = new Time();
        time.setToNow();
        year = time.year;
        month = time.month;
        day = time.monthDay;
        hour = time.hour;
        minute = time.minute;

    }

    private  void initDialog(){



        //实例化date_picker dialog
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    date_Input.setText(year+"/"+monthOfYear +"/"+dayOfMonth);
            }
        };

        date_Picker = new DatePickerDialog(MainActivity.this,dateSetListener, year,month,day);


        //实例化time_picker dialog
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    time_Input.setText(hourOfDay+":"+minute);

            }
        };

        time_Picker = new TimePickerDialog(MainActivity.this,timeSetListener,hour,minute,true);
    }


    @Override
    protected void onPause() {

        SharedPreferences save_Preference = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = save_Preference.edit();

        editor.putString(KEY_DATE,date_Input.getText().toString());
        editor.putString(KEY_TIME,time_Input.getText().toString());
        editor.putString(KEY_WEIGHT, weight_inpt.getText().toString());

        editor.commit();

        super.onPause();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
