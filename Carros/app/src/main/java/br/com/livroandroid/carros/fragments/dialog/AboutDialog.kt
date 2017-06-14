package br.com.livroandroid.carros.fragments.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.widget.TextView

import br.com.livroandroid.carros.R
import livroandroid.lib.utils.AndroidUtils

class AboutDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Cria o HTML com o texto de sobre o aplicativo
        val aboutBody = SpannableStringBuilder()
        // Versão do aplicativo
        val versionName = AndroidUtils.getVersionName(activity)
        // Converte o texto do strings.xml para HTML
        aboutBody.append(Html.fromHtml(getString(R.string.about_dialog_text, versionName)))
        // Infla o layout
        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(R.layout.dialog_about, null) as TextView
        view.text = aboutBody
        view.movementMethod = LinkMovementMethod()
        // Cria o dialog customizado
        return AlertDialog.Builder(activity)
                .setTitle(R.string.about_dialog_title)
                .setView(view)
                .setPositiveButton(R.string.ok
                ) { dialog, whichButton -> dialog.dismiss() }
                .create()
    }

    companion object {
        // Método utilitário para mostrar o dialog
        fun showAbout(fm: android.support.v4.app.FragmentManager) {
            val ft = fm.beginTransaction()
            val prev = fm.findFragmentByTag("dialog_about")
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            AboutDialog().show(ft, "dialog_about")
        }
    }
}
