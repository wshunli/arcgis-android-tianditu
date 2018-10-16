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


public interface TianDiTuLayerTypes {

    /**
     * 天地图矢量墨卡托投影地图服务
     */
    int TIANDITU_VECTOR_MERCATOR = 0;
    /**
     * 天地图矢量墨卡托中文标注
     */
    int TIANDITU_VECTOR_ANNOTATION_CHINESE_MERCATOR = 1;
    /**
     * 天地图矢量墨卡托英文标注
     */
    int TIANDITU_VECTOR_ANNOTATION_ENGLISH_MERCATOR = 2;
    /**
     * 天地图影像墨卡托投影地图服务
     */
    int TIANDITU_IMAGE_MERCATOR = 3;
    /**
     * 天地图影像墨卡托投影中文标注
     */
    int TIANDITU_IMAGE_ANNOTATION_CHINESE_MERCATOR = 4;
    /**
     * 天地图影像墨卡托投影英文标注
     */
    int TIANDITU_IMAGE_ANNOTATION_ENGLISH_MERCATOR = 5;
    /**
     * 天地图地形墨卡托投影地图服务
     */
    int TIANDITU_TERRAIN_MERCATOR = 6;
    /**
     * 天地图地形墨卡托投影中文标注
     */
    int TIANDITU_TERRAIN_ANNOTATION_CHINESE_MERCATOR = 7;
    /**
     * 天地图矢量国家2000坐标系地图服务
     */
    int TIANDITU_VECTOR_2000 = 8;
    /**
     * 天地图矢量国家2000坐标系中文标注
     */
    int TIANDITU_VECTOR_ANNOTATION_CHINESE_2000 = 9;
    /**
     * 天地图矢量国家2000坐标系英文标注
     */
    int TIANDITU_VECTOR_ANNOTATION_ENGLISH_2000 = 10;
    /**
     * 天地图影像国家2000坐标系地图服务
     */
    int TIANDITU_IMAGE_2000 = 11;
    /**
     * 天地图影像国家2000坐标系中文标注
     */
    int TIANDITU_IMAGE_ANNOTATION_CHINESE_2000 = 12;
    /**
     * 天地图影像国家2000坐标系英文标注
     */
    int TIANDITU_IMAGE_ANNOTATION_ENGLISH_2000 = 13;
    /**
     * 天地图地形国家2000坐标系地图服务
     */
    int TIANDITU_TERRAIN_2000 = 14;
    /**
     * 天地图地形国家2000坐标系中文标注
     */
    int TIANDITU_TERRAIN_ANNOTATION_CHINESE_2000 = 15;
}