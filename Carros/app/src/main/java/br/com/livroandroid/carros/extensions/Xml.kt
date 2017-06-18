package br.com.livroandroid.carros.extensions

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.ArrayList
import javax.xml.parsers.DocumentBuilderFactory

class Xml(val root: Element) {
    // Retorna todos os elementos filhos desta tag
    fun getChildren(name: String): List<Node> {
        return getChildren(root, name);
    }

    fun getChildren(node: Node = root, name: String): List<Node> {
        val children = ArrayList<Node>()
        val nodes = node.childNodes
        if (nodes != null && nodes.length >= 1) {
            for (i in 0..nodes.length - 1) {
                val n = nodes.item(i)
                if (name == n.nodeName) {
                    children.add(n)
                }
            }
        }
        return children
    }
}

// Retorna a tag filha desta tag.
fun Node.getChild(tag: String): Node? {
    val childNodes = childNodes ?: return null
    for (i in 0..childNodes.length - 1) {
        val item = childNodes.item(i)
        if (item != null) {
            val name = item.nodeName
            if (tag.equals(name, ignoreCase = true)) {
                return item
            }
        }
    }
    return null
}

// Retorna o texto desta tag XML
fun Node.getText(tag: String): String {
    val n = getChild(tag)
    if (n != null) {
        val text = n.firstChild
        if (text != null) {
            val s = text.nodeValue
            return s.trim { it <= ' ' }
        }
    }
    return ""
}

// Retorna um objeto XML a partir da String
fun String.getXml(charset: String = "UTF-8"): Xml {
    try {
        val xml = this
        val factory = DocumentBuilderFactory.newInstance()
        factory.isValidating = false
        val bytes = xml.toByteArray(charset(charset))
        val inputStream = ByteArrayInputStream(bytes)
        val builder = factory.newDocumentBuilder()
        val dom = builder.parse(inputStream)
        val root = dom.documentElement ?: throw RuntimeException("XML incorreto.")
        return Xml(root)
    } catch (e: Exception) {
        throw RuntimeException(e.message, e)
    }
}
