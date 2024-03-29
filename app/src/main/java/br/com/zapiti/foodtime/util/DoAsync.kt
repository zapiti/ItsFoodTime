package br.com.zapiti.foodtime.util

import android.os.AsyncTask


class DoAsync(val doInBackgroundTask: () -> Unit, val onPostExecuteTask: () -> Unit) : AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg params: Void?): Void? {
        doInBackgroundTask()
        return null
    }

    override fun onPostExecute(result: Void?) {
        onPostExecuteTask()
    }
}
