package com.me.heartattack.module;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.me.heartattack.like.GeneralLiker;
import com.me.heartattack.like.Liker;
import com.me.heartattack.like.PhotoLiker;
import com.me.heartattack.model.MomentType;
import dev.brachtendorf.jimagehash.hashAlgorithms.HashingAlgorithm;
import dev.brachtendorf.jimagehash.hashAlgorithms.PerceptiveHash;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static com.me.heartattack.config.Config.APPIUM_SERVER;
import static com.me.heartattack.config.Config.DEVICE_ID;

public class AppModules implements Module {

    @Override
    public void configure(Binder binder) {

    }

    @Provides
    @Singleton
    AndroidDriver provideAndroidDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", DEVICE_ID);
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:launchTimeout", 500000);
        desiredCapabilities.setCapability("appium:connectionTimeouts", 12000);
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
        desiredCapabilities.setCapability("appium:autoGrantPermissions", true);

        URL remoteUrl = new URL(APPIUM_SERVER);
        return new AndroidDriver(remoteUrl, desiredCapabilities);
    }


    @Provides
    @Singleton
    Map<MomentType, String> provideMomentTypeToXpath() {
        return Map.of(
                MomentType.LINK, "//${LL}[@content-desc and ./${LL}/${TV} and ./${LL}/${TV}[not(child::*) and not(following-sibling::*)]]",
                MomentType.TEXT, "//${LL}[@content-desc and not(child::*)]",
                MomentType.PHOTO, "//${LL}[./${FL}/${FL}/${V}[@content-desc=\"Images\"]]"
        );
    }

    @Provides
    @Singleton
    Map<MomentType, Liker> provideMomentTypeToLiker(final GeneralLiker generalLiker, final PhotoLiker photoLiker) {
        return Map.of(
                MomentType.LINK, generalLiker,
                MomentType.TEXT, generalLiker,
                MomentType.PHOTO, photoLiker
        );
    }

    @Provides
    @Singleton
    HashingAlgorithm provideHarsher() {
        return new PerceptiveHash(32);
    }
}
