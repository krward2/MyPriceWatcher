package edu.utep.cs.cs4330.mypricewatcher;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class EditUrlDialog extends AppCompatDialogFragment {
    private EditText editUrl;
    private EditDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_url_dialog, null);

        builder.setView(view)
                .setTitle("Edit Url")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        editUrl = view.findViewById(R.id.editTextUrl);

        return builder.create();
    }

    public interface EditDialogListener{
        void applyUrl(String url);
    }
}
