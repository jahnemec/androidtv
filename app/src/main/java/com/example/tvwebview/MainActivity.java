import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private static final String TARGET_URL = "https://sitenizin-adresi.com"; // Hedef sitenizi buraya yazın

    // Sade ve hatasız reklam engelleme scripti
    private static final String AD_BLOCK_SCRIPT = "javascript:(function() {" +
            "var style = document.createElement('style');" +
            "style.type = 'text/css';" +
            "style.innerHTML = 'iframe[src*=\"data:\"], iframe[src*=\"about:blank\"], [id*=\"ad\"], [class*=\"ad\"], [id*=\"banner\"], [class*=\"banner\"], [id*=\"popup\"], [class*=\"popup\"], .ad, .ads, div[style*=\"z-index: 99999\"] { display: none !important; visibility: hidden !important; };" +
            "document.getElementsByTagName('head')[0].appendChild(style);" +
            "window.open = function() { return null; };" +
            "})();";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdBlocker.init(this);

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        webSettings.setSupportMultipleWindows(false);

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (AdBlocker.isAd(url)) {
                    return AdBlocker.createEmptyResource();
                }
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                view.evaluateJavascript(AD_BLOCK_SCRIPT, null);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.evaluateJavascript(AD_BLOCK_SCRIPT, null);
            }
        });

        webView.loadUrl(TARGET_URL);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
