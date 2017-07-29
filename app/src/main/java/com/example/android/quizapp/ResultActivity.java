package com.example.android.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.android.quizapp.NAME";
    public static final String EXTRA_MESSAGE = "com.example.android.quizapp.MESSAGE";
    public static final String EXTRA_INTEGER = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get the Intent that started this activity and extract the name & message & score
        Intent intent = getIntent();
        final String nameOfUser = intent.getStringExtra(MainActivity.EXTRA_NAME);
        final String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        final int score = intent.getIntExtra(MainActivity.EXTRA_INTEGER, Integer.parseInt(MainActivity.EXTRA_INTEGER));

        // Capture the layout's TextView and set the message as its text
        TextView textView = (TextView) findViewById(R.id.answer_result);
        textView.setText(message);

        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();

        ImageView pic = (ImageView) findViewById(R.id.imageView);

        if (score == 0)
            pic.setImageResource(R.drawable.pic_0);
        else if (score < 3)
            pic.setImageResource(R.drawable.pic_1_and_2);
        else if (score == 3)
            pic.setImageResource(R.drawable.pic_3);
        else if (score == 4)
            pic.setImageResource(R.drawable.pic_4);
        else
            pic.setImageResource(R.drawable.pic_5);

        Button backToQuiz = (Button) findViewById(R.id.back_to_quiz);
        backToQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra(EXTRA_NAME, nameOfUser);
                intent.putExtra(EXTRA_MESSAGE,message);
                intent.putExtra(EXTRA_INTEGER, score);
                startActivity(intent);
            }
        });

    }
}
