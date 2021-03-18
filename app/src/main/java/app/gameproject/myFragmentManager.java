package app.gameproject;

import androidx.fragment.app.Fragment;

import java.util.Objects;

public class myFragmentManager extends Fragment {

    //프래그먼트 종료 -> 홈 프래그먼트로
    public void goToMain(Fragment fragment){
        androidx.fragment.app.FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(fragment).commit();
        fragmentManager.popBackStack();
    }
}
