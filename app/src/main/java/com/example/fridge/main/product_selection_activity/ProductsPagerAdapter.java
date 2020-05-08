package com.example.fridge.main.product_selection_activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fridge.entity.managers.TypesManager;

 class ProductsPagerAdapter extends FragmentPagerAdapter {

    ProductsPagerAdapter(final FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new PageFragment(position);
    }

    @Override
    public int getCount() {
        return TypesManager.get().getProductTypes().length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TypesManager.get().getProductTypes()[position];
    }
 }