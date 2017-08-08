package net.estebanrodriguez.apps.ibetcha.ui.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.estebanrodriguez.apps.ibetcha.R;
import net.estebanrodriguez.apps.ibetcha.data.DataAccessObject;
import net.estebanrodriguez.apps.ibetcha.data.Firebase.FirebaseDAO;
import net.estebanrodriguez.apps.ibetcha.model.User;
import net.estebanrodriguez.apps.ibetcha.ui.fragments.SearchFragment;
import net.estebanrodriguez.apps.ibetcha.ui.adapters.NavigationDrawerAdapter;

import java.util.Arrays;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener, ListView.OnItemClickListener {

    private static final int RC_SIGN_IN = 1;
    FirebaseAuth mAuth;
    DataAccessObject mDAO;
    User mCurrentUser;

    @BindView(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_drawer_list_view)
    ListView mListView;
    @BindView(R.id.main_fragment_frame)
    FrameLayout mFragmentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Timber.plant(new Timber.DebugTree());


        mAuth = FirebaseAuth.getInstance();
        mDAO = FirebaseDAO.getInstance();
        signInUser();
        populateNavigationDrawer();


    }

    private void populateNavigationDrawer() {

        String[] navigationStrings = getResources().getStringArray(R.array.navigation_items);
        ListAdapter navigationDrawerAdapter = new NavigationDrawerAdapter(this, navigationStrings);
        mListView.setAdapter(navigationDrawerAdapter);
        mListView.setOnItemClickListener(this);
    }

    private void signInUser(){
        if (mAuth.getCurrentUser() != null) {






        } else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(
                                    Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                            .build(),
                    RC_SIGN_IN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {

            String userId = firebaseUser.getUid();

            if (mDAO.hasUser(userId)) {
                setCurrentUser(userId);
            } else {
                mCurrentUser = new User();
                mCurrentUser.setId(firebaseUser.getUid());
                mCurrentUser.setName(firebaseUser.getDisplayName());
                mDAO.addUser(mCurrentUser);
            }
        }
    }



    private void setCurrentUser(final String userId) {



            Observable<User> observable = Observable.fromCallable(new Callable<User>() {
                @Override
                public User call() throws Exception {
                    return mDAO.getUser(userId);
                }
            });
            observable.observeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<User>() {
                        @Override
                        public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@io.reactivex.annotations.NonNull User user) {
                            mCurrentUser = user;
                        }

                        @Override
                        public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mDrawerLayout.closeDrawers();
        switch (position){
            case 0: {
                Fragment fragment = new SearchFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.main_fragment_frame, fragment,SearchFragment.class.getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
        }
    }



}
