package com.example.jgervas.ttemperatureconverter;

import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class TemperatureConverterActivity extends Activity {

    private EditText text;

    //Called when the activity is fist created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        text = (EditText) findViewById(R.id.etTemperature);
        //Method1, dynamic way
        //Implement the onKeyListener callback
        text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //We only want to handle ACTION_UP events, when user releases a key
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    return false;

                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    doConversion();
                    return true;
                } else
                    return false;
            }
        });

    }

    //Method2, static way
    public void calculateTemperature(View v) {

        switch (v.getId()) {
            case R.id.btCalculate:
                doConversion();
                break;
        }
    }



    private void doConversion() {
        RadioButton celsiusButton = (RadioButton) findViewById(R.id.rbCelsius);
        RadioButton fahrenheitButton = (RadioButton) findViewById(R.id.rbFahrenheit);

        if (text.getText().length() == 0) {
            Toast.makeText(this, "Please a valid", Toast.LENGTH_LONG).show();
            return;
        }

        float inputValue = Float.parseFloat(text.getText().toString());
        if (celsiusButton.isChecked()) {
            text.setText(String.valueOf(convertCelsiusToFahrenheit(inputValue)));
            celsiusButton.setChecked(false);
            fahrenheitButton.setChecked(true);
        } else {
            text.setText(String.valueOf(convertFahrenheitToCelsius(inputValue)));
            fahrenheitButton.setChecked(false);
            celsiusButton.setChecked(true);
        }
    }

    private float convertFahrenheitToCelsius(float fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }

    private float convertCelsiusToFahrenheit(float celsius) {

        return (((celsius * 9) / 5) + 32);
    }

}
