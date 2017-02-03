package edu.vcu.cmsc355.temperationconversion;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Converter extends AppCompatActivity {
    private final DecimalFormat FORMAT = new DecimalFormat("#.##");
    private EditText fromTemp;
    private EditText toTemp;
    private RadioButton rbFromFahr;
    private RadioButton rbFromCelsius;
    private RadioButton rbToFahr;
    private RadioButton rbToCelsius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        fromTemp = (EditText) findViewById(R.id.fromTemp);
        toTemp = (EditText) findViewById(R.id.toTemp);
        rbFromCelsius = (RadioButton) findViewById(R.id.rbFromCelsius);
        rbFromFahr = (RadioButton) findViewById(R.id.rbFromFahr);
        rbToCelsius = (RadioButton) findViewById(R.id.rbToCelsius);
        rbToFahr = (RadioButton) findViewById(R.id.rbToFahr);
    }

    public void handleClick(View view){
        String unitFrom = "fahr";
        String unitTo = "fahr";

        switch (view.getId()){
            case R.id.resetButton:
                fromTemp.setText("");
                toTemp.setText("");
                rbFromFahr.setChecked(true);
                rbToFahr.setChecked(true);
                break;

            case R.id.convertButton:
            String input = fromTemp.getText().toString();

            if(input.length() > 0){
                if(rbFromCelsius.isChecked()) unitFrom = "cel";
                if(rbToCelsius.isChecked()) unitTo = "cel";

                if(unitFrom.equals(unitTo)) toTemp.setText(input);
                else if (unitFrom.contentEquals("fahr")){
                    double result = (Double.parseDouble(input) - 32.0) * 5.0/9.0;
                    toTemp.setText(FORMAT.format(result));
                }
                else{
                    double result2 = Double.parseDouble(input) * 9.0/5.0 + 32.0;
                    toTemp.setText(FORMAT.format(result2));
                }
            }
                else{
                Context context = getApplicationContext();
                CharSequence text = "Temperature not given; Unable to convert";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }
}
