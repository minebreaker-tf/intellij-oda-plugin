package rip.deadcode.intellij.oda.utils

import com.intellij.ui.components.labels.LinkLabel
import com.intellij.ui.components.labels.LinkListener
import java.awt.Desktop
import java.net.URI

class HttpLinkLabel(uri: URI) : LinkLabel<URI>(uri.toString(), null, OpenBrowserListener, uri) {

    object OpenBrowserListener : LinkListener<URI> {
        override fun linkSelected(aSource: LinkLabel<*>?, aLinkData: URI) {
            Desktop.getDesktop().browse(aLinkData)
        }
    }
}
