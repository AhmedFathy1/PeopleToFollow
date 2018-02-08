package com.example.android.af.follower;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * RecyclerView Adapter
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.MyViewHolder> {


    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView person;
        public int position;
        private final String KEY_SELECTED = "com.example.android.af.follower.SELECTED";

        public MyViewHolder(View view) {
            super(view);
            person = (TextView) view.findViewById(R.id.person_name);
            context = itemView.getContext();
            person.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intent;
                    context = view.getContext();
                    intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra(KEY_SELECTED, position);
                    context.startActivity(intent);
                }
            });
            person.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(position, v.getId(), 0, "Edit");//groupId, itemId, order, title
            menu.add(position, v.getId(), 1, "Delete");
        }


    }


    public PeopleAdapter() {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        PersonData people = PeopleList.getInstance().getList().get(position);
        holder.person.setText(people.getUserName());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return PeopleList.getInstance().getList().size();
    }


}
