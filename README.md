 Time Trackor Application
---------------------------------------------
Application name: Client Clock
----------------------------------------------------------
https://github.com/VCCT-OPSC7311-G1-2024/Emmanuel-Mukwakungu_ST10082911_PM_Client_Clock.git

Group - ST10082911(group leader), ST10082700, ST10114326, ST10205167, ST10069127

--------------------------------------------------------------------------------------
Gradle must be synced before running the application. 


Feature 1 - Daily Work
-----------------------------------------------------------------------
the daily work feature allows you to set the minimum and maximum hours you would like to 
work on your goal


Feature 2 - Photograph Attachments for Timesheet Entries
-----------------------------------------------------------------------
out application Offers the option to add photographs to timesheet entries, adding a unique 
identifier for each project or entry.

Feature 3 - Timer
-----------------------------------------------------------------------
the timer can be used to keep time of work that is being conducted. it consists of a start and restet button


--Requirements--
-----------------------------------------------------------------------
Android Studio Iguana | 2023.2.1

minSdk 26

target Sdk 34

------------------------------------------------------------------------
--dependencies--
------------------------------------------------------------------------

    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-appcheck")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.31")
