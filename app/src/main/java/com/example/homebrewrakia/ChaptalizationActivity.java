package com.example.homebrewrakia;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormatSymbols;
import java.util.Map;

public class ChaptalizationActivity extends AppCompatActivity {

    private EditText txtSourceDegree;
    private EditText txtSourceVolume;
    private EditText txtSugarDensity;
    private EditText txtTargetDegree;
    private EditText txtResult;

    private ChaptalizationService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaptalization);

        service = new ChaptalizationService(this);

        txtSourceDegree = findViewById(R.id.txtSourceDegree);
        txtSourceVolume = findViewById(R.id.txtSourceVolume);
        txtSugarDensity = findViewById(R.id.txtSugarDensity);
        txtTargetDegree = findViewById(R.id.txtTargetDegree);
        txtResult = findViewById(R.id.txtResult);
        txtResult.setEnabled(false);

        char separator = DecimalFormatSymbols.getInstance().getDecimalSeparator();
        txtSourceDegree.setKeyListener(DigitsKeyListener.getInstance("0123456789" + separator));
        txtSourceVolume.setKeyListener(DigitsKeyListener.getInstance("0123456789" + separator));
        txtSugarDensity.setKeyListener(DigitsKeyListener.getInstance("0123456789" + separator));
        txtTargetDegree.setKeyListener(DigitsKeyListener.getInstance("0123456789" + separator));

        Button btnChaptalizationOK = findViewById(R.id.btnChaptalizationOK);
        Button btnCorrectSourceDegree = findViewById(R.id.btnCorrectSourceDegree);

        //Define and attach click listeners
        btnChaptalizationOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateChaptalization();
            }
        });

        btnCorrectSourceDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to do ... save to bundle
                startSugarCorrectionActivity(Constants.REQUEST_CODE_SUGAR_CORRECTION_ACTIVITY_FOR_SOURCE_DEGREE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_CODE_SUGAR_CORRECTION_ACTIVITY_FOR_SOURCE_DEGREE && resultCode == Activity.RESULT_OK) {
            // to do ... save to bundle
            txtSourceDegree.setText(data.getStringExtra(Constants.EXTRA_RESULT));
        }
    }

    private void calculateChaptalization() {
        ErrorMessageDialogFragment dialog;

        Map.Entry<Boolean, String> setSourceDegreeResult = service.trySetSourceDegree(txtSourceDegree.getText().toString());
        if (!setSourceDegreeResult.getKey()) {
            dialog = ErrorMessageDialogFragment.newInstance(setSourceDegreeResult.getValue());
            dialog.show(getSupportFragmentManager(), "Error");

            txtResult.setText("");
            txtSourceDegree.requestFocus();
            return;
        }
        Map.Entry<Boolean, String> setSourceVolumeResult = service.trySetSourceVolume(txtSourceVolume.getText().toString());
        if (!setSourceVolumeResult.getKey()) {
            dialog = ErrorMessageDialogFragment.newInstance(setSourceVolumeResult.getValue());
            dialog.show(getSupportFragmentManager(), "Error");

            txtResult.setText("");
            txtSourceVolume.requestFocus();
            return;
        }
        Map.Entry<Boolean, String> setDiluentDegreeResult = service.trySetSugarDensity(txtSugarDensity.getText().toString());
        if (!setDiluentDegreeResult.getKey()) {
            dialog = ErrorMessageDialogFragment.newInstance(setDiluentDegreeResult.getValue());
            dialog.show(getSupportFragmentManager(), "Error");

            txtResult.setText("");
            txtSugarDensity.requestFocus();
            return;
        }
        Map.Entry<Boolean, String> setTargetDegreeResult = service.trySetTargetDegree(txtTargetDegree.getText().toString());
        if (!setTargetDegreeResult.getKey()) {
            dialog = ErrorMessageDialogFragment.newInstance(setTargetDegreeResult.getValue());
            dialog.show(getSupportFragmentManager(), "Error");

            txtResult.setText("");
            txtTargetDegree.requestFocus();
            return;
        }

        float sugarMass = service.getSugarMass();
        txtResult.setText(String.format("%d", (int)sugarMass));
    }

    private void startSugarCorrectionActivity(int requestCode) {
        Intent correctionIntent = new Intent(this, SugarCorrectionActivity.class);
        correctionIntent.putExtra(Constants.EXTRA_RETURN_TO_CALLER, true);
        if (requestCode == Constants.REQUEST_CODE_SUGAR_CORRECTION_ACTIVITY_FOR_SOURCE_DEGREE)
            correctionIntent.putExtra(Constants.EXTRA_INPUT_VALUE, txtSourceDegree.getText().toString());
        startActivityForResult(correctionIntent, requestCode);
    }
}
