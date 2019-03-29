package ua.edu.onat.observonat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.prof.rssparser.Article;
import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;

import java.util.List;

import ua.edu.onat.observonat.Helpers.ArticleAdapter;

public class NewsActivity extends AppCompatActivity {

    private ArticleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        RecyclerView recyclerview_news = findViewById(R.id.recyclerview_news);
        recyclerview_news.setLayoutManager(new LinearLayoutManager(this));
        String urlString = "https://onat.edu.ua/feed";
        Parser parser = new Parser();
        parser.onFinish(new OnTaskCompleted() {

            //what to do when the parsing is done
            @Override
            public void onTaskCompleted(List<Article> list) {
                runOnUiThread(() -> {
                    mAdapter = new ArticleAdapter(list, NewsActivity.this);
                    recyclerview_news.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                });
            }

            //what to do in case of error
            @Override
            public void onError(Exception e) {
                //setArticleList(new ArrayList<Article>());
                e.printStackTrace();
                //snackbar.postValue("An error has occurred. Please try again");
            }
        });
        parser.execute(urlString);
    }
}
