package de.kulturbremen.memorize.activity.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.kulturbremen.memorize.R;

import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a list of strings with Quiz names.
 */
public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.ViewHolder> {

    private final ArrayList<String> mQuizzes;
    private OnQuizListener mOnQuizListener;

    public QuizRecyclerAdapter(ArrayList<String> items, OnQuizListener onQuizListener) {
        this.mQuizzes = items;
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
        holder.mItem = mQuizzes.get(position);
        holder.mContentView.setText(mQuizzes.get(position));
    }

    @Override
    public int getItemCount() {
        return mQuizzes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView mContentView;
        public String mItem;
        OnQuizListener onQuizListener;

        public ViewHolder(View view, OnQuizListener onQuizListener) {
            super(view);
            mView = view;
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
            onQuizListener.onQuizClick(getAdapterPosition());
        }
    }

    public interface OnQuizListener {
        void onQuizClick(int position);
    }
}