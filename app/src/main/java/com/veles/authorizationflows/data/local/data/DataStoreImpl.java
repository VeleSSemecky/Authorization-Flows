package com.veles.authorizationflows.data.local.data;

import android.content.SharedPreferences;
import android.util.Base64;


import com.veles.authorizationflows.common.Keys;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class DataStoreImpl implements DataStore {

    private final SharedPreferences sharedPreferences;

    @Inject
    DataStoreImpl(final SharedPreferences _sharedPreferences) {
        sharedPreferences = _sharedPreferences;
    }

    @Override
    public void setAccessToken(String value) {
        sharedPreferences.edit()
                .putString(Keys.PreferencesDataStore.ACCESS_TOKEN, value)
                .apply();
    }

    @Override
    public String getAccessToken() {
        return sharedPreferences.getString(Keys.PreferencesDataStore.ACCESS_TOKEN, "");
    }

    @Override
    public void setRefreshToken(String value) {
        sharedPreferences.edit()
                .putString(Keys.PreferencesDataStore.REFRESH_TOKEN, value)
                .apply();
    }

    @Override
    public String getRefreshToken() {
        return sharedPreferences.getString(Keys.PreferencesDataStore.REFRESH_TOKEN, "");
    }

    @Override
    public void setCityId(int cityId) {
        sharedPreferences.edit()
                .putInt(Keys.PreferencesDataStore.CITY_ID, cityId)
                .apply();
    }

    @Override
    public int getCityId() {
        return sharedPreferences.getInt(Keys.PreferencesDataStore.CITY_ID, -1);
    }

    @Override
    public void setCityName(String city) {
        sharedPreferences.edit()
                .putString(Keys.PreferencesDataStore.CITY_NAME, city)
                .apply();
    }

    @Override
    public String getCityName() {
        return sharedPreferences.getString(Keys.PreferencesDataStore.CITY_NAME, "");
    }

    @Override
    public void setPinEncrypt(String value) {
        sharedPreferences.edit()
                .putString(Keys.PreferencesDataStore.PIN_ENCRYPT, value)
                .apply();
    }

    @Override
    public String getPinEncrypt() {
        return sharedPreferences.getString(Keys.PreferencesDataStore.PIN_ENCRYPT,"");
    }

    @Override
    public void setIV(byte[] encryptionIv) {
        sharedPreferences.edit()
                .putString(Keys.PreferencesDataStore.PIN_IV, Base64.encodeToString(encryptionIv, Base64.DEFAULT))
                .apply();
    }

    @Override
    public byte[] getIV() {
        return Base64.decode(sharedPreferences.getString(Keys.PreferencesDataStore.PIN_IV,""), Base64.DEFAULT);
    }

    @Override
    public void setFCMToken(String value) {
        sharedPreferences.edit()
                .putString(Keys.PreferencesDataStore.FCM_TOKEN, value)
                .apply();
    }

    @Override
    public String getFCMToken() {
        return sharedPreferences.getString(Keys.PreferencesDataStore.FCM_TOKEN, "");
    }

    @Override
    public void setIsUseFingerprint(boolean value) {
        sharedPreferences.edit()
                .putBoolean(Keys.PreferencesDataStore.IS_USE_FINGERPRINT, value)
                .apply();
    }

    @Override
    public boolean isUseFingerprint() {
        return sharedPreferences.getBoolean(Keys.PreferencesDataStore.IS_USE_FINGERPRINT, false);
    }

    @Override
    public boolean isInForeground() {
        return sharedPreferences.getBoolean(Keys.PreferencesDataStore.IN_FOREGROUND, false);
    }

    @Override
    public void setIsInForeground(boolean value) {
        sharedPreferences.edit()
                .putBoolean(Keys.PreferencesDataStore.IN_FOREGROUND, value)
                .apply();
    }

    @Override
    public void clear() {
        sharedPreferences.edit()
                .putString(Keys.PreferencesDataStore.ACCESS_TOKEN, "")
                .putString(Keys.PreferencesDataStore.REFRESH_TOKEN, "")
                .putString(Keys.PreferencesDataStore.PIN_ENCRYPT, "")
                .putString(Keys.PreferencesDataStore.PIN_IV,"")
                .putString(Keys.PreferencesDataStore.FCM_TOKEN, "")
                .putBoolean(Keys.PreferencesDataStore.IS_USE_FINGERPRINT, false)
                .apply();
    }
}