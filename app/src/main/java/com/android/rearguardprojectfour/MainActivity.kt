package com.android.rearguardprojectfour

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val asyncbtn = findViewById<Button>(R.id.async)
        val sync  = findViewById<Button>(R.id.sync)

        asyncbtn.setOnClickListener {
            GlobalScope.launch{
                val startTime = System.currentTimeMillis()
                Log.d("기록시작", "$startTime")
                val list1 = listOf(
                    task1()
                )
                val list2 = listOf(
                    async { task2() },
                    async { task3() },
                    async { task4() },
                    async { task5() },
                )
                list2.awaitAll()
                val list3 = listOf(
                    task6()
                )
                val endTime = System.currentTimeMillis()
                Log.d("기록종료", "$endTime")
                val resultTime = (endTime - startTime) / 1000
                Log.d("소요시간", "$resultTime 초")
            }
        }

        sync.setOnClickListener {
            val startTime = System.currentTimeMillis()
            Log.d("기록시작","$startTime")
            task1()
            task2()
            task3()
            task4()
            task5()
            task6()
            val endTime = System.currentTimeMillis()
            Log.d("기록종료","$endTime")
            val resultTime = (endTime-startTime)/1000
            Log.d("소요시간","$resultTime 초")
        }

    }
    fun task1(): Int {
        Thread.sleep(1000)
        return Log.d("작업 완료","1번")
    }
    fun task2(): Int {
        Thread.sleep(1500)
        return Log.d("작업 완료","2번")
    }
    fun task3(): Int {
        Thread.sleep(2000)
        return Log.d("작업 완료","3번")
    }
    fun task4(): Int {
        Thread.sleep(2500)
        return Log.d("작업 완료","4번")
    }
    fun task5(): Int {
        Thread.sleep(3000)
        return Log.d("작업 완료","5번")
    }fun task6(): Int {
        Thread.sleep(4000)
        return Log.d("작업 완료","6번")
    }

}