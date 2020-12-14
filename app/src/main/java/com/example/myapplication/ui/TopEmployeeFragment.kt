package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.TopUserAdapter
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.fragment_top_employee.*


class TopEmployeeFragment : Fragment() {
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var autoCompleteTextView2: AutoCompleteTextView

    private val topUsers = listOf(
            User("Ізтілеуов","Мақсат","Атқарушы директор","маркетинг","алтын","https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",37),
            User("Төлепберген","Дина","Атқарушы директор","аналитика","күміс","https://i.pinimg.com/originals/7d/1a/3f/7d1a3f77eee9f34782c6f88e97a6c888.jpg",100),
            User("Тілешова","Мария","Атқарушы директор","есеп","мыс","https://www.bigredcloud.com/wp-content/uploads/4-tips-for-taking-professional-profile-pictures.jpg",68),
            User("Ізтілеуов","Мағжан","Атқарушы директор","қаржы","алтын","https://writestylesonline.com/wp-content/uploads/2019/01/What-To-Wear-For-Your-Professional-Profile-Picture-or-Headshot.jpg",70),
            User("Болатов","Арман","Атқарушы директор","кадр","алтын","https://mobirise.com/bootstrap-template/profile-template/assets/images/timothy-paul-smith-256424-1200x800.jpg",40),
            User("Ізтілеуов","Мақсат","Атқарушы директор","маркетинг","күміс","https://monteluke.com.au/wp-content/gallery/linkedin-profile-pictures/1.jpg",90),
            User("Төлепберген","Дина","Атқарушы директор","аналитика","темір","https://www.goodmorningimagesdownload.com/wp-content/uploads/2019/10/Best-Whatsapp-DP-Profile-Images-98.jpg",70),
            User("Тілешова","Мария","Атқарушы директор","есеп","алтын","https://monteluke.com.au/wp-content/gallery/linkedin-profile-pictures/cache/3.JPG-nggid03125-ngg0dyn-591x591-00f0w010c010r110f110r010t010.JPG",83),
            User("Ізтілеуов","Мағжан","Атқарушы директор","қаржы","алтын","https://cultivatedculture.com/wp-content/uploads/2019/12/LinkedIn-Profile-Picture-Example-Tynan-Allan.jpeg",93),
            User("Болатов","Арман","Атқарушы директор","кадр","алтын","https://textgod.com/wp-content/uploads/2019/06/louis-roze-trui-pink.jpg",64),
            User("Ізтілеуов","Мақсат","Атқарушы директор","маркетинг","күміс","https://freepikpsd.com/wp-content/uploads/2019/09/Cute-Girl-Picture-for-Profile-18.png",85),
            User("Төлепберген","Дина","Атқарушы директор","аналитика","алтын","https://cdn.business2community.com/wp-content/uploads/2014/04/profile-picture-300x300.jpg",98),
            User("Тілешова","Мария","Атқарушы директор","есеп","мыс","https://www.irreverentgent.com/wp-content/uploads/2018/03/Awesome-Profile-Pictures-for-Guys-look-away2.jpg",34),
            User("Ізтілеуов","Мағжан","Атқарушы директор","қаржы","алтын","https://i.pinimg.com/originals/7d/1a/3f/7d1a3f77eee9f34782c6f88e97a6c888.jpg",56),
            User("Болатов","Арман","Атқарушы директор","кадр","алтын","https://i.pinimg.com/originals/7d/1a/3f/7d1a3f77eee9f34782c6f88e97a6c888.jpg",80),
            User("Ізтілеуов","Мақсат","Атқарушы директор","маркетинг","күміс","https://i.pinimg.com/originals/7d/1a/3f/7d1a3f77eee9f34782c6f88e97a6c888.jpg",30),
            User("Төлепберген","Дина","Атқарушы директор","аналитика","темір","https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",67),
            User("Тілешова","Мария","Атқарушы директор","есеп","күміс","https://www.irreverentgent.com/wp-content/uploads/2018/03/Awesome-Profile-Pictures-for-Guys-look-away2.jpg",77),
            User("Ізтілеуов","Мағжан","Атқарушы директор","қаржы","күміс","https://textgod.com/wp-content/uploads/2019/06/louis-roze-trui-pink.jpg",82),
            User("Болатов","Арман","Атқарушы директор","кадр","күміс","https://i.pinimg.com/originals/7d/1a/3f/7d1a3f77eee9f34782c6f88e97a6c888.jpg",54)
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_top_employee, container, false)
    }

    override fun onResume() {
        super.onResume()
        autoCompleteTextView2 = requireActivity().findViewById(R.id.filled_exposed_dropdown2)
        autoCompleteTextView = requireActivity().findViewById(R.id.filled_exposed_dropdown)
        val optionDep = arrayOf(
                "Маркетинг",
                "Аналитика",
                "Кадр",
                "Қаржы",
                "IT",
                "Есеп"
        )
        val optionRank = arrayOf(
                "Алтын",
                "Күміс",
                "Мыс",
                "Темір"
        )
        autoCompleteTextView2.setAdapter(ArrayAdapter(requireContext(),R.layout.option_item,optionRank))
        autoCompleteTextView.setAdapter(ArrayAdapter(requireContext(),R.layout.option_item,optionDep))


        autoCompleteTextView2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(requireContext(), optionRank[position].toString(),Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        autoCompleteTextView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(requireContext(), optionDep[position].toString(),Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


        list_view.layoutManager = LinearLayoutManager(this.context)
        list_view.adapter = TopUserAdapter(topUsers,onItemClick = {
            //handleFrame(EmployeeStatsFragment())
        })
    }


    private fun handleFrame(fragment:Fragment): Boolean {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        fragmentTransaction.replace(R.id.frame1,fragment).commit()
        return true
    }

}