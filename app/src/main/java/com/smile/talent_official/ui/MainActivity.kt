package com.smile.talent_official.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.smile.talent_official.R
import com.smile.talent_official.databinding.ActivityMainBinding
import com.smile.talent_official.data.models.TalentModel
import com.smile.talent_official.ui.worker.AddWorkerViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelAdd: AddWorkerViewModel

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_fragment)

        viewModelAdd = ViewModelProvider(this).get(AddWorkerViewModel::class.java)
        viewModelAdd.insert(
            TalentModel(0, "Bezhan Abdulmajid", "UX/UI designer", "Добрый день!Меня зовут Бежан. " +
                "Имею 5 летний опыт работы (стаж) в сфере графического дизайна и UI / UX." +
                " Покажу портфолио только при личном запросе из за соображений конфиденциального характера. " +
                "Работаю исключительно ...", R.drawable.bezhan )
        )
/*
        //Добавление Специалистов в БД
        viewModelAdd.insert(TalentModel(1, "Alisher Narzulloev", "Web developer", "Открыт к предложениям", R.drawable.narzulloev_a))
        viewModelAdd.insert(TalentModel(2, "Ayana Muhamadieva", "Graphic designer", "Работаю", R.drawable.muhamadieva_a))
        viewModelAdd.insert(TalentModel(3, "Азиз Мирзоев", "Graphic designer", "Хочу работать", R.drawable.mirzoev_a))
        viewModelAdd.insert(TalentModel(4, "Рустам Сафаров", "Full Stack developer", "Я Android разработчик с опытом работы более двух лет. ", R.drawable.safarov_r))
        viewModelAdd.insert(TalentModel(5, "Masrur Rahimov", "Android developer", "На международной олимпиаде по математике в Индии получил 2-е место (серебро)Несколько медалей в городских и республиканских олимпиадТретье место на олимпиаде Кенгуру в прошлом году (2020)", R.drawable.rahimov_m))
        viewModelAdd.insert(TalentModel(6, "Sukhrob Sharifov", "Back-end developer", "Меня зовут Сухроб, мне 19 лет. Учусь в Технологическом на 1 курсе, являюсь волонтером в ITCC (Korean - Tajik IT Communication Center). С 7 класса выбрал профессию программист, на данный момент знаю основы С++, HTML ...", R.drawable.sharifov_s))
        viewModelAdd.insert(TalentModel(7, "Бахтовар Умаров", "iOS developer", "Амбициозный молодой человек который хочет стать конкурентоспособным программистом в Таджикистане и это я, Бахтовар. В данное время прохожу обучение в АУЦА по факультету Software Engineering, первый курс. Проходил не...", R.drawable.umarov_b))
        viewModelAdd.insert(TalentModel(8, "Eraj Rahmonberdiev", "UX/UI designer", "Графический и UX/UI дизайнер.", R.drawable.rahmonberdiev_e))
        viewModelAdd.insert(TalentModel(9, "Abdurahmon Odinaev","Full Stack developer", "I have 2+ years of expirence as Full-Stack Developer. I have experience on creating full stack application created on laravel, and React JS. Nowaday, I love create fast web application like SPA by lovely javascript...", R.drawable.odinaev_a))
*/



    }
}