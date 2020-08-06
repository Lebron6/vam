# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\MGWorkSpace\ProgramFiles\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Retain generic type information for use by reflection by converters and adapters.

 -optimizationpasses 5          # 指定代码的压缩级别
    -dontusemixedcaseclassnames   # 是否使用大小写混合
    -dontpreverify                 # 混淆时是否做预校验
    -verbose                        # 混淆时是否记录日志

    -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法

    -keep public class * extends android.app.Activity      # 所有activity的子类不要去混淆  
    -keep public class * extends android.app.Fragment      # 所有activity的子类不要去混淆  
    -keep public class * extends android.app.Application   # 保持哪些类不被混淆
    -keep public class * extends android.app.Service       # 保持哪些类不被混淆
    -keep public class * extends android.app.widget.RelativeLayout       # 保持哪些类不被混淆
    -keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
    -keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
    -keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
    -keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
    -keep public class * extends android.widget.BaseAdapter

    # support-v7-appcompat
    -keep public class android.support.v7.widget.** { *; }
    -keep public class android.support.v7.internal.widget.** { *; }
    -keep public class android.support.v7.internal.view.menu.** { *; }
    -keep public class * extends android.support.v4.view.ActionProvider {
        public <init>(android.content.Context);
    }
    # support-design
    -dontwarn android.support.design.**
    -keep class android.support.design.** { *; }
    -keep interface android.support.design.** { *; }
    -keep public class android.support.design.R$* { *; }

#retrofit2
-keepattributes Signature
# Retain service method parameters.
-keepclassmembernames,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# EventBus
#-keepattributes *Annotation*
#-keepclassmembers class ** {
#    @org.greenrobot.eventbus.Subscribe <methods>;
#}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

#okhttp3
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

#AgentWeb
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**
-keepclassmembers class com.just.agentweb.sample.common.AndroidInterface{ *; }

#Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

# 极光
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }

#权限
-dontwarn com.zhy.m.**
-keep class com.zhy.m.** {*;}
-keep interface com.zhy.m.** { *; }
-keep class **$$PermissionProxy { *; }

#bean
-keep class com.dy.vam.bean.** { *; }

#状态栏
-keep class com.gyf.barlibrary.* {*;}