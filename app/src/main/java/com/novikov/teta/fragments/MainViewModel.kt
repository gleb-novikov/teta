package com.novikov.teta.fragments

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.novikov.teta.App
import com.novikov.teta.R
import com.novikov.teta.database.Movie
import com.novikov.teta.movies.MoviesDataSourceImpl
import com.novikov.teta.movies.MoviesModel
import kotlinx.coroutines.*

typealias MyViewState = MainFragment.ViewState

class MainViewModel : ViewModel() {
    lateinit var navController: NavController
    private val model = MoviesModel(MoviesDataSourceImpl())

    val viewState: LiveData<MyViewState> get() = _viewState
    private val _viewState = MutableLiveData<MyViewState>()

    val dataList: LiveData<List<Movie>> get() = _dataList
    private val _dataList = MutableLiveData<List<Movie>>()

    @Suppress("BlockingMethodInNonBlockingContext")
    @DelicateCoroutinesApi
    fun refreshMovies() {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                _viewState.postValue(MyViewState(isDownloaded = true))
                Thread.sleep(2000)
                model.randomizeMovies()
            }
            withContext(Dispatchers.Main) {
                _dataList.postValue(model.getMovies())
                _viewState.postValue(MyViewState(isDownloaded = false))
            }
        }
    }

    fun loadMovies() {
        _dataList.postValue(model.getMovies())
    }

    fun navigateToMovie(movie: Movie) {
        Toast.makeText(App.applicationContext, movie.title, Toast.LENGTH_SHORT).show()
        navController.navigate(R.id.action_mainFragment_to_movieFragment)
    }
}