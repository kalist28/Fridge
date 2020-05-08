package com.example.fridge.main.product_selection_activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fridge.R;

public class PageFragment extends Fragment {

    private int typeId;

    PageFragment(int typeId) {
        this.typeId = typeId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.RecyclerView);
        recyclerView.setAdapter(new ProductsRVAdapter(getContext(), typeId));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
