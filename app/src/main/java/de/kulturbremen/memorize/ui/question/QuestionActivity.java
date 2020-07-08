package de.kulturbremen.memorize.ui.question;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import de.kulturbremen.memorize.R;
import de.kulturbremen.memorize.quizmanager.QuizManager;

public class QuestionActivity extends AppCompatActivity {
    public static final String EXTRA_USER_ANSWER = "de.kulturbremen.memorize.USER_ANSWER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // set the text of the question
        QuizManager qm = QuizManager.getInstance();
        String question = qm.getQuestionContainer().getQuestion();
        TextView textView = findViewById(R.id.lastQuestionMessage);
        textView.setText(question);

        // show keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    /** called when check answer button is tapped */
    public void checkAnswer(View view) {
        EditText editText = findViewById(R.id.userAnswer);
        String userAnswer = editText.getText().toString();
        Intent intent;
        QuizManager qm = QuizManager.getInstance();

        if (qm.checkAnswer(userAnswer)){
            intent = new Intent(this, RightAnswerActivity.class);
        } else {
            intent = new Intent(this, WrongAnswerActivity.class);
        }
        startActivity(intent);
    }


}
