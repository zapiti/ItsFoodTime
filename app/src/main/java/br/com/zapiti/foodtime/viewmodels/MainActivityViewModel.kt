package br.com.zapiti.foodtime.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zapiti.foodtime.models.NicePlace
import br.com.zapiti.foodtime.repositories.NicePlaceRepository
import br.com.zapiti.foodtime.util.DoAsync

class MainActivityViewModel : ViewModel() {

    private var mNicePlaces: MutableLiveData<ArrayList<NicePlace>>  = MutableLiveData<ArrayList<NicePlace>>()
    private var mRepo: NicePlaceRepository = NicePlaceRepository()
    private val mIsUpdating = MutableLiveData<Boolean>()

    val nicePlaces: LiveData<ArrayList<NicePlace>>
        get() = mNicePlaces


    val isUpdating: LiveData<Boolean>
        get() = mIsUpdating

    fun init() {
        mRepo = NicePlaceRepository.getInstance()
        mNicePlaces = mRepo.nicePlaces
    }

    fun addNewValue(nicePlace: NicePlace) {
        mIsUpdating.value = true

        DoAsync(doInBackgroundTask = {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }, onPostExecuteTask = {

            val currentPlaces = ArrayList(mNicePlaces.value ?: ArrayList())
            currentPlaces.add(nicePlace)
            mNicePlaces.postValue(currentPlaces)
            mIsUpdating.postValue(false)
        }).execute()
    }
}
