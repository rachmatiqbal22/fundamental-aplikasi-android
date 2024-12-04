package com.example.test.data.response

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.test.databinding.ActivityDetailBinding
import androidx.core.text.HtmlCompat
import com.example.test.R


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail"

        val eventName = intent.getStringExtra(EXTRA_EVENT_NAME)
        val eventDate = intent.getStringExtra(EXTRA_EVENT_DATE)
        val eventDescription = intent.getStringExtra(EXTRA_EVENT_DESCRIPTION)
        val eventImage = intent.getStringExtra(EXTRA_EVENT_IMAGE)
        val eventLink = intent.getStringExtra(EXTRA_EVENT_LINK)
        val eventOrganizer = intent.getStringExtra(EXTRA_EVENT_ORGANIZER)
        val eventQuota = intent.getIntExtra(EXTRA_EVENT_QUOTA, 0)
        val eventRegistrants = intent.getIntExtra(EXTRA_EVENT_REGISTRANTS, 0)
        val remainingQuota = (eventQuota - eventRegistrants).coerceAtLeast(0)


        Log.d("DetailActivity", "Quota: $eventQuota, Registrants: $eventRegistrants")

        val eventFullDescription = intent.getStringExtra(EXTRA_EVENT_FULL_DESCRIPTION)

        (eventQuota - eventRegistrants).coerceAtLeast(0)

        binding.tvEventQuota.text = getString(R.string.quota_remaining, eventQuota)
        binding.tvEventName.text = eventName
        binding.tvEventDate.text = eventDate
        binding.tvEventDescription.text = eventDescription
        binding.tvEventOrganizer.text = eventOrganizer
        binding.tvEventQuota.text = getString(R.string.quota_remaining, remainingQuota)
        binding.tvEventFullDescription.text = HtmlCompat.fromHtml(
            eventFullDescription.orEmpty(),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )


        Glide.with(this)
            .load(eventImage)
            .into(binding.ivEventImage)

        binding.btnRegister.setOnClickListener {
            if (!eventLink.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(eventLink))
                startActivity(intent)
            }
        }
    }

    companion object {
        const val EXTRA_EVENT_NAME = "extra_event_name"
        const val EXTRA_EVENT_DATE = "extra_event_date"
        const val EXTRA_EVENT_DESCRIPTION = "extra_event_description"
        const val EXTRA_EVENT_IMAGE = "extra_event_image"
        const val EXTRA_EVENT_LINK = "extra_event_link"
        const val EXTRA_EVENT_ORGANIZER = "extra_event_organizer"
        const val EXTRA_EVENT_QUOTA = "extra_event_quota"
        const val EXTRA_EVENT_REGISTRANTS = "extra_event_registrants"
        const val EXTRA_EVENT_FULL_DESCRIPTION = "extra_event_full_description"
    }
}
