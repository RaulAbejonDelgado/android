package com.example.fragments.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment {

    private EditText text;
    private Button button;
    private DataListener callBack;



    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callBack =(DataListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString()+ " should implement Datalistener") ;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_data, container,false);

        //logica para capturar los elementos de la ui

        text = (EditText) view.findViewById(R.id.editTextData);
        button = (Button) view.findViewById(R.id.editTextButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendText(text.getText().toString());
            }
        });

        return view;
    }

    private void sendText(String texto){
        callBack.sendData(texto);
    }

    public interface DataListener {
        void sendData(String data);
    }

}
