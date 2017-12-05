package com.twins.osama.salemsmarketandgrill.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Activity.ImgZoom;
import com.twins.osama.salemsmarketandgrill.Fragment.RestaurantFragment;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;
import com.twins.osama.salemsmarketandgrill.Template.RVGallaryTemplate;

import java.util.ArrayList;

/**
 * Created by Osama on 7/26/2017.
 */

public class GallaryAdapter extends RecyclerView.Adapter<GallaryAdapter.ViewHolder> {

    ArrayList<RVGallaryTemplate> item = new ArrayList<>();
    AppCompatActivity context;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    public GallaryAdapter(AppCompatActivity context, ArrayList item) {
        this.context = context;
        this.item = item;
    }


    @Override
    public GallaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_gallary, parent, false);
        TypefaceUtil.applyFont(context, view.findViewById(R.id.cardView));
        return new ViewHolder(view);
    }

    // viewHolder.tv_country.setText(item.get(i));
    @Override
    public void onBindViewHolder(GallaryAdapter.ViewHolder holder, int position) {
        holder.text_info.setText(item.get(position).getInfo());
        holder.text_main.setText(item.get(position).getMain());
        holder.imags.setImageResource(item.get(position).getImages());
////            Uri uri = Uri.parse(IMG_URL + item.get(position).getImages().replace("~", ""));
//            Uri uri = Uri.parse(item.get(position).getImages());
//            holder.image_home.setImageURI(uri);
//
        holder.expand.setOnClickListener(new View.OnClickListener() {
            public Bundle bundle;

            @Override
            public void onClick(View v) {
//
                Intent intent = new Intent(context, ImgZoom.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 bundle=new Bundle();
               // bundle.putParcelable("imagebitmap", getItemViewType(item.get(position).images));

                context.startActivity(intent);
            }
        });
        holder.text_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantFragment fragment = new RestaurantFragment();
                FragmentManager mFragmentManager = context.getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.frame_layout, fragment);
                mFragmentTransaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void addItem(RVGallaryTemplate rvGallaryTemplates) {
        item.add(rvGallaryTemplates);
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
        ImageView imags;
        TextView text_info;
        TextView text_main;
        ImageView expand;

        // CardView cv;
        public ViewHolder(View view) {
            super(view);
            //cv = (CardView) itemView.findViewById(R.id.cardView);

            text_info = (TextView) view.findViewById(R.id.information);
            text_main = (TextView) view.findViewById(R.id.main);
            imags = (ImageView) view.findViewById(R.id.rv_img_gallary);
            expand = (ImageView) view.findViewById(R.id.expand_photo);
        }

    }
}