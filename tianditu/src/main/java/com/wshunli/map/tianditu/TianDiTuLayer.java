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

import com.esri.arcgisruntime.arcgisservices.TileInfo;
import com.esri.arcgisruntime.io.RequestConfiguration;
import com.esri.arcgisruntime.layers.WebTiledLayer;

public class TianDiTuLayer {

    private static final String TAG = "TianDiTuLayer";

    private Context context = null;
    private String key = null;
    private String cachePath = null;

    private TianDiTuLayer() {
    }

    private volatile static TianDiTuLayer instance = null;

    public static TianDiTuLayer getInstance() {
        if (instance == null) {
            synchronized (TianDiTuLayer.class) {
                if (instance == null) {
                    instance = new TianDiTuLayer();
                }
            }
        }
        return instance;
    }

    // 初始化
    public void init(Context context, String key) {
        if (context == null || key == null || key.isEmpty()) {
            throw new NullPointerException();
        }
        this.init(context, key, getDefaultCachePath(context));
    }

    // 初始化
    public void init(Context context, String key, String cachePath) {
        if (context == null) {
            Log.e(TAG, "context is null, please check it");
            throw new NullPointerException();
        }
        if (key == null || key.isEmpty()) {
            Log.e(TAG, "Please set the key value. See http://lbs.tianditu.gov.cn/authorization/authorization.html");
            throw new NullPointerException();
        }
        if (cachePath == null || cachePath.isEmpty()) {
            Log.w(TAG, "cachePath is null or empty , set default value");
            cachePath = getDefaultCachePath(context);
        }
        this.context = context;
        this.key = key;
        this.cachePath = cachePath;
        Log.i(TAG, "init key: " + key);
    }

    /**
     * 获取 WebTiledLayer 图层
     *
     * @param layerType        天地图图层类型
     * @param spatialReference 天地图坐标系
     * @return ArcGIS Android 对应图层
     */
    public WebTiledLayer getLayer(TianDiTuLayerType layerType, TianDiTuLayerType.SR spatialReference) {

        if (layerType == null) {
            Log.e(TAG, "layerType is null, please check it");
            throw new NullPointerException();
        }

        if (spatialReference == null) {
            Log.e(TAG, "spatialReference is null, please check it");
            throw new NullPointerException();
        }

        // 图层信息
        String templateUri = layerType.getTemplateUri(spatialReference);
        TileInfo tileInfo = layerType.getTileInfo(spatialReference);
        // 创建图层
        WebTiledLayer webTiledLayer = new WebTiledLayer(
                templateUri + "&tk=" + key,
                TianDiTuLayerConstants.SUB_DOMAIN,
                tileInfo,
                spatialReference.getEnvelope());
        webTiledLayer.setName(layerType.getValue() + "_" + spatialReference.getValue());
        // 配置请求头
        RequestConfiguration requestConfiguration = new RequestConfiguration();
        requestConfiguration.getHeaders().put("referer", "https://www.tianditu.gov.cn/");
        webTiledLayer.setRequestConfiguration(requestConfiguration);
        return webTiledLayer;
    }

    private Context getContext() {
        return context;
    }

    private String getToken() {
        return key;
    }

    private String getCachePath() {
        return cachePath;
    }

    private String getDefaultCachePath(Context context) {
        return context.getCacheDir().getAbsolutePath() + "/tdt";
    }

}
