package com.example.guj_to_eng;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class future extends Fragment {
    public future() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.future, container, false);

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        View view =(View) getActivity().findViewById(R.id.future_s);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),simple_future.class);
                startActivity(i);
            }
        });
        View view1 =(View) getActivity().findViewById(R.id.future_c);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),conti_future.class);
                startActivity(i);
            }
        });
        View view2=(View) getActivity().findViewById(R.id.future_p);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),per_future.class);
                startActivity(i);
            }
        });
        View view3=(View) getActivity().findViewById(R.id.future_p_c);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),per_conti_future.class);
                startActivity(i);
            }
        });
    }



}

