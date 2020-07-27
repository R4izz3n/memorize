package de.kulturbremen.memorize.ui.question;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import de.kulturbremen.memorize.R;
import de.kulturbremen.memorize.manager.QuestionManager;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // set the text of the question
        QuestionManager qm = QuestionManager.getInstance();
        String question = qm.getQuestionEntity().getQuestion();
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
        QuestionManager qm = QuestionManager.getInstance();

        if (qm.checkAnswer(userAnswer)){
            intent = new Intent(this, RightAnswerActivity.class);
        } else {
            intent = new Intent(this, WrongAnswerActivity.class);
        }
        startActivity(intent);
    }


}
