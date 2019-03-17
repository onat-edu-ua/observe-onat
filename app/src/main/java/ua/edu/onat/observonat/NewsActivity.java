package ua.edu.onat.observonat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.prof.rssparser.Article;
import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;

import java.util.List;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        String urlString = "https://onat.edu.ua/feed";
        Parser parser = new Parser();
        parser.onFinish(new OnTaskCompleted() {

            //what to do when the parsing is done
            @Override
            public void onTaskCompleted(List<Article> list) {
                for(Article article: list) {
                    Log.v("article Author", article.getAuthor());
                    Log.v("article Content", article.getContent());
                }
                //setArticleList(list);
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
