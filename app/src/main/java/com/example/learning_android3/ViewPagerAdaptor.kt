package com.example.learning_android3

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdaptor(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        // 4개의 페이지를 쓰겠다.
        return 4
    }

    // 새로운 페이지를 호출할 때 아래 함수 실행 -> 실행될 때 Position 같이 들어옴
    override fun createFragment(position: Int): Fragment {
        return QuestionFragment.newInstance(position) // 새로운 fragment를 만들면서 새인스턴스 생성하고 Positoin (페이지 넘버)를 파라미터로 넘겨줌
    }
}