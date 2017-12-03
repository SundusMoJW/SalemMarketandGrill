package com.twins.osama.salemsmarketandgrill.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.ListView;

import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.DbBackend;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class SearchActivity extends AppCompatActivity {
    private SearchView searchView;
    private ListView listView;
    private DbBackend databaseObject;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Const.setLangSettings(this);
        setContentView(R.layout.activity_search);
        TypefaceUtil.applyFont(getApplicationContext(), findViewById(R.id.search_activity));

//        searchView = (SearchView) findViewById(R.id.searchView);
//        searchView.setQueryHint("Enter search");
//       // databaseObject = new DbBackend(MainActivity.this);
//        listView = (ListView)findViewById(R.id.listView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                //List<ItemObject> dictionaryObject = databaseObject.searchDictionaryWords(query);
//                //SearchAdapter mSearchAdapter = new SearchAdapter(MainActivity.this, dictionaryObject);
//                listView.setAdapter(mSearchAdapter);
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    }
//                });
//                return true;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//    }
    }
}