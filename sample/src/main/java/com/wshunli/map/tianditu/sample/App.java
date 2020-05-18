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
package com.wshunli.map.tianditu.sample;

import android.app.Application;

import com.wshunli.map.tianditu.TianDiTuLayer;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /*
          TODO： 请将 TDT_KEY 替换为自己申请的 key
          http://lbs.tianditu.gov.cn/authorization/authorization.html
         */

        // 简单初始化
        TianDiTuLayer.getInstance().init(this, "TDT_KEY");

        // 设置缓存路径
//        String cachePath = getCacheDir().getAbsolutePath() + "/TianDiTu100Cache";
        // 注意权限处理
//        String cachePath = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/TianDiTu100Cache";
        // 设置缓存路径
//        TianDiTuLayer.getInstance().init(this, "TDT_KEY", cachePath);

    }

}
