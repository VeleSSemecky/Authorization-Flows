package com.veles.authorizationflows.data.local.data;

public interface DataStore {

    void setAccessToken(String value);

    String getAccessToken();

    void setRefreshToken(String value);

    String getRefreshToken();

    void setCityId(int cityId);

    int getCityId();

    void setPinEncrypt(String value);

    String getPinEncrypt();

    void setIV(byte[] encryptionIv);

    byte[] getIV();

    void setCityName(String city);

    String getCityName();

    void setFCMToken(String value);

    String getFCMToken();

    void setIsUseFingerprint(boolean value);

    boolean isUseFingerprint();

    boolean isInForeground();

    void setIsInForeground(boolean value);

    void clear();
}