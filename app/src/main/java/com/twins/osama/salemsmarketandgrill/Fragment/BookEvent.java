package com.twins.osama.salemsmarketandgrill.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookEvent extends Fragment {
    private TextView event_date;
    private FragmentTransaction manager;
    private TextView value;
    private EditText showPopup;

    public static BookEvent newInstance() {
        BookEvent fragment = new BookEvent();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // استدعي الميثود وامررها اكتيفيتي يعني (this)قبل ما أمرر ال layout
        Const.setLangSettings(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_book_event, container, false);
        TypefaceUtil.applyFont(getActivity(), view.findViewById(R.id.bookEvent));
        event_date = (TextView) view.findViewById(R.id.event_date);

        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);

        event_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String myTime = year + "-" + (month + 1) + "-" + dayOfMonth;
                        event_date.setText(myTime);
                    }
                }, year, month, day);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                }
                datePickerDialog.show();
            }
        });

        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        value = (TextView) view.findViewById(R.id.value);
       seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        showPopup = (EditText) view.findViewById(R.id.showPopup);
        showPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                dialog.setContentView(R.layout.spinner_layout);
                ListView listView = (ListView) dialog.findViewById(R.id.showPopupCart);
                final List<String> items = new ArrayList();
                items.add("Event Type");
                items.add("Event Type");
                items.add("Event Type");
                items.add("Event Type");
                ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,/* R.layout.spinner_layout, R.id.showPopupCart,*/items);
                listView.setAdapter(adapter);
                listView.setTextFilterEnabled(true);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        showPopup.setText(items.get(position));
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        return view;

    }

}
