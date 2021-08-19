package com.novikov.teta.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.novikov.teta.R
import com.novikov.teta.database.Movie
import com.squareup.picasso.Picasso

class MoviesAdapter(
    private val listener: (movie: Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private var dataList: MutableList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.itemView.setOnClickListener {
            listener(dataList[position])
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun initData(movies: List<Movie>) {
        dataList.clear()
        dataList.addAll(movies)
        notifyDataSetChanged()
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var movieImage: ImageView = view.findViewById(R.id.itemMovieImage)
        private var movieTitle: TextView = view.findViewById(R.id.itemMovieTitle)
        private var movieDescription: TextView = view.findViewById(R.id.itemMovieDescription)
        private var movieAge: TextView = view.findViewById(R.id.itemMovieAge)
        private var movieStars: List<ImageView> = listOf(
            view.findViewById(R.id.itemMovieStar1),
            view.findViewById(R.id.itemMovieStar2),
            view.findViewById(R.id.itemMovieStar3),
            view.findViewById(R.id.itemMovieStar4),
            view.findViewById(R.id.itemMovieStar5)
        )

        init {
            movieImage.clipToOutline = true
        }

        @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
        fun bind(item: Movie) {
            Picasso.get().load(item.imageUrl).into(movieImage)
            movieTitle.text = item.title
            movieDescription.text = item.description
            movieAge.text = "${item.ageRestriction}+"
            for (i in 0 until 5) {
                val idImage = if (i + 1 <= item.rateScore) R.drawable.ic_star_fill
                    else R.drawable.ic_star_stroke
                movieStars[i].setImageDrawable(itemView.context.getDrawable(idImage))
            }
        }
    }
}