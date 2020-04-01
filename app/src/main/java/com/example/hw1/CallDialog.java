package com.example.hw1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hw1.contacts.ContactListContent;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallDialog extends DialogFragment {

    private OnCallDialogInteractionListener mListener;
    public ContactListContent.Contact contact;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCallDialogInteractionListener) {
            mListener = (OnCallDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCallDialogInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public CallDialog() {
    }
    public CallDialog(ContactListContent.Contact item){
        contact = item;
    }

    static CallDialog newInstance(ContactListContent.Contact contact){
        return new CallDialog(contact);
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.call_question) + " " + contact.getSurname());
        builder.setPositiveButton(getString(R.string.call_confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onCallPositivieClick(CallDialog.this);
            }
        });
        builder.setNegativeButton(getString(R.string.call_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    mListener.onCallNegativeClick(CallDialog.this);
                }
                catch (NullPointerException a){
                }
            }
        });
        return builder.create();
    }

    public interface OnCallDialogInteractionListener {
        void onCallPositivieClick(DialogFragment dialog);
        void onCallNegativeClick(DialogFragment dialog);
    }
}