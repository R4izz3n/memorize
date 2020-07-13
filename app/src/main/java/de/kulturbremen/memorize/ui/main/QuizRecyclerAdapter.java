package de.kulturbremen.memorize.ui.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.kulturbremen.memorize.R;
import de.kulturbremen.memorize.model.QuizEntity;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a list of strings with QuizEntity names.
 */
public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.ViewHolder> {

    private static final String TAG = "QuizRecyclerAdapter";
    private final List<QuizEntity> mQuizzes;
    private OnQuizListener mOnQuizListener;

    public QuizRecyclerAdapter(List<QuizEntity> items, OnQuizListener onQuizListener) {
        this.mQuizzes = items;
        Log.d(TAG, "QuizRecyclerAdapter: mQuizzes: " + items);
        this.mOnQuizListener = onQuizListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view, mOnQuizListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mQuizzes.get(position).getName();
        holder.mContentView.setText(mQuizzes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mQuizzes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "ViewHolder";
        public final View mView;
        public final TextView mContentView;
        public String mItem;
        OnQuizListener onQuizListener;

        public ViewHolder(View view, OnQuizListener onQuizListener) {
            super(view);
            mView = view;
            Log.d(TAG, "ViewHolder: constructor called with view: " + view);
            mContentView = view.findViewById(R.id.content);
            this.onQuizListener = onQuizListener;

            view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            QuizEntity quiz = mQuizzes.get(getAdapterPosition());
            onQuizListener.onQuizClick(quiz);
        }
    }

    public interface OnQuizListener {
        void onQuizClick(QuizEntity quiz);
    }
}