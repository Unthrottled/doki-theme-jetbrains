package io.unthrottled.doki.settings.actors

import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.editor.colors.impl.EditorColorsManagerImpl
import io.unthrottled.doki.config.ThemeConfig
import io.unthrottled.doki.service.ConsoleFontService

object ConsoleFontActor {
  fun enableCustomFontSize(enabled: Boolean, consoleFontName: String) {
    val previousEnablement = ThemeConfig.instance.isOverrideConsoleFont
    ThemeConfig.instance.isOverrideConsoleFont = enabled
    val previousFontSize = ThemeConfig.instance.consoleFontName
    ThemeConfig.instance.consoleFontName = consoleFontName
    ConsoleFontService.applyConsoleFont()

    val fontSizeChanged = previousFontSize != consoleFontName
    val enablementChanged = previousEnablement != enabled
    if (fontSizeChanged || enablementChanged) {
      refreshConsole()
    }
  }

  fun refreshConsole() {
    (EditorColorsManager.getInstance() as EditorColorsManagerImpl).schemeChangedOrSwitched(null)
  }
}
