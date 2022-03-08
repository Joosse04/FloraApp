package org.izv.flora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.izv.flora.model.Repository;

public class DeleteImgViewModel extends AndroidViewModel {

    // No es necesario usar esta clase

    private Repository repository;

    public DeleteImgViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<Long> getDeleteImgLiveData() {
        return repository.getDeleteImgLiveData();
    }


}
