package de.kulturbremen.memorize.ui.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.kulturbremen.memorize.R;
import de.kulturbremen.memorize.model.QuizEntity;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a list of strings with QuizEntity names.
 */
public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.ViewHolder> {

    private final List<QuizEntity> quizzes;
    private OnQuizListener onQuizListener;
    private OnEditQuizListener onEditQuizListener;

    public QuizRecyclerAdapter(List<QuizEntity> items,
                               OnQuizListener onQuizListener,
                               OnEditQuizListener onEditQuizListener) {
        this.quizzes = items;
        this.onQuizListener = onQuizListener;
        this.onEditQuizListener = onEditQuizListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_display, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.getContentView().setText(quizzes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView contentView;

        public ViewHolder(View view) {
            super(view);
            this.contentView = view.findViewById(R.id.content);

            contentView.setOnClickListener(this);
            ImageView editButton = view.findViewById(R.id.editButton);
            editButton.setOnClickListener(this);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            QuizEntity quiz = quizzes.get(getAdapterPosition());
            switch (view.getId()) {
                case R.id.content: {
                    onQuizListener.onQuizClick(quiz);
                    break;
                }
                case R.id.editButton: {
                    onEditQuizListener.onEditQuizClick(quiz);
                    break;
                }
            }
        }

        public TextView getContentView() {
            return contentView;
        }
    }

    public interface OnQuizListener {
        void onQuizClick(QuizEntity quiz);
    }
    public interface OnEditQuizListener {
        void onEditQuizClick(QuizEntity quiz);
    }
}