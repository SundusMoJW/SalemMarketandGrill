package com.twins.osama.salemsmarketandgrill.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Helpar.DrawerItem;
import com.twins.osama.salemsmarketandgrill.Interface.OnDrawerItemClickListener;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.EMAIL_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.USER_NAME_SHARED_PREF;


/**
 * Created by Osama on 7/23/2017.
 */


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final SharedPrefUtil sharedPref;
    ArrayList<DrawerItem> items;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private String name;
    private int profile;
    private String email;
    private Activity context;
    OnDrawerItemClickListener listener;
    public static int PROFILE = R.drawable.logo;
    private String NAME;
    private String EMAILProfile;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;

        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;

        public LinearLayout drawer_Item;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.text_home);
                drawer_Item = (LinearLayout) itemView.findViewById(R.id.drower_Item);
                TypefaceUtil.applyFont(itemView.getContext(), itemView.findViewById(R.id.drower_Item));
                imageView = (ImageView) itemView.findViewById(R.id.image_home);
                Holderid = 1;
            } else {

                Name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                profile = (ImageView) itemView.findViewById(R.id.circleView);
                TypefaceUtil.applyFont(itemView.getContext(), itemView.findViewById(R.id.header_id));
                Holderid = 0;
            }
        }

        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;
            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }
    }

    public ArrayList<DrawerItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<DrawerItem> items) {
        this.items = items;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyAdapter(Activity context, ArrayList<DrawerItem> items, OnDrawerItemClickListener listener) {
        sharedPref = new SharedPrefUtil(context);
        this.context = context;
        this.items = items;
        this.listener = listener;


    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.toolbar, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType);

            return vhItem;

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
            ViewHolder vhHeader = new ViewHolder(v, viewType);
            return vhHeader;


        }
        return null;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {

        if (holder.Holderid == 0) {
            NAME = sharedPref.getString(USER_NAME_SHARED_PREF);
            if(NAME.equals("null")){
                NAME="Your Name";
            }
            EMAILProfile = sharedPref.getString(EMAIL_SHARED_PREF);
            if(EMAILProfile.equals("null")){
                EMAILProfile="Your Email";
            }
//            name = sharedPref.getString(USER_NAME_SHARED_PREF);
//            email = sharedPref.getString(EMAIL_SHARED_PREF);
            holder.profile.setImageResource(PROFILE);
            holder.Name.setText(NAME);
            holder.email.setText(EMAILProfile);
            holder.profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPhotoClick(v);
                }
            });
        } else {

            DrawerItem item = items.get(position - 1);//0 is header
            holder.textView.setText(item.getTitle());
            if (item.isSelected()) {
                holder.textView.setTextColor(Color.parseColor("#ffffff"));
                holder.drawer_Item.setBackgroundColor(Color.parseColor("#D35650"));
                holder.imageView.setImageResource(item.getImage_on());

            } else {
                holder.textView.setTextColor(Color.parseColor("#ee333333"));
                holder.drawer_Item.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.imageView.setImageResource(item.getImage());
            }
            holder.drawer_Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position - 1);

                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return items.size() + 1;
    }


    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_ITEM;
    }
}
