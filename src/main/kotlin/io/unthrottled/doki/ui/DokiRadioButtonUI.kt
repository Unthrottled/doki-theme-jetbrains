package io.unthrottled.doki.ui

import com.intellij.ide.ui.laf.darcula.ui.DarculaRadioButtonUI
import io.unthrottled.doki.icon.IconLookup.getIcon
import java.awt.Graphics2D
import java.awt.Rectangle
import javax.swing.AbstractButton
import javax.swing.JComponent
import javax.swing.plaf.ComponentUI

@Suppress("ACCIDENTAL_OVERRIDE")
class DokiRadioButtonUI : DarculaRadioButtonUI() {

  companion object {
    @JvmStatic
    @Suppress("UNUSED", "UNUSED_PARAMETER")
    fun createUI(c: JComponent?): ComponentUI = DokiRadioButtonUI()
  }

  override fun paintIcon(c: JComponent, g: Graphics2D?, viewRect: Rectangle?, iconRect: Rectangle) {
    val icon = getIcon("radio", (c as AbstractButton).isSelected, c.hasFocus(), c.isEnabled())
    icon.paintIcon(c, g, iconRect.x, iconRect.y)
  }
}
