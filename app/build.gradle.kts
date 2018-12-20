import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "org.gradle.kotlin.dsl.samples.androidstudio"
        minSdkVersion(15)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }
    packagingOptions {
        exclude("META-INF/project.properties")
        exclude("**/javamoney.properties")
    }
}

dependencies {
    val appcompatVersion = "28.0.0"
    val constraintLayoutVersion = "1.1.3"
    val junitVersion = "4.12"
    val testRunnerVersion = "1.0.2"
    val espressoVersion = "3.0.2"
    val ankoVersion = "0.10.8"
    val javamoneyVersion = "1.1"
    val koinVersion = "1.0.2"

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation("com.android.support:appcompat-v7:$appcompatVersion")
    implementation("com.android.support.constraint:constraint-layout:$constraintLayoutVersion")
    implementation("org.jetbrains.anko:anko:$ankoVersion")
    implementation("org.koin:koin-android:$koinVersion")
    implementation("org.javamoney:moneta:$javamoneyVersion")
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("com.android.support.test:runner:$testRunnerVersion")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:$espressoVersion")
}