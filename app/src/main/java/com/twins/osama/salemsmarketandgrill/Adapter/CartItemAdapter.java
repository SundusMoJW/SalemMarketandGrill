package com.twins.osama.salemsmarketandgrill.Adapter;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.twins.osama.salemsmarketandgrill.Classes.CartItem;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.IMG_URL;

/**
 * Created by Osama on 12/3/2017.
 */

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    private ArrayList<CartItem> item = new ArrayList<>();
    private AppCompatActivity context;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private int count = 1;
    double price = 1;

    public CartItemAdapter(AppCompatActivity context, ArrayList item) {
        this.context = context;
        this.item = item;
    }


    @Override
    public CartItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        TypefaceUtil.applyFont(context, view.findViewById(R.id.cardView));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartItemAdapter.ViewHolder holder, int position) {
        price = item.get(position).getPrice();
        count = item.get(position).getCount();
        holder.componentSalry.setText(price + "");
        holder.name.setText(item.get(position).getName());
        holder.result.setText(count + "");
        holder.mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                if (count <= 0) {
                    count = 1;
                    holder.result.setText(count + "");
                    holder.componentSalry.setText(price + "");
                } else {
                    holder.result.setText(count + "");
                    int getCount = Integer.parseInt(holder.result.getText().toString());
                    holder.componentSalry.setText(price / getCount + "");
                }
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
//                if (count <= 0) {
//                    count = 1;
//                    holder.result.setText(count + "");
//                    holder.componentSalry.setText(price + "");
//                } else {
                    holder.result.setText(count + "");
                    int getCount = Integer.parseInt(holder.result.getText().toString());
                    holder.componentSalry.setText(price * getCount + "");
                }
//            }
        });
        Uri uri = Uri.parse(IMG_URL + item.get(position).getFilePath().replace("~", ""));
        holder.imageView.setImageURI(uri);
        holder.result.setText(item.get(position).getCount() + "");
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                listener.onButtonClick(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void addItem(CartItem cartItem) {
        item.add(cartItem);
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
        SimpleDraweeView imageView;
        TextView name;
        TextView componentSalry;
        TextView result;
        CardView cardView;
        Button mins;
        Button plus;

        public ViewHolder(View view) {
            super(view);
            //cv = (CardView) itemView.findViewById(R.id.cardView);
            mins = view.findViewById(R.id.mins);
            plus = view.findViewById(R.id.plus);
            name = view.findViewById(R.id.name);
            componentSalry = view.findViewById(R.id.componentSalry);
            imageView = view.findViewById(R.id.imageView);
            result = view.findViewById(R.id.result);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}
