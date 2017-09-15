package com.twins.osama.salemsmarketandgrill.Helpar;

/**
 * Created by Osama on 8/3/2017.
 */
public class DbBackend {
//        extends DbObject{
//    public DbBackend(Context context) {
//        super(context);
//    }
//    public List<ItemObject> searchDictionaryWords(String searchWord){
//        List<ItemObject> mItems = new ArrayList<ItemObject>();
//        String query = "Select * from dictionary where word like " + "'%" + searchWord + "%'";
//        Cursor cursor = this.getDbConnection().rawQuery(query, null);
//        ArrayList<String> wordTerms = new ArrayList<String>();
//        if(cursor.moveToFirst()){
//            do{
//                int id = cursor.getInt(0);
//                String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));
//                mItems.add(new ItemObject(id, word));
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        return mItems;
//    }
}