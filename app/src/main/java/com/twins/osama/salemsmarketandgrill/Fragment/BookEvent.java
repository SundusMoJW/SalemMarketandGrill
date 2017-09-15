package com.twins.osama.salemsmarketandgrill.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.Calendar;

public class BookEvent extends Fragment {
    private TextView event_date;
    FragmentTransaction manager;
    TextView value;
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
        TypefaceUtil.applyFont(getActivity(),view.findViewById(R.id.bookEvent));
        event_date = (TextView) view.findViewById(R.id.event_date);

        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);

        event_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
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
                datePickerDialog.show();
            }
        });


//        Spinner spinner = (Spinner) view.findViewById(R.id.event_type);
//
//        final List<String> list = new ArrayList<String>();
//        list.add("Event Type");
//        list.add("Event Type");
//        list.add("Event Type");
//        list.add("Event Type");
//        list.add("Event Type");
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(arrayAdapter);


        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        value = (TextView) view.findViewById(R.id.value);
//        seekBar.getProgressDrawable();
                //setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        //seekBar.getThumb().setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_IN);
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
            public void onClick(View v) {
                showPopup(v);
            }
        });

        return view;

    }


    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(getActivity(), view);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popup1:
                      showPopup.setText(item.getTitle());
                        return true;
                    case R.id.popup2:
                        showPopup.setText(item.getTitle());
                        return true;
                    case R.id.popup3:
                        showPopup.setText(item.getTitle());
                        return true;
                    default:
                        return false;
                }
            }

        });

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());

        popup.show();
    }


}
