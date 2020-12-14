package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.DepartmentEmployeeAdapter
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.fragment_employee_stats.*


class EmployeeStatsFragment : Fragment() {
    private val topUsers = listOf(
            User("Ізтілеуов","Мақсат","Атқарушы директор","маркетинг","алтын","https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",37),
            User("Төлепберген","Дина","Атқарушы директор","аналитика","күміс","https://i.pinimg.com/originals/7d/1a/3f/7d1a3f77eee9f34782c6f88e97a6c888.jpg",100),
            User("Тілешова","Мария","Атқарушы директор","есеп","мыс","https://www.bigredcloud.com/wp-content/uploads/4-tips-for-taking-professional-profile-pictures.jpg",68),
            User("Ізтілеуов","Мағжан","Атқарушы директор","қаржы","алтын","https://lh3.googleusercontent.com/proxy/yohW7_PHtDMWlfJjJfpjforLiNbBpcDhbxBiuAOK656bbBjFO_9KFcxFkrGp_oYukhKSBru7bj2zyyssOtlAAB8d_8kSSL0cBTXkgTHNwHtqNQjt2hTsxrefVgey",70),
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

//        val bundleData = this.arguments
//            var passedDepName = arguments?.getString("DEPNAME","hjhjhjh")
//            Log.d("passedData",passedDepName)
//            emp_stat_title.text = passedDepName+"/nқызметкерлері"
        return inflater.inflate(R.layout.fragment_employee_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: Bundle? = this.arguments
        val data : String? = bundle?.getString("DEPNAME")
        println(data)
    }

    override fun onResume() {
        super.onResume()

        department_emp_list.layoutManager = LinearLayoutManager(this.context)
        department_emp_list.adapter = DepartmentEmployeeAdapter(topUsers.sortedByDescending { it.workPercent },onItemClick = {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame1, ProfileFragment())
                .commit()
        })
        back_to_department.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame1, DepartmentFragment())
                .commit()
        }
    }
}