package gao.xuejuni.gestureclear;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_add_apk;
    private Dynamic dynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_add_apk = findViewById(R.id.btn_add_apk);

        btn_add_apk.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.btn_add_apk:

                loadDexClass();

                break;
        }

    }


    /**
     * 加载dex文件中的class，并调用其中的sayHello方法
     */
    private void loadDexClass() {

        File cacheFile = FileUtils.getCacheDir(getApplicationContext());
        String internalPath = cacheFile.getAbsolutePath() + File.separator + "dynamic_dex.jar";
        Log.d("当前的路径",internalPath);
        File desFile = new File(internalPath);
        try {
            if (!desFile.exists()) {
                desFile.createNewFile();
                FileUtils.copyFiles(this, "dynamic_dex.jar", desFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //下面开始加载dex class
        // 1: 需要加载的class类文件,dex 文件,可以有多个,用File.pathSeparator分割
        // 2: dex文件被加载后会被编译器优化，优化之后的dex存放路径，不可以为null。注意，注释中也提到需要一个应用私有的可写的一个路径，
        //    以防止应用被注入攻击，并且给出了例子 File dexOutputDir = context.getDir(“dex”, 0);
        // 3: 包含libraries的目录列表，同样用File.pathSeparator分割，如果没有则传null就行了
        // 4: 父类构造器,比如获取本地的classloder来加载项目
        DexClassLoader dexClassLoader = new DexClassLoader(internalPath, cacheFile.getAbsolutePath(), null, getClassLoader());
        try {
            Class libClazz = dexClassLoader.loadClass("gao.xuejuni.gestureclear.impl.DynamicImpl");
            dynamic = (Dynamic) libClazz.newInstance();
            if (dynamic != null)

                Toast.makeText(this, dynamic.sayHello(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
