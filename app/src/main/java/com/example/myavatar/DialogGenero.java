package com.example.myavatar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogGenero extends DialogFragment
{
    Interfaz mListener;
    RadioButton rdBHombre;
    RadioButton rdBMujer;
    DialogEspecie DialogEspecie;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        //Construimos el dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dlgsexo, null));

        View MyView = inflater.inflate(R.layout.dlgsexo, null);
        rdBHombre = MyView.findViewById(R.id.rdHombre);
        rdBMujer = MyView.findViewById(R.id.rdMujer);

        builder.setView(MyView)
                .setTitle(R.string.dlg_crear)
                //damos funcionalidad al OK del RadiGroup
                .setPositiveButton(R.string.btn_aceptar, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try
                        {
                            if (rdBHombre.isChecked())
                            {
                                mListener.onDataSetSexo(rdBHombre.getText().toString());
                            }
                            else
                            {
                                mListener.onDataSetSexo(rdBMujer.getText().toString());
                            }
                        }
                        catch (Exception error)
                        {
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                        mListener.onDlgGuardarListener();
                        DialogEspecie = new DialogEspecie();

                        // Cerramos el Dialog y volvemos a la pantalla principal
                        DialogEspecie.setCancelable(false);
                        DialogEspecie.show(getFragmentManager(), "Dialogo especie");
                    }
                })
                //damos funcionalidad al boton CANCELAR del RadiGroup
                .setNegativeButton(R.string.btn_cancelar, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // Cerramos el Dialog y volvemos a la pantalla principal
                        dialog.dismiss();
                        mListener.onDlgCancelarListener();
                    }
                });
        // Crearmos el Objeto y con return lo devolvemos
        return builder.create();
    }

    //proporciona la interfaz necesaria para las verificaciones
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            mListener = (Interfaz) context;
        } catch (ClassCastException error)
        {
            throw new ClassCastException(context.toString() + " debe implementar InterfazNombre");
        }
    }
}