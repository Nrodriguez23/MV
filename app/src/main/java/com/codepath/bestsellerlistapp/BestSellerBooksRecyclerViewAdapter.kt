package com.codepath.bestsellerlistapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.bestsellerlistapp.R.id

/**
 * [RecyclerView.Adapter] that can display a [BestSellerBook] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class BestSellerBooksRecyclerViewAdapter(
    val context: Context,
    private val books: List<BestSellerBook>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<BestSellerBooksRecyclerViewAdapter.BookViewHolder>()
    {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_best_seller_book, parent, false)
        return BookViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: BestSellerBook? = null
        val mBookTitle: TextView = mView.findViewById<View>(id.book_title) as TextView
        val mBookAuthor: TextView = mView.findViewById<View>(id.book_author) as TextView
        val mBookRanking : TextView = mView.findViewById<View>(id.ranking) as TextView
        val book_image = mView.findViewById<ImageView>(id.book_image)
        val mBook_description : TextView = mView.findViewById<View>(id.book_description) as TextView
        val mBookButton = mView.findViewById(id.buy_button) as Button
            override fun toString(): String {
            return mBookTitle.toString() + " '" + mBookAuthor.text + "'"
        }
    }
//HI
        //hi
    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: BookViewHolder, position: Int)   {
        val book = books[position]

        holder.mItem = book
        Glide
            .with(context)
            .load(book.bookImageUrl)
            .fitCenter()
            .into(holder.book_image);

        holder.mBookTitle.text = book.title
        holder.mBookAuthor.text = book.author
        holder.mBookRanking.text = book.rank
        holder.mBook_description.text =book.description
        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }
            holder.mBookButton.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(book.amazonUrl))
                startActivity(it.context, browserIntent, null)
            }

        }
    }


    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return books.size
    }
}