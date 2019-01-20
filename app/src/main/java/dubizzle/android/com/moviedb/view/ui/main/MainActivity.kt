package dubizzle.android.com.moviedb.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dubizzle.android.com.moviedb.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_home.*
import javax.inject.Inject
import android.widget.TextView
import android.view.Window.FEATURE_NO_TITLE
import android.app.Dialog
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.bigkoo.pickerview.OptionsPickerView
import dubizzle.android.com.moviedb.utils.DialogUtils
import java.util.*


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUI()
    }

    private fun initializeUI() {
        supportFragmentManager.beginTransaction().add(R.id.frameMovies, MovieListFragment(), MovieListFragment::class.java.name).commit()
        filter.setOnClickListener {
            showFilterDialog()
        }
    }

    var startDate = ""
    var endDate = ""

    private fun showFilterDialog() {

        val dialog = Dialog(this@MainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

        dialog.setContentView(R.layout.year_filter_dialog)

        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent)

        val bFilter = dialog.findViewById<Button>(R.id.bFilter)
        val bReset = dialog.findViewById<Button>(R.id.bReset)
        val tvYearMax = dialog.findViewById<TextView>(R.id.tvYearMax)
        val tvYearMin = dialog.findViewById<TextView>(R.id.tvYearMin)
        val llYearMin = dialog.findViewById<LinearLayout>(R.id.llYearMin)
        val llYearMax = dialog.findViewById<LinearLayout>(R.id.llYearMax)

        if (startDate.isNotEmpty()) {
            tvYearMin.text = startDate
        }
        if (endDate.isNotEmpty()) {
            tvYearMax.text = endDate
        }

        llYearMin.setOnClickListener {
            dialog.hide()
            DialogUtils.showListPicker(this@MainActivity, "Min Year", getYears(),
                    OptionsPickerView.OnOptionsSelectListener { options1, options2, options3, v ->
                        startDate = getYears()[options1]
                        tvYearMin.text = startDate
                        dialog.show()
                    })
        }
        llYearMax.setOnClickListener {
            dialog.hide()
            DialogUtils.showListPicker(this@MainActivity, "Max Year", getYears(),
                    OptionsPickerView.OnOptionsSelectListener { options1, options2, options3, v ->
                        endDate = getYears()[options1]
                        tvYearMax.text = endDate
                        dialog.show()

                    })
        }

        bFilter.setOnClickListener {
            if (startDate.isEmpty()) {
                Toast.makeText(this@MainActivity, getString(R.string.choose_start_date), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (endDate.isEmpty()) {
                Toast.makeText(this@MainActivity, getString(R.string.choose_end_date), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            (supportFragmentManager
                    .findFragmentByTag(MovieListFragment::class.java.name) as MovieListFragment)
                    .filterMovies(startDate.toInt(), endDate.toInt())
            dialog.dismiss()

        }
        bReset.setOnClickListener {
            (supportFragmentManager
                    .findFragmentByTag(MovieListFragment::class.java.name) as MovieListFragment)
                    .resetFilter()
            dialog.dismiss()

        }

        dialog.show()


    }

    private fun getYears(): List<String> {
        var years = ArrayList<String>(0)
        for (i in Calendar.getInstance().get(Calendar.YEAR) downTo 1970) {
            years.add(i.toString())
        }
        return years
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}
