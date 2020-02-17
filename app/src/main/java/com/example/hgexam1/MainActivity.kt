package com.example.hgexam1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (nameEdit.text.isBlank()) {
                    greetText.text = "이름이 없습니다."
                } else {
                    greetText.text = "${nameEdit.text}님, 반갑습니다."
                }
            }
        })

        photoView.setOnClickListener(object : View.OnClickListener {
            var isDefault = true
            override fun onClick(v: View?) {
                isDefault = !isDefault
                if (isDefault) photoView.setImageResource(R.drawable.iu1)
                else photoView.setImageResource(R.drawable.iu2)
            }

        })

        scaleTypeButton.setOnClickListener(object: View.OnClickListener {
            var isDefault = true
            override fun onClick(v: View?) {
                isDefault = !isDefault
                if(isDefault) photoView.scaleType = ImageView.ScaleType.CENTER_CROP
                else photoView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
        })
    }
}
