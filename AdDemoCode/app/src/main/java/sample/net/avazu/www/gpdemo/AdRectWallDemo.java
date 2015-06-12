package sample.net.avazu.www.gpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import nativesdk.ad.adcore.modules.webviewad.AdViewSettings;
import nativesdk.ad.adcore.modules.webviewad.AvazuAdView;

public class AdRectWallDemo extends Activity {
	
	private ImageView mImg;
	private RelativeLayout mLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_ad_rect_wall);
		
		mImg = (ImageView) findViewById(R.id.left_img);
		mLayout = (RelativeLayout) findViewById(R.id.ad_layout);
		resizeImage();
		showAd();
	}
	
	private void resizeImage(){
//		int screenHeight = getResources().getDisplayMetrics().heightPixels;
		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		int widthDip = Utils.px2dp(this, screenWidth);
		int imgWidthDip = widthDip - 245;
		int imgHeightDip = imgWidthDip * 670 / 721;
		/*int statusHeight = Utils.getStatusBarHeight(this);
		int showHeight = screenHeight - statusHeight;
		int showWidth = 721 * showHeight / 670;*/
		
		ViewGroup.LayoutParams imgLayoutParams = mImg.getLayoutParams();
		imgLayoutParams.height = Utils.dip2px(this, imgHeightDip);
		imgLayoutParams.width = Utils.dip2px(this, imgWidthDip);
		mImg.setLayoutParams(imgLayoutParams);
	}
	
	private void showAd(){
		AdViewSettings adSettings = new AdViewSettings(-1, -1, AdViewSettings.TYPE_RECT_WALL, true);
		adSettings.setNeedIcon(true);
		adSettings.setNeedTitle(true);
		adSettings.setNeedRating(true);
		adSettings.setNeedReviewNum(true);
		adSettings.setAppCount(12);
		AvazuAdView adView = new AvazuAdView(this, adSettings);
		adView.loadWebviewAd();
		RelativeLayout.LayoutParams adLayoutParams = new RelativeLayout.LayoutParams(-1,-1);
		mLayout.addView(adView, adLayoutParams);
	}
	
	
}