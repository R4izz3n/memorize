package de.kulturbremen.memorize.ui.edit;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.databinding.DataBindingUtil;


import java.util.List;

import de.kulturbremen.memorize.R;
import de.kulturbremen.memorize.databinding.FragmentItemEditBinding;
import de.kulturbremen.memorize.model.QuestionModel;

/**
 * {@link RecyclerView.Adapter} that can display a list of strings with QuizEntity names.
 */
public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.ViewHolder> {
    private final List<QuestionModel> questions;

    public QuestionRecyclerAdapter(List<QuestionModel> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentItemEditBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.fragment_item_edit, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FragmentItemEditBinding binding;

        public ViewHolder(FragmentItemEditBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(QuestionModel question){
            binding.setQuestion(question);
            // important to execute binding immediately, to not populate incorrect view
            binding.executePendingBindings();
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString();
        }


    }

}