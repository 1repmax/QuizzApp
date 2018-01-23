package com.example.android.quizzapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LolQuizzActivity extends AppCompatActivity {

    //Define all the variables and Views used in the application
    public int rightAnswersLoL, wrongAnswersLoL, questionsAnsweredLoL;
    RadioGroup firstQuestionRadioGroup, hashinshinCategoryRadioGroup;
    RadioButton firstQuestionFirstRB, firstQuestionSecondRB, firstQuestionThirdRB;
    RadioButton hashinshinCategoryFirstRB, hashinshinCategorySecondRB, hashinshinCategoryThirdRB;
    TextView nameField;
    Button getResults;
    CheckBox lol_cb_1, lol_cb_2, lol_cb_3;
    EditText getChampionsName;
    String championsName = "zoe";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_layout_lol);

        //Initialize RadioGroup and RadioButtons for first question
        firstQuestionRadioGroup = findViewById(R.id.first_question_radio_group);
        firstQuestionFirstRB = findViewById(R.id.first_question_first_radio_button);
        firstQuestionSecondRB = findViewById(R.id.first_question_second_radio_button);
        firstQuestionThirdRB = findViewById(R.id.first_question_third_radio_button);

        //Initialize all the Checkboxes in LoL quizz
        lol_cb_1 = findViewById(R.id.first_question_lol_cb);
        lol_cb_2 = findViewById(R.id.second_question_lol_cb);
        lol_cb_3 = findViewById(R.id.third_question_lol_cb);
        getChampionsName = findViewById(R.id.pl_edittext);

        //Initialize RadioGroup and RadioButtons for fourth question
        hashinshinCategoryRadioGroup = findViewById(R.id.fourth_question_radio_group);
        hashinshinCategoryFirstRB = findViewById(R.id.fourth_question_first_radio_button);
        hashinshinCategorySecondRB = findViewById(R.id.fourth_question_second_radio_button);
        hashinshinCategoryThirdRB = findViewById(R.id.fourth_question_third_radio_button);

        //Doesn't show input keyboard until the Edittext View is being focused
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        getResults = findViewById(R.id.result_button_lol);
        getResults.setEnabled(false);

        /*
          Adds a state change listener to the first questions radio group. When user chooses one of the answers
         the RadioGroups RadioButtons get disabled and rightAnswersLoL or wrongAnswersLoL and questionsAnsweredLoL global variables get updated.
         */
        firstQuestionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (firstQuestionFirstRB.isChecked() || firstQuestionSecondRB.isChecked()) {
                    wrongAnswersLoL += 1;
                    for (int j = 0; j < firstQuestionRadioGroup.getChildCount(); j++) {
                        firstQuestionRadioGroup.getChildAt(j).setEnabled(false);
                    }
                    questionsAnsweredLoL += 1;
                    displayAnswers(rightAnswersLoL, wrongAnswersLoL, questionsAnsweredLoL);
                } else if (firstQuestionThirdRB.isChecked()) {
                    rightAnswersLoL += 1;
                    for (int j = 0; j < firstQuestionRadioGroup.getChildCount(); j++) {
                        firstQuestionRadioGroup.getChildAt(j).setEnabled(false);
                    }
                    questionsAnsweredLoL += 1;
                    displayAnswers(rightAnswersLoL, wrongAnswersLoL, questionsAnsweredLoL);

                }
            }
        });

        /*
         Adds a state change listener to the fourth questions radio group. When user chooses one of the answers
         the RadioGroups RadioButtons get disabled and rightAnswersLoL or wrongAnswersLoL and questionsAnsweredLoL global variables get updated.
         */
        hashinshinCategoryRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (hashinshinCategoryFirstRB.isChecked() || hashinshinCategoryThirdRB.isChecked()) {
                    wrongAnswersLoL += 1;
                    for (int j = 0; j < hashinshinCategoryRadioGroup.getChildCount(); j++) {
                        hashinshinCategoryRadioGroup.getChildAt(j).setEnabled(false);
                    }
                    questionsAnsweredLoL += 1;
                    displayAnswers(rightAnswersLoL, wrongAnswersLoL, questionsAnsweredLoL);
                } else if (hashinshinCategorySecondRB.isChecked()) {
                    rightAnswersLoL += 1;
                    for (int j = 0; j < hashinshinCategoryRadioGroup.getChildCount(); j++) {
                        hashinshinCategoryRadioGroup.getChildAt(j).setEnabled(false);
                    }
                    questionsAnsweredLoL += 1;
                    displayAnswers(rightAnswersLoL, wrongAnswersLoL, questionsAnsweredLoL);

                }
                enableResultsButton();
            }
        });

        /*
        Add logical IF statements to check how many Checkboxes are checked. When 2 of them are checked, disable the checkboxes and update rightAnswersLoL or wrongAnswersLoL and questionsAnsweredLoL global variables
         */
        lol_cb_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if ((lol_cb_3.isChecked() && lol_cb_2.isChecked()) || (lol_cb_3.isChecked() && lol_cb_1.isChecked()) || lol_cb_2.isChecked()
                        && lol_cb_1.isChecked()) {
                    disableCheckboxes();
                }
                if (lol_cb_1.isChecked() && lol_cb_2.isChecked()) {
                    rightAnswersLoL += 1;
                } else if ((lol_cb_1.isChecked() && lol_cb_3.isChecked()) || (lol_cb_2.isChecked() && lol_cb_3.isChecked())) {
                    wrongAnswersLoL += 1;
                }
                displayAnswers(rightAnswersLoL, wrongAnswersLoL, questionsAnsweredLoL);
                enableResultsButton();
            }
        });

        /*
         Add logical IF statements to check how many Checkboxes are checked. When 2 of them are checked, disable the checkboxes and update rightAnswersLoL or wrongAnswersLoL and questionsAnsweredLoL global variables
         */
        lol_cb_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if ((lol_cb_3.isChecked() && lol_cb_2.isChecked()) || (lol_cb_3.isChecked() && lol_cb_1.isChecked()) || lol_cb_2.isChecked()
                        && lol_cb_1.isChecked()) {
                    disableCheckboxes();
                }
                if (lol_cb_1.isChecked() && lol_cb_2.isChecked()) {
                    rightAnswersLoL += 1;
                } else if ((lol_cb_1.isChecked() && lol_cb_3.isChecked()) || (lol_cb_2.isChecked() && lol_cb_3.isChecked())) {
                    wrongAnswersLoL += 1;
                }
                displayAnswers(rightAnswersLoL, wrongAnswersLoL, questionsAnsweredLoL);
                enableResultsButton();
            }
        });

        /*
          Add logical IF statements to check how many Checkboxes are checked. When 2 of them are checked, disable the checkboxes and update rightAnswersLoL or wrongAnswersLoL and questionsAnsweredLoL global variables
         */
        lol_cb_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if ((lol_cb_3.isChecked() && lol_cb_2.isChecked()) || (lol_cb_3.isChecked() && lol_cb_1.isChecked()) || lol_cb_2.isChecked()
                        && lol_cb_1.isChecked()) {
                    disableCheckboxes();
                }
                if (lol_cb_1.isChecked() && lol_cb_2.isChecked()) {
                    rightAnswersLoL += 1;
                } else if ((lol_cb_1.isChecked() && lol_cb_3.isChecked()) || (lol_cb_2.isChecked() && lol_cb_3.isChecked())) {
                    wrongAnswersLoL += 1;
                }
                displayAnswers(rightAnswersLoL, wrongAnswersLoL, questionsAnsweredLoL);
                enableResultsButton();
            }
        });

        /*

         */
        getChampionsName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    getAnswerString();
                    questionsAnsweredLoL += 1;
                    displayAnswers(rightAnswersLoL, wrongAnswersLoL, questionsAnsweredLoL);
                    Log.i("Log this stuff", Integer.toString(questionsAnsweredLoL));

                    //Hides keyboard when user presses DONE and clear focus from EditText
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(getChampionsName.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    getChampionsName.clearFocus();
                    getChampionsName.setEnabled(false);
                    enableResultsButton();
                }
                return false;
            }
        });

        getResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toast_message = getString(R.string.toast_message, questionsAnsweredLoL, rightAnswersLoL);
                Context context = getApplicationContext();
                CharSequence text = toast_message;
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        /**
         * Getting the Intent that started this activity and extracting the string passed into the Edittext field in first activity
         */
        Intent startQuiz = getIntent();
        String name = startQuiz.getStringExtra(MainActivity.EXTRA_MESSAGE);
        nameField = findViewById(R.id.user_name);
        nameField.setText(name);
    }

    public void displayAnswers(int rightAnswers, int wrongAnswers, int questionsAnswered) {
        nameField.setText("Right: " + Integer.toString(rightAnswers) + "  Wrong: " + Integer.toString(wrongAnswers) + " Answered: " + questionsAnswered);
    }

    public void disableCheckboxes() {
        lol_cb_1.setEnabled(false);
        lol_cb_2.setEnabled(false);
        lol_cb_3.setEnabled(false);
        questionsAnsweredLoL += 1;
    }

    public void getAnswerString() {
        if (getChampionsName.getText().toString().toLowerCase().equals(championsName)) {
            rightAnswersLoL += 1;
            displayAnswers(rightAnswersLoL, wrongAnswersLoL, questionsAnsweredLoL);
        } else {
            wrongAnswersLoL += 1;
            displayAnswers(rightAnswersLoL, wrongAnswersLoL, questionsAnsweredLoL);
        }
    }
    public void enableResultsButton (){
        if (questionsAnsweredLoL == 4) {
            getResults.setEnabled(true);
        }}

}

