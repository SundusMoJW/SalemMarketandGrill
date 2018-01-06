package com.twins.osama.salemsmarketandgrill.Adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.twins.osama.salemsmarketandgrill.Classes.Events;
import com.twins.osama.salemsmarketandgrill.Fragment.EventsFragment;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.IMG_URL;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.getDate;

/**
 * Created by Osama on 12/12/2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    //    private final OnDrawerItemClickListener listener;
    ArrayList<Events> item = new ArrayList<>();
    Context context;
    private FragmentTransaction mFragmentTransaction;

    public EventsAdapter(Context context, ArrayList item ){
        this.context = context;
        this.item = item;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_temp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.text_title.setText(item.get(position).getTitle());

        /*********************************/

        holder.to.setText(getDate(item.get(position).getToDate()));
        holder.from.setText(getDate(item.get(position).getFromDate()));
        /********************************/

        Uri uri = Uri.parse(IMG_URL + item.get(position).getFilePath().replace("~", ""));
        holder.image.setImageURI(uri);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventsFragment fragment = new EventsFragment();
                android.support.v4.app.FragmentManager mFragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("position", item.get(position).getId());
                fragment.setArguments(bundle);
                mFragmentTransaction.replace(R.id.frame_layout, fragment);
                mFragmentTransaction.commit();
            }
        });
    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    public void addItem(Events events) {
        item.add(events);
        notifyItemInserted(item.size());
    }

    public void removeItem(int position) {
        item.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, item.size());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView image;
        TextView text_title;
        TextView from;
        TextView to;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            TypefaceUtil.applyFont(view.getContext(), view.findViewById(R.id.cardView));
            text_title = (TextView) view.findViewById(R.id.text_title);
            from = (TextView) view.findViewById(R.id.from);
            to = (TextView) view.findViewById(R.id.to);
            image= (SimpleDraweeView) view.findViewById(R.id.img);
            cardView= view.findViewById(R.id.cardView);
        }
    }
}