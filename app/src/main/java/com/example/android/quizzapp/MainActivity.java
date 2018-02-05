package com.example.android.quizzapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "enteredNameKey";
    public String name;
    /*
    Declaration of the Views that are used in the MainActivity
     */
    Button startPowerliftingQuizz, startLolQuizz;
    EditText enterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Initialization of Views used in the application.
         */
        startPowerliftingQuizz = findViewById(R.id.powerlifting_launch_button);
        enterName = findViewById(R.id.name_textfield);

        //Doesn't show input keyboard until the Edittext View is being focused
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /**
         * Allows to save users name to the variable as soon as it changes.
         */
        enterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                name = enterName.getText().toString();
            }
        });

        /**
         * Starts intent setStartQuiz when startQuiz Button is pressed
         */
        startPowerliftingQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setStartPowerliftingQuizz();
            }
        });

    }

    /**
     * Method declaration which starts a new intent and loads PowerliftingQuizzActivity.class
     */
    public void setStartPowerliftingQuizz() {
        Intent startPLQuizIntent = new Intent(this, PowerliftingQuizzActivity.class);
        startPLQuizIntent.putExtra(EXTRA_MESSAGE, name);
        startActivity(startPLQuizIntent);
    }

}
