package fit.ba.myapplication_studenti.helper;

import java.io.Serializable;

/**
 * Created by Developer on 02.02.2017..
 */

public interface MyRunnable<T> extends Serializable {


    void run(T t);

}
