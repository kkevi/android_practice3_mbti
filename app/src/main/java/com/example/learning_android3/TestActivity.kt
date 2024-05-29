package com.example.learning_android3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2

class TestActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    val questionarieResults = QuestionarieResults()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test)
        viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = ViewPagerAdaptor(this)
        viewPager.isUserInputEnabled = false //좌우 스크롤 방지
    }

    fun moveToNextQuestion(){
        if (viewPager.currentItem == 3) {
            // 마지막 페이지 -> 결과 화면으로 이동
//            val intent
        } else {
            val nextItem = viewPager.currentItem + 1 //페이징 처리
            if(nextItem < (viewPager.adapter?.itemCount ?: 0)){
                viewPager.setCurrentItem(nextItem, true)
            }
        }
    }
}

class QuestionarieResults{
    val results = mutableListOf<Int>()

    fun addResponses(response: List<Int>){
        // groupingBy로 List<Int>안의 같은 값끼리 묶어준다.
        // eachCount()로 각각의 개수를 센 뒤 어떤 수가 더 개수가 많은지 maxByOrNull로 판단한다.
        // 그것의 key 값을 mostFrequent로 할당
        val mostFrequent = response.groupingBy{it}.eachCount().maxByOrNull{it.value}?.key
        mostFrequent?.let{results.add(it)}
    }

}