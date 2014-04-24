package com.edittextvalidity.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by deezzel on 4/23/14.
 */
public class PopupFragment extends DialogFragment {

    public interface PopupDialogListener{
        public void onButtonSendClick(DialogFragment dialogFragment, CharSequence value);
    }

    EditText editText;
    Button btnSend;
    PopupDialogListener pListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstance){

        LayoutInflater inflater = getActivity().getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View layout = inflater.inflate(R.layout.popup,null);
        editText = (EditText) layout.findViewById(R.id.value);
        editText.setError("Can't be blank");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editText.getText().toString().equalsIgnoreCase("")) {
                    if (editText.getText().toString().equalsIgnoreCase("")) {
                        editText.setError("Can't be blank");
                    } else {
                        editText.setError(null);
                    }
                } else if (!editText.getText().toString().matches("[0-9]*") && !editText.getText().toString().equalsIgnoreCase("")) {
                    editText.setError("Can contain only digits");
                } else {
                    editText.setError(null);
                }


            }
        });

        btnSend = (Button) layout.findViewById(R.id.send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText)layout.findViewById(R.id.value);
                CharSequence value = et.getText().toString();
                pListener.onButtonSendClick(PopupFragment.this, value);
            }
        });
        builder.setView(layout);
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            pListener = (PopupDialogListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + " must implement PopupDialogListener");
        }
    }
}
