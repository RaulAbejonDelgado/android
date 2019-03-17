package com.example.fragments.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragments extends Fragment {

    private TextView details ;


    public DetailsFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_fragments, container, false);

        details = (TextView) view.findViewById(R.id.textViewDetails);



        return view ;
    }

    public void renderText(String text){
        details.setText(text);

    }

}
