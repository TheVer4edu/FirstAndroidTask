package com.thever4.MockyBot.ui

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thever4.MockyBot.R
import com.thever4.MockyBot.adapter.Adapter
import com.thever4.MockyBot.domain.*
import com.thever4.MockyBot.domain.nested.NestedElement
import com.thever4.MockyBot.domain.nested.TariffElement
import com.thever4.MockyBot.domain.nested.UserdataElement
import com.thever4.MockyBot.network.models.Balance
import com.thever4.MockyBot.network.models.Tariff
import com.thever4.MockyBot.network.models.UserInfo
import com.thever4.MockyBot.network.retrofit.ApiProvider
import com.thever4.MockyBot.network.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerTest: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var loadingScroll: ProgressBar
    private val api = ApiProvider(RetrofitClient()).getApi()

    private lateinit var tariffs: List<TariffElement>
    private lateinit var balance: BalanceElement
    private lateinit var userInfo: MutableList<UserdataElement>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
        val data = intent?.getStringExtra(MainActivity.KEY_TG_TOKEN)
        Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show()

        findView()
        initList()
        mockData()
        reload()
        loadFromApi()
    }

    private fun mockData() {
        balance = mapBalanceToItem(
            Balance(
                accNum = 1,
                balance = 0.0,
                nextPay = 0.0,
                id = "Идёт загрузка..."
            )
        )
        tariffs = mutableListOf(
            mapTariffToItem(
                Tariff(
                    title = "Загружаем элементы",
                    "Пожалуйста, подождите...",
                    cost = 0,
                    id = ""
                )
            )
        )
        userInfo = mapUserInfoToItem(
            UserInfo(
                firstName = "Осталось",
                lastName = "Совсем немного",
                address = "Скоро всё будет готов",
                id = ""
            )
        )
    }

    private fun reload() {
        recyclerTest.post {
            adapter.reload(createData())
        }
    }

    private fun mapTariffToItem(tariff: Tariff) =
        TariffElement(
            name = tariff.title,
            description = tariff.desc,
            amount = tariff.cost.toString() + "₽"
        )

    private fun mapBalanceToItem(balance: Balance) =
        BalanceElement(
            accountId = balance.accNum.toString(),
            balance = balance.balance.toString() + " ₽",
            nextMonthPayment = balance.nextPay.toString() + "₽"
        )

    private fun mapUserInfoToItem(userInfo: UserInfo) =
        mutableListOf(
            UserdataElement(
                R.drawable.ic_baseline_supervised_user_circle_24,
                userInfo.firstName + " " + userInfo.lastName
            ),
            UserdataElement(
                R.drawable.ic_baseline_house_24,
                userInfo.address
            ),
            UserdataElement(
                R.drawable.ic_baseline_widgets_24,
                "Доступные услуги"
            ),
        )

    private fun loadFromApi() {
        loadingScroll.isVisible = true
        val scope = MainScope().launch(Dispatchers.IO) {

            /*val callbackTariffs = object: Callback<List<Tariff>> {
                override fun onResponse(call: Call<List<Tariff>>, response: Response<List<Tariff>>) {
                    val tariffs1 = response.body() ?: onFailure(call, Exception("Body is null"))
                    tariffs = (tariffs1 as List<Tariff>).map(::mapTariffToItem)
                    loadingScroll.isVisible = false
                }

                override fun onFailure(call: Call<List<Tariff>>, t: Throwable) {
                    Toast.makeText(this@ConfirmationActivity, "Bad internet connection", Toast.LENGTH_LONG).show()
                    loadingScroll.isVisible = false
                }
            }

            val callbackBalance = object: Callback<List<Balance>> {
                override fun onResponse(call: Call<List<Balance>>, response: Response<List<Balance>>) {
                    val balance1 = response.body() ?: onFailure(call, Exception("Body is null"))
                    balance = (balance1 as List<Balance>).map(::mapBalanceToItem)[0]
                    loadingScroll.isVisible = false
                }

                override fun onFailure(call: Call<List<Balance>>, t: Throwable) {
                    Toast.makeText(this@ConfirmationActivity, "Bad internet connection", Toast.LENGTH_LONG).show()
                    loadingScroll.isVisible = false
                }
            }

            val callbackUserInfo = object: Callback<List<UserInfo>> {
                override fun onResponse(call: Call<List<UserInfo>>, response: Response<List<UserInfo>>) {
                    val userInfo1 = response.body() ?: onFailure(call, Exception("Body is null"))
                    userInfo = (userInfo1 as List<UserInfo>).map(::mapBalanceToItem)[0]
                    loadingScroll.isVisible = false
                }

                override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                    Toast.makeText(this@ConfirmationActivity, "Bad internet connection", Toast.LENGTH_LONG).show()
                    loadingScroll.isVisible = false
                }
            }

            api.getTariffs().enqueue(callbackTariffs)
            api.getBalances().enqueue(callbackBalance)
            api.getUserInfo().enqueue(callbackUserInfo)*/

            balance = mapBalanceToItem(api.getBalances().execute().body()!![0])
            tariffs = api.getTariffs().execute().body()!!.map(::mapTariffToItem)
            userInfo = mapUserInfoToItem(api.getUserInfo().execute().body()!![0])

        }
        MainScope().launch(Dispatchers.Main) {
            while(!scope.isCompleted) {}
            loadingScroll.isVisible = false
            reload()
        }
    }

    private fun createData(): MutableList<RootElement> {
        return mutableListOf(
            HeaderElement("Личный кабинет", HeaderType.HEADER_CAPITAL),
            balance,
            HeaderElement("Тариф", HeaderType.HEADER_CATEGORY),
            RecyclerElement(
                tariffs.toMutableList()
            ),
            HeaderElement("Пользователь", HeaderType.HEADER_CATEGORY),
            RecyclerElement(
                userInfo as MutableList<NestedElement>, "#DFDFDF"
            ),
        )
    }

    private fun findView() {
        loadingScroll = findViewById(R.id.loading)
        recyclerTest = findViewById(R.id.recyclerTest)
    }

    private fun initList() {
        adapter = Adapter()

        layoutManager = LinearLayoutManager(this)
        recyclerTest.layoutManager = layoutManager
        recyclerTest.adapter = adapter
    }

    /*private fun createData(): MutableList<RootElement> {
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
                    UserdataElement(
                        R.drawable.ic_baseline_widgets_24,
                        "Доступные услуги"
                    ),
                ), "#DFDFDF"
            ),
        )
    }*/
}