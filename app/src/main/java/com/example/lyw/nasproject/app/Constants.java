package com.example.lyw.nasproject.app;

import java.io.File;

/**
 * Created by Administrator on 2018/4/4.
 */

public class Constants {

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
    public static final String LOCATIONLAT = "locat_lat";
    public static final String LOCATIONLONG ="locat_long" ;
}
