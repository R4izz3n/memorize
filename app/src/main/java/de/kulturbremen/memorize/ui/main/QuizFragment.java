package de.kulturbremen.memorize.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import de.kulturbremen.memorize.R;
import de.kulturbremen.memorize.model.QuizEntity;
import de.kulturbremen.memorize.manager.QuestionManager;
import de.kulturbremen.memorize.manager.QuizManager;
import de.kulturbremen.memorize.ui.question.QuestionActivity;

/**
 * A fragment representing a list of QuizEntity names.
 */
public class QuizFragment extends Fragment
        implements QuizRecyclerAdapter.OnQuizListener, QuizRecyclerAdapter.OnEditQuizListener {

    private static final String TAG = "QuizFragment";
    private Context context;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuizFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            context = view.getContext();
            QuizManager quizManager = new QuizManager(context);
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new QuizRecyclerAdapter(quizManager.getQuizzes(),
                    this, this));
        }
        return view;
    }

    @Override
    public void onQuizClick(QuizEntity quiz) {
        Intent intent = new Intent(getActivity(), QuestionActivity.class);
        QuestionManager qm = QuestionManager.getInstance();
        qm.updateQuestions(quiz, getContext());
        if (qm.hasQuestions()) {
            startActivity(intent);
        } else {
            int duration = Toast.LENGTH_SHORT;
            String message = "No questions available for this quiz";
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
        }
    }

    @Override
    public void onEditQuizClick(QuizEntity quiz) {
        Log.d(TAG, "onEditQuizClick: clicked! (" + quiz + ")");
    }
}