package com.vaxapp.domain.interactor;

import com.vaxapp.domain.executor.PostExecutionThread;
import com.vaxapp.domain.executor.ThreadExecutor;
import com.vaxapp.domain.repository.PhotoRepository;
import java.util.Map;
import javax.inject.Inject;
import rx.Observable;

public class GetPhotos extends UseCase {

    public static final int DEFAULT_NUMBER_OF_RESULTS = 50;
    public static final String NUMBER_OF_RESULTS_KEY = "number_of_results";

    private final PhotoRepository photoRepository;

    @Inject
    public GetPhotos(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                     PhotoRepository photoRepository) {
        super(threadExecutor, postExecutionThread);
        this.photoRepository = photoRepository;
    }

    @Override
    protected <T> Observable buildUseCaseObservable(Map<String, T> parameters) {
        Integer numberOfResults = parameters == null || !parameters.containsKey(NUMBER_OF_RESULTS_KEY) ? DEFAULT_NUMBER_OF_RESULTS
            : (Integer) parameters.get(NUMBER_OF_RESULTS_KEY);
        return photoRepository.getPhotoList(numberOfResults);
    }
}
