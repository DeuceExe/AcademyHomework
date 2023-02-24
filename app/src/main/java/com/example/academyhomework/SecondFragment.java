package com.example.academyhomework;

import static com.example.academyhomework.MainActivity.AGE;
import static com.example.academyhomework.MainActivity.IS_MAN;
import static com.example.academyhomework.MainActivity.NAME;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    public static SecondFragment newInstance(String name, Integer age, Boolean isMan) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putInt(AGE, age);
        args.putBoolean(IS_MAN, isMan);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvName = view.findViewById(R.id.tvName2);
        TextView tvAge = view.findViewById(R.id.tvAge2);
        TextView tvIsMan = view.findViewById(R.id.tvIsMan2);

        if (getArguments() != null) {
            tvName.setText(getArguments().getString(NAME));
            tvAge.setText(String.valueOf(getArguments().getInt(AGE)));
            if (getArguments().getBoolean(IS_MAN)) tvIsMan.setText("Man");
            else tvIsMan.setText("Woman");
        }
    }
}