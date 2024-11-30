plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
<<<<<<< HEAD
}

android {
    namespace = "com.example.healthappproject"
    compileSdk = 34
=======
    alias(libs.plugins.google.gms.google.services)
    id("androidx.navigation.safeargs.kotlin")
}
android {
    namespace = "com.example.healthappproject"
    compileSdk = 35
>>>>>>> master

    defaultConfig {
        applicationId = "com.example.healthappproject"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
<<<<<<< HEAD
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
=======
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
>>>>>>> master
    }
    viewBinding {
        enable = true
    }
}

dependencies {

<<<<<<< HEAD
=======
    implementation(libs.retrofit)
    implementation(libs.converter.gson)


>>>>>>> master
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
<<<<<<< HEAD
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
=======
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.firebase.database)
    implementation(libs.firebase.database.v2100)
    implementation("com.google.firebase:firebase-database:21.0.0'")
    implementation(libs.glide)
    implementation (libs.androidx.viewpager2)
    annotationProcessor(libs.compiler)


}

apply(plugin = "com.google.gms.google-services")
>>>>>>> master
