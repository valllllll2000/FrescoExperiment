package com.vaxapp.domain.interactor;

import com.vaxapp.domain.executor.PostExecutionThread;
import com.vaxapp.domain.executor.ThreadExecutor;
import com.vaxapp.domain.repository.PhotoRepository;
import java.util.Map;
import javax.inject.Inject;
import rx.Observable;

public class GetPhotos extends UseCase {

    private final PhotoRepository photoRepository;

    @Inject
    public GetPhotos(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                     PhotoRepository photoRepository) {
        super(threadExecutor, postExecutionThread);
        this.photoRepository = photoRepository;
    }

    @Override
    protected <T> Observable buildUseCaseObservable(Map<String, T> parameters) {
        return photoRepository.getPhotoList();
    }
}
