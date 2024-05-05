plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    id("com.google.devtools.ksp")
    id("com.google.protobuf") version "0.9.4"
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.trbofficerandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.trbofficerandroid"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        create("staging") {
            initWith(getByName("debug"))
            isDebuggable = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    protobuf {
        protoc {
            artifact = "com.google.protobuf:protoc:3.25.1"
        }
        plugins {
            create("java") {
                artifact = "com.google.protobuf:protoc-gen-javalite:3.0.0"
            }
            create("grpc") {
                artifact = "io.grpc:protoc-gen-grpc-java:1.62.2"
            }
        }
        generateProtoTasks {
            all().forEach { task ->
                task.builtins {
                    create("java") {
                        option("lite")
                    }
                }
                task.plugins {
                    create("grpc") {
                        option("lite")
                    }
                }
            }
        }
    }
}

dependencies {

    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.7")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Koin
    implementation("io.insert-koin:koin-androidx-compose:3.5.3")

    // OkHttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    // Json Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")

    // Room
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    // Rebugger
    implementation("io.github.theapache64:rebugger:1.0.0-rc02")

    // Grpc
    implementation("io.grpc:grpc-okhttp:1.62.2")
    implementation("io.grpc:grpc-protobuf-lite:1.62.2")
    implementation("io.grpc:grpc-stub:1.62.2")
    implementation("org.apache.tomcat:annotations-api:6.0.53")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Splash Screen API
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-messaging")

    // PlayServices auth
    implementation("com.google.android.gms:play-services-auth:21.0.0")

    // Coil Compose
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Chrome Custom Tabs
    implementation("androidx.browser:browser:1.8.0")

    // Yandex Ads
    implementation("com.yandex.android:mobileads:7.0.0")

    // Recycler View
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}