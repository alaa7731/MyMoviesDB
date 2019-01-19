package dubizzle.android.com.moviedb.utils

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.bigkoo.pickerview.OptionsPickerView
import dubizzle.android.com.moviedb.R


open class DialogUtils {
    companion object {
        fun showListPicker(activity: Context, title: String, list: List<String>,
                           onOptionsSelectListener: OptionsPickerView.OnOptionsSelectListener) {
            val singlePicker = OptionsPickerView.Builder(activity, onOptionsSelectListener)
                    .setTitleText(title)
                    .setSubmitText("Done")
                    .setCancelText("Cancel")
                    .setSubCalSize(12)
                    .setTitleSize(16)
                    .setSubmitColor(ContextCompat.getColor(activity, R.color.white))
                    .setCancelColor(ContextCompat.getColor(activity, R.color.white))
                    .setTitleColor(ContextCompat.getColor(activity, R.color.white))
                    .setTitleBgColor(ContextCompat.getColor(activity, R.color.colorPrimary))
                    .setBgColor(Color.WHITE)
                    .setContentTextSize(18)
                    .setLinkage(false)
                    .isCenterLabel(true)
                    .setCyclic(false, false, false)
                    .setSelectOptions(0)
                    .setOutSideCancelable(true)
                    .isDialog(false)
                    .build()

            singlePicker.setPicker(list)
            singlePicker.show()
         }
    }
}