plugins {
  `kotlin-dsl`
}

repositories {
  jcenter()
  mavenLocal()
  mavenCentral()
}

dependencies {
  implementation(group = "com.google.code.gson", name = "gson", version = "2.7")
  implementation("org.jsoup:jsoup:1.13.1")
}
