package com.quwaysim.gads2020project.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.quwaysim.gads2020project.R;

import java.util.Objects;

public class SubmissionConfirmationDialog extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = requireActivity().getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.dialog_confirm_submission, null));
            return builder.create();
        }

    @Override
    public void onStart() {
        super.onStart();
        Button pos_btn =((AlertDialog) Objects.requireNonNull(getDialog())).getButton(DialogInterface.BUTTON_POSITIVE);
        pos_btn.setBackground(getResources().getDrawable(R.drawable.confirmation_btn_bg));

    }
}
