package fit.ba.myapplication_studenti;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import fit.ba.myapplication_studenti.helper.MyRunnable;
import fit.ba.myapplication_studenti.podaci.Studenti;
import fit.ba.myapplication_studenti.podaci.SviPodaci;

/**
 * Created by Developer on 02.02.2017..
 */

public class StudentiDialog extends DialogFragment {

    public static void newInstance(FragmentActivity activity, MyRunnable<Studenti> onSuccess) {

        Bundle args = new Bundle();
        args.putSerializable("student",onSuccess);
        FragmentManager fm= activity.getSupportFragmentManager();
        StudentiDialog fragment = new StudentiDialog();
        fragment.setArguments(args);
        fragment.show(fm,"");

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view= inflater.inflate(R.layout.lista_studenata_fragment,container,false);


        ListView lista= (ListView) view.findViewById(R.id.listaStudenata);

        lista.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return SviPodaci.studentiList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View cV, ViewGroup parent) {
                LayoutInflater inf= (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cV=inf.inflate(R.layout.detalji_studenti,parent,false);

                Studenti s=SviPodaci.studentiList.get(position);


                ((TextView)cV.findViewById(R.id.ime)).setText(s.ime.toString());
                ((TextView)cV.findViewById(R.id.prezime)).setText(s.prezime.toString());



                return cV;
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyRunnable<Studenti> s= (MyRunnable<Studenti>) getArguments().getSerializable("student");
                Studenti student=SviPodaci.studentiList.get(position);
                s.run(student);
                dismiss();

            }
        });




        return view;
    }
}
