package com.example.bloodbank;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class History extends Fragment implements View.OnClickListener{

    RecyclerView recyclerView;
    ArrayList<Model> itemList;
    ItemAdapter itemAdapter;
    Button click;
    SwipeRefreshLayout swipeRefreshLayout;
    String Date1,Date2;

    //for current date and time
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy ");
    final String CurrentDateandTime = sdf.format(new Date());

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment_history,container,false);

        itemAdapter = new ItemAdapter(initData());
        recyclerView = view.findViewById(R.id.recycler);
//        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        swipeRefreshLayout = view.findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        click = view.findViewById(R.id.btn2);
        click.setOnClickListener(this);
        return view;
    }



    private ArrayList<Model> initData()
    {
        itemList = Preconfig.readListFromPref(getContext());
        if(itemList == null)
        {
            itemList = new ArrayList<>();
        }
//        itemList = new ArrayList<>();
        return itemList;
    }

    @Override
    public void onClick(View v) {
        itemList.add(0,new Model("You have donated blood on "+CurrentDateandTime));
        itemAdapter.notifyItemInserted(0);
        Preconfig.writeListInPref(v.getContext(),itemList);
    }
}
