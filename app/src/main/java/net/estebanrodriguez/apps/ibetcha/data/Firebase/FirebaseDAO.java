package net.estebanrodriguez.apps.ibetcha.data.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.estebanrodriguez.apps.ibetcha.data.DataAccessObject;
import net.estebanrodriguez.apps.ibetcha.model.Agreement;
import net.estebanrodriguez.apps.ibetcha.model.User;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class FirebaseDAO implements DataAccessObject {

    private static FirebaseDAO INSTANCE = new FirebaseDAO();

    private FirebaseDatabase mDatabase;
    private boolean isDataReady = false;
    private boolean isUser = false;


    private FirebaseDAO(){

        mDatabase = FirebaseDatabase.getInstance();
    }

    public static FirebaseDAO getInstance(){
        return INSTANCE;
    }

    public void setDataReady(boolean dataReady) {
        isDataReady = dataReady;
    }

    public void setIsUser(boolean user) {
        isUser = user;
    }


    @Override
    public User getUser(String userId) {

        final User[] user = new User[1];

        Observable<DataSnapshot> observable = getUserSnapShotObservable(userId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataSnapshot>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull DataSnapshot dataSnapshot) {
                        user[0] = getUserFromDataSnapshot(dataSnapshot);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return user[0];
    }

    @Override
    public void addUser(User user) {

        mDatabase.getReference("users").child(user.getId()).setValue(user);

    }

    @Override
    public User removeUser(String userId) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean hasUser(final String userId) {


        Observable<DataSnapshot> observable = getUserSnapShotObservable(userId);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataSnapshot>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull DataSnapshot dataSnapshot) {
                        while (!isDataReady){
                        }
                        setIsUser(dataSnapshot.exists());
                        isDataReady = false;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return isUser;
    }

    @Override
    public void AddAgreement(Agreement agreement) {

    }

    @Override
    public Agreement getAgreement(String agreementId) {
        return null;
    }


    private DataSnapshot getUserSnapshot(String userId){

        DatabaseReference userRef = mDatabase.getReference("users").child(userId);
        DataSnapshotListener listener = new DataSnapshotListener(this);
        userRef.addListenerForSingleValueEvent(listener);
        return listener.getDataSnapshot();
    }

    private User getUserFromDataSnapshot(DataSnapshot dataSnapshot){
        return dataSnapshot.getValue(User.class);
    }

    private Observable<DataSnapshot> getUserSnapShotObservable(final String userId){

        Observable<DataSnapshot> observable = Observable.fromCallable(new Callable<DataSnapshot>() {
            @Override
            public DataSnapshot call() throws Exception {
                return getUserSnapshot(userId);
            }
        });
        return observable;

    }
}
