package com.example.learning_android3

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class QuestionFragment : Fragment() {

    private var questionType: Int = 0 //몇번째 질문인지

    private val questionTitle = listOf(
        R.string.question1_title,
        R.string.question2_title,
        R.string.question3_title,
        R.string.question4_title,
    )

    private val questionTexts = listOf(
        listOf(R.string.question1_1, R.string.question1_2, R.string.question1_3,),
        listOf(R.string.question2_1, R.string.question2_2, R.string.question2_3,),
        listOf(R.string.question3_1, R.string.question3_2, R.string.question3_3,),
        listOf(R.string.question4_1, R.string.question4_2, R.string.question4_3,),
    )

    private val questionAnswer = listOf(
        listOf(
            listOf(R.string.question1_1_answer1, R.string.question1_1_answer2,),
            listOf(R.string.question1_2_answer1, R.string.question1_2_answer2,),
            listOf(R.string.question1_3_answer1, R.string.question1_3_answer2,),
        ),
        listOf(
            listOf(R.string.question2_1_answer1, R.string.question2_1_answer2,),
            listOf(R.string.question2_2_answer1, R.string.question2_2_answer2,),
            listOf(R.string.question2_3_answer1, R.string.question2_3_answer2,),
        ),
        listOf(
            listOf(R.string.question3_1_answer1, R.string.question3_1_answer2,),
            listOf(R.string.question3_2_answer1, R.string.question3_2_answer2,),
            listOf(R.string.question3_3_answer1, R.string.question3_3_answer2,),
        ),
        listOf(
            listOf(R.string.question4_1_answer1, R.string.question4_1_answer2,),
            listOf(R.string.question4_2_answer1, R.string.question4_2_answer2,),
            listOf(R.string.question4_3_answer1, R.string.question4_3_answer2,),
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let{
            questionType = it.getInt(ARG_QUESTION_TYPE) //몇 번째 화면인지
        }
    }


    // view를 처음 생성할 때 실행되는 함수
    override fun onCreateView(
        inflater: LayoutInflater, // xml로 만든 코드를 들고 오는 것
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        val title: TextView = view.findViewById(R.id.tv_question_title)
        title.text = getString(questionTitle[questionType]) //title 세팅

        val questionTextView = listOf<TextView>(
            view.findViewById(R.id.tv_question_1),
            view.findViewById(R.id.tv_question_2),
            view.findViewById(R.id.tv_question_3),
        )

        val answerRadioGroup = listOf<RadioGroup>(
            view.findViewById(R.id.rg_answer_1),
            view.findViewById(R.id.rg_answer_2),
            view.findViewById(R.id.rg_answer_3),
        )

        for(i:Int in questionTextView.indices){
            questionTextView[i].text = getString(questionTexts[questionType][i])

            val radioButton1 = answerRadioGroup[i].getChildAt(0) as RadioButton
            val radioButton2 = answerRadioGroup[i].getChildAt(1) as RadioButton

            radioButton1.text = getString(questionAnswer[questionType][i][0])
            radioButton2.text = getString(questionAnswer[questionType][i][1])
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val answerRadioGroup = listOf<RadioGroup>(
            view.findViewById(R.id.rg_answer_1),
            view.findViewById(R.id.rg_answer_2),
            view.findViewById(R.id.rg_answer_3),
        )

        val btn_next: Button = view.findViewById(R.id.btn_next)

        btn_next.setOnClickListener{
            val isAllAnswered = answerRadioGroup.all{it.checkedRadioButtonId != -1} //radiogroup의 모든 값이 null이 아닌지 체크

            if(isAllAnswered){
                val responses = answerRadioGroup.map{ radioGroup ->
                    val firstRadioButton = radioGroup.getChildAt(0) as RadioButton
                    if(firstRadioButton.isChecked) 1 else 2 //1번 체크인지 2번 체크인지 확인하고 return 하는 부분
                }

                (activity as? TestActivity)?.questionarieResults?.addResponses(responses)
                (activity as? TestActivity)?.moveToNextQuestion()


            } else {
                Toast.makeText(context, "모든 질문에 답 해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }



    //생성자
    companion object {
        private const val ARG_QUESTION_TYPE = "questionType"

        fun newInstance(questionType: Int): QuestionFragment {
            val fragment = QuestionFragment()
            val args = Bundle()
            args.putInt(ARG_QUESTION_TYPE, questionType)
            fragment.arguments = args
            return fragment
        }
    }
}