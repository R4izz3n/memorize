package de.kulturbremen.memorize.activities.question;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import de.kulturbremen.memorize.R;
import de.kulturbremen.memorize.quizmanager.QuizManager;

public class RightAnswer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_answer);

        QuizManager qm = QuizManager.getInstance();
        qm.removeLastQuestion();

        if (qm.hasQuestions()) {
            Intent intent = new Intent(this, Question.class);
            waitAndGoToNextIntent(intent);
        } else {
            Intent intent = new Intent(this, FinishedLastQuestion.class);
            waitAndGoToNextIntent(intent);
        }
    }

    private void waitAndGoToNextIntent(final Intent intent) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}