package android.bignerdranch.com;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    //内部类
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        }

        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));

            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }
    }

    private class CrimePoliceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        }

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;
        private ImageButton mButton;

        public CrimePoliceHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_require_police_crime,parent,false));

            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mButton = (ImageButton) itemView.findViewById(R.id.PoliceButton);
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            if(viewType == 0)
                return new CrimeHolder(layoutInflater,parent);
            else
                return new CrimePoliceHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            if (getItemViewType(position) == 0)
                ((CrimeHolder)holder).bind(crime);
            else
                ((CrimePoliceHolder)holder).bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        @Override
        public int getItemViewType(int position){
            if(mCrimes.get(position).getRequires() == 1){
                return 1;
            }
            else{
                return 0;
            }
        }
    }
}
