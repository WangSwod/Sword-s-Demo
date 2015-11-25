package com.example.apple.dialogdemo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.text.format.Time;
import android.util.Log;
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

    private  EditText date_Input,time_Input;

    private DatePickerDialog date_Picker ;
    private TimePickerDialog time_Picker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date_Input = (EditText)findViewById(R.id.date_Input);
        time_Input = (EditText)findViewById(R.id.time_Input);

     //   time_Input.setInputType(InputType.TYPE_NULL);
       // date_Input.setInputType(InputType.TYPE_NULL);


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

//        date_Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    date_Input.setInputType(InputType.TYPE_NULL);
//                    date_Picker.show();
//                }
//            }
//        });
//
//        time_Input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    time_Input.setInputType(InputType.TYPE_NULL);
//                    time_Picker.show();
//                }
//            }
//        });


    }

    private  void initDialog(){

        Time time = new Time();
        time.setToNow();
        int year = time.year;
        int month = time.month;
        int day = time.monthDay;
        int hour = time.hour;
        int minute = time.minute;


        Log.i(TAG,year+"/"+month+"/"+day + " "+hour +":"+minute);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    date_Input.setText(year+"/"+monthOfYear +"/"+dayOfMonth);
            }
        };

        date_Picker = new DatePickerDialog(MainActivity.this,dateSetListener, year,month,day);

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    time_Input.setText(hourOfDay+":"+minute);

            }
        };

        time_Picker = new TimePickerDialog(MainActivity.this,timeSetListener,hour,minute,true);
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
