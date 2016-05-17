package com.darshilpatel.tipme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currentFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private double billAmount = 0.0;
    private double percent = 0.15;
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private TextView peopleTextView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextView = (TextView)findViewById(R.id.amountTextView);
        percentTextView = (TextView)findViewById(R.id.percentTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        peopleTextView = (TextView)findViewById(R.id.peopleTextView);


        tipTextView.setText(currentFormat.format(0));
        totalTextView.setText(currentFormat.format(0));
            EditText amountEditText = (EditText)findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        SeekBar percentSeekBar = (SeekBar)findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);

        SeekBar peopleSeekBar = (SeekBar)findViewById(R.id.peopleSeekBar);
        peopleSeekBar.setOnSeekBarChangeListener(seekPeopleListener);
    }

    private void calculate (){
        percentTextView.setText(percentFormat.format(percent));

        double tip = billAmount * percent;
        double finalAmount = billAmount + tip;

        tipTextView.setText(currentFormat.format(tip));
        totalTextView.setText(currentFormat.format(finalAmount));
    }

    private void calculatePeople(){
        peopleTextView.setText(percentFormat.format(percent));
    }
        //seekbar listener

    // everytime the user moves the slider, it will re calculate. SO the calculations are real time.
    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            percent = progress /100.0; //converts into percent form
            calculate(); // calls the calculation function

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
          // fixed app crash on empty userInput


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {
                billAmount = Double.parseDouble(s.toString()) / 100;
                amountTextView.setText(currentFormat.format(billAmount));
            } catch (NumberFormatException e){
                amountTextView.setText("");
                billAmount = 0.0;
            }
            calculate();// update textviews and calculations
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    // everytime the user moves the slider, it will re calculate. SO the calculations are real time.
    private final SeekBar.OnSeekBarChangeListener seekPeopleListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            calculate(); // calls the calculation function

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}
