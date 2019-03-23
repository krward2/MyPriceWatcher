package edu.utep.cs.cs4330.mypricewatcher;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class EditNameDialog extends AppCompatDialogFragment {
    private EditText editName;
    private EditDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_name_dialog, null);

        String viewToEditName = getArguments().getString("viewToEditName");
        int viewToEditID = getArguments().getInt("viewToEditID");

        builder.setView(view)
                .setTitle("Edit " + viewToEditName)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = editName.getText().toString();
                        listener.applyText(viewToEditID, newName);
                    }
                });
        editName = view.findViewById(R.id.editTextName);
        editName.setHint("Enter a new " + viewToEditName);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (EditDialogListener)context;
    }

    public interface EditDialogListener{
        void applyText(int viewToEditID,String newName);
    }
}
