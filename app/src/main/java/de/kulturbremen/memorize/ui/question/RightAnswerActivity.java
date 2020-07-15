package de.kulturbremen.memorize.ui.question;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import de.kulturbremen.memorize.R;
import de.kulturbremen.memorize.manager.QuestionManager;

public class RightAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_answer);

        QuestionManager qm = QuestionManager.getInstance();
        qm.removeLastQuestion();

        if (qm.hasQuestions()) {
            Intent intent = new Intent(this, QuestionActivity.class);
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