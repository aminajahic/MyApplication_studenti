package fit.ba.myapplication_studenti;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fit.ba.myapplication_studenti.helper.MyRunnable;
import fit.ba.myapplication_studenti.podaci.Ocjene;
import fit.ba.myapplication_studenti.podaci.Studenti;
import fit.ba.myapplication_studenti.podaci.SviPodaci;

public class MainActivity extends AppCompatActivity {
List<Ocjene> listaOcjena= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SviPodaci.GenerisiPodatke();
        Button btnDodaj= (Button) findViewById(R.id.btnDodaj);

        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();
            }
        });


        do_popuniPodatke();

    }

    private void openDialog() {

        StudentiDialog.newInstance(MainActivity.this, new MyRunnable<Studenti>() {
            @Override
            public void run(Studenti studenti) {

                Ocjene o=new Ocjene(studenti.redniBroj.toString(),studenti.ime+" "+studenti.prezime,"",5);
                listaOcjena= SviPodaci.ocjeneList;
                listaOcjena.add(o);
                do_popuniPodatke();
            }
        });
    }

    private void do_popuniPodatke() {


        ListView lista= (ListView) findViewById(R.id.listaOcjena);
        lista.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return SviPodaci.ocjeneList.size();
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
            public View getView(int position, View CV, ViewGroup parent) {
                LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                CV=inflater.inflate(R.layout.detalji_ocjena,parent,false);

                Ocjene o=SviPodaci.ocjeneList.get(position);

                ((TextView)CV.findViewById(R.id.redniBroj1)).setText(o.redniBroj.toString());
                ((TextView)CV.findViewById(R.id.imePrezime)).setText(o.imePrezime.toString());
                ((TextView)CV.findViewById(R.id.komentar)).setText(o.komentar.toString());
                ((TextView)CV.findViewById(R.id.ocjena)).setText(o.ocjena.toString());

                return CV;
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewL, int position, long id) {

               final Ocjene oo=SviPodaci.ocjeneList.get(position);

                AlertDialog.Builder adb= new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inf= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
              final View view=inf.inflate(R.layout.unos_komentara,null);


                final View finalView = view;
                adb.setTitle("Unos detalja").setView(view).setPositiveButton("Sacuvaj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText komentar= (EditText) view.findViewById(R.id.unosKomentara);
                        EditText ocjena= (EditText) view.findViewById(R.id.unosOcjene);

                        oo.komentar=komentar.getText().toString();
                        oo.ocjena=Integer.parseInt(ocjena.getText().toString());

                        do_popuniPodatke();


                    }
                }).setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                adb.show();




            }
        });
    }
}
