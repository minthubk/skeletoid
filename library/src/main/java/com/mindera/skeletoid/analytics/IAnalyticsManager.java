package com.mindera.skeletoid.analytics;

import android.content.Context;

import com.mindera.skeletoid.analytics.appenders.IAnalyticsAppender;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Analytics interface
 */
public interface IAnalyticsManager {

    /**
     * Enable analytics appenders
     *
     * @param context            Context
     * @param analyticsAppenders Log appenders to enable
     * @return Ids of the logs enabled by their order
     */
    Set<String> addAppenders(Context context, List<IAnalyticsAppender> analyticsAppenders);


    /**
     * Disable analytics appenders
     *
     * @param context   Context
     * @param analyticsIds Log ids of each of the analytics enabled by the order sent
     */
    void removeAppenders(Context context, Set<String> analyticsIds);

    /**
     * Disable all analytics appenders
     */
    void removeAllAppenders();

    /**
     * Track app event
     *
     * @param screenName       Screen name
     * @param analyticsPayload generic analytics payload
     */
    void trackEvent(String screenName, Map<String, Object> analyticsPayload);

    /**
     * Track app page hit
     *
     * @param screenName       Screen name
     * @param analyticsPayload generic analytics payload
     */
    void trackPageHit(String screenName, Map<String, Object> analyticsPayload);
}
