# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/yuanzheng/Documents/develop/adt-bundle-mac-x86_64-20131030/sdk/tools/proguard/proguard-android.txt
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

# -- Android Annotations --
-dontwarn org.springframework.**

# -- umeng --
-dontwarn com.umeng.**
-dontwarn com.alimama.mobile.**
-keep class com.umeng*.** {*; }
-keep class com.alimama.mobile*.** {*; }
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}
-keep public class com.dachu.shop.R$*{
public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

## -- Gson  --
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# -- keep eventbus lib --
-keepclassmembers class ** {
    public void onEvent*(**);
    public void onEventMainThread*(**);
    public void onEventBackgroundThread*(**);
    public void onEventBusAsync*(**);
}
-keepclassmembers class * extends de.greenrobot.event.util.ThrowableFailureEvent {
    public <init>(java.lang.Throwable);
}
-dontwarn de.greenrobot.event.util.*$Support
-dontwarn de.greenrobot.event.util.*$SupportManagerFragment

# -- keep support  --
-keep class android.support.**{*;}

# -- keep volley --
-dontwarn org.apache.**
-dontwarn com.android.volley.**
-keep class org.apache.commons.logging.**
-keep class com.android.volley.** { *; }
-keep interface com.android.volley.** { *; }
-keep class * implements com.android.volley.** { *; }

# -- keep Glide --
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}


# -- Remove log codes --
-assumenosideeffects class android.util.Log {
    public static *** e(...);
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** wtf(...);
}



# -- keep java bean --
# please keep your java bean class at here
