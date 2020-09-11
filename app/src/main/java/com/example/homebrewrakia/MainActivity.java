package com.example.homebrewrakia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSolution = findViewById(R.id.btnSolution);
        Button btnCorrection = findViewById(R.id.btnCorrection);
        Button btnChaptalization = findViewById(R.id.btnChaptalization);
        Button btnSugarCorrection = findViewById(R.id.btnSugarCorrection);
        Button btnSulfite = findViewById(R.id.btnSulfite);

        //Define and attach click listeners
        btnSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSolutionActivity();
            }
        });
        btnCorrection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCorrectionActivity();
            }
        });
        btnChaptalization.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startChaptalizationActivity();
             }
        });
        btnSugarCorrection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSugarCorrectionActivity();
            }
        });
        btnSulfite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSulfiteActivity();
            }
        });
    }

    private void startSolutionActivity() {
        Intent solutionIntent = new Intent(this, SolutionActivity.class);
        solutionIntent.putExtra(Constants.EXTRA_RETURN_TO_CALLER, false);
        startActivity(solutionIntent);
    }

    private void startCorrectionActivity() {
        Intent correctionIntent = new Intent(this, CorrectionActivity.class);
        correctionIntent.putExtra(Constants.EXTRA_RETURN_TO_CALLER, false);
        startActivity(correctionIntent);
    }

    private void startChaptalizationActivity() {
        Intent chaptalizationIntent = new Intent(this, ChaptalizationActivity.class);
        chaptalizationIntent.putExtra(Constants.EXTRA_RETURN_TO_CALLER, false);
        startActivity(chaptalizationIntent);
    }

    private void startSugarCorrectionActivity() {
        Intent sugarCorrectionIntent = new Intent(this, SugarCorrectionActivity.class);
        sugarCorrectionIntent.putExtra(Constants.EXTRA_RETURN_TO_CALLER, false);
        startActivity(sugarCorrectionIntent);
    }

    private void startSulfiteActivity() {
        Intent sulfiteIntent = new Intent(this, SulfiteActivity.class);
        sulfiteIntent.putExtra(Constants.EXTRA_RETURN_TO_CALLER, false);
        startActivity(sulfiteIntent);
    }
}
