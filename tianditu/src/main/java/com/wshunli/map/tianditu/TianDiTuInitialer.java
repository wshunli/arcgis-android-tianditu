/*
 * Copyright 2020 wshunli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wshunli.map.tianditu;

import android.content.Context;
import android.util.Log;

public class TianDiTuInitialer {
    private static final String TAG = "TianDiTuInitialer";

    private Context context = null;
    private String token = null;
    private String cachePath = null;

    private TianDiTuInitialer() {

    }

    private volatile static TianDiTuInitialer instance = null;

    public static TianDiTuInitialer getInstance() {
        if (instance == null) {
            synchronized (TianDiTuInitialer.class) {
                if (instance == null) {
                    instance = new TianDiTuInitialer();
                }
            }
        }
        return instance;
    }

    // 初始化
    public void init(Context context, String token) {
        if (context == null || token == null || token.isEmpty()) {
            throw new NullPointerException();
        }
        this.init(context, token, getDefaultCachePath(context));
    }

    // 初始化
    public void init(Context context, String token, String cachePath) {
        if (context == null) {
            Log.e(TAG, "context is null, please check it");
            throw new NullPointerException();
        }
        if (token == null || token.isEmpty()) {
            Log.e(TAG, "Please set the token value. See http://lbs.tianditu.gov.cn/authorization/authorization.html");
            throw new NullPointerException();
        }
        if (cachePath == null || cachePath.isEmpty()) {
            Log.w(TAG, "cachePath is null or empty , set default value");
            cachePath = getDefaultCachePath(context);
        }
        this.context = context;
        this.token = token;
        this.cachePath = cachePath;
        Log.i(TAG, "token: " + token);
    }

    private String getDefaultCachePath(Context context) {
        return context.getCacheDir().getAbsolutePath() + "/tdt";
    }

    Context getContext() {
        return context;
    }

    String getToken() {
        return token;
    }

    String getCachePath() {
        return cachePath;
    }

}
