package es.unex.saee.sonicmusiccollection;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class About extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.about);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(R.string.about_option);
        alertDialogBuilder.setMessage(R.string.aboutMessage);
        return alertDialogBuilder.create();
    }
}
