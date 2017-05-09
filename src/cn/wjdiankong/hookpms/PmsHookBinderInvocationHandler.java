package cn.wjdiankong.hookpms;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

/**
 * Created by jiangwei1-g on 2016/9/7.
 */
public class PmsHookBinderInvocationHandler implements InvocationHandler{

    private Object base;
    
    //应用正确的签名信息
    private String SIGN;
    private int hashCode = 0;

    public PmsHookBinderInvocationHandler(Object base, String sign, int hashCode) {
        try {
            this.base = base;
            this.SIGN = sign;
            this.hashCode = hashCode;
        } catch (Exception e) {
            Log.d("jw", "error:"+Log.getStackTraceString(e));
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("getPackageInfo".equals(method.getName())){
            Integer flag = (Integer)args[1];
            if(flag == PackageManager.GET_SIGNATURES){
            	Signature sign = new Signature(SIGN);
            	if(hashCode != 0){
            		try{
                		Class<?> clazz = sign.getClass();
                		Field mHaveHashCodeF = clazz.getDeclaredField("mHaveHashCode");
                		mHaveHashCodeF.setAccessible(true);
                		mHaveHashCodeF.set(sign, true);
                		Field mHashCodeF = clazz.getDeclaredField("mHashCode");
                		mHashCodeF.setAccessible(true);
                		mHashCodeF.set(sign, hashCode);
                	}catch(Exception e){
                		Log.i("jw", "hook error:"+Log.getStackTraceString(e));
                	}
            	}
            	PackageInfo info = (PackageInfo) method.invoke(base, args);
            	info.signatures[0] = sign;
            	return info;
            }
        }
        return method.invoke(base, args);
    }

}
