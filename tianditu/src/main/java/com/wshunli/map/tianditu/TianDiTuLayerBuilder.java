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

import com.esri.arcgisruntime.arcgisservices.TileInfo;
import com.esri.arcgisruntime.geometry.Envelope;

public class TianDiTuLayerBuilder {
    private int layerType = 0;
    private String cachePath = null;

    public TianDiTuLayerBuilder setLayerType(int layerType) {
        this.layerType = layerType;
        return this;
    }

    public TianDiTuLayerBuilder setCachePath(String cachePath) {
        this.cachePath = cachePath;
        return this;
    }

    public TianDiTuLayer build() {
        TianDiTuLayerInfo layerInfo = LayerInfoFactory.getLayerInfo(layerType);
        TileInfo tileInfo = layerInfo.getTileInfo();
        Envelope fullExtent = layerInfo.getFullExtent();
        TianDiTuLayer tianDiTuLayer = new TianDiTuLayer(tileInfo, fullExtent);
        tianDiTuLayer.setLayerType(layerType);
        tianDiTuLayer.setCachePath(cachePath);
        return tianDiTuLayer;
    }
}
