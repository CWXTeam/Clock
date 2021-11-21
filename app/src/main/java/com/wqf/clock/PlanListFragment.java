package com.wqf.clock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PlanListFragment extends Fragment {


    private String [] data = {"Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry",
            "Mango","Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple","Strawberry","Cherry",
            "Mango"};

    public static PlanListFragment newInstance(String label) {
        Bundle args = new Bundle();
        args.putString("label", label);
        PlanListFragment fragment = new PlanListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan_list, container, false);
    }

    @Override
    public void onStart() {

        super.onStart();
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String> (
                getContext(), android.R.layout.simple_list_item_1,data);
        ListView listView = getView().findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);
    }
}
