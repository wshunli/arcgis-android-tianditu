/*
 * Copyright 2017 wshunli
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


import android.util.Log;

import com.esri.arcgisruntime.arcgisservices.TileInfo;
import com.esri.arcgisruntime.data.TileKey;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.layers.ImageTiledLayer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TianDiTuLayer extends ImageTiledLayer {

    private static final String TAG = "TianDiTuLayer";

    private int layerType = 0;
    private TianDiTuLayerInfo layerInfo;
    private String token = getToken();
    private String cachePath = getCachePath();

    public TianDiTuLayer(TileInfo tileInfo, Envelope fullExtent) {
        super(tileInfo, fullExtent);
    }

    @Override
    protected byte[] getTile(TileKey tileKey) {

        int level = tileKey.getLevel();
        int col = tileKey.getColumn();
        int row = tileKey.getRow();
        if (level > layerInfo.getMaxZoomLevel() || level < layerInfo.getMinZoomLevel())
            return new byte[0];
        byte[] bytes = null;
        if (cachePath != null)
            bytes = getOfflineCacheFile(cachePath, level, col, row);
        if (bytes == null) {
            String url = layerInfo.getUrl()
                    + "?service=wmts&request=gettile&version=1.0.0&tk=" + token + "&layer="
                    + layerInfo.getLayerName() + "&format=tiles&tilematrixset="
                    + layerInfo.getTileMatrixSet() + "&tilecol=" + col
                    + "&tilerow=" + row + "&tilematrix=" + (level);

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
                httpConnection.setRequestMethod("GET");
                httpConnection.setConnectTimeout(5000);
                InputStream in = httpConnection.getInputStream();
                bytes = getBytes(in);
                httpConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (cachePath != null) {
                AddOfflineCacheFile(cachePath, level, col, row, bytes);
            }

        }
        return bytes;
    }


    // 保存切片到本地
    private void AddOfflineCacheFile(String cachePath, int level, int col, int row, byte[] bytes) {

        File file = new File(cachePath);
        if (!file.exists()) file.mkdirs();
        File levelFile = new File(cachePath + "/" + level);
        if (!levelFile.exists()) levelFile.mkdirs();
        File rowFile = new File(cachePath + "/" + level + "/" + col + "x" + row
                + ".tdt");

        if (!rowFile.exists()) {
            try {
                FileOutputStream out = new FileOutputStream(rowFile);
                out.write(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    // 从本地获取切片
    private byte[] getOfflineCacheFile(String cachePath, int level, int col, int row) {
        byte[] bytes = null;
        File rowFile = new File(cachePath + "/" + level + "/" + col + "x" + row
                + ".tdt");
        if (rowFile.exists()) {
            try {
                FileInputStream in = new FileInputStream(rowFile);
                bytes = getBytes(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            bytes = null;
        }
        return bytes;
    }

    // 读取字节数组
    private byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        byte[] temp = new byte[1024];
        int size;
        while ((size = is.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        is.close();
        out.flush();
        return out.toByteArray();
    }

    public int getLayerType() {
        return layerType;
    }

    public void setLayerType(int layerType) {
        this.layerType = layerType;
        this.layerInfo = LayerInfoFactory.getLayerInfo(layerType);
    }

    private String getToken() {
        return TianDiTuInitialer.getInstance().getToken();
    }

    private String getCachePath() {
        String initPath = TianDiTuInitialer.getInstance().getCachePath();
        return cachePath == null ? null : cachePath + "/" + layerInfo.getLayerName() + "_" + layerInfo.getTileMatrixSet() + "/";
    }

}
