package com.example.android.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.android.quizapp.NAME";
    public static final String EXTRA_MESSAGE = "com.example.android.quizapp.MESSAGE";
    public static final String EXTRA_INTEGER = "-1";
    int correctAnswered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String nameOfUser = intent.getStringExtra(ResultActivity.EXTRA_NAME);
        String messageBack = intent.getStringExtra(ResultActivity.EXTRA_MESSAGE);
        int scoreBack = intent.getIntExtra(ResultActivity.EXTRA_INTEGER, Integer.parseInt(ResultActivity.EXTRA_INTEGER));

        final TextView result = (TextView) findViewById(R.id.answer_result);

        if (scoreBack >= 0) {
            result.setText(messageBack);
            result.setVisibility(View.VISIBLE);
            ((EditText)findViewById(R.id.name_field)).setText(nameOfUser);
        } else
            result.setVisibility(View.GONE);

        Button submit = (Button) findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameOfUser = (((EditText) findViewById(R.id.name_field)).getText()).toString();
                String massageForUser = allQuestionsAnswered(nameOfUser);

                if (nameOfUser.isEmpty()) {
                    // Show an error message as a toast
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Enter Your Name", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                } else if (!(massageForUser.isEmpty())) {
                    //Show an error message as a toast
                    Toast toast = Toast.makeText(getApplicationContext(), massageForUser, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                } else {
                    numberOfCorrectAnswered();
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra(EXTRA_NAME, nameOfUser);
                    intent.putExtra(EXTRA_INTEGER, correctAnswered);
                    massageForUser = nameOfUser + " you answered " + correctAnswered + " of 5 ";
                    if (correctAnswered == 0) {
                        massageForUser += "!! \nTry again ";
                        intent.putExtra(EXTRA_MESSAGE, massageForUser);
                        startActivity(intent);
                    } else if (correctAnswered <= 2) {
                        massageForUser += "\nGood but try again";
                        intent.putExtra(EXTRA_MESSAGE, massageForUser);
                        startActivity(intent);
                    } else if (correctAnswered == 3) {
                        massageForUser += "\nGood job !";
                        intent.putExtra(EXTRA_MESSAGE, massageForUser);
                        startActivity(intent);
                    } else if (correctAnswered == 4) {
                        massageForUser += "\nExcellent !!";
                        intent.putExtra(EXTRA_MESSAGE, massageForUser);
                        startActivity(intent);
                    } else {
                        massageForUser += "\nWonderful , Great job !!!";
                        intent.putExtra(EXTRA_MESSAGE, massageForUser);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public String allQuestionsAnswered(String nameOfUser) {

        CheckBox answer_1_for_Questions_1 = (CheckBox) findViewById(R.id.an1_q1);
        CheckBox answer_2_for_Questions_1 = (CheckBox) findViewById(R.id.an2_q1);
        CheckBox answer_3_for_Questions_1 = (CheckBox) findViewById(R.id.an3_q1);
        CheckBox answer_4_for_Questions_1 = (CheckBox) findViewById(R.id.an4_q1);

        RadioButton answer_1_for_Questions_2 = (RadioButton) findViewById(R.id.an1_radio_button_q2);
        RadioButton answer_2_for_Questions_2 = (RadioButton) findViewById(R.id.an2_radio_button_q2);
        RadioButton answer_3_for_Questions_2 = (RadioButton) findViewById(R.id.an3_radio_button_q2);
        RadioButton answer_4_for_Questions_2 = (RadioButton) findViewById(R.id.an4_radio_button_q2);

        String answer_for_Questions_3 = (((EditText) findViewById(R.id.anField_q3)).getText()).toString();

        RadioButton answer_1_for_Questions_4 = (RadioButton) findViewById(R.id.an1_radio_button_q4);
        RadioButton answer_2_for_Questions_4 = (RadioButton) findViewById(R.id.an2_radio_button_q4);
        RadioButton answer_3_for_Questions_4 = (RadioButton) findViewById(R.id.an3_radio_button_q4);
        RadioButton answer_4_for_Questions_4 = (RadioButton) findViewById(R.id.an4_radio_button_q4);

        CheckBox answer_1_for_Questions_5 = (CheckBox) findViewById(R.id.an1_q5);
        CheckBox answer_2_for_Questions_5 = (CheckBox) findViewById(R.id.an2_q5);
        CheckBox answer_3_for_Questions_5 = (CheckBox) findViewById(R.id.an3_q5);
        CheckBox answer_4_for_Questions_5 = (CheckBox) findViewById(R.id.an4_q5);
        CheckBox answer_5_for_Questions_5 = (CheckBox) findViewById(R.id.an5_q5);
        CheckBox answer_6_for_Questions_5 = (CheckBox) findViewById(R.id.an6_q5);

        String massage = "";
        int missAnswerQuestions1;
        int missAnswerQuestions2;
        int missAnswerQuestions3;
        int missAnswerQuestions4;
        int missAnswerQuestions5;

        if (answer_1_for_Questions_1.isChecked() || answer_2_for_Questions_1.isChecked() || answer_3_for_Questions_1.isChecked() || answer_4_for_Questions_1.isChecked())
            missAnswerQuestions1 = 0;
        else {
            massage += nameOfUser + " you miss to answer Questions 1";
            missAnswerQuestions1 = 1;
        }

        if (answer_1_for_Questions_2.isChecked() || answer_2_for_Questions_2.isChecked() || answer_3_for_Questions_2.isChecked() || answer_4_for_Questions_2.isChecked())
            missAnswerQuestions2 = 0;
        else {
            if (massage.isEmpty())
                massage += nameOfUser + " you miss to answer Questions 2";
            else
                massage += ", Questions 2";
            missAnswerQuestions2 = 1;
        }

        if (!(answer_for_Questions_3.isEmpty()))
            missAnswerQuestions3 = 0;
        else {
            if (massage.isEmpty())
                massage += nameOfUser + " you miss to answer Questions 3";
            else
                massage += ", Questions 3";
            missAnswerQuestions3 = 1;
        }

        if (answer_1_for_Questions_4.isChecked() || answer_2_for_Questions_4.isChecked() || answer_3_for_Questions_4.isChecked() || answer_4_for_Questions_4.isChecked())
            missAnswerQuestions4 = 0;
        else {
            if (massage.isEmpty())
                massage += nameOfUser + " you miss to answer Questions 4";
            else
                massage += ", Questions 4";
            missAnswerQuestions4 = 1;
        }

        if (answer_1_for_Questions_5.isChecked() || answer_2_for_Questions_5.isChecked() || answer_3_for_Questions_5.isChecked() || answer_4_for_Questions_5.isChecked() || answer_5_for_Questions_5.isChecked() || answer_6_for_Questions_5.isChecked())
            missAnswerQuestions5 = 0;
        else {
            if (massage.isEmpty())
                massage += nameOfUser + " you miss to answer Questions 5";
            else
                massage += ", Questions 5";
            missAnswerQuestions5 = 1;
        }

        int sum = missAnswerQuestions1 + missAnswerQuestions2 + missAnswerQuestions3 + missAnswerQuestions4 + missAnswerQuestions5;

        if (sum == 5)
            massage = nameOfUser + ", You missed to answer all questions!!";

        else if (sum >= 3)
            massage = nameOfUser + ", You missed to answer " + sum + " questions!";

        return massage;
    }

    public void numberOfCorrectAnswered() {

        correctAnswered = 0;

        if (((CheckBox) findViewById(R.id.an1_q1)).isChecked() && ((CheckBox) findViewById(R.id.an4_q1)).isChecked())
            if ((!((CheckBox) findViewById(R.id.an2_q1)).isChecked()) && (!((CheckBox) findViewById(R.id.an3_q1)).isChecked()))
                correctAnswered++;

        if (((RadioButton) findViewById(R.id.an2_radio_button_q2)).isChecked())
            correctAnswered++;

        if (((((EditText) findViewById(R.id.anField_q3)).getText()).toString()).equals("18"))
            correctAnswered++;

        if (((RadioButton) findViewById(R.id.an3_radio_button_q4)).isChecked())
            correctAnswered++;

        if (((CheckBox) findViewById(R.id.an1_q5)).isChecked() && ((CheckBox) findViewById(R.id.an3_q5)).isChecked())
            if (((CheckBox) findViewById(R.id.an5_q5)).isChecked() && ((CheckBox) findViewById(R.id.an6_q5)).isChecked())
                if ((!((CheckBox) findViewById(R.id.an2_q5)).isChecked()) && (!((CheckBox) findViewById(R.id.an4_q5)).isChecked()))
                    correctAnswered++;
    }
}