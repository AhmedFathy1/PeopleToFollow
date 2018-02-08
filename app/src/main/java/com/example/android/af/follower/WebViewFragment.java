package com.example.android.af.follower;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebViewFragment extends Fragment {

    public String mURL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_view_fragment, container, false);
        if (isNetworkAvailable()) {
            WebView myWebView = view.findViewById(R.id.web_view);
            myWebView.setWebViewClient(new MyWebViewClient());
            myWebView.getSettings().setJavaScriptEnabled(true);

            myWebView.loadUrl(mURL);

            return view;
        } else {
            AlertDialog alert = new AlertDialog.Builder(this.getContext()).create();
            alert.setTitle("Info");
            alert.setMessage("Internet not available, Check your internet connectivity and try again!");
            alert.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getActivity().finish();
                }
            });

            alert.show();
        }

        return view;
    }

    /**
     * Change the default behavior of the webview of displaying the webpage in an external browser
     */
    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
