package com.adcash.demo.interstitial;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.adcash.mobileads.AdcashAdRequestFailedError;
import com.adcash.mobileads.AdcashInterstitial;


public class InterstitialDemoActivity extends ActionBarActivity {
    Context mContext;
    AdcashInterstitial mInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial_demo);
        mContext = this;

        mInterstitial = new AdcashInterstitial("409357", this); // "<YOUR_ZONE_ID_HERE>"
        mInterstitial.setAdListener(new AdcashInterstitial.AdListener() {
            //Only define methods what are needed
            @Override
            public void onAdLoaded()//When interstitial is ready to be shoved
            {
                mInterstitial.showAd();
            }
            @Override
            public void onAdClosed() //When Interstitial ad is closed
            {
                //do something
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                String message="";
                switch(errorCode){
                    case AdcashAdRequestFailedError.NO_NETWORK:
                        message = "No internet connection"; break;
                    case AdcashAdRequestFailedError.REQUEST_FAILED:
                        message = "Request failed"; break;
                    case AdcashAdRequestFailedError.NETWORK_FAILURE:
                        message = "Network failure"; break;
                    case AdcashAdRequestFailedError.NO_AD:
                        message = "There is no ad"; break;
                    default:
                        message = "Some other problem";

                }

                Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();

                //do something

            }
        });

        mInterstitial.loadAd();
    }
    //Alternatevily you can display Interstitial when you are ready to display it. Just invoke showInterstitial() when ready
    public void showInterstitial() {
        if (mInterstitial.isReady) {
            mInterstitial.showAd();
        }
    }
}
