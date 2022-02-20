package com.brookapps.spacelife;

import android.os.Bundle;

import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;


public class MyWelcomeActivity extends WelcomeActivity {

    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(R.color.background)
                .page(new TitlePage(R.drawable.img,
                        getString(R.string.welcome_title_text))
                )
                .page(new BasicPage(R.drawable.img,
                        getString(R.string.welcome_subtitle1_text),
                        getString(R.string.welcome_desc1_text))
                        .background(R.color.background)
                )
                .page(new BasicPage(R.drawable.img,
                        getString(R.string.welcome_subtitle2_text),
                        getString(R.string.welcome_desc2_text))
                )
                .swipeToDismiss(true)
                .build();
    }
}
