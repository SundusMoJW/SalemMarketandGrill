package com.twins.osama.salemsmarketandgrill.Adapter;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.twins.osama.salemsmarketandgrill.Classes.CartItem;
import com.twins.osama.salemsmarketandgrill.Helpar.RealmController;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;

import io.realm.Realm;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.IMG_URL;

/**
 * Created by Osama on 12/3/2017.
 */

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    private ArrayList<CartItem> item = new ArrayList<>();
    private AppCompatActivity context;
    private int count = 1;
    double price = 1;
    private TextView subtotal;
    private TextView shipping;
    private TextView total;
    private double result = 0;
    private double shipp = 0;
    private Realm realm;
//    private OnClickedCartItem listener;

    public CartItemAdapter(AppCompatActivity context, ArrayList item, TextView subtotal, TextView shipping, TextView total/*, OnClickedCartItem listener*/) {
        this.context = context;
        this.item = item;
        this.subtotal = subtotal;
        this.shipping = shipping;
        this.total = total;
//        this.listener = listener;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }


    @Override
    public CartItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        TypefaceUtil.applyFont(context, view.findViewById(R.id.cardView));
        shipp = Double.parseDouble(shipping.getText().toString().replace("US", ""));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartItemAdapter.ViewHolder holder, final int position) {
        final int id = item.get(position).getId();
        final int idTypeList = item.get(position).getIdTypeList();
        Log.d("//Id  :", id + "   idType:" + idTypeList);
        price = RealmController.with(context).getPrice(id, idTypeList);
        count = item.get(position).getCount();
        holder.componentSalry.setText(price + " $US");
        holder.name.setText(item.get(position).getName());
        holder.result.setText(count + "");
        calculator();
        final CartItem cartItem = RealmController.with(context).cheackCartItem(item.get(position).getId(),
                item.get(position).getIdTypeList());
        holder.mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                count = item.get(position).getCount();
                count = Integer.parseInt(holder.result.getText().toString());
                count--;
                if (count <= 0) {
                    count = 1;
                    price = RealmController.with(context).getPrice(id, idTypeList);
                    realm.beginTransaction();
                    cartItem.setCount(count);
                    realm.commitTransaction();
                    holder.result.setText(count + "");
                    calculator();
                } else {
                    realm.beginTransaction();
                    cartItem.setCount(count);
                    realm.commitTransaction();
                    holder.result.setText(count + "");
                    calculator();
                }
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                count = item.get(position).getCount();
                count = Integer.parseInt(holder.result.getText().toString());
                count++;
                price = RealmController.with(context).getPrice(id, idTypeList);
                realm.beginTransaction();
                cartItem.setCount(count);
                realm.commitTransaction();
                holder.result.setText(count + "");
                calculator();
            }
//            }
        });
        Uri uri = Uri.parse(IMG_URL + item.get(position).getFilePath().replace("~", ""));
        holder.imageView.setImageURI(uri);
        holder.result.setText(item.get(position).getCount() + "");

        holder.checkBox.setChecked(item.get(position).isSelect());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                item.get(holder.getAdapterPosition()).setSelect(b);
//                                listener.onItemLongClicked(holder.getAdapterPosition());

//                realm.beginTransaction();
//                cartItem.setSelect(b);
//                realm.commitTransaction();
            }
        });

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                listener.onItemLongClicked(position);
//                return true;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    private void calculator() {
        result = 0;
        shipp = Double.parseDouble(shipping.getText().toString().replace("US", ""));
        for (CartItem cartItem : item) {
            double price = cartItem.getPrice();
            int count = cartItem.getCount();
            result += (price * count);
        }
        total.setText(result + shipp + "US");
        subtotal.setText(result + "US");
        Log.i("total", result + shipp + "");
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
        CheckBox checkBox;

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
            checkBox = view.findViewById(R.id.checkBox);
        }
    }
}
