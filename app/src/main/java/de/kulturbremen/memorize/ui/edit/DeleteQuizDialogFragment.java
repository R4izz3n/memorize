package de.kulturbremen.memorize.ui.edit;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import de.kulturbremen.memorize.R;

public class DeleteQuizDialogFragment extends DialogFragment {

    private NoticeDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_delete_quiz)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogPositiveClick();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick();
                    }
                });
        return builder.create();
    }
    public interface NoticeDialogListener {
        void onDialogPositiveClick();
        void onDialogNegativeClick();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (NoticeDialogListener) context;
    }


}
