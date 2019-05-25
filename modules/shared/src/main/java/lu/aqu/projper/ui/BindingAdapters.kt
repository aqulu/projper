package lu.aqu.projper.ui

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import lu.aqu.projper.shared.R

@BindingAdapter("tags", "onTagClick", requireAll = true)
fun TextView.setTags(tags: List<String>?, onTagClick: OnTagClickListener) {
    movementMethod = LinkMovementMethod.getInstance()

    text = SpannableStringBuilder().apply {
        tags?.forEachIndexed { index, tag ->
            val formattedTag = context.getString(R.string.tag_format, tag)
            val span = object : ClickableSpan() {
                override fun onClick(widget: View) = onTagClick.onClick(tag)
            }
            append(formattedTag, span, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            if (index < tags.lastIndex) append(" ")
        }
    }
}
