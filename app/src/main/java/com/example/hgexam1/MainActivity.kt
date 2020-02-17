package com.example.hgexam1

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.ITALIC
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    //CheckBox 통해 글씨 모양 변경하는 eventListener
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        var option = 0

        if (boldOption.isChecked) option += BOLD
        if (italicOption.isChecked) option += ITALIC

        previewText.setTypeface(null, option)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //이름 입력 시 text값 변경하는 eventListener등록
        inputButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (nameEdit.text.isBlank()) {
                    greetText.text = "이름이 없습니다."
                } else {
                    greetText.text = "${nameEdit.text}님, 반갑습니다."
                }
            }
        })

        //ImageView의 drawable을 변경하는 eventListener등록
        photoView.setOnClickListener(object : View.OnClickListener {
            var isDefault = true
            override fun onClick(v: View?) {
                isDefault = !isDefault
                if (isDefault) photoView.setImageResource(R.drawable.iu1)
                else photoView.setImageResource(R.drawable.iu2)
            }

        })

        //ImageView의 ScaleType을 변경하는 eventListener등록
        scaleTypeButton.setOnClickListener(object : View.OnClickListener {
            var isDefault = true
            override fun onClick(v: View?) {
                isDefault = !isDefault
                if (isDefault) photoView.scaleType = ImageView.ScaleType.CENTER_CROP
                else photoView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
        })

        //Text의 boldoption 변경하는 eventListener 등록
        boldOption.setOnCheckedChangeListener(this)

        //Text의 italicoption 변경하는 eventListener 등록
        italicOption.setOnCheckedChangeListener(this)

        //Text의 색깔 변경하는 eventListener 등록
        colorOptionGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when(checkedId) {
                    R.id.redOption -> previewText.setTextColor(Color.RED)
                    R.id.blueOption -> previewText.setTextColor(Color.BLUE)
                    R.id.blackOption -> previewText.setTextColor(Color.BLACK)
                }
            }
        })
    }
}
