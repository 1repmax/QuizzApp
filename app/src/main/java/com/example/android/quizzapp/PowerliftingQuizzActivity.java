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

public class PowerliftingQuizzActivity extends AppCompatActivity {

    static final String FED_NAME = "fedName";
    static final String EDIT_TEXT_STATE = "edtTxtState";
    static final String RB_1_STATE = "rb1State";
    static final String RB_2_STATE = "rb2State";
    static final String RB_3_STATE = "rb3State";
    static final String RB_1_CHECKED = "rb1State";
    static final String RB_2_CHECKED = "rb2State";
    static final String RB_3_CHECKED = "rb3State";
    static final String RB_1AGE_STATE = "rb1AgeState";
    static final String RB_2AGE_STATE = "rb2AgeState";
    static final String RB_3AGE_STATE = "rb3AgeState";
    static final String RB_1AGE_CHECKED = "rb1AgeState";
    static final String RB_2AGE_CHECKED = "rb2AgeState";
    static final String RB_3AGE_CHECKED = "rb3AgeState";
    static final String CB_1_STATE = "cb1State";
    static final String CB_2_STATE = "cb2State";
    static final String CB_3_STATE = "cb3State";
    static final String CB_1_CHECKED = "cb1Checked";
    static final String CB_2_CHECKED = "cb2Checked";
    static final String CB_3_CHECKED = "cb3Checked";
    //Define all the variables and Views used in the application
    public int rightAnswersPL, wrongAnswersPL, questionsAnsweredPL;
    RadioGroup firstQuestionRadioGroup, ageCategoryRadioGroup;
    RadioButton firstQuestionFirstRB, firstQuestionSecondRB, firstQuestionThirdRB;
    RadioButton ageCategoryFirstRB, ageCategorySecondRB, ageCategoryThirdRB;
    TextView nameField;
    CheckBox powerlifting_cb_1, powerlifting_cb_2, powerlifting_cb_3;
    EditText getFederationsName;
    String orgName = "international powerlifting federation";
    Button getResults;

    /*
    Method used to save states of views and variables.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //Save EditTexts state and name for the IPF
        savedInstanceState.putString(FED_NAME, getFederationsName.getText().toString());
        savedInstanceState.putBoolean(EDIT_TEXT_STATE, getFederationsName.isEnabled());

        //Save states of RadioButtons as booleans for first question
        savedInstanceState.putBoolean(RB_1_STATE, firstQuestionFirstRB.isEnabled());
        savedInstanceState.putBoolean(RB_2_STATE, firstQuestionSecondRB.isEnabled());
        savedInstanceState.putBoolean(RB_3_STATE, firstQuestionThirdRB.isEnabled());
        //Save Checks of RadioGroups as booleans for first question
        savedInstanceState.putBoolean(RB_1_CHECKED, firstQuestionFirstRB.isChecked());
        savedInstanceState.putBoolean(RB_2_CHECKED, firstQuestionSecondRB.isChecked());
        savedInstanceState.putBoolean(RB_3_CHECKED, firstQuestionThirdRB.isChecked());

        //Save states of RadioButtons as booleans for third question
        savedInstanceState.putBoolean(RB_1AGE_STATE, ageCategoryFirstRB.isEnabled());
        savedInstanceState.putBoolean(RB_2AGE_STATE, ageCategorySecondRB.isEnabled());
        savedInstanceState.putBoolean(RB_3AGE_STATE, ageCategoryThirdRB.isEnabled());
        //Save Checks of RadioGroups as booleans for third question
        savedInstanceState.putBoolean(RB_1AGE_CHECKED, ageCategoryFirstRB.isChecked());
        savedInstanceState.putBoolean(RB_2AGE_CHECKED, ageCategorySecondRB.isChecked());
        savedInstanceState.putBoolean(RB_3AGE_CHECKED, ageCategoryThirdRB.isChecked());

        //Save states of RadioButtons as booleans for 2nd question
        savedInstanceState.putBoolean(CB_1_STATE, firstQuestionFirstRB.isEnabled());
        savedInstanceState.putBoolean(CB_2_STATE, firstQuestionSecondRB.isEnabled());
        savedInstanceState.putBoolean(CB_3_STATE, firstQuestionThirdRB.isEnabled());
        //Save Checks of RadioGroups as booleans for 2nd question
        savedInstanceState.putBoolean(CB_1_CHECKED, firstQuestionFirstRB.isChecked());
        savedInstanceState.putBoolean(CB_2_CHECKED, firstQuestionSecondRB.isChecked());
        savedInstanceState.putBoolean(CB_3_CHECKED, firstQuestionThirdRB.isChecked());
    }

    /*
    Method used to restore states of Buttons, Radiobuttons, Checkboxes and other views.
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        //Restore EditTexts state and name for the IPF
        getFederationsName.setText(savedInstanceState.getString(FED_NAME));
        boolean textFieldState = savedInstanceState.getBoolean(EDIT_TEXT_STATE);
        getFederationsName.setEnabled(textFieldState);
        getAnswerString();

        //Restore states and checks for first question RadioButtons
        boolean firstRbState = savedInstanceState.getBoolean(RB_1_STATE);
        boolean secondRbState = savedInstanceState.getBoolean(RB_2_STATE);
        boolean thirdRbState = savedInstanceState.getBoolean(RB_3_STATE);
        firstQuestionFirstRB.setEnabled(!firstRbState);
        firstQuestionSecondRB.setEnabled(!secondRbState);
        firstQuestionThirdRB.setEnabled(!thirdRbState);
        boolean firstRbChecked = savedInstanceState.getBoolean(RB_1_CHECKED);
        boolean secondRbChecked = savedInstanceState.getBoolean(RB_2_CHECKED);
        boolean thirdRbChecked = savedInstanceState.getBoolean(RB_3_CHECKED);
        firstQuestionFirstRB.setChecked(firstRbChecked);
        firstQuestionSecondRB.setChecked(secondRbChecked);
        firstQuestionThirdRB.setChecked(thirdRbChecked);

        //Restore states and checks for first question RadioButtons
        boolean firstRbAgeState = savedInstanceState.getBoolean(RB_1AGE_STATE);
        boolean secondRbAgeState = savedInstanceState.getBoolean(RB_2AGE_STATE);
        boolean thirdRbAgeState = savedInstanceState.getBoolean(RB_3AGE_STATE);
        ageCategoryFirstRB.setEnabled(!firstRbAgeState);
        ageCategorySecondRB.setEnabled(!secondRbAgeState);
        ageCategoryThirdRB.setEnabled(!thirdRbAgeState);
        boolean firstRbAgeChecked = savedInstanceState.getBoolean(RB_1AGE_CHECKED);
        boolean secondRbAgeChecked = savedInstanceState.getBoolean(RB_2AGE_CHECKED);
        boolean thirdRbAgeChecked = savedInstanceState.getBoolean(RB_3AGE_CHECKED);
        ageCategoryFirstRB.setChecked(firstRbAgeChecked);
        ageCategorySecondRB.setChecked(secondRbAgeChecked);
        ageCategoryThirdRB.setChecked(thirdRbAgeChecked);


        //Restore states and checks for the Checkboxes in 2nd question
        boolean firstCbState = savedInstanceState.getBoolean(CB_1_STATE);
        boolean secondCbState = savedInstanceState.getBoolean(CB_2_STATE);
        boolean thirdCbState = savedInstanceState.getBoolean(CB_3_STATE);
        powerlifting_cb_1.setEnabled(!firstCbState);
        powerlifting_cb_2.setEnabled(!secondCbState);
        powerlifting_cb_3.setEnabled(!thirdCbState);
        boolean firstCbChecked = savedInstanceState.getBoolean(CB_1_CHECKED);
        boolean secondCbChecked = savedInstanceState.getBoolean(CB_2_CHECKED);
        boolean thirdCbChecked = savedInstanceState.getBoolean(CB_3_CHECKED);
        powerlifting_cb_1.setChecked(firstCbChecked);
        powerlifting_cb_1.setChecked(secondCbChecked);
        powerlifting_cb_1.setChecked(thirdCbChecked);
    }

    /*
    Method which is used to inflate all the Views and their hierarchy during onStart() part of activity lifecycle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_layout_pl);

        //Initialize RadioGroup and RadioButtons for first question
        firstQuestionRadioGroup = findViewById(R.id.first_question_radio_group);
        firstQuestionFirstRB = findViewById(R.id.first_question_first_radio_button);
        firstQuestionSecondRB = findViewById(R.id.first_question_second_radio_button);
        firstQuestionThirdRB = findViewById(R.id.first_question_third_radio_button);

        //Initialize all the Checkboxes in Powerlifting Quizz
        powerlifting_cb_1 = findViewById(R.id.first_question_pl_cb);
        powerlifting_cb_2 = findViewById(R.id.second_question_pl_cb);
        powerlifting_cb_3 = findViewById(R.id.third_question_pl_cb);
        getFederationsName = findViewById(R.id.pl_edittext);

        //Initialize RadioGroup and RadioButtons for fourth question
        ageCategoryRadioGroup = findViewById(R.id.fourth_question_radio_group);
        ageCategoryFirstRB = findViewById(R.id.fourth_question_first_radio_button);
        ageCategorySecondRB = findViewById(R.id.fourth_question_second_radio_button);
        ageCategoryThirdRB = findViewById(R.id.fourth_question_third_radio_button);

        //Initialize GetResults button which allows user to get his quizz results
        getResults = findViewById(R.id.result_button_pl);
        getResults.setEnabled(false);

        //Doesn't show input keyboard until the Edittext View is being focused
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /*
          Adds a state change listener to the first questions radio group. When user chooses one of the answers
         the RadioGroups RadioButtons get disabled and rightAnswersLoL or wrongAnswersLoL and questionsAnsweredLoL global variables get updated.
         */
        firstQuestionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (firstQuestionFirstRB.isChecked() || firstQuestionThirdRB.isChecked()) {
                    wrongAnswersPL += 1;
                    disableRadiobuttonsQuestion1();
                } else if (firstQuestionSecondRB.isChecked()) {
                    rightAnswersPL += 1;
                    disableRadiobuttonsQuestion1();
                }
                questionsAnsweredPL += 1;
                displayAnswers(rightAnswersPL, wrongAnswersPL, questionsAnsweredPL);
                enableResultsButton();
            }
        });

        /*
         Adds a state change listener to the fourth questions radio group. When user chooses one of the answers
         the RadioGroups RadioButtons get disabled and rightAnswersLoL or wrongAnswersLoL and questionsAnsweredLoL global variables get updated.
         */
        ageCategoryRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (ageCategoryFirstRB.isChecked() || ageCategoryThirdRB.isChecked()) {
                    wrongAnswersPL += 1;
                    disableRadiobuttonsQuestion3();
                } else if (ageCategorySecondRB.isChecked()) {
                    rightAnswersPL += 1;
                    disableRadiobuttonsQuestion3();
                }
                questionsAnsweredPL += 1;
                displayAnswers(rightAnswersPL, wrongAnswersPL, questionsAnsweredPL);
                enableResultsButton();
            }
        });

        /*
        Add logical IF statements to check how many Checkboxes are checked. When 2 of them are checked, disable the checkboxes and update rightAnswersLoL or wrongAnswersLoL and questionsAnsweredLoL global variables
         */
        powerlifting_cb_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if ((powerlifting_cb_3.isChecked() && powerlifting_cb_2.isChecked()) || (powerlifting_cb_3.isChecked() && powerlifting_cb_1.isChecked()) || powerlifting_cb_2.isChecked()
                        && powerlifting_cb_1.isChecked()) {
                    disableCheckboxes();
                }
                if (powerlifting_cb_2.isChecked() && powerlifting_cb_3.isChecked()) {
                    rightAnswersPL += 1;
                } else if ((powerlifting_cb_1.isChecked() && powerlifting_cb_2.isChecked()) || (powerlifting_cb_1.isChecked() && powerlifting_cb_3.isChecked())) {
                    wrongAnswersPL += 1;
                }
                displayAnswers(rightAnswersPL, wrongAnswersPL, questionsAnsweredPL);
                enableResultsButton();
            }
        });

        /*
         Add logical IF statements to check how many Checkboxes are checked. When 2 of them are checked, disable the checkboxes and update rightAnswersLoL or wrongAnswersLoL and questionsAnsweredLoL global variables
         */
        powerlifting_cb_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if ((powerlifting_cb_3.isChecked() && powerlifting_cb_2.isChecked()) || (powerlifting_cb_3.isChecked() && powerlifting_cb_1.isChecked()) || powerlifting_cb_2.isChecked()
                        && powerlifting_cb_1.isChecked()) {
                    disableCheckboxes();
                }
                if (powerlifting_cb_2.isChecked() && powerlifting_cb_3.isChecked()) {
                    rightAnswersPL += 1;
                } else if ((powerlifting_cb_1.isChecked() && powerlifting_cb_2.isChecked()) || (powerlifting_cb_1.isChecked() && powerlifting_cb_3.isChecked())) {
                    wrongAnswersPL += 1;
                }
                displayAnswers(rightAnswersPL, wrongAnswersPL, questionsAnsweredPL);
                enableResultsButton();
            }
        });

        /*
          Add logical IF statements to check how many Checkboxes are checked. When 2 of them are checked, disable the checkboxes and update rightAnswersLoL or wrongAnswersLoL and questionsAnsweredLoL global variables
         */
        powerlifting_cb_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if ((powerlifting_cb_3.isChecked() && powerlifting_cb_2.isChecked()) || (powerlifting_cb_3.isChecked() && powerlifting_cb_1.isChecked()) || powerlifting_cb_2.isChecked()
                        && powerlifting_cb_1.isChecked()) {
                    disableCheckboxes();
                }
                if (powerlifting_cb_2.isChecked() && powerlifting_cb_3.isChecked()) {
                    rightAnswersPL += 1;
                } else if ((powerlifting_cb_1.isChecked() && powerlifting_cb_2.isChecked()) || (powerlifting_cb_1.isChecked() && powerlifting_cb_3.isChecked())) {
                    wrongAnswersPL += 1;
                }
                displayAnswers(rightAnswersPL, wrongAnswersPL, questionsAnsweredPL);
                enableResultsButton();
            }
        });

        /*
            Adds an OnEditorActionListener which allows to compare entered text only once user has finished input. After user is done, focus is cleared from the Edittext View.
         */
        getFederationsName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    getAnswerString();

                    displayAnswers(rightAnswersPL, wrongAnswersPL, questionsAnsweredPL);
                    Log.i("Log this stuff", Integer.toString(questionsAnsweredPL));
                    //Hides keyboard when user presses DONE and clear focus from EditText
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(getFederationsName.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    getFederationsName.clearFocus();
                    getFederationsName.setEnabled(false);
                    enableResultsButton();
                }
                return false;
            }
        });

/*
Adds a OnClickListener to the getResults button. When user clicks this button an Toast is created and results are passed into the toast.
 */
        getResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toast_message = getString(R.string.toast_message, questionsAnsweredPL, rightAnswersPL);
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, toast_message, duration);
                toast.show();
            }
        });

        /*
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


    /*
    Public method which  compares the Editable of Edittext to right answer and increases global variable rightAnswers or wrongAnswers.
     */
    public void getAnswerString() {
        if (getFederationsName.getText().toString().toLowerCase().equals(orgName)) {
            rightAnswersPL += 1;
            questionsAnsweredPL += 1;
            displayAnswers(rightAnswersPL, wrongAnswersPL, questionsAnsweredPL);
        } else {
            wrongAnswersPL += 1;
            questionsAnsweredPL += 1;
            displayAnswers(rightAnswersPL, wrongAnswersPL, questionsAnsweredPL);
        }
    }

    /*
    Public method which enables getResults button as soon as user has answered all the questions.
     */
    public void enableResultsButton() {
        if (questionsAnsweredPL == 4) {
            getResults.setEnabled(true);
        }
        if (questionsAnsweredPL > 4) {
            questionsAnsweredPL = 4;
        }
    }

    /*
    Public method which disables Radio buttons for first question
     */
    public void disableRadiobuttonsQuestion1() {
        firstQuestionFirstRB.setEnabled(false);
        firstQuestionSecondRB.setEnabled(false);
        firstQuestionThirdRB.setEnabled(false);
    }

    /*
Public method which disables Radio buttons for third question
 */
    public void disableRadiobuttonsQuestion3() {
        ageCategoryFirstRB.setEnabled(false);
        ageCategorySecondRB.setEnabled(false);
        ageCategoryThirdRB.setEnabled(false);
    }

    /*
Public method which disables Checkboxes for second question
 */
    public void disableCheckboxes() {
        powerlifting_cb_1.setEnabled(false);
        powerlifting_cb_2.setEnabled(false);
        powerlifting_cb_3.setEnabled(false);
    }

}

