# MyAndroidUtils
my utilities collection for android development

```java
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    implementation "org.jetbrains.anko:anko-commons:$anko_version"
    //noinspection GradleCompatible
    implementation "com.android.support:appcompat-v7:$support_version"
    implementation "com.android.support:design:$support_version"
    implementation "com.android.support:support-core-ui:$support_version"
    implementation "com.android.support:cardview-v7:$support_version"
    implementation "com.android.support:recyclerview-v7:$support_version"
    implementation "com.android.support:support-v4:$support_version"
    implementation 'com.android.support:multidex:1.0.3'

    // GOOGLE
    implementation "com.google.code.gson:gson:2.8.5"
    implementation 'com.google.firebase:firebase-core:16.0.9'
    implementation "com.google.firebase:firebase-messaging:18.0.0"
    implementation 'com.google.android.gms:play-services-auth:16.0.1'

    // NETWORK
    implementation "com.squareup.retrofit2:retrofit:2.4.0"
    implementation "com.squareup.retrofit2:converter-gson:2.4.0"

    // DEBUGGER
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    debugImplementation "com.readystatesoftware.chuck:library:1.1.0"
    releaseImplementation "com.readystatesoftware.chuck:library-no-op:1.1.0"

    implementation "com.github.GrenderG:Toasty:1.3.0"

    implementation 'com.pixplicity.easyprefs:library:1.9.0'

    implementation 'com.github.delight-im:Android-SimpleLocation:v1.0.1'

    // Image
    implementation 'de.hdodenhof:circleimageview:3.0.0'
    implementation "com.github.firdausmaulan:GlideSlider:$glide_slider_version"
    implementation 'com.github.bumptech.glide:glide:4.9.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'
    implementation 'com.github.jkwiecien:EasyImage:1.3.1'
    implementation "id.zelory:compressor:2.1.0"
    implementation 'com.github.jrvansuita:GaussianBlur:1.0.3'
    implementation 'com.theartofdev.edmodo:android-image-cropper:2.7.0'

    // Multi Screen
    implementation 'com.intuit.sdp:sdp-android:1.0.6'
    implementation 'com.intuit.ssp:ssp-android:1.0.6'

    // Page Indicator
    implementation 'com.romandanylyk:pageindicatorview:1.0.2@aar'

    // Progress Bar
    implementation 'com.wang.avi:library:2.1.3'

    // Pretty Time
    implementation 'org.ocpsoft.prettytime:prettytime:4.0.1.Final'
    
    // Facebook Login
    implementation 'com.facebook.android:facebook-login:4.42.0'

    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
}
```
