package com.playground.transaction.component

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.playground.transaction.R
import com.playground.transaction.databinding.ViewDialogLoadingBinding

internal class DialogLoading(context: Context) :
    Dialog(context, R.style.Widget_Playground_Dialog_Loading) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ViewDialogLoadingBinding.inflate(inflater, null, false)
        setCancelable(false)
        setContentView(binding.root)
    }

    fun setLoading(isLoading: Boolean) {
        if (isLoading) show()
        else dismiss()
    }
}