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


import com.esri.arcgisruntime.arcgisservices.LevelOfDetail;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;

import java.util.ArrayList;
import java.util.List;

class LayerInfoFactory {

    private static final String URL_VECTOR_2000 = "https://t0.tianditu.gov.cn/vec_c/wmts";
    private static final String URL_VECTOR_ANNOTATION_CHINESE_2000 = "https://t0.tianditu.gov.cn/cva_c/wmts";
    private static final String URL_VECTOR_ANNOTATION_ENGLISH_2000 = "https://t0.tianditu.gov.cn/eva_c/wmts";
    private static final String URL_IMAGE_2000 = "https://t0.tianditu.gov.cn/img_c/wmts";
    private static final String URL_IMAGE_ANNOTATION_CHINESE_2000 = "https://t0.tianditu.gov.cn/cia_c/wmts";
    private static final String URL_IMAGE_ANNOTATION_ENGLISH_2000 = "https://t0.tianditu.gov.cn/cia_c/wmts";
    private static final String URL_TERRAIN_2000 = "https://t0.tianditu.gov.cn/ter_c/wmts";
    private static final String URL_TERRAIN_ANNOTATION_CHINESE_2000 = "https://t0.tianditu.gov.cn/cta_c/wmts";

    private static final String URL_VECTOR_MERCATOR = "https://t0.tianditu.gov.cn/vec_w/wmts";
    private static final String URL_VECTOR_ANNOTATION_CHINESE_MERCATOR = "https://t0.tianditu.gov.cn/cva_w/wmts";
    private static final String URL_VECTOR_ANNOTATION_ENGLISH_MERCATOR = "https://t0.tianditu.gov.cn/eva_w/wmts";
    private static final String URL_IMAGE_MERCATOR = "https://t0.tianditu.gov.cn/img_w/wmts";
    private static final String URL_IMAGE_ANNOTATION_CHINESE_MERCATOR = "https://t0.tianditu.gov.cn/cia_w/wmts";
    private static final String URL_IMAGE_ANNOTATION_ENGLISH_MERCATOR = "https://t0.tianditu.gov.cn/cia_w/wmts";
    private static final String URL_TERRAIN_MERCATOR = "https://t0.tianditu.gov.cn/ter_w/wmts";
    private static final String URL_TERRAIN_ANNOTATION_CHINESE_MERCATOR = "https://t0.tianditu.gov.cn/cta_w/wmts";

    private static final String LAYER_NAME_VECTOR = "vec";
    private static final String LAYER_NAME_VECTOR_ANNOTATION_CHINESE = "cva";
    private static final String LAYER_NAME_VECTOR_ANNOTATION_ENGLISH = "eva";
    private static final String LAYER_NAME_IMAGE = "img";
    private static final String LAYER_NAME_IMAGE_ANNOTATION_CHINESE = "cia";
    private static final String LAYER_NAME_IMAGE_ANNOTATION_ENGLISH = "eia";
    private static final String LAYER_NAME_TERRAIN = "ter";
    private static final String LAYER_NAME_TERRAIN_ANNOTATION_CHINESE = "cta";

    private static final String TILE_MATRIX_SET_MERCATOR = "w";
    private static final String TILE_MATRIX_SET_2000 = "c";

    private static final Point ORIGIN_2000 = new Point(-180, 90,
            SpatialReference.create(LayerInfoFactory.SRID_2000));
    private static final Point ORIGIN_MERCATOR = new Point(-20037508.3427892,
            20037508.3427892,
            SpatialReference.create(LayerInfoFactory.SRID_MERCATOR));

    private static final int SRID_2000 = 4326;
    private static final int SRID_MERCATOR = 3857;

    private static final double X_MIN_2000 = -180;
    private static final double Y_MIN_2000 = -90;
    private static final double X_MAX_2000 = 180;
    private static final double Y_MAX_2000 = 90;

    private static final double X_MIN_MERCATOR = -20037508.3427892;
    private static final double Y_MIN_MERCATOR = -20037508.3427892;
    private static final double X_MAX_MERCATOR = 20037508.3427892;
    private static final double Y_MAX_MERCATOR = 20037508.3427892;

    private static final double[] SCALES = { 2.958293554545656E8,
            1.479146777272828E8, 7.39573388636414E7, 3.69786694318207E7,
            1.848933471591035E7, 9244667.357955175, 4622333.678977588,
            2311166.839488794, 1155583.419744397, 577791.7098721985,
            288895.85493609926, 144447.92746804963, 72223.96373402482,
            36111.98186701241, 18055.990933506204, 9027.995466753102,
            4513.997733376551, 2256.998866688275, 1127.2338602399827,
            563.6169301199914 };//

    private static final double[] RESOLUTIONS_MERCATOR = { 78271.51696402048,
            39135.75848201024, 19567.87924100512, 9783.93962050256,
            4891.96981025128, 2445.98490512564, 1222.99245256282,
            611.49622628141, 305.748113140705, 152.8740565703525,
            76.43702828517625, 38.21851414258813, 19.109257071294063,
            9.554628535647032, 4.777314267823516, 2.388657133911758,
            1.194328566955879, 0.5971642834779395 };

    private static final double[] RESOLUTIONS_2000 = { 0.7031249999891485,
            0.35156249999999994, 0.17578124999999997, 0.08789062500000014,
            0.04394531250000007, 0.021972656250000007, 0.01098632812500002,
            0.00549316406250001, 0.0027465820312500017, 0.0013732910156250009,
            0.000686645507812499, 0.0003433227539062495,
            0.00017166137695312503, 0.00008583068847656251,
            0.000042915344238281406, 0.000021457672119140645,
            0.000010728836059570307, 0.000005364418029785169,
            2.682209014892579e-006, 1.3411045074462895e-006 };//
    private static List<LevelOfDetail> getLods102100(){
        List<LevelOfDetail> lods = new ArrayList<LevelOfDetail>();
        lods.add(new LevelOfDetail(1, 78271.51696402048, 2.958293554545656E8));
        lods.add(new LevelOfDetail(2, 39135.75848201024, 1.479146777272828E8));
        lods.add(new LevelOfDetail(3, 19567.87924100512, 7.39573388636414E7));
        lods.add(new LevelOfDetail(4, 9783.93962050256, 3.69786694318207E7));
        lods.add(new LevelOfDetail(5, 4891.96981025128, 1.848933471591035E7));
        lods.add(new LevelOfDetail(6, 2445.98490512564, 9244667.35795517));
        lods.add(new LevelOfDetail(7, 1222.99245256282, 4622333.678977588));
        lods.add(new LevelOfDetail(8, 611.49622628141, 2311166.839488794));
        lods.add(new LevelOfDetail(9, 305.748113140705, 1155583.419744397));
        lods.add(new LevelOfDetail(10, 152.8740565703525, 577791.7098721985));
        lods.add(new LevelOfDetail(11, 76.43702828517625, 288895.85493609926));
        lods.add(new LevelOfDetail(12, 38.21851414258813, 144447.92746804963));
        lods.add(new LevelOfDetail(13, 19.109257071294063, 72223.96373402482));
        lods.add(new LevelOfDetail(14, 9.554628535647032, 36111.98186701241));
        lods.add(new LevelOfDetail(15, 4.777314267823516, 18055.990933506204));
        lods.add(new LevelOfDetail(16, 2.388657133911758, 9027.995466753102));
        lods.add(new LevelOfDetail(17, 1.194328566955879, 4513.997733376551));
        lods.add(new LevelOfDetail(18, 0.5971642834779395, 2256.998866688275));
        return lods;
    }
    private static List<LevelOfDetail> getLods2000(){
        List<LevelOfDetail> lods = new ArrayList<LevelOfDetail>();
        lods.add(new LevelOfDetail(1, 0.7031249999891485, 2.958293554545656E8));
        lods.add(new LevelOfDetail(2, 0.35156249999999994, 1.479146777272828E8));
        lods.add(new LevelOfDetail(3, 0.17578124999999997, 7.39573388636414E7));
        lods.add(new LevelOfDetail(4, 0.08789062500000014, 3.69786694318207E7));
        lods.add(new LevelOfDetail(5, 0.04394531250000007, 1.848933471591035E7));
        lods.add(new LevelOfDetail(6, 0.021972656250000007, 9244667.357955175));
        lods.add(new LevelOfDetail(7, 0.01098632812500002, 4622333.678977588));
        lods.add(new LevelOfDetail(8, 0.00549316406250001, 2311166.839488794));
        lods.add(new LevelOfDetail(9, 0.0027465820312500017, 1155583.419744397));
        lods.add(new LevelOfDetail(10, 0.0013732910156250009, 577791.7098721985));
        lods.add(new LevelOfDetail(11, 0.000686645507812499, 288895.85493609926));
        lods.add(new LevelOfDetail(12, 0.0003433227539062495, 144447.92746804963));
        lods.add(new LevelOfDetail(13, 0.00017166137695312503, 72223.96373402482));
        lods.add(new LevelOfDetail(14, 0.00008583068847656251, 36111.98186701241));
        lods.add(new LevelOfDetail(15, 0.000042915344238281406, 18055.990933506204));
        lods.add(new LevelOfDetail(16, 0.000021457672119140645, 9027.995466753102));
        lods.add(new LevelOfDetail(17, 0.000010728836059570307, 4513.997733376551));
        lods.add(new LevelOfDetail(18, 0.000005364418029785169, 2256.998866688275));
        return lods;
    }

    public static TianDiTuLayerInfo getLayerInfo(int layerType) {
        TianDiTuLayerInfo layerInfo = new TianDiTuLayerInfo();
        switch (layerType) {
            case TianDiTuLayerTypes.TIANDITU_IMAGE_2000:
                layerInfo.setUrl(LayerInfoFactory.URL_IMAGE_2000);
                layerInfo.setLayerName(LayerInfoFactory.LAYER_NAME_IMAGE);
                break;
            case TianDiTuLayerTypes.TIANDITU_IMAGE_ANNOTATION_CHINESE_2000:
                layerInfo
                        .setUrl(LayerInfoFactory.URL_IMAGE_ANNOTATION_CHINESE_2000);
                layerInfo
                        .setLayerName(LayerInfoFactory.LAYER_NAME_IMAGE_ANNOTATION_CHINESE);
                break;
            case TianDiTuLayerTypes.TIANDITU_IMAGE_ANNOTATION_ENGLISH_2000:
                layerInfo
                        .setUrl(LayerInfoFactory.URL_IMAGE_ANNOTATION_ENGLISH_2000);
                layerInfo
                        .setLayerName(LayerInfoFactory.LAYER_NAME_IMAGE_ANNOTATION_ENGLISH);
                break;
            case TianDiTuLayerTypes.TIANDITU_IMAGE_ANNOTATION_CHINESE_MERCATOR:
                layerInfo
                        .setUrl(LayerInfoFactory.URL_IMAGE_ANNOTATION_CHINESE_MERCATOR);
                layerInfo
                        .setLayerName(LayerInfoFactory.LAYER_NAME_IMAGE_ANNOTATION_CHINESE);
                break;
            case TianDiTuLayerTypes.TIANDITU_IMAGE_ANNOTATION_ENGLISH_MERCATOR:
                layerInfo
                        .setUrl(LayerInfoFactory.URL_IMAGE_ANNOTATION_ENGLISH_MERCATOR);
                layerInfo
                        .setLayerName(LayerInfoFactory.LAYER_NAME_IMAGE_ANNOTATION_ENGLISH);
                break;
            case TianDiTuLayerTypes.TIANDITU_IMAGE_MERCATOR:
                layerInfo.setUrl(LayerInfoFactory.URL_IMAGE_MERCATOR);
                layerInfo.setLayerName(LayerInfoFactory.LAYER_NAME_IMAGE);
                break;
            case TianDiTuLayerTypes.TIANDITU_VECTOR_2000:
                layerInfo.setUrl(LayerInfoFactory.URL_VECTOR_2000);
                layerInfo.setLayerName(LayerInfoFactory.LAYER_NAME_VECTOR);
                break;
            case TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_CHINESE_2000:
                layerInfo
                        .setUrl(LayerInfoFactory.URL_VECTOR_ANNOTATION_CHINESE_2000);
                layerInfo
                        .setLayerName(LayerInfoFactory.LAYER_NAME_VECTOR_ANNOTATION_CHINESE);
                break;
            case TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_ENGLISH_2000:
                layerInfo
                        .setUrl(LayerInfoFactory.URL_VECTOR_ANNOTATION_ENGLISH_2000);
                layerInfo
                        .setLayerName(LayerInfoFactory.LAYER_NAME_VECTOR_ANNOTATION_ENGLISH);
                break;
            case TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_CHINESE_MERCATOR:
                layerInfo
                        .setUrl(LayerInfoFactory.URL_VECTOR_ANNOTATION_CHINESE_MERCATOR);
                layerInfo
                        .setLayerName(LayerInfoFactory.LAYER_NAME_VECTOR_ANNOTATION_CHINESE);
                break;
            case TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_ENGLISH_MERCATOR:
                layerInfo
                        .setUrl(LayerInfoFactory.URL_VECTOR_ANNOTATION_ENGLISH_MERCATOR);
                layerInfo
                        .setLayerName(LayerInfoFactory.LAYER_NAME_VECTOR_ANNOTATION_ENGLISH);
                break;
            case TianDiTuLayerTypes.TIANDITU_VECTOR_MERCATOR:
                layerInfo.setUrl(LayerInfoFactory.URL_VECTOR_MERCATOR);
                layerInfo.setLayerName(LayerInfoFactory.LAYER_NAME_VECTOR);
                break;
            case TianDiTuLayerTypes.TIANDITU_TERRAIN_2000:
                layerInfo.setUrl(LayerInfoFactory.URL_TERRAIN_2000);
                layerInfo.setLayerName(LayerInfoFactory.LAYER_NAME_TERRAIN);
                break;
            case TianDiTuLayerTypes.TIANDITU_TERRAIN_ANNOTATION_CHINESE_2000:
                layerInfo
                        .setUrl(LayerInfoFactory.URL_TERRAIN_ANNOTATION_CHINESE_2000);
                layerInfo
                        .setLayerName(LayerInfoFactory.LAYER_NAME_TERRAIN_ANNOTATION_CHINESE);
                break;
            case TianDiTuLayerTypes.TIANDITU_TERRAIN_MERCATOR:
                layerInfo.setUrl(LayerInfoFactory.URL_TERRAIN_MERCATOR);
                layerInfo.setLayerName(LayerInfoFactory.LAYER_NAME_TERRAIN);
                break;
            case TianDiTuLayerTypes.TIANDITU_TERRAIN_ANNOTATION_CHINESE_MERCATOR:
                layerInfo
                        .setUrl(LayerInfoFactory.URL_TERRAIN_ANNOTATION_CHINESE_MERCATOR);
                layerInfo
                        .setLayerName(LayerInfoFactory.LAYER_NAME_TERRAIN_ANNOTATION_CHINESE);
                break;
        }
        handleLayerInfo(layerInfo, layerType);
        return layerInfo;
    }

    private static void handleLayerInfo(TianDiTuLayerInfo layerInfo,
                                        int layerType) {
        switch (layerType) {
            case TianDiTuLayerTypes.TIANDITU_IMAGE_2000:
            case TianDiTuLayerTypes.TIANDITU_IMAGE_ANNOTATION_CHINESE_2000:
            case TianDiTuLayerTypes.TIANDITU_IMAGE_ANNOTATION_ENGLISH_2000:
            case TianDiTuLayerTypes.TIANDITU_VECTOR_2000:
            case TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_CHINESE_2000:
            case TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_ENGLISH_2000:
            case TianDiTuLayerTypes.TIANDITU_TERRAIN_2000:
            case TianDiTuLayerTypes.TIANDITU_TERRAIN_ANNOTATION_CHINESE_2000:
                layerInfo.setOrigin(LayerInfoFactory.ORIGIN_2000);
                layerInfo.setSrid(LayerInfoFactory.SRID_2000);
                layerInfo.setxMin(LayerInfoFactory.X_MIN_2000);
                layerInfo.setyMin(LayerInfoFactory.Y_MIN_2000);
                layerInfo.setxMax(LayerInfoFactory.X_MAX_2000);
                layerInfo.setyMax(LayerInfoFactory.Y_MAX_2000);
                layerInfo.setScales(LayerInfoFactory.SCALES);
                layerInfo.setResolutions(LayerInfoFactory.RESOLUTIONS_2000);
                layerInfo.setTileMatrixSet(LayerInfoFactory.TILE_MATRIX_SET_2000);
                layerInfo.setLods(getLods2000());
                break;
            case TianDiTuLayerTypes.TIANDITU_IMAGE_ANNOTATION_CHINESE_MERCATOR:
            case TianDiTuLayerTypes.TIANDITU_IMAGE_ANNOTATION_ENGLISH_MERCATOR:
            case TianDiTuLayerTypes.TIANDITU_IMAGE_MERCATOR:
            case TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_CHINESE_MERCATOR:
            case TianDiTuLayerTypes.TIANDITU_VECTOR_ANNOTATION_ENGLISH_MERCATOR:
            case TianDiTuLayerTypes.TIANDITU_VECTOR_MERCATOR:
            case TianDiTuLayerTypes.TIANDITU_TERRAIN_MERCATOR:
            case TianDiTuLayerTypes.TIANDITU_TERRAIN_ANNOTATION_CHINESE_MERCATOR:
                layerInfo.setOrigin(LayerInfoFactory.ORIGIN_MERCATOR);
                layerInfo.setSrid(LayerInfoFactory.SRID_MERCATOR);
                layerInfo.setxMin(LayerInfoFactory.X_MIN_MERCATOR);
                layerInfo.setyMin(LayerInfoFactory.Y_MIN_MERCATOR);
                layerInfo.setxMax(LayerInfoFactory.X_MAX_MERCATOR);
                layerInfo.setyMax(LayerInfoFactory.Y_MAX_MERCATOR);
                layerInfo.setScales(LayerInfoFactory.SCALES);
                layerInfo.setResolutions(LayerInfoFactory.RESOLUTIONS_MERCATOR);
                layerInfo.setTileMatrixSet(LayerInfoFactory.TILE_MATRIX_SET_MERCATOR);
                layerInfo.setLods(getLods102100());
                break;
        }
    }
}
