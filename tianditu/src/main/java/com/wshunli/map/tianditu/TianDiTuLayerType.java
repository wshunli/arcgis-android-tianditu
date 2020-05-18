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

import com.esri.arcgisruntime.arcgisservices.LevelOfDetail;
import com.esri.arcgisruntime.arcgisservices.TileInfo;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;

import java.util.ArrayList;
import java.util.List;

/**
 * author : wshunli
 * email : wshunli@qq.com
 * date : 2020/5/17 20:25
 * description ： 天地图图层类型
 */
public enum TianDiTuLayerType {

    /**
     * 天地图矢量图层
     */
    VECTOR("vec"),
    // 矢量图层_中文标注
    VECTOR_ANNOTATION_CN("cva"),
    // 矢量图层_英文标注
    VECTOR_ANNOTATION_EN("eva"),

    /**
     * 天地图影像图层
     */
    IMAGE("img"),
    // 影像图层_中文标注
    IMAGE_ANNOTATION_CN("cia"),
    // 影像图层_英文标注
    IMAGE_ANNOTATION_EN("eia"),

    /**
     * 天地图地形图层
     */
    TERRAIN("ter"),
    // 地形图层_中文标注
    TERRAIN_ANNOTATION_CN("cta");
    // 地形图层_英文标注，暂不支持
    // TERRAIN_ANNOTATION_EN("cta_");

    private String value;

    public String getValue() {
        return value;
    }

    /**
     * 获取模板 Url
     * https://{subDomain}.tianditu.gov.cn/DataServer?T=vec_c&x={col}&y={row}&l={level}&tk=TDT_KEY
     *
     * @param sr 图层坐标系
     * @return 模板 Url
     */
    public String getTemplateUri(SR sr) {
        String templateUri = "https://{subDomain}.tianditu.gov.cn/DataServer?T="
                + this.getValue() + "_" + sr.getValue()
                + "&x={col}&y={row}&l={level}";
        Log.i("TianDiTuLayer", "getTemplateUri: " + templateUri);
        return templateUri;
    }

    /**
     * 获取图层切片信息
     *
     * @param sr 图层坐标系
     * @return 切片信息
     */
    public TileInfo getTileInfo(SR sr) {

        return new TileInfo(
                TianDiTuLayerConstants.TILE_DPI,
                TianDiTuLayerConstants.TILE_FORMAT,
                sr.getLevelOfDetails(),
                sr.getOrigin(),
                sr.getSpatialReference(),
                TianDiTuLayerConstants.TILE_HEIGHT,
                TianDiTuLayerConstants.TILE_WIDTH
        );
    }

    TianDiTuLayerType(String value) {
        this.value = value;
    }

    /**
     * 坐标系类型
     */
    public enum SR {

        ID_2000("c") {
            @Override
            List<LevelOfDetail> getLevelOfDetails() {
                List<LevelOfDetail> levelOfDetail = new ArrayList<>(TianDiTuLayerConstants.LEVEL_MAX);
                for (int i = TianDiTuLayerConstants.LEVEL_MIN; i < TianDiTuLayerConstants.LEVEL_MAX; i++) {
                    LevelOfDetail item = new LevelOfDetail(i, TianDiTuLayerConstants.RESOLUTIONS_2000[i - 1], TianDiTuLayerConstants.SCALES[i - 1]);
                    levelOfDetail.add(item);
                }
                return levelOfDetail;
            }

            @Override
            Point getOrigin() {
                return TianDiTuLayerConstants.ORIGIN_2000;
            }

            @Override
            SpatialReference getSpatialReference() {
                return TianDiTuLayerConstants.SRID_2000;
            }

            @Override
            Envelope getEnvelope() {
                return TianDiTuLayerConstants.ENVELOPE_2000;
            }

        },
        ID_102100("w") {
            @Override
            List<LevelOfDetail> getLevelOfDetails() {
                List<LevelOfDetail> levelOfDetail = new ArrayList<>(TianDiTuLayerConstants.LEVEL_MAX);
                for (int i = TianDiTuLayerConstants.LEVEL_MIN; i < TianDiTuLayerConstants.LEVEL_MAX; i++) {
                    LevelOfDetail item = new LevelOfDetail(i, TianDiTuLayerConstants.RESOLUTIONS_MERCATOR[i - 1], TianDiTuLayerConstants.SCALES[i - 1]);
                    levelOfDetail.add(item);
                }
                return levelOfDetail;
            }

            @Override
            Point getOrigin() {
                return TianDiTuLayerConstants.ORIGIN_MERCATOR;
            }

            @Override
            SpatialReference getSpatialReference() {
                return TianDiTuLayerConstants.SRID_MERCATOR;
            }

            @Override
            Envelope getEnvelope() {
                return TianDiTuLayerConstants.ENVELOPE_MERCATOR;
            }
        };


        // 获取坐标系对应分级
        abstract List<LevelOfDetail> getLevelOfDetails();

        // 获取坐标系起点
        abstract Point getOrigin();

        // 获取对应坐标系
        abstract SpatialReference getSpatialReference();

        // 获取坐标系范围
        abstract Envelope getEnvelope();

        private String value;

        public String getValue() {
            return value;
        }

        SR(String value) {
            this.value = value;
        }
    }


}
