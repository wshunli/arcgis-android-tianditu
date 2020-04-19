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


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.esri.arcgisruntime.arcgisservices.TileInfo;
import com.esri.arcgisruntime.data.TileKey;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.layers.ImageTiledLayer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class TianDiTuLayer extends ImageTiledLayer {

    private static final String TAG = "TianDiTuLayer";

    private TianDiTuLayerInfo layerInfo;
    private String token = getToken();
    // private String cachePath = getCachePath();
    private StringBuffer tileUrlBuffer = new StringBuffer();

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

        tileUrlBuffer.append(layerInfo.getUrl())
                .append("?service=wmts&request=gettile&version=1.0.0&tk=").append(token)
                .append("&layer=").append(layerInfo.getLayerName())
                .append("&format=tiles&tilematrixset=").append(layerInfo.getTileMatrixSet())
                .append("&tilecol=").append(col)
                .append("&tilerow=").append(row)
                .append("&tilematrix=").append(level);

        FutureTarget<File> submit = Glide
                .with(TianDiTuInitialer.getInstance().getContext())
                .asFile()
                .load(tileUrlBuffer.toString())
                .submit();
        tileUrlBuffer.setLength(0);

        try {
            File file = submit.get();
            try {
                FileInputStream inputStream = new FileInputStream(file);
                bytes = getBytes(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
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

    void setLayerType(int layerType) {
        this.layerInfo = LayerInfoFactory.getLayerInfo(layerType);
    }

    private String getToken() {
        return TianDiTuInitialer.getInstance().getToken();
    }

    private String getCachePath() {
        String initPath = TianDiTuInitialer.getInstance().getCachePath();
        return initPath + "/" + layerInfo.getLayerName() + "_" + layerInfo.getTileMatrixSet() + "/";
    }

}
