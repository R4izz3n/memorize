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
import de.kulturbremen.memorize.model.QuestionEntity;
import de.kulturbremen.memorize.model.QuizEntity;
import de.kulturbremen.memorize.ui.main.MainActivity;
import de.kulturbremen.memorize.ui.Util;
import de.kulturbremen.memorize.ui.main.QuizFragment;


/**
 * Activity used for creating a new quiz with questions or editing an existing one
 */
public class EditQuizActivity extends AppCompatActivity
        implements View.OnClickListener, QuestionRecyclerAdapter.OnDeleteQuestionListener {
    private List<QuestionEntity> questionList;
    private QuizEntity quizEntity;
    private QuizManager quizManager;
    private QuestionRecyclerAdapter questionAdapter;
    private EditText quizNameEditText;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);

        quizManager = new QuizManager(getApplicationContext());
        quizNameEditText = findViewById(R.id.quizName);
        recyclerView = findViewById(R.id.questionList);

        setQuestionListAndQuizEntity();
        setAdapter();
        setAdapterDataObserver();
        setListener();

    }

    private void setQuestionListAndQuizEntity() {
        Intent intent = getIntent();
        QuizEntity quiz = (QuizEntity) intent.getSerializableExtra(QuizFragment.EXTRA_QUESTIONS);
        if (quiz != null){
            quizEntity = quiz;
            quizNameEditText.setText(quiz.getName());
            questionList = quizManager.getQuestions(quiz);
        } else {
            quizEntity = new QuizEntity("");
            questionList = new ArrayList<>();
            questionList.add(new QuestionEntity());
        }
    }

    private void setAdapter() {
        Context context = getApplicationContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        questionAdapter = new QuestionRecyclerAdapter(questionList, quizEntity,
                this);
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
        findViewById(R.id.deleteQuizButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addQuestionButton:{
                onAddQuestionClick();
                break;
            }
            case R.id.submitQuizButton:{
                onSubmitQuizClick(view);
                break;
            }
            case R.id.deleteQuizButton:{
                onDeleteQuizClick();
                break;
            }
        }
    }

    private void onAddQuestionClick() {
        questionList.add(new QuestionEntity());
        questionAdapter.notifyItemInserted(questionList.size() - 1);
    }

    private void onSubmitQuizClick(View view) {
        Context context = view.getContext();
        String quizName = quizNameEditText.getText().toString().trim();
        if (quizName.equals("")) {
            Util.showToast(context, "Please add a quiz name");
            return;
        }
        quizEntity.setName(quizName);

        quizManager.editQuiz(quizEntity, questionList);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void onDeleteQuizClick() {
        quizManager.deleteQuiz(quizEntity);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDeleteQuestionClick(int position) {
        questionList.remove(position);
        questionAdapter.notifyItemRemoved(position);
    }
}
