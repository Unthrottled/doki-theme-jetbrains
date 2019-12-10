/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

/*
 * Created by JFormDesigner on Fri Jun 29 18:52:29 IDT 2018
 */

package io.acari.DDLC.wizard.steps

import com.intellij.ide.customize.AbstractCustomizeWizardStep
import com.intellij.ui.components.JBScrollPane
import io.acari.DDLC.AnthroThemes.CLEO
import io.acari.DDLC.AnthroThemes.ELENIEL
import io.acari.DDLC.AnthroThemes.NEERA
import io.acari.DDLC.AnthroThemes.SANYA
import io.acari.DDLC.AnthroThemes.SYRENA
import io.acari.DDLC.AnthroThemes.WYLA
import io.acari.DDLC.DDLCThemes
import io.acari.DDLC.actions.DarkMode
import io.acari.DDLC.actions.themes.anthro.*
import io.acari.DDLC.actions.themes.literature.club.*
import io.acari.DDLC.chibi.ChibiOrchestrator
import io.acari.DDLC.themes.AnthroTheme
import io.acari.DDLC.wizard.ThemeSuite
import io.acari.DDLC.wizard.WizardConfig
import net.miginfocom.swing.MigLayout
import java.awt.BorderLayout
import java.awt.Dimension
import java.util.*
import javax.swing.*
import javax.swing.border.EmptyBorder

class ThemeItem(
    val themeAction: ClubMemberThemeAction,
    val themeName: String,
    val theme: AnthroTheme
)

class DDLCWizardThemesPanel : AbstractCustomizeWizardStep() {

  private val menagerieThemes = listOf(
      ThemeItem(
          CleoThemeAction(),
          "Cleo",
          CLEO
      ),
      ThemeItem(
          ElenielThemeAction(),
          "Eleniel",
          ELENIEL
      ),
      ThemeItem(
          NeeraThemeAction(),
          "Neera",
          NEERA
      ),
      ThemeItem(
          SanyaThemeAction(),
          "Sanya",
          SANYA
      ),
      ThemeItem(
          SyrenaThemeAction(),
          "Syrena",
          SYRENA
      ),
      ThemeItem(
          WylaThemeAction(),
          "Wyla",
          WYLA
      )
  )


  private val justMonikaThemeAction = JustMonikaThemeAction()

  private val sayoriThemeAction = SayoriThemeAction()

  private val natsukiThemeAction = NatsukiThemeAction()

  private val yuriThemeAction = YuriThemeAction()

  private var scrollPane: JBScrollPane? = null
  private var grid: JPanel? = null
  private var justMonikaPanel: JPanel? = null
  private var justMonikaButton: JRadioButton? = null
  private var justMonikaLabel: JLabel? = null
  private var sayoriLayout: JPanel? = null
  private var sayoriButton: JRadioButton? = null
  private var sayoriLabel: JLabel? = null
  private var natsukiPanel: JPanel? = null
  private var natsukiButton: JRadioButton? = null
  private var natsukiLabel: JLabel? = null
  private var yuriPanel: JPanel? = null
  private var yuriButton: JRadioButton? = null
  private var yuriLabel: JLabel? = null
  private var onlyMonikaPanel: JPanel? = null
  private var onlyMonikaButton: JRadioButton? = null
  private var onlyMonikaLabel: JLabel? = null
  private var deletedCharacterLayout: JPanel? = null
  private var deletedCharacterButton: JRadioButton? = null
  private var deletedCharacterLabel: JLabel? = null
  private var onlyPlayWithMePanel: JPanel? = null
  private var onlyPlayWithMeButton: JRadioButton? = null
  private var onlyPlayWithMeLabel: JLabel? = null
  private var edgyPanel: JPanel? = null
  private var edgyButton: JRadioButton? = null
  private var edgyLabel: JLabel? = null

  init {
    initComponents()
  }

  override fun beforeShown(forward: Boolean) {
    super.beforeShown(forward)
    initRadioButton()
    initializeView()
  }

  private fun initRadioButton() {
    val darkMode = DarkMode.isOn()
    when (ChibiOrchestrator.currentActiveTheme()) {
      DDLCThemes.MONIKA ->
        if (darkMode) onlyMonikaButtonActionPerformed()
        else justMonikaButtonActionPerformed()
      DDLCThemes.SAYORI ->
        if (darkMode) deletedCharacterButtonActionPerformed()
        else sayoriButtonActionPerformed()
      DDLCThemes.NATSUKI ->
        if (darkMode) onlyPlayWithMeButtonActionPerformed()
        else natsukiButtonActionPerformed()
      DDLCThemes.YURI ->
        if (darkMode) edgyButtonActionPerformed()
        else yuriButtonActionPerformed()
    }

  }

  override fun getTitle(): String {
    return "Themes"
  }

  override fun getHTMLHeader(): String {
    return when (WizardConfig.chosenThemeSuite) {
      ThemeSuite.LITERATURE_CLUB -> """<html>
            <body>
            <h2>Choose your <strong>favorite</strong> Club Member: </h2>&nbsp;
            <h3>Sayori, Natsuki, Yuri, or <strong>just Monika</strong>.</h3>
            </body></html>""".trimIndent()
      ThemeSuite.MENAGERIE -> """<html>
            <body>
            <h2>Choose your <strong>favorite</strong> girl: </h2>&nbsp;
            <h3>${menagerieThemes.map { it.themeName }.joinToString(", ")}.</h3>
            </body></html>""".trimIndent()
    }
  }

  private fun justMonikaButtonActionPerformed() {
    DarkMode.turnOff()
    justMonikaThemeAction.selectionActivation()
    justMonikaButton!!.isSelected = true
  }

  private fun onlyMonikaButtonActionPerformed() {
    DarkMode.turnOn()
    justMonikaThemeAction.selectionActivation()
    onlyMonikaButton!!.isSelected = true
  }

  private fun deletedCharacterButtonActionPerformed() {
    DarkMode.turnOn()
    sayoriThemeAction.selectionActivation()
    deletedCharacterButton!!.isSelected = true
  }

  private fun sayoriButtonActionPerformed() {
    DarkMode.turnOff()
    sayoriThemeAction.selectionActivation()
    sayoriButton!!.isSelected = true
  }

  private fun onlyPlayWithMeButtonActionPerformed() {
    DarkMode.turnOn()
    natsukiThemeAction.selectionActivation()
    onlyPlayWithMeButton!!.isSelected = true
  }

  private fun natsukiButtonActionPerformed() {
    DarkMode.turnOff()
    natsukiThemeAction.selectionActivation()
    natsukiButton!!.isSelected = true
  }

  private fun edgyButtonActionPerformed() {
    DarkMode.turnOn()
    yuriThemeAction.selectionActivation()
    edgyButton!!.isSelected = true
  }

  private fun yuriButtonActionPerformed() {
    DarkMode.turnOff()
    yuriThemeAction.selectionActivation()
    yuriButton!!.isSelected = true
  }

  private fun initComponents() {
    scrollPane = JBScrollPane()
    grid = JPanel()
    justMonikaPanel = JPanel()
    justMonikaButton = JRadioButton()
    justMonikaLabel = JLabel()
    sayoriLayout = JPanel()
    sayoriButton = JRadioButton()
    sayoriLabel = JLabel()
    natsukiPanel = JPanel()
    natsukiButton = JRadioButton()
    natsukiLabel = JLabel()
    yuriPanel = JPanel()
    yuriButton = JRadioButton()
    yuriLabel = JLabel()
    onlyMonikaPanel = JPanel()
    onlyMonikaButton = JRadioButton()
    onlyMonikaLabel = JLabel()
    deletedCharacterLayout = JPanel()
    deletedCharacterButton = JRadioButton()
    deletedCharacterLabel = JLabel()
    onlyPlayWithMePanel = JPanel()
    onlyPlayWithMeButton = JRadioButton()
    onlyPlayWithMeLabel = JLabel()
    edgyPanel = JPanel()
    edgyButton = JRadioButton()
    edgyLabel = JLabel()

    //======== this ========
    layout = BorderLayout()
    grid!!.maximumSize = Dimension(2147483647, 200)
    grid!!.layout = MigLayout(
        "flowy,insets 0,align left top",
        // columns
        "[left]" + "[grow,fill]",
        // rows
        "[grow,top]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]")

    scrollPane!!.border = null
    add(scrollPane!!, BorderLayout.CENTER)
    scrollPane!!.setViewportView(grid)
  }

  private fun initializeView() {
    grid!!.removeAll()
    val bundle = ResourceBundle.getBundle("messages.DDLCWizardBundle")
    run {
      run {
        when (WizardConfig.chosenThemeSuite) {
          ThemeSuite.LITERATURE_CLUB -> initializeLiteratureClub(bundle)
          ThemeSuite.MENAGERIE -> initializeMenagerie(bundle)
        }
      }
    }
    if (WizardConfig.chosenThemeSuite == ThemeSuite.LITERATURE_CLUB) {
      val selectedTheme = ButtonGroup()
      selectedTheme.add(justMonikaButton)
      selectedTheme.add(sayoriButton)
      selectedTheme.add(natsukiButton)
      selectedTheme.add(yuriButton)
      selectedTheme.add(onlyMonikaButton)
      selectedTheme.add(deletedCharacterButton)
      selectedTheme.add(onlyPlayWithMeButton)
      selectedTheme.add(edgyButton)
    }

  }

  private fun initializeMenagerie(bundle: ResourceBundle) {
    val selectedThemeButtonGroup = ButtonGroup()
    val activeTheme = ChibiOrchestrator.currentActiveTheme().value
    menagerieThemes.forEachIndexed { index, themeItem ->

      val menagerieThemePanel = JPanel()
      menagerieThemePanel.border = EmptyBorder(5, 5, 5, 5)
      menagerieThemePanel.layout = BoxLayout(menagerieThemePanel, BoxLayout.Y_AXIS)

      val menagerieThemeButton = JRadioButton()
      selectedThemeButtonGroup.add(menagerieThemeButton)
      menagerieThemeButton.isSelected = activeTheme == themeItem.theme
      menagerieThemeButton.text = themeItem.themeName
      menagerieThemeButton.horizontalAlignment = SwingConstants.LEFT
      menagerieThemeButton.actionCommand = bundle.getString("DDLCWizardThemesPanel.${themeItem.themeName}Button.actionCommand")
      menagerieThemeButton.addActionListener {
        menagerieThemeButton.isSelected = true
        themeItem.themeAction.selectionActivation()
      }
      menagerieThemePanel.add(menagerieThemeButton)

      val menagerieThemeLabel = JLabel()
      menagerieThemeLabel.icon = ImageIcon(javaClass.getResource("/wizard/anthro/${themeItem.themeName.toLowerCase()}.png"))
      menagerieThemeLabel.addMouseListener(createMouseListener {
        menagerieThemeButton.isSelected = true
        themeItem.themeAction.selectionActivation()
      })
      menagerieThemePanel.add(menagerieThemeLabel)
      val row = if ((index + 1) % 2 == 0) 1 else 0
      val column = Math.floorDiv(index, 2)
      grid!!.add(menagerieThemePanel, "cell ${row} ${column}")

    }

  }

  private fun initializeLiteratureClub(bundle: ResourceBundle) {
    //======== justMonikaPanel ========
    run {
      justMonikaPanel!!.border = EmptyBorder(5, 5, 5, 5)
      justMonikaPanel!!.layout = BoxLayout(justMonikaPanel, BoxLayout.Y_AXIS)

      //---- justMonikaButton ----
      justMonikaButton!!.text = "Just Monika"
      justMonikaButton!!.horizontalAlignment = SwingConstants.LEFT
      justMonikaButton!!.actionCommand = bundle.getString("DDLCWizardThemesPanel.justMonikaButton.actionCommand")
      justMonikaButton!!.addActionListener { this.justMonikaButtonActionPerformed() }
      justMonikaPanel!!.add(justMonikaButton)

      //---- justMonikaLabel ----
      justMonikaLabel!!.icon = ImageIcon(javaClass.getResource("/wizard/justMonika.png"))
      justMonikaLabel!!.addMouseListener(createMouseListener { this.justMonikaButtonActionPerformed() })
      justMonikaPanel!!.add(justMonikaLabel)
    }
    grid!!.add(justMonikaPanel!!, "cell 0 0")

    //======== onlyMonikaPanel ========
    run {
      onlyMonikaPanel!!.border = EmptyBorder(5, 5, 5, 5)
      onlyMonikaPanel!!.layout = BoxLayout(onlyMonikaPanel, BoxLayout.Y_AXIS)

      //---- onlyMonikaButton ----
      onlyMonikaButton!!.text = "Only Monika"
      onlyMonikaButton!!.horizontalAlignment = SwingConstants.LEFT
      onlyMonikaButton!!.actionCommand = bundle.getString("DDLCWizardThemesPanel.onlyMonikaButton.text")
      onlyMonikaButton!!.addActionListener { this.onlyMonikaButtonActionPerformed() }
      onlyMonikaPanel!!.add(onlyMonikaButton)

      //---- onlyMonikaLabel ----
      onlyMonikaLabel!!.icon = ImageIcon(javaClass.getResource("/wizard/onlyMonika.png"))
      onlyMonikaLabel!!.addMouseListener(createMouseListener { this.onlyMonikaButtonActionPerformed() })
      onlyMonikaPanel!!.add(onlyMonikaLabel)
    }
    grid!!.add(onlyMonikaPanel!!, "cell 1 0,align center center,grow 0 0")

    //======== sayoriLayout ========
    run {
      sayoriLayout!!.border = EmptyBorder(5, 5, 5, 5)
      sayoriLayout!!.layout = BoxLayout(sayoriLayout, BoxLayout.Y_AXIS)

      //---- sayoriButton ----
      sayoriButton!!.text = bundle.getString("DDLCWizardThemesPanel.sayoriButton.text")
      sayoriButton!!.horizontalAlignment = SwingConstants.LEFT
      sayoriButton!!.addActionListener { this.sayoriButtonActionPerformed() }
      sayoriLayout!!.add(sayoriButton)

      //---- sayoriLabel ----
      sayoriLabel!!.icon = ImageIcon(javaClass.getResource("/wizard/sayori.png"))
      sayoriLabel!!.addMouseListener(createMouseListener { this.sayoriButtonActionPerformed() })
      sayoriLayout!!.add(sayoriLabel)
    }
    grid!!.add(sayoriLayout!!, "cell 0 1,align center center,grow 0 0")

    //======== deletedCharacterLayout ========
    run {
      deletedCharacterLayout!!.border = EmptyBorder(5, 5, 5, 5)
      deletedCharacterLayout!!.layout = BoxLayout(deletedCharacterLayout, BoxLayout.Y_AXIS)

      //---- deletedCharacterButton ----
      deletedCharacterButton!!.text = "<html><div>Ć̤̳͕̟͖̖̯͡h̼̦̝̞̖̮̠̝͎͟a̵̯͇̮̘̩͖͉̦͞r̛͔̙͉a͞͏̬͔̬̤c̵̖͔̫̰̘t͕̖̩̖̦̼e͇̹̙̺͓̰̠̱͝ͅr̵͚̭̞̳̣̭͇̪ ҉̢̟Ṉ̱o̪͙̬͓̗͝t̨̻͓̫̺͠ ̶̷̲̗̩͟ͅF̵͖͇͔̹̜̮̀ọ̵͖̺͡u̴̝̟̜̩n̶̡̪̮͎̟̱͚̲͚͟d̛̠̺̝͞</div></html>"
      deletedCharacterButton!!.addActionListener { this.deletedCharacterButtonActionPerformed() }
      deletedCharacterLayout!!.add(deletedCharacterButton)

      //---- deletedCharacterLabel ----
      deletedCharacterLabel!!.icon = ImageIcon(javaClass.getResource("/wizard/deletedCharacter.png"))
      deletedCharacterLabel!!.addMouseListener(createMouseListener { this.deletedCharacterButtonActionPerformed() })
      deletedCharacterLayout!!.add(deletedCharacterLabel)
    }
    grid!!.add(deletedCharacterLayout!!, "cell 1 1,align center center,grow 0 0")

    //======== natsukiPanel ========
    run {
      natsukiPanel!!.border = EmptyBorder(5, 5, 5, 5)
      natsukiPanel!!.layout = BoxLayout(natsukiPanel, BoxLayout.Y_AXIS)

      //---- natsukiButton ----
      natsukiButton!!.text = bundle.getString("DDLCWizardThemesPanel.natsukiButton.text")
      natsukiButton!!.addActionListener { this.natsukiButtonActionPerformed() }
      natsukiPanel!!.add(natsukiButton)

      //---- natsukiLabel ----
      natsukiLabel!!.icon = ImageIcon(javaClass.getResource("/wizard/natsuki.png"))
      natsukiLabel!!.addMouseListener(createMouseListener { this.natsukiButtonActionPerformed() })
      natsukiPanel!!.add(natsukiLabel)
    }
    grid!!.add(natsukiPanel!!, "cell 0 2,align center center,grow 0 0")

    //======== onlyPlayWithMePanel ========
    run {
      onlyPlayWithMePanel!!.border = EmptyBorder(5, 5, 5, 5)
      onlyPlayWithMePanel!!.layout = BoxLayout(onlyPlayWithMePanel, BoxLayout.Y_AXIS)

      //---- onlyPlayWithMeButton ----
      onlyPlayWithMeButton!!.text = bundle.getString("DDLCWizardThemesPanel.onlyPlayWithMeButton.text")
      onlyPlayWithMeButton!!.addActionListener { this.onlyPlayWithMeButtonActionPerformed() }
      onlyPlayWithMePanel!!.add(onlyPlayWithMeButton)

      //---- onlyPlayWithMeLabel ----
      onlyPlayWithMeLabel!!.icon = ImageIcon(javaClass.getResource("/wizard/onlyPlayWithMe.png"))
      onlyPlayWithMeLabel!!.addMouseListener(createMouseListener { this.onlyPlayWithMeButtonActionPerformed() })
      onlyPlayWithMePanel!!.add(onlyPlayWithMeLabel)
    }
    grid!!.add(onlyPlayWithMePanel!!, "cell 1 2,align center center,grow 0 0")

    //======== yuriPanel ========
    run {
      yuriPanel!!.border = EmptyBorder(5, 5, 5, 5)
      yuriPanel!!.layout = BoxLayout(yuriPanel, BoxLayout.Y_AXIS)

      //---- yuriButton ----
      yuriButton!!.text = bundle.getString("DDLCWizardThemesPanel.yuriButton.text")
      yuriButton!!.addActionListener { this.yuriButtonActionPerformed() }
      yuriPanel!!.add(yuriButton)

      //---- yuriLabel ----
      yuriLabel!!.icon = ImageIcon(javaClass.getResource("/wizard/yuri.png"))
      yuriLabel!!.addMouseListener(createMouseListener { this.yuriButtonActionPerformed() })
      yuriPanel!!.add(yuriLabel)
    }
    grid!!.add(yuriPanel!!, "cell 0 3,align center center,grow 0 0")

    //======== edgyPanel ========
    run {
      edgyPanel!!.border = EmptyBorder(5, 5, 5, 5)
      edgyPanel!!.layout = BoxLayout(edgyPanel, BoxLayout.Y_AXIS)

      //---- edgyButton ----
      edgyButton!!.text = bundle.getString("DDLCWizardThemesPanel.edgyButton.text")
      edgyButton!!.addActionListener { this.edgyButtonActionPerformed() }
      edgyPanel!!.add(edgyButton)

      //---- edgyLabel ----
      edgyLabel!!.icon = ImageIcon(javaClass.getResource("/wizard/edgy.png"))
      edgyLabel!!.addMouseListener(createMouseListener { this.edgyButtonActionPerformed() })
      edgyPanel!!.add(edgyLabel)
    }
    grid!!.add(edgyPanel!!, "cell 1 3,align center center,grow 0 0")
  }


}