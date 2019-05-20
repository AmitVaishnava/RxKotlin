package com.rxkotlin.wikipediasearch.view

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rxkotlin.R
import com.rxkotlin.wikipediasearch.model.Search

class WikiAdapter : RecyclerView.Adapter<WikiAdapter.ViewHolder>() {

    private var mSearches: List<Search>? = null

    fun setWikiData(searches: List<Search>) {
        mSearches = searches
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_wiki_child_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val search = mSearches!![i]
        viewHolder.mTitleValueTextView?.text = "NAME: " + search.title!!
        viewHolder.mSnippetValueTextView?.text = "Snippet Desc: " + Html.fromHtml(search.snippet)
        viewHolder.mTimeStampValueTextView?.text = "Time Stamp: " + search.timestamp!!
    }

    override fun getItemCount(): Int {
        return if (mSearches == null) 0 else mSearches!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mTitleValueTextView: TextView
        val mSnippetValueTextView: TextView
        val mTimeStampValueTextView: TextView

        init {
            mTitleValueTextView = itemView.findViewById(R.id.title_value_text_view)
            mSnippetValueTextView = itemView.findViewById(R.id.snippet_value_text_view)
            mTimeStampValueTextView = itemView.findViewById(R.id.time_stamp_value_text_view)
        }
    }
}
