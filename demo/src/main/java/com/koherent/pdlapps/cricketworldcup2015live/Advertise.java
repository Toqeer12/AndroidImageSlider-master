package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import utilities.CommonMethods;

public class Advertise {
    AdView adView = null;
    Boolean interstitialadds = false;
    Activity act;
    int index = 0;
    Context con;
    com.google.android.gms.ads.InterstitialAd interstitial = null;

    public void Banner(LinearLayout layout, Activity activity) {
        AdRequest re = new AdRequest.Builder().build();
        adView = new AdView(activity);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId("ca-app-pub-1810661350953447/5828740015");
        layout.addView(adView);
        adView.loadAd(re);
    }

//    public void InterstitialAd(Activity activity, String screen, int number) {
//        act = activity;
//
//        if (CommonMethods.isNetworkAvailable(activity)) {
//
//            int v = PreferenceManager.getDefaultSharedPreferences(activity)
//                    .getInt(screen, 1);
//            if (v >= number) {
//                PreferenceManager.getDefaultSharedPreferences(activity).edit()
//                        .putInt(screen, 1).commit();
//                interstitial = new com.google.android.gms.ads.InterstitialAd(
//                        activity);
//                interstitial.setAdUnitId("ca-app-pub-3216600718487191/9395156264");
//                AdRequest adRequest = new AdRequest.Builder().build();
//                interstitial.loadAd(adRequest);
//                interstitial.setAdListener(new AdListener() {
//                    @Override
//                    public void onAdLoaded() {
//                        // TODO Auto-generated method stub
//                        interstitial.show();
//                        super.onAdLoaded();
//                    }
//                });
//            } else {
//                PreferenceManager.getDefaultSharedPreferences(activity).edit()
//                        .putInt(screen, (v + 1)).commit();
//            }
//
//        }
//
//
//    }

    public void InterstitialAd(Activity activity) {
        act = activity;
        if (CommonMethods.isNetworkAvailable(activity)) {

            interstitial = new com.google.android.gms.ads.InterstitialAd(
                    activity);
            interstitial.setAdUnitId("ca-app-pub-1810661350953447/8782206415");
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitial.loadAd(adRequest);
            interstitial.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    // TODO Auto-generated method stub
                    interstitial.show();
                    super.onAdLoaded();
                }
            });
        }
    }

    public AdView getAdView() {
        return this.adView;
    }

}
