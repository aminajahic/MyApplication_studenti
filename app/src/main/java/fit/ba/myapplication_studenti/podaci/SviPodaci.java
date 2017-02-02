package fit.ba.myapplication_studenti.podaci;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 03.01.2017..
 */

public class SviPodaci {


    public static List<Ocjene> ocjeneList= new ArrayList<>();
    public static List<Studenti> studentiList= new ArrayList<>();

    public static void  GenerisiPodatke()
    {


        studentiList.add(new Studenti(1,"Amina","Jahic"));
        studentiList.add(new Studenti(1,"Emir","Jahic"));
        studentiList.add(new Studenti(1,"Melina","Jahic"));

    }
}
