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
import com.twins.osama.salemsmarketandgrill.Helpar.OnDrawerItemClickListener;
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
    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

//    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
//    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java

    private String name;        //String Resource for header View Name
    private int profile;        //int Resource for header view profile picture
    private String email;       //
    private Activity context;
    OnDrawerItemClickListener listener;
    public static int PROFILE = R.drawable.blank_profile_picture;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;

        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;

        public LinearLayout drawer_Item;

        public ViewHolder(View itemView, int ViewType) {
            // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);

            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.text_home); // Creating TextView object with the id of textView from item_row.xml
                drawer_Item = (LinearLayout) itemView.findViewById(R.id.drower_Item);
                TypefaceUtil.applyFont(itemView.getContext(),itemView.findViewById(R.id.drower_Item));
                imageView = (ImageView) itemView.findViewById(R.id.image_home);// Creating ImageView object with the id of ImageView from item_row.xml
                Holderid = 1; // setting holder id as 1 as the object being populated are of type item row
            } else {

                Name = (TextView) itemView.findViewById(R.id.name);         // Creating Text View object from header.xml for name
                email = (TextView) itemView.findViewById(R.id.email);       // Creating Text View object from header.xml for email
                profile = (ImageView) itemView.findViewById(R.id.circleView);// Creating Image view object from header.xml for profile pic
                TypefaceUtil.applyFont(itemView.getContext(), itemView.findViewById(R.id.header_id));

                Holderid = 0;                                                // Setting holder id = 0 as the object being populated are of type header view
            }
        }


        // This method returns the number of items present in the list


        // Witht the following method we check what type of view is being passed

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

    public MyAdapter(Activity context, ArrayList<DrawerItem> items/*, String Name, String Email, int Profile*/, OnDrawerItemClickListener listener) { // MyAdapter Constructor with titles and icons parameter
        sharedPref = new SharedPrefUtil(context);
        // titles, icons, name, email, profile pic are passed from the main activity as we
//        mNavTitles = Titles;                //have seen earlier
//        mIcons = Icons;
        this.context = context;
        this.items = items;
//        name = Name;

//        email = Email;
//        profile = Profile;                     //here we assign those passed values to the values we declared here
        this.listener = listener;                     //here we assign those passed values to the values we declared here
        //in adapter


    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.toolbar, parent, false); //Inflating the layout

            ViewHolder vhItem = new ViewHolder(v, viewType); //Creating ViewHolder and passing the object of type view

            return vhItem; // Returning the created object

            //inflate your layout and pass it to view holder

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false); //Inflating the layout

            ViewHolder vhHeader = new ViewHolder(v, viewType); //Creating ViewHolder and passing the object of type view

            return vhHeader; //returning the object created


        }
        return null;
    }
//Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {

        if (holder.Holderid == 0) {
            name = sharedPref.getString(USER_NAME_SHARED_PREF);
            email =sharedPref.getString(EMAIL_SHARED_PREF);
            // holder.profile.setImageDrawable(profile);
            holder.profile.setImageResource(PROFILE);
            holder.Name.setText(name);
            holder.email.setText(email);
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
                listener.onClick(position-1);

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
