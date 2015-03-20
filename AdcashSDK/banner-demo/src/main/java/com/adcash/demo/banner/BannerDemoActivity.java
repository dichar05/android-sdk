package com.adcash.demo.banner;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.adcash.mobileads.AdcashAdRequestFailedError;
import com.adcash.mobileads.AdcashBannerView;


public class BannerDemoActivity extends ActionBarActivity {
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_demo);
        mContext = this;

        AdcashBannerView mAdcashView = (AdcashBannerView) findViewById(R.id.adview);
        mAdcashView.setAdUnitId("409358");// "<YOUR_ZONE_ID_HERE>"

        //If needed implement event listener to catch banner loaded event
        mAdcashView.setAdListener(new AdcashBannerView.AdListener() {
            @Override
            public void onAdLoaded() {
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
            }
        });

        mAdcashView.loadAd();
    }

}
