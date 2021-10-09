package com.robbie.rock_scissors_paper_appchallenge_chapter5.utils
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.robbie.rock_scissors_paper_appchallenge_chapter5.R


object Helper {


    fun showToast(context: Context, message: String){
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }

    fun setBg(linearLayout: View){
        linearLayout.setBackgroundColor(R.drawable.select)

    }
     fun defSetBg(linearLayout: LinearLayout) {
        linearLayout.setBackgroundResource(R.drawable.defsetbg)
    }

    fun showDialog(context: Context,Img : Int, close: String){
        val alertDialog = AlertDialog.Builder(context)
        val view: View = LayoutInflater.from(context).inflate(R.layout.template_dialog,null)
        val dialogImgView = view.findViewById<ImageView>(R.id.iv_dialog_image)
        dialogImgView.setImageResource(Img)
        alertDialog.setView(view)
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton(close){
            _, _ ->
        }.show()
    }




}