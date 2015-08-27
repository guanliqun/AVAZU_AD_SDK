package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import nativesdk.ad.adcore.AdSdk;
import nativesdk.ad.adcore.common.utils.L;
import nativesdk.ad.adcore.modules.directToMarket.DirectToMarketManager;
import nativesdk.ad.adcore.modules.webviewad.AdViewSettings;


public class MainActivity extends Activity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ad_main_layout);

        findViewById(R.id.go_banner_single).setOnClickListener(this);
        findViewById(R.id.go_banner_wall).setOnClickListener(this);
        findViewById(R.id.go_banner_custom).setOnClickListener(this);
        findViewById(R.id.go_rect_single).setOnClickListener(this);
        findViewById(R.id.go_rect_wall).setOnClickListener(this);
        findViewById(R.id.go_rect_custom).setOnClickListener(this);
        findViewById(R.id.raw_data).setOnClickListener(this);
        findViewById(R.id.direct_to_market).setOnClickListener(this);
        findViewById(R.id.subscription_ad).setOnClickListener(this);
        AdSdk.initialize(getApplicationContext(), "15887");
    }

    private void goDefaultRectWall() {
        AdViewSettings adSettings = new AdViewSettings(-1, -1, AdViewSettings.TYPE_RECT_WALL, true);
        adSettings.setAppCount(6);
        adSettings.setNeedBtn(false);
        adSettings.setNeedIcon(true);
        adSettings.setNeedRating(true);
        adSettings.setNeedTitle(true);
        adSettings.setNeedSize(false);
        adSettings.setNeedCat(false);
        adSettings.setMainBackColor("#E8E6E6");
        adSettings.setBlockBackColor("#EDEDE1");
        adSettings.setAppTitleColor("#000000");
        /*controller.setButtonBackColor("#ADCF7C");
        controller.setButtonTextColor("#000000");*/
        goShowAd(adSettings);
    }

    private void goDefaultRectSingle() {
        AdViewSettings controller = new AdViewSettings(120, 165, AdViewSettings.TYPE_RECT_SINGLE, true);
        controller.setNeedIcon(true);
        controller.setNeedRating(true);
        controller.setNeedTitle(true);
        controller.setBlockBackColor("#EDEDE1");
        controller.setAppTitleColor("#000000");
        goShowAd(controller);
    }

    private void goDefaultBannerWall() {
        AdViewSettings controller = new AdViewSettings(-1, -1, AdViewSettings.TYPE_BANNER_WALL, true);
        controller.setAppCount(12);
        controller.setNeedBtn(true);
        controller.setNeedCat(true);
        controller.setNeedIcon(true);
        controller.setNeedInstalls(true);
        controller.setNeedRating(true);
        controller.setNeedReviewNum(true);
        controller.setNeedSize(true);
        controller.setNeedTitle(true);
        controller.setMainBackColor("#EDEDE1");
        controller.setAppTitleColor("#000000");
        goShowAd(controller);
    }

    private void goDefaultBannerSingle() {

        int sw = getResources().getDisplayMetrics().widthPixels;
        int swD = Utils.px2dp(this, sw);
        int hD = swD * 100 / 360;
        AdViewSettings controller = new AdViewSettings(swD, hD, AdViewSettings.TYPE_BANNER_SINGLE, true);
        controller.setNeedBtn(true);
        controller.setNeedCat(true);
        controller.setNeedIcon(true);
        controller.setNeedInstalls(true);
        controller.setNeedRating(true);
        controller.setNeedReviewNum(true);
        controller.setNeedSize(true);
        controller.setNeedTitle(true);
        controller.setBlockBackColor("#EDEDE1");
        controller.setAppTitleColor("#000000");
        goShowAd(controller);
    }

    private void goCustomAd(int style) {
        Intent custom = new Intent(this, AdCustomizedActivity.class);
        custom.putExtra(Utils.Constants.BUNDLE_KEY_ADSTYLE, style);
        startActivity(custom);
    }

    private void goShowAd(AdViewSettings controller) {
        Intent fire = new Intent(this, AdShowActy.class);
        fire.putExtra(Utils.Constants.BUNDLE_KEY_ADVIEWCONTROLLER, controller);
        startActivity(fire);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int id = v.getId();
        switch (id) {
            case R.id.go_banner_single:
//			goDefaultBannerSingle();
                showTwoPickDialog();
                break;

            case R.id.go_banner_wall:
//			goDefaultBannerWall();
                goBannerWallNew();
                break;

            case R.id.go_rect_single:
//			goDefaultRectSingle();
                goRectSingleNew();
                break;

            case R.id.go_rect_wall:
//			goDefaultRectWall();
                goRectWallNew();
                break;

            case R.id.go_rect_custom:
                goCustomAd(Utils.Constants.STYLE_RECT);
                break;

            case R.id.go_banner_custom:
                goCustomAd(Utils.Constants.STYLE_BANNER);
                break;
            case R.id.raw_data:
                Intent fire = new Intent(this, AdRawDataShow.class);
                startActivity(fire);
                break;
            case R.id.direct_to_market:
                AdSdk.directToMarket(this, "15887", new DirectToMarketManager.DirectToMarketListener() {
                    @Override
                    public void DirectToMarketSuccess() {
                        L.d("DirectToMarketSuccess");
                    }

                    @Override
                    public void DirectMarketToFail() {
                        L.d("DirectMarketToFail");
                    }
                });
                break;
            case R.id.subscription_ad:
                Intent SubscriptionIntent = new Intent(this, AdSubscriptionActivity.class);
                startActivity(SubscriptionIntent);
                break;
        }
    }

    private void goBannerWallNew() {
        Intent fire = new Intent(this, AdBannerWallDemo.class);
        startActivity(fire);
    }

    private void goBannerSingleNew1() {
        Intent fire = new Intent(this, AdBannerSingleDemo.class);
        startActivity(fire);
    }

    private void goBannerSingleNew0() {
        Intent fire = new Intent(this, AdBannerSingleDemo2.class);
        startActivity(fire);
    }

    private void goRectWallNew() {
        Intent fire = new Intent(this, AdRectWallDemo.class);
        startActivity(fire);
    }

    private void goRectSingleNew() {
        Intent fire = new Intent(this, AdRectSingleDemo.class);
        startActivity(fire);
    }

    private void showTwoPickDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(new CharSequence[]{"Integrated right inside of your app", "Integrated above your app"}, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (which == 0) {
                    goBannerSingleNew0();
                } else if (which == 1) {
                    goBannerSingleNew1();
                }
            }
        });
        builder.show();
    }
}
