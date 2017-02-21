package com.example.litepal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCreate, mAdd, mUpdate, mDelete, mQuery;
    private Book book;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCreate = (Button) findViewById(R.id.create);
        mAdd = (Button) findViewById(R.id.add);
        mUpdate = (Button) findViewById(R.id.update);
        mDelete = (Button) findViewById(R.id.delete);
        mQuery = (Button) findViewById(R.id.query);
        mCreate.setOnClickListener(this);
        mAdd.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mQuery.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:
                LitePal.getDatabase();
                break;
            case R.id.add:
                book = new Book();
                book.setName("java");
                book.setAuthor("A");
                book.setPages(231);
                book.setPrice(25.5);
                book.setPress("Unknow");
                book.save();
                break;
            case R.id.update:
                //第一种更新方法，限制性较大
               /* book = new Book();
                book.setName("android");
                book.setAuthor("B");
                book.setPages(212);
                book.setPrice(20);
                book.setPress("Unknow");
                book.save();
                book.setPrice(30);
                book.save();  */
                //第二种根据条件约束更新数据
                book = new Book();
                book.setPrice(50.5);
                book.setPress("gdcp");
                book.updateAll("name = ? and author = ?", "android", "B");
                //更新默认值数据
               /* book = new Book();
                book.setToDefault("pages");
                book.updateAll("约束条件");*/
                break;
            case R.id.delete:
                //根据约束条件删除数据
                DataSupport.deleteAll(Book.class, "id > ?", "2");
                //删除表中所有数据
                DataSupport.deleteAll(Book.class);
                break;
            case R.id.query:
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book : books) {
                    Log.d(TAG, "book name is: "+book.getName());
                    Log.d(TAG, "book name is: "+book.getAuthor());
                    Log.d(TAG, "book name is: "+book.getPages());
                    Log.d(TAG, "book name is: "+book.getPrice());
                    Log.d(TAG, "book name is: "+book.getPress());
                    /*
                    *  Litepal还提供了非常多的查询API
                    *  ====查询表中第一行数据
                    * Book fristBook = DataSupport.findFirst(Book.class);
                    * ====查询表中第一行数据
                    * Book lastBook = DataSupport.findLast(Book.class);
                    * ====指定查询哪几条数据======select();
                    * List<Book> books = DataSupport.select("name","author").find(Book.class);
                    * ====根据约束条件查询数据====where();
                    *List<Book> books = DataSupport.where("id > ?","4").find(Book.class);
                    * ====指定结果排序方式====order();  desc表示降序 asc表示升序  默认为升序
                    * List<Book> books = DataSupport.order("id desc").find(Book.class);
                    * ====指定查绚结果的数量====limit();
                    * List<Book> books = DataSupport.limit(3).find(Book.class);
                    * ====指定查询结果的偏移量===offset();
                    * 比如查询表中的第2,3,4条数据：
                    * List<Book> books = DataSupport.limit(3).offset(1).find(Book.class);
                    *
                    * *******5个方法可以进行任意连缀组合*******
                    * List<Book> books = DataSupport.select("name","author"，"pages")
                    *                               .where("pages > ?","50")
                    *                               .order("pages")
                    *                               .limit(10)
                    *                               .offset(2)
                    *                               .find(Book.class);
                    *
                    * */

                }
                break;
            default:
                break;
        }
    }
}
