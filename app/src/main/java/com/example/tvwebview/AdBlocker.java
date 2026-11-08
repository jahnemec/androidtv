package com.example.tvwebview;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class AdBlocker {
    private static final Set<String> AD_HOSTS = new HashSet<>();
    private static boolean isInitialized = false;

    // Popüler reklam, analytics ve pop-up sunucularının listesi
    private static final String[] KNOWN_AD_DOMAINS = {
        "doubleclick.net", "google-analytics.com", "googlesyndication.com",
        "googleadservices.com", "adnxs.com", "rubiconproject.com", "criteo.com",
        "outbrain.com", "taboola.com", "popads.net", "popcash.net", "exoclick.com",
        "adspyglass.com", "juicyads.com", "propellerads.com", "adroll.com",
        "scorecardresearch.com", "zedo.com", "adform.net", "openx.net",
        "amazon-adsystem.com", "serving-sys.com", "quantserve.com", "exponential.com"
    };

    public static void init(Context context) {
        if (isInitialized) return;
        for (String domain : KNOWN_AD_DOMAINS) {
            AD_HOSTS.add(domain);
        }
        isInitialized = true;
    }

    public static boolean isAd(String url) {
        if (TextUtils.isEmpty(url)) return false;
        try {
            String host = new URL(url).getHost();
            if (host == null) return false;
            
            // Subdomain kontrolü (örn. pagead2.googlesyndication.com)
            for (String adHost : AD_HOSTS) {
                if (host.equals(adHost) || host.endsWith("." + adHost)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static WebResourceResponse createEmptyResource() {
        return new WebResourceResponse("text/plain", "utf-8", new ByteArrayInputStream("".getBytes()));
    }
}
