package com.example.hgexam1

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.ITALIC
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    val nameArray = arrayOf("아이유", "신봉선", "이지은")
    val productArray = arrayOf(
        ProductModel("김치찌개", 7000),
        ProductModel("삼겹살", 13000),
        ProductModel("계란찜", 3000)
    )
    val numberList = mutableListOf<Int>()

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

        //adapter 생성
        val nameAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, nameArray)
        val productAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, productArray)
        val numberAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, numberList)

        //Spinner에 adapter등록
        nameOption.adapter = nameAdapter
        productOption.adapter = productAdapter
        numberOption.adapter = numberAdapter

        //Spinner에서 숫자 선택 시 문구 변경하는 eventListener 등록
        changeNumber()

        //숫자 추가 버튼에 숫자 랜덤 추가하는 eventListener 등록
        addNumber(numberAdapter)

        //숫자 삭제 버튼에 숫자 삭제하는 eventListener 등록
        removeNumber(numberAdapter)

        //Spinner에서 제품 선택 시 가격 변경하는 eventListener 등록
        changeProductPrice()

        //Spinner에서 이름 선택 시 문구 변경하는 eventListener 등록
        changeHelloText()

        //이름 입력 시 text값 변경하는 eventListener등록
        changeName()

        //ImageView의 drawable을 변경하는 eventListener등록
        changeDrawable()

        //ImageView의 ScaleType을 변경하는 eventListener등록
        changeScaleType()

        //Text의 boldoption 변경하는 eventListener 등록
        boldOption.setOnCheckedChangeListener(this)

        //Text의 italicoption 변경하는 eventListener 등록
        italicOption.setOnCheckedChangeListener(this)

        //Text의 색깔 변경하는 eventListener 등록
        changeTextColor()
    }

    private fun removeNumber(numberAdapter: ArrayAdapter<Int>) {
        removeButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                numberList.removeAt(numberList.lastIndex)
                numberAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun addNumber(numberAdapter: ArrayAdapter<Int>) {
        addButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                numberList.add(Random.nextInt(0..100))
                numberAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun changeNumber() {
        numberOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                notifyText.text = "${numberList[position]}번을 선택하셨습니다."
            }
        }
    }

    private fun changeProductPrice() {
        productOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                priceText.text = productArray[position].let { "${it.name}의 가격은 ${it.price}입니다!" }
            }

        }
    }

    private fun changeHelloText() {
        nameOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                helloText.setText("${nameArray[position]}님 환영합니다.^^")
            }
        }
    }

    private fun changeTextColor() {
        colorOptionGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.redOption -> previewText.setTextColor(Color.RED)
                    R.id.blueOption -> previewText.setTextColor(Color.BLUE)
                    R.id.blackOption -> previewText.setTextColor(Color.BLACK)
                }
            }
        })
    }

    private fun changeScaleType() {
        scaleTypeButton.setOnClickListener(object : View.OnClickListener {
            var isDefault = true
            override fun onClick(v: View?) {
                isDefault = !isDefault
                if (isDefault) photoView.scaleType = ImageView.ScaleType.CENTER_CROP
                else photoView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
        })
    }

    private fun changeDrawable() {
        photoView.setOnClickListener(object : View.OnClickListener {
            var isDefault = true
            override fun onClick(v: View?) {
                isDefault = !isDefault
                if (isDefault) photoView.setImageResource(R.drawable.iu1)
                else photoView.setImageResource(R.drawable.iu2)
            }
        })
    }

    private fun changeName() {
        inputButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (nameEdit.text.isBlank()) {
                    greetText.text = "이름이 없습니다."
                } else {
                    greetText.text = "${nameEdit.text}님, 반갑습니다."
                }
            }
        })
    }
}
