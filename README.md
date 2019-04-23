# arcgis-android-tianditu

[![Download](https://api.bintray.com/packages/wshunli/maven/arcgis-android-tianditu/images/download.svg)](https://bintray.com/wshunli/maven/arcgis-android-tianditu/_latestVersion)
[![Build Status](https://travis-ci.org/wshunli/arcgis-android-tianditu.svg?branch=master)](https://travis-ci.org/wshunli/arcgis-android-tianditu)
[![Author](https://img.shields.io/badge/Author-wshunli-0E7FBF.svg)](http://www.wshunli.com)
[![GitHub license](https://img.shields.io/github/license/wshunli/arcgis-android-tianditu.svg)](https://github.com/wshunli/arcgis-android-tianditu/blob/master/LICENSE)

基于 ArcGIS for Android 的天地图图层显示及缓存

![vector](screenshot/vector.jpg) | ![image](screenshot/image.jpg) | ![terrain](screenshot/terrain.jpg)
:-: | :-: | :-:
矢量 | 影像 | 地形

## 天地图授权

天地图于 2019年1月1日 停止对无开发许可（Key）使用应用程序开发接口或者调用在线服务的支持。

http://lbs.tianditu.gov.cn/authorization/authorization.html

请参考以上内容申请 token 值，以继续使用本依赖。

## 依赖

### ArcGIS for Android 依赖

在使用 arcgis-android-tianditu 之前需要添加 ArcGIS 仓库 以及 ArcGIS for Android 依赖。

```groovy
repositories {
    jcenter()
    // Add the Esri public Bintray Maven repository
    maven {
        url 'https://esri.bintray.com/arcgis'
    }
}
dependencies {
    // Add ArcGIS Runtime SDK for Android dependency
    implementation 'com.esri.arcgisruntime:arcgis-android:100.3.0'
}
```

更多信息可参考: [ArcGIS for Android开发环境搭建](http://www.wshunli.com/posts/29ec97b7.html)

### arcgis-android-tianditu 依赖

arcgis-android-tianditu 已经发布至 jcenter ，确定项目已配置 jcenter 仓库即可。

```groovy
// 添加 arcgis-android-tianditu 依赖
dependencies {
    implementation 'com.wshunli.map:arcgis-android-tianditu:2.1.0'
}
```

**强烈建议使用最新版**，查看更多版本： [arcgis-android-tianditu releases](https://github.com/wshunli/arcgis-android-tianditu/releases)

## 快速开始

### 声明权限

除 ArcGIS for Android 三个权限外，arcgis-android-tianditu 不需要额外权限:

``` XML
<uses-feature android:glEsVersion="0x00020000" android:required="true" />

<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

针对 Android 6.0+ 以上版本, 注意运行时权限请求。

### 简单示例

``` Java
MapView mMapView = findViewById(R.id.mapView);
ArcGISMap map = new ArcGISMap();
TianDiTuLayer vec_c = new TianDiTuLayerBuilder()
    .setLayerType(TianDiTuLayerTypes.TIANDITU_VECTOR_MERCATOR)
    .setToken("2ce94f67e58faa24beb7cb8a09780552")
    .build();
map.getBasemap().getBaseLayers().add(vec_c);
mMapView.setMap(map);
```

### 缓存切片

指定缓存位置即可缓存切片。

``` Java
MapView mMapView = findViewById(R.id.mapView);
ArcGISMap map = new ArcGISMap();
String cachePath = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/TianDiTu100Cache";
TianDiTuLayer vec_c = new TianDiTuLayerBuilder()
    .setLayerType(TianDiTuLayerTypes.TIANDITU_VECTOR_MERCATOR)
    .setCachePath(cachePath)
    .setToken("2ce94f67e58faa24beb7cb8a09780552")
    .build();
map.getBasemap().getBaseLayers().add(vec_c);
mMapView.setMap(map);
```

## 更多

更多信息可以查看 [示例](https://github.com/wshunli/arcgis-android-tianditu/tree/master/sample)

## 10.X 版本

（不推荐）针对 ArcGIS for Android 10.2.9 及以前的版本，请参考 [README.10.X](README.10.X.md) 


## License

    Copyright 2017 wshunli

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
