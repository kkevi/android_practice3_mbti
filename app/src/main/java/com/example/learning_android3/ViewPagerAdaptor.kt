package com.example.learning_android3

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdaptor(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        TODO("Not yet implemented")

        // 4개의 페이지를 쓰겠다.
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")

//        return QuestionFragment.newInstance(position)
    }
}