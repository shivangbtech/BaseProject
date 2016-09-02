package com.example.baseproject.activities.twitter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.baseproject.R;
import com.example.baseproject.utilities.TwitterUtils;

import twitter4j.Twitter;
import twitter4j.auth.RequestToken;

public class TwitterSampleActivity extends Activity {

    private static Twitter twitter;

    private Twitter mTwitter;
    private RequestToken mRequestToken;
    private Context context;
    private TwitterUtils twit;
    private String TWITTER_CONSUMER_KEY = "NJxK57EUeksyaPeWBRhqK97iD";
    private String TWITTER_CONSUMER_SECRET = "q3Ic7YVwTIgU0eZ91VpRdyMqLYolubYXt2rMtsS7xpqB2VjCEz";
    protected static final String AUTHENTICATION_URL_KEY = "AUTHENTICATION_URL_KEY";
    protected static final int LOGIN_TO_TWITTER_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_sample);
        context = this;
        findViewById(R.id.btnLoginToTwitter)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        twit = new TwitterUtils(context, TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET);
                        twit.loginToTwitter();
                    }
                });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_TO_TWITTER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                twit.postTwittes("ErrorMessage to post", data.getStringExtra(LoginToTwitter.CALLBACK_URL_KEY));
            }
        }
    }

   /* http://twitter4j.org/en/code-examples.html*/

   /* String tweetUrl = "https://twitter.com/intent/tweet?text=PUT TEXT HERE &url=" + "https://www.google.com";
    Uri uri = Uri.parse(tweetUrl);
    startActivity(new Intent(Intent.ACTION_VIEW, uri));*/
}
