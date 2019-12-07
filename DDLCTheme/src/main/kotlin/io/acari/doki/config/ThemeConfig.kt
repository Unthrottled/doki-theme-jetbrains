package io.acari.doki.config

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil.copyBean
import com.intellij.util.xmlb.XmlSerializerUtil.createCopy
import io.acari.doki.stickers.StickerLevel
import io.acari.doki.themes.DokiThemes

@State(
  name = "DokiDokiThemeConfig",
  storages = [Storage("doki_doki_theme.xml")]
)
class ThemeConfig : PersistentStateComponent<ThemeConfig>, Cloneable {
  companion object {
    val instance: ThemeConfig
      get() = ServiceManager.getService(ThemeConfig::class.java)
  }

  var version: String = "0.0.0"
  var chibiLevel: String = StickerLevel.ON.name
  var stickerLevel: String = StickerLevel.ON.name
  var selectedTheme: String = DokiThemes.MONIKA_LIGHT // todo : set dis
  var isDokiFileColors: Boolean = false
  var processedLegacyStickers: Boolean = false
  var processedLegacyStartup: Boolean = false

  override fun getState(): ThemeConfig? =
    createCopy(this)

  override fun loadState(state: ThemeConfig) {
    copyBean(state, this)
  }

  fun asJson(): Map<String, Any> = mapOf(
    "version" to version,
    "chibiLevel" to chibiLevel,
    "selectedTheme" to selectedTheme,
    "isDokiFileColors" to isDokiFileColors,
    "processedLegacyStickers" to processedLegacyStickers,
    "processedLegacyStartup" to processedLegacyStartup
  )

  val currentStickerLevel: StickerLevel
    get() {
      if (processedLegacyStickers) {
        processedLegacyStickers = true
        stickerLevel = chibiLevel
      }
      return StickerLevel.valueOf(stickerLevel)
    }
}