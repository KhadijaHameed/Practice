package com.example.roomwrapperimplement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomwrapperimplement.db.Repository;
import com.example.roomwrapperimplement.db.WordDao;
import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    Repository dbRepo;
    EditText etNewWord;
    String newWord = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbRepo = new Repository(getApplicationContext());

        etNewWord = findViewById(R.id.et_word);

        //region stetho
        // Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(new DumperPluginsProvider() {
                    @Override
                    public Iterable<DumperPlugin> get() {
                        return new Stetho.DefaultDumperPluginsBuilder(MainActivity.this)
                                //  .provide(new MyDumperPlugin())
                                .finish();
                    }
                })
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
        //endregion stetho

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            try {
                newWord = etNewWord.getText().toString();
                Word word = new Word("");
                //    dbRepo.insert(word);
                //    dbRepo.updateWord(word, 9);
                //    dbRepo.deleteRowbyID(10);
                //    **it can also delete null space row <==>
                //    dbRepo.deleteRowbySpecificWord(word);
                Toast.makeText(this, "delete Successfully! " + word.getWord(), Toast.LENGTH_SHORT).show();
                etNewWord.setText(" ");
            } catch (Exception e) {
                Log.d("#error", e.getLocalizedMessage());
            }
        });

    }


}