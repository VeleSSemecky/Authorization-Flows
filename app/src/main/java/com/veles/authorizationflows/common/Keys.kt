package com.veles.authorizationflows.common

class Keys {
    object Args {
        const val FILE_PROVIDER = ".fileprovider"
        const val JPG = ".jpg"
        const val YYYYMMDD_HHMMSS = "yyyyMMdd_HHmmss"
        const val START_ACTIVITY_FOR_RESULT = 96
        const val PURCHASE_MODELS = "purchaseModels"
        const val FCM_MODELS = "fcmModels"
        const val PURCHASE_MODEL = "purchaseModel"
        const val USER_DATA = "USER_DATA"
    }

    object Constant {
        const val CITY_ID = "city_id"
    }

    object PreferencesDataStore{
            const val ACCESS_TOKEN = "ACCESS_TOKEN"
            const val REFRESH_TOKEN = "REFRESH_TOKEN"
            const val FCM_TOKEN = "FCM_TOKEN"
            const val CITY_ID = "CITY_ID"
            const val CITY_NAME = "CITY_NAME"
            const val PIN_ENCRYPT = "PIN_ENCRYPT"
            const val PIN_IV = "PIN_IV"
            const val IS_USE_FINGERPRINT = "IS_USE_FINGERPRINT"
            const val IN_FOREGROUND = "IN_FOREGROUND"
    }

    object NetworkConstants{
            const val READ_TIMEOUT = 2
            const val WRITE_TIMEOUT = 2
    }

    object NotificationData{
            const val INTENT_NOTIFICATION_ID = "NOTIFICATION_ID"
            const val INTENT_NOTIFICATION_URL = "NOTIFICATION_URL"
            const val INTENT_NOTIFICATION_CATEGORY = "NOTIFICATION_CATEGORY"
            const val INTENT_NOTIFICATION_NOTIFY_ID = 1
            const val INTENT_NOTIFICATION_REQUEST_CODE = 0
            const val INTENT_NOTIFICATION_CHANNEL_ID = "purchase_channel_id"
            const val INTENT_NOTIFICATION_DESCRIPTION = "purchase_channel_controller"
        }
}