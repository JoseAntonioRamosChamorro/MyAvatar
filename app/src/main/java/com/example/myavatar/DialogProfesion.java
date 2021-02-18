package com.example.myavatar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogProfesion extends DialogFragment
{
    Interfaz mListener;
    Spinner spinnerProfecion;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        //Construimos el dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dlgprofesion, null));

        View MyView = inflater.inflate(R.layout.dlgprofesion,null);

        spinnerProfecion = MyView.findViewById(R.id.spinnerProfecion);

        String[] types = getResources().getStringArray(R.array.despegableProfesion);
        // Creamos el array para el Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MyView.getContext(),
                R.array.despegableProfesion, android.R.layout.simple_spinner_item);
        // le damos un diseño al spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // aplicamos el diseño al spinner
        spinnerProfecion.setAdapter(adapter);

        builder.setView(MyView)
                .setTitle(R.string.dlg_crear)

                //damos funcionalidad al OK del spinner
                .setPositiveButton(R.string.btn_aceptar, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try
                        {
                            mListener.onDataSetProfesion(spinnerProfecion.getSelectedItem().toString());
                        }
                        catch (Exception error){
                            Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                        //ultimo dialog
                    }
                })
                //damos funcionalidad al boton CANCELAR del spinner
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
            mListener=(Interfaz) context;
        }
        catch (ClassCastException error)
        {
            throw new ClassCastException(context.toString()+" debe implementar InterfazNombre");
        }
    }
}
