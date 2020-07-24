package de.kulturbremen.memorize.ui.edit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import de.kulturbremen.memorize.R;
import de.kulturbremen.memorize.manager.QuizManager;
import de.kulturbremen.memorize.model.QuestionModel;
import de.kulturbremen.memorize.ui.main.MainActivity;
import de.kulturbremen.memorize.ui.Util;


/**
 * Activity used for creating a new quiz with questions or editing an existing one
 */
public class EditQuiz extends AppCompatActivity implements View.OnClickListener{
    private List<QuestionModel> questionList;
    private QuestionRecyclerAdapter questionAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);

        questionList = new ArrayList<>();
        questionList.add(new QuestionModel());
        recyclerView = findViewById(R.id.questionList);

        setAdapter();
        setAdapterDataObserver();
        setListener();

    }

    private void setAdapter() {
        Context context = getApplicationContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        questionAdapter = new QuestionRecyclerAdapter(questionList);
        recyclerView.setAdapter(questionAdapter);
    }

    /**
     * scroll to the new question item when item is added
     */
    private void setAdapterDataObserver() {
        questionAdapter.registerAdapterDataObserver(
                new RecyclerView.AdapterDataObserver(){

                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount){
                        super.onItemRangeInserted(positionStart, itemCount);
                        int position = questionList.size() - 1;
                        recyclerView.scrollToPosition(position);
                    }
                });
    }

    private void setListener(){
        findViewById(R.id.addQuestionButton).setOnClickListener(this);
        findViewById(R.id.submitQuizButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addQuestionButton:{
                onAddQuestion();
                break;
            }

            case R.id.submitQuizButton:{
                onSubmitQuiz(view);
                break;

            }
        }
    }

    private void onAddQuestion() {
        questionList.add(new QuestionModel());
        questionAdapter.notifyItemInserted(questionList.size() - 1);
    }

    private void onSubmitQuiz(View view) {
        Context context = view.getContext();
        EditText quizNameEditText = findViewById(R.id.quizName);
        String quizName = quizNameEditText.getText().toString().trim();
        if (quizName.equals("")) {
            Util.showToast(context, "Please add a quiz name");
            return;
        }
        QuizManager quizManager = new QuizManager(context);
        quizManager.addQuiz(quizName, questionList);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
