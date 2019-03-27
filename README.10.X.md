# arcgis-android-tianditu (10.X)

[![Download](https://api.bintray.com/packages/wshunli/maven/arcgis-android-tianditu/images/download.svg)](https://bintray.com/wshunli/maven/arcgis-android-tianditu/_latestVersion)
[![Build Status](https://travis-ci.org/wshunli/arcgis-android-tianditu.svg?branch=master)](https://travis-ci.org/wshunli/arcgis-android-tianditu)
[![Author](https://img.shields.io/badge/Author-wshunli-0E7FBF.svg)](http://www.wshunli.com)
[![GitHub license](https://img.shields.io/github/license/wshunli/arcgis-android-tianditu.svg)](https://github.com/wshunli/arcgis-android-tianditu/blob/master/LICENSE)

基于 ArcGIS for Android 的天地图图层显示及缓存

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
    implementation 'com.esri.arcgis.android:arcgis-android:10.2.9'
}
```

Packaging 配置：

```groovy
packagingOptions {
    exclude 'META-INF/LGPL2.1'
    exclude 'META-INF/LICENSE'
    exclude 'META-INF/NOTICE'
}
```

更多信息可参考: [ArcGIS for Android开发环境搭建](http://www.wshunli.com/posts/29ec97b7.html)

### arcgis-android-tianditu 依赖

arcgis-android-tianditu 已经发布至 jcenter ，确定项目已配置 jcenter 仓库即可。

```groovy
repositories {
    jcenter()
}
    // 添加 arcgis-android-tianditu 依赖
dependencies {
    implementation 'com.wshunli.map:arcgis-android-tianditu:1.1.0'
}
```

查看更多版本： [arcgis-android-tianditu releases](https://github.com/wshunli/arcgis-android-tianditu/releases)

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
MapView mMapView = (MapView) findViewById(R.id.map);
TianDiTuLayer vec_c = new TianDiTuLayer(TianDiTuLayerTypes.TIANDITU_VECTOR_MERCATOR);
mMapView.addLayer(vec_c);
```

### 缓存切片

指定缓存位置即可缓存切片。

``` Java
MapView mMapView = (MapView) findViewById(R.id.map);
String cachePath = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/TianDiTuCache";
TianDiTuLayer vec_c = new TianDiTuLayer(TianDiTuLayerTypes.TIANDITU_VECTOR_MERCATOR, cachePath);
mMapView.addLayer(vec_c);
```

## 更多

更多信息可以查看 [示例](https://github.com/wshunli/arcgis-android-tianditu/tree/master/sample)

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
