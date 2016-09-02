package com.example.baseproject.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;


import com.example.baseproject.activities.twitter.LoginToTwitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Created by Shivang Goel on 29/2/16.
 */
public class TwitterUtils {

private Context context;
    private Twitter twitter;
    private RequestToken mRequestToken;
    public static final String AUTHENTICATION_URL_KEY = "AUTHENTICATION_URL_KEY";
    private final int LOGIN_TO_TWITTER_REQUEST = 0;
    private String msgToPost = "";
    private String TWITTER_CONSUMER_KEY = "";
    private String TWITTER_CONSUMER_SECRET = "";
    public static String TWITTER_CALLBACK_URL = "oauth://twitter_callback";

    public TwitterUtils(Context context, String consumerKey, String consumerSecret){
        this.context = context;
//        TWITTER_CALLBACK_URL = callbackUrl;
        TWITTER_CONSUMER_KEY = consumerKey;
        TWITTER_CONSUMER_SECRET = consumerSecret;
    }

    /**
     * Method call to login with twitter
     */
    public void loginToTwitter() {
        GetRequestTokenTask getRequestTokenTask = new GetRequestTokenTask();
        getRequestTokenTask.execute();
    }

    private class GetRequestTokenTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            twitter = TwitterFactory.getSingleton();
            twitter.setOAuthConsumer(TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET);

            try {
                RequestToken requestToken = twitter.getOAuthRequestToken(TWITTER_CALLBACK_URL);
                launchLoginWebView(requestToken);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void launchLoginWebView(RequestToken requestToken) {
        Intent intent = new Intent(context, LoginToTwitter.class);
        intent.putExtra(AUTHENTICATION_URL_KEY, requestToken.getAuthenticationURL());
        ((Activity)context).startActivityForResult(intent, LOGIN_TO_TWITTER_REQUEST);
    }

   /* protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_TO_TWITTER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                getAccessToken(data.getStringExtra(LoginToTwitter.CALLBACK_URL_KEY));

            }
        }
    }*/

    /**
     * Method call to post twittes
     * @param msg
     * @param callbackUrl
     */
    public void postTwittes(String msg, String callbackUrl){
        getAccessToken(callbackUrl);
        msgToPost = msg;
    }


    private void getAccessToken(String callbackUrl) {
        Uri uri = Uri.parse(callbackUrl);
        String verifier = uri.getQueryParameter("oauth_verifier");

        GetAccessTokenTask getAccessTokenTask = new GetAccessTokenTask();
        getAccessTokenTask.execute(verifier);
    }

    private class GetAccessTokenTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            String verifier = strings[0];
            try {
                AccessToken accessToken = twitter.getOAuthAccessToken(verifier);
                Logger.getInstance().log_debug(TwitterUtils.class.getSimpleName() + " - Access Token:  " + accessToken.getToken());
                twitter.updateStatus(msgToPost);
                Logger.getInstance().log_info("Twit Posted Successfully");
            } catch (Exception e) {
               Logger.getInstance().log_debug("Exception while post twit", e);
            }
            return null;
        }
    }
}
