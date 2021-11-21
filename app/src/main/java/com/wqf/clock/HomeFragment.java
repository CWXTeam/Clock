package com.wqf.clock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    TextView dianaSaid;
    Button setText;
    public static HomeFragment newInstance(String label) {
        Bundle args = new Bundle();
        args.putString("label", label);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        dianaSaid=getView().findViewById(R.id.diana_said);
        setText=getView().findViewById(R.id.setText);
        setText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dianaSaid.setText("好吧");
            }
        });
    }
}
