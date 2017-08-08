package net.estebanrodriguez.apps.ibetcha.data.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class DataSnapshotListener implements ValueEventListener {

    DataSnapshot mDataSnapshot;
    FirebaseDAO mFirebaseDAO;

    DataSnapshotListener(FirebaseDAO firebaseDao){
        mFirebaseDAO = firebaseDao;
    }

    public DataSnapshot getDataSnapshot() {
        return mDataSnapshot;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        mDataSnapshot = dataSnapshot;
        mFirebaseDAO.setDataReady(true);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
