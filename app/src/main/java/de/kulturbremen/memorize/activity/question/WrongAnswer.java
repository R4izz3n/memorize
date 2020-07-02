package de.kulturbremen.memorize.activity.question;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import de.kulturbremen.memorize.R;
import de.kulturbremen.memorize.quizmanager.QuizManager;

public class WrongAnswer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);

        // set the text of the question
        String answer = QuizManager.getQuestion().getAnswer();
        TextView textView = findViewById(R.id.correctAnswer);
        textView.setText(answer);
        QuizManager.reshuffleLastQuestion();
        waitAndGoToNextQuestion();
    }

    private void waitAndGoToNextQuestion() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WrongAnswer.this, Question.class);
                startActivity(intent);
                finish();
            }
        }, 3500);
    }

}