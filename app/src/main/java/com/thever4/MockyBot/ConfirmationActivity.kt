package com.thever4.MockyBot

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thever4.MockyBot.adapter.Adapter
import com.thever4.MockyBot.adapter.domain.*
import com.thever4.MockyBot.adapter.domain.nested.TariffElement
import com.thever4.MockyBot.adapter.domain.nested.UserdataElement

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerTest: RecyclerView
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
        val data = intent?.getStringExtra(MainActivity.KEY_TG_TOKEN)
        Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show()

        findView()
        initList()
        reload()
    }

    private fun reload() {
        recyclerTest.post {
            adapter.reload(createData())
        }
    }

    private fun findView() {
        recyclerTest = findViewById(R.id.recyclerTest)
    }

    private fun initList() {
        adapter = Adapter()

        layoutManager = LinearLayoutManager(this)
        recyclerTest.layoutManager = layoutManager
        recyclerTest.adapter = adapter
    }

    private fun createData(): MutableList<RootElement> {
        return mutableListOf(
            HeaderElement("Личный кабинет", HeaderType.HEADER_CAPITAL),
            BalanceElement("11010010", "100.42 ₽", "0.00₽"),
            HeaderElement("Тариф", HeaderType.HEADER_CATEGORY),
            RecyclerElement(
                mutableListOf(
                    TariffElement("Тариф «Улыбка (беспла...", "Скорость до 100 Мбит/с", "0₽"),
                    TariffElement("Тариф «Улыбка (беспла...", "Скорость до 100 Мбит/с", "0₽"),
                )
            ),
            HeaderElement("Пользователь", HeaderType.HEADER_CATEGORY),
            RecyclerElement(
                mutableListOf(
                    UserdataElement(
                        R.drawable.ic_baseline_supervised_user_circle_24,
                        "Иванов иван Иваныч"
                    ),
                    UserdataElement(
                        R.drawable.ic_baseline_house_24,
                        "Сахалин, ул. Пушкина, д. Колот..."
                    ),
                    UserdataElement(R.drawable.ic_baseline_widgets_24, "Доступные услуги"),
                ), "#FF0000"
            ),
        )
    }
}