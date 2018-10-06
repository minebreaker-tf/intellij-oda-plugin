package rip.deadcode.intellij.oda

import com.intellij.openapi.options.Configurable
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import rip.deadcode.intellij.oda.utils.HttpLinkLabel
import rip.deadcode.intellij.oda.utils.OdaPasswords
import java.awt.FlowLayout
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.net.URI
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class OdaSettingPage : Configurable, Configurable.NoScroll {

    private val tbId = JBTextField().also {
        it.columns = 16
    }
    private val tbKey = JBTextField().also {
        it.columns = 16
    }

    private val component = JPanel(FlowLayout(FlowLayout.LEFT)).also {
        it.border = EmptyBorder(2, 2, 2, 2)
        it.add(JPanel(GridBagLayout()).apply {
            add(JBLabel("Oxford Dictionaries API Key settings"), GridBagConstraints().apply {
                anchor = GridBagConstraints.WEST
                gridx = 0
                gridy = 0
            })
            add(JBLabel("App ID"), GridBagConstraints().apply {
                anchor = GridBagConstraints.WEST
                gridx = 0
                gridy = 1
                insets = Insets(0, 0, 0, 4)
            })
            add(tbId, GridBagConstraints().apply {
                anchor = GridBagConstraints.WEST
                gridx = 1
                gridy = 1
            })
            add(JBLabel("App Key"), GridBagConstraints().apply {
                anchor = GridBagConstraints.WEST
                gridx = 0
                gridy = 2
                insets = Insets(0, 0, 0, 4)
            })
            add(tbKey, GridBagConstraints().apply {
                anchor = GridBagConstraints.WEST
                gridx = 1
                gridy = 2
            })
        })
        it.add(JPanel().apply {
            add(JBLabel("To get your API key: "))
            add(HttpLinkLabel(URI("https://developer.oxforddictionaries.com/documentation/getting_started")))
        })
    }

    override fun isModified(): Boolean {
        return tbId.text != OdaPasswords.getId() ||
                tbKey.text != OdaPasswords.getKey()
    }

    override fun getDisplayName(): String = "Thesaurus Plugin"

    override fun apply() {
        OdaPasswords.set(tbId.text, tbKey.text)
    }

    override fun createComponent(): JComponent {
        tbId.text = OdaPasswords.getId()
        tbKey.text = OdaPasswords.getKey()
        return component
    }
}
