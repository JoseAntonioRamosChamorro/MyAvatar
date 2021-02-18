package com.example.myavatar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogNombre extends DialogFragment
{
    Interfaz mListener;
    EditText nombre;
    DialogGenero DialogSexo;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //Construimos el dialog
        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dlgnombre, null));

        View MyView = inflater.inflate(R.layout.dlgnombre,null);
        nombre=MyView.findViewById(R.id.lblNombre);

        builder.setView(MyView)
                .setTitle(R.string.dlg_crear)
                //damos funcionalidad al OK
                .setPositiveButton(R.string.btn_aceptar, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try
                        {
                            mListener.onDataSetNombre(nombre.getText().toString());
                        }catch (Exception error){
                            Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                        mListener.onDlgGuardarListener();
                        DialogSexo = new DialogGenero();
                        DialogSexo.setCancelable(false);//Establece que si pulsamos fuera del dialogo no se quite
                        DialogSexo.show(getFragmentManager(),"Dialogo Genero");
                    }
                })
                //damos funcionalidad al boton CANCELAR
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