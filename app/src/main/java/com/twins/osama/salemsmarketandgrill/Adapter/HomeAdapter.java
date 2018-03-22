package com.twins.osama.salemsmarketandgrill.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.twins.osama.salemsmarketandgrill.Classes.Meals;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.Interface.OnDrawerItemClickListener;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.IMG_URL;

/**
 * Created by Osama on 7/24/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private final OnDrawerItemClickListener listener;
    ArrayList<Meals> item = new ArrayList<>();
    Context context;

    public HomeAdapter(Context context, ArrayList item, OnDrawerItemClickListener listener) {
        this.context = context;
        this.item = item;
        this.listener = listener;
    }


    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_template_recycleview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, final int position) {
        holder.text_title.setText(item.get(position).getName());

//        if ((item.get(position).Description).equals("null"))
//            holder.text_main.setText(" ");
//        else holder.text_main.setText(item.get(position).Description);
        holder.txt_salry.setText( item.get(position).getPrice()+" $");
        try {

        Uri uri = Uri.parse(IMG_URL + item.get(position).getFilePath().replace("~", ""));
        holder.image_home.setImageURI(uri);
        }catch (Exception e){

        }
        holder.goToDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
        holder.add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonClick(position);
            }
        });
//            holder.goToDescription.setOnClickListener((View.OnClickListener) this);
    }


    @Override
    public int getItemCount() {
        return item.size();
    }

    public void addItem(Meals meals) {
        item.add(meals);
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
        TextView add_to_cart;
        LinearLayout goToDescription;
        TextView txt_salry;
        SimpleDraweeView image_home;
        TextView text_title;
        TextView text_main;

        // CardView cv;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            TypefaceUtil.applyFont(view.getContext(), view.findViewById(R.id.cardView_home));
            text_title = (TextView) view.findViewById(R.id.text_title);
//            text_main = (TextView) view.findViewById(R.id.text_main);
            txt_salry = (TextView) view.findViewById(R.id.txt_salry);
            image_home = (SimpleDraweeView) view.findViewById(R.id.img_hom_recview);
            goToDescription = (LinearLayout) view.findViewById(R.id.goToDescription);
            add_to_cart = (TextView) view.findViewById(R.id.add_to_cart);
        }
    }
}
