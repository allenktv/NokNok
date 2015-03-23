package com.kbear.noknok.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kbear.noknok.R;

/**
 * Created by allen on 3/22/15.
 */
public class DrawerMenuAdapter extends RecyclerView.Adapter<DrawerMenuAdapter.DrawerMenuViewHolder> {

    private String[] items;

    public DrawerMenuAdapter(String[] items) {
        this.items = items;
    }

    @Override
    public DrawerMenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drawer_list_item, viewGroup, false);
        return new DrawerMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawerMenuViewHolder drawerMenuViewHolder, int i) {
        drawerMenuViewHolder.bindItem(items[i]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    protected static class DrawerMenuViewHolder extends RecyclerView.ViewHolder {
        private TextView menuItem;

        public DrawerMenuViewHolder(View itemView) {
            super(itemView);
            menuItem = (TextView) itemView.findViewById(R.id.drawer_item_name);
        }

        public void bindItem(String item) {
            menuItem.setText(item);
        }
    }
}
