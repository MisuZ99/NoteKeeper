package com.trainingcourse.notekeeper.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.trainingcourse.notekeeper.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}