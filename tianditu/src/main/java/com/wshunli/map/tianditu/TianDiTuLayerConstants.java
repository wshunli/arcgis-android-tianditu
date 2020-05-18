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
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;

import java.util.Arrays;
import java.util.List;

/**
 * author : wshunli
 * email : wshunli@qq.com
 * date : 2020/5/17 18:22
 * description ： 天地图图层相关常量
 */
class TianDiTuLayerConstants {

    // 子域名
    static final List<String> SUB_DOMAIN = Arrays.asList("t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7");

    // 切片相关信息
    static final int TILE_DPI = 96;
    static final TileInfo.ImageFormat TILE_FORMAT = TileInfo.ImageFormat.PNG24;
    static final int TILE_WIDTH = 256, TILE_HEIGHT = 256;

    // 国家 2000 坐标系
    static final SpatialReference SRID_2000 = SpatialReference.create(4490);
    private static final double X_MIN_2000 = -180;
    private static final double Y_MIN_2000 = -90;
    private static final double X_MAX_2000 = 180;
    private static final double Y_MAX_2000 = 90;
    static final Point ORIGIN_2000 = new Point(-180, 90, SRID_2000);
    static final Envelope ENVELOPE_2000 = new Envelope(X_MIN_2000, Y_MIN_2000, X_MAX_2000, Y_MAX_2000, SRID_2000);

    // 墨卡托投影坐标系
    static final SpatialReference SRID_MERCATOR = SpatialReference.create(102100);
    private static final double X_MIN_MERCATOR = -20037508.3427892;
    private static final double Y_MIN_MERCATOR = -20037508.3427892;
    private static final double X_MAX_MERCATOR = 20037508.3427892;
    private static final double Y_MAX_MERCATOR = 20037508.3427892;
    static final Point ORIGIN_MERCATOR = new Point(-20037508.3427892, 20037508.3427892, SRID_MERCATOR);
    static final Envelope ENVELOPE_MERCATOR = new Envelope(X_MIN_MERCATOR, Y_MIN_MERCATOR, X_MAX_MERCATOR, Y_MAX_MERCATOR, SRID_MERCATOR);

    // 缩放等级边界值
    static final int LEVEL_MIN = 1, LEVEL_MAX = 18;
    static final double[] SCALES = {
            2.958293554545656E8, 1.479146777272828E8, 7.39573388636414E7, 3.69786694318207E7,
            1.848933471591035E7, 9244667.357955175, 4622333.678977588, 2311166.839488794,
            1155583.419744397, 577791.7098721985, 288895.85493609926, 144447.92746804963,
            72223.96373402482, 36111.98186701241, 18055.990933506204, 9027.995466753102,
            4513.997733376551, 2256.998866688275, 1127.2338602399827, 563.6169301199914
    };
    static final double[] RESOLUTIONS_2000 = {
            0.7031249999891485, 0.35156249999999994, 0.17578124999999997, 0.08789062500000014,
            0.04394531250000007, 0.021972656250000007, 0.01098632812500002, 0.00549316406250001,
            0.0027465820312500017, 0.0013732910156250009, 0.000686645507812499, 0.0003433227539062495,
            0.00017166137695312503, 0.00008583068847656251, 0.000042915344238281406, 0.000021457672119140645,
            0.000010728836059570307, 0.000005364418029785169, 2.682209014892579e-006, 1.3411045074462895e-006
    };

    static final double[] RESOLUTIONS_MERCATOR = {
            78271.51696402048, 39135.75848201024, 19567.87924100512, 9783.93962050256,
            4891.96981025128, 2445.98490512564, 1222.99245256282, 611.49622628141,
            305.748113140705, 152.8740565703525, 76.43702828517625, 38.21851414258813,
            19.109257071294063, 9.554628535647032, 4.777314267823516, 2.388657133911758,
            1.194328566955879, 0.5971642834779395, 0.2985821417389698, 0.1492910708694849
    };

}
