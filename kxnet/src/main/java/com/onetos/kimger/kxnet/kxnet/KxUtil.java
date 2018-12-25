package com.onetos.kimger.kxnet.kxnet;

import android.support.annotation.Nullable;

/**
 * @author Kimger
 * @email kimgerxue@gmail.com
 * @date 2018/12/25 0025 16:03
 * @description
 */
class KxUtil {
    static <T> T checkNotNull(@Nullable T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }

    public static void throwValidation() {
        throw new IllegalStateException("KxNet is not init. " +
                "Please call KxNet.init().build() and wait the initialisation finishes.");
    }

    public static void throwValidation(String msg) {
        throw new IllegalStateException(msg);
    }
}
